package fr.ozf.fmr.ReingoldDershowitz;

import java.io.Serializable;

public abstract class ProtoDate
   implements Cloneable, Serializable
{

   public static final int JANUARY = 1;
   public static final int FEBRUARY = 2;
   public static final int MARCH = 3;
   public static final int APRIL = 4;
   public static final int MAY = 5;
   public static final int JUNE = 6;
   public static final int JULY = 7;
   public static final int AUGUST = 8;
   public static final int SEPTEMBER = 9;
   public static final int OCTOBER = 10;
   public static final int NOVEMBER = 11;
   public static final int DECEMBER = 12;
   public static final int SUNDAY = 0;
   public static final int MONDAY = 1;
   public static final int TUESDAY = 2;
   public static final int WEDNESDAY = 3;
   public static final int THURSDAY = 4;
   public static final int FRIDAY = 5;
   public static final int SATURDAY = 6;
   public static final double JD_EPOCH = -1721424.5D;
   public static final long MJD_EPOCH = 678576L;
   public static final double J2000 = hr(12.0D) + Gregorian.toFixed(2000L, 1, 1);

   public static final double MEAN_TROPICAL_YEAR = 365.242189D;

   public static final double MEAN_SYNODIC_MONTH = 29.530588853D;

   public static final double NEW = deg(0.0D);

   public static final double FIRST_QUARTER = deg(90.0D);

   public static final double FULL = deg(180.0D);

   public static final double LAST_QUARTER = deg(270.0D);

   public static final double SPRING = deg(0.0D);

   public static final double SUMMER = deg(90.0D);

   public static final double AUTUMN = deg(180.0D);

   public static final double WINTER = deg(270.0D);

   public static final Location JERUSALEM = new Location("Jerusalem, Israel", deg(31.8D), deg(35.2D), mt(800.0D), 2.0D);

   public ProtoDate()
   {
   }

   public ProtoDate(long date)
   {
      fromFixed(date);
   }

   public ProtoDate(Date date)
   {
      fromDate(date);
   }

   public abstract void fromFixed(long paramLong);

   public void fromDate(Date fromDate)
   {
      convert(fromDate, this);
   }

   public abstract void fromArray(int[] paramArrayOfInt);

   public static void convert(Date fromDate, ProtoDate toDate)
   {
      toDate.fromFixed(fromDate.toFixed());
   }

   public static double currentMoment()
   {
      return Gregorian.toFixed(1970L, 1, 1)
         + System.currentTimeMillis() / 8.64E7D;
   }

   public static long currentDate()
   {
      return (long) currentMoment();
   }

   public static long difference(Date date1, Date date2)
   {
      return date2.toFixed() - date1.toFixed();
   }

   public static long difference(long date1, Date date2)
   {
      return date2.toFixed() - date1;
   }

   public static long difference(Date date1, long date2)
   {
      return date2 - date1.toFixed();
   }

   public static long difference(long date1, long date2)
   {
      return date2 - date1;
   }

   public static double mod(double x, double y)
   {
      return x % y;
   }

   public static int mod(int x, int y)
   {
      return x % y;
   }

   public static long mod(long x, long y)
   {
      return x % y;
   }

   public static long quotient(double x, double y)
   {
      return (long) Math.floor(x / y);
   }

   public static int adjustedMod(int x, int y)
   {
      int q = x % y;
      if (q == 0)
      {
         q = y;
      }
      return q;
   }

   public static long adjustedMod(long x, long y)
   {
      long q = x % y;
      if (q == 0)
      {
         q = y;
      }
      return q;
   }

   public static double adjustedMod(double x, double y)
   {
      double q = x % y;
      if (q == 0)
      {
         q = y;
      }
      return q;
   }

   public static long dayOfWeekFromFixed(long date)
   {
      return mod(date, 7L);
   }

   public static long kDayOnOrBefore(long date, int k)
   {
      return date - dayOfWeekFromFixed(date - k);
   }

   public static long kDayOnOrAfter(long date, int k)
   {
      return kDayOnOrBefore(date + 6L, k);
   }

   public static long kDayNearest(long date, int k)
   {
      return kDayOnOrBefore(date + 3L, k);
   }

   public static long kDayAfter(long date, int k)
   {
      return kDayOnOrBefore(date + 7L, k);
   }

   public static long kDayBefore(long date, int k)
   {
      return kDayOnOrBefore(date - 1L, k);
   }

   public static long nthKDay(int n, int k, long gDate)
   {
      return n > 0
         ? kDayBefore(gDate, k) + 7 * n
         : kDayAfter(gDate, k) + 7 * n;
   }

   public static long firstKDay(int k, long gDate)
   {
      return nthKDay(1, k, gDate);
   }

   public static long lastKDay(int k, long gDate)
   {
      return nthKDay(-1, k, gDate);
   }

   public static int signum(double x)
   {
      if (x < 0.0D)
      {
         return -1;
      }
      if (x > 0.0D)
      {
         return 1;
      }
      return 0;
   }

   public static double square(double x)
   {
      return x * x;
   }

   public static double poly(double x, double[] a)
   {
      double result = a[0];
      for (int i = 1; i < a.length; i++)
      {
         result += a[i] * Math.pow(x, i);
      }
      return result;
   }

   public static double hr(double x)
   {
      return x / 24.0D;
   }

   public static double mt(double x)
   {
      return x;
   }

   public static double deg(double x)
   {
      return x;
   }

   public static double[] deg(double[] x)
   {
      return x;
   }

   public static double angle(double d, double m, double s)
   {
      return d + (m + s / 60.0D) / 60.0D;
   }

   public static double degrees(double theta)
   {
      return mod(theta, 360.0D);
   }

   public static double radiansToDegrees(double theta)
   {
      return degrees(theta / 3.141592653589793D * 180.0D);
   }

   public static double degreesToRadians(double theta)
   {
      return degrees(theta) * 3.141592653589793D / 180.0D;
   }

   public static double sinDegrees(double theta)
   {
      return Math.sin(degreesToRadians(theta));
   }

   public static double cosDegrees(double theta)
   {
      return Math.cos(degreesToRadians(theta));
   }

   public static double tanDegrees(double theta)
   {
      return Math.tan(degreesToRadians(theta));
   }

   public static double arcTanDegrees(double x, int quad)
   {
      double alpha = radiansToDegrees(Math.atan(x));
      return mod((quad == 1) || (quad == 4) ? alpha : alpha + deg(180.0D), 360.0D);
   }

   public static double arcSinDegrees(double x)
   {
      return radiansToDegrees(Math.asin(x));
   }

   public static double arcCosDegrees(double x)
   {
      return radiansToDegrees(Math.acos(x));
   }

   public static double standardFromUniversal(double teeU, Location locale)
   {
      return teeU + locale.zone / 24.0D;
   }

   public static double universalFromStandard(double teeS, Location locale)
   {
      return teeS - locale.zone / 24.0D;
   }

   public static double localFromUniversal(double teeU, Location locale)
   {
      return teeU + locale.longitude / deg(360.0D);
   }

   public static double universalFromLocal(double teeEll, Location locale)
   {
      return teeEll - locale.longitude / deg(360.0D);
   }

   public static double standardFromLocal(double teeEll, Location locale)
   {
      return standardFromUniversal(universalFromLocal(teeEll, locale), locale);
   }

   public static double localFromStandard(double teeS, Location locale)
   {
      return localFromUniversal(universalFromStandard(teeS, locale), locale);
   }

   public static double midday(long date, Location locale)
   {
      return standardFromLocal(localFromApparent(date + hr(12.0D)), locale);
   }

   public static double midnight(long date, Location locale)
   {
      return standardFromLocal(localFromApparent(date), locale);
   }

   public static double momentFromJD(double jd)
   {
      return jd + -1721424.5D;
   }

   public static double jdFromMoment(double tee)
   {
      return tee - -1721424.5D;
   }

   public static long fixedFromJD(double jd)
   {
      return (long) Math.floor(momentFromJD(jd));
   }

   public static double jdFromFixed(long date)
   {
      return jdFromMoment(date);
   }

   public static long fixedFromMJD(double mjd)
   {
      return (long) Math.floor(mjd + 678576.0D);
   }

   public static double mjdFromFixed(long date)
   {
      return date - 678576L;
   }

   public static double direction(Location locale, Location focus)
   {
      double phi = locale.latitude;
      double phiPrime = focus.latitude;
      double psi = locale.longitude;
      double psiPrime = focus.longitude;
      double denom = cosDegrees(phi) * tanDegrees(phiPrime) - sinDegrees(phi) * cosDegrees(psi - psiPrime);
      return denom == 0.0D
         ? 0.0D
         : mod(arcTanDegrees(
         sinDegrees(psiPrime - psi) / denom,
         denom < 0.0D ? 2 : 1),
         360.0D);
   }

   public static double julianCenturies(double tee)
   {
      return (dynamicalFromUniversal(tee) - J2000) / 36525.0D;
   }

   public static double obliquity(double tee)
   {
      double c = julianCenturies(tee);
      return angle(23.0D, 26.0D, 21.448D) + poly(c, ob.coeffObliquity);
   }

   private static class ob
   {

      private static final double[] coeffObliquity =
         {
            0.0D, ProtoDate.angle(0.0D, 0.0D, -46.815D), ProtoDate.angle(0.0D, 0.0D, -5.9E-4D), ProtoDate.angle(0.0D, 0.0D, 0.001813D)
         };
   }

   public static double momentFromDepression(double approx, Location locale, double alpha)
      throws BogusTimeException
   {
      double phi = locale.latitude;
      double tee = universalFromLocal(approx, locale);
      double delta = arcSinDegrees(sinDegrees(obliquity(tee)) * sinDegrees(solarLongitude(tee)));
      boolean morning = mod(approx, 1.0D) < 0.5D;
      double sineOffset = tanDegrees(phi) * tanDegrees(delta)
         + sinDegrees(alpha) / (cosDegrees(delta) * cosDegrees(phi));
      double offset = mod(0.5D + arcSinDegrees(sineOffset) / deg(360.0D), 1.0D) - 0.5D;
      if (Math.abs(sineOffset) > 1.0D)
      {
         throw new BogusTimeException();
      }
      return localFromApparent(Math.floor(approx) + (morning ? 0.25D - offset : 0.75D + offset));
   }

   public static double dawn(long date, Location locale, double alpha)
      throws BogusTimeException
   {
      double approx;

      try
      {
         approx = momentFromDepression(date + 0.25D, locale, alpha);
      }
      catch (BogusTimeException ex)
      {
         approx = date;
      }
      double result = momentFromDepression(approx, locale, alpha);
      return standardFromLocal(result, locale);
   }

   public static double dusk(long date, Location locale, double alpha)
      throws BogusTimeException
   {
      double approx;

      try
      {
         approx = momentFromDepression(date + 0.75D, locale, alpha);
      }
      catch (BogusTimeException ex)
      {
         approx = date + 0.99D;
      }
      double result = momentFromDepression(approx, locale, alpha);
      return standardFromLocal(result, locale);
   }

   public static double sunrise(long date, Location locale)
      throws BogusTimeException
   {
      double h = Math.max(0.0D, locale.elevation);
      double capR = mt(6372000.0D);
      double dip = arcCosDegrees(capR / (capR + h));
      double alpha = angle(0.0D, 50.0D, 0.0D) + dip;
      return dawn(date, locale, alpha);
   }

   public static double sunset(long date, Location locale)
      throws BogusTimeException
   {
      double h = Math.max(0.0D, locale.elevation);
      double capR = mt(6372000.0D);
      double dip = arcCosDegrees(capR / (capR + h));
      double alpha = angle(0.0D, 50.0D, 0.0D) + dip;
      return dusk(date, locale, alpha);
   }

   public static double temporalHour(long date, Location locale)
      throws BogusTimeException
   {
      return (sunset(date, locale) - sunrise(date, locale)) / 12.0D;
   }

   public static double standardFromSundial(long date, double hour, Location locale)
      throws BogusTimeException
   {
      double tee = temporalHour(date, locale);
      return sunrise(date, locale) + ((6.0D <= hour) && (hour <= 18.0D) ? (hour - 6.0D) * tee : (hour - 6.0D) * (0.08333333333333333D - tee));
   }

   public static double universalFromDynamical(double tee)
   {
      return tee - ephemerisCorrection(tee);
   }

   public static double dynamicalFromUniversal(double tee)
   {
      return tee + ephemerisCorrection(tee);
   }

   public static double ephemerisCorrection(double tee)
   {
      long year = Gregorian.yearFromFixed((long) Math.floor(tee));
      double c = difference(Gregorian.toFixed(1900L, 1, 1), Gregorian.toFixed(year, 7, 1)) / 36525.0D;
      double result;
      if ((1988L <= year) && (year <= 2019L))
      {
         result = (year - 1933L) / 86400.0D;
      }
      else if ((1900L <= year) && (year <= 1987L))
      {
         result = poly(c, ec.coeff19th);
      }
      else if ((1800L <= year) && (year <= 1899L))
      {
         result = poly(c, ec.coeff18th);
      }
      else if ((1700L <= year) && (year <= 1799L))
      {
         result = poly(year - 1700L, ec.coeff17th) / 86400.0D;
      }
      else if ((1620L <= year) && (year <= 1699L))
      {
         result = poly(year - 1600L, ec.coeff16th) / 86400.0D;
      }
      else
      {
         double x = hr(12.0D) + difference(Gregorian.toFixed(1810L, 1, 1), Gregorian.toFixed(year, 1, 1));
         return (x * x / 4.104848E7D - 15.0D) / 86400.0D;
      }
      return result;
   }

   private static class ec
   {

      private static final double[] coeff19th =
         {
            -2.0E-5D, 2.97E-4D, 0.025184D, -0.181133D, 0.55304D, -0.861938D, 0.677066D, -0.212591D
         };
      private static final double[] coeff18th =
         {
            -9.0E-6D, 0.003844D, 0.083563D, 0.865736D, 4.867575D, 15.845535D, 31.332267D, 38.291999D, 28.316289D, 11.636204D, 2.043794D
         };
      private static final double[] coeff17th =
         {
            8.118780842D, -0.005092142D, 0.003336121D, -2.66484E-5D
         };
      private static final double[] coeff16th =
         {
            196.58333D, -4.0675D, 0.0219167D
         };
   }

   public static double equationOfTime(double tee)
   {
      double c = julianCenturies(tee);
      double longitude = poly(c, et.coeffLongitude);
      double anomaly = poly(c, et.coeffAnomaly);
      double eccentricity = poly(c, et.coeffEccentricity);
      double varepsilon = obliquity(tee);
      double y = square(tanDegrees(varepsilon / 2.0D));
      double equation = 0.15915494309189535D * (y * sinDegrees(2.0D * longitude)
         + -2.0D * eccentricity * sinDegrees(anomaly)
         + 4.0D * eccentricity * y * sinDegrees(anomaly) * cosDegrees(2.0D * longitude)
         + -0.5D * y * y * sinDegrees(4.0D * longitude)
         + -1.25D * eccentricity * eccentricity * sinDegrees(2.0D * anomaly));
      return signum(equation) * Math.min(Math.abs(equation), hr(12.0D));
   }

   private static class et
   {

      private static final double[] coeffLongitude = ProtoDate.deg(new double[]
         {
            280.46645D, 36000.76983D, 3.032E-4D
         });
      private static final double[] coeffAnomaly = ProtoDate.deg(new double[]
         {
            357.5291D, 35999.0503D, -1.559E-4D, -4.8E-7D
         });
      private static final double[] coeffEccentricity = ProtoDate.deg(new double[]
         {
            0.016708617D, -4.2037E-5D, -1.236E-7D
         });
   }

   public static double localFromApparent(double tee)
   {
      return tee - equationOfTime(tee);
   }

   public static double apparentFromLocal(double tee)
   {
      return tee + equationOfTime(tee);
   }

   public static double solarLongitude(double tee)
   {
      double c = julianCenturies(tee);
      double sigma = 0.0D;
      for (int i = 0; i < sl.coefficients.length; i++)
      {
         sigma += sl.coefficients[i] * sinDegrees(sl.multipliers[i] * c + sl.addends[i]);
      }
      double longitude = deg(282.7771834D)
         + 36000.76953744D * c
         + 5.729577951308232E-6D * sigma;
      return mod(longitude + aberration(tee) + nutation(tee), 360.0D);
   }

   private static class sl
   {

      private static final int[] coefficients =
         {
            403406, 195207, 119433, 112392, 3891, 2819, 1721,
            660, 350, 334, 314, 268, 242, 234, 158, 132, 129, 114,
            99, 93, 86, 78, 72, 68, 64, 46, 38, 37, 32, 29, 28, 27, 27,
            25, 24, 21, 21, 20, 18, 17, 14, 13, 13, 13, 12, 10, 10, 10,
            10
         };

      private static final double[] multipliers =
         {
            0.9287892D, 35999.1376958D, 35999.4089666D,
            35998.7287385D, 71998.20261D, 71998.4403D,
            36000.35726D, 71997.4812D, 32964.4678D,
            -19.441D, 445267.1117D, 45036.884D, 3.1008D,
            22518.4434D, -19.9739D, 65928.9345D,
            9038.0293D, 3034.7684D, 33718.148D, 3034.448D,
            -2280.773D, 29929.992D, 31556.493D, 149.588D,
            9037.75D, 107997.405D, -4444.176D, 151.771D,
            67555.316D, 31556.08D, -4561.54D,
            107996.706D, 1221.655D, 62894.167D,
            31437.369D, 14578.298D, -31931.757D,
            34777.243D, 1221.999D, 62894.511D,
            -4442.039D, 107997.909D, 119.066D, 16859.071D,
            -4.578D, 26895.292D, -39.127D, 12297.536D,
            90073.778D
         };

      private static final double[] addends =
         {
            270.54861D, 340.19128D, 63.91854D, 331.2622D,
            317.843D, 86.631D, 240.052D, 310.26D, 247.23D,
            260.87D, 297.82D, 343.14D, 166.79D, 81.53D,
            3.5D, 132.75D, 182.95D, 162.03D, 29.8D,
            266.4D, 249.2D, 157.6D, 257.8D, 185.1D, 69.9D,
            8.0D, 197.1D, 250.4D, 65.3D, 162.7D, 341.5D,
            291.6D, 98.5D, 146.7D, 110.0D, 5.2D, 342.6D,
            230.9D, 256.1D, 45.3D, 242.9D, 115.2D, 151.8D,
            285.3D, 53.3D, 126.6D, 205.7D, 85.9D,
            146.1D
         };
   }

   public static double nutation(double tee)
   {
      double c = julianCenturies(tee);
      double capA = poly(c, nu.coeffa);
      double capB = poly(c, nu.coeffb);
      return deg(-0.004778D) * sinDegrees(capA)
         + deg(-3.667E-4D) * sinDegrees(capB);
   }

   private static class nu
   {

      private static final double[] coeffa = ProtoDate.deg(new double[]
         {
            124.9D, -1934.134D, 0.002063D
         });
      private static final double[] coeffb = ProtoDate.deg(new double[]
         {
            201.11D, 72001.5377D, 5.7E-4D
         });
   }

   public static double aberration(double tee)
   {
      double c = julianCenturies(tee);
      return deg(9.74E-5D) * cosDegrees(deg(177.63D) + deg(35999.01848D) * c) - deg(0.005575D);
   }

   public static double solarLongitudeAfter(double tee, double phi)
   {
      double varepsilon = 1.0E-5D;
      double rate = 365.242189D / deg(360.0D);
      double tau = tee + rate * mod(phi - solarLongitude(tee), 360.0D);
      double l = Math.max(tee, tau - 5.0D);
      double u = tau + 5.0D;

      double lo = l;
      double hi = u;
      double x = (hi + lo) / 2.0D;
      while (hi - lo > varepsilon)
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

   public static double lunarLongitude(double tee)
   {
      double c = julianCenturies(tee);
      double meanMoon = degrees(poly(c, llon.coeffMeanMoon));
      double elongation = degrees(poly(c, llon.coeffElongation));
      double solarAnomaly = degrees(poly(c, llon.coeffSolarAnomaly));
      double lunarAnomaly = degrees(poly(c, llon.coeffLunarAnomaly));
      double moonNode = degrees(poly(c, llon.coeffMoonNode));
      double capE = poly(c, llon.coeffCapE);
      double sigma = 0.0D;
      for (int i = 0; i < llon.argsLunarElongation.length; i++)
      {
         double x = llon.argsSolarAnomaly[i];

         sigma = sigma + llon.sineCoefficients[i] * Math.pow(capE, Math.abs(x)) * sinDegrees(llon.argsLunarElongation[i] * elongation
            + x * solarAnomaly
            + llon.argsLunarAnomaly[i] * lunarAnomaly
            + llon.argsMoonFromNode[i] * moonNode);
      }
      double correction = deg(1.0D) / 1000000.0D * sigma;
      double venus = deg(3958.0D) / 1000000.0D * sinDegrees(119.75D + c * 131.849D);
      double jupiter = deg(318.0D) / 1000000.0D * sinDegrees(53.09D + c * 479264.29D);
      double flatEarth = deg(1962.0D) / 1000000.0D * sinDegrees(meanMoon - moonNode);
      return mod(meanMoon + correction + venus + jupiter + flatEarth + nutation(tee), 360.0D);
   }

   private static class llon
   {

      private static final double[] coeffMeanMoon = ProtoDate.deg(new double[]
         {
            218.3164591D, 481267.88134236D, -0.0013268D, 1.855835023689734E-6D, -1.5338834862103876E-8D
         });
      private static final double[] coeffElongation = ProtoDate.deg(new double[]
         {
            297.8502042D, 445267.1115168D, -0.00163D, 1.8319447192361523E-6D, -8.844469995135542E-9D
         });
      private static final double[] coeffSolarAnomaly = ProtoDate.deg(new double[]
         {
            357.5291092D, 35999.0502909D, -1.536E-4D, 4.083299305839118E-8D
         });
      private static final double[] coeffLunarAnomaly = ProtoDate.deg(new double[]
         {
            134.9634114D, 477198.8676313D, 0.008997D, 1.4347408140719379E-5D, -6.797172376291463E-8D
         });
      private static final double[] coeffMoonNode = ProtoDate.deg(new double[]
         {
            93.2720993D, 483202.0175273D, -0.0034029D, -2.8360748723766307E-7D, 1.1583324645839848E-9D
         });
      private static final double[] coeffCapE =
         {
            1.0D, -0.002516D, -7.4E-6D
         };
      private static final byte[] argsLunarElongation =
         {
            0, 2, 2, 0, 0, 0, 2, 2, 2, 2, 0, 1, 0, 2, 0, 0, 4, 0, 4, 2, 2, 1,
            1, 2, 2, 4, 2, 0, 2, 2, 1, 2, 0, 0, 2, 2, 2, 4, 0, 3, 2, 4, 0, 2,
            2, 2, 4, 0, 4, 1, 2, 0, 1, 3, 4, 2, 0, 1, 2
         };

      private static final byte[] argsSolarAnomaly =
         {
            0, 0, 0, 0, 1, 0, 0, -1, 0, -1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1,
            0, 1, -1, 0, 0, 0, 1, 0, -1, 0, -2, 1, 2, -2, 0, 0, -1, 0, 0, 1,
            -1, 2, 2, 1, -1, 0, 0, -1, 0, 1, 0, 1, 0, 0, -1, 2, 1, 0
         };

      private static final byte[] argsLunarAnomaly =
         {
            1, -1, 0, 2, 0, 0, -2, -1, 1, 0, -1, 0, 1, 0, 1, 1, -1, 3, -2,
            -1, 0, -1, 0, 1, 2, 0, -3, -2, -1, -2, 1, 0, 2, 0, -1, 1,
            0, -1, 2, -1, 1, -2, -1, -1, -2, 0, 1, 4, 0, -2, 0, 2, 1, -2, -3,
            2, 1, -1, 3
         };

      private static final byte[] argsMoonFromNode =
         {
            0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, -2, 2, -2,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, -2, 2, 0, 2,
            0, 0, 0, 0, 0, 0, -2, 0, 0, 0, 0, -2, -2, 0, 0, 0, 0, 0, 0, 0
         };

      private static final int[] sineCoefficients =
         {
            6288774, 1274027, 658314, 213618, -185116, -114332,
            58793, 57066, 53322, 45758, -40923, -34720, 35153,
            15327, 53008, 10980, 10675, 10034, 8548, 57648,
            58770, 60373, 4987, 4036, 3994, 3861, 3665, 62847,
            62934, 2390, 63188, 2236, 63416, 63467, 2048, 63763,
            63941, 1215, 64426, 64644, 64726, 759, 64823, 64836, 691,
            596, 549, 537, 520, 65049, 65137, 65155, 351, 65196, 330,
            327, 65213, 299, 294
         };
   }

   public static double nthNewMoon(long n)
   {
      double k = n - 24724L;
      double c = k / 1236.85D;
      double approx = poly(c, nm.coeffApprox);
      double capE = poly(c, nm.coeffCapE);
      double solarAnomaly = poly(c, nm.coeffSolarAnomaly);
      double lunarAnomaly = poly(c, nm.coeffLunarAnomaly);
      double moonArgument = poly(c, nm.coeffMoonArgument);
      double capOmega = poly(c, nm.coeffCapOmega);
      double correction = -1.7E-4D * sinDegrees(capOmega);
      for (int ix = 0; ix < nm.sineCoeff.length; ix++)
      {
         correction = correction + nm.sineCoeff[ix] * Math.pow(capE, nm.EFactor[ix]) * sinDegrees(nm.solarCoeff[ix] * solarAnomaly
            + nm.lunarCoeff[ix] * lunarAnomaly
            + nm.moonCoeff[ix] * moonArgument);
      }
      double additional = 0.0D;
      for (int ix = 0; ix < nm.addConst.length; ix++)
      {
         additional = additional + nm.addFactor[ix] * sinDegrees(nm.addConst[ix] + nm.addCoeff[ix] * k);
      }
      double extra = 3.25E-4D * sinDegrees(poly(c, nm.extra));
      return universalFromDynamical(approx + correction + extra + additional);
   }

   private static class nm
   {

      private static final double[] coeffApprox =
         {
            730125.59765D, 36524.90882283305D, 1.337E-4D, -1.5E-7D, 7.3E-10D
         };
      private static final double[] coeffCapE =
         {
            1.0D, -0.002516D, -7.4E-6D
         };
      private static final double[] coeffSolarAnomaly = ProtoDate.deg(new double[]
         {
            2.5534D, 35998.960422026496D, -2.18E-5D, -1.1E-7D
         });
      private static final double[] coeffLunarAnomaly = ProtoDate.deg(new double[]
         {
            201.5643D, 477197.67640106793D, 0.0107438D, 1.239E-5D, -5.8E-8D
         });
      private static final double[] coeffMoonArgument = ProtoDate.deg(new double[]
         {
            160.7108D, 483200.81131396897D, -0.0016341D, -2.27E-6D, 1.1E-8D
         });
      private static final double[] coeffCapOmega =
         {
            124.7746D, -1934.1313612299998D, 0.0020691D, 2.15E-6D
         };
      private static final byte[] EFactor =
         {
            0, 1, 0, 0, 1, 1, 2, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
         };
      private static final byte[] solarCoeff =
         {
            0, 1, 0, 0, -1, 1, 2, 0, 0, 1, 0, 1, 1, -1, 2, 0, 3, 1, 0, 1, -1, -1, 1, 0
         };
      private static final byte[] lunarCoeff =
         {
            1, 0, 2, 0, 1, 1, 0, 1, 1, 2, 3, 0, 0, 2, 1, 2, 0, 1, 2, 1, 1, 1, 3, 4
         };
      private static final byte[] moonCoeff =
         {
            0, 0, 0, 2, 0, 0, 0, -2, 2, 0, 0, 2, -2, 0, 0, -2, 0, -2, 2, 2, 2, -2, 0, 0
         };
      private static final double[] sineCoeff =
         {
            -0.4072D, 0.17241D, 0.01608D, 0.01039D, 0.00739D, -0.00514D, 0.00208D,
            -0.00111D, -5.7E-4D, 5.6E-4D, -4.2E-4D, 4.2E-4D, 3.8E-4D, -2.4E-4D,
            -7.0E-5D, 4.0E-5D, 4.0E-5D, 3.0E-5D, 3.0E-5D, -3.0E-5D, 3.0E-5D,
            -2.0E-5D, -2.0E-5D, 2.0E-5D
         };

      private static final double[] addConst =
         {
            251.88D, 251.83D, 349.42D, 84.66D, 141.74D, 207.14D, 154.84D, 34.52D, 207.19D,
            291.34D, 161.72D, 239.56D, 331.55D
         };

      private static final double[] addCoeff =
         {
            0.016321D, 26.641886D, 36.412478D, 18.206239D, 53.303771D, 2.453732D,
            7.30686D, 27.261239D, 0.121824D, 1.844379D, 24.198154D, 25.513099D, 3.592518D
         };

      private static final double[] addFactor =
         {
            1.65E-4D, 1.64E-4D, 1.26E-4D, 1.1E-4D, 6.2E-5D, 6.0E-5D, 5.6E-5D,
            4.7E-5D, 4.2E-5D, 4.0E-5D, 3.7E-5D, 3.5E-5D, 2.3E-5D
         };

      private static final double[] extra = ProtoDate.deg(new double[]
         {
            299.77D, 132.8475848D, -0.009173D
         });
   }

   public static double newMoonBefore(double tee)
   {
      double t0 = nthNewMoon(0L);
      double phi = lunarPhase(tee);
      long n = Math.round((tee - t0) / 29.530588853D - phi / deg(360.0D));
      long k = n - 1L;
      while (nthNewMoon(k) < tee)
      {
         k += 1L;
      }
      k -= 1L;
      return nthNewMoon(k);
   }

   public static double newMoonAfter(double tee)
   {
      double t0 = nthNewMoon(0L);
      double phi = lunarPhase(tee);
      long n = Math.round((tee - t0) / 29.530588853D - phi / deg(360.0D));
      long k = n;
      while (nthNewMoon(k) < tee)
      {
         k += 1L;
      }
      return nthNewMoon(k);
   }

   public static double lunarPhase(double tee)
   {
      return mod(lunarLongitude(tee) - solarLongitude(tee), 360.0D);
   }

   public static double lunarPhaseBefore(double tee, double phi)
   {
      double varepsilon = 1.0E-5D;
      double tau = tee - 0.08202941348055556D * mod(lunarPhase(tee) - phi, deg(360.0D));
      double l = tau - 2.0D;
      double u = Math.min(tee, tau + 2.0D);

      double lo = l;
      double hi = u;
      double x = (hi + lo) / 2.0D;
      while (hi - lo > varepsilon)
      {
         if (mod(lunarPhase(x) - phi, 360.0D) < deg(180.0D))
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

   public static double lunarPhaseAfter(double tee, double phi)
   {
      double varepsilon = 1.0E-5D;
      double tau = tee + 0.08202941348055556D * mod(phi - lunarPhase(tee), deg(360.0D));
      double l = Math.max(tee, tau - 2.0D);
      double u = tau + 2.0D;

      double lo = l;
      double hi = u;
      double x = (hi + lo) / 2.0D;
      while (hi - lo > varepsilon)
      {
         if (mod(lunarPhase(x) - phi, 360.0D) < deg(180.0D))
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

   public static double lunarLatitude(double tee)
   {
      double c = julianCenturies(tee);
      double longitude = degrees(poly(c, llat.coeffLongitude));
      double elongation = degrees(poly(c, llat.coeffElongation));
      double solarAnomaly = degrees(poly(c, llat.coeffSolarAnomaly));
      double lunarAnomaly = degrees(poly(c, llat.coeffLunarAnomaly));
      double moonNode = degrees(poly(c, llat.coeffMoonNode));
      double capE = poly(c, llat.coeffCapE);
      double latitude = 0.0D;
      for (int i = 0; i < llat.argsLunarElongation.length; i++)
      {
         double x = llat.argsSolarAnomaly[i];

         latitude = latitude + llat.sineCoefficients[i] * Math.pow(capE, Math.abs(x)) * sinDegrees(llat.argsLunarElongation[i] * elongation
            + x * solarAnomaly
            + llat.argsLunarAnomaly[i] * lunarAnomaly
            + llat.argsMoonNode[i] * moonNode);
      }
      latitude *= deg(1.0D) / 1000000.0D;
      double venus = deg(175.0D) / 1000000.0D * (sinDegrees(deg(119.75D) + c * 131.849D + moonNode) + sinDegrees(deg(119.75D) + c * 131.849D - moonNode));
      double flatEarth = deg(-2235.0D) / 1000000.0D * sinDegrees(longitude)
         + deg(127.0D) / 1000000.0D * sinDegrees(longitude - lunarAnomaly)
         + deg(-115.0D) / 1000000.0D * sinDegrees(longitude + lunarAnomaly);
      double extra = deg(382.0D) / 1000000.0D * sinDegrees(deg(313.45D) + c * deg(481266.484D));
      return mod(latitude + venus + flatEarth + extra, 360.0D);
   }

   private static class llat
   {

      private static final double[] coeffLongitude = ProtoDate.deg(new double[]
         {
            218.3164591D, 481267.88134236D, -0.0013268D, 1.855835023689734E-6D, -1.5338834862103876E-8D
         });
      private static final double[] coeffElongation = ProtoDate.deg(new double[]
         {
            297.8502042D, 445267.1115168D, -0.00163D, 1.8319447192361523E-6D, -8.844469995135542E-9D
         });
      private static final double[] coeffSolarAnomaly = ProtoDate.deg(new double[]
         {
            357.5291092D, 35999.0502909D, -1.536E-4D, 4.083299305839118E-8D
         });
      private static final double[] coeffLunarAnomaly = ProtoDate.deg(new double[]
         {
            134.9634114D, 477198.8676313D, 0.008997D, 1.4347408140719379E-5D, -6.797172376291463E-8D
         });
      private static final double[] coeffMoonNode = ProtoDate.deg(new double[]
         {
            93.2720993D, 483202.0175273D, -0.0034029D, -2.8360748723766307E-7D, 1.1583324645839848E-9D
         });
      private static final double[] coeffCapE =
         {
            1.0D, -0.002516D, -7.4E-6D
         };
      private static final byte[] argsLunarElongation =
         {
            0, 0, 0, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 4,
            0, 0, 0, 1, 0, 0, 0, 1, 0, 4, 4, 0, 4, 2, 2, 2, 2, 0, 2, 2, 2, 2, 4, 2, 2,
            0, 2, 1, 1, 0, 2, 1, 2, 0, 4, 4, 1, 4, 1, 4, 2
         };
      private static final byte[] argsSolarAnomaly =
         {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 1, -1, -1, -1, 1, 0, 1,
            0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 1, 1,
            0, -1, -2, 0, 1, 1, 1, 1, 1, 0, -1, 1, 0, -1, 0, 0, 0, -1, -2
         };
      private static final byte[] argsLunarAnomaly =
         {
            0, 1, 1, 0, -1, -1, 0, 2, 1, 2, 0, -2, 1, 0, -1, 0, -1, -1, -1,
            0, 0, -1, 0, 1, 1, 0, 0, 3, 0, -1, 1, -2, 0, 2, 1, -2, 3, 2, -3,
            -1, 0, 0, 1, 0, 1, 1, 0, 0, -2, -1, 1, -2, 2, -2, -1, 1, 1, -2, 0, 0
         };

      private static final byte[] argsMoonNode =
         {
            1, 1, -1, -1, 1, -1, 1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, -1,
            -1, 1, 3, 1, 1, 1, -1, -1, -1, 1, -1, 1, -3, 1, -3, -1, -1, 1,
            -1, 1, -1, 1, 1, 1, 1, -1, 3, -1, -1, 1, -1, -1, 1, -1, 1, -1,
            -1, -1, -1, -1, -1, 1
         };
      private static final int[] sineCoefficients =
         {
            5128122, 280602, 277693, 173237, 55413, 46271, 32573,
            17198, 9266, 8822, 8216, 4324, 4200, 62177, 2463, 2211,
            2065, 63666, 1828, 63742, 63787, 63971, 64045, 64061,
            64126, 64192, 64201, 1107, 1021, 833, 777, 671, 607,
            596, 491, 65085, 439, 422, 421, 65170, 65185, 331, 315,
            302, 65253, 65307, 223, 223, 65316, 65316, 65351, 181,
            65359, 176, 166, 65372, 132, -119, 115, 107
         };
   }

   public static double lunarAltitude(double tee, Location locale)
   {
      double phi = locale.latitude;
      double psi = locale.longitude;
      double varepsilon = obliquity(tee);
      double lambda = lunarLongitude(tee);
      double beta = lunarLatitude(tee);
      double alpha = arcTanDegrees(
         (sinDegrees(lambda) * cosDegrees(varepsilon) - tanDegrees(beta) * sinDegrees(varepsilon))
            / cosDegrees(lambda),
         (int) quotient(lambda, deg(90.0D)) + 1);

      double delta = arcSinDegrees(sinDegrees(beta) * cosDegrees(varepsilon)
         + cosDegrees(beta) * sinDegrees(varepsilon) * sinDegrees(lambda));
      double theta0 = siderealFromMoment(tee);
      double capH = mod(theta0 + psi - alpha, 360.0D);
      double altitude = arcSinDegrees(sinDegrees(phi) * sinDegrees(delta) + cosDegrees(phi) * cosDegrees(delta) * cosDegrees(capH));
      return mod(altitude + deg(180.0D), 360.0D) - deg(180.0D);
   }

   public static double estimatePriorSolarLongitude(double tee, double phi)
   {
      double rate = 365.242189D / deg(360.0D);
      double tau = tee - rate * mod(solarLongitude(tee) - phi, 360.0D);
      double capDelta = mod(solarLongitude(tau) - phi + deg(180.0D), 360.0D) - deg(180.0D);
      return Math.min(tee, tau - rate * capDelta);
   }

   public static boolean visibleCrescent(long date, Location locale)
      throws BogusTimeException
   {
      double tee = universalFromStandard(dusk(date - 1L, locale, deg(4.5D)), locale);
      double phase = lunarPhase(tee);
      double altitude = lunarAltitude(tee, locale);
      double arcOfLight = arcCosDegrees(cosDegrees(lunarLatitude(tee)) * cosDegrees(phase));

      return (NEW < phase) && (phase < FIRST_QUARTER) && (deg(10.6D) <= arcOfLight) && (arcOfLight <= deg(90.0D)) && (altitude > deg(4.1D));
   }

   public static long phasisOnOrBefore(long date, Location locale)
      throws BogusTimeException
   {
      long mean = (long) (date - Math.floor(lunarPhase(date) / deg(360.0D) * 29.530588853D));
      long tau = (date - mean <= 3L) && (!visibleCrescent(date, locale)) ? mean - 30L : mean - 2L;
      long d;

      for (d = tau; !visibleCrescent(d, locale); d += 1L)
      {
      }
      return d;
   }

   public static double siderealFromMoment(double tee)
   {
      double c = (tee - J2000) / 36525.0D;
      return mod(poly(c, sfm.siderealCoeff), 360.0D);
   }

   private static class sfm
   {

      private static final double[] siderealCoeff = ProtoDate.deg(new double[]
         {
            280.46061837D, 1.3185000770053742E7D, 3.87933E-4D, 2.5833118057349522E-8D
         });
   }

   public static String nameFromNumber(long number, String[] nameList)
   {
      return nameList[((int) adjustedMod(number, nameList.length) - 1)];
   }

   public static String nameFromDayOfWeek(long dayOfWeek, String[] nameList)
   {
      return nameFromNumber(dayOfWeek + 1L, nameList);
   }

   public static String nameFromMonth(long month, String[] nameList)
   {
      return nameFromNumber(month, nameList);
   }

   public String toString()
   {
      return getClass().getName() + "[" + toStringFields() + "]";
   }

   public String format()
   {
      return toString();
   }

   protected abstract String toStringFields();

   public abstract boolean equals(Object paramObject);
}
