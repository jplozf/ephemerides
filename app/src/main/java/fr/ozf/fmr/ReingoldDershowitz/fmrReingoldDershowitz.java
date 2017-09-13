package fr.ozf.fmr.ReingoldDershowitz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class fmrReingoldDershowitz
{

    public static Gregorian gd;
    public List<String[]> out;

    public static void main(String[] args)
    {
        fmrReingoldDershowitz fmr = new fmrReingoldDershowitz();
    }

    public fmrReingoldDershowitz()
    {
        java.util.Calendar today = new java.util.GregorianCalendar();
        gd = new Gregorian(today.get(Calendar.YEAR), 1 + today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
        out = convertDate(gd.toFixed());
        for (String[] s : out)
        {
            System.out.println(s[0]);
            System.out.println(s[1]);
        }
    }

    public fmrReingoldDershowitz(long jd)
    {
        out = convertDate(jd);
        for (String[] s : out)
        {
            System.out.println(s[0]);
            System.out.println(s[1]);
        }
    }

    public fmrReingoldDershowitz(long year, int month, int day)
    {
        gd = new Gregorian(year, month, day);
        out = convertDate(gd.toFixed());
        for (String[] s : out)
        {
            System.out.println(s[0]);
            System.out.println(s[1]);
        }
    }

    public List<String[]> convertDate(long d)
    {
        List<String[]> dates = new ArrayList<>();
        String[] s;

        s = new String[]
        {
            "Fixe", d + " R.D."
        };
        dates.add(s);

        long jd = (long) Math.ceil(ProtoDate.jdFromFixed(d));
        s = new String[]
        {
            "Jour Julien (à midi)", jd + " j.d."
        };
        dates.add(s);

        long mjd = (int) ProtoDate.mjdFromFixed(d);
        s = new String[]
        {
            "Jour Julien (modifié)", Long.toString(mjd)
        };
        dates.add(s);

        gd = new Gregorian(d);
        s = new String[]
        {
            "Grégorien", gd.format()
        };
        dates.add(s);

        s = new String[]
        {
            "Jour de l'année", "Jour " + gd.dayNumber() + " en " + gd.year
        };
        dates.add(s);

        Julian juld = new Julian(d);
        s = new String[]
        {
            "Julien", juld.format()
        };
        dates.add(s);

        Armenian ad = new Armenian(d);
        s = new String[]
        {
            "Arménien", ad.format()
        };
        dates.add(s);

        Egyptian yd = new Egyptian(d);
        s = new String[]
        {
            "Egyptien", yd.format()
        };
        dates.add(s);

        Coptic cd = new Coptic(d);
        s = new String[]
        {
            "Copte", cd.format()
        };
        dates.add(s);

        Ethiopic ed = new Ethiopic(d);
        s = new String[]
        {
            "Ethiopien", ed.format()
        };
        dates.add(s);

        ISO isd = new ISO(d);
        s = new String[]
        {
            "ISO", isd.format()
        };
        dates.add(s);

        Bahai bd = new Bahai(d);
        s = new String[]
        {
            "Bahaï occidental", bd.format()
        };
        dates.add(s);

        FutureBahai fbd = new FutureBahai(d);
        s = new String[]
        {
            "Bahaï futur", fbd.format()
        };
        dates.add(s);

        Islamic id = new Islamic(d);
        s = new String[]
        {
            "Islamique (arithmétique)", id.format()
        };
        dates.add(s);

        ObservationalIslamic aid = new ObservationalIslamic(d);
        s = new String[]
           {
              "Islamique (observé)", aid.format()
           };
        dates.add(s);

        Hebrew hd = new Hebrew(d);
        s = new String[]
        {
            "Hébraïque", hd.format()
        };
        dates.add(s);

        Persian pd = new Persian(d);
        s = new String[]
        {
            "Perse", pd.format()
        };
        dates.add(s);

        ArithmeticPersian apd = new ArithmeticPersian(d);
        s = new String[]
        {
            "Perse (arithmétique)", apd.format()
        };
        dates.add(s);

        OldHinduSolar ohsd = new OldHinduSolar(d);
        s = new String[]
        {
            "Hindou solaire ancien", ohsd.format()
        };
        dates.add(s);

        OldHinduLunar ohld = new OldHinduLunar(d);
        s = new String[]
        {
            "Hindou lunaire ancien", ohld.format()
        };
        dates.add(s);

        Chinese chd = new Chinese(d);
        s = new String[]
           {
              "Chinois", chd.format()
           };
        dates.add(s);

        HinduSolar hsd = new HinduSolar(d);
        s = new String[]
        {
            "Hindou solaire", hsd.format()
        };
        dates.add(s);

        HinduLunar hld = new HinduLunar(d);
        s = new String[]
        {
            "Hindou lunaire", hld.format()
        };
        dates.add(s);

        MayanLongCount mlc = new MayanLongCount(d);
        s = new String[]
        {
            "Maya long", mlc.format()
        };
        dates.add(s);

        MayanHaab mhd = new MayanHaab(d);
        MayanTzolkin mtd = new MayanTzolkin(d);
        s = new String[]
        {
            "Maya circulaire", mhd.format() + " " + mtd.format()
        };
        dates.add(s);

        Balinese paw = new Balinese(d);
        s = new String[]
        {
            "Balinésien", paw.format()
        };
        dates.add(s);

        Roman rom = new Roman(d);
        s = new String[]
        {
            "Romain", rom.format()
        };
        dates.add(s);

        ModifiedFrench mfd = new ModifiedFrench(d);
        s = new String[]
        {
            "Révolutionnaire modifié", mfd.format()
        };
        dates.add(s);

        /*
        French fd = new French(d);
        s = new String[]
           {
              "Révolutionnaire", fd.format()
           };
        dates.add(s);
        */
        return dates;
    }

}
