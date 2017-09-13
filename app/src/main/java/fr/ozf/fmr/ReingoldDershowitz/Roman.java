package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;


public class Roman
   extends Date
{
   public long year;
   public int month;
   public int event;
   public int count;
   public boolean leapDay;
   public static final int KALENDS = 1;
   public static final int NONES = 2;
   public static final int IDES = 3;

   public Roman()
   {
   }

   public Roman(long date)
   {
      super(date);
   }

   public Roman(Date date)
   {
      super(date);
   }

   public Roman(long year, int month, int event, int count, boolean leapDay)
   {
      this.year = year;
      this.month = month;
      this.event = event;
      this.count = count;
      this.leapDay = leapDay;
   }


   public static long toFixed(long year, int month, int event, int count, boolean leapDay)
   {
      long approx = 0L;
      if (event == 1)
      {
         approx = Julian.toFixed(year, month, 1);
      }
      else if (event == 2)
      {
         approx = Julian.toFixed(year, month, nonesOfMonth(month));
      }
      else if (event == 3)
      {
         approx = Julian.toFixed(year, month, idesOfMonth(month));
      }

      return approx -
         count + (
         (Julian.isLeapYear(year)) && (month == 3) && (event == 1) && (16 >= count) && (count >= 6) ? 0 : 1) + (
         leapDay ? 1 : 0);
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.event, this.count, this.leapDay);
   }


   public void fromFixed(long date)
   {
      Julian j = new Julian(date);
      int month = j.month;
      int day = j.day;
      long year = j.year;
      int monthPrime = adjustedMod(month + 1, 12);
      long yearPrime = monthPrime == 1 ? year + 1L : year;
      long kalends1 = toFixed(yearPrime, monthPrime, 1, 1, false);
      if (day == 1)
      {
         this.year = year;
         this.month = month;
         this.event = 1;
         this.count = 1;
         this.leapDay = false;
      }
      else if (day <= nonesOfMonth(month))
      {
         this.year = year;
         this.month = month;
         this.event = 2;
         this.count = (nonesOfMonth(month) - day + 1);
         this.leapDay = false;
      }
      else if (day <= idesOfMonth(month))
      {
         this.year = year;
         this.month = month;
         this.event = 3;
         this.count = (idesOfMonth(month) - day + 1);
         this.leapDay = false;
      }
      else if ((month != 2) || (!Julian.isLeapYear(year)))
      {
         this.year = yearPrime;
         this.month = monthPrime;
         this.event = 1;
         this.count = ((int) (kalends1 - date + 1L));
         this.leapDay = false;
      }
      else if (day < 25)
      {
         this.year = year;
         this.month = 3;
         this.event = 1;
         this.count = (30 - day);
         this.leapDay = false;
      }
      else
      {
         this.year = year;
         this.month = 3;
         this.event = 1;
         this.count = (31 - day);
         this.leapDay = (day == 25);
      }
   }

   public void fromArray(int[] a)
   {
      this.year = a[0];
      this.month = a[1];
      this.event = a[2];
      this.count = a[3];
      this.leapDay = (a[4] != 0);
   }


   public static int idesOfMonth(int month)
   {
      return (month == 3) || (month == 5) || (month == 7) || (month == 10) ? 15 : 13;
   }


   public static int nonesOfMonth(int month)
   {
      return idesOfMonth(month) - 8;
   }


   protected String toStringFields()
   {
      return
         "year=" + this.year + ",month=" + this.month + ",event=" + this.event + ",count=" + this.count + ",leapDay=" + this.leapDay;
   }


   public static final String[] countNames = {
      "",
      "pridie ",
      "ante diem iii ",
      "ante diem iv ",
      "ante diem v ",
      "ante diem vi ",
      "ante diem vii ",
      "ante diem viii ",
      "ante diem ix ",
      "ante diem x ",
      "ante diem xi ",
      "ante diem xii ",
      "ante diem xiii ",
      "ante diem xiv ",
      "ante diem xv ",
      "ante diem xvi ",
      "ante diem xvii ",
      "ante diem xviii ",
      "ante diem xix "};


   public static final String[] eventNames = {
      "Kalends",
      "Nones",
      "Ides"};

   public String format()
   {
      return MessageFormat.format("{0}{1} {2}, {3,number,#} {4}",
         new Object[]{
            this.leapDay ? "ante diem bis vi " : nameFromNumber(this.count, countNames),
            nameFromNumber(this.event, eventNames),
            nameFromMonth(this.month, Gregorian.monthNames),
            new Long(this.year < 0L ? -this.year : this.year),
            this.year < 0L ? "B.C.E." : "C.E."});
   }


   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Roman))
      {
         return false;
      }
      Roman o = (Roman) obj;


      return (o.year == this.year) && (o.month == this.month) && (o.event == this.event) && (o.count == this.count) && (o.leapDay == this.leapDay);
   }
}