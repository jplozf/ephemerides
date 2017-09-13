package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class French
   extends StandardDate
{
   public French()
   {
   }

   public French(long date)
   {
      super(date);
   }

   public French(Date date)
   {
      super(date);
   }

   public French(long year, int month, int day)
   {
      super(year, month, day);
   }

   public static final long EPOCH = Gregorian.toFixed(1792L, 9, 22);

   public static final Location PARIS = new Location("Paris, France", angle(48.0D, 50.0D, 11.0D), angle(2.0D, 20.0D, 15.0D), mt(27.0D), 1.0D);

   public static long toFixed(long year, int month, int day)
   {
      long newYear = newYearOnOrBefore((long) Math.floor(EPOCH + 180L + 365.242189D * (year - 1L)));
      return newYear - 1L + 30 * (month - 1) + day;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }

   public void fromFixed(long date)
   {
      long newYear = newYearOnOrBefore(date);
      this.year = (Math.round((newYear - EPOCH) / 365.242189D) + 1L);
      this.month = (1 + (int) quotient(date - newYear, 30.0D));
      this.day = (1 + (int) mod(date - newYear, 30L));
   }

   public static double midnightInParis(long date)
   {
      return universalFromStandard(midnight(date + 1L, PARIS), PARIS);
   }

   public static long newYearOnOrBefore(long date)
   {
      double approx = estimatePriorSolarLongitude(midnightInParis(date), AUTUMN);
      long i;

      for (i = (long) (Math.floor(approx) - 1.0D); AUTUMN > solarLongitude(midnightInParis(i)); i += 1L)
      {
      }
      return i;
   }

   public static final String[] monthNames =
   {
      "Vendemiaire",
      "Brumaire",
      "Frimaire",
      "Nivose",
      "Pluviose",
      "Ventose",
      "Germinal",
      "Floreal",
      "Prairial",
      "Messidor",
      "Thermidor",
      "Fructidor",
      "Sansculottides"
   };

   public static final String[] dayOfWeekNames =
   {
      "Primidi",
      "Duodi",
      "Tridi",
      "Quartidi",
      "Quintidi",
      "Sextidi",
      "Septidi",
      "Octidi",
      "Nonidi",
      "Decadi"
   };

   public static final String[] specialDayNames =
   {
      "Jour de la Vertu",
      "Jour du Genie",
      "Jour du Labour",
      "Jour de la Raison",
      "Jour de la Recompense",
      "Jour de la Revolution"
   };

   public static final String[] decadeNames =
   {
      "I",
      "II",
      "III"
   };

   public String format()
   {
      if (this.month == 13)
      {
         return MessageFormat.format("{0} de l''Année {1,number,#} de la République",
            new Object[]
            {
               nameFromNumber(this.day, specialDayNames),
               new Long(this.year)
            });
      }

      return MessageFormat.format("Decade {0}, {1} de {2} de l''Année {3,number,#} de la République",
         new Object[]
         {
            decadeNames[((int) quotient(this.day - 1, 10.0D))],
            nameFromNumber(this.day, dayOfWeekNames),
            nameFromMonth(this.month, monthNames),
            new Long(this.year)
         });
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof French))
      {
         return false;
      }
      return internalEquals(obj);
   }
}
