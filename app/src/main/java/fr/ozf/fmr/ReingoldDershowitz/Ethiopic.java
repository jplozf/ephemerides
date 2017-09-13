package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;


public class Ethiopic
   extends StandardDate
{
   public Ethiopic()
   {
   }

   public Ethiopic(long date)
   {
      super(date);
   }

   public Ethiopic(Date date)
   {
      super(date);
   }

   public Ethiopic(long year, int month, int day)
   {
      super(year, month, day);
   }


   public static final long EPOCH = Julian.toFixed(Julian.CE(8L), 8, 29);


   public static long toFixed(long year, int month, int day)
   {
      return Coptic.toFixed(year, month, day) - Coptic.EPOCH + EPOCH;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }


   public void fromFixed(long date)
   {
      Coptic coptic = new Coptic(date + Coptic.EPOCH - EPOCH);
      this.month = coptic.month;
      this.day = coptic.day;
      this.year = coptic.year;
   }


   public static final String[] dayOfWeekNames = {
      "Ihud",
      "Sanyo",
      "Maksanyo",
      "Rob",
      "Hamus",
      "Arb",
      "Kidamme"};

   public static final String[] monthNames = {
      "Maskaram",
      "Teqemt",
      "Kehdar",
      "Takhsas",
      "Ter",
      "Yakatit",
      "Magabit",
      "Miyazya",
      "Genbot",
      "Sane",
      "Hamle",
      "Nahase",
      "Paguemen"};

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#} E.E.",
         new Object[]{
            nameFromDayOfWeek(toFixed(), dayOfWeekNames),
            new Integer(this.day),
            nameFromMonth(this.month, monthNames),
            new Long(this.year)});
   }


   public boolean equals(Object obj)
   {
      if (!(obj instanceof Ethiopic))
      {
         return false;
      }
      return internalEquals(obj);
   }
}