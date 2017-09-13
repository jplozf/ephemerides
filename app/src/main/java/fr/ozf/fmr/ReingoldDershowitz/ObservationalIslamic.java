package fr.ozf.fmr.ReingoldDershowitz;

public class ObservationalIslamic   extends Islamic
{
   public ObservationalIslamic()
   {
   }

   public ObservationalIslamic(long date)
   {
      super(date);
   }

   public ObservationalIslamic(Date date)
   {
      super(date);
   }

   public ObservationalIslamic(long year, int month, int day)
   {
      super(year, month, day);
   }

   public static final Location CAIRO = new Location("Cairo, Egypt", deg(30.1D), deg(31.3D), mt(200.0D), 2.0D);
   public static final Location ISLAMIC_LOCALE = CAIRO;

   public static long toFixed(long iYear, int iMonth, int iDay)
   {
      long result;

      try
      {
         long midmonth = (long) (EPOCH + Math.floor(((iYear - 1L) * 12L + iMonth - 0.5D) * 29.530588853D));
         result = phasisOnOrBefore(midmonth, ISLAMIC_LOCALE) + iDay - 1L;
      }
      catch (BogusTimeException ex)
      {
         result = 0L;
      }

      return result;
   }

   @Override
   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }

   @Override
   public void fromFixed(long date)
   {
      try
      {
         long crescent = phasisOnOrBefore(date, ISLAMIC_LOCALE);
         long elapsedMonths = Math.round((crescent - EPOCH) / 29.530588853D);
         this.year = (quotient(elapsedMonths, 12.0D) + 1L);
         this.month = ((int) (mod(elapsedMonths, 12L) + 1L));
         this.day = ((int) (date - crescent + 1L));
      }
      catch (BogusTimeException localBogusTimeException)
      {
      }
   }
}
