package fr.ozf.fmr.Schlyter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.ozf.fmr.fmrDate;
import fr.ozf.fmr.fmrLocation;

public class fmrMoon extends fmrObject
{
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

   private final static double K_N0 = 125.1228;
   private final static double K_N1 = -0.0529538083;
   private final static double K_i0 = 5.1454;
   private final static double K_i1 = 0.0;
   private final static double K_w0 = 318.0634;
   private final static double K_w1 = 0.1643573223;
   private final static double K_a0 = 60.2666;
   private final static double K_a1 = 0.0;
   private final static double K_e0 = 0.054900;
   private final static double K_e1 = 0.0;
   private final static double K_M0 = 115.3654;
   private final static double K_M1 = 13.0649929509;
   
   private final static Logger logger = Logger.getLogger("");

//******************************************************************************
// fmrMoon()
//******************************************************************************
   public fmrMoon()
   {
      super(   K_N0, K_N1,
               K_i0, K_i1,
               K_w0, K_w1,
               K_a0, K_a1,
               K_e0, K_e1,
               K_M0, K_M1);
   }

   public fmrMoon(fmrDate d)
   {
      super(   K_N0, K_N1,
               K_i0, K_i1,
               K_w0, K_w1,
               K_a0, K_a1,
               K_e0, K_e1,
               K_M0, K_M1);
      this.setDate(d);
   }

   //******************************************************************************
// setDate()
//******************************************************************************
   @Override
   public final void setDate(fmrDate d)
   {
      super.setDate(d);
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
// setLocation()
//******************************************************************************
   @Override
   public final void setLocation(fmrLocation l)
   {
      super.setLocation(l);
      // TODO : Add location depending computations

   }

   //******************************************************************************
// printAll()
//******************************************************************************
   @Override
   public void printAll()
   {
      super.printAll();
      System.out.println("Mean Longitude  = " + this.MeanLongitude);
      System.out.println("XRect           = " + this.XRect);
      System.out.println("YRect           = " + this.YRect);
      System.out.println("Distance        = " + this.Distance);
      System.out.println("True Anomaly    = " + this.TrueAnomaly);
      System.out.println("Longitude       = " + this.Longitude);
      System.out.println("XEclRect        = " + this.XEclRect);
      System.out.println("YEclRect        = " + this.YEclRect);
      System.out.println("ZEclRect        = " + this.ZEclRect);
      System.out.println("XEquat          = " + this.XEquat);
      System.out.println("YEquat          = " + this.YEquat);
      System.out.println("ZEquat          = " + this.ZEquat);
      System.out.println("Right Ascension = " + fmrDate.dd2hms(this.RightAscension));
      System.out.println("Declination     = " + fmrDate.dd2dms(this.Declination));
   }

   //******************************************************************************
// getAllValues()
//******************************************************************************
   public List<String[]> getValues(int values)
   {
      List<String[]> out = new ArrayList<>();
      String[] s;

      if(values == VALUES_ALL || values == VALUES_GEO_ECLIPTIC)
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

      if(values == VALUES_ALL || values == VALUES_MISC)
      {

         s = new String[]
                 {
                         "Distance", String.format("%10.8f AU", this.Distance)
                 };
         out.add(s);
      }

      if(values == VALUES_ALL || values == VALUES_GEO_EQUATORIAL)
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
