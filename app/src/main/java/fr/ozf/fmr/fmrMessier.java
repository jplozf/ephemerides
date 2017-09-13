package fr.ozf.fmr;

public final class fmrMessier
{
   public int M;
   public int NGC;
   public String Con;
   public double RA;
   public double Dec;
   public double vMag;
   public String Type;
   public String Name;
   
   public static final int LOW_BOUND = 1;
   public static final int HIGH_BOUND = 110;
   
   private static final fmrMessier obj[] =
   {
      new fmrMessier(1, 1952, "Tau", 5.345, 22.01, 8.4, "SNR", "Crab Nebula"),
      new fmrMessier(2, 7089, "Aqr", 21.335, -0.49, 6.5, "GC", "N/A"),
      new fmrMessier(3, 5272, "CVn", 13.422, 28.23, 6.4, "GC", "N/A"),
      new fmrMessier(4, 6121, "Sco", 16.236, -26.32, 5.9, "GC", "N/A"),
      new fmrMessier(5, 5904, "Ser", 15.186, 2.05, 5.8, "GC", "N/A"),
      new fmrMessier(6, 6405, "Sco", 17.401, -32.13, 4.2, "OC", "Butterfly Cluster"),
      new fmrMessier(7, 6475, "Sco", 17.539, -34.49, 3.3, "OC", "N/A"),
      new fmrMessier(8, 6523, "Sgr", 18.038, -24.23, 5.8, "EN", "Lagoon Nebula"),
      new fmrMessier(9, 6333, "Oph", 17.192, -18.31, 7.9, "GC", "N/A"),
      new fmrMessier(10, 6254, "Oph", 16.571, -4.06, 6.6, "GC", "N/A"),
      new fmrMessier(11, 6705, "Sct", 18.511, -6.16, 5.8, "OC", "Wild Duck Cluster"),
      new fmrMessier(12, 6218, "Oph", 16.472, -1.57, 6.6, "GC", "N/A"),
      new fmrMessier(13, 6205, "Her", 16.417, 36.28, 5.9, "GC", "Hercules Cluster"),
      new fmrMessier(14, 6402, "Oph", 17.376, -03.15, 7.6, "GC", "N/A"),
      new fmrMessier(15, 7078, "Peg", 21.300, 12.10, 6.4, "GC", "N/A"),
      new fmrMessier(16, 6611, "Ser", 18.188, -13.47, 6.0, "EN", "Eagle Nebula"),
      new fmrMessier(17, 6618, "Sgr", 18.208, -16.11, 7.0, "EN", "Swan Nebula"),
      new fmrMessier(18, 6613, "Sgr", 18.199, -17.08, 6.9, "OC", "N/A"),
      new fmrMessier(19, 6273, "Oph", 17.026, -26.16, 7.2, "GC", "N/A"),
      new fmrMessier(20, 6514, "Sgr", 18.026, -23.02, 8.5, "ERN", "Trifid Nebula"),
      new fmrMessier(21, 6531, "Sgr", 18.046, -22.30, 5.9, "OC", "N/A"),
      new fmrMessier(22, 6656, "Sgr", 18.364, -23.54, 5.1, "GC", "N/A"),
      new fmrMessier(23, 6494, "Sgr", 17.568, -19.01, 5.5, "OC", "N/A"),
      new fmrMessier(24, 0, "Sgr", 18.169, -18.29, 4.5, "SCL", "star cloud"),
      new fmrMessier(25, 4725, "Sgr", 18.316, -19.15, 4.6, "OC", "N/A"),
      new fmrMessier(26, 6694, "Sct", 18.452, -9.24, 8.0, "OC", "N/A"),
      new fmrMessier(27, 6853, "Vul", 19.596, 22.43, 8.1, "PN", "Dumbbell Nebula"),
      new fmrMessier(28, 6626, "Sgr", 18.245, -24.52, 6.9, "GC", "N/A"),
      new fmrMessier(29, 6913, "Cyg", 20.239, 38.32, 6.6, "OC", "N/A"),
      new fmrMessier(30, 7099, "Cap", 21.404, -23.11, 7.5, "GC", "N/A"),
      new fmrMessier(31, 224, "And", 0.427, 41.16, 3.4, "Sb", "Great Andromeda Sp."),
      new fmrMessier(32, 221, "And", 0.427, 40.52, 8.2, "E2", "(M31 companion)"),
      new fmrMessier(33, 598, "Tri", 1.339, 30.39, 5.7, "Sc", "Triangulum Spiral"),
      new fmrMessier(34, 1039, "Per", 2.420, 42.47, 5.2, "OC", "N/A"),
      new fmrMessier(35, 2168, "Gem", 6.089, 24.20, 5.1, "OC", "N/A"),
      new fmrMessier(36, 1960, "Aur", 5.361, 34.08, 6.0, "OC", "N/A"),
      new fmrMessier(37, 2099, "Aur", 5.524, 32.33, 5.6, "OC", "N/A"),
      new fmrMessier(38, 1912, "Aur", 5.287, 35.50, 6.4, "OC", "N/A"),
      new fmrMessier(39, 7092, "Cyg", 21.322, 48.26, 4.6, "OC", "N/A"),
      new fmrMessier(40, 0, "UMa", 12.224, 58.05, 8.0, "DS", "Winnecke 4"),
      new fmrMessier(41, 2287, "CMa", 6.470, -20.44, 4.5, "OC", "N/A"),
      new fmrMessier(42, 1976, "Ori", 5.354, -5.27, 4.0, "EN", "Great Orion Nebula"),
      new fmrMessier(43, 1982, "Ori", 5.356, -5.16, 9.0, "EN", "Orion Nebula (part)"),
      new fmrMessier(44, 2632, "Cnc", 8.401, 19.59, 3.1, "OC", "Beehive Cluster"),
      new fmrMessier(45, 0, "Tau", 3.470, 24.07, 1.2, "OC", "Pleiades"),
      new fmrMessier(46, 2437, "Pup", 7.418, -14.49, 6.1, "OC", "N/A"),
      new fmrMessier(47, 2422, "Pup", 7.366, -14.30, 4.4, "OC", "N/A"),
      new fmrMessier(48, 2548, "Hya", 8.138, -5.48, 5.8, "OC", "N/A"),
      new fmrMessier(49, 4472, "Vir", 12.298, 8.00, 8.4, "E4", "N/A"),
      new fmrMessier(50, 2323, "Mon", 7.032, -8.20, 5.9, "OC", "N/A"),
      new fmrMessier(51, 194, "CVn", 13.299, 47.12, 8.1, "Sc", "Whirlpool Galaxy"),
      new fmrMessier(52, 7654, "Cas", 23.242, 61.35, 6.9, "OC", "N/A"),
      new fmrMessier(53, 5024, "Com", 13.129, 18.10, 7.7, "GC", "N/A"),
      new fmrMessier(54, 6715, "Sgr", 18.551, -30.29, 7.7, "GC", "N/A"),
      new fmrMessier(55, 6809, "Sgr", 19.400, -30.58, 7.0, "GC", "N/A"),
      new fmrMessier(56, 6779, "Lyr", 19.166, 30.11, 8.2, "GC", "N/A"),
      new fmrMessier(57, 6720, "Lyr", 18.536, 33.02, 9.0, "PN", "Ring Nebula"),
      new fmrMessier(58, 4579, "Vir", 12.377, 11.49, 9.8, "SB", "N/A"),
      new fmrMessier(59, 4621, "Vir", 12.420, 11.39, 9.8, "E3", "N/A"),
      new fmrMessier(60, 4649, "Vir", 12.437, 11.33, 8.8, "E1", "N/A"),
      new fmrMessier(61, 4303, "Vir", 12.219, 4.28, 9.7, "Sc", "N/A"),
      new fmrMessier(62, 6266, "Oph", 17.012, -30.07, 6.6, "GC", "N/A"),
      new fmrMessier(63, 5055, "CVn", 13.158, 42.02, 8.6, "Sb", "Sunflower Galaxy"),
      new fmrMessier(64, 4826, "Com", 12.567, 21.41, 8.5, "Sb", "Black Eye Galaxy"),
      new fmrMessier(65, 3623, "Leo", 11.189, 13.05, 9.3, "Sb", "N/A"),
      new fmrMessier(66, 3627, "Leo", 11.202, 12.59, 9.0, "Sb", "N/A"),
      new fmrMessier(67, 2682, "Cnc", 8.504, 11.49, 6.9, "OC", "N/A"),
      new fmrMessier(68, 4590, "Hya", 12.395, -26.45, 8.2, "GC", "N/A"),
      new fmrMessier(69, 6637, "Sgr", 18.314, -32.21, 7.7, "GC", "N/A"),
      new fmrMessier(70, 6681, "Sgr", 18.432, -32.18, 8.1, "GC", "N/A"),
      new fmrMessier(71, 6838, "Sge", 19.538, 18.47, 8.3, "GC", "N/A"),
      new fmrMessier(72, 6981, "Aqr", 20.535, -12.32, 9.4, "GC", "N/A"),
      new fmrMessier(73, 6994, "Aqr", 20.589, -12.38, 5.0, "OC", "N/A"),
      new fmrMessier(74, 628, "Psc", 1.367, 15.47, 9.2, "Sc", "N/A"),
      new fmrMessier(75, 6864, "Sgr", 20.061, -21.55, 8.6, "GC", "N/A"),
      new fmrMessier(76, 650, "Per", 1.424, 51.34, 11.5, "PN", "Little Dumbbell"),
      new fmrMessier(77, 1068, "Cet", 2.427, -0.01, 8.8, "Sbp", "N/A"),
      new fmrMessier(78, 2068, "Ori", 5.467, 0.03, 8.0, "RN", "N/A"),
      new fmrMessier(79, 1904, "Lep", 5.245, -24.33, 8.0, "GC", "N/A"),
      new fmrMessier(80, 6093, "Sco", 16.170, -22.59, 7.2, "GC", "N/A"),
      new fmrMessier(81, 3031, "UMa", 9.556, 69.04, 6.8, "Sb", "N/A"),
      new fmrMessier(82, 3034, "UMa", 9.558, 69.41, 8.4, "Irr", "exploding galaxy"),
      new fmrMessier(83, 5236, "Hya", 13.370, -29.52, 10.1, "Sc", "N/A"),
      new fmrMessier(84, 4374, "Vir", 12.251, 12.53, 9.3, "E1", "N/A"),
      new fmrMessier(85, 4382, "Com", 12.254, 18.11, 9.3, "Ep", "N/A"),
      new fmrMessier(86, 4406, "Vir", 12.262, 12.57, 9.2, "E3", "N/A"),
      new fmrMessier(87, 4486, "Vir", 12.308, 12.24, 8.6, "E1", "N/A"),
      new fmrMessier(88, 4501, "Com", 12.320, 14.25, 9.5, "Sb", "N/A"),
      new fmrMessier(89, 4552, "Vir", 12.357, 12.33, 9.8, "E0", "N/A"),
      new fmrMessier(90, 4569, "Vir", 12.368, 13.10, 9.5, "Sb", "N/A"),
      new fmrMessier(91, 4548, "Com", 12.354, 14.30, 10.2, "SBb", "N/A"),
      new fmrMessier(92, 6341, "Her", 17.171, 43.08, 6.5, "GC", "N/A"),
      new fmrMessier(93, 2447, "Pup", 7.446, -23.52, 6.2, "OC", "N/A"),
      new fmrMessier(94, 4736, "CVn", 12.509, 41.07, 8.1, "Sbp", "N/A"),
      new fmrMessier(95, 3351, "Leo", 10.440, 11.42, 9.7, "SBb", "N/A"),
      new fmrMessier(96, 3368, "Leo", 10.468, 11.49, 9.2, "Sbp", "N/A"),
      new fmrMessier(97, 3587, "UMa", 11.148, 55.01, 11.2, "PN", "Owl Nebula"),
      new fmrMessier(98, 4192, "Com", 12.138, 14.54, 10.1, "Sb", "N/A"),
      new fmrMessier(99, 4254, "Com", 12.188, 14.25, 9.8, "Sc", "N/A"),
      new fmrMessier(100, 4321, "Com", 12.229, 15.49, 9.4, "Sc", "N/A"),
      new fmrMessier(101, 5457, "UMa", 14.032, 54.21, 7.7, "Sc", "Pinwheel Galaxy"),
      new fmrMessier(102, 5866, "Dra", 15.065, 55.46, 10.0, "E6p", "N/A"),
      new fmrMessier(103, 581, "Cas", 1.332, 60.42, 7.4, "OC", "N/A"),
      new fmrMessier(104, 4594, "Vir", 12.400, -11.37, 8.3, "Sb", "Sombrero Galaxy"),
      new fmrMessier(105, 3379, "Leo", 10.478, 12.35, 9.3, "E1", "N/A"),
      new fmrMessier(106, 4258, "CVn", 12.190, 47.18, 8.3, "Sbp", "N/A"),
      new fmrMessier(107, 6171, "Oph", 16.325, -13.03, 8.1, "GC", "N/A"),
      new fmrMessier(108, 3556, "UMa", 11.115, 55.40, 10.0, "Sc", "N/A"),
      new fmrMessier(109, 3992, "UMa", 11.576, 53.23, 9.8, "Sb", "N/A"),
      new fmrMessier(110, 205, "And", 0.404, 41.41, 8.0, "E6", "(M31 companion)")
   };

//******************************************************************************
// fmrMessier()
//******************************************************************************
// This is a private constructor !
// Only Charles Messier (1730-1817) is supposed to add new objects.
//******************************************************************************  
   private fmrMessier(int M, int NGC, String Con, double RA, double Dec, double vMag, String Type, String Name)
   {
      this.M = M;
      this.NGC = NGC;
      this.Con = Con;
      this.RA = RA;
      this.Dec = Dec;
      this.vMag = vMag;
      this.Type = Type;
      this.Name = Name;
   }

//******************************************************************************
// get()
//******************************************************************************
   public static fmrMessier get(int M)
   {
      if (M >= LOW_BOUND && M <= HIGH_BOUND)
      {
         return obj[M - 1];
      }
      else
      {
         throw new IndexOutOfBoundsException("\nMessier index " + M + " is out of bounds !\nShould be between " + LOW_BOUND + " and " + HIGH_BOUND + ".");
      }
   }

//******************************************************************************
// printInfos()
//******************************************************************************
   public static void printInfos(int M)
   {
      if (M >= LOW_BOUND && M <= HIGH_BOUND)
      {
         fmrMessier o = obj[M - 1];

         System.out.println("==============================");
         System.out.println("M" + o.M + " (NGC #" + o.NGC + ")");
         System.out.println("==============================");
         System.out.println("Constellation : " + o.Con);
         System.out.println("Right Asc.    : " + o.RA);
         System.out.println("Declination   : " + o.Dec);
         System.out.println("Magnitude     : " + o.vMag);
         System.out.println("Type          : " + o.Type);
         System.out.println("Name          : " + o.Name);
         System.out.println("==============================");
      }
      else
      {
         throw new IndexOutOfBoundsException("\nMessier index " + M + " is out of bounds !\nShould be between " + LOW_BOUND + " and " + HIGH_BOUND + ".");
      }
   }

   public void printInfos()
   {
         System.out.println("==============================");
         System.out.println("M" + this.M + " (NGC #" + this.NGC + ")");
         System.out.println("==============================");
         System.out.println("Constellation : " + this.Con);
         System.out.println("Right Asc.    : " + this.RA);
         System.out.println("Declination   : " + this.Dec);
         System.out.println("Magnitude     : " + this.vMag);
         System.out.println("Type          : " + this.Type);
         System.out.println("Name          : " + this.Name);
         System.out.println("==============================");
   }
}
/*
Following is the list of objects in the Messier Catalogue. 
 * Generally, these are the finest deep-sky objects visible from Northern Hemisphere skies. 
 * Objects are listed in order by Messier Catalogue number. 
 * Diameters, where known, are given in seconds of arc (1/3600 degree).

 M#   NGC#  Con  RA         Dec      vMag  Type  Dia"   Name
-------------------------------------------------------------------------
  1   1952  Tau  05h 34.5m  +22d 01'  8.4  SNR    300   Crab Nebula         
  2   7089  Aqr  21h 33.5m  -00d 49'  6.5  GC                               
  3   5272  CVn  13h 42.2m  +28d 23'  6.4  GC                               
  4   6121  Sco  16h 23.6m  -26d 32'  5.9  GC                               
  5   5904  Ser  15h 18.6m  +02d 05'  5.8  GC                               
  6   6405  Sco  17h 40.1m  -32d 13'  4.2  OC           Butterfly Cluster   
  7   6475  Sco  17h 53.9m  -34d 49'  3.3  OC                               
  8   6523  Sgr  18h 03.8m  -24d 23'  5.8  EN    2400   Lagoon Nebula       
  9   6333  Oph  17h 19.2m  -18d 31'  7.9  GC                               
 10   6254  Oph  16h 57.1m  -04d 06'  6.6  GC                               
 11   6705  Sct  18h 51.1m  -06d 16'  5.8  OC     750   Wild Duck Cluster   
 12   6218  Oph  16h 47.2m  -01d 57'  6.6  GC                               
 13   6205  Her  16h 41.7m  +36d 28'  5.9  GC           Hercules Cluster    
 14   6402  Oph  17h 37.6m  -03d 15'  7.6  GC                               
 15   7078  Peg  21h 30.0m  +12d 10'  6.4  GC                               
 16   6611  Ser  18h 18.8m  -13d 47'  6.0  EN           Eagle Nebula        
 17   6618  Sgr  18h 20.8m  -16d 11'  7.0  EN    1200   Swan Nebula (also known as Omega Nebula        )
 18   6613  Sgr  18h 19.9m  -17d 08'  6.9  OC                               
 19   6273  Oph  17h 02.6m  -26d 16'  7.2  GC                               
 20   6514  Sgr  18h 02.6m  -23d 02'  8.5  ERN    900   Trifid Nebula       
 21   6531  Sgr  18h 04.6m  -22d 30'  5.9  OC                               
 22   6656  Sgr  18h 36.4m  -23d 54'  5.1  GC                               
 23   6494  Sgr  17h 56.8m  -19d 01'  5.5  OC                               
 24         Sgr  18h 16.9m  -18d 29'  4.5  SCL          (star cloud)        
 25   4725  Sgr  18h 31.6m  -19d 15'  4.6  OC                               
 26   6694  Sct  18h 45.2m  -09d 24'  8.0  OC                               
 27   6853  Vul  19h 59.6m  +22d 43'  8.1  PN     420   Dumbbell Nebula     
 28   6626  Sgr  18h 24.5m  -24d 52'  6.9  GC                               
 29   6913  Cyg  20h 23.9m  +38d 32'  6.6  OC                               
 30   7099  Cap  21h 40.4m  -23d 11'  7.5  GC                               
 31    224  And  00h 42.7m  +41d 16'  3.4  Sb   14400   Great Andromeda Sp. 
 32    221  And  00h 42.7m  +40d 52'  8.2  E2           (M31 companion)     
 33    598  Tri  01h 33.9m  +30d 39'  5.7  Sc    3600   Triangulum Spiral   
 34   1039  Per  02h 42.0m  +42d 47'  5.2  OC                               
 35   2168  Gem  06h 08.9m  +24d 20'  5.1  OC                               
 36   1960  Aur  05h 36.1m  +34d 08'  6.0  OC                               
 37   2099  Aur  05h 52.4m  +32d 33'  5.6  OC                               
 38   1912  Aur  05h 28.7m  +35d 50'  6.4  OC                               
 39   7092  Cyg  21h 32.2m  +48d 26'  4.6  OC                               
 40         UMa  12h 22.4m  +58d 05'  8.0  DS           Winnecke 4          
 41   2287  CMa  06h 47.0m  -20d 44'  4.5  OC                               
 42   1976  Ori  05h 35.4m  -05d 27'  4.0  EN           Great Orion Nebula  
 43   1982  Ori  05h 35.6m  -05d 16'  9.0  EN           Orion Nebula (part) 
 44   2632  Cnc  08h 40.1m  +19d 59'  3.1  OC    5400   Beehive Cluster     
 45         Tau  03h 47.0m  +24d 07'  1.2  OC    7200   Pleiades            
 46   2437  Pup  07h 41.8m  -14d 49'  6.1  OC                               
 47   2422  Pup  07h 36.6m  -14d 30'  4.4  OC                               
 48   2548  Hya  08h 13.8m  -05d 48'  5.8  OC                               
 49   4472  Vir  12h 29.8m  +08d 00'  8.4  E4                               
 50   2323  Mon  07h 03.2m  -08d 20'  5.9  OC                               
 51  194&5  CVn  13h 29.9m  +47d 12'  8.1  Sc           Whirlpool Galaxy    
 52   7654  Cas  23h 24.2m  +61d 35'  6.9  OC                               
 53   5024  Com  13h 12.9m  +18d 10'  7.7  GC                               
 54   6715  Sgr  18h 55.1m  -30d 29'  7.7  GC                               
 55   6809  Sgr  19h 40.0m  -30d 58'  7.0  GC                               
 56   6779  Lyr  19h 16.6m  +30d 11'  8.2  GC                               
 57   6720  Lyr  18h 53.6m  +33d 02'  9.0  PN           Ring Nebula         
 58   4579  Vir  12h 37.7m  +11d 49'  9.8  SB                               
 59   4621  Vir  12h 42.0m  +11d 39'  9.8  E3                               
 60   4649  Vir  12h 43.7m  +11d 33'  8.8  E1                               
 61   4303  Vir  12h 21.9m  +04d 28'  9.7  Sc                               
 62   6266  Oph  17h 01.2m  -30d 07'  6.6  GC                               
 63   5055  CVn  13h 15.8m  +42d 02'  8.6  Sb           Sunflower Galaxy    
 64   4826  Com  12h 56.7m  +21d 41'  8.5  Sb           Black Eye Galaxy    
 65   3623  Leo  11h 18.9m  +13d 05'  9.3  Sb                               
 66   3627  Leo  11h 20.2m  +12d 59'  9.0  Sb                               
 67   2682  Cnc  08h 50.4m  +11d 49'  6.9  OC                               
 68   4590  Hya  12h 39.5m  -26d 45'  8.2  GC                               
 69   6637  Sgr  18h 31.4m  -32d 21'  7.7  GC                               
 70   6681  Sgr  18h 43.2m  -32d 18'  8.1  GC                               
 71   6838  Sge  19h 53.8m  +18d 47'  8.3  GC                               
 72   6981  Aqr  20h 53.5m  -12d 32'  9.4  GC                               
 73   6994  Aqr  20h 58.9m  -12d 38'  5.0  OC                               
 74    628  Psc  01h 36.7m  +15d 47'  9.2  Sc                               
 75   6864  Sgr  20h 06.1m  -21d 55'  8.6  GC                               
 76  650-1  Per  01h 42.4m  +51d 34' 11.5  PN           Little Dumbbell     
 77   1068  Cet  02h 42.7m  -00d 01'  8.8  Sbp                              
 78   2068  Ori  05h 46.7m  +00d 03'  8.0  RN                               
 79   1904  Lep  05h 24.5m  -24d 33'  8.0  GC                               
 80   6093  Sco  16h 17.0m  -22d 59'  7.2  GC                               
 81   3031  UMa  09h 55.6m  +69d 04'  6.8  Sb                               
 82   3034  UMa  09h 55.8m  +69d 41'  8.4  Irr          'exploding' galaxy  
 83   5236  Hya  13h 37.0m  -29d 52' 10.1  Sc                               
 84   4374  Vir  12h 25.1m  +12d 53'  9.3  E1                               
 85   4382  Com  12h 25.4m  +18d 11'  9.3  Ep                               
 86   4406  Vir  12h 26.2m  +12d 57'  9.2  E3                               
 87   4486  Vir  12h 30.8m  +12d 24'  8.6  E1                               
 88   4501  Com  12h 32.0m  +14d 25'  9.5  Sb                               
 89   4552  Vir  12h 35.7m  +12d 33'  9.8  E0                               
 90   4569  Vir  12h 36.8m  +13d 10'  9.5  Sb                               
 91   4548  Com  12h 35.4m  +14d 30' 10.2  SBb                              
 92   6341  Her  17h 17.1m  +43d 08'  6.5  GC                               
 93   2447  Pup  07h 44.6m  -23d 52'  6.2  OC                               
 94   4736  CVn  12h 50.9m  +41d 07'  8.1  Sbp                              
 95   3351  Leo  10h 44.0m  +11d 42'  9.7  SBb                              
 96   3368  Leo  10h 46.8m  +11d 49'  9.2  Sbp                              
 97   3587  UMa  11h 14.8m  +55d 01' 11.2  PN           Owl Nebula          
 98   4192  Com  12h 13.8m  +14d 54' 10.1  Sb                               
 99   4254  Com  12h 18.8m  +14d 25'  9.8  Sc                               
100   4321  Com  12h 22.9m  +15d 49'  9.4  Sc                               
101   5457  UMa  14h 03.2m  +54d 21'  7.7  Sc           Pinwheel Galaxy     
102   5866  Dra  15h 06.5m  +55d 46' 10.0  E6p                              
103    581  Cas  01h 33.2m  +60d 42'  7.4  OC                               
104   4594  Vir  12h 40.0m  -11d 37'  8.3  Sb           Sombrero Galaxy     
105   3379  Leo  10h 47.8m  +12d 35'  9.3  E1                               
106   4258  CVn  12h 19.0m  +47d 18'  8.3  Sbp                              
107   6171  Oph  16h 32.5m  -13d 03'  8.1  GC                               
108   3556  UMa  11h 11.5m  +55d 40' 10.0  Sc                               
109   3992  UMa  11h 57.6m  +53d 23'  9.8  Sb                               
110    205  And  00h 40.4m  +41d 41'  8.0  E6           (M31 companion)     

 * Legend of Object Types

DS   Double star
E#   Elliptical galaxy
EN   Emission nebula (glowing gas)
ERN  Emission and reflection nebula
GC   Globular cluster (stars)
Irr  Irregular galaxy
OC   Open cluster (stars)
PN   Planetary (disc-like) nebula
S..  Spiral galaxy
SB.  Barred spiral galaxy
SCL  Star cloud
SNR  Supernova remnant (complex gas/dust cloud)
 
 */
