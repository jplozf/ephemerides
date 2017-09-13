package fr.ozf.fmr;

import org.joda.time.*;

import java.util.ArrayList;

public class fmrJoursRemarquables
{
	public ArrayList<JourRemarquable> listeJoursRemarquables = new ArrayList<>();

	public class JourRemarquable
	{
		public int mois;
		public int jour;
		public String journee;
		public boolean chome;

		public JourRemarquable(int j, int m, String jrn, boolean chome)
		{
			this.mois = m;
			this.jour = j;
			this.journee = jrn;
			this.chome = chome;
		}
	}

	public fmrJoursRemarquables()
	{
		// Current date to get the current year
		DateTime dateTime = new DateTime();

		// Fêtes fixes
		listeJoursRemarquables.add(new JourRemarquable(1, 1, "Jour de l'An", true));
		listeJoursRemarquables.add(new JourRemarquable(1, 5, "Fête du Travail", true));
		listeJoursRemarquables.add(new JourRemarquable(8, 5, "Victoire 1945", true));
		listeJoursRemarquables.add(new JourRemarquable(14, 7, "Fête Nationale", true));
		listeJoursRemarquables.add(new JourRemarquable(15, 8, "Assomption", true));
		listeJoursRemarquables.add(new JourRemarquable(1, 11, "Toussaint", true));
		listeJoursRemarquables.add(new JourRemarquable(11, 11, "Armistice 1918", true));
		listeJoursRemarquables.add(new JourRemarquable(25, 12, "Noël", true));

		// Saint-Valentin
		listeJoursRemarquables.add(new JourRemarquable(14, 2, "Saint-Valentin : Fête des amoureux", false));

		// Saints de Glace
		listeJoursRemarquables.add(new JourRemarquable(11, 5, "Saints de Glace (1/3) : Saint-Mamert", false));
		listeJoursRemarquables.add(new JourRemarquable(12, 5, "Saints de Glace (2/3) : Saint-Pancrace", false));
		listeJoursRemarquables.add(new JourRemarquable(13, 5, "Saints de Glace (3/3) : Saint-Servais", false));

		// Microsoft's Patch Tuesday : 2ème mardi du mois
		for (int i = 1; i <= 12; i++)
		{
			LocalDate mpt = nthWeekdayOfMonth(2, i, dateTime.getYear(), 2);
			listeJoursRemarquables.add(new JourRemarquable(mpt.getDayOfMonth(), mpt.getMonthOfYear(), "Microsoft's Patch Tuesday", false));
		}

		// Fête des Pères : Troisième dimanche de Juin
		LocalDate fdp = nthWeekdayOfMonth(7, 6, dateTime.getYear(), 3);
		listeJoursRemarquables.add(new JourRemarquable(fdp.getDayOfMonth(), fdp.getMonthOfYear(), "Fête des Pères", false));

		// Fête des Grands-Mères : Premier dimanche de Mars
		LocalDate fgm = nthWeekdayOfMonth(7, 3, dateTime.getYear(), 1);
		listeJoursRemarquables.add(new JourRemarquable(fgm.getDayOfMonth(), fgm.getMonthOfYear(), "Fête des Grand-Mères", false));

		// Fête des Grands-Parents : Premier dimanche d'Octobre
		LocalDate fgp = nthWeekdayOfMonth(7, 10, dateTime.getYear(), 1);
		listeJoursRemarquables.add(new JourRemarquable(fgp.getDayOfMonth(), fgp.getMonthOfYear(), "Fête des Grands-Parents", false));

	}

	/**
	 * Calculates the nth occurrence of a day of the week, for a given month and
	 * year.
	 *
	 * @param dayOfWeek The day of the week to calculate the day for (In the range of
	 *                  [1,7], where 1 is Monday.
	 * @param month     The month to calculate the day for.
	 * @param year      The year to calculate the day for.
	 * @param n         The occurrence of the weekday to calculate. (ie. 1st, 2nd,
	 *                  3rd)
	 * @return A {@link LocalDate} with the nth occurrence of the day of week,
	 * for the given month and year.
	 */
	public static LocalDate nthWeekdayOfMonth(int dayOfWeek, int month, int year, int n)
	{
		LocalDate start = new LocalDate(year, month, 1);
		LocalDate date = start.withDayOfWeek(dayOfWeek);
		return (date.isBefore(start)) ? date.plusWeeks(n) : date.plusWeeks(n - 1);
	}

	public static LocalDate lastWeekdayOfMonth(int dayOfWeek, int month, int year)
	{
		LocalDate date = new LocalDate(year, month, 1).dayOfMonth()
				.withMaximumValue()
				.dayOfWeek()
				.setCopy(dayOfWeek);
		if (date.getMonthOfYear() != month)
		{
			return date.dayOfWeek().addToCopy(-7);
		}
		return date;
	}

	// Fête des Mères : Dernier dimanche de Mai sauf s'il coïncide avec Pentecôte, dans ce cas 1er dimanche de Juin

	// http://pgj.pagesperso-orange.fr/paques.htm
	// Rameaux
	// Pâques
	// Dimanches de Pâques (2ème au 7ème dimanche, jusqu'à la Pentecôte)
	// Pentecôte
	// Ascension
	// Mardi-Gras
	// Carême
	// Chandeleur
	// Mercredi des Cendres
	// Halloween
	// Avent

	// Cendres					P-46
	// 1er dimanche de Carême	P-42
	// La Passion				P-14
	// Les Rameaux				P-7
	// Pâques					P
	// Ascension				P+39
	// Pentecôte				P+49
	// Trinité					P+56
	// Fête-Dieu				P+63

	// Fête de la musique (21 juin)

	// Saints Patrons
	// Saisons (Printemps, été, automne, hiver)

	private class DivInt
	{
		public int Quotient;
		public int Remainder;

		public DivInt(int n1, int n2)
		{
			this.Quotient = n1 / n2;
			this.Remainder = n1 % n2;
		}
	}

	private class Paques
	{
		JourRemarquable paques;

		public Paques(int annee)
		{
			int s = new DivInt(annee, 100).Quotient;
			int m = new DivInt(annee, 100).Remainder;
			int N = new DivInt(annee, 19).Remainder;
			int g = new DivInt(annee * 5, 4).Quotient;

		}
	}

}
