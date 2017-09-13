package fr.ozf.fmr;

import fr.ozf.fmr.Schlyter.fmrSun;

public class fmrIDE
{
    fmrDate zeDate = new fmrDate();
    fmrSun zeSun = new fmrSun(zeDate);
    
//******************************************************************************
// main()
//******************************************************************************
    public static void main(String[] args) throws Exception
    {
        fmrIDE fmrIDE = new fmrIDE();
    }    
    
//******************************************************************************
// fmrIDE()
//******************************************************************************
    public fmrIDE()
    {
        zeSun.printAll();
        System.out.println(zeDate.D2000);
        System.out.println(fmrDate.dd2dms(zeDate.OblEcl));
    }       
}
