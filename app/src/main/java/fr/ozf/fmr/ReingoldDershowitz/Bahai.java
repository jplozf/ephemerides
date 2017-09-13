package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class Bahai
   extends Date
{
   public long major;
   public int cycle;
   public int year;
   public int month;
   public int day;

   public Bahai()
   {
   }

   public Bahai(long date)
   {
      super(date);
   }

   public Bahai(Date date)
   {
      super(date);
   }

   public Bahai(long major, int cycle, int year, int month, int day)
   {
      this.major = major;
      this.cycle = cycle;
      this.year = year;
      this.month = month;
      this.day = day;
   }

   public static final long EPOCH = Gregorian.toFixed(1844L, 3, 21);

   public static final int AYYAM_I_HA = 0;

   public static long toFixed(long major, int cycle, int year, int month, int day)
   {
      long gYear = 361L * (major - 1L)
         + 19 * (cycle - 1)
         + year
         - 1L
         + Gregorian.yearFromFixed(EPOCH);
      long monthDays;
      if (month == 0)
      {
         monthDays = 342L;
      }
      else if (month == 19)
      {
         monthDays = Gregorian.isLeapYear(gYear + 1L) ? 'ś' : 'Ś';
      }
      else
      {
         monthDays = 19 * (month - 1);
      }
      return Gregorian.toFixed(gYear, 3, 20) + monthDays + day;
   }

   public long toFixed()
   {
      return toFixed(this.major, this.cycle, this.year, this.month, this.day);
   }

   public void fromFixed(long date)
   {
      long gYear = Gregorian.yearFromFixed(date);
      long start = Gregorian.yearFromFixed(EPOCH);
      long years = gYear
         - start - (date <= Gregorian.toFixed(gYear, 3, 20) ? 1 : 0);
      this.major = (1L + quotient(years, 361.0D));
      this.cycle = (1 + (int) quotient(mod(years, 361L), 19.0D));
      this.year = (1 + (int) mod(years, 19L));
      long days = date - toFixed(this.major, this.cycle, this.year, 1, 1);
      if (date >= toFixed(this.major, this.cycle, this.year, 19, 1))
      {
         this.month = 19;
      }
      else if (date >= toFixed(this.major, this.cycle, this.year, 0, 1))
      {
         this.month = 0;
      }
      else
      {
         this.month = ((int) (1L + quotient(days, 19.0D)));
      }
      this.day = ((int) (date + 1L - toFixed(this.major, this.cycle, this.year, this.month, 1)));
   }

   public void fromArray(int[] a)
   {
      this.major = a[0];
      this.cycle = a[1];
      this.year = a[2];
      this.month = a[3];
      this.day = a[4];
   }

   public static long newYear(long gYear)
   {
      return Gregorian.toFixed(gYear, 3, 21);
   }

   protected String toStringFields()
   {
      return "major=" + this.major + ",cycle=" + this.cycle + ",year=" + this.year + ",month=" + this.month + ",day=" + this.day;
   }

   public static final String[] dayOfWeekNames =
   {
      "Jamal",
      "Kamal",
      "Fidal",
      "`Idal",
      "Istijlal",
      "Istiqlal",
      "Jalal"
   };

   public static final String[] dayOfMonthNames =
   {
      "Baha'",
      "Jalal",
      "Jamal",
      "`Azamat",
      "Nur",
      "Rahmat",
      "Kalimat",
      "Kamal",
      "Asma'",
      "`Izzat",
      "Mashiyyat",
      "`Ilm",
      "Qudrat",
      "Qawl",
      "Masa'il",
      "Sharaf",
      "Sultan",
      "Mulk",
      "`Ala'"
   };

   public static final String[] monthNames =
   {
      "Ayyam-i-Ha",
      "Baha'",
      "Jalal",
      "Jamal",
      "`Azamat",
      "Nur",
      "Rahmat",
      "Kalimat",
      "Kamal",
      "Asma'",
      "`Izzat",
      "Mashiyyat",
      "`Ilm",
      "Qudrat",
      "Qawl",
      "Masa'il",
      "Sharaf",
      "Sultan",
      "Mulk",
      "`Ala'"
   };

   public static final String[] yearNames =
   {
      "Alif",
      "Ba'",
      "Ab",
      "Dal",
      "Bab",
      "Vav",
      "Abad",
      "Jad",
      "Baha'",
      "Hubb",
      "Bahhaj",
      "Javab",
      "Ahad",
      "Vahhab",
      "Vidad",
      "Badi'",
      "Bahi",
      "Abha",
      "Vahid"
   };

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2}, {3} of Vahid {4}, Kull-i-Shay {5} B.E.",
         new Object[]
         {
            nameFromDayOfWeek(toFixed(), dayOfWeekNames),
            nameFromNumber(this.day, dayOfMonthNames),
            nameFromMonth(this.month + 1, monthNames),
            nameFromNumber(this.year, yearNames),
            new Integer(this.cycle),
            new Long(this.major)
         });
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Bahai))
      {
         return false;
      }
      Bahai o = (Bahai) obj;

      return (o.major == this.major) && (o.cycle == this.cycle) && (o.year == this.year) && (o.month == this.month) && (o.day == this.day);
   }
}
