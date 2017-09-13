package fr.ozf.fmr.ReingoldDershowitz;

public abstract class Ecclesiastical
   extends ProtoDate
{
   public static long orthodoxEaster(long gYear)
   {
      long shiftedEpact = mod(14L + 11L * mod(gYear, 19L), 30L);
      long jYear = gYear > 0L ? gYear : gYear - 1L;
      long paschalMoon = Julian.toFixed(jYear, 4, 19) - shiftedEpact;
      return kDayAfter(paschalMoon, 0);
   }

   public static long altOrthodoxEaster(long gYear)
   {
      long paschalMoon = 354L * gYear
         + 30L * quotient(7L * gYear - 8L, 19.0D)
         + quotient(gYear, 4.0D)
         - quotient(gYear, 19.0D)
         - 272L;
      return kDayAfter(paschalMoon, 0);
   }

   public static long easter(long gYear)
   {
      long century = 1L + quotient(gYear, 100.0D);
      long shiftedEpact = mod(14L
            + 11L * mod(gYear, 19L)
            - quotient(3L * century, 4.0D)
            + quotient(5L + 8L * century, 25.0D),
         30L);
      long adjustedEpact = (shiftedEpact == 0L) || ((shiftedEpact == 1L) && (10L < mod(gYear, 19L)))
         ? shiftedEpact + 1L
         : shiftedEpact;
      long paschalMoon = Gregorian.toFixed(gYear, 4, 19) - adjustedEpact;
      return kDayAfter(paschalMoon, 0);
   }

   public static long pentecost(long gYear)
   {
      return easter(gYear) + 49L;
   }

   public static long astronomicalEaster(long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      double equinox = solarLongitudeAfter(jan1, SPRING);
      long paschalMoon = (long) Math.floor(apparentFromLocal(localFromUniversal(lunarPhaseAfter(equinox, FULL), JERUSALEM)));
      return kDayAfter(paschalMoon, 0);
   }
}
