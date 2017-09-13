package fr.ozf.fmr.ReingoldDershowitz;


public class ModifiedFrench
  extends StandardDate
{
  public ModifiedFrench() {}
  

  public ModifiedFrench(long date)
  {
    super(date);
  }
  
  public ModifiedFrench(Date date) {
    super(date);
  }
  
  public ModifiedFrench(long year, int month, int day) {
    super(year, month, day);
  }
  









  public static long toFixed(long year, int month, int day)
  {
    return French.EPOCH - 1L +
      365L * (year - 1L) + 
      quotient(year - 1L, 4.0D) - 
      quotient(year - 1L, 100.0D) + 
      quotient(year - 1L, 400.0D) - 
      quotient(year - 1L, 4000.0D) + 
      30 * (month - 1) + 
      day;
  }
  
  public long toFixed() {
    return toFixed(this.year, this.month, this.day);
  }
  






  public void fromFixed(long date)
  {
    long approx = 1L + quotient(date - French.EPOCH + 2L,
      365.24225D);
    this.year = (date < toFixed(approx, 1, 1) ? approx - 1L : approx);
    this.month = (1 + (int)quotient(date - toFixed(this.year, 1, 1), 30.0D));
    this.day = ((int)(date - toFixed(this.year, this.month, 1) + 1L));
  }
  











  public static boolean isLeapYear(long fYear)
  {
    boolean result = false;
    
    if (mod(fYear, 4L) == 0L) {
      long m400 = mod(fYear, 400L);
      if ((m400 != 100L) && (m400 != 200L) && (m400 != 300L) && 
        (mod(fYear, 4000L) != 0L)) {
        result = true;
      }
    }
    
    return result;
  }
  



  public String format()
  {
    return new French(this.year, this.month, this.day).format();
  }
  
  public boolean equals(Object obj) {
    if (!(obj instanceof ModifiedFrench)) {
      return false;
    }
    return internalEquals(obj);
  }
}