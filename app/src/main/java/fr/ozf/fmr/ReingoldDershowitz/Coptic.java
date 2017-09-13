package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;


public class Coptic
   extends StandardDate
{
   public Coptic()
   {
   }

   public Coptic(long date)
   {
      super(date);
   }

   public Coptic(Date date)
   {
      super(date);
   }

   public Coptic(long year, int month, int day)
   {
      super(year, month, day);
   }


   public static final long EPOCH = Julian.toFixed(Julian.CE(284L), 8, 29);


   public static long toFixed(long year, int month, int day)
   {
      return EPOCH - 1L +
         365L * (year - 1L) +
         quotient(year, 4.0D) +
         30 * (month - 1) +
         day;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }


   public void fromFixed(long date)
   {
      this.year = quotient(4L * (date - EPOCH) + 1463L, 1461.0D);
      this.month = (1 + (int) quotient(date - toFixed(this.year, 1, 1), 30.0D));
      this.day = ((int) (date + 1L - toFixed(this.year, this.month, 1)));
   }


   public static boolean isLeapYear(long cYear)
   {
      return mod(cYear, 4L) == 3L;
   }


   public static FixedVector inGregorian(int cMonth, int cDay, long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      long dec31 = Gregorian.toFixed(gYear, 12, 31);
      long y = new Coptic(jan1).year;
      long date1 = toFixed(y, cMonth, cDay);
      long date2 = toFixed(y + 1L, cMonth, cDay);
      FixedVector result = new FixedVector(1, 1);
      if ((jan1 <= date1) && (date1 <= dec31))
         result.addFixed(date1);
      if ((jan1 <= date2) && (date2 <= dec31))
         result.addFixed(date2);
      return result;
   }


   public static FixedVector christmas(long gYear)
   {
      return inGregorian(4, 29, gYear);
   }


   public static final String[] dayOfWeekNames = {
      "Tkyriaka",
      "Pesnau",
      "Pshoment",
      "Peftoou",
      "Ptiou",
      "Psoou",
      "Psabbaton"};

   public static final String[] monthNames = {
      "Tut",
      "Babah",
      "Hatur",
      "Kiyahk",
      "Tubah",
      "Amshir",
      "Baramhat",
      "Baramundah",
      "Bashans",
      "Ba'unah",
      "Abib",
      "Misra",
      "al-Nasi"};

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#} A.M.",
         new Object[]{
            nameFromDayOfWeek(toFixed(), dayOfWeekNames),
            new Integer(this.day),
            nameFromMonth(this.month, monthNames),
            new Long(this.year)});
   }


   public boolean equals(Object obj)
   {
      if (!(obj instanceof Coptic))
      {
         return false;
      }
      return internalEquals(obj);
   }
}