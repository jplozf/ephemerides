package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class Balinese extends ProtoDate
{
   public boolean luang;
   public int dwiwara;
   public int triwara;
   public int caturwara;
   public int pancawara;
   public int sadwara;
   public int saptawara;
   public int asatawara;
   public int sangawara;
   public int dasawara;
   public static final long EPOCH = fixedFromJD(146.0D);

   public Balinese()
   {
   }

   public Balinese(long date)
   {
      super(date);
   }

   public Balinese(Date date)
   {
      super(date);
   }

   public Balinese(boolean luang, int dwiwara, int triwara, int caturwara, int pancawara, int sadwara, int saptawara, int asatawara, int sangawara, int dasawara)
   {
      this.luang = luang;
      this.dwiwara = dwiwara;
      this.triwara = triwara;
      this.caturwara = caturwara;
      this.pancawara = pancawara;
      this.sadwara = sadwara;
      this.saptawara = saptawara;
      this.asatawara = asatawara;
      this.sangawara = sangawara;
      this.dasawara = dasawara;
   }

   public void fromFixed(long date)
   {
      this.luang = luangFromFixed(date);
      this.dwiwara = dwiwaraFromFixed(date);
      this.triwara = triwaraFromFixed(date);
      this.caturwara = caturwaraFromFixed(date);
      this.pancawara = pancawaraFromFixed(date);
      this.sadwara = sadwaraFromFixed(date);
      this.saptawara = saptawaraFromFixed(date);
      this.asatawara = asatawaraFromFixed(date);
      this.sangawara = sangawaraFromFixed(date);
      this.dasawara = dasawaraFromFixed(date);
   }

   public void fromArray(int[] a)
   {
      this.luang = (a[0] != 0);
      this.dwiwara = a[1];
      this.triwara = a[2];
      this.caturwara = a[3];
      this.pancawara = a[4];
      this.sadwara = a[5];
      this.saptawara = a[6];
      this.asatawara = a[7];
      this.sangawara = a[8];
      this.dasawara = a[9];
   }

   public static int dayFromFixed(long date)
   {
      return (int) mod(date - EPOCH, 210L);
   }

   public static boolean luangFromFixed(long date)
   {
      return mod(dasawaraFromFixed(date), 2) == 0;
   }

   public static int dwiwaraFromFixed(long date)
   {
      return mod(dasawaraFromFixed(date) + 1, 2) + 1;
   }

   public static int triwaraFromFixed(long date)
   {
      return mod(dayFromFixed(date), 3) + 1;
   }

   public static int caturwaraFromFixed(long date)
   {
      return adjustedMod(asatawaraFromFixed(date), 4);
   }

   public static int pancawaraFromFixed(long date)
   {
      return mod(dayFromFixed(date) + 1, 5) + 1;
   }

   public static int sadwaraFromFixed(long date)
   {
      return mod(dayFromFixed(date), 6) + 1;
   }

   public static int saptawaraFromFixed(long date)
   {
      return mod(dayFromFixed(date), 7) + 1;
   }

   public static int asatawaraFromFixed(long date)
   {
      int day = dayFromFixed(date);
      return mod(Math.max(6, 4 + mod(day - 70, 210)), 8) + 1;
   }

   public static int sangawaraFromFixed(long date)
   {
      return mod(Math.max(0, dayFromFixed(date) - 3), 9) + 1;
   }

   public static int dasawaraFromFixed(long date)
   {
      int i = pancawaraFromFixed(date);
      int j = saptawaraFromFixed(date);
      return mod(pancawaraI[(i - 1)] + saptawaraJ[(j - 1)] + 1, 10);
   }

   private static final int[] pancawaraI =
      {
         5, 9, 7, 4, 8
      };
   private static final int[] saptawaraJ =
      {
         5, 4, 3, 7, 8, 6, 9
      };

   public static int weekFromFixed(long date)
   {
      return (int) quotient(dayFromFixed(date), 7.0D) + 1;
   }

   public static long onOrBefore(Balinese bDate, long date)
   {
      int a5 = bDate.pancawara - 1;
      int a6 = bDate.sadwara - 1;
      int b7 = bDate.saptawara - 1;
      int b35 = mod(a5 + 14 + 15 * (b7 - a5), 35);
      int days = a6 + 36 * (b35 - a6);
      int capDelta = dayFromFixed(0L);
      return date - mod(date + capDelta - days, 210L);
   }

   public static int day(Balinese bDate)
   {
      return (int) (onOrBefore(bDate, EPOCH + 209L) - EPOCH + 1L);
   }

   public static int week(Balinese bDate)
   {
      return (int) (quotient(day(bDate) - 1, 7.0D) + 1L);
   }

   public static FixedVector positionsInInterval(int n, int c, int capDelta, long start, long end)
   {
      FixedVector result = new FixedVector();
      long pos = start;
      for (; ; )
      {
         pos += mod(n - start - capDelta - 1L, c);
         if (pos > end)
         {
            break;
         }
         result.addFixed(pos);
         pos += 1L;
      }
      return result;
   }

   public static FixedVector kajengKeliwonInGregorian(long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      long dec31 = Gregorian.toFixed(gYear, 12, 31);
      int capDelta = dayFromFixed(0L);
      return positionsInInterval(9, 15, capDelta, jan1, dec31);
   }

   public static FixedVector tumpekInGregorian(long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      long dec31 = Gregorian.toFixed(gYear, 12, 31);
      int capDelta = dayFromFixed(0L);
      return positionsInInterval(14, 35, capDelta, jan1, dec31);
   }

   protected String toStringFields()
   {
      return "luang=" + this.luang + ",dwiwara=" + this.dwiwara + ",triwara=" + this.triwara + ",caturwara=" + this.caturwara + ",pancawara=" + this.pancawara + ",sadwara=" + this.sadwara + ",saptawara=" + this.saptawara + ",asatawara=" + this.asatawara + ",sangawara=" + this.sangawara + ",dasawara=" + this.dasawara;
   }

   public static final String[] dwiwaraNames =
      {
         "Menga",
         "Pepet"
      };

   public static final String[] triwaraNames =
      {
         "Pasah",
         "Beteng",
         "Kajeng"
      };

   public static final String[] caturwaraNames =
      {
         "Sri",
         "Laba",
         "Jaya",
         "Menala"
      };

   public static final String[] pancawaraNames =
      {
         "Umanis",
         "Paing",
         "Pon",
         "Wage",
         "Keliwon"
      };

   public static final String[] sadwaraNames =
      {
         "Tungleh",
         "Aryang",
         "Urukung",
         "Paniron",
         "Was",
         "Maulu"
      };

   public static final String[] saptawaraNames =
      {
         "Redite",
         "Coma",
         "Anggara",
         "Buda",
         "Wraspati",
         "Sukra",
         "Saniscara"
      };

   public static final String[] asatawaraNames =
      {
         "Sri",
         "Indra",
         "Guru",
         "Yama",
         "Ludra",
         "Brahma",
         "Kala",
         "Uma"
      };

   public static final String[] sangawaraNames =
      {
         "Dangu",
         "Jangur",
         "Gigis",
         "Nohan",
         "Ogan",
         "Erangan",
         "Urungan",
         "Tulus",
         "Dadi"
      };

   public static final String[] dasawaraNames =
      {
         "Pandita",
         "Pati",
         "Suka",
         "Duka",
         "Sri",
         "Manuh",
         "Manusa",
         "Raja",
         "Dewa",
         "Raksasa"
      };

   public static final String[] weekNames =
      {
         "Sinta",
         "Landep",
         "Ukir",
         "Kulantir",
         "Taulu",
         "Gumbreg",
         "Wariga",
         "Warigadian",
         "Jukungwangi",
         "Sungsang",
         "Dunggulan",
         "Kuningan",
         "Langkir",
         "Medangsia",
         "Pujut",
         "Pahang",
         "Krulut",
         "Merakih",
         "Tambir",
         "Medangkungan",
         "Matal",
         "Uye",
         "Menail",
         "Parangbakat",
         "Bala",
         "Ugu",
         "Wayang",
         "Kelawu",
         "Dukut",
         "Watugunung"
      };

   public String format()
   {
      return MessageFormat.format("{0}{1} {2} {3} {4} {5} {6} {7} {8} {9}", new Object[]
         {
            this.luang ? "Luang " : "", nameFromNumber(this.dwiwara, dwiwaraNames), nameFromNumber(this.triwara, triwaraNames), nameFromNumber(this.caturwara, caturwaraNames), nameFromNumber(this.pancawara, pancawaraNames), nameFromNumber(this.sadwara, sadwaraNames), nameFromNumber(this.saptawara, saptawaraNames), nameFromNumber(this.asatawara, asatawaraNames), nameFromNumber(this.sangawara, sangawaraNames), nameFromNumber(this.dasawara, dasawaraNames)
         }) + MessageFormat.format(" ({0})",
         new Object[]
            {
               nameFromNumber(week(this), weekNames)
            });
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Balinese))
      {
         return false;
      }
      Balinese o = (Balinese) obj;

      return (o.luang == this.luang) && (o.dwiwara == this.dwiwara) && (o.triwara == this.triwara) && (o.caturwara == this.caturwara) && (o.pancawara == this.pancawara) && (o.sadwara == this.sadwara) && (o.saptawara == this.saptawara) && (o.asatawara == this.asatawara) && (o.sangawara == this.sangawara) && (o.dasawara == this.dasawara);
   }
}
