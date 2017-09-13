package fr.ozf.fmr.ReingoldDershowitz;


public abstract class Date extends ProtoDate
{
   public Date()
   {
   }


   public Date(long date)
   {
      super(date);
   }

   public Date(Date date)
   {
      super(date);
   }


   public abstract long toFixed();


   public void convertTo(ProtoDate toDate)
   {
      convert(this, toDate);
   }
}