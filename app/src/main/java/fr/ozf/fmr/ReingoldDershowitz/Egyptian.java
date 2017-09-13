package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;


public class Egyptian extends StandardDate
{
   public Egyptian()
   {
   }

   public Egyptian(long date)
   {
      super(date);
   }

   public Egyptian(Date date)
   {
      super(date);
   }

   public Egyptian(long year, int month, int day)
   {
      super(year, month, day);
   }


   public static final long EPOCH = fixedFromJD(1448638.0D);


   public static long toFixed(long year, int month, int day)
   {
      return EPOCH + 365L * (year - 1L) + 30 * (month - 1) + day - 1L;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }


   public void fromFixed(long date)
   {
      long days = date - EPOCH;
      this.year = (1L + quotient(days, 365.0D));
      this.month = ((int) (1L + quotient(mod(days, 365L), 30.0D)));
      this.day = ((int) (days - 365L * (this.year - 1L) - 30 * (this.month - 1) + 1L));
   }


   public static final String[] monthNames = {
      "Thoth",
      "Phaophi",
      "Athyr",
      "Choiak",
      "Tybi",
      "Mechir",
      "Phamenoth",
      "Pharmuthi",
      "Pachon",
      "Payni",
      "Epiphi",
      "Mesori",
      "Epagomenai"};

   public String format()
   {
      return MessageFormat.format("{0} {1} {2,number,#}",
         new Object[]{
            new Integer(this.day),
            nameFromMonth(this.month, monthNames),
            new Long(this.year)});
   }


   public boolean equals(Object obj)
   {
      if (!(obj instanceof Egyptian))
      {
         return false;
      }
      return internalEquals(obj);
   }
}