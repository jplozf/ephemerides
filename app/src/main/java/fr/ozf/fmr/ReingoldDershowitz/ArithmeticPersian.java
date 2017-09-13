package fr.ozf.fmr.ReingoldDershowitz;

public class ArithmeticPersian extends Persian
{
   public ArithmeticPersian()
   {
   }


   public ArithmeticPersian(long date)
   {
      super(date);
   }

   public ArithmeticPersian(Date date)
   {
      super(date);
   }

   public ArithmeticPersian(long year, int month, int day)
   {
      super(year, month, day);
   }


   public static long toFixed(long pYear, int month, int day)
   {
      long y = 0L < pYear ? pYear - 474L : pYear - 473L;
      long year = mod(y, 2820L) + 474L;
      return EPOCH - 1L +
         1029983L * quotient(y, 2820.0D) +
         365L * (year - 1L) +
         quotient(682L * year - 110L, 2816.0D) + (
         month <= 7 ? 31 * (month - 1) : 30 * (month - 1) + 6) +
         day;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }


   public void fromFixed(long date)
   {
      this.year = yearFromFixed(date);
      long dayOfYear = 1L + date - toFixed(this.year, 1, 1);
      this.month = ((int) (dayOfYear < 186L ? Math.ceil(dayOfYear / 31.0D) : Math.ceil((dayOfYear - 6L) / 30.0D)));
      this.day = ((int) (date - (toFixed(this.year, this.month, 1) - 1L)));
   }


   public static boolean isLeapYear(long pYear)
   {
      long y = 0L < pYear ? pYear - 474L : pYear - 473L;
      long year = mod(y, 2820L) + 474L;
      return mod((year + 38L) * 682L, 2816L) < 682L;
   }


   public static long yearFromFixed(long date)
   {
      long l0 = date - toFixed(475L, 1, 1);
      long n2820 = quotient(l0, 1029983.0D);
      long d1 = mod(l0, 1029983L);
      long y2820 = d1 == 1029982L ? 2820L : quotient(2816.0D * d1 + 1031337.0D, 1028522.0D);
      long year = 474L + 2820L * n2820 + y2820;
      return 0L < year ? year : year - 1L;
   }
}