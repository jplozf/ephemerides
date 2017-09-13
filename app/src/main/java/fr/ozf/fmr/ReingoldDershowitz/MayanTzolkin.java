package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class MayanTzolkin
   extends ProtoDate
{
   public int number;
   public int name;
   public static final long EPOCH = MayanLongCount.EPOCH - ordinal(new MayanTzolkin(4, 20));

   public MayanTzolkin()
   {
   }

   public MayanTzolkin(long date)
   {
      super(date);
   }

   public MayanTzolkin(Date date)
   {
      super(date);
   }

   public MayanTzolkin(int number, int name)
   {
      this.number = number;
      this.name = name;
   }

   public void fromFixed(long date)
   {
      long count = date - EPOCH + 1L;
      this.number = ((int) adjustedMod(count, 13L));
      this.name = ((int) adjustedMod(count, 20L));
   }

   public void fromArray(int[] a)
   {
      this.number = a[0];
      this.name = a[1];
   }

   public static int ordinal(MayanTzolkin tDate)
   {
      return mod(tDate.number - 1 + 39 * (tDate.number - tDate.name), 260);
   }

   public static long onOrBefore(MayanTzolkin tzolkin, long date)
   {
      return date - mod(date - EPOCH - ordinal(tzolkin), 260L);
   }

   public static long calendarRoundOnOrBefore(MayanHaab haab, MayanTzolkin tzolkin, long date)
      throws BogusDateException
   {
      long haabCount = MayanHaab.ordinal(haab) + MayanHaab.EPOCH;
      long tzolkinCount = ordinal(tzolkin) + EPOCH;
      long diff = tzolkinCount - haabCount;
      long result;
      if (mod(diff, 5L) == 0L)
      {
         result = date - mod(date - haabCount - 365L * diff, 18980L);
      }
      else
      {
         throw new BogusDateException();
      }
      return result;
   }

   @Override
   protected String toStringFields()
   {
      return "number=" + this.number + ",name=" + this.name;
   }

   public static final String[] names =
   {
      "Imix",
      "Ik",
      "Akbal",
      "Kan",
      "Chicchan",
      "Cimi",
      "Manik",
      "Lamat",
      "Muluc",
      "Oc",
      "Chuen",
      "Eb",
      "Ben",
      "Ix",
      "Men",
      "Cib",
      "Caban",
      "Etznab",
      "Cauac",
      "Ahau"
   };

   @Override
   public String format()
   {
      return MessageFormat.format("{0} {1}",
         new Object[]
         {
            new Integer(this.number),
            nameFromNumber(this.name, names)
         });
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof MayanTzolkin))
      {
         return false;
      }
      MayanTzolkin o = (MayanTzolkin) obj;

      return (o.number == this.number) && (o.name == this.name);
   }
}
