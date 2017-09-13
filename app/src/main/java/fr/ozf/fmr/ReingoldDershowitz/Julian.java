package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;


public class Julian
   extends StandardDate
{
   public static final long EPOCH = Gregorian.toFixed(0L, 12, 30);


   public Julian()
   {
   }


   public Julian(long date)
   {
      super(date);
   }

   public Julian(Date date)
   {
      super(date);
   }

   public Julian(long year, int month, int day)
   {
      super(year, month, day);
   }


   public static long toFixed(long year, int month, int day)
   {
      long y = year < 0L ? year + 1L : year;
      return EPOCH - 1L +
         365L * (y - 1L) +
         quotient(y - 1L, 4.0D) +
         quotient(367 * month - 362, 12.0D) + (

         isLeapYear(year) ? -1 : month <= 2 ? 0 : -2) +
         day;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }


   public void fromFixed(long date)
   {
      long approx = quotient(4L * (date - EPOCH) + 1464L, 1461.0D);
      this.year = (approx <= 0L ? approx - 1L : approx);
      long priorDays = date - toFixed(this.year, 1, 1);
      int correction = isLeapYear(this.year) ? 1 : date < toFixed(this.year, 3, 1) ? 0 : 2;
      this.month = ((int) quotient(12L * (priorDays + correction) + 373L, 367.0D));
      this.day = ((int) (date - toFixed(this.year, this.month, 1) + 1L));
   }


   public static long BCE(long n)
   {
      return -n;
   }


   public static long CE(long n)
   {
      return n;
   }


   public static boolean isLeapYear(long jYear)
   {
      return mod(jYear, 4L) == (jYear > 0L ? 0 : 3);
   }


   public static FixedVector inGregorian(int jMonth, int jDay, long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      long dec31 = Gregorian.toFixed(gYear, 12, 31);
      long y = new Julian(jan1).year;
      long yPrime = y == -1L ? 1L : y + 1L;
      long date1 = toFixed(y, jMonth, jDay);
      long date2 = toFixed(yPrime, jMonth, jDay);
      FixedVector result = new FixedVector(1, 1);
      if ((jan1 <= date1) && (date1 <= dec31))
         result.addFixed(date1);
      if ((jan1 <= date2) && (date2 <= dec31))
         result.addFixed(date2);
      return result;
   }


   public static FixedVector easternOrthodoxChristmas(long gYear)
   {
      return inGregorian(12, 25, gYear);
   }


   public String format()
   {
      return MessageFormat.format("{0} {1} {2,number,#} {3}",
         new Object[]{
            new Integer(this.day),
            nameFromMonth(this.month, Gregorian.monthNames),
            new Long(this.year < 0L ? -this.year : this.year),
            this.year < 0L ? "B.C.E." : "C.E."});
   }


   public boolean equals(Object obj)
   {
      if (!(obj instanceof Julian))
      {
         return false;
      }
      return internalEquals(obj);
   }
}