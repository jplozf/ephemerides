package fr.ozf.fmr.ReingoldDershowitz;

import java.io.Serializable;
import java.text.MessageFormat;


public class Location
   implements Cloneable, Serializable
{
   public String name;
   public double latitude;
   public double longitude;
   public double elevation;
   public double zone;

   public Location()
   {
   }

   public Location(String name, double latitude, double longitude, double elevation, double zone)
   {
      this.name = name;
      this.latitude = latitude;
      this.longitude = longitude;
      this.elevation = elevation;
      this.zone = zone;
   }


   public static final Location URBANA = new Location("Urbana, IL, USA", 40.1D, -88.2D, ProtoDate.mt(225.0D), -6.0D);

   public static final Location LOS_ANGELES = new Location("Los Angeles, CA, USA", ProtoDate.angle(34.0D, 4.0D, 0.0D), -ProtoDate.angle(118.0D, 15.0D, 0.0D), ProtoDate.mt(0.0D), -8.0D);


   public String toString()
   {
      return getClass().getName() + "[latitude=" + this.latitude + ",longitude=" + this.longitude + ",elevation=" + this.elevation + ",zone=" + this.zone + "]";
   }

   public String format()
   {
      return MessageFormat.format("{0}: lat {1} long {2} elev {3} zone {4}",
         new Object[]{
            new String(this.name),
            new Double(this.latitude),
            new Double(this.longitude),
            new Double(this.elevation),
            new Double(this.zone)});
   }


   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Location))
      {
         return false;
      }
      Location o = (Location) obj;


      return (o.latitude == this.latitude) && (o.longitude == this.longitude) && (o.elevation == this.elevation) && (o.zone == this.zone);
   }
}