package fr.ozf.fmr;

public class fmrSeasons
{
	public int nbDaysFrom;
	public int nbDaysTotal;
	public int season;

	public static final int SPRING = 0;
	public static final int SUMMER = 1;
	public static final int AUTUMN = 2;
	public static final int WINTER = 3;
/*
	public fmrDate getSpringDate(int year)
	{
		fmrDate springDate;

		return (springDate);
	}

	public fmrDate getSummerDate(int year)
	{
		fmrDate summerDate;

		return (summerDate);
	}

	public fmrDate getAutumnDate(int year)
	{
		fmrDate autumnDate;

		return (autumnDate);
	}

	public fmrDate getWinterDate(int year)
	{
		fmrDate winterDate;

		return (winterDate);
	}

	private double getJJ0(int year, int season)
	{
		double JJ0 = ((double) year + (((double) season) / 4.0)) * 365.2422 + 1721141.3;
		return JJ0;
	}

	private double getL(double JJ0)
	{
		double T = (JJ0 - 2451545.0) / 36525.0;
		double M = 357.5291 + 35999.05 * T;
		double C = 1.9146 * Math.sin(Math.toRadians(M)) + 0.02 * Math.sin(Math.toRadians(2.0 * M));
		double L = 280.46645 + 36000.77 * T + C;

		return L;
	}

	private double getJJ1(double JJ0, int season, double L)
	{
		double JJ1 = JJ0 + 58.0 * Math.sin(Math.toRadians((double) season * 90.0 - L));
		return JJ1;
	}

	private double getJJSeason(int year, int season)
	{
		double JJ0 = getJJ0(year, season);
		double L = getL(JJ0);
		double JJ1 = getJJ1(JJ0, season, L);
		L = getL(JJ1);
		JJ1 = getJJ1(JJ1, season, L);
		L = getL(JJ1);
		JJ1 = getJJ1(JJ1, season, L);

		return JJ1;
	}

	public fmrDate JulianDayToDate(double jj)
	{
		int JD = jj.intValue();
		int b, c, d, e, m;


	}
	void jdn_to_date(int style, int JD, int *year, int *month, int *day)
	{
		int b, c, d, e, m;

		if (style == JULIAN)
		{
			b = 0;
			c = JD + 32082;
		}
		else
		{
			int a = JD + 32044;
			b = (4 * a + 3) / 146097;
			c = a - (b * 146097) / 4;
		}

		d = (4 * c + 3) / 1461;
		e = c - (1461 * d) / 4;
		m = (5 * e + 2) / 153;

		*day   = e - (153 * m + 2) / 5 + 1;
		*month = m + 3 - 12 * (m / 10);
		*year  = b * 100 + d - 4800 + m / 10;
	}

	int date_to_jdn(int style, int year, int month, int day)
	{
		int a = (14 - month) / 12;
		int y = year + 4800 - a;
		int m = month + 12 * a - 3;
		return style == JULIAN
			? day + (153 * m + 2) / 5 + y * 365 + y / 4 - 32083
			: day + (153 * m + 2) / 5 + y * 365 + y / 4 - y / 100 + y / 400 - 32045;
	}
	*/
}

/*
 Ces formules déterminent donc des évènements astronomiques qui débutent conventionnellement des saisons, mais le nom des saisons dépend bien sûr de l'hémisphère dans lequel on est : quand c'est l'hiver dans l'hémisphère nord c'est l'été dans le sud et vice-versa....(et inversement).

1ère étape

Elle consiste à trouver la date approximative en Jour Julien JJ0 :

JJ0 = ( A + k / 4 ) * 365,2422 + 1721141,3

L'année A est un entier (une valeur décimale n' a pas de sens ici)

k prend la valeur :	0 pour l'équinoxe de Mars
1 pour le solstice de Juin
2 pour l'équinoxe de Septembre
3 pour le solstice de Décembre
2ème étape

Pour le jour JJ0 trouvé ci-dessus, et pour chaque saison, calculer la longitude apparente L du soleil par l'ensemble de formules suivantes :

T = (JJ0 - 2451545 ) / 36525	M = 357°,5291 + 35999°,05 * T
C = 1°,9146 * sin ( M ) + 0°,02 * sin ( 2M )

L = 280°,46645 + 36000°,77 * T + C

On retrouve des expressions utilisées dans le calcul de l'Equation du Temps mais exprimées ici avec plus de précision

3ème étape

Appliquer la correction suivante à la valeur de JJ0 trouvée à la 1ère étape :

JJ1 = JJ0 + 58 * sin ( k * 90° - L )

4ème étape

Utiliser cette nouvelle valeur JJ1 pour recommencer le calcul des étapes 2 et 3 afin d'obtenir une valeur plus précise. Pour une utilisation courante une itération suffit souvent (cela dépend du nombre de décimales que vous souhaitez)

 

Une fois JJ obtenu il faut en déduire la date correspondante par la méthode exposée dans une autre page.



Calcul d'une date correspondant à un jour julien J J donné

Soit JJ un jour julien. A quelle date (jour J, mois M, année A) ce jour correspond il ? Le calcul est le suivant :

1ère étape

Ajouter 0,5 au jour JJ. Soit Z la partie entière et F la partie fractionnaire du résultat :

Z = Ent [ JJ + 0,5 ]	F = Frac [ JJ + 0,5 ]
2ème étape

Si Z < 2299161 (c'est le JJ du 15 octobre 1582) alors :	A = Z
Si Z = ou > 2299161 (c'est le cas des JJ du calendrier grégorien) alors :	a = Ent [ (Z-1867216,25)/36524,25 ]
A = Z + 1 + a - Ent [ a / 4 ]
3ème étape

Calculer :

B = A + 1524
C = Ent [ (B - 122,1) / 365,25 ]	D = Ent [ 365,25 * C ]
E = Ent [ (B - D ) / 30,6001 ]
Il faut bien garder les décimales indiquées

4ème étape

Le jour J cherché est :	J = B - D - Ent [ 30,6001 * E ) + F
Le résultat est un nombre décimal qui transformé en Jour Heure Minute si besoin est

Le mois M est donné par :
ou :

M = E - 1 si E < 13,5
M = E - 13 si E > 13,5

L'année A est donnée par :
ou :

A = C - 4716 si M > 2,5
A = C - 4715 si M < 2,5

La programmation de cet ensemble de formules est plus simple et rapide qu'il n'y paraît.

Remarque

>> Ent [ ] représente la partie entière de l'expression entre crochets, c'est-à-dire la partie du nombre qui précède la virgule. Par exemple : Ent [8,99999] = 8 et Ent [-8,9999] = -8

FSi vous utilisez Excel la fonction Ent [ ] est rendue par la fonction TRONQUE et non ENT !

Calcul du jour julien J J correspondant à une date donnée

Jour de la semaine

Soit une date J M A avec :

J : jour du mois, plus éventuellement une heure UTC sous la forme heures (HH) minutes (MM) secondes (SS)

M : mois (janvier M=1, février M=2, etc..)

A : année

1ère étape

Si M = 1 ou 2 (cad Janvier ou Février)

Remplacer A par A - 1 et M par M + 12

(Janvier et Février sont considérés comme les 13ème et 14ème mois de l'année précédente)

2ème étape

- Si J M A est une date du calendrier grégorien calculer :

C = Ent [ A / 100 ]

B = 2 - C + Ent [ C / 4 ]
- Si J M A est une date du calendrier julien : B = 0 (inutile de calculer C)

3ème étape

Calculer la fraction de jour correspondant à HH MM SS

T = HH / 24 + MM / 1440 + SS / 86400

4ème étape

Le jour julien est donné par :

JJ = Ent [ 365,25 * ( A + 4716 ) ] + Ent [ 30,6001 * ( M + 1 ) ] + J + T + B - 1524,5

 

Calcul inverse : Calcul d'une date correspondant à un jour julien J J donné

*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

Remarques

>> Ent [ ] représente la partie entière de l'expression entre crochets, c'est-à-dire la partie du nombre qui précède la virgule. Par exemple : Ent [8,99999] = 8 et Ent [-8,9999] = -8

FSi vous utilisez Excel la fonction Ent [ ] est rendue par la fonction TRONQUE et non ENT !

>> Le calendrier julien s'est terminé le 4 octobre 1582. Le calendrier grégorien a débuté le 15 octobre 1582.

Les dates allant du 5/10/1582 au 14/10/1582 n'existent pas

>> Par convention le Jour Julien débute à 12 h UT. Donc si vous calculez le JJ pour 00h UT vous trouverez un résultat terminé par ...,5. Ces formules fonctionnent aussi bien pour des années positives que négatives. Elles ne sont pas valables pour des JJ négatifs (de toutes façons antérieurs au 1er janvier de l'an -4712.....).

>> Les astronomes comptent les années non pas avant ou après J.-C. mais de façon arithmétique : c'est-à-dire que l'année 1 est précédée de l'année 0 qui elle-même est précédée de l'année -1. D'où la correspondance :

Histoire	Astronomie
2 après J.-C.	2
1 après J.-C.	1
1 avant J.-C.	0
2 avant J.-C.	-1
3 avant J.-C.	-2
....	....
482 avant J.-C.	-481
Mais ici les astronomes ne font pas un compte des années avec un calendrier, ils mesurent le temps écoulé depuis une origine pour donner une valeur à une variable T dans des expressions. Il est donc normal d'introduire une année 0.

>> Le MJD se déduit facilement du J J par soustraction de 2 400 000,5 au résultat.

*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=

Jour de la semaine

Il peut être facilement déduit du calcul du jour julien.

Pour une date donnée calculer la valeur du J J pour cette date à 00h UT , ajouter 1,5 puis diviser le résultat par 7. Le reste de cette division donnera le jour de la semaine avec la convention : 0 pour dimanche, 1 pour lundi, etc... Le calcul est valable quel que soit le calendrier puisque la réforme grégorienne n'a pas rompu le rythme des jours de la semaine.
 */
