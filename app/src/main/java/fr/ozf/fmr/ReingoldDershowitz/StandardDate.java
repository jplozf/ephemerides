package fr.ozf.fmr.ReingoldDershowitz;

public abstract class StandardDate
   extends Date
{
   public long year;

   public int month;

   public int day;

   public StandardDate()
   {
   }

   public StandardDate(long date)
   {
      super(date);
   }

   public StandardDate(Date date)
   {
      super(date);
   }

   public StandardDate(long year, int month, int day)
   {
      this.year = year;
      this.month = month;
      this.day = day;
   }

   public StandardDate(int[] a)
   {
      fromArray(a);
   }

   public void fromArray(int[] a)
   {
      this.year = a[0];
      this.month = a[1];
      this.day = a[2];
   }

   protected String toStringFields()
   {
      return "year=" + this.year + ",month=" + this.month + ",day=" + this.day;
   }

   protected boolean internalEquals(Object obj)
   {
      StandardDate o = (StandardDate) obj;

      if (this == obj)
      {
         return true;
      }

      return (o.year == this.year) && (o.month == this.month) && (o.day == this.day);
   }
}