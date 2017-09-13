package fr.ozf.fmr;

public class fmrSaint
{
//******************************************************************************
// getSaint()
//******************************************************************************
   public static String getSaint(fmrDate d)
   {
      int day = d.Day;
      int month = d.Month;

      String saint = "";
      switch (month)
      {
         case 1:
            switch (day)
            {
               case 1:
                  saint = "Jour de l'an";
                  break;
               case 2:
                  saint = "Saint Basile";
                  break;
               case 3:
                  saint = "Sainte Geneviève";
                  break;
               case 4:
                  saint = "Saint Odilon";
                  break;
               case 5:
                  saint = "Saint Edouard";
                  break;
               case 6:
                  saint = "Sainte Mélanie";
                  break;
               case 7:
                  saint = "Saint Raymond";
                  break;
               case 8:
                  saint = "Saint Lucien";
                  break;
               case 9:
                  saint = "Sainte Alix";
                  break;
               case 10:
                  saint = "Saint Guillaume";
                  break;
               case 11:
                  saint = "Saint Paulin";
                  break;
               case 12:
                  saint = "Sainte Tatiana";
                  break;
               case 13:
                  saint = "Sainte Yvette";
                  break;
               case 14:
                  saint = "Sainte Nina";
                  break;
               case 15:
                  saint = "Saint Rémi";
                  break;
               case 16:
                  saint = "Saint Marcel";
                  break;
               case 17:
                  saint = "Sainte Roseline";
                  break;
               case 18:
                  saint = "Sainte Prisca";
                  break;
               case 19:
                  saint = "Saint Marius";
                  break;
               case 20:
                  saint = "Saint Sébastien";
                  break;
               case 21:
                  saint = "Sainte Agnès";
                  break;
               case 22:
                  saint = "Saint Vincent";
                  break;
               case 23:
                  saint = "Saint Barnard";
                  break;
               case 24:
                  saint = "Saint François";
                  break;
               case 25:
                  saint = "Conv de Saint Paul";
                  break;
               case 26:
                  saint = "Saint Timothée";
                  break;
               case 27:
                  saint = "Sainte Angèle";
                  break;
               case 28:
                  saint = "Saint Thomas d'Aquin";
                  break;
               case 29:
                  saint = "Saint Gildas";
                  break;
               case 30:
                  saint = "Sainte Martine";
                  break;
               case 31:
                  saint = "Sainte Marcelle";
                  break;
            }
            break;
         case 2:
            switch (day)
            {
               case 1:
                  saint = "Sainte Ella";
                  break;
               case 2:
                  saint = "Présentation";
                  break;
               case 3:
                  saint = "Saint Blaise";
                  break;
               case 4:
                  saint = "Sainte Véronique";
                  break;
               case 5:
                  saint = "Sainte Agathe";
                  break;
               case 6:
                  saint = "Saint Gaston";
                  break;
               case 7:
                  saint = "Sainte Eugénie";
                  break;
               case 8:
                  saint = "Sainte Jacqueline";
                  break;
               case 9:
                  saint = "Sainte Apolline";
                  break;
               case 10:
                  saint = "Saint Arnaud";
                  break;
               case 11:
                  saint = "N-D de Lourdes";
                  break;
               case 12:
                  saint = "Saint Félix";
                  break;
               case 13:
                  saint = "Sainte Béatrice";
                  break;
               case 14:
                  saint = "Saint Valentin";
                  break;
               case 15:
                  saint = "Saint Claude";
                  break;
               case 16:
                  saint = "Sainte Julienne";
                  break;
               case 17:
                  saint = "Saint Alexis";
                  break;
               case 18:
                  saint = "Sainte Bernadette";
                  break;
               case 19:
                  saint = "Saint Gabin";
                  break;
               case 20:
                  saint = "Sainte Aimée";
                  break;
               case 21:
                  saint = "Saint Damien";
                  break;
               case 22:
                  saint = "Sainte Isabelle";
                  break;
               case 23:
                  saint = "Saint Lazare";
                  break;
               case 24:
                  saint = "Saint Modeste";
                  break;
               case 25:
                  saint = "Saint Roméo";
                  break;
               case 26:
                  saint = "Saint Nestor";
                  break;
               case 27:
                  saint = "Sainte Honorine";
                  break;
               case 28:
                  saint = "Saint Romain";
                  break;
               case 29:
                  saint = "Saint Auguste";
                  break;
            }
            break;
         case 3:
            switch (day)
            {
               case 1:
                  saint = "Saint Aubin";
                  break;
               case 2:
                  saint = "Saint Charles";
                  break;
               case 3:
                  saint = "Saint Guénolé";
                  break;
               case 4:
                  saint = "Saint Casimir";
                  break;
               case 5:
                  saint = "Sainte Olive";
                  break;
               case 6:
                  saint = "Sainte Colette";
                  break;
               case 7:
                  saint = "Sainte Félicité";
                  break;
               case 8:
                  saint = "Saint Jean de Dieu";
                  break;
               case 9:
                  saint = "Sainte Françoise";
                  break;
               case 10:
                  saint = "Saint Vivien";
                  break;
               case 11:
                  saint = "Sainte Rosine";
                  break;
               case 12:
                  saint = "Sainte Justine";
                  break;
               case 13:
                  saint = "Saint Rodrigue";
                  break;
               case 14:
                  saint = "Sainte Mathilde";
                  break;
               case 15:
                  saint = "Sainte Louise";
                  break;
               case 16:
                  saint = "Sainte Bénedicte";
                  break;
               case 17:
                  saint = "Saint Patrice";
                  break;
               case 18:
                  saint = "Saint Cyrille";
                  break;
               case 19:
                  saint = "Saint Joseph";
                  break;
               case 20:
                  saint = "Saint Herbert";
                  break;
               case 21:
                  saint = "Sainte Clémence";
                  break;
               case 22:
                  saint = "Sainte Léa";
                  break;
               case 23:
                  saint = "Saint Victorien";
                  break;
               case 24:
                  saint = "Sainte Catherine de Suède";
                  break;
               case 25:
                  saint = "Annonciation";
                  break;
               case 26:
                  saint = "Sainte Larissa";
                  break;
               case 27:
                  saint = "Saint Habib";
                  break;
               case 28:
                  saint = "Saint Gontran";
                  break;
               case 29:
                  saint = "Sainte Gwladys";
                  break;
               case 30:
                  saint = "Saint Amedée";
                  break;
               case 31:
                  saint = "Saint Benjamin";
                  break;
            }
            break;
         case 4:
            switch (day)
            {

               case 1:
                  saint = "Saint Hughes";
                  break;
               case 2:
                  saint = "Sainte Sandrine";
                  break;
               case 3:
                  saint = "Saint Richard";
                  break;
               case 4:
                  saint = "Saint Isidore";
                  break;
               case 5:
                  saint = "Sainte Irène";
                  break;
               case 6:
                  saint = "Saint Marcellin";
                  break;
               case 7:
                  saint = "Saint J-B de la Salle";
                  break;
               case 8:
                  saint = "Sainte Julie";
                  break;
               case 9:
                  saint = "Saint Gautier";
                  break;
               case 10:
                  saint = "Saint Fulbert";
                  break;
               case 11:
                  saint = "Saint Stanislas";
                  break;
               case 12:
                  saint = "Saint Jules";
                  break;
               case 13:
                  saint = "Sainte Ida";
                  break;
               case 14:
                  saint = "Saint Maxime";
                  break;
               case 15:
                  saint = "Saint Paterne";
                  break;
               case 16:
                  saint = "Saint Benoit-Joseph";
                  break;
               case 17:
                  saint = "Saint Anicet";
                  break;
               case 18:
                  saint = "Saint Parfait";
                  break;
               case 19:
                  saint = "Sainte Emma";
                  break;
               case 20:
                  saint = "Sainte Odette";
                  break;
               case 21:
                  saint = "Saint Anselme";
                  break;
               case 22:
                  saint = "Saint Alexandre";
                  break;
               case 23:
                  saint = "Saint Georges";
                  break;
               case 24:
                  saint = "Saint Fidèle";
                  break;
               case 25:
                  saint = "Saint Marc";
                  break;
               case 26:
                  saint = "Sainte Alida";
                  break;
               case 27:
                  saint = "Sainte Zita";
                  break;
               case 28:
                  saint = "Sainte Valérie";
                  break;
               case 29:
                  saint = "Sainte Cath. de Sienne";
                  break;
               case 30:
                  saint = "Saint Robert";
                  break;
            }
            break;
         case 5:
            switch (day)
            {

               case 1:
                  saint = "Fête du Travail";
                  break;
               case 2:
                  saint = "Saint Boris";
                  break;
               case 3:
                  saint = "SS Philippe Jacques";
                  break;
               case 4:
                  saint = "Saint Sylvain";
                  break;
               case 5:
                  saint = "Sainte Judith";
                  break;
               case 6:
                  saint = "Sainte Prudence";
                  break;
               case 7:
                  saint = "Sainte Gisèle";
                  break;
               case 8:
                  saint = "Victoire 1945";
                  break;
               case 9:
                  saint = "Sainte Pacôme";
                  break;
               case 10:
                  saint = "Sainte Solange";
                  break;
               case 11:
                  saint = "Sainte Estelle";
                  break;
               case 12:
                  saint = "Saint Achille";
                  break;
               case 13:
                  saint = "Sainte Rolande";
                  break;
               case 14:
                  saint = "Saint Mathias";
                  break;
               case 15:
                  saint = "Sainte Denise";
                  break;
               case 16:
                  saint = "Saint Honoré";
                  break;
               case 17:
                  saint = "Saint Pascal";
                  break;
               case 18:
                  saint = "Saint Eric";
                  break;
               case 19:
                  saint = "Saint Yves";
                  break;
               case 20:
                  saint = "Saint Bernardin";
                  break;
               case 21:
                  saint = "Saint Constantin";
                  break;
               case 22:
                  saint = "Saint Emile";
                  break;
               case 23:
                  saint = "Saint Didier";
                  break;
               case 24:
                  saint = "Saint Donatien";
                  break;
               case 25:
                  saint = "Sainte Sophie";
                  break;
               case 26:
                  saint = "Saint Béranger";
                  break;
               case 27:
                  saint = "Saint Augustin";
                  break;
               case 28:
                  saint = "Saint Germain";
                  break;
               case 29:
                  saint = "Saint Aymard";
                  break;
               case 30:
                  saint = "Saint Ferdinand";
                  break;
               case 31:
                  saint = "Visitation";
                  break;
            }
            break;
         case 6:
            switch (day)
            {
               case 1:
                  saint = "Saint Justin";
                  break;
               case 2:
                  saint = "Sainte Blandine";
                  break;
               case 3:
                  saint = "Saint Kevin";
                  break;
               case 4:
                  saint = "Sainte Clotilde";
                  break;
               case 5:
                  saint = "Saint Igor";
                  break;
               case 6:
                  saint = "Saint Norbert";
                  break;
               case 7:
                  saint = "Saint Gilbert";
                  break;
               case 8:
                  saint = "Saint Médard";
                  break;
               case 9:
                  saint = "Sainte Diane";
                  break;
               case 10:
                  saint = "Saint Landry";
                  break;
               case 11:
                  saint = "Saint Barnabé";
                  break;
               case 12:
                  saint = "Saint Guy";
                  break;
               case 13:
                  saint = "Saint Antoine de P.";
                  break;
               case 14:
                  saint = "Saint Elisée";
                  break;
               case 15:
                  saint = "Sainte Germaine";
                  break;
               case 16:
                  saint = "Saint J-F Régis";
                  break;
               case 17:
                  saint = "Saint Hervé";
                  break;
               case 18:
                  saint = "Saint Léonce";
                  break;
               case 19:
                  saint = "Saint Romuald";
                  break;
               case 20:
                  saint = "Saint Silvère";
                  break;
               case 21:
                  saint = "Eté";
                  break;
               case 22:
                  saint = "Saint Alban";
                  break;
               case 23:
                  saint = "Sainte Audrey";
                  break;
               case 24:
                  saint = "Saint Jean-Baptiste";
                  break;
               case 25:
                  saint = "Saint Prosper";
                  break;
               case 26:
                  saint = "Saint Anthelme";
                  break;
               case 27:
                  saint = "Saint Fernand";
                  break;
               case 28:
                  saint = "Saint Irenée";
                  break;
               case 29:
                  saint = "SS Pierre: Saint Paul";
                  break;
               case 30:
                  saint = "Saint Martial";
                  break;
            }
            break;
         case 7:
            switch (day)
            {
               case 1:
                  saint = "Saint Thierry";
                  break;
               case 2:
                  saint = "Saint Martinien";
                  break;
               case 3:
                  saint = "Saint Thomas";
                  break;
               case 4:
                  saint = "Saint Florent";
                  break;
               case 5:
                  saint = "Saint Antoine-Marie";
                  break;
               case 6:
                  saint = "Sainte Mariette";
                  break;
               case 7:
                  saint = "Saint Raoul";
                  break;
               case 8:
                  saint = "Saint Thibaut";
                  break;
               case 9:
                  saint = "Sainte Amandine";
                  break;
               case 10:
                  saint = "Saint Ulrich";
                  break;
               case 11:
                  saint = "Saint Benoît";
                  break;
               case 12:
                  saint = "Saint Olivier";
                  break;
               case 13:
                  saint = "SS Henri et Joël";
                  break;
               case 14:
                  saint = "Fête Nationale";
                  break;
               case 15:
                  saint = "Saint Donald";
                  break;
               case 16:
                  saint = "N-D du Mt-Carmel";
                  break;
               case 17:
                  saint = "Sainte Charlotte";
                  break;
               case 18:
                  saint = "Saint Frédéric";
                  break;
               case 19:
                  saint = "Saint Arsène";
                  break;
               case 20:
                  saint = "Sainte Marina";
                  break;
               case 21:
                  saint = "Saint Victor";
                  break;
               case 22:
                  saint = "Sainte Marie-Madeleine";
                  break;
               case 23:
                  saint = "Sainte Brigitte";
                  break;
               case 24:
                  saint = "Sainte Christine";
                  break;
               case 25:
                  saint = "Saint Jacques";
                  break;
               case 26:
                  saint = "SS Anne et Joachim";
                  break;
               case 27:
                  saint = "Sainte Nathalie";
                  break;
               case 28:
                  saint = "Saint Samson";
                  break;
               case 29:
                  saint = "Sainte Marthe";
                  break;
               case 30:
                  saint = "Sainte Juliette";
                  break;
               case 31:
                  saint = "Saint Ignace de Loyola";
                  break;
            }
            break;
         case 8:
            switch (day)
            {
               case 1:
                  saint = "Saint Alphonse";
                  break;
               case 2:
                  saint = "Saint Julien Eymard";
                  break;
               case 3:
                  saint = "Sainte Lydie";
                  break;
               case 4:
                  saint = "Saint J-M Vianney";
                  break;
               case 5:
                  saint = "Saint Abel";
                  break;
               case 6:
                  saint = "Transfiguration";
                  break;
               case 7:
                  saint = "Saint Gaëtan";
                  break;
               case 8:
                  saint = "Saint Dominique";
                  break;
               case 9:
                  saint = "Saint Amour";
                  break;
               case 10:
                  saint = "Saint Laurent";
                  break;
               case 11:
                  saint = "Sainte Claire";
                  break;
               case 12:
                  saint = "Sainte Clarisse";
                  break;
               case 13:
                  saint = "Saint Hippolyte";
                  break;
               case 14:
                  saint = "Saint Evrard";
                  break;
               case 15:
                  saint = "Assomption";
                  break;
               case 16:
                  saint = "Saint Armel";
                  break;
               case 17:
                  saint = "Saint Hyacinthe";
                  break;
               case 18:
                  saint = "Sainte Hélène";
                  break;
               case 19:
                  saint = "Saint Jean Eudes";
                  break;
               case 20:
                  saint = "Saint Bernard";
                  break;
               case 21:
                  saint = "Saint Christophe";
                  break;
               case 22:
                  saint = "Saint Fabrice";
                  break;
               case 23:
                  saint = "Sainte Rose de Lima";
                  break;
               case 24:
                  saint = "Saint Barthélémy";
                  break;
               case 25:
                  saint = "Saint Louis";
                  break;
               case 26:
                  saint = "Sainte Natacha";
                  break;
               case 27:
                  saint = "Sainte Monique";
                  break;
               case 28:
                  saint = "Saint Augustin";
                  break;
               case 29:
                  saint = "Sainte Sabine";
                  break;
               case 30:
                  saint = "Saint Fiacre";
                  break;
               case 31:
                  saint = "Saint Aristide";
                  break;
            }
            break;
         case 9:
            switch (day)
            {
               case 1:
                  saint = "Saint Gilles";
                  break;
               case 2:
                  saint = "Sainte Ingrid";
                  break;
               case 3:
                  saint = "Saint Grégoire";
                  break;
               case 4:
                  saint = "Sainte Rosalie";
                  break;
               case 5:
                  saint = "Sainte Raïssa";
                  break;
               case 6:
                  saint = "Saint Bertrand";
                  break;
               case 7:
                  saint = "Sainte Reine";
                  break;
               case 8:
                  saint = "Nativité de N-D";
                  break;
               case 9:
                  saint = "Saint Alain";
                  break;
               case 10:
                  saint = "Sainte Inès";
                  break;
               case 11:
                  saint = "Saint Adolphe";
                  break;
               case 12:
                  saint = "Saint Apollinaire";
                  break;
               case 13:
                  saint = "Saint Aimé";
                  break;
               case 14:
                  saint = "La Sainte-Croix";
                  break;
               case 15:
                  saint = "Saint Roland";
                  break;
               case 16:
                  saint = "Sainte Edith";
                  break;
               case 17:
                  saint = "Saint Renaud";
                  break;
               case 18:
                  saint = "Sainte Nadège";
                  break;
               case 19:
                  saint = "Sainte Emilie";
                  break;
               case 20:
                  saint = "Saint Davy";
                  break;
               case 21:
                  saint = "Saint Matthieu";
                  break;
               case 22:
                  saint = "Saint Maurice";
                  break;
               case 23:
                  saint = "Saint Constant";
                  break;
               case 24:
                  saint = "Saint Thècle";
                  break;
               case 25:
                  saint = "Saint Hermann";
                  break;
               case 26:
                  saint = "Saint Côme";
                  break;
               case 27:
                  saint = "Saint Vincent de Paul";
                  break;
               case 28:
                  saint = "Saint Venceslas";
                  break;
               case 29:
                  saint = "Saint Michel";
                  break;
               case 30:
                  saint = "Saint Jérome";
                  break;
            }
            break;
         case 10:
            switch (day)
            {
               case 1:
                  saint = "Sainte Thérèse de l'E-J";
                  break;
               case 2:
                  saint = "Saint Léger";
                  break;
               case 3:
                  saint = "Saint Gérard";
                  break;
               case 4:
                  saint = "Saint François d'Assise";
                  break;
               case 5:
                  saint = "Sainte Fleur";
                  break;
               case 6:
                  saint = "Saint Bruno";
                  break;
               case 7:
                  saint = "Saint Serge";
                  break;
               case 8:
                  saint = "Sainte Pélagie";
                  break;
               case 9:
                  saint = "Saint Denis";
                  break;
               case 10:
                  saint = "Saint Ghislain";
                  break;
               case 11:
                  saint = "Saint Firmin";
                  break;
               case 12:
                  saint = "Saint Wilfrid";
                  break;
               case 13:
                  saint = "Saint Géraud";
                  break;
               case 14:
                  saint = "Saint Juste";
                  break;
               case 15:
                  saint = "Sainte Thérèse d'Avila";
                  break;
               case 16:
                  saint = "Sainte Edwige";
                  break;
               case 17:
                  saint = "Saint Baudouin";
                  break;
               case 18:
                  saint = "Saint Luc";
                  break;
               case 19:
                  saint = "Saint René";
                  break;
               case 20:
                  saint = "Sainte Adeline";
                  break;
               case 21:
                  saint = "Sainte Céline";
                  break;
               case 22:
                  saint = "Sainte Elodie";
                  break;
               case 23:
                  saint = "Saint Jean de Capistran";
                  break;
               case 24:
                  saint = "Saint Florentin";
                  break;
               case 25:
                  saint = "Saint Crépin";
                  break;
               case 26:
                  saint = "Saint Dimitri";
                  break;
               case 27:
                  saint = "Sainte Emeline";
                  break;
               case 28:
                  saint = "SS Simon et Juda";
                  break;
               case 29:
                  saint = "Saint Narcisse";
                  break;
               case 30:
                  saint = "Saint Bienvenu";
                  break;
               case 31:
                  saint = "Saint Quentin";
                  break;
            }
            break;
         case 11:
            switch (day)
            {
               case 1:
                  saint = "Toussaint";
                  break;
               case 2:
                  saint = "Défunts";
                  break;
               case 3:
                  saint = "Saint Hubert";
                  break;
               case 4:
                  saint = "Saint Charles";
                  break;
               case 5:
                  saint = "Sainte Sylvie";
                  break;
               case 6:
                  saint = "Sainte Bertille";
                  break;
               case 7:
                  saint = "Sainte Carine";
                  break;
               case 8:
                  saint = "Saint Geoffroy";
                  break;
               case 9:
                  saint = "Saint Théodore";
                  break;
               case 10:
                  saint = "Saint Léon";
                  break;
               case 11:
                  saint = "Saint Martin";
                  break;
               case 12:
                  saint = "Saint Christian";
                  break;
               case 13:
                  saint = "Saint Brice";
                  break;
               case 14:
                  saint = "Saint Sidoine";
                  break;
               case 15:
                  saint = "Saint Albert";
                  break;
               case 16:
                  saint = "Sainte Marguerite";
                  break;
               case 17:
                  saint = "Sainte Elisabeth";
                  break;
               case 18:
                  saint = "Sainte Aude";
                  break;
               case 19:
                  saint = "Saint Tanguy";
                  break;
               case 20:
                  saint = "Saint Edmond";
                  break;
               case 21:
                  saint = "Prés. de Marie";
                  break;
               case 22:
                  saint = "Sainte Cécile";
                  break;
               case 23:
                  saint = "Saint Clément";
                  break;
               case 24:
                  saint = "Sainte Flora";
                  break;
               case 25:
                  saint = "Sainte Catherine";
                  break;
               case 26:
                  saint = "Sainte Delphine";
                  break;
               case 27:
                  saint = "Saint Séverin";
                  break;
               case 28:
                  saint = "Saint Jacques de la Marche";
                  break;
               case 29:
                  saint = "Saint Saturnin";
                  break;
               case 30:
                  saint = "Saint André";
                  break;
            }
            break;
         case 12:
            switch (day)
            {
               case 1:
                  saint = "Sainte Florence";
                  break;
               case 2:
                  saint = "Sainte Viviane";
                  break;
               case 3:
                  saint = "Saint Xavier";
                  break;
               case 4:
                  saint = "Sainte Barbara";
                  break;
               case 5:
                  saint = "Saint Gérald";
                  break;
               case 6:
                  saint = "Saint Nicolas";
                  break;
               case 7:
                  saint = "Saint Ambroise";
                  break;
               case 8:
                  saint = "Imm. Conception";
                  break;
               case 9:
                  saint = "Saint P. Fourier";
                  break;
               case 10:
                  saint = "Saint Romaric";
                  break;
               case 11:
                  saint = "Saint Daniel";
                  break;
               case 12:
                  saint = "Sainte Jeanne F.-C.";
                  break;
               case 13:
                  saint = "Sainte Lucie";
                  break;
               case 14:
                  saint = "Sainte Odile";
                  break;
               case 15:
                  saint = "Sainte Ninon";
                  break;
               case 16:
                  saint = "Sainte Alice";
                  break;
               case 17:
                  saint = "Saint Gaël";
                  break;
               case 18:
                  saint = "Saint Gatien";
                  break;
               case 19:
                  saint = "Saint Urbain";
                  break;
               case 20:
                  saint = "Saint Abraham";
                  break;
               case 21:
                  saint = "Saint P. Cenisius";
                  break;
               case 22:
                  saint = "Sainte Fr.-Xavière";
                  break;
               case 23:
                  saint = "Saint Armand";
                  break;
               case 24:
                  saint = "Sainte Adéle";
                  break;
               case 25:
                  saint = "Noël";
                  break;
               case 26:
                  saint = "Saint Etienne";
                  break;
               case 27:
                  saint = "Saint Jean";
                  break;
               case 28:
                  saint = "Saint Innocents";
                  break;
               case 29:
                  saint = "Saint David";
                  break;
               case 30:
                  saint = "Saint Roger";
                  break;
               case 31:
                  saint = "Saint Sylvestre";
                  break;
            }
            break;
      }
      return saint;
   }
}
