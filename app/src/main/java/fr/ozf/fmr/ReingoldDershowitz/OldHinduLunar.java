package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class OldHinduLunar
   extends Date
{
   public long year;
   public int month;
   public boolean leapMonth;
   public int day;
   public static final double ARYA_LUNAR_MONTH = 29.530581807581694D;
   public static final double ARYA_LUNAR_DAY = 0.9843527269193898D;

   public OldHinduLunar()
   {
   }

   public OldHinduLunar(long date)
   {
      super(date);
   }

   public OldHinduLunar(Date date)
   {
      super(date);
   }

   public OldHinduLunar(long year, int month, boolean leapMonth, int day)
   {
      this.year = year;
      this.month = month;
      this.leapMonth = leapMonth;
      this.day = day;
   }

   public static long toFixed(long year, int month, boolean leapMonth, int day)
   {
      double mina = (12L * year - 1L) * 30.43822337962963D;
      double lunarNewYear = 29.530581807581694D * (quotient(mina, 29.530581807581694D) + 1L);
      return (long) Math.floor(
         OldHinduSolar.EPOCH
         + lunarNewYear
         + 29.530581807581694D * ((!leapMonth)
         && (Math.ceil((lunarNewYear - mina) / 0.907641572047936D) <= month)
         ? month : month - 1)
         + (day - 1) * 0.9843527269193898D
         + 0.75D);
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.leapMonth, this.day);
   }

   public void fromFixed(long date)
   {
      double sun = OldHinduSolar.dayCount(date) + 0.25D;
      double newMoon = sun - mod(sun, 29.530581807581694D);
      this.leapMonth = ((0.907641572047936D >= mod(newMoon, 30.43822337962963D))
         && (mod(newMoon, 30.43822337962963D) > 0.0D));
      this.month = (1 + (int) mod(Math.ceil(newMoon / 30.43822337962963D), 12.0D));
      this.day = (1 + (int) mod(quotient(sun, 0.9843527269193898D), 30L));
      this.year = (long) (Math.ceil((newMoon + 30.43822337962963D) / 365.25868055555554D) - 1L);
   }

   @Override
   public void fromArray(int[] a)
   {
      this.year = a[0];
      this.month = a[1];
      this.leapMonth = (a[2] != 0);
      this.day = a[3];
   }

   public static boolean isLeapYear(long lYear)
   {
      return mod(lYear * 365.25868055555554D - 30.43822337962963D, 29.530581807581694D) >= 18.638882943006465D;
   }

   @Override
   protected String toStringFields()
   {
      return "year=" + this.year + ",month=" + this.month + ",leapMonth=" + this.leapMonth + ",day=" + this.day;
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
      "Chaitra",
      "Vaisakha",
      "Jyaishtha",
      "Ashadha",
      "Sravana",
      "Bhadrapada",
      "Asvina",
      "Kartika",
      "Margasirsha",
      "Pausha",
      "Magha",
      "Phalguna"
   };

   @Override
   public String format()
   {
      return MessageFormat.format("{0}, {1} {2}{3} {4,number,#} K.Y.",
         new Object[]
         {
            nameFromDayOfWeek(toFixed(), dayOfWeekNames),
            new Integer(this.day),
            nameFromMonth(this.month, monthNames),
            this.leapMonth ? " II" : "",
            new Long(this.year)
         });
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof OldHinduLunar))
      {
         return false;
      }
      OldHinduLunar o = (OldHinduLunar) obj;

      return (o.year == this.year) && (o.month == this.month) && (o.leapMonth == this.leapMonth) && (o.day == this.day);
   }
}
