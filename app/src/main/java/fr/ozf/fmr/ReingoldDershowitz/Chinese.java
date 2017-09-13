package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class Chinese
   extends Date
{
   public long cycle;
   public int year;
   public int month;
   public boolean leapMonth;
   public int day;

   public Chinese()
   {
   }

   public Chinese(long date)
   {
      super(date);
   }

   public Chinese(Date date)
   {
      super(date);
   }

   public Chinese(long cycle, int year, int month, boolean leapMonth, int day)
   {
      this.cycle = cycle;
      this.year = year;
      this.month = month;
      this.leapMonth = leapMonth;
      this.day = day;
   }

   public static final long EPOCH = Gregorian.toFixed(-2636L, 2, 15);

   public static final int DAY_NAME_EPOCH = 15;

   public static final int MONTH_NAME_EPOCH = 3;

   public static long toFixed(long cycle, int year, int month, boolean leapMonth, int day)
   {
      long midYear = (long) Math.floor(EPOCH + ((cycle - 1L) * 60L + (year - 1) + 0.5D) * 365.242189D);
      long theNewYear = newYearOnOrBefore(midYear);
      long p = newMoonOnOrAfter(theNewYear + 29 * (month - 1));
      Chinese d = new Chinese(p);
      long priorNewMoon = (month == d.month) && (leapMonth == d.leapMonth)
         ? p
         : newMoonOnOrAfter(p + 1L);
      return priorNewMoon + day - 1L;
   }

   public long toFixed()
   {
      return toFixed(this.cycle, this.year, this.month, this.leapMonth, this.day);
   }

   public void fromFixed(long date)
   {
      long s1 = winterSolsticeOnOrBefore(date);
      long s2 = winterSolsticeOnOrBefore(s1 + 370L);
      long m12 = newMoonOnOrAfter(s1 + 1L);
      long nextM11 = newMoonBefore(s2 + 1L);
      long m = newMoonBefore(date + 1L);
      boolean leapYear = Math.round((nextM11 - m12) / 29.530588853D) == 12L;
      this.month = ((int) adjustedMod(
         Math.round((m - m12) / 29.530588853D) - ((leapYear) && (hasPriorLeapMonth(m12, m)) ? 1 : 0),
         12L));
      this.leapMonth = ((leapYear)
         && (hasNoMajorSolarTerm(m))
         && (!hasPriorLeapMonth(m12, newMoonBefore(m))));
      long elapsedYears = (long) Math.floor(1.5D - this.month / 12.0D + (date - EPOCH) / 365.242189D);
      this.cycle = (quotient(elapsedYears - 1L, 60.0D) + 1L);
      this.year = ((int) adjustedMod(elapsedYears, 60L));
      this.day = ((int) (date - m + 1L));
   }

   public void fromArray(int[] a)
   {
      this.cycle = a[0];
      this.year = a[1];
      this.month = a[2];
      this.leapMonth = (a[3] != 0);
      this.day = a[4];
   }

   public static double solarLongitudeOnOrAfter(long date, double theta)
   {
      Location beijing = chineseLocation(date);
      double tee = solarLongitudeAfter(universalFromStandard(date, beijing), theta);
      return standardFromUniversal(tee, beijing);
   }

   public static double midnightInChina(long date)
   {
      return universalFromStandard(date, chineseLocation(date));
   }

   public static long winterSolsticeOnOrBefore(long date)
   {
      double approx = estimatePriorSolarLongitude(midnightInChina(date + 1L), WINTER);
      long i;

      for (i = (long) (Math.floor(approx) - 1.0D); WINTER > solarLongitude(midnightInChina(i + 1L)); i += 1L)
      {
      }
      return i;
   }

   public static long newYearInSui(long date)
   {
      long s1 = winterSolsticeOnOrBefore(date);
      long s2 = winterSolsticeOnOrBefore(s1 + 370L);
      long m12 = newMoonOnOrAfter(s1 + 1L);
      long m13 = newMoonOnOrAfter(m12 + 1L);
      long nextM11 = newMoonBefore(s2 + 1L);
      if ((Math.round((nextM11 - m12) / 29.530588853D) == 12L) && ((hasNoMajorSolarTerm(m12)) || (hasNoMajorSolarTerm(m13))))
      {
         return newMoonOnOrAfter(m13 + 1L);
      }
      return m13;
   }

   public static long newYearOnOrBefore(long date)
   {
      long newYear = newYearInSui(date);
      return date >= newYear ? newYear : newYearInSui(date - 180L);
   }

   public static int nameDifference(ChineseName name1, ChineseName name2)
   {
      int stemDifference = name2.stem - name1.stem;
      int branchDifference = name2.branch - name1.branch;
      return 1 + mod(stemDifference - 1 + 25 * (branchDifference - stemDifference), 60);
   }

   public static long dayNameOnOrBefore(ChineseName name, long date)
   {
      return date - mod(date + nameDifference(name, sexagesimalName(15L)), 60L);
   }

   public static int currentMajorSolarTerm(long date)
   {
      double s = solarLongitude(universalFromStandard(date, chineseLocation(date)));
      return (int) adjustedMod(2L + quotient(s, deg(30.0D)), 12L);
   }

   public static double majorSolarTermOnOrAfter(long date)
   {
      double l = mod(30.0D * Math.ceil(solarLongitude(midnightInChina(date)) / 30.0D), 360.0D);
      return solarLongitudeOnOrAfter(date, l);
   }

   public static int currentMinorSolarTerm(long date)
   {
      double s = solarLongitude(midnightInChina(date));
      return (int) adjustedMod(3L + quotient(s - deg(15.0D), deg(30.0D)), 12L);
   }

   public static double minorSolarTermOnOrAfter(long date)
   {
      double l = mod(30.0D * Math.ceil((solarLongitude(midnightInChina(date)) - deg(15.0D)) / 30.0D) + deg(15.0D), 360.0D);
      return solarLongitudeOnOrAfter(date, l);
   }

   public static long newMoonBefore(long date)
   {
      double tee = Date.newMoonBefore(midnightInChina(date));
      return (long) Math.floor(standardFromUniversal(tee, chineseLocation(tee)));
   }

   public static long newMoonOnOrAfter(long date)
   {
      double tee = newMoonAfter(midnightInChina(date));
      return (long) Math.floor(standardFromUniversal(tee, chineseLocation(tee)));
   }

   public static boolean hasNoMajorSolarTerm(long date)
   {
      return currentMajorSolarTerm(date)
         == currentMajorSolarTerm(newMoonOnOrAfter(date + 1L));
   }

   public static boolean hasPriorLeapMonth(long mPrime, long m)
   {
      return (m >= mPrime) && ((hasNoMajorSolarTerm(m)) || (hasPriorLeapMonth(mPrime, newMoonBefore(m))));
   }

   public static ChineseName sexagesimalName(long n)
   {
      try
      {
         return new ChineseName((int) adjustedMod(n, 10L), (int) adjustedMod(n, 12L));
      }
      catch (BogusDateException ex)
      {
      }
      return new ChineseName();
   }

   public static ChineseName nameOfYear(int year)
   {
      return sexagesimalName(year);
   }

   public static ChineseName nameOfMonth(int year, int month)
   {
      long elapsedMonths = 12L * (year - 1) + (month - 1);
      return sexagesimalName(elapsedMonths + 15L);
   }

   public static ChineseName nameOfDay(long fixed)
   {
      return sexagesimalName(fixed + 15L);
   }

   public static final Location beijing(double tee)
   {
      long year = Gregorian.yearFromFixed((long) Math.floor(tee));
      return new Location("Beijing, China", deg(39.55D), angle(116.0D, 25.0D, 0.0D), mt(43.5D), year < 1929L ? 7.761111111111111D : 8.0D);
   }

   public static final Location chineseLocation(double tee)
   {
      return beijing(tee);
   }

   public static final Location tokyo(double date)
   {
      long year = Gregorian.yearFromFixed((long) Math.floor(date));
      if (year < 1888L)
      {
         return new Location("Tokyo, Japan", deg(35.7D), angle(139.0D, 46.0D, 0.0D), mt(24.0D), 9.317777777777778D);
      }
      return new Location("Tokyo, Japan", deg(35.0D), deg(135.0D), mt(0.0D), 9.0D);
   }

   public static final Location japaneseLocation(double date)
   {
      return tokyo(date);
   }

   public static long newYear(long gYear)
   {
      return newYearOnOrBefore(Gregorian.toFixed(gYear, 7, 1));
   }

   public static long dragonFestival(long gYear)
   {
      long elapsedYears = gYear - Gregorian.yearFromFixed(EPOCH) + 1L;
      long cycle = quotient(elapsedYears - 1L, 60.0D) + 1L;
      int year = (int) adjustedMod(elapsedYears, 60L);
      return toFixed(cycle, year, 5, false, 5);
   }

   public static long qingMing(long gYear)
   {
      return (long) Math.floor(minorSolarTermOnOrAfter(Gregorian.toFixed(gYear, 3, 30)));
   }

   public static long age(Chinese birthdate, long date)
      throws BogusDateException
   {
      Chinese today = new Chinese(date);
      if (date >= birthdate.toFixed())
      {
         return 60L * (today.cycle - birthdate.cycle) + (today.year - birthdate.year) + 1L;
      }
      throw new BogusDateException();
   }

   protected String toStringFields()
   {
      return "cycle=" + this.cycle + ",year=" + this.year + ",month=" + this.month + ",leapMonth=" + this.leapMonth + ",day=" + this.day;
   }

   public static final String[] yearStemNames =
      {
         "Jia",
         "Yi",
         "Bing",
         "Ding",
         "Wu",
         "Ji",
         "Geng",
         "Xin",
         "Ren",
         "Gui"
      };

   public static final String[] yearBranchNames =
      {
         "Zi",
         "Chou",
         "Yin",
         "Mao",
         "Chen",
         "Si",
         "Wu",
         "Wei",
         "Shen",
         "You",
         "Xu",
         "Hai"
      };

   public String format()
   {
      ChineseName yearName = nameOfYear(this.year);
      return MessageFormat.format("cycle {0,number,#}, année {1}-{2}, {3}mois {4}, jour {5}",
         new Object[]
            {
               new Long(this.cycle),
               nameFromNumber(yearName.stem, yearStemNames),
               nameFromNumber(yearName.branch, yearBranchNames).toLowerCase(),
               this.leapMonth ? "leap " : "",
               new Integer(this.month),
               new Integer(this.day)
            });
   }

   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Chinese))
      {
         return false;
      }
      Chinese o = (Chinese) obj;

      return (o.cycle == this.cycle) && (o.year == this.year) && (o.month == this.month) && (o.leapMonth == this.leapMonth) && (o.day == this.day);
   }
}
