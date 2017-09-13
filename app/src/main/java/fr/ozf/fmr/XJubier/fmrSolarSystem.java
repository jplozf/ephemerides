package fr.ozf.fmr.XJubier;

import fr.ozf.fmr.fmrDate;
import fr.ozf.fmr.fmrLocation;

public class fmrSolarSystem
{
    // Observer location and date
    public fmrLocation Location;
    public fmrDate Date;

    private int day, month, year, hour, minute, second;


    public fmrSolarSystem(fmrLocation Location, fmrDate Date)
    {
        this.Location = Location;
        this.Date = Date;


    }

    public double julianDay(int day, int month, int year, int hour, int minute, int second)
    {
        int y, m;
        double a, b;
        boolean gregorian = true;
        if (year < 1582)
        {
            gregorian = false;
        }
        else
        {
            if (year == 1582) {
                if ((month < 10) | ((month == 10) & (day < 15))) {
                    gregorian = false;
                }
            }
        }
        if(month > 2)
        {
            y = year;
            m = month;
        }
        else
        {
            y = year - 1;
            m = month + 12;
        }
        a = Math.floor(y / 100);
        if(gregorian)
        {
            b = 2 - a + Math.floor(a / 4);
        }
        else
        {
            b = 0.0;
        }
        double jd = Math.floor(365.25 * (y + 4716.0)) + Math.floor(30.6001 * (m + 1.0)) + day + b - 1524.5;
        jd += (hour + (minute / 60.0) + (second / 3600.0)) / 24.0;

        return jd;
    }

}
