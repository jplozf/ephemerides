package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class Armenian extends StandardDate
{
   public static final long EPOCH = 201443L;

   public Armenian()
   {
   }

   public Armenian(long date)
   {
      super(date);
   }

   public Armenian(Date date)
   {
      super(date);
   }

   public Armenian(long year, int month, int day)
   {
      super(year, month, day);
   }

   public static long toFixed(long year, int month, int day)
   {
      return 201443L + Egyptian.toFixed(year, month, day) - Egyptian.EPOCH;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }

   public void fromFixed(long date)
   {
      Egyptian d = new Egyptian(date + Egyptian.EPOCH - 201443L);
      this.year = d.year;
      this.month = d.month;
      this.day = d.day;
   }

   public static final String[] dayOfWeekNames =
      {
         "Miashabathi",
         "Erkoushabathi",
         "Erekhshabathi",
         "Chorekhshabathi",
         "Hingshabathi",
         "Urbath",
         "Shabath"
      };

   public static final String[] monthNames =
      {
         "Nawasardi",
         "Hori",
         "Sahmi",
         "Tre",
         "Kaloch",
         "Arach",
         "Mehekani",
         "Areg",
         "Ahekani",
         "Mareri",
         "Margach",
         "Hrotich",
         "Aweleach"
      };

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#}",
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
      if (!(obj instanceof Armenian))
      {
         return false;
      }
      return internalEquals(obj);
   }
}
