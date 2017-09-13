package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class HinduLunar extends Date
{
   public long year;
   public int month;
   public boolean leapMonth;
   public int day;
   public boolean leapDay;
   public static final int LUNAR_ERA = 3044;
   public static final double SYNODIC_MONTH = 29.53058794607172D;
   public static final double SIDEREAL_MONTH = 27.321674162683866D;
   public static final double ANOMALISTIC_MONTH = 27.554597974680476D;

   public HinduLunar()
   {
   }

   public HinduLunar(long date)
   {
      super(date);
   }

   public HinduLunar(Date date)
   {
      super(date);
   }

   public HinduLunar(long year, int month, boolean leapMonth, int day, boolean leapDay)
   {
      this.year = year;
      this.month = month;
      this.leapMonth = leapMonth;
      this.day = day;
      this.leapDay = leapDay;
   }

   public static long toFixed(long year, int month, boolean leapMonth, int day, boolean leapDay)
   {
      return new HinduLunar(year, month, leapMonth, day, leapDay).toFixed();
   }

   public long toFixed()
   {
      double approx = OldHinduSolar.EPOCH + 365.2587564814815D * (this.year + 3044L + (this.month - 1) / 12.0D);
      long s = (long) Math.floor(approx - 1.0D / deg(360.0D) * 365.2587564814815D * (mod(HinduSolar.solarLongitude(approx) - (this.month - 1) * deg(30.0D) + deg(180.0D), deg(360.0D)) - 180.0D));
      int k = lunarDay(s + 0.25D);
      long x;
      if ((3 < k) && (k < 27))
      {
         x = k;
      }
      else
      {
         HinduLunar mid = new HinduLunar(s - 15L);
         if ((mid.month < this.month) || ((mid.leapMonth) && (!this.leapMonth)))
         {
            x = mod(k + 15, 30) - 15;
         }
         else
         {
            x = mod(k - 15, 30) + 15;
         }
      }
      long est = s + this.day - x;
      long tau = est - mod(lunarDay(est + 0.25D) - this.day + 15, 30) + 15L;
      long date = tau - 1L;
      while (!onOrBefore(this, new HinduLunar(date)))
      {
         date += 1L;
      }
      return date;
   }

   public void fromFixed(long date)
   {
      double critical = HinduSolar.sunrise(date);
      this.day = lunarDay(critical);
      this.leapDay = (this.day == lunarDay(HinduSolar.sunrise(date - 1L)));
      double lastNewMoon = newMoonBefore(critical);
      double nextNewMoon = newMoonBefore(Math.floor(lastNewMoon) + 35.0D);
      int solarMonth = HinduSolar.zodiac(lastNewMoon);
      this.leapMonth = (solarMonth == HinduSolar.zodiac(nextNewMoon));
      this.month = adjustedMod(solarMonth + 1, 12);
      this.year
         = (HinduSolar.calendarYear(nextNewMoon) - 3044L - ((this.leapMonth) && (this.month == 1) ? -1 : 0));
   }

   public void fromArray(int[] a)
   {
      this.year = a[0];
      this.month = a[1];
      this.leapMonth = (a[2] != 0);
      this.day = a[3];
      this.leapDay = (a[4] != 0);
   }

   public static double newMoonBefore(double tee)
   {
      double varepsilon = Math.pow(2.0D, -34.0D);
      double tau = tee - 1.0D / deg(360.0D) * lunarPhase(tee) * 29.53058794607172D;
      double l = tau - 1.0D;
      double u = Math.min(tee, tau + 1.0D);
      double lo = l;
      double hi = u;
      double x = (hi + lo) / 2.0D;
      while ((HinduSolar.zodiac(lo) != HinduSolar.zodiac(hi)) && (hi - lo >= varepsilon))
      {
         if (lunarPhase(x) < deg(180.0D))
         {
            hi = x;
         }
         else
         {
            lo = x;
         }
         x = (hi + lo) / 2.0D;
      }
      return x;
   }

   public static boolean onOrBefore(HinduLunar d1, HinduLunar d2)
   {
      return (d1.year < d2.year) || ((d1.year == d2.year) && ((d1.month < d2.month) || ((d1.month == d2.month) && (((d1.leapMonth) && (!d2.leapMonth)) || ((d1.leapMonth == d2.leapMonth) && ((d1.day < d2.day) || ((d1.day == d2.day) && ((!d1.leapDay) || (d2.leapDay)))))))));
   }

   public static double lunarDayAfter(double tee, double k)
   {
      double varepsilon = Math.pow(2.0D, -17.0D);
      double phase = (k - 1.0D) * 12.0D;
      double tau = tee + 0.002777777777777778D * mod(phase - lunarPhase(tee), deg(360.0D)) * 29.53058794607172D;
      double l = Math.max(tee, tau - 2.0D);
      double u = tau + 2.0D;
      double lo = l;
      double hi = u;
      double x = (hi + lo) / 2.0D;
      while (hi - lo >= varepsilon)
      {
         if (mod(lunarPhase(x) - phase, 360.0D) < deg(180.0D))
         {
            hi = x;
         }
         else
         {
            lo = x;
         }
         x = (hi + lo) / 2.0D;
      }
      return x;
   }

   public static double lunarLongitude(double tee)
   {
      return HinduSolar.truePosition(tee, 27.321674162683866D, 0.08888888888888889D, 27.554597974680476D, 0.010416666666666666D);
   }

   public static double lunarPhase(double tee)
   {
      return mod(lunarLongitude(tee) - HinduSolar.solarLongitude(tee), 360.0D);
   }

   public static int lunarDay(double tee)
   {
      return (int) quotient(lunarPhase(tee), deg(12.0D)) + 1;
   }

   public static int lunarStation(long date)
   {
      double critical = HinduSolar.sunrise(date);
      return (int) quotient(lunarLongitude(critical), deg(800.0D) / 60.0D) + 1;
   }

   public static long newYear(long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      double mina = HinduSolar.solarLongitudeAfter(jan1, deg(330.0D));
      double newMoon = lunarDayAfter(mina, 1.0D);
      long hDay = (long) Math.floor(newMoon);
      double critical = HinduSolar.sunrise(hDay);
      return hDay + ((newMoon < critical) || (lunarDay(HinduSolar.sunrise(hDay + 1L)) == 2) ? 0 : 1);
   }

   public static int karana(int n)
   {
      if (n == 1)
      {
         return 0;
      }
      if (n > 57)
      {
         return n - 50;
      }
      return adjustedMod(n - 1, 7);
   }

   public static int yoga(long date)
   {
      return (int) Math.floor(
         mod((HinduSolar.solarLongitude(date)
            + lunarLongitude(date)) * 60.0D / 800.0D, deg(27.0D)))
         + 1;
   }

   public static FixedVector sacredWednesdaysInGregorian(long gYear)
   {
      return sacredWednesdays(
         Gregorian.toFixed(gYear, 1, 1),
         Gregorian.toFixed(gYear, 12, 31));
   }

   public static FixedVector sacredWednesdays(long start, long end)
   {
      long wed = kDayOnOrAfter(start, 3);
      FixedVector result = new FixedVector();
      while (wed <= end)
      {
         HinduLunar hDate = new HinduLunar(wed);
         if (hDate.day == 8)
         {
            result.addFixed(wed);
         }
         wed += 7L;
      }
      return result;
   }

   protected String toStringFields()
   {
      return "year=" + this.year + ",month=" + this.month + ",leapMonth=" + this.leapMonth + ",day=" + this.day + ",leapDay=" + this.leapDay;
   }

   public String format()
   {
      return MessageFormat.format("{0}, {1}{2} {3}{4} {5,number,#} V.E.",
         new Object[]
            {
               nameFromDayOfWeek(toFixed(), OldHinduLunar.dayOfWeekNames),
               new Integer(this.day),
               this.leapDay ? " II" : "",
               nameFromMonth(this.month, OldHinduLunar.monthNames),
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
      if (!(obj instanceof HinduLunar))
      {
         return false;
      }
      HinduLunar o = (HinduLunar) obj;

      return (o.year == this.year) && (o.month == this.month) && (o.leapMonth == this.leapMonth) && (o.day == this.day) && (o.leapDay == this.leapDay);

   }
}
