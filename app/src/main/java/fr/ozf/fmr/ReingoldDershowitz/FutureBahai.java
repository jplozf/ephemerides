package fr.ozf.fmr.ReingoldDershowitz;

public class FutureBahai
   extends Bahai
{
   public FutureBahai()
   {
   }

   public FutureBahai(long date)
   {
      super(date);
   }

   public FutureBahai(Date date)
   {
      super(date);
   }

   public FutureBahai(long major, int cycle, int year, int month, int day)
   {
      this.major = major;
      this.cycle = cycle;
      this.year = year;
      this.month = month;
      this.day = day;
   }

   public static final Location HAIFA = new Location("Haifa, Israel", deg(32.82D), deg(35.0D), mt(0.0D), 2.0D);

   public static long toFixed(long bMajor, int bCycle, int bYear, int bMonth, int bDay)
   {
      long years = 361L * (bMajor - 1L)
         + 19 * (bCycle - 1)
         + bYear;
      if (bMonth == 19)
      {
         return newYearOnOrBefore((long) (EPOCH + Math.floor(365.242189D * (years + 0.5D)))) - 19L + bDay - 1L;
      }
      if (bMonth == 0)
      {
         return newYearOnOrBefore((long) (EPOCH + Math.floor(365.242189D * (years - 0.5D)))) + 342L + bDay - 1L;
      }
      return newYearOnOrBefore((long) (EPOCH + Math.floor(365.242189D * (years - 0.5D)))) + (bMonth - 1) * 19 + bDay - 1L;
   }

   public long toFixed()
   {
      return toFixed(this.major, this.cycle, this.year, this.month, this.day);
   }

   public void fromFixed(long date)
   {
      long newYear = newYearOnOrBefore(date);
      long years = Math.round((newYear - EPOCH) / 365.242189D);
      this.major = (quotient(years, 361.0D) + 1L);
      this.cycle = ((int) quotient(mod(years, 361L), 19.0D) + 1);
      this.year = ((int) mod(years, 19L) + 1);
      long days = date - newYear;
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
         this.month = ((int) quotient(days, 19.0D) + 1);
      }
      this.day = ((int) (date + 1L - toFixed(this.major, this.cycle, this.year, this.month, 1)));
   }

   public static double sunsetInHaifa(long date)
   {
      try
      {
         return universalFromStandard(sunset(date, HAIFA), HAIFA);
      } catch (BogusTimeException ex)
      {
      }
      return 0.0D;
   }

   public static long newYearOnOrBefore(long date)
   {
      double approx = estimatePriorSolarLongitude(sunsetInHaifa(date), SPRING);
      long i;

      for (i = (long) (Math.floor(approx) - 1L); solarLongitude(sunsetInHaifa(i)) > SPRING + deg(2.0D); i += 1L)
      {
      }
      return i;
   }

   public static long feastOfRidvan(long gYear)
   {
      long years = gYear - Gregorian.yearFromFixed(EPOCH);
      long major = 1L + quotient(years, 361.0D);
      int cycle = 1 + (int) quotient(mod(years, 361L), 19.0D);
      int year = 1 + (int) mod(years, 19L);
      return toFixed(major, cycle, year, 2, 13);
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof FutureBahai))
      {
         return false;
      }
      FutureBahai o = (FutureBahai) obj;

      return (o.major == this.major) && (o.cycle == this.cycle) && (o.year == this.year) && (o.month == this.month) && (o.day == this.day);
   }
}
