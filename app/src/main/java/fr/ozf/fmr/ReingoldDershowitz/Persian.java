package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class Persian
   extends StandardDate
{
   public Persian()
   {
   }

   public Persian(long date)
   {
      super(date);
   }

   public Persian(Date date)
   {
      super(date);
   }

   public Persian(long year, int month, int day)
   {
      super(year, month, day);
   }

   public static final long EPOCH = Julian.toFixed(Julian.CE(622L), 3, 19);

   public static final Location TEHRAN = new Location("Tehran, Iran", deg(35.68D), deg(51.42D), mt(1100.0D), 3.5D);

   public static long toFixed(long year, int month, int day)
   {
      long newYear = newYearOnOrBefore((long) (EPOCH + 180L + Math.floor(365.242189D * (0L < year ? year - 1L : year))));
      return newYear - 1L + (month <= 7 ? 31 * (month - 1) : 30 * (month - 1) + 6)
         + day;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }

   public void fromFixed(long date)
   {
      long newYear = newYearOnOrBefore(date);
      long y = 1L + Math.round((newYear - EPOCH) / 365.242189D);
      this.year = (0L < y ? y : y - 1L);
      long dayOfYear = 1L + date - toFixed(this.year, 1, 1);
      this.month = (dayOfYear < 186L ? (int) Math.ceil(dayOfYear / 31.0D) : (int) Math.ceil((dayOfYear - 6L) / 30.0D));
      this.day = ((int) (date - (toFixed(this.year, this.month, 1) - 1L)));
   }

   public static double middayInTehran(long date)
   {
      return universalFromStandard(midday(date, TEHRAN), TEHRAN);
   }

   public static long newYearOnOrBefore(long date)
   {
      double approx = estimatePriorSolarLongitude(middayInTehran(date), SPRING);
      long i;

      for (i = (long) (Math.floor(approx) - 1L); solarLongitude(middayInTehran(i)) > SPRING + deg(2.0D); i += 1L)
      {
      }
      return i;
   }

   public static long nawRuz(long gYear)
   {
      long pYear = 1L + gYear - Gregorian.yearFromFixed(EPOCH);
      return toFixed(pYear <= 0L ? pYear - 1L : pYear, 1, 1);
   }

   public static final String[] dayOfWeekNames =
   {
      "Yek-shanbeh",
      "Do-shanbeh",
      "Se-shanbeh",
      "Char-shanbeh",
      "Panj-shanbeh",
      "Jom`eh",
      "Shanbeh"
   };

   public static final String[] monthNames =
   {
      "Farvardin",
      "Ordibehesht",
      "Xordad",
      "Tir",
      "Mordad",
      "Shahrivar",
      "Mehr",
      "Aban",
      "Azar",
      "Dey",
      "Bahman",
      "Esfand"
   };

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#} A.P.",
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
      if (!(obj instanceof Persian))
      {
         return false;
      }
      return internalEquals(obj);
   }
}
