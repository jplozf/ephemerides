package fr.ozf.fmr;

public class fmrHatcher
{
   public HCalendar calEgyptien;
   public HCalendar calArmenien;
   public HCalendar calRepublicain;
   public HCalendar calJulien;

   public fmrHatcher()
   {
      // Calendrier Egyptien
      calEgyptien = new HCalendar("Egyptien");
      calEgyptien.setParams1(1448638, 0, 1448638, 4110215, 4110215);
      calEgyptien.setParams2(1, 13, 1, 365, 0, 3968, 47, 11260, 50);
      calEgyptien.setParamsConcord(0, 0, 1, 30, 0, 0);

      // Calendrier Arménien
      calArmenien = new HCalendar("Arménien");
      calArmenien.setParams1(1922868, 0, 1922868, 4584445, 4584445);
      calArmenien.setParams2(1, 13, 1, 365, 0, 5268, 317, 12560, 320);
      calArmenien.setParamsConcord(0, 0, 1, 30, 0, 0);

      // Calendrier Républicain
      calRepublicain = new HCalendar("Républicain");
      calRepublicain.setParams1(2375840, 1096, 2376936, 5037417, 5038513);
      calRepublicain.setParams2(1, 13, 4, 1461, 1, 6504, 111, 13792, 476);
      calRepublicain.setParamsConcord(0, 3, 1, 30, 0, 0);

      // Calendrier Julien
      calJulien = new HCalendar("Julien");
      calJulien.setParams1(1721424, 1155, 1722579, 4383001, 4384156);
      calJulien.setParams2(3, 12, 4, 1461, 1, 4716, 1401, 12000, 305);
      calJulien.setParamsConcord(0, 3, 5, 153, 2, 2);
   }

   /*
      CalX2CF
      CF2J
      J2CF
      CF2CalX
    */
   public HDate CF2CalX(HDate cf, HCalendar hc)
   {
      return (CF2CalX(cf, hc.n, hc.m, hc.y));
   }

   public HDate CF2CalX(HDate cf, int n, int m, int y)
   {
      int D = cf.getDay() + 1;
      int M = ((cf.getMonth() + m - 1) % n) + 1;
      int Y = cf.getYear() - y + (n + m - 1 + M) / n;
      HDate r = new HDate(D, M, Y);
      return r;
   }

   public HDate CalX2CF(HDate cx, HCalendar hc)
   {
      return (CalX2CF(cx, hc.n, hc.m, hc.y));
   }

   public HDate CalX2CF(HDate cx, int n, int m, int y)
   {
      int D = cx.getDay() - 1;
      int M = (cx.getMonth() - m + n) % n;
      int Y = cx.getYear() + y - (n + m - 1 - M) / n;
      HDate r = new HDate(D, M, Y);
      return r;
   }

   public int DMY2J(HDate hd, HCalendar hc)
   {
      int h = hd.getMonth() - hc.m;
      int J = (hc.p * (hd.getYear() + hc.y - (hc.n - h - 1) / hc.n) + hc.q) / hc.r;
      J += (hc.s * (h + hc.n) % hc.n + hc.t) / hc.u;
      J += hd.getDay() - 1;
      J += (-hc.j);
      return J;
   }

   public HDate J2DMY(int J, HCalendar hc)
   {
      int J_ = J + hc.j;
      int T_ = ((hc.r * J_ + hc.v) % hc.p) / hc.r;
      int D = ((hc.u * T_ + hc.w) % hc.s) / hc.u + 1;
      int M = (((hc.u * T_ + hc.w) / hc.s + hc.m - 1) % hc.n) + 1;
      int Y = (hc.r * J_ + hc.v) / hc.p - hc.y + ((hc.n + hc.m - 1 - M) / hc.n);
      HDate hd = new HDate(D, M, Y);
      return hd;
   }

	/*
   J.       L’algorithme complet pour convertir D/M/Y en J
1.                            Y' = Y + y - (n + m - 1 - M)/n                                           (3a)

2.                            M' = MOD(M - m + n,n)                                                                   (2a)

3.                            D' = D - 1                                                                                             (1a)

4.                            J  = (p*Y' + q)/r + (s*M' + t)/u + D' - j                                             (7)

ou plus succinctement et en évitant entièrement les éléments du calendrier fictif :

1.                            h  = M - m

2.                            J  = (p*[Y + y - (n - h - 1)/n] + q)/r

                                               + (s*MOD(h + n,n) + t)/u

                                               +  D - 1

                                               - j



K.      L’algorithme complet pour convertir J en D/M/Y
1.                            J' = J + j

2.                            Y' = (r*J' + v)/p

3.                            T' = MOD(r*J' + v,p)/r

4.                            M' = (u*T' + w)/s

5.                            D  = MOD(u*T' + w,s)/u + 1

6.                            M  = MOD(M' + m - 1,n) + 1

7.                            Y  = Y' - y + (n + m - 1 - M)/n

ou légèrement plus succinctement :

1.                            J' = J + j

2.                            T' = MOD(r*J' + v,p)/r

3.                            D  = MOD(u*T' + w,s)/u + 1

4.                            M  = MOD[(u*T' + w)/s + m - 1,n] + 1

5.                            Y  = (r*J' + v)/p - y + (n + m - 1 - M)/n

	 */

   public static class HDate
   {
      public int d;
      public int m;
      public int y;

      public HDate(int d, int m, int y)
      {
         this.d = d;
         this.m = m;
         this.y = y;
      }

      public int getDay()
      {
         return this.d;
      }

      public int getMonth()
      {
         return this.m;
      }

      public int getYear()
      {
         return this.y;
      }

      public String toString()
      {
         return (String.valueOf(this.d) + "/" + String.valueOf(this.m) + "/" + String.valueOf(this.y));
      }
   }

   public class HCalendar
   {
      public String name;
      public int Jg, x, J1, Jg_, J1_;
      public int m, n, r, p, E, y, j, y_, j_;
      public int q, v, u, s, t, w;

      public HCalendar(String name)
      {
         this.name = name;
      }

      public void setParams1(int Jg, int x, int J1, int Jg_, int J1_)
      {
         this.Jg = Jg;
         this.x = x;
         this.J1 = J1;
         this.Jg_ = Jg_;
         this.J1_ = J1_;
      }

      public void setParams2(int m, int n, int r, int p, int E, int y, int j, int y_, int j_)
      {
         this.m = m;
         this.n = n;
         this.r = r;
         this.p = p;
         this.E = E;
         this.y = y;
         this.j = j;
         this.y_ = y_;
         this.j_ = j_;
      }

      public void setParamsConcord(int q, int v, int u, int s, int t, int w)
      {
         this.q = q;
         this.v = v;
         this.u = u;
         this.s = s;
         this.t = t;
         this.w = w;
      }
   }
}
