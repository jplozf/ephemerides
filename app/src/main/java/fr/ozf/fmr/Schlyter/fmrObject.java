package fr.ozf.fmr.Schlyter;

import java.util.ArrayList;
import java.util.List;

import fr.ozf.fmr.fmrDate;
import fr.ozf.fmr.fmrLocation;

//******************************************************************************
// http://stjarnhimlen.se/comp/ppcomp.html
//******************************************************************************
public class fmrObject
{
    public final static int VALUES_ALL = 0;
    public final static int VALUES_GEO_ECLIPTIC = 1;
    public final static int VALUES_TOPO_HORIZONTAL = 2;
    public final static int VALUES_GEO_EQUATORIAL = 3;
    public final static int VALUES_MISC = 4;
    //
    public double N;        // longitude of the ascending node
    public double i;        // inclination to the ecliptic (plane of the Earth's orbit)
    public double w;        // argument of perihelion
    public double a;        // semi-major axis, or mean distance from Sun
    public double e;        // eccentricity (0=circle, 0-1=ellipse, 1=parabola)
    public double M;        // mean anomaly (0 at perihelion; increases uniformly with time)
    public double E;        // eccentric anomaly
    public fmrDate d;       // date of event
    public fmrLocation loc; // location of event
    public double MeanLongitude;
    public double XRect;
    public double YRect;
    public double Distance;
    public double TrueAnomaly;
    public double Longitude;
    public double XEclRect;
    public double YEclRect;
    public double ZEclRect;
    public double XEquat;
    public double YEquat;
    public double ZEquat;
    public double RightAscension;
    public double Declination;
    private double N0, N1;  // coefficients for longitude of the ascending node
    private double i0, i1;  // coefficients for inclination to the ecliptic
    private double w0, w1;  // coefficients for argument of perihelion
    private double a0, a1;  // coefficients for semi-major axis, or mean distance from Sun
    private double e0, e1;  // coefficients for eccentricity
    private double M0, M1;  // coefficients for mean anomaly

    //******************************************************************************
// fmrObject()
//******************************************************************************
    public fmrObject(double N0, double N1, double i0, double i1, double w0, double w1, double a0, double a1, double e0, double e1, double M0, double M1)
    {
        this.N0 = N0;
        this.i0 = i0;
        this.w0 = w0;
        this.a0 = a0;
        this.e0 = e0;
        this.M0 = M0;
        this.N1 = N1;
        this.i1 = i1;
        this.w1 = w1;
        this.a1 = a1;
        this.e1 = e1;
        this.M1 = M1;
    }

    //******************************************************************************
// setDate()
//******************************************************************************
    public void setDate(fmrDate d)
    {
        this.d = d;
        this.computeTemporalEvents();
    }

    //******************************************************************************
// setLocation()
//******************************************************************************
    public void setLocation(fmrLocation l)
    {
        this.loc = l;
        this.computeLocalEvents();
    }

    //******************************************************************************
// setLongitudeAscendingNode()
//******************************************************************************
    void setLongitudeAscendingNode(double N0, double N1)
    {
        this.N0 = N0;
        this.N1 = N1;
    }

    //******************************************************************************
// setInclinationEcliptic()
//******************************************************************************
    void setInclinationEcliptic(double i0, double i1)
    {
        this.i0 = i0;
        this.i1 = i1;
    }

    //******************************************************************************
// setArgumentPerihelion()
//******************************************************************************
    void setArgumentPerihelion(double w0, double w1)
    {
        this.w0 = w0;
        this.w1 = w1;
    }

    //******************************************************************************
// setMeanDistanceFromSun()
//******************************************************************************
    void setMeanDistanceFromSun(double a0, double a1)
    {
        this.a0 = a0;
        this.a1 = a1;
    }

    //******************************************************************************
// setEccentricity()
//******************************************************************************
    void setEccentricity(double e0, double e1)
    {
        this.e0 = e0;
        this.e1 = e1;
    }

    //******************************************************************************
// setMeanAnomaly()
//******************************************************************************
    void setMeanAnomaly(double M0, double M1)
    {
        this.M0 = M0;
        this.M1 = M1;
    }

    //******************************************************************************
// computeTemporalEvents()
//******************************************************************************
    private void computeTemporalEvents()
    {
        this.N = computeLongitudeAscendingNode(d.getD2000());
        this.i = getInclinationEcliptic(d.getD2000());
        this.w = getArgumentPerihelion(d.getD2000());
        this.a = getMeanDistanceFromSun(d.getD2000());
        this.e = getEccentricity(d.getD2000());
        this.M = getMeanAnomaly(d.getD2000());
        this.E = getEccentricAnomaly();
        //
        this.MeanLongitude = this.getMeanLongitude();
        this.XRect = this.getXRect();
        this.YRect = this.getYRect();
        this.Distance = this.getDistance(this.XRect, this.YRect);
        this.TrueAnomaly = this.getTrueAnomaly(this.XRect, this.YRect);
        this.Longitude = fmrDate.reduceAngle(this.TrueAnomaly + this.w);
        this.XEclRect = this.Distance * Math.cos(Math.toRadians(this.Longitude));
        this.YEclRect = this.Distance * Math.sin(Math.toRadians(this.Longitude));
        this.ZEclRect = 0;
        this.XEquat = this.XEclRect;
        this.YEquat = this.YEclRect * Math.cos(Math.toRadians(d.OblEcl));
        this.ZEquat = this.YEclRect * Math.sin(Math.toRadians(d.OblEcl));
        this.RightAscension = Math.toDegrees(Math.atan2(this.YEquat, this.XEquat));
        this.Declination = Math.toDegrees(Math.atan2(this.ZEquat, Math.sqrt(this.XEquat * this.XEquat + this.YEquat * this.YEquat)));
    }

    //******************************************************************************
// computeLocalEvents()
//******************************************************************************
    private void computeLocalEvents()
    {
        // overridden
    }

    //******************************************************************************
// printAll()
//******************************************************************************
    public void printAll()
    {
        System.out.println("d : Date of event                                                 = " + this.d);
        System.out.println("N : Longitude of the ascending node                               = " + this.N);
        System.out.println("i : Inclination to the ecliptic (plane of the Earth's orbit)      = " + this.i);
        System.out.println("w : Argument of perihelion                                        = " + this.w);
        System.out.println("a : Semi-major axis, or mean distance from Sun                    = " + this.a);
        System.out.println("e : Eccentricity (0=circle, 0-1=ellipse, 1=parabola)              = " + this.e);
        System.out.println("M : Mean anomaly (0 at perihelion; increases uniformly with time) = " + this.M);
        System.out.println("E : Eccentric anomaly                                             = " + this.E);
    }


    //******************************************************************************
// getAllValues()
//******************************************************************************
    public List<String[]> getValues(int values)
    {
        List<String[]> out = new ArrayList<>();
        String[] s;

        if (values == VALUES_ALL || values == VALUES_GEO_ECLIPTIC)
        {
            s = new String[]
                    {
                            "Longitude", fmrDate.dd2dms(this.Longitude)
                    };
            out.add(s);

            s = new String[]
                    {
                            "Latitude", fmrDate.dd2dms(0.0)
                    };
            out.add(s);
        }

        if (values == VALUES_ALL || values == VALUES_MISC)
        {

            s = new String[]
                    {
                            "Distance", String.format("%10.8f AU", this.Distance)
                    };
            out.add(s);
        }

        if (values == VALUES_ALL || values == VALUES_GEO_EQUATORIAL)
        {
            s = new String[]
                    {
                            "Right Ascension", fmrDate.dd2hms(this.RightAscension)
                    };
            out.add(s);

            s = new String[]
                    {
                            "Declination", fmrDate.dd2dms(this.Declination)
                    };
            out.add(s);
        }

        return out;
    }

//******************************************************************************
// computeLongitudeAscendingNode()
//******************************************************************************
    double computeLongitudeAscendingNode(double d)
    {
        return this.N1 * d + this.N0;
    }

    //******************************************************************************
// getInclinationEcliptic()
//******************************************************************************
    double getInclinationEcliptic(double d)
    {
        return this.i1 * d + this.i0;
    }

    //******************************************************************************
// getArgumentPerihelion()
//******************************************************************************
    double getArgumentPerihelion(double d)
    {
        return this.w1 * d + this.w0;
    }

    //******************************************************************************
// getMeanDistanceFromSun()
//******************************************************************************
    double getMeanDistanceFromSun(double d)
    {
        return this.a1 * d + this.a0;
    }

    //******************************************************************************
// getEccentricity()
//******************************************************************************
    double getEccentricity(double d)
    {
        return this.e1 * d + this.e0;
    }

    //******************************************************************************
// getMeanAnomaly()
//******************************************************************************
    double getMeanAnomaly(double d)
    {
        return fmrDate.reduceAngle(this.M1 * d + this.M0);
    }

//******************************************************************************
// getEccentricAnomaly()
//******************************************************************************
    double getEccentricAnomaly()
    {
//       E = M + e*(180/pi) * sin(M) * ( 1.0 + e * cos(M) ) with E and M in degrees
        return (this.M + this.e * (180.0 / Math.PI) * Math.sin(Math.toRadians(M)) * (1.0 + this.e * Math.cos(Math.toRadians(this.M))));
    }


    //******************************************************************************
// infos()
//******************************************************************************
    public String infos()
    {
        String txt = "";
        txt += "N : Longitude of the ascending node\n";
        txt += "i : Inclination to the ecliptic (plane of the Earth's orbit)\n";
        txt += "w : Argument of perihelion\n";
        txt += "a : Semi-major axis, or mean distance from Sun\n";
        txt += "e : Eccentricity (0=circle, 0-1=ellipse, 1=parabola)\n";
        txt += "M : Mean anomaly (0 at perihelion; increases uniformly with time)\n";
        txt += "E : Eccentric anomaly\n";

        return txt;
    }

    //******************************************************************************
// getMeanLongitude()
//******************************************************************************
    public double getMeanLongitude()
    {
        return fmrDate.reduceAngle(this.w + this.M);
    }

    //******************************************************************************
// getXRect()
//******************************************************************************
    public double getXRect()
    {
        return (Math.cos(Math.toRadians(this.E)) - this.e);
    }

    //******************************************************************************
// getYRect()
//******************************************************************************
    public double getYRect()
    {
        return (Math.sin(Math.toRadians(this.E)) * Math.sqrt(1.0 - this.e * this.e));
    }

    //******************************************************************************
// getDistance()
//******************************************************************************
    public double getDistance(double x, double y)
    {
        return (Math.sqrt(x * x + y * y));
    }

    //******************************************************************************
// getTrueAnomaly()
//******************************************************************************
    public double getTrueAnomaly(double x, double y)
    {
        return (Math.toDegrees(Math.atan2(y, x)));
    }
}
