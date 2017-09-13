package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class HinduSolar extends StandardDate
{
   public static final double SIDEREAL_YEAR = 365.2587564814815D;

   public HinduSolar()
   {
   }

   public HinduSolar(long date)
   {
      super(date);
   }

   public HinduSolar(Date date)
   {
      super(date);
   }

   public HinduSolar(long year, int month, int day)
   {
      super(year, month, day);
   }

   public static final double CREATION = OldHinduSolar.EPOCH - 7.14402296627E11D;

   public static final double ANOMALISTIC_YEAR = 365.25878920258134D;

   public static final int SOLAR_ERA = 3179;

   public static final Location UJJAIN = new Location("Ujjain, India", angle(23.0D, 9.0D, 0.0D), angle(75.0D, 46.0D, 0.0D), mt(0.0D), 5.051222222222222D);

   public static final Location HINDU_LOCALE = UJJAIN;

   public static long toFixed(long year, int month, int day)
   {
      return new HinduSolar(year, month, day).toFixed();
   }

   public long toFixed()
   {
      long approx = (long) (Math.floor((this.year + 3179L + (this.month - 1) / 12.0D) * 365.2587564814815D) + OldHinduSolar.EPOCH + this.day - 1L);
      double rate = deg(360.0D) / 365.2587564814815D;
      double phi = (this.month - 1) * deg(30.0D) + (this.day - 1) * rate;
      double capDelta = mod(solarLongitude(approx + 0.25D) - phi + deg(180.0D), 360.0D) - deg(180.0D);
      long tau = (long) (approx - Math.ceil(capDelta / rate));
      long date = tau - 2L;
      while (!onOrBefore(this, new HinduSolar(date)))
      {
         date += 1L;
      }
      return date;
   }

   public void fromFixed(long date)
   {
      double critical = sunrise(date + 1L);
      this.month = zodiac(critical);
      this.year = (calendarYear(critical) - 3179L);
      long approx = (long) (date - 3L - mod(Math.floor(solarLongitude(critical)), deg(30.0D)));
      long begin = approx;
      while (zodiac(sunrise(1L + begin)) != this.month)
      {
         begin += 1L;
      }
      this.day = ((int) (date - begin + 1L));
   }

   public static double hinduSineTable(int entry)
   {
      double exact = 3438.0D * sinDegrees(entry * 225.0D / 60.0D);
      double error = 0.215D * signum(exact) * signum(Math.abs(exact) - 1716.0D);
      return Math.round(exact + error) / 3438.0D;
   }

   public static double hinduSine(double theta)
   {
      double entry = theta * 60.0D / 225.0D;
      double fraction = mod(entry, 1.0D);
      return fraction * hinduSineTable((int) Math.ceil(entry))
         + (1.0D - fraction) * hinduSineTable((int) Math.floor(entry));
   }

   public static double hinduArcsin(double amp)
   {
      boolean neg = amp < 0.0D;
      if (neg)
      {
         amp = -amp;
      }
      int pos = 0;
      while (amp > hinduSineTable(pos))
      {
         pos++;
      }
      double below = hinduSineTable(pos - 1);
      double result = 3.75D * (pos - 1 + (amp - below) / (hinduSineTable(pos) - below));

      if (neg)
      {
         result = -result;
      }
      return result;
   }

   public static double meanPosition(double tee, double period)
   {
      return deg(360.0D) * mod((tee - CREATION) / period, 1.0D);
   }

   public static double truePosition(double tee, double period, double size, double anomalistic, double change)
   {
      double aLong = meanPosition(tee, period);
      double offset = hinduSine(meanPosition(tee, anomalistic));
      double contraction = Math.abs(offset) * change * size;
      double equation = hinduArcsin(offset * (size - contraction));
      return mod(aLong - equation, 360.0D);
   }

   public static double solarLongitude(double tee)
   {
      return truePosition(tee, 365.2587564814815D, 0.03888888888888889D, 365.25878920258134D, 0.023809523809523808D);
   }

   public static int zodiac(double tee)
   {
      return (int) quotient(solarLongitude(tee), deg(30.0D)) + 1;
   }

   public static boolean onOrBefore(HinduSolar d1, HinduSolar d2)
   {
      return (d1.year < d2.year) || ((d1.year == d2.year) && ((d1.month < d2.month) || ((d1.month == d2.month) && (d1.day <= d2.day))));
   }

   public static long calendarYear(double tee)
   {
      return Math.round((tee - OldHinduSolar.EPOCH) / 365.2587564814815D - solarLongitude(tee) / deg(360.0D));
   }

   public static double equationOfTime(long date)
   {
      double offset = hinduSine(meanPosition(date, 365.25878920258134D));
      double equationSun = offset * 3438.0D / 60.0D * (Math.abs(offset) / 1080.0D - 0.03888888888888889D);
      return dailyMotion(date) * 1.0D / 360.0D * equationSun * 1.0D / 360.0D * 365.2587564814815D;
   }

   public static double ascensionalDifference(long date, Location locale)
   {
      double sinDecl = 0.4063408958696917D * hinduSine(tropicalLongitude(date));
      double lat = locale.latitude;
      double diurnalRadius = hinduSine(deg(90.0D) + hinduArcsin(sinDecl));
      double tanLat = hinduSine(lat) / hinduSine(deg(90.0D) + lat);
      double earthSine = sinDecl * tanLat;
      return hinduArcsin(-(earthSine / diurnalRadius));
   }

   public static double tropicalLongitude(long date)
   {
      long days = (long) Math.floor(date - OldHinduSolar.EPOCH);
      double precession = deg(27.0D) - Math.abs(deg(54.0D) - mod(deg(27.0D) + deg(108.0D) * 3.80247937727211E-7D * days, 108.0D));
      return mod(solarLongitude(date) - precession, 360.0D);
   }

   public static double risingSign(long date)
   {
      int index = (int) mod(quotient(tropicalLongitude(date), deg(30.0D)), 6L);
      return rs[index];
   }

   private static final double[] rs =
      {
         0.9277777777777778D, 0.9972222222222222D, 1.075D, 1.075D, 0.9972222222222222D, 0.9277777777777778D
      };

   public static double dailyMotion(long date)
   {
      double meanMotion = deg(360.0D) / 365.2587564814815D;
      double anomaly = meanPosition(date, 365.25878920258134D);
      double epicycle = 0.03888888888888889D - Math.abs(hinduSine(anomaly)) / 1080.0D;
      int entry = (int) quotient(anomaly, deg(225.0D) / 60.0D);
      double sineTableStep = hinduSineTable(entry + 1) - hinduSineTable(entry);
      double factor = sineTableStep * -15.0D * epicycle;
      return meanMotion * (factor + 1.0D);
   }

   public static double solarSiderealDifference(long date)
   {
      return dailyMotion(date) * risingSign(date);
   }

   public static double sunrise(long date)
   {
      return date + 0.25D + (UJJAIN.longitude - HINDU_LOCALE.longitude) / deg(360.0D)
         + equationOfTime(date) + 0.9972696898509495D / deg(360.0D) * (ascensionalDifference(date, HINDU_LOCALE) + 0.25D * solarSiderealDifference(date));
   }

   public static double altSunrise(long date)
   {
      try
      {
         double rise = Date.sunrise(date, UJJAIN);
         return 6.944444444444444E-4D * Math.round(rise * 24.0D * 60.0D);
      } catch (BogusTimeException ex)
      {
      }
      return 0.0D;
   }

   public static double solarLongitudeAfter(double tee, double phi)
   {
      double varepsilon = 1.0E-6D;
      double tau = tee + 1.0146076568930043D * mod(phi - solarLongitude(tee), deg(360.0D));
      double l = Math.max(tee, tau - 5.0D);
      double u = tau + 5.0D;
      double lo = l;
      double hi = u;
      double x = (hi + lo) / 2.0D;
      while (hi - lo >= varepsilon)
      {
         if (mod(solarLongitude(x) - phi, 360.0D) < deg(180.0D))
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

   public static double meshaSamkranti(long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      return solarLongitudeAfter(jan1, deg(0.0D));
   }

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#} S.E.",
         new Object[]
            {
               nameFromDayOfWeek(toFixed(), OldHinduSolar.dayOfWeekNames),
               new Integer(this.day),
               nameFromMonth(adjustedMod(this.month + 1, 12), OldHinduLunar.monthNames),
               new Long(this.year)
            });
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof HinduSolar))
      {
         return false;
      }
      return internalEquals(obj);
   }
}
