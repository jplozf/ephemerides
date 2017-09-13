package fr.ozf.fmr.ReingoldDershowitz;

import java.io.Serializable;
import java.text.MessageFormat;


public class Time
   implements Cloneable, Serializable
{
   public int hour;
   public int minute;
   public double second;

   public Time()
   {
   }

   public Time(double moment)
   {
      fromMoment(moment);
   }

   public Time(int hour, int minute, double second)
   {
      this.hour = hour;
      this.minute = minute;
      this.second = second;
   }


   public static double toMoment(int hour, int minute, double second)
   {
      return hour / 24.0D + minute / 1440.0D + second / 86400.0D;
   }

   public double toMoment()
   {
      return toMoment(this.hour, this.minute, this.second);
   }


   public void fromMoment(double tee)
   {
      this.hour = ((int) Math.floor(ProtoDate.mod(tee * 24.0D, 24.0D)));
      this.minute = ((int) Math.floor(ProtoDate.mod(tee * 24.0D * 60.0D, 60.0D)));
      this.second = ProtoDate.mod(tee * 24.0D * 60.0D * 60.0D, 60.0D);
   }


   public String toString()
   {
      return getClass().getName() + "[hour=" + this.hour + ",minute=" + this.minute + ",second=" + this.second + "]";
   }

   public String format()
   {
      int modHour = ProtoDate.mod(this.hour, 12);
      int aHour = modHour == 0 ? 12 : modHour;
      return MessageFormat.format("{0,number,00}:{1,number,00}:{2,number,00} {3}",
         new Object[]{
            new Integer(aHour),
            new Integer(this.minute),
            new Integer((int) this.second),
            ProtoDate.mod(this.hour, 24) < 12 ? "A.M." : "P.M."});
   }


   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Time))
      {
         return false;
      }
      Time o = (Time) obj;


      return (o.hour == this.hour) && (o.minute == this.minute) && (o.second == this.second);
   }
}