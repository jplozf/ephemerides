package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;


public class Islamic
   extends StandardDate
{
   public Islamic()
   {
   }

   public Islamic(long date)
   {
      super(date);
   }

   public Islamic(Date date)
   {
      super(date);
   }

   public Islamic(long year, int month, int day)
   {
      super(year, month, day);
   }


   public static final long EPOCH = Julian.toFixed(Julian.CE(622L), 7, 16);


   public static final Location MECCA = new Location("Mecca, Saudi Arabia", angle(21.0D, 25.0D, 24.0D), angle(39.0D, 49.0D, 24.0D), mt(1000.0D), 2.0D);


   public static long toFixed(long year, int month, int day)
   {
      return day +
         29 * (month - 1) +
         quotient(6 * month - 1, 11.0D) +
         (year - 1L) * 354L +
         quotient(3L + 11L * year, 30.0D) +
         EPOCH - 1L;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }


   public void fromFixed(long date)
   {
      this.year = quotient(30L * (date - EPOCH) + 10646L, 10631.0D);
      long priorDays = date - toFixed(this.year, 1, 1);
      this.month = ((int) quotient(11L * priorDays + 330L, 325.0D));
      this.day = ((int) (1L + date - toFixed(this.year, this.month, 1)));
   }


   public static boolean isLeapYear(long iYear)
   {
      return mod(11L * iYear + 14L, 30L) < 11L;
   }


   public static double asr(long date, Location locale)
      throws BogusTimeException
   {
      double noon = universalFromStandard(midday(date, locale), locale);
      double phi = locale.latitude;
      double delta = arcSinDegrees(sinDegrees(obliquity(noon)) * sinDegrees(solarLongitude(noon)));
      double altitude = arcSinDegrees(sinDegrees(phi) * sinDegrees(delta) + cosDegrees(phi) * cosDegrees(delta));
      double h = arcTanDegrees(tanDegrees(altitude) / (1.0D + 2.0D * tanDegrees(altitude)), 1);
      return dusk(date, locale, -h);
   }


   public static FixedVector inGregorian(int iMonth, int iDay, long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      long dec31 = Gregorian.toFixed(gYear, 12, 31);
      long y = new Islamic(jan1).year;
      long date1 = toFixed(y, iMonth, iDay);
      long date2 = toFixed(y + 1L, iMonth, iDay);
      long date3 = toFixed(y + 2L, iMonth, iDay);
      FixedVector result = new FixedVector(1, 1);
      if ((jan1 <= date1) && (date1 <= dec31))
         result.addFixed(date1);
      if ((jan1 <= date2) && (date2 <= dec31))
         result.addFixed(date2);
      if ((jan1 <= date3) && (date3 <= dec31))
         result.addFixed(date3);
      return result;
   }


   public static FixedVector mawlidAnNabi(long gYear)
   {
      return inGregorian(3, 12, gYear);
   }


   public static final String[] dayOfWeekNames = {
      "yaum al-ahad",
      "yaum al-ithnayna",
      "yaum ath-thalatha'",
      "yaum al-arba`a'",
      "yaum al-hamis",
      "yaum al-jum`a",
      "yaum as-sabt"};

   public static final String[] monthNames = {
      "Muharram",
      "Safar",
      "Rabi I",
      "Rabi II",
      "Jumada I",
      "Jumada II",
      "Rajab",
      "Sha`ban",
      "Ramadan",
      "Shawwal",
      "Dhu al-Qa`da",
      "Dhu al-Hijja"};

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#} A.H.",
         new Object[]{
            nameFromDayOfWeek(toFixed(), dayOfWeekNames),
            new Integer(this.day),
            nameFromMonth(this.month, monthNames),
            new Long(this.year)});
   }


   public boolean equals(Object obj)
   {
      if (!(obj instanceof Islamic))
      {
         return false;
      }
      return internalEquals(obj);
   }
}