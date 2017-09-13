package fr.ozf.fmr;

public final class fmrConstellation
{
    public String Con;
    public String NameFR;
    public String NameLatinNom;
    public String NameLatinGen;
    public String NameEN;

    private static final fmrConstellation con[] =
    {
        new fmrConstellation("And", "Andromède", "Andromeda", "Andromedae", "Andromeda"),
        new fmrConstellation("Ant", "Machine pneumatique", "Antlia", "Antliae", "Air pump"),
        new fmrConstellation("Aps", "Oiseau de paradis", "Apus", "Apodis", "Bird of paradise"),
        new fmrConstellation("Aql", "Aigle", "Aquila", "Aquilae", "Eagle"),
        new fmrConstellation("Aqr", "Verseau", "Aquarius", "Aquarii", "Water carrier"),
        new fmrConstellation("Ara", "Autel", "Ara", "Arae", "Altar"),
        new fmrConstellation("Ari", "Bélier", "Aries", "Arietis", "Ram"),
        new fmrConstellation("Aur", "Cocher", "Auriga", "Aurigae", "Charioteer"),
        new fmrConstellation("Boo", "Bouvier", "Bootes", "Bootis", "Herdsman"),
        new fmrConstellation("Cae", "Burin", "Caelum", "Caeli", "Chisel"),
        new fmrConstellation("Cam", "Girafe", "Camelopardalis", "Camelopardalis", "Giraffe"),
        new fmrConstellation("Cap", "Capricorne", "Capricornus", "Capricorni", "Goat (Capricorn)"),
        new fmrConstellation("Car", "Carène", "Carina", "Carinae", "Keel"),
        new fmrConstellation("Cas", "Cassiopée", "Cassiopeia", "Cassiopeiae", "Cassiopeia"),
        new fmrConstellation("Cen", "Centaure", "Centaurus", "Centauri", "Centaur"),
        new fmrConstellation("Cep", "Cephée", "Cepheus", "Cephei", "Cepheus"),
        new fmrConstellation("Cet", "Baleine", "Cetus", "Ceti", "Whale"),
        new fmrConstellation("Cha", "Caméléon", "Chamaeleon", "Chamaeleontis", "Chameleon"),
        new fmrConstellation("Cir", "Compas", "Circinus", "Circinii", "Compasses"),
        new fmrConstellation("CMa", "Grand Chien", "Canis Major", "Canis Majoris", "Big Dog"),
        new fmrConstellation("CMi", "Petit Chien", "Canis Minor", "Canis Minoris", "Little Dog"),
        new fmrConstellation("Cnc", "Cancer", "Cancer", "Cancri", "Crab"),
        new fmrConstellation("Col", "Colombe", "Columba", "Columbae", "Dove"),
        new fmrConstellation("Com", "Chevelure de Bérénice", "Coma Berenices", "Comae Berenices", "Berenice's Hair"),
        new fmrConstellation("CrA", "Couronne australe", "Corona Australis", "Coronae Australis", "Southern Crown"),
        new fmrConstellation("CrB", "Couronne boréale", "Corona Borealis", "Coronae Borealis", "Northern Crown"),
        new fmrConstellation("Crt", "Coupe", "Crater", "Crateris", "Cup"),
        new fmrConstellation("Lib", "Balance", "Libra", "Librae", "Balance")
    };

//******************************************************************************
// fmrConstellation()
//******************************************************************************
    private fmrConstellation(String Con, String NameFR, String NameLatinNom, String NameLatinGen, String NameEN)
    {
        this.Con = Con;
        this.NameFR = NameFR;
        this.NameLatinNom = NameLatinNom;
        this.NameLatinGen = NameLatinGen;
        this.NameEN = NameEN;
    }

//******************************************************************************
// get()
//******************************************************************************
    public static fmrConstellation get(String Con)
    {
        fmrConstellation o = null;

        for (fmrConstellation i : con)
        {
            if (i.Con.equals(Con))
            {
                o = i;
                break;
            }
        }

        if (o == null)
        {
            throw new IndexOutOfBoundsException("\nConstellation [" + Con + "] is not known (not yet).");
        }
        return o;
    }

//******************************************************************************
// printInfos()
//******************************************************************************
    public static void printInfos(String Con)
    {
        fmrConstellation o = null;

        for (fmrConstellation i : con)
        {
            if (i.Con.equals(Con))
            {
                o = i;
                break;
            }
        }

        System.out.println("==============================");
        System.out.println(o.NameLatinNom + " (" + o.Con + ")");
        System.out.println("==============================");
        System.out.println(o.NameLatinGen + " (Latin Génitif)");
        System.out.println(o.NameFR + " (Français)");
        System.out.println(o.NameEN + " (English)");
        System.out.println("==============================");
        
        if (o == null)
        {
            throw new IndexOutOfBoundsException("\nConstellation [" + Con + "] is not known (yet).");
        }
    }

    public void printInfos()
    {
        System.out.println("==============================");
        System.out.println(this.NameLatinNom + " (" + this.Con + ")");
        System.out.println("==============================");
        System.out.println(this.NameLatinGen + " (Latin Génitif)");
        System.out.println(this.NameFR + " (Français)");
        System.out.println(this.NameEN + " (English)");
        System.out.println("==============================");
    }
}

/*

Ordre
alpha.	Nom français	Nom latin
(Nominatif)	Nom latin
(génitif)	Abréviation	Éphéméride
zodiacale
2	Andromède	Andromeda	Andromedae	And	sans objet
52	Machine pneumatique	Antlia	Antliae	Ant	sans objet
56	Oiseau de paradis	Apus	Apodis	Aps	sans objet
1	Aigle	Aquila	Aquilae	Aql	sans objet
86	Verseau	Aquarius	Aquarii	Aqr	16 février - 11 mars (en 2008)
3	Autel	Ara	Arae	Ara	sans objet
6	Bélier	Aries	Arietis	Ari	18 avril - 13 mai (en 2008)
19	Cocher	Auriga	Aurigae	Aur	sans objet
8	Bouvier	Bootes	Bootis	Boo	sans objet
9	Burin	Caelum	Caeli	Cae	sans objet
36	Girafe	Camelopardalis	Camelopardalis	Cam	sans objet
12	Capricorne	Capricornus	Capricorni	Cap	20 janvier - 16 février (en 2008)
13	Carène	Carina	Carinae	Car	sans objet
14	Cassiopée	Cassiopeia	Cassiopeiae	Cas	sans objet
15	Centaure	Centaurus	Centauri	Cen	sans objet
16	Céphée	Cepheus	Cephei	Cep	sans objet
5	Baleine	Cetus	Ceti	Cet	sans objet
10	Caméléon	Chamaeleon	Chamaeleontis	Cha	sans objet
21	Compas	Circinus	Circinii	Cir	sans objet
37	Grand Chien	Canis Major	Canis Majoris	CMa	sans objet
64	Petit Chien	Canis Minor	Canis Minoris	CMi	sans objet
11	Cancer	Cancer	Cancri	Cnc	20 juillet - 10 août (en 2008)
20	Colombe	Columba	Columbae	Col	sans objet
17	Chevelure de Bérénice	Coma Berenices	Comae Berenices	Com	sans objet
24	Couronne australe	Corona Australis	Coronae Australis	CrA	sans objet
25	Couronne boréale	Corona Borealis	Coronae Borealis	CrB	sans objet
23	Coupe	Crater	Crateris	Crt	sans objet
26	Croix du Sud	Crux	Crucis	Cru	sans objet
22	Corbeau	Corvus	Corvi	Crv	sans objet
18	Chiens de chasse	Canes Venatici	Canum Venaticorum	CVn	sans objet
27	Cygne	Cygnus	Cygni	Cyg	sans objet
28	Dauphin	Delphinus	Delphini	Del	sans objet
29	Dorade	Dorado	Doradus	Dor	sans objet
30	Dragon	Draco	Draconis	Dra	sans objet
63	Petit Cheval	Equuleus	Equulei	Equ	sans objet
32	Éridan	Eridanus	Eridani	Eri	sans objet
34	Fourneau	Fornax	Fornacis	For	sans objet
35	Gémeaux	Gemini	Geminorum	Gem	21 juin - 20 juillet (en 2008)
39	Grue	Grus	Gruis	Gru	sans objet
40	Hercule	Hercules	Herculis	Her	sans objet
41	Horloge	Horologium	Horologii	Hor	sans objet
42	Hydre	Hydra	Hydrae	Hya	sans objet
43	Hydre mâle	Hydrus	Hydri	Hyi	sans objet
44	Indien	Indus	Indi	Ind	sans objet
45	Lézard	Lacerta	Lacertae	Lac	sans objet
48	Lion	Leo	Leonis	Leo	10 août - 16 septembre (en 2008)
47	Lièvre	Lepus	Leporis	Lep	sans objet
4	Balance	Libra	Librae	Lib	30 octobre - 20 novembre (en 2008)
65	Petit Lion	Leo Minor	Leonis Minoris	LMi	sans objet
49	Loup	Lupus	Lupi	Lup	sans objet
50	Lynx	Lynx	Lyncis	Lyn	sans objet
51	Lyre	Lyra	Lyrae	Lyr	sans objet
80	Table	Mensa	Mensae	Men	sans objet
53	Microscope	Microscopium	Microscopii	Mic	sans objet
46	Licorne	Monoceros	Monocerotis	Mon	sans objet
54	Mouche	Musca	Muscae	Mus	sans objet
73	Règle	Norma	Normae	Nor	sans objet
55	Octant	Octans	Octantis	Oct	sans objet
57	Ophiuchus	Ophiuchus	Ophiuchi	Oph	29 novembre - 17 décembre (en 2008)
58	Orion	Orion	Orionis	Ori	sans objet
59	Paon	Pavo	Pavonis	Pav	sans objet
60	Pégase	Pegasus	Pegasi	Peg	sans objet
62	Persée	Perseus	Persei	Per	sans objet
68	Phénix	Phoenix	Phoenicis	Phe	sans objet
61	Peintre	Pictor	Pictoris	Pic	sans objet
69	Poisson austral	Piscis Austrinus	Piscis Austrini	PsA	sans objet
71	Poissons	Pisces	Piscium	Psc	11 mars - 18 avril (en 2008)
72	Poupe	Puppis	Puppis	Pup	sans objet
7	Boussole	Pyxis	Pyxidis	Pyx	sans objet
74	Réticule	Reticulum	Reticuli	Ret	sans objet
77	Sculpteur	Sculptor	Sculptoris	Scl	sans objet
76	Scorpion	Scorpius	Scorpii	Sco	20 novembre - 29 novembre (en 2008)
31	Écu de Sobieski	Scutum	Scuti	Sct	sans objet
78	Serpent	Serpens	Serpentis	Ser	sans objet
79	Sextant	Sextans	Sextantis	Sex	sans objet
33	Flèche	Sagitta	Sagittae	Sge	sans objet
75	Sagittaire	Sagittarius	Sagittarii	Sgr	17 décembre - 20 janvier (en 2008)
81	Taureau	Taurus	Tauri	Tau	13 mai - 21 juin (en 2008)
82	Télescope	Telescopium	Telescopii	Tel	sans objet
85	Triangle austral	Triangulum Australe	Trianguli Australis	TrA	sans objet
84	Triangle	Triangulum	Trianguli	Tri	sans objet
83	Toucan	Tucana	Tucanae	Tuc	sans objet
38	Grande Ourse	Ursa Major	Ursae Majoris	UMa	sans objet
67	Petite Ourse	Ursa Minor	Ursae Minoris	UMi	sans objet
88	Voiles	Vela	Velorum	Vel	sans objet
87	Vierge	Virgo	Virginis	Vir	16 septembre - 30 octobre (en 2008)
70	Poisson volant	Volans	Volantis	Vol	sans objet
66	Petit Renard	Vulpecula	Vulpeculae	Vul	sans objet
 * 
 * 
 * 1	 And	 Andromeda	 Andromedae	 Andromeda	 722	 NH	 Alpheratz
2	 Ant	 Antlia	 Antliae	 Air Pump	 239	 SH	 
3	 Aps	 Apus	 Apodis	 Bird of Paradise	 206	 SH	  
4	 Aqr	 Aquarius	 Aquarii	 Water Carrier	 980	 SH	 Sadalmelik
5	 Aql	 Aquila	 Aquilae	 Eagle	 652	 NH-SH	 Altair
6	 Ara	 Ara	 Arae	 Altar	 237	 SH	  
7	 Ari	 Aries	 Arietis	 Ram	 441	 NH	 Hamal
8	 Aur	 Auriga	 Aurigae	 Charioteer	 657	 NH	 Capella
9	 Boo	 Bootes	 Bootis	 Herdsman	 907	 NH	 Arcturus
10	 Cae	 Caelum	 Caeli	 Chisel	 125	 SH	  
11	 Cam	 Camelopardalis	 Camelopardalis	 Giraffe	 757	 NH	 
12	 Cnc	 Cancer	 Cancri	 Crab	 506	 NH	 Acubens
13	 CVn	 Canes Venatici	 Canun Venaticorum	 Hunting Dogs	 465	 NH	 Cor Caroli
14	 CMa	 Canis Major	 Canis Majoris	 Big Dog	 380	 SH	 Sirius
15	 CMi	 Canis Minor	 Canis Minoris	 Little Dog	 183	 NH	 Procyon
16	 Cap	 Capricornus	 Capricorni	 Goat ( Capricorn )	 414	 SH	 Algedi
17	 Car	 Carina	 Carinae	 Keel	 494	 SH	 Canopus
18	 Cas	 Cassiopeia	 Cassiopeiae	 Cassiopeia	 598	 NH	 Schedar
19	 Cen	 Centaurus	 Centauri	 Centaur	 1060	 SH	 Rigil Kentaurus
20	 Cep	 Cepheus	 Cephei	 Cepheus	 588	 SH	 Alderamin
21	 Cet	 Cetus	 Ceti	 Whale	 1231	 SH	 Menkar
22	 Cha	 Chamaleon	 Chamaleontis	 Chameleon	 132	 SH	  
23	 Cir	 Circinus	 Circini	 Compasses	 93	 SH	 
24	 Col	 Columba	 Columbae	 Dove	 270	 SH	 Phact
25	 Com	 Coma Berenices	 Comae Berenices	 Berenice's Hair	 386	 NH	 Diadem
26	 CrA	 Corona Australis	 Coronae Australis	 Southern Crown	 128	 SH	  
27	 CrB	 Corona Borealis	 Coronae Borealis	 Northern Crown	 179	 NH	 Alphecca
28	 Crv	 Corvus	 Corvi	 Crow	 184	 SH	 Alchiba
29	 Crt	 Crater	 Crateris	 Cup	 282	 SH	 Alkes
30	 Cru	 Crux	 Crucis	 Southern Cross	 68	 SH	 Acrux
31	 Cyg	 Cygnus	 Cygni	 Swan	 804	 NH	 Deneb
32	 Del	 Delphinus	 Delphini	 Dolphin	 189	 NH	 Sualocin
33	 Dor	 Dorado	 Doradus	 Goldfish	 179	 SH	  
34	 Dra	 Draco	 Draconis	 Dragon	 1083	 NH	 Thuban
35	 Equ	 Equuleus	 Equulei	 Little Horse	 72	 NH	 Kitalpha
36	 Eri	 Eridanus	 Eridani	 River	 1138	 SH	 Achernar
37	 For	 Fornax	 Fornacis	 Furnace	 398	 SH	  
38	 Gem	 Gemini	 Geminorum	 Twins	 514	 NH	 Castor
39	 Gru	 Grus	 Gruis	 Crane	 366	 SH	 Al Na'ir
40	 Her	 Hercules	 Herculis	 Hercules	 1225	 NH	 Rasalgethi
41	 Hor	 Horologium	 Horologii	 Clock	 249	 SH	  
42	 Hya	 Hydra	 Hydrae	 Hydra ( Sea Serpent )	 1303	 SH	 Alphard
43	 Hyi	 Hydrus	 Hydri	 Water Serpen ( male )	 243	 SH	  
44	 Ind	 Indus	 Indi	 Indian	 294	 SH	 
45	 Lac	 Lacerta	 Lacertae	 Lizard	 201	 NH	  
46	 Leo	 Leo	 Leonis	 Lion	 947	 NH	 Regulus
47	 LMi	 Leo Minor	 Leonis Minoris	 Smaller Lion	 232	 NH	 
48	 Lep	 Lepus	 Leporis	 Hare	 290	 SH	 Arneb
49	 Lib	 Libra	 Librae	 Balance	 538	 SH	 Zubenelgenubi
50	 Lup	 Lupus	 Lupi	 Wolf	 334	 SH	 Men
51	 Lyn	 Lynx	 Lyncis	 Lynx	 545	 NH	  
52	 Lyr	 Lyra	 Lyrae	 Lyre	 286	 NH	 Vega
53	 Men	 Mensa	 Mensae	 Table	 153	 SH	  
54	 Mic	 Microscopium	 Microscopii	 Microscope	 210	 SH	  
55	 Mon	 Monoceros	 Monocerotis	 Unicorn	 482	 SH	  
56	 Mus	 Musca	 Muscae	 Fly	 138	 SH	  
57	 Nor	 Norma	 Normae	 Square	 165	 SH	 
58	 Oct	 Octans	 Octantis	 Octant	 291	 SH	  
59	 Oph	 Ophiucus	 Ophiuchi	 Serpent Holder	 948	 NH-SH	 Rasalhague
60	 Ori	 Orion	 Orionis	 Orion	 594	 NH-SH	 Betelgeuse
61	 Pav	 Pavo	 Pavonis	 Peacock	 378	 SH	 Peacock
62	 Peg	 Pegasus	 Pegasi	 Winged Horse	 1121	 NH	 Markab
63	 Per	 Perseus	 Persei	 Perseus	 615	 NH	 Mirfak
64	 Phe	 Phoenix	 Phoenicis	 Phoenix	 469	 SH	 Ankaa
65	 Pic	 Pictor	 Pictoris	 Easel	 247	 SH	  
66	 Psc	 Pisces	 Piscium	 Fishes	 889	 NH	 Alrischa
67	 PsA	 Pisces Austrinus	 Pisces Austrini	 Southern Fish	 245	 SH	 Fomalhaut
68	 Pup	 Puppis	 Puppis	 Stern	 673	 SH	  
69	 Pyx	 Pyxis	 Pyxidis	 Compass	 221	 SH	 
70	 Ret	 Reticulum	 Reticuli	 Reticle	 114	 SH	 
71	 Sge	 Sagitta	 Sagittae	 Arrow	 80	 NH	  
72	 Sgr	 Sagittarius	 Sagittarii	 Archer	 867	 SH	 Rukbat
73	 Sco	 Scorpius	 Scorpii	 Scorpion	 497	 SH	 Antares
74	 Scl	 Sculptor	 Sculptoris	 Sculptor	 475	 SH	 
75	 Sct	 Scutum	 Scuti	 Shield	 109	 SH	  
76	 Ser	 Serpens	 Serpentis	 Serpent	 637	 NH-SH	 Unuck al Hai
77	 Sex	 Sextans	 Sextantis	 Sextant	 314	 SH	  
78	 Tau	 Taurus	 Tauri	 Bull	 797	 NH	 Aldebaran
79	 Tel	 Telescopium	 Telescopii	 Telescope	 252	 SH	  
80	 Tri	 Triangulum	 Trianguli	 Triangle	 132	 NH	 Ras al Mothallah
81	 TrA	 Triangulum Australe	 Trianguli Australis	 Southern Triangle	 110	 SH	 Atria
82	 Tuc	 Tucana	 Tucanae	 Toucan	 295	 SH	  
83	 UMa	 Ursa Major	 Ursae Majoris	 Great Bear	 1280	 NH	 Dubhe
84	 UMi	 Ursa Minor	 Ursae Minoris	 Little Bear	 256	 NH	 Polaris
85	 Vel	 Vela	 Velorum	 Sails	 500	 SH	  
86	 Vir	 Virgo	 Virginis	 Virgin	 1294	 NH-SH	 Spica
87	 Vol	 Volans	 Volantis	 Flying Fish	 141	 SH	 
88	 Vul	 Vulpecula	 Vulpeculae	 Fox	 268	 NH	
 */
