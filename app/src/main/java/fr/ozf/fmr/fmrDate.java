package fr.ozf.fmr;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//******************************************************************************
// class fmrDate
//******************************************************************************
public final class fmrDate
{
    public int Year;
    public int Month;
    public int Day;
    public int Hour;
    public int Minute;
    public int Second;
    public double TZV;
    public TimeZone TZ;
    public double D2000;
    public double OblEcl;
    public Calendar cal;
    //
    public double JD;       // Julian Day
    public double GMST0;    // Mean Sideral Time at Greenwich

    //******************************************************************************
// fmrDate()
//******************************************************************************
    public fmrDate()
    {
        this.cal = Calendar.getInstance();

        this.Day = cal.get(Calendar.DATE);
        this.Month = cal.get(Calendar.MONTH) + 1;
        this.Year = cal.get(Calendar.YEAR);
        this.Hour = cal.get(Calendar.HOUR_OF_DAY);
        this.Minute = cal.get(Calendar.MINUTE);
        this.Second = cal.get(Calendar.SECOND);
        this.TZ = cal.getTimeZone();
        this.TZV = (double) (this.TZ.getRawOffset()) / (60.0 * 60.0 * 1000.0);
        this.D2000 = this.getD2000();
        this.OblEcl = this.getOblEcl();
        this.JD = this.getJD();
        this.GMST0 = this.getMeanSideralTimeGreenwich();
    }

    //******************************************************************************
// fmrDate()
//******************************************************************************
    public fmrDate(int Year, int Month, int Day, int Hour, int Minute, int Second, TimeZone TZ)
    {
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.Hour = Hour;
        this.Minute = Minute;
        this.Second = Second;
        this.TZ = TZ;
        this.D2000 = this.getD2000();
        this.OblEcl = this.getOblEcl();
        this.JD = this.getJD();
        this.GMST0 = this.getMeanSideralTimeGreenwich();
    }

    //******************************************************************************
// fmrDate()
//******************************************************************************
    public fmrDate(int Year, int Month, int Day)
    {
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.Hour = 12;
        this.Minute = 0;
        this.Second = 0;
        this.TZ = cal.getTimeZone();
        this.TZV = (double) (this.TZ.getRawOffset()) / (60.0 * 60.0 * 1000.0);
        this.D2000 = this.getD2000();
        this.OblEcl = this.getOblEcl();
        this.JD = this.getJD();
        this.GMST0 = this.getMeanSideralTimeGreenwich();
    }

    //******************************************************************************
// reduceAngle()
//******************************************************************************
    public static double reduceAngle(double a)
    {
        return (a - Math.floor(a / 360.0) * 360.0);
    }

    //******************************************************************************
// dd2dms()
//******************************************************************************
    public static String dd2dms(double dd)
    {
        double d2 = Math.abs(dd);
        double sign = dd / d2;

        double d = Math.floor(d2);
        double t = (d2 - d) * 60.0;
        double m = Math.floor(t);
        double s = (t - m) * 60.0;

        d = sign * d;

        return String.format("%+04d°%02d'%02d\"", (int) d, (int) m, (int) s);
    }

    //******************************************************************************
// dd2hms()
//******************************************************************************
    public static String dd2hms(double dd)
    {
        double d2 = Math.abs(dd);
        double sign = dd / d2;

        double d = d2 / 15.0;
        double h = Math.floor(d);
        double t = (d - h) * 60.0;
        double m = Math.floor(t);
        double s = (t - m) * 60.0;

        return String.format("%02dh %02dm %02ds", (int) h, (int) m, (int) s);
    }

    //******************************************************************************
// hd2hms()
//******************************************************************************
    public static String hd2hms(double dd)
    {
        double d = Math.abs(dd);

        double h = Math.floor(d);
        double t = (d - h) * 60.0;
        double m = Math.floor(t);
        double s = (t - m) * 60.0;

        return String.format("%02dh %02dm %02ds", (int) h, (int) m, (int) s);
    }

    //******************************************************************************
// getYear()
//******************************************************************************
    public int getYear()
    {
        return this.Year;
    }

    //******************************************************************************
// getMonth()
//******************************************************************************
    public int getMonth()
    {
        return this.Month;
    }

    //******************************************************************************
// getDay()
//******************************************************************************
    public int getDay()
    {
        return this.Day;
    }

    //******************************************************************************
// getHour()
//******************************************************************************
    public int getHour()
    {
        return this.Hour;
    }

    //******************************************************************************
// getMinute()
//******************************************************************************
    public int getMinute()
    {
        return this.Minute;
    }

    //******************************************************************************
// getSecond()
//******************************************************************************
    public int getSecond()
    {
        return this.Second;
    }

    //******************************************************************************
// getTZ()
//******************************************************************************
    public TimeZone getTZ()
    {
        return this.TZ;
    }

    //******************************************************************************
// getDayOfWeek()
//******************************************************************************
    public String getDayOfWeek()
    {
        String[] strDays = new String[]{
                "Dimanche",
                "Lundi",
                "Mardi",
                "Mercredi",
                "Jeudi",
                "Vendredi",
                "Samedi"
        };

        return (strDays[this.cal.get(Calendar.DAY_OF_WEEK) - 1]);
    }

    //******************************************************************************
// getMonthName()
//******************************************************************************
    public String getMonthName()
    {
        String[] strDays = new String[]{
                "Janvier",
                "Février",
                "Mars",
                "Avril",
                "Mai",
                "Juin",
                "Juillet",
                "Août",
                "Septembre",
                "Octobre",
                "Novembre",
                "Décembre"
        };

        return (strDays[this.cal.get(Calendar.MONTH)]);

    }

    //******************************************************************************
// setTime()
//******************************************************************************
    public void setTime(int Year, int Month, int Day, int Hour, int Minute, int Second, TimeZone TZ)
    {
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.Hour = Hour;
        this.Minute = Minute;
        this.Second = Second;
        this.TZ = TZ;
        this.D2000 = this.getD2000();
        this.OblEcl = this.getOblEcl();
        this.JD = this.getJD();
        this.GMST0 = this.getMeanSideralTimeGreenwich();

    }

    //******************************************************************************
// setTime()
//******************************************************************************
    public void setTime(int Year, int Month, int Day, int Hour, int Minute, int Second)
    {
        this.Day = Day;
        this.Month = Month;
        this.Year = Year;
        this.Hour = Hour;
        this.Minute = Minute;
        this.Second = Second;
        this.D2000 = this.getD2000();
        this.OblEcl = this.getOblEcl();
        this.JD = this.getJD();
        this.GMST0 = this.getMeanSideralTimeGreenwich();
    }

    //******************************************************************************
// getTZV()
//******************************************************************************
    public double getTZV()
    {
        return this.TZV;
    }

    //******************************************************************************
// getD2000()
//******************************************************************************
    public double getD2000()
    {
        int iD = 0;
        double dH;
        double D2000 = 0.0;

        iD = 367 * this.Year - (7 * (this.Year + ((this.Month + 9) / 12))) / 4 + (275 * this.Month) / 9 + this.Day - 730530;
        dH = this.Hour + (this.Minute / 60.0) + (this.Second / 3600.0);
        dH = dH - this.TZV;
        dH = dH / 24.0;
        D2000 = (double) iD + dH;

        return D2000;
    }

    //******************************************************************************
// getOblEcl()
//******************************************************************************
    public double getOblEcl()
    {
        return (23.4393 - this.getD2000() * 3.563E-7);
    }

    //******************************************************************************
// toString()
//******************************************************************************
    @Override
    public String toString()
    {
        return String.format("%04d/%02d/%02d %02d:%02d:%02d", this.Year, this.Month, this.Day, this.Hour, this.Minute, this.Second);
    }

    //******************************************************************************
// daysUntil3112()
//******************************************************************************
    public int daysUntil3112()
    {
        Calendar ech = Calendar.getInstance();
        ech.set(Calendar.MONTH, Calendar.DECEMBER);
        ech.set(Calendar.DAY_OF_MONTH, 31);

        long nowMilli = this.cal.getTimeInMillis();
        long echMilli = ech.getTimeInMillis();
        long delta = echMilli - nowMilli;

        return (int) (delta / 24 / 60 / 60 / 1000);
    }

    //******************************************************************************
// daysFrom0101()
//******************************************************************************
    public int daysFrom0101()
    {
        return (this.cal.get(Calendar.DAY_OF_YEAR));
    }

    //******************************************************************************
// getWeekFromDate()
//******************************************************************************
    public int getWeekFromDate()
    {
        //La première semaine de l'année est celle contenant au moins 4 jours
        this.cal.setMinimalDaysInFirstWeek(4);
        int week = this.cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    //******************************************************************************
// addDays()
//******************************************************************************
    public fmrDate addDays(int days)
    {
        Calendar cNew = Calendar.getInstance();
        cNew.set(this.Year, this.Month - 1, this.Day, 0, 0);
        cNew.add(Calendar.DATE, days);
        return (new fmrDate(cNew.get(Calendar.YEAR), cNew.get(Calendar.MONTH) + 1, cNew.get(Calendar.DATE)));
    }

    //******************************************************************************
// getNewEarthTime()
//******************************************************************************
    public String getNewEarthTime()
    {
        Calendar utc = this.getUTC();
        double dd = Math.abs((double) (utc.get(Calendar.HOUR_OF_DAY)) + ((double) utc.get(Calendar.MINUTE) / 60.0) + ((double) utc.get(Calendar.SECOND) / 3600.0));
        double net = dd * 15.0;
        double d = (int) net;
        double t1 = (net - d) * 60.0;
        double m = (int) t1;
        double s = (t1 - m) * 60.0;
        return (String.format("%03d°%02d'%02d\"", (int) d, (int) m, (int) s));
    }

    //******************************************************************************
// getSwatchInternetTime()
//******************************************************************************
    public String getSwatchInternetTime()
    {
        Calendar utc = this.getUTC();
        utc.add(Calendar.HOUR, 5); // Convert UTC to BMT (Biel Mean Time)
        double beat = Math.abs((double) (utc.get(Calendar.HOUR_OF_DAY)) + ((double) utc.get(Calendar.MINUTE) / 60.0) + ((double) utc.get(Calendar.SECOND) / 3600.0));
        beat = beat / 24.0 * 1000.0;
        return (String.format("@%03d", (int) beat));
    }

    //******************************************************************************
// getUTC()
//******************************************************************************
    public Calendar getUTC()
    {
        Calendar c = this.cal;

        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();
        if (z.inDaylightTime(new Date()))
        {
            offset = offset + z.getDSTSavings();
        }
        int offsetHrs = offset / 1000 / 60 / 60;
        int offsetMins = offset / 1000 / 60 % 60;

        System.out.println("offset: " + offsetHrs);
        System.out.println("offset: " + offsetMins);

        c.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
        c.add(Calendar.MINUTE, (-offsetMins));

        System.out.println("GMT Time: " + c.getTime());
        return c;
    }

    //******************************************************************************
// getUTC()
//******************************************************************************
    public double getUTCDouble()
    {
        Calendar c = this.cal;

        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();
        if (z.inDaylightTime(new Date()))
        {
            offset = offset + z.getDSTSavings();
        }
        int offsetHrs = offset / 1000 / 60 / 60;
        int offsetMins = offset / 1000 / 60 % 60;

        System.out.println("offset: " + offsetHrs);
        System.out.println("offset: " + offsetMins);

        c.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
        c.add(Calendar.MINUTE, (-offsetMins));

        System.out.println("GMT Time: " + c.getTime());
        double dec = 0.0;

        dec += c.get(Calendar.SECOND) / 60.0;
        dec += c.get(Calendar.MINUTE) / 60.0;
        dec += c.get(Calendar.HOUR_OF_DAY);

        return dec;
    }

    //******************************************************************************
// cal2dec()
//******************************************************************************
    public double cal2dec()
    {
        Calendar c = this.cal;
        double dec = 0.0;

        dec += c.get(Calendar.SECOND) / 60.0;
        dec += c.get(Calendar.MINUTE) / 60.0;
        dec += c.get(Calendar.HOUR_OF_DAY);

        return dec;
    }

    //******************************************************************************
// cal2dec()
//******************************************************************************
    public static double cal2dec(Calendar c)
    {
        double dec = 0.0;

        dec += c.get(Calendar.SECOND) / 60.0;
        dec += c.get(Calendar.MINUTE) / 60.0;
        dec += c.get(Calendar.HOUR_OF_DAY);

        return dec;
    }

    //******************************************************************************
// getUnixTime()
//******************************************************************************
    public long getUnixTime()
    {
        Calendar c = getUTC();
        return (c.getTimeInMillis() / 1000L);
    }

    //******************************************************************************
// Hour2String()
//******************************************************************************
    public static String Hour2String(Calendar cal)
    {
        return (String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)));
    }

    //******************************************************************************
// getJD()
//******************************************************************************
    double getJD()
    {
        double JD = 0.0;

        int Y = this.Year;
        int M = this.Month;
        if (M < 3)
        {
            Y = Y - 1;
            M = M + 12;
        }
        int A = Y / 100;
        int B = 2 - A + (A / 4);
        JD = Math.floor(365.25 * (Y + 4716.0)) + Math.floor(30.6001 * (M + 1.0)) + this.Day + B - 1524.5 + (this.Hour / 24.0) + (this.Minute / 1440.0) + (this.Second / 86400.0);

        return JD;
    }

    //******************************************************************************
// getMeanSideralTimeGreenwich()
//******************************************************************************
    public double getMeanSideralTimeGreenwich()
    {
        double GMST0 = 0.0;
        double JD0 = Math.floor(this.JD) - 0.5;
        double T = (JD0 - 2451545.0) / 36525.0;

        GMST0 = 100.46061837 + 36000.770053608 * T + 0.000387933 * (T * T) - ((T * T * T) / 38710000);
        GMST0 = reduceAngle(GMST0);
        return GMST0;
    }

    //******************************************************************************
// getDeltaT()
//******************************************************************************
    double getDeltaT(int year, int month)
    {
        // https://eclipse.gsfc.nasa.gov/SEhelp/deltatpoly2004.html
        double deltaT = 0.0;
        double y = year + (month - 0.5) / 12.0;
        if (year < -500)
        {
            double u = (year - 1820.0) / 100.0;
            deltaT = -20.0 + 32.0 * (u * u);
        } else
        {
            if (year >= -500 && year < 500)
            {
                double u = y / 100.0;
                deltaT = 10583.6 - 1014.41 * u + 33.78311 * (u * u) - 5.952053 * (u * u * u) - 0.1798452 * (u * u * u * u) + 0.022174192 * (u * u * u * u * u) + 0.0090316521 * (u * u * u * u * u * u);
            } else
            {
                if (year >= 500 && year < 1600)
                {
                    double u = (y - 1000.0) / 100.0;
                    deltaT = 1574.2 - 556.01 * u + 71.23472 * (u * u) + 0.319781 * (u * u * u) - 0.8503463 * (u * u * u * u) - 0.005050998 * (u * u * u * u * u) + 0.0083572073 * (u * u * u * u * u * u);
                } else
                {
                    if (year >= 1600 && year < 1700)
                    {
                        double t = (y - 1600.0);
                        deltaT = 120.0 - 0.9808 * t - 0.01532 * (t * t) + ((t * t * t) / 7129.0);
                    } else
                    {
                        if (year >= 1700 && year < 1800)
                        {

                        } else
                        {
                            if (year >= 1800 && year < 1860)
                            {

                            } else
                            {
                                if (year >= 1860 && year < 1900)
                                {

                                } else
                                {
                                    if (year >= 1900 && year < 1920)
                                    {

                                    } else
                                    {
                                        if (year >= 1920 && year < 1941)
                                        {

                                        } else
                                        {
                                            if (year >= 1941 && year < 1961)
                                            {

                                            } else
                                            {
                                                if (year >= 1961 && year < 1986)
                                                {

                                                } else
                                                {
                                                    if (year >= 1986 && year < 2005)
                                                    {

                                                    } else
                                                    {
                                                        if (year >= 2005 && year < 2050)
                                                        {

                                                        } else
                                                        {
                                                            if (year >= 2050 && year < 2150)
                                                            {

                                                            } else
                                                            {

                                                            }

                                                        }

                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        return deltaT;
    }
}
