package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class Gregorian extends StandardDate
{
   public static final long EPOCH = 1L;

   public Gregorian()
   {
   }

   public Gregorian(long date)
   {
      super(date);
   }

   public Gregorian(Date date)
   {
      super(date);
   }

   public Gregorian(long year, int month, int day)
   {
      super(year, month, day);
   }

   public static long toFixed(long year, int month, int day)
   {
      return 0L
         + 365L * (year - 1L)
         + quotient(year - 1L, 4.0D)
         - quotient(year - 1L, 100.0D)
         + quotient(year - 1L, 400.0D)
         + quotient(367 * month - 362, 12.0D) + (isLeapYear(year) ? -1 : month <= 2 ? 0 : -2)
         + day;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }

   public void fromFixed(long date)
   {
      this.year = yearFromFixed(date);
      long priorDays = date - toFixed(this.year, 1, 1);
      int correction
         = isLeapYear(this.year) ? 1 : date < toFixed(this.year, 3, 1) ? 0 : 2;
      this.month = ((int) quotient(12L * (priorDays + correction) + 373L, 367.0D));
      this.day = ((int) (date - toFixed(this.year, this.month, 1) + 1L));
   }

   public static long altFixedFromGregorian(long year, int month, int day)
   {
      long m = adjustedMod(month - 2, 12);
      long y = year + quotient(month + 9, 12.0D);
      return -306L
         + 365L * (y - 1L)
         + quotient(y - 1L, 4.0D)
         - quotient(y - 1L, 100.0D)
         + quotient(y - 1L, 400.0D)
         + quotient(3L * m - 1L, 5.0D)
         + 30L * (m - 1L)
         + day;
   }

   public void altGregorianFromFixed(long date)
   {
      long y = yearFromFixed(0L + date + 306L);
      long priorDays = date - toFixed(y - 1L, 3, 1);
      this.month = ((int) adjustedMod(quotient(5L * priorDays + 155L, 153.0D) + 2L, 12L));
      this.year = (y - quotient(this.month + 9, 12.0D));
      this.day = ((int) (1L + date - toFixed(this.year, this.month, 1)));
   }

   public static boolean isLeapYear(long gYear)
   {
      boolean result = false;

      if (mod(gYear, 4L) == 0L)
      {
         long n = mod(gYear, 400L);
         if ((n != 100L) && (n != 200L) && (n != 300L))
         {
            result = true;
         }
      }
      return result;
   }

   public static long yearFromFixed(long date)
   {
      long l0 = date - 1L;
      long n400 = quotient(l0, 146097.0D);
      long d1 = mod(l0, 146097L);
      long n100 = quotient(d1, 36524.0D);
      long d2 = mod(d1, 36524L);
      long n4 = quotient(d2, 1461.0D);
      long d3 = mod(d2, 1461L);
      long n1 = quotient(d3, 365.0D);
      long year = 400L * n400 + 100L * n100 + 4L * n4 + n1;
      return (n100 == 4L) || (n1 == 4L) ? year : year + 1L;
   }

   public static long altGregorianYearFromFixed(long date)
   {
      long approx = quotient(date - 1L + 2L, 365.2425D);
      long start = 1L
         + 365L * approx
         + quotient(approx, 4.0D)
         - quotient(approx, 100.0D)
         + quotient(approx, 400.0D);
      return date < start ? approx : approx + 1L;
   }

   public int lastDayOfMonth()
   {
      switch (this.month)
      {
         case 2:
            if (isLeapYear(this.year))
            {
               return 29;
            }
            return 28;
         case 4:
         case 6:
         case 9:
         case 11:
            return 30;
      }
      return 31;
   }

   public long dayNumber()
   {
      return difference(toFixed(this.year - 1L, 12, 31), toFixed());
   }

   public long daysRemaining()
   {
      return difference(toFixed(), toFixed(this.year, 12, 31));
   }

   public static long independenceDay(long gYear)
   {
      return toFixed(gYear, 7, 4);
   }

   public static long laborDay(long gYear)
   {
      return firstKDay(1, toFixed(gYear, 9, 1));
   }

   public static long memorialDay(long gYear)
   {
      return lastKDay(1, toFixed(gYear, 5, 31));
   }

   public static long electionDay(long gYear)
   {
      return firstKDay(2, toFixed(gYear, 11, 2));
   }

   public static long daylightSavingStart(long gYear)
   {
      return firstKDay(0, toFixed(gYear, 4, 1));
   }

   public static long daylightSavingEnd(long gYear)
   {
      return lastKDay(0, toFixed(gYear, 10, 31));
   }

   public static long christmas(long gYear)
   {
      return toFixed(gYear, 12, 25);
   }

   public static long advent(long gYear)
   {
      return kDayNearest(toFixed(gYear, 11, 30), 0);
   }

   public static long epiphany(long gYear)
   {
      return firstKDay(0, toFixed(gYear, 1, 2));
   }

   public static final String[] dayOfWeekNames =
      {
         "Dimanche",
         "Lundi",
         "Mardi",
         "Mercredi",
         "Jeudi",
         "Vendredi",
         "Samedi"
      };

   public static final String[] monthNames =
      {
         "Janvier",
         "Février",
         "Mars",
         "Avril",
         "Mai",
         "Juin",
         "Juillet",
         "Août",
         "Septembre",
         "Octobre",
         "Novembre",
         "Décembre"
      };

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#}",
         new Object[]
            {
               nameFromDayOfWeek(toFixed(), dayOfWeekNames),
               new Integer(this.day),
               nameFromMonth(this.month, monthNames),
               new Long(this.year)
            });
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Gregorian))
      {
         return false;
      }
      return internalEquals(obj);
   }
}
