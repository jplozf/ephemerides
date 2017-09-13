package fr.ozf.fmr.ReingoldDershowitz;

import java.text.MessageFormat;

public class Hebrew extends StandardDate
{
   public static final int TISHRI = 7;
   public static final int NISAN = 1;

   public Hebrew()
   {
   }

   public Hebrew(long date)
   {
      super(date);
   }

   public Hebrew(Date date)
   {
      super(date);
   }

   public Hebrew(long year, int month, int day)
   {
      super(year, month, day);
   }

   public static final long EPOCH = Julian.toFixed(Julian.BCE(3761L), 10, 7);

   public static long toFixed(long year, int month, int day)
   {
      long date
         = newYear(year)
         + day - 1L;

      if (month < 7)
      {
         for (int m = 7; m <= lastMonthOfYear(year); m++)
         {
            date += lastDayOfMonth(m, year);
         }
         for (int m = 1; m < month; m++)
         {
            date += lastDayOfMonth(m, year);
         }
      }
      else
      {
         for (int m = 7; m < month; m++)
         {
            date += lastDayOfMonth(m, year);
         }
      }
      return date;
   }

   public long toFixed()
   {
      return toFixed(this.year, this.month, this.day);
   }

   public void fromFixed(long date)
   {
      long approx = 1L + quotient(date - EPOCH, 365.24682220597794D);
      for (this.year = (approx - 1L); newYear(this.year) <= date; this.year += 1L)
      {
      }
      this.year -= 1L;

      int start = date < toFixed(this.year, 1, 1) ? 7 : 1;
      for (this.month = start; date > toFixed(this.year, this.month, lastDayOfMonth(this.month, this.year)); this.month += 1)
      {
      }

      this.day = ((int) (1L + date - toFixed(this.year, this.month, 1)));
   }

   public static boolean isLeapYear(long hYear)
   {
      return mod(1L + 7L * hYear, 19L) < 7L;
   }

   public static int lastMonthOfYear(long hYear)
   {
      return isLeapYear(hYear) ? 13 : 12;
   }

   public static int lastDayOfMonth(int hMonth, long hYear)
   {
      return (hMonth == 2) || (hMonth == 4) || (hMonth == 6) || (hMonth == 10) || (hMonth == 13)
         || ((hMonth == 12) && (!isLeapYear(hYear)))
         || ((hMonth == 8) && (!hasLongMarheshvan(hYear))) || ((hMonth == 9) && (hasShortKislev(hYear))) ? 29 : 30;
   }

   public static double molad(int hMonth, long hYear)
   {
      long y = hMonth < 7 ? hYear + 1L : hYear;
      long monthsElapsed = hMonth - 7 + quotient(235L * y - 234L, 19.0D);
      return EPOCH - 0.033796296296296297D
         + monthsElapsed * (29.0D + hr(12.0D) + 0.030594135802469134D);
   }

   public static long calendarElapsedDays(long hYear)
   {
      long monthsElapsed = quotient(235L * hYear - 234L, 19.0D);
      double partsElapsed = 12084.0D + 13753.0D * monthsElapsed;
      long day = 29L * monthsElapsed + quotient(partsElapsed, 25920.0D);
      return mod(3L * (day + 1L), 7L) < 3L ? day + 1L : day;
   }

   public static long newYear(long hYear)
   {
      return EPOCH
         + calendarElapsedDays(hYear)
         + newYearDelay(hYear);
   }

   public static int newYearDelay(long hYear)
   {
      long ny0 = calendarElapsedDays(hYear - 1L);
      long ny1 = calendarElapsedDays(hYear);
      long ny2 = calendarElapsedDays(hYear + 1L);
      if (ny2 - ny1 == 356L)
      {
         return 2;
      }
      if (ny1 - ny0 == 382L)
      {
         return 1;
      }
      return 0;
   }

   public static int daysInYear(long hYear)
   {
      return (int) (newYear(hYear + 1L) - newYear(hYear));
   }

   public static boolean hasLongMarheshvan(long hYear)
   {
      int days = daysInYear(hYear);
      return (days == 355) || (days == 385);
   }

   public static boolean hasShortKislev(long hYear)
   {
      int days = daysInYear(hYear);
      return (days == 353) || (days == 383);
   }

   public static double jewishDusk(long date, Location locale)
      throws BogusTimeException
   {
      return dusk(date, locale, angle(4.0D, 40.0D, 0.0D));
   }

   public static double jewishSabbathEnds(long date, Location locale)
      throws BogusTimeException
   {
      return dusk(date, locale, angle(7.0D, 5.0D, 0.0D));
   }

   public static double jewishMorningEnd(long date, Location locale)
      throws BogusTimeException
   {
      return standardFromSundial(date, 10.0D, locale);
   }

   public static long yomKippur(long gYear)
   {
      long hYear = 1L + gYear - Gregorian.yearFromFixed(EPOCH);
      return toFixed(hYear, 7, 10);
   }

   public static long passover(long gYear)
   {
      long hYear = gYear - Gregorian.yearFromFixed(EPOCH);
      return toFixed(hYear, 1, 15);
   }

   public static long classicalPassoverEve(long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      double equinox = solarLongitudeAfter(jan1, SPRING);
      try
      {
         long newMoon = phasisOnOrBefore((long) (Math.floor(equinox) + 10L), JERUSALEM);
         double set = universalFromStandard(sunset(newMoon + 14L, JERUSALEM), JERUSALEM);
         long nisan = equinox < set ? newMoon : phasisOnOrBefore(newMoon + 45L, JERUSALEM);
         return nisan + 13L;
      } catch (BogusTimeException ex)
      {
      }
      return 0L;
   }

   public static int[] omer(long date)
      throws BogusDateException
   {
      long c = date - passover(Gregorian.yearFromFixed(date));
      int[] result;
      if ((1L <= c) && (c <= 49L))
      {
         result = new int[]
            {
               (int) quotient(c, 7.0D), (int) mod(c, 7L)
            };
      }
      else
      {
         throw new BogusDateException();
      }
      return result;
   }

   public static long purim(long gYear)
   {
      long hYear = gYear - Gregorian.yearFromFixed(EPOCH);
      int lastMonth = lastMonthOfYear(hYear);
      return toFixed(hYear, lastMonth, 14);
   }

   public static long taAnitEsther(long gYear)
   {
      long purimDate = purim(gYear);
      return dayOfWeekFromFixed(purimDate) == 0L ? purimDate - 3L : purimDate - 1L;
   }

   public static long tishahBeAv(long gYear)
   {
      long hYear = gYear - Gregorian.yearFromFixed(EPOCH);
      long ninthOfAv = toFixed(hYear, 5, 9);
      return dayOfWeekFromFixed(ninthOfAv) == 6L ? ninthOfAv + 1L : ninthOfAv;
   }

   public static FixedVector birkathHaHama(long gYear)
   {
      FixedVector dates = Coptic.inGregorian(7, 30, gYear);
      if ((dates.size() != 0) && (mod(new Coptic(dates.fixedAt(0)).year, 28L) == 17L))
      {
         return dates;
      }
      return new FixedVector();
   }

   public static FixedVector shEla(long gYear)
   {
      return Coptic.inGregorian(3, 26, gYear);
   }

   public static long yomHaZikkaron(long gYear)
   {
      long hYear = gYear - Gregorian.yearFromFixed(EPOCH);
      long iyar4 = toFixed(hYear, 2, 4);
      return 3L < dayOfWeekFromFixed(iyar4) ? kDayBefore(iyar4, 3) : iyar4;
   }

   public static long birthday(Hebrew birthDate, long hYear)
   {
      return birthDate.month == lastMonthOfYear(birthDate.year)
         ? toFixed(hYear, lastMonthOfYear(hYear), birthDate.day)
         : toFixed(hYear, birthDate.month, 1) + birthDate.day - 1L;
   }

   public static FixedVector birthdayInGregorian(Hebrew birthDate, long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      long dec31 = Gregorian.toFixed(gYear, 12, 31);
      long y = new Hebrew(jan1).year;
      long date1 = birthday(birthDate, y);
      long date2 = birthday(birthDate, y + 1L);
      FixedVector result = new FixedVector(1, 1);
      if (jan1 <= date1)
      {
         result.addFixed(date1);
      }
      if (date2 <= dec31)
      {
         result.addFixed(date2);
      }
      return result;
   }

   public static long yahrzeit(Hebrew deathDate, long hYear)
   {
      long result;

      if ((deathDate.month == 8) && (deathDate.day == 30) && (!hasLongMarheshvan(deathDate.year + 1L)))
      {
         result = toFixed(hYear, 9, 1) - 1L;
      }
      else if ((deathDate.month == 9) && (deathDate.day == 30) && (hasShortKislev(deathDate.year + 1L)))
      {
         result = toFixed(hYear, 10, 1) - 1L;
      }
      else if (deathDate.month == 13)
      {
         result = toFixed(hYear, lastMonthOfYear(hYear), deathDate.day);
      }
      else if ((deathDate.day == 30) && (deathDate.month == 12) && (!isLeapYear(hYear)))
      {
         result = toFixed(hYear, 11, 30);
      }
      else
      {
         result = toFixed(hYear, deathDate.month, 1) + deathDate.day - 1L;
      }
      return result;
   }

   public static FixedVector yahrzeitInGregorian(Hebrew deathDate, long gYear)
   {
      long jan1 = Gregorian.toFixed(gYear, 1, 1);
      long dec31 = Gregorian.toFixed(gYear, 12, 31);
      long y = new Hebrew(jan1).year;
      long date1 = yahrzeit(deathDate, y);
      long date2 = yahrzeit(deathDate, y + 1L);
      FixedVector result = new FixedVector(1, 1);
      if (jan1 <= date1)
      {
         result.addFixed(date1);
      }
      if (date2 <= dec31)
      {
         result.addFixed(date2);
      }
      return result;
   }

   public static final String[] dayOfWeekNames =
      {
         "yom rishon",
         "yom sheni",
         "yom shelishi",
         "yom revi`i",
         "yom hamishi",
         "yom shishi",
         "yom shabbat"
      };

   public static final String[] monthNames =
      {
         "Nisan",
         "Iyyar",
         "Sivan",
         "Tammuz",
         "Av",
         "Elul",
         "Tishri",
         "Marheshvan",
         "Kislev",
         "Tevet",
         "Shevat",
         "Adar"
      };

   public static final String[] leapYearMonthNames =
      {
         "Nisan",
         "Iyyar",
         "Sivan",
         "Tammuz",
         "Av",
         "Elul",
         "Tishri",
         "Marheshvan",
         "Kislev",
         "Tevet",
         "Shevat",
         "Adar I",
         "Adar II"
      };

   public String format()
   {
      return MessageFormat.format("{0}, {1} {2} {3,number,#} A.M.",
         new Object[]
            {
               nameFromDayOfWeek(toFixed(), dayOfWeekNames),
               new Integer(this.day),
               nameFromMonth(this.month, isLeapYear(this.year) ? leapYearMonthNames : monthNames),
               new Long(this.year)
            });
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Hebrew))
      {
         return false;
      }
      return internalEquals(obj);
   }
}
