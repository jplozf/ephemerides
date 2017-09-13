package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class OldHinduSolar
   extends StandardDate
{
   public OldHinduSolar()
   {
   }

   public OldHinduSolar(long date)
   {
      super(date);
   }

   public OldHinduSolar(Date date)
   {
      super(date);
   }

   public OldHinduSolar(long year, int month, int day)
   {
      super(year, month, day);
   }

   public static final long EPOCH = Julian.toFixed(Julian.BCE(3102L), 2, 18);

   public static final double ARYA_SOLAR_YEAR = 365.25868055555554D;

   public static final double ARYA_SOLAR_MONTH = 30.43822337962963D;

   public static final double ARYA_JOVIAN_PERIOD = 4332.27217316816D;

   public static long toFixed(long year, int month, int day)
   {
      return (long) Math.ceil(
         EPOCH
         + year * 365.25868055555554D
         + (month - 1) * 30.43822337962963D
         + day - 1.25D);
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }

   public void fromFixed(long date)
   {
      double sun = dayCount(date) + 0.25D;
      this.year = quotient(sun, 365.25868055555554D);
      this.month = (1 + (int) mod(quotient(sun, 30.43822337962963D), 12L));
      this.day = (1 + (int) Math.floor(mod(sun, 30.43822337962963D)));
   }

   public static long dayCount(long date)
   {
      return date - EPOCH;
   }

   public static double dayCount(double date)
   {
      return date - EPOCH;
   }

   public static int jovianYear(long date)
   {
      return 1 + (int) mod(quotient(dayCount(date), 361.0226810973467D), 60L);
   }

   public static final String[] dayOfWeekNames =
   {
      "Ravivara",
      "Chandravara",
      "Mangalavara",
      "Buddhavara",
      "Brihaspatvara",
      "Sukravara",
      "Sanivara"
   };

   public static final String[] monthNames =
   {
      "Mesha",
      "Vrishabha",
      "Mithuna",
      "Karka",
      "Simha",
      "Kanya",
      "Tula",
      "Vrischika",
      "Dhanu",
      "Makara",
      "Kumbha",
      "Mina"
   };

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#} K.Y.",
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
      if (!(obj instanceof OldHinduSolar))
      {
         return false;
      }
      return internalEquals(obj);
   }
}
