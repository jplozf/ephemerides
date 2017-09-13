package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;


public class MayanLongCount
  extends Date
{
  public long baktun;
  public int katun;
  public int tun;
  public int uinal;
  public int kin;
  
  public MayanLongCount() {}
  
  public MayanLongCount(long date)
  {
    super(date);
  }
  
  public MayanLongCount(Date date) {
    super(date);
  }
  
  public MayanLongCount(long baktun, int katun, int tun, int uinal, int kin) {
    this.baktun = baktun;
    this.katun = katun;
    this.tun = tun;
    this.uinal = uinal;
    this.kin = kin;
  }
  












  public static final long EPOCH = fixedFromJD(584283.0D);
  











  public static long toFixed(long baktun, int katun, int tun, int uinal, int kin)
  {
    return EPOCH + 
      baktun * 144000L + 
      katun * 7200 + 
      tun * 360 + 
      uinal * 20 + 
      kin;
  }
  
  public long toFixed() {
    return toFixed(this.baktun, this.katun, this.tun, this.uinal, this.kin);
  }
  





  public void fromFixed(long date)
  {
    long longCount = date - EPOCH;
    this.baktun = quotient(longCount, 144000.0D);
    int dayOfBaktun = (int)mod(longCount, 144000L);
    this.katun = ((int)quotient(dayOfBaktun, 7200.0D));
    int dayOfKatun = mod(dayOfBaktun, 7200);
    this.tun = ((int)quotient(dayOfKatun, 360.0D));
    int dayOfTun = mod(dayOfKatun, 360);
    this.uinal = ((int)quotient(dayOfTun, 20.0D));
    this.kin = mod(dayOfTun, 20);
  }
  
  public void fromArray(int[] a)
  {
    this.baktun = a[0];
    this.katun = a[1];
    this.tun = a[2];
    this.uinal = a[3];
    this.kin = a[4];
  }
  




  protected String toStringFields()
  {
    return 
      "baktun=" + this.baktun + ",katun=" + this.katun + ",tun=" + this.tun + ",uinal=" + this.uinal + ",kin=" + this.kin;
  }
  
  public String format() {
    return MessageFormat.format("{0}.{1}.{2}.{3}.{4}", 
      new Object[] {
      new Long(this.baktun), 
      new Integer(this.katun), 
      new Integer(this.tun), 
      new Integer(this.uinal), 
      new Integer(this.kin) });
  }
  

  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MayanLongCount)) {
      return false;
    }
    MayanLongCount o = (MayanLongCount)obj;
    





    return (o.baktun == this.baktun) && (o.katun == this.katun) && (o.tun == this.tun) && (o.uinal == this.uinal) && (o.kin == this.kin);
  }
}