package fr.ozf.fmr;

import java.util.Random;

/*
 * http://www.saint-dicton.com/liens/
 * 
*/

public class fmrDicton
{
   private static String[][] dictonsMois =
      {
         {        // JANVIER
            "Dieu te garde d'un bon Janvier. ",
            "Mieux vaut un voleur dans son grenier que du beau temps dès le mois de Janvier. ",
            "Il vaudrait mieux voir un loup sur le fumier qu'un homme en chemise en Janvier. ",
            "Janvier d'eau chiche rend le paysan riche. ",
            "Quand il tonne en Janvier, il tonne tous les mois de l'année. ",
            "Le travail de Janvier ne doit pas valoir un denier. ",
            "Je me fais Janvier appeler, le plus froid des mois de l'année. Et pourtant, je me puis vanter que ma saison doit être aimée. ",
            "Un mois de Janvier sans gelée, jamais n'amène bonne année. ",
            "Gelées en Janvier, blé au grenier. ",
            "Si les moucherons dansent en Janvier, ménage le foin du fenier. ",
            "Quand le crapaud chante en Janvier, serre la paille, métayer. ",
            "Si la grive chante en Janvier, prends garde, bouvier, à ton grenier. ",
            "Janvier sec et sage est un bon présage. ",
            "Sec Janvier, heureux fermier. ",
            "Sécheresse de Janvier, richesse du fermier. ",
            "S'il ne pleut en Janvier, paysan, étaie ton grenier. ",
            "Neige en Janvier vaut fumier. ",
            "La neige au blé rend même service que fait à l'homme chaude pelisse. ",
            "Janvier ne doit pas voir pisser un rat. ",
            "Si Janvier ne prend son manteau, malheur aux bois, aux moissons, aux coteaux. ",
            "Un Janvier froid et sans neige fait mal aux arbres et aux vignes. ",
            "Pluie de Janvier, cherté, brouillards, maladies mortelles. ",
            "Pluie de Janvier emplit les cimetières. ",
            "Le mauvais an entre en nageant. ",
            "Sous l'eau la faim, sous la neige le pain. ",
            "S'il tonne en Janvier, cuves au fumier, barils au grenier !"
         },
         {        // FEVRIER
            "Eau de Février vaut fumier. ",
            "Février par la pluie inondée, lors même que chacun s'écrie que tout est perdu sans retour n'a pas encore assez de pluie. ",
            "Quand Février n'a pas le temps de Février, le vent souffle l'an tout entier. ",
            "Neige de Février vaut fumier. ",
            "Neige en Février, bon temps pour les blés. ",
            "Février neigeux, été avantageux. ",
            "Fleur de Février ne va pas aux pommiers. ",
            "Février, le plus court des mois, est de tous le pire à la fois. ",
            "Février, entre tous les mois, le plus court et le moins courtois. ",
            "Février, de tous les mois, le plus froid et le plus matois. ",
            "Février n'a pas deux jours pareils. ",
            "Février tourne son bonnet sept coup devant derrière. ",
            "Quand Février débute en lion, il finit comme un mouton. ",
            "Février, tu févrièreras, mas tous les jours tu t'ensoleilleras. ",
            "Mieux vaut le loup près du fumier que la pluie de Février. ",
            "Pluie de Février emplit les greniers. ",
            "En Février, si au soleil ton chat tend sa peau, en mars, il l'exposera au fourneau. ",
            "Si Février ne févrotte, mars marmotte. ",
            "Février trop doux, printemps en courroux. ",
            "Si Février est chaud, croyez bien, sans défaut, que par cette aventure, Pâques aura sa froidure. ",
            "Quand la bise oublie Février, elle arrive en mai. ",
            "Si Février ne févrière pas, tout mois de l'an peu ou prou le fera. ",
            "Si Février n'a ses bourrasques, tous les mois feront des frasques. ",
            "S'il tonne en Février, montez vos tonneaux aux greniers. ",
            "Tonnerre en Février, point de vin au cellier, jette tes fûts au fumier ! ",
            "Février veut pain, vin, viande et feu."
         },
         {        // MARS
            "Mars sec, c'est du blé partout. ",
            "Poussière de Mars, poussière d'or. ",
            "Quand Mars est sec, le grain fait touffe. ",
            "Mars sec et beau emplit huches et tonneaux. ",
            "Mars hâleux marie la fille du laboureux. ",
            "Neige de Mars, vaut du blé en sac. ",
            "S'il neige en Mars, gare aux vergers. ",
            "Neige de Mars brûle le bourgeon. ",
            "En Mars, s'il tonne, l'année est bonne. ",
            "Mars venteux, verger pommeux. ",
            "Quand on entend le tonnerre en Mars, hélas, les vaches sont traites ! ",
            "Si vent en Mars, pas de fortes gelées. ",
            "Quand il tonne en Mars, le bonhomme dit «Hélas !» [car la neige couvrira la terre en mai] .",
            "Ce que Mars couve, on le sait après son trente et unième jour. ",
            "Soit au commencement, soit à la fin, Mars nous montrera son venin. ",
            "En Mars, vent ou pluie, que chacun veille sur lui. ",
            "Fleur marsière ne tient guère. ",
            "Des fleurs de Mars, on ne tient compte, non plus que de femme sans honte. ",
            "Des fleurs de Mars, on n'en a que le regard. ",
            "Des fleurs que Mars verra, peu de fruits on mangera. ",
            "En Mars, les fous vont nu-pieds. ",
            "En Mars, qui n'a pas de souliers va nu-pieds, et qui en a les porte encore au-delà. ",
            "Le soleil de Mars donne des rhumes tenaces. ",
            "En Mars, quand il fait beau, prends ton manteau. ",
            "Gelée du mois de Mars donne le blé et le lard. ",
            "Vigneron, vide ton verre, si la pluie en Mars inonde la terre. ",
            "Quand Mars bien mouillé sera, bien du vin tu récolteras. ",
            "Pluie de Mars grandit l'herbette et souvent annonce disette. ",
            "Mars pluvieux, an disetteux. ",
            "Mars dans l'eau prépare au laboureur fléau. ",
            "Mars pluvieux, disette d'œuf. ",
            "Entre Mars et avril, on va de la table au lit. ",
            "Taille tôt, taille tard, rien ne vaut la taille de Mars. ",
            "La vigne dit : en Mars, me lie, en Mars, me taille, en Mars, il faut qu'on me travaille. ",
            "Quand Mars fait avril, avril fait Mars. ",
            "Quand Mars se déguise en été, avril prend ses habits fourrés. ",
            "En Mars, autant de gelées, en avril autant de poussées. ",
            "Mars aride, avril humide. ",
            "Mars venteux, avril pluvieux, mai soleilleux, font le paysan orgueilleux. ",
            "Hâle de Mars, pluie d'avril, rosée de mai, emplissent le grenier. "
         },
         {        // AVRIL
            "Pluie en Avril, belle moisson d'été. ",
            "Avril mou, rend l'usurier fou. ",
            "Pluie d'Avril vaut purin de brebis. ",
            "Pluie d'Avril emplit le fenil. ",
            "Quand Avril est froid et pluvieux, les moissons n'en vont que mieux. ",
            "Avril frais et rousineux, rend toujours l'an plantureux. ",
            "S'il tonne alors que l'aubépine est encore dénudée, il y aura peu de lait. ",
            "Quand il tonne au mois d'Avril, le laboureur se réjouit, mais la mouche et la brebis ont beaucoup à souffrir. ",
            "Avril a trente jours et s'il en pleuvait trente et un, ce serait grand bien pour chacun. ",
            "En Avril, ne te découvre pas d'un fil. ",
            "On n'est pas sorti de l'hiver qu'Avril n'ait montré son derrière. ",
            "Celui qui s'allège avant le mois de mai, certainement ne sait pas ce qu'il fait. ",
            "Il n'est si gentil mois d'Avril qui n'ait son manteau de grésil. ",
            "Il n'est point d'Avril si beau qui n'ait neige à son chapeau. ",
            "Fleur d'Avril tient par un fil. ",
            "Bourgeon qui pousse en Avril, met peu de vin dans le baril. ",
            "Bourgeon d'Avril emplit le baril. ",
            "Gelée d'Avril ou de mai, misère nous prédit au vrai. ",
            "Quand Avril se met en fureur, il est le pire des laboureurs. ",
            "Tout ce qui pousse en mars, Avril le mange. ",
            "En Avril, si la gelée vient, elle apporte pain et vin."
         },
         {        // MAI
            "Au mois de Mai la chaleur de tout l'an en fait la valeur. ",
            "À la mi-Mai, queue de l'hiver. ",
            "Eau de Mai, c'est du pain pour toute l'année. ",
            "En Mai, fais ce qu'il te plaît. ",
            "Mai chaud emplit la grange et le portai. ",
            "Chaleur de Mai verdit la haie. ",
            "Fraîcheur de Mai, fèves fleuries, pain dans la maie, et des folies. ",
            "Mai frais et venteux fait l'an plantureux. ",
            "Froid Mai et chaud juin donnent pain et vin. ",
            "Fraîcheur et rosée de Mai, vin à la vigne et foin au pré. ",
            "Plus Mai est chaud, plus l'an vaut. ",
            "Mai sec, année propice. ",
            "Au mois de Mai, il faudrait qu'il ne plût jamais. ",
            "Mai en rosée abondant rend le paysan content. ",
            "En Mai rosée, en mars grésil, pluie abondante au mois d'avril, le laboureur est content plus que ne feraient cinquante écus. ",
            "La rosée du mois de Mai rend le cœur du laboureur gai. ",
            "Rosée et fraîcheur de Mai donnent vin à la vigne et foin au pré. ",
            "Avril pluvieux et Mai venteux, ne font pas l'an disetteux. ",
            "Quand il tonne en Mai, vaches ont du lait. ",
            "Mai froid n'enrichit personne, mais il est excellent quand il tonne. ",
            "Brouillard de Mai, chaleur de juin, amènent la moisson à point. ",
            "Pendant le joli mois de Mai, couvre-toi plus que jamais. ",
            "Au mois de Mai, manteau jeté. "
         },
         {        // JUIN
            "Juin froid et pluvieux, tout l'an sera grincheux. ",
            "Juin gris et orageux, novembre neigeux. ",
            "Prépare autant de tonneaux, qu'en Juin tu compteras de jours beaux. ",
            "C'est le mois de Juin qui fait le foin. ",
            "Beau mois de Juin change l'herbe rare en beau foin. ",
            "En bon Juin, mauvaise herbe donne beau foin. ",
            "Un pré est bien vaurien quand en Juin il ne donne rien. ",
            "Beau temps en Juin, abondance de grain. ",
            "Beau temps avant la Saint-Jean, beau grain pour l'an. ",
            "En Juin beau soleil qui donne n'a jamais ruiné personne. ",
            "Juin bien fleuri, vrai paradis. ",
            "Juin pluvieux vide celliers et greniers. ",
            "Eau de Juin ruine le moulin. ",
            "Temps trop humide en Juin au paysan est grand chagrin. ",
            "Pluie de Juin fait belle avoine et maigre grain. ",
            "Juin larmoyeux rend le paysan joyeux. ",
            "De Juin vent du soir, pour le grain est de bon espoir. ",
            "En Juin, c'est la saison de tondre brebis et moutons. ",
            "Si Juin fait la quantité, Septembre fait la qualité. ",
            "Qui en Juin se porte bien, au temps chaud ne craindra rien."
         },
         {        // JUILLET
            "Le soleil de juillet travaille pour deux. ",
            "Juillet ensoleillé remplit cave et grenier. ",
            "Si juillet est beau, prépare tes tonneaux. ",
            "Souvent juillet orageux annonce hiver rigoureux. ",
            "Soleil de juillet donne la fortune. ",
            "Juillet sans orage, famine au village. ",
            "Juillet doit rôtir ce que septembre mûrira. ",
            "Pluie de juillet, eau en panier. ",
            "Pluie de juillet, eau en janvier. ",
            "Tel juillet, tel janvier. ",
            "Quand en juillet, fourmi rehausse sa maison, l'hiver sera dur et long."
         },
         {        // AOUT
            "S'il tonne en Août, grande prospérité partout, mais des malades beaucoup.",
            "Un mois d'Août sans rosée donne une mauvaise année.",
            "Autant de brouillards en Août, autant de déluges dans l'année.",
            "En Juillet comme en Août, ni femmes ni choux.",
            "Qui dort en Août, dort à son coût.",
            "Quand il pleut en Août, il pleut miel et bon moût.",
            "Quand Août est pluvieux, Septembre est radieux.",
            "Tonnerre d'Août, belle vendange et bon moût.",
            "Ce qu'Août ne cuira, Septembre ne le rôtira.",
            "À tonnerre d'Août, grosses grappes et bon moût.",
            "Temps sec et vent en Août, donneront en hiver des froids de loup.",
            "Août sec et gros nuages en l'air, donneront de la neige en hiver.",
            "Temps trop beau en Août annonce un hiver en courroux.",
            "C'est le mois d'Août qui réchauffe tout.",
            "Si le mois d'Août est beau, c'est signe que l'hiver sera bon.",
            "Soleil rouge en Août, c'est de la pluie partout.",
            "Jamais d'Août la sécheresse n'amènera la richesse.",
            "Chaleur d'Août, c'est du bien partout.",
            "Quand Août est bon, abondance à la maison."
         },
         {        // SEPTEMBRE
            "Si Juin fait la quantité, Septembre fait la qualité. ",
            "Lorsque beaucoup d'étoiles filent en Septembre, les tonneaux sont alors trop petits en Novembre. ",
            "En Septembre, si trois jours tonne, c'est un nouveau bail pour l'automne. ",
            "Septembre emporte les ponts ou tarit les fonts. ",
            "Pluie de Septembre, joie du paysan. ",
            "Pluie de Septembre est bonne à semailles et à vigne. ",
            "En Septembre, pluie fine est bonne pour la vigne. ",
            "Septembre humide, pas de tonneau vide. ",
            "Orages de Septembre, neiges de Décembre. ",
            "S'il tonne encore en Septembre, la neige sera haute. ",
            "Septembre est le mai de l'automne. ",
            "Septembre de noix, hiver froid. ",
            "À Septembre chaud, Octobre mouillé. ",
            "Beau Septembre, Octobre maussade. "
         },
         {        // OCTOBRE
            "Octobre et Mars se ressemblent. ",
            "Octobre est bon s'il est de saison. ",
            "Brouillard d'Octobre et pluvieux Novembre font bon Décembre. ",
            "Si Octobre est chaud, Février sera froid. ",
            "Si Octobre est trop chaud, en Février, la glace est au carreau. ",
            "En Octobre, qui ne fume rien, ne récolte rien. ",
            "Octobre ensoleillé, décembre emmitouflé. ",
            "Octobre en bruine, hiver en ruines. ",
            "Un Octobre en brumes est un mois à rhumes. ",
            "Gelée d'Octobre, rend le vigneron sobre. ",
            "Tonnerre d'Octobre, vendanges peu sobres. ",
            "En Octobre tonnerre, vendanges prospères. ",
            "Quand Octobre prend sa fin, dans la cuve est le raisin. ",
            "Octobre glacé, fait vermine trépasser. ",
            "Octobre au coin du feu, fait venir le leu. ",
            "Si Octobre s'emplit de vents, du froid tu pâtiras longtemps. ",
            "Octobre le vaillant, surmène le paysan. ",
            "Beaucoup de pluie en Octobre, beaucoup de vent en Décembre. ",
            "Automne en fleur, hiver plein de rigueur."
         },
         {        // NOVEMBRE
            "Novembre chaud au début, froid à la fin.",
            "Le mois des brumes réchauffe par-devant et refroidit par-derrière.",
            "En Novembre, s'il tonne, l'année sera bonne.",
            "Tonnerre de Novembre remplit le grenier.",
            "Quand en Novembre, il a tonné, l'hiver est avorté.",
            "Quand il gèle en Novembre, l'hiver part comme tendre.",
            "Brouillard en Novembre, l'hiver sera tendre.",
            "Le vent de Novembre arrache la dernière feuille."
         },
         {        // DECEMBRE
            "Si l'hiver ne fait pas son devoir aux mois de Décembre ou Janvier, au plus tard, il se fera voir le 2 Février.",
            "Tonnerre de Décembre promet beaucoup de vent pour l'année suivante.",
            "La neige de l'Avent ne dure pas longtemps.",
            "En Décembre, temps neigeux, Janvier bon ni pour les jeunes ni pour les vieux, mauvaise année pour les laboureux, pas de violon, mariages peu nombreux.",
            "Quand Décembre est froid, quand la neige tombe, en année féconde, tu peux avoir foi.",
            "En Décembre froid, laboureur a foi.",
            "Décembre de froid chiche, ne rend pas le paysan riche.",
            "Froid et neige en Décembre, du blé à revendre.",
            "Si Décembre est sous la neige, la récolte elle protège.",
            "Décembre aux pieds blancs s'en vient ; an de neige est an de bien.",
            "Neige de Décembre est engrais pour la terre.",
            "Neige avant Noël, fumier pour le seigle.",
            "S'il tonne en Décembre, l'hiver sera manqué.",
            "Quand en Décembre, il a tonné, l'hiver est avorté.",
            "Décembre trop beau, été dans l'eau.",
            "Décembre prend, il ne rend.",
            "En Décembre fais du bois et endors-toi."
         }
      };

   private static String[][] dictonsJour =
      {
         {        // JANVIER
            "Jour de l'an beau, mois d'août très chaud.",   // 01
            "Les douze premiers jours de janvier indiquent le temps qu'il fera les douze mois de l'année.",   // 02
            "Neige en janvier vaut du fumier.",   // 03
            "Mois de janvier laisse la terre se reposer.",   // 04
            "A la Saint-Gerlac, temps froid et serein, l'année sera bonne, c'est certain.",   // 05
            "Si le jour des Rois, beaucoup d'étoiles tu vois, auras sécheresse en étét, et beaucoup d'oeufs au poulailler.",   // 06
            "S'il gèle à la Saint-Raymond, l'hiver sera encore long.",   // 07
            "Temps de Lucien, temps de chien.\nAu jour de Sainte-Gudule, Le jour croît, mais le froid ne recule.",   // 08
            "A la Bienheureuse Alix claire journée, nous annonce une bonne année.\nSaint-Julien brise la glace, s'il ne la brise, c'est qu'il l'embrasse.",   // 09
            "Beau temps à la Saint-Guillaume, donne plus de blé que de chaume.",   // 10
            "Si janvier ne prend son manteau, malheur aux arbres, aux moissons, aux corbeaux.",   // 11
            "Neige au blé est bénéfice, comme à un vieillard la pelisse.",   // 12
            "Mieux vaut chien enragé, que chaud soleil en janvier.",   // 13
            "Soleil de Sainte-Nina, pour un long hiver rentre ton bois.",   // 14
            "Si tu vois l'herbe en janvier, serre ton grain dans ton grenier.\nS'il gèle à la Saint-Maur, la moitié de l'hiver est dehors.",   // 15
            "Prépare pour Saint-Marcel, tes graines nouvelles.",   // 16
            "Si Saint-Antoine a la barbe blanche, il y aura beaucoup de pommes de terre.",   // 17
            "Neige à Sainte-Prisca, la belle année que voilà.",   // 18
            "Pie dans la ferme, neige à court terme.",   // 19
            "Quand Sébastien gèle le matin, mauvaise herbe ne revient.",   // 20
            "A la Sainte-Agnès, soins des plantes ne laisse.",   // 21
            "Saint-Vincent clair et beau, plus de vin que d'eau.\nA la Saint-Vincnet, la sève monte dans les sarments.",   // 22
            "Saint-Barnard ensoleillé, rend le vigneron gai.",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // FEVRIER
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // MARS
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // AVRIL
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // MAI
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // JUIN
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // JUILLET
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // AOUT
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "A Saint-Privat, pour les labours, donne du jarret à ton cheval.",   // 26
            "Qui pluie demande à Saint-Ebbon, n'est pas laissé à l'abandon.",   // 27
            "A Saint-Augustin, tout va bien.\nA la Saint-Augustin, le soleil a grillé le serpolet et le thym.",   // 28
            "Pluie à la Sainte-Sabine, est une grâce divine.",   // 29
            "A la Saint-Fiacre, soleil ardent, pour huit jours encore du beau temps.",   // 30
            "Saint-Aristide récolte fruits et légumes pour remplir caves et greniers avant que terre ne fume."    // 31
         },
         {        // SEPTEMBRE
            "S'il fait beau à la Saint-Gilles, Cela durera jusqu'à la Saint-Michel.\nA la Saint-Loup, la lampe au clou.",   // 01
            "En septembre, le feignant peut aller se pendre.",   // 02
            "Pluie du jour à la Saint-Grégoire, autant de vin de plus à boire.\nSeptembre humide, pas de tonneau vide.\nA la Saint-Mansuy, les veillées au pays.",   // 03
            "Orages de septembre, neiges de décembre.",   // 04
            "Du premier au huit, l'hirondelle fuit.",   // 05
            "Lorsque la sève baisse à la Saint-Bertrand, l'hiver montre les dents.\nA la Saint-Onésiphore, fini les doryphores !\nA la Saint-Onésiphore, la sève s'endort.",   // 06
            "A la Sainte-Reine, sème tes graines.\nA la Saint-Cloud, sème ton blé, car ce jour vaut du fumier.",   // 07
            "A la bonne Dame de septembre, tout fruit est bon à prendre.",   // 08
            "Quand Alain tonne, la vendange est bonne.",   // 09
            "A la Sainte-Inès, travaille sans cesse.",   // 10
            "Tu peux semer sans crainte, quand arrive la Saint-Hyacinthe.",   // 11
            "A la Sainte-Aubierge, vole le fil de la Vierge.",   // 12
            "A Saint-Aimé, point de moutons affamés.",   // 13
            "A la Sainte-Croix, cueille tes pommes et gaule tes noix.",   // 14
            "Saint-Roland aux champs, Saint-Tanguy à l'écurie.",   // 15
            "A la Sainte-Edith, la pluie n'est pas maudite.",   // 16
            "Saint-Lambert pluvieux, neuf jours dangereux.",   // 17
            "Froid de la Sainte-Nadège, annonce pour bientôt la neige.",   // 18
            "Qui sème à la Saint-Janvier, de l'an récolte le premier.\nA Sainte-Emilie, luzerne à pleine faucille.\nA Saint-Janvier, les chrysanthèmes repoussent du pied.",   // 19
            "Gelée blanche de Saint-Eustache, grossit le raisin qui tache.",   // 20
            "A la Saint-Matthieu, cueille le raisin si tu veux.\nA la Saint-Matthieu, les jours sont égaux aux nuits dans leur cours.\nQuand vient Saint-Matthieu, l'été, adieu !",   // 21
            "Semis de Saint-Maurice, récolte à ton caprice.\nAutomne en fleurs, hiver plein de rigueurs.",   // 22
            "Brouillards de Constant d'automne, beau temps nous donnent.",   // 23
            "A la Saint-Gérard, les noix sont mûres pour toi et pour moi.",   // 24
            "A la Saint-Firmin, on attrape les mouches à la main.\nA la Saint-Firmin, l'hiver est en chemin.",   // 25
            "A Sainte-Justine, toute fleur s'incline.\nA la Saint-Damien, on trouve des noix pleins les chemins.",   // 26
            "Saint-Vincent-de-Paul trouble, met du vin dans la gourde.\nMois de septembre pluvieux, an fructueux.",   // 27
            "Pour vendanger, il faut attendre au moins Venceslas de septembre.",   // 28
            "A la Saint-Michel, cueille ton fruit tel quel.\nA la Saint-Michel, goûte ton miel !\nDe Saint-Michel à la Toussaint, laboure grand train.\nQuand les hirondelles voient la Saint-Michel, l'hiver ne vient qu'à Noël.",   // 29
            "A la Saint-Jérôme, hoche tes pommes.\nA la Saint-Jérôme venue, sors ta charrue.",   // 30
         },
         {        // OCTOBRE
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // NOVEMBRE
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
         {        // DECEMBRE
            "",   // 01
            "",   // 02
            "",   // 03
            "",   // 04
            "",   // 05
            "",   // 06
            "",   // 07
            "",   // 08
            "",   // 09
            "",   // 10
            "",   // 11
            "",   // 12
            "",   // 13
            "",   // 14
            "",   // 15
            "",   // 16
            "",   // 17
            "",   // 18
            "",   // 19
            "",   // 20
            "",   // 21
            "",   // 22
            "",   // 23
            "",   // 24
            "",   // 25
            "",   // 26
            "",   // 27
            "",   // 28
            "",   // 29
            "",   // 30
            ""    // 31
         },
      };

//******************************************************************************
// get()
//******************************************************************************
   public static String get(int mois, int jour)
   {
      return dictonsJour[mois - 1][jour - 1];
   }

   public static String get(int mois, long seed)
   {
      return dictonsMois[mois - 1][new Random(seed).nextInt(dictonsMois[mois - 1].length)];
   }
}


