package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;


public class MayanHaab
  extends ProtoDate
{
  public int month;
  public int day;
  public static final long EPOCH = MayanLongCount.EPOCH - ordinal(new MayanHaab(18, 8));
  


  public MayanHaab() {}
  


  public MayanHaab(long date)
  {
    super(date);
  }
  
  public MayanHaab(Date date) {
    super(date);
  }
  
  public MayanHaab(int month, int day) {
    this.month = month;
    this.day = day;
  }
  









  public void fromFixed(long date)
  {
    long count = mod(date - EPOCH, 365L);
    this.day = ((int)mod(count, 20L));
    this.month = (1 + (int)quotient(count, 20.0D));
  }
  
  public void fromArray(int[] a)
  {
    this.month = a[0];
    this.day = a[1];
  }
  










  public static int ordinal(MayanHaab hDate)
  {
    return (hDate.month - 1) * 20 + hDate.day;
  }
  











  public static long onOrBefore(MayanHaab haab, long date)
  {
    return date - mod(date - EPOCH - ordinal(haab), 365L);
  }
  




  protected String toStringFields()
  {
    return "month=" + this.month + ",day=" + this.day;
  }
  
  public static final String[] monthNames = {
    "Pop", 
    "Uo", 
    "Zip", 
    "Zotz", 
    "Tzec", 
    "Xul", 
    "Yaxkin", 
    "Mol", 
    "Chen", 
    "Yax", 
    "Zac", 
    "Ceh", 
    "Mac", 
    "Kankin", 
    "Muan", 
    "Pax", 
    "Kayab", 
    "Cumku", 
    "Uayeb" };
  
  public String format() {
    return MessageFormat.format("{0} {1}", 
      new Object[] {
      new Integer(this.day), 
      nameFromMonth(this.month, monthNames) });
  }
  

  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MayanHaab)) {
      return false;
    }
    MayanHaab o = (MayanHaab)obj;
    


    return (o.month == this.month) && (o.day == this.day);
  }
}