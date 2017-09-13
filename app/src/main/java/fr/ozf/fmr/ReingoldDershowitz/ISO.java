package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class ISO
   extends Date
{

    public long year;
    public int week;
    public int day;

    public ISO()
    {
    }

    public ISO(long date)
    {
        super(date);
    }

    public ISO(Date date)
    {
        super(date);
    }

    public ISO(long year, int week, int day)
    {
        this.year = year;
        this.week = week;
        this.day = day;
    }

    public static long toFixed(long year, int week, int day)
    {
        return nthKDay(week, 0, Gregorian.toFixed(year - 1L, 12, 28)) + day;
    }

    public long toFixed()
    {
        return toFixed(this.year, this.week, this.day);
    }

    public void fromFixed(long date)
    {
        long approx = Gregorian.yearFromFixed(date - 3L);
        this.year = (date >= toFixed(approx + 1L, 1, 1) ? approx + 1L : approx);
        this.week = ((int) quotient(date - toFixed(this.year, 1, 1), 7.0D) + 1);
        this.day = ((int) adjustedMod(date, 7L));
    }

    public void fromArray(int[] a)
    {
        this.year = a[0];
        this.week = a[1];
        this.day = a[2];
    }

    protected String toStringFields()
    {
        return "year=" + this.year + ",week=" + this.week + ",day=" + this.day;
    }

    public String format()
    {
        return MessageFormat.format("{0}, Semaine {1}, {2,number,#}",
           new Object[]
           {
               nameFromDayOfWeek(toFixed(), Gregorian.dayOfWeekNames),
               new Integer(this.week),
               new Long(this.year)
           });
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof ISO))
        {
            return false;
        }
        ISO o = (ISO) obj;

        return (o.year == this.year) && (o.week == this.week) && (o.day == this.day);
    }
}
