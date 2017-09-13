package fr.ozf.ephemerides;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import fr.ozf.fmr.ReingoldDershowitz.fmrReingoldDershowitz;
import fr.ozf.fmr.Schlyter.fmrObject;
import fr.ozf.fmr.fmrDate;
import fr.ozf.fmr.fmrLocation;
import fr.ozf.fmr.Schlyter.fmrSolarSystem;
import fr.ozf.fmr.fmrBlague;
import fr.ozf.fmr.fmrCitation;
import fr.ozf.fmr.fmrDicton;
import fr.ozf.fmr.fmrFruitsLegumes;
import fr.ozf.fmr.fmrHoroscope;
import fr.ozf.fmr.fmrJourneesMondiales;
import fr.ozf.fmr.fmrLeSaviezVous;
import fr.ozf.fmr.fmrSaint;
import fr.ozf.fmr.fmrSaintBreton;


/*
coordonnees geocentriques ecliptiques :
	longitude
	latitude
coordonnees geocentriques equatoriales :
	ascension droite
	declinaison
distance terre (ua ou km)
distance soleil (ua)
diametre apparent (' et ")
magnitude
illumination

heures de lever et coucher de soleil et lune
minutes de soleil en + et en -
jour julien
jour julien 2000
new earth time [http://newearthtime.net/]
heure internet [https://www.wikiwand.com/fr/Heure_Internet]

https://androidresearch.wordpress.com/2013/06/01/creating-a-simple-rss-application-in-android-v2/
 */

//***********************************************************************
// MainActivity
//***********************************************************************
public class MainActivity extends FragmentActivity
{
	// CONSTANTS
	private final static String puce = "#";

	// Variables
	private fmrDate zeDate;
	private fmrLocation zeLocation;
	private fmrLocation prefLocation;
	private fmrSolarSystem solarSystem;
	// private fmrSun zeSun;
	private LocationManager locationManager;
	private Location myLocation;
	private TabHost myTabHost;
	private TextView tvLocation;
	private TextView tvBuildDate;
	private String currentTab = "idToday";
	private int currentObjectStringID = R.string.str_sun;
	private String currentObjectImg = String.valueOf(R.drawable.ic_sun);
	private MenuItem btnRefresh;
	private Boolean chkTime;
	private long seed;
	private Handler mHandler;
	private SharedPreferences prefs;
	private boolean prefAutoRefresh;
	private Menu actionBarMenu;
	private String prefTimeFont;
	private Typeface LCDTypeface;
	public static String prefRSSLink;
	private String prefAstrologicalSign;
	private static Date buildDate = null;

	private final Runnable m_Runnable = new Runnable()
	{
		public void run()
		{
			if (prefAutoRefresh == true)
			{
				refreshUI();
				actionBarMenu.findItem(R.id.refresh).setIcon(R.drawable.ic_refresh_white_24dp);
			} else
			{
				actionBarMenu.findItem(R.id.refresh).setIcon(R.drawable.ic_refresh_black_24dp);
			}
			mHandler.postDelayed(m_Runnable, 1000);
		}
	};//runnable

	//***********************************************************************
	// onCreateOptionsMenu()
	//***********************************************************************
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Display the action bar with the refresh button
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar, menu);
		actionBarMenu = menu;
		return super.onCreateOptionsMenu(menu);
	}

	//***********************************************************************
	// onOptionsItemSelected()
	//***********************************************************************
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Manage the action bar
		switch (item.getItemId())
		{
			case R.id.refresh:
				refreshUI();
				return true;

			case R.id.overflow:
				Intent intent = new Intent(this, PrefsActivity.class);
				startActivity(intent);
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	//***********************************************************************
	// onCreate()
	//***********************************************************************
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initApp();

		setContentView(R.layout.main);
		myTabHost = (TabHost) findViewById(R.id.TabHost01);
		myTabHost.setup();

		myTabHost.addTab(myTabHost.newTabSpec("idToday").setIndicator("", getResources().getDrawable(R.drawable.ic_schedule_black_24dp)).setContent(R.id.Today));
		myTabHost.addTab(myTabHost.newTabSpec("idObjects").setIndicator("", getResources().getDrawable(R.drawable.ic_brightness_low_black_24dp)).setContent(R.id.Objects));
		myTabHost.addTab(myTabHost.newTabSpec("idDetails").setIndicator("", getResources().getDrawable(R.drawable.ic_format_list_bulleted_black_24dp)).setContent(R.id.Details));
		myTabHost.addTab(myTabHost.newTabSpec("idCharts").setIndicator("", getResources().getDrawable(R.drawable.ic_insert_chart_black_24dp)).setContent(R.id.Charts));
		myTabHost.addTab(myTabHost.newTabSpec("idRSS").setIndicator("", getResources().getDrawable(R.drawable.ic_rss_black_24dp)).setContent(R.id.fragment_container));
		myTabHost.addTab(myTabHost.newTabSpec("idInfo").setIndicator("", getResources().getDrawable(R.drawable.ic_info_outline_black_24dp)).setContent(R.id.Info));

		if (savedInstanceState == null)
		{
			addRssFragment();
		}

		showToday();
		//
		myTabHost.setCurrentTab(0);
		// Tab change Listener
		myTabHost.setOnTabChangedListener(
				new TabHost.OnTabChangeListener()
				{
					public void onTabChanged(String tabId)
					{
						currentTab = tabId;
						// Toast.makeText(MainActivity.this, "L'onglet avec l'identifiant : " + tabId + " a été cliqué", Toast.LENGTH_SHORT).show();
						switch (tabId)
						{
							case "idToday":
								showToday();
								break;

							case "idObjects":
								computeObjects();
								showObjects();
								break;

							case "idDetails":
								// computeObject(currentObject);
								computeObjects();
								showDetails(currentObjectStringID, currentObjectImg);
								break;

							case "idCharts":
								showCharts();
								break;

							case "idRSS":
								showRSS();
								break;

							case "idInfo":
								showInfo();
								break;
						}
					}
				}
		);

		this.mHandler = new Handler();
		this.mHandler.postDelayed(m_Runnable, 5000);

		//
		  /*
		  imgRSS.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View view)
         {
            showInputDialog();
         }
      });
      */
	}

	//***********************************************************************
	// onSaveInstanceState()
	//***********************************************************************
	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putBoolean("fragment_added", true);
	}

	//***********************************************************************
	// initApp()
	//***********************************************************************
	private void initApp()
	{
		// Update the current date
		zeDate = new fmrDate();
		seed = zeDate.getYear() * 10000 + zeDate.getMonth() * 100 + zeDate.getDay();
		solarSystem = new fmrSolarSystem();

		// Get preferred location from Preferences screen
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefLocation = parseDBLocation(prefs.getString("prfDBList", "Montcuq, France:+44.3333:+1.2167:+215"));
		// MessageBox(prefs.getString("prfDBList", "@@"));

		// Get auto refresh status from Preferences screen
		prefAutoRefresh = prefs.getBoolean("chkPrefAutoRefresh", false);

		// Get font used for time display and load it
		prefTimeFont = prefs.getString("prfTimeDisplayFont", "Patopian1986.ttf");
		LCDTypeface = Typeface.createFromAsset(this.getAssets(), prefTimeFont);

		// Get RSS link
		prefRSSLink = prefs.getString("prfRSS", "http://www.presse-citron.net/feed/");

		// Get Astrological Sign
		prefAstrologicalSign = prefs.getString("prfAstrologicalSign", "8");

		// Retrieve current location (if any)
		String locationContext = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) getSystemService(locationContext);
		updateCoords();

		// Get Build date
		buildDate = getClassBuildTime();
	}

	//***********************************************************************
	// refreshUI()
	//***********************************************************************
	private void refreshUI()
	{
		updateCoords();
		computeObjects();
		switch (currentTab)
		{
			case "idToday":
				showToday();
				break;

			case "idObjects":
				showObjects();
				break;

			case "idInfo":
				showInfo();
				break;

			case "idCharts":
				// TODO
				break;

			case "idDetails":
				showDetails(currentObjectStringID, currentObjectImg);
				break;
		}
	}

	//***********************************************************************
	// addRssFragment()
	//***********************************************************************
	private void addRssFragment()
	{
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		RssFragment fragment = new RssFragment();
		transaction.add(R.id.fragment_container, fragment);
		transaction.commit();
	}

	//***********************************************************************
	// showToday()
	//***********************************************************************
	private void showToday()
	{
		zeDate = new fmrDate();
		//
		TableLayout tb = (TableLayout) findViewById(R.id.tbToday);
		tb.removeAllViews();
		addTitleRow(tb, getString(R.string.str_title_today));
		addRowEnhanced(tb, fmrSaint.getSaint(zeDate) + " - " + fmrSaintBreton.getSaintBreton(zeDate), Color.WHITE, Color.BLACK, 14, Gravity.CENTER_HORIZONTAL, false);
		addRowEnhanced(tb, zeDate.getDayOfWeek(), Color.WHITE, Color.BLUE, 20, Gravity.CENTER_HORIZONTAL, false);
		addRowEnhanced(tb, String.valueOf(zeDate.getDay()), Color.WHITE, Color.RED, 48, Gravity.CENTER_HORIZONTAL, false);
		addRowEnhanced(tb, zeDate.getMonthName() + " " + String.valueOf(zeDate.getYear()), Color.WHITE, Color.BLUE, 20, Gravity.CENTER_HORIZONTAL, false);
		addRowEnhanced(tb, getString(R.string.str_week) + " " + String.valueOf(zeDate.getWeekFromDate()), Color.WHITE, Color.BLACK, 14, Gravity.CENTER_HORIZONTAL, false);
		int j0101 = zeDate.daysFrom0101();
		int j3112 = zeDate.daysUntil3112();
		String infoyear = String.valueOf(j0101) + (j0101 > 1 ? "ème" : "er") + " jour de l'année";
		infoyear += " - " + String.valueOf(j3112) + (j3112 > 1 ? " jours restants" : " jour restant");
		addRowEnhanced(tb, infoyear, Color.WHITE, Color.BLACK, 14, Gravity.CENTER_HORIZONTAL, false);
		List<String> jm = fmrJourneesMondiales.getJournee(zeDate.getMonth(), zeDate.getDay());
		for (String n : jm)
		{
			addRowEnhanced(tb, n, Color.WHITE, Color.BLACK, 14, Gravity.CENTER_HORIZONTAL, false);
		}

		//
		addSpaceRow(tb);
		addTitleRow(tb, getString(R.string.str_title_whattimeisit));
		addRowEnhanced(tb, "Heure locale (" + zeDate.getTZ().getID() + ")", Color.WHITE, Color.BLACK, 14, Gravity.LEFT, false);
		addRowEnhanced(tb, fmrDate.Hour2String(zeDate.cal) + " ", Color.WHITE, Color.BLUE, 30, Gravity.RIGHT, true);
		addRowEnhanced(tb, "Heure GMT", Color.WHITE, Color.BLACK, 14, Gravity.LEFT, false);
		addRowEnhanced(tb, fmrDate.Hour2String(zeDate.getUTC()) + " ", Color.WHITE, Color.BLUE, 30, Gravity.RIGHT, true);
		addRowEnhanced(tb, "New Earth Time", Color.WHITE, Color.BLACK, 14, Gravity.LEFT, false);
		addRowEnhanced(tb, zeDate.getNewEarthTime(), Color.WHITE, Color.BLUE, 30, Gravity.RIGHT, true);
		addRowEnhanced(tb, "Swatch® Internet Time", Color.WHITE, Color.BLACK, 14, Gravity.LEFT, false);
		addRowEnhanced(tb, zeDate.getSwatchInternetTime() + " ", Color.WHITE, Color.BLUE, 30, Gravity.RIGHT, true);
		addRowEnhanced(tb, "Unix Time", Color.WHITE, Color.BLACK, 14, Gravity.LEFT, false);
		addRowEnhanced(tb, Long.toString(zeDate.getUnixTime()) + " ", Color.WHITE, Color.BLUE, 30, Gravity.RIGHT, true);
		//
		addSpaceRow(tb);
		addTitleRow(tb, getString(R.string.str_title_dictons));
		addRow(tb, "", fmrDicton.get(zeDate.getMonth(), zeDate.getDay()), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addSpaceRow(tb);
		addRow(tb, "", fmrDicton.get(zeDate.getMonth(), seed), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addSpaceRow(tb);
		//
		addTitleRow(tb, getString(R.string.str_title_citation));
		addRow(tb, "", fmrCitation.get(seed), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addSpaceRow(tb);
		//
		addTitleRow(tb, getString(R.string.str_title_blague));
		addRow(tb, "", fmrBlague.get(seed), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		//
		addSpaceRow(tb);
		addTitleRow(tb, getString(R.string.str_title_le_saviez_vous));
		addRow(tb, "", fmrLeSaviezVous.get(seed), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		//
		addSpaceRow(tb);
		addTitleRow(tb, getString(R.string.str_title_calendriers));
		fmrReingoldDershowitz fmr = new fmrReingoldDershowitz();
		addTable(tb, fmr.out, getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		//
		int horoSign = Integer.parseInt(prefAstrologicalSign);
		addSpaceRow(tb);
		addTitleRow(tb, getString(R.string.str_title_horoscope) + " " + fmrHoroscope.horo_signes[horoSign].toUpperCase());
		addTable(tb, fmrHoroscope.getAll(horoSign, zeDate.getDay(), zeDate.getMonth(), zeDate.getYear()), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		//
		addSpaceRow(tb);
		addTitleRow(tb, getString(R.string.str_title_vegetables_month));
		String[] legumes = fmrFruitsLegumes.getLegumes(zeDate.Month);
		String listLegumes = "";
		for (String l : legumes)
			listLegumes += l + " - ";
		listLegumes = listLegumes.substring(0, listLegumes.length()-3);
		addRow(tb, "", listLegumes, getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		//
		addSpaceRow(tb);
		addTitleRow(tb, getString(R.string.str_title_fruits_month));
		String[] fruits = fmrFruitsLegumes.getFruits(zeDate.Month);
		String listFruits = "";
		for (String f : fruits)
			listFruits += f + " - ";
		listFruits = listFruits.substring(0, listFruits.length()-3);
		addRow(tb, "", listFruits, getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		//
		addSpaceRow(tb);
	}

	//***********************************************************************
	// addTable()
	//***********************************************************************
	private void addTable(TableLayout tb, List<String[]> vals, int colorLabel, int colorValue)
	{
		TableLayout innerTable = new TableLayout(tb.getContext());
		TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
		innerTable.setLayoutParams(lp1);
		innerTable.setColumnShrinkable(1, true);

		for (String[] s : vals)
		{
			// Row
			TableRow row = new TableRow(tb.getContext());

			// Label
			TextView tvLabel = new TextView(tb.getContext());
			tvLabel.setTextColor(colorLabel);
			tvLabel.setPadding(5, 0, 5, 0);
			tvLabel.setGravity(Gravity.RIGHT);
			tvLabel.setText(Html.fromHtml(s[0]));

			// Value
			TextView tvValue = new TextView(tb.getContext());
			tvValue.setTextColor(colorValue);
			tvValue.setPadding(5, 0, 5, 0);
			tvValue.setGravity(Gravity.LEFT);
			tvValue.setSingleLine(false);
			tvValue.setMaxLines(100);
			tvValue.setText(Html.fromHtml(s[1]));

			// Add label & value to row
			row.addView(tvLabel);
			row.addView(tvValue);

			// Add row to inner table
			innerTable.addView(row);
		}

		// Add inner table to view
		tb.addView(innerTable);
	}

	//***********************************************************************
	// addRow()
	//***********************************************************************
	private void addRow(TableLayout tb, String label, String value, int colorLabel, int colorValue)
	{
		tb.setColumnShrinkable(1, true);
		TableRow row = new TableRow(this);
		TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
		row.setLayoutParams(lp);
		row.setPadding(0, 0, 0, 0);

		TextView tvLabel = new TextView(this);
		TextView tvValue = new TextView(this);

		tvLabel.setTextColor(colorLabel);
		tvValue.setTextColor(colorValue);

		tvValue.setEllipsize(null);
		tvValue.setMaxLines(100);
		tvValue.setHorizontallyScrolling(false);
		tvLabel.setText(Html.fromHtml(label));
		tvValue.setText(Html.fromHtml(value));
		tvLabel.setPadding(5, 0, 5, 0);
		tvValue.setPadding(5, 0, 5, 0);
		tvLabel.setGravity(Gravity.RIGHT);

		row.addView(tvLabel);
		row.addView(tvValue);

		tb.addView(row);
	}

	//***********************************************************************
	// addRowEnhanced()
	//***********************************************************************
	public void addRowEnhanced(TableLayout tb, String text, int backColor, int foreColor, float textSize, int align, boolean lcd)
	{
		prefTimeFont = prefs.getString("prfTimeDisplayFont", "Patopian1986.ttf");
		LCDTypeface = Typeface.createFromAsset(this.getAssets(), prefTimeFont);

		tb.setColumnShrinkable(1, true);
		TableRow row = new TableRow(this);
		TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
		row.setLayoutParams(lp);
		row.setPadding(0, 0, 0, 0);

		TextView tvLabel = new TextView(this);
		TextView tvValue = new TextView(this);

		tvValue.setTextColor(foreColor);
		tvValue.setBackgroundColor(backColor);
		tvValue.setTextSize(textSize);
		if (lcd == true)
			tvValue.setTypeface(LCDTypeface);

		tvValue.setEllipsize(null);
		tvValue.setMaxLines(100);
		tvValue.setHorizontallyScrolling(false);
		tvLabel.setText("");
		tvValue.setText(Html.fromHtml(text));
		tvLabel.setPadding(5, 0, 5, 0);
		tvValue.setPadding(5, 0, 5, 0);
		tvValue.setGravity(Gravity.CENTER_VERTICAL | align);

		row.addView(tvLabel);
		row.addView(tvValue);

		tb.addView(row);
	}

	//***********************************************************************
	// addTitleRow()
	//***********************************************************************
	private void addTitleRow(TableLayout tb, String value)
	{
		addRow(tb, "<b>" + puce + "</b>", "<b>" + value + "</b>", getResources().getColor(R.color.titlecolor), getResources().getColor(R.color.titlecolor));
	}

	//***********************************************************************
	// addSpaceRow()
	//***********************************************************************
	private void addSpaceRow(TableLayout tb)
	{
		addRow(tb, "", "", Color.WHITE, Color.WHITE);
	}

	//***********************************************************************
	// showInfo()
	//***********************************************************************
	private void showInfo()
	{
		String pattern = "yyyyMMdd.HHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String buildString = simpleDateFormat.format(buildDate);
		tvBuildDate = (TextView) findViewById(R.id.tvBuildDate);
		tvBuildDate.setText("Build " + buildString);
		//
		tvLocation = (TextView) findViewById(R.id.tvLocation);
		tvLocation.setText("Location : " + zeLocation.toString() + "\nDefault : " + prefLocation.toString());
	}

	//***********************************************************************
	// showDetails()
	//***********************************************************************
	private void showDetails(int objectStringID, String img)
	{
		ImageView iv = (ImageView) findViewById(R.id.img_details);
		iv.setImageResource(Integer.parseInt(img));

		TextView tv = (TextView) findViewById(R.id.title_details);
		tv.setText(getString(objectStringID));

		TableLayout tb = (TableLayout) findViewById(R.id.tbDetails);
		tb.removeAllViews();
		addTitleRow(tb, getString(R.string.str_title_earth_location));
		addRow(tb, getString(R.string.str_location), zeLocation.Name, getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addRow(tb, getString(R.string.str_latitude), fmrDate.dd2dms(zeLocation.Latitude), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addRow(tb, getString(R.string.str_longitude), fmrDate.dd2dms(zeLocation.Longitude), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addRow(tb, getString(R.string.str_elevation), Double.toString(zeLocation.Elevation) + " m", getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addSpaceRow(tb);
		addTitleRow(tb, getString(R.string.str_title_time));
		addRow(tb, getString(R.string.str_time), zeDate.toString(), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addRow(tb, getString(R.string.str_d2000), Double.toString(zeDate.D2000), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addRow(tb, "JD", Double.toString(zeDate.JD), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addRow(tb, getString(R.string.str_time_zone), zeDate.getTZ().getDisplayName(), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addRow(tb, getString(R.string.str_tzv_offset), String.format("%+06.2f", zeDate.getTZV()), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addRow(tb, getString(R.string.str_obliquity_ecliptic), fmrDate.dd2dms(zeDate.OblEcl), getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		addSpaceRow(tb);

		switch (objectStringID)
		{
			case R.string.str_earth:
				showDetailsEarth(tb);
				break;

			case R.string.str_sun:
				showDetailsSun(tb);
				break;

			case R.string.str_moon:
				showDetailsMoon(tb);
				break;

			case R.string.str_mercury:
				showDetailsMercury(tb);
				break;

			case R.string.str_venus:
				showDetailsVenus(tb);
				break;

			case R.string.str_mars:
				showDetailsMars(tb);
				break;

			case R.string.str_jupiter:
				showDetailsJupiter(tb);
				break;

			case R.string.str_saturn:
				showDetailsSaturn(tb);
				break;

			case R.string.str_uranus:
				showDetailsUranus(tb);
				break;

			case R.string.str_neptune:
				showDetailsNeptune(tb);
				break;
		}
	}

	//***********************************************************************
	// showDetailsEarth()
	//***********************************************************************
	private void showDetailsEarth(TableLayout tb)
	{
		// TODO
	}

	//***********************************************************************
	// showDetailsSun()
	//***********************************************************************
	private void showDetailsSun(TableLayout tb)
	{
		addTitleRow(tb, "ECLIPTIC GEOCENTRIC");
		for (String[] s : solarSystem.sun.getValues(fmrObject.VALUES_GEO_ECLIPTIC))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
		addSpaceRow(tb);
		addTitleRow(tb, "EQUATORIAL GEOCENTRIC");
		for (String[] s : solarSystem.sun.getValues(fmrObject.VALUES_GEO_EQUATORIAL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
		addSpaceRow(tb);
		addTitleRow(tb, "TOPOCENTRIC");
		for (String[] s : solarSystem.sun.getValues(fmrObject.VALUES_TOPO_HORIZONTAL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
		addSpaceRow(tb);
	}

	//***********************************************************************
	// showDetailsMoon()
	//***********************************************************************
	private void showDetailsMoon(TableLayout tb)
	{
		addTitleRow(tb, getString(R.string.str_moon).toUpperCase());
		for (String[] s : solarSystem.moon.getValues(fmrObject.VALUES_ALL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
	}

	//***********************************************************************
	// showDetailsMercury()
	//***********************************************************************
	private void showDetailsMercury(TableLayout tb)
	{
		addTitleRow(tb, getString(R.string.str_mercury).toUpperCase());
		for (String[] s : solarSystem.mercury.getValues(fmrObject.VALUES_ALL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
	}

	//***********************************************************************
	// showDetailsVenus()
	//***********************************************************************
	private void showDetailsVenus(TableLayout tb)
	{
		addTitleRow(tb, getString(R.string.str_venus).toUpperCase());
		for (String[] s : solarSystem.venus.getValues(fmrObject.VALUES_ALL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
	}

	//***********************************************************************
	// showDetailsMars()
	//***********************************************************************
	private void showDetailsMars(TableLayout tb)
	{
		addTitleRow(tb, getString(R.string.str_mars).toUpperCase());
		for (String[] s : solarSystem.mars.getValues(fmrObject.VALUES_ALL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
	}

	//***********************************************************************
	// showDetailsJupiter()
	//***********************************************************************
	private void showDetailsJupiter(TableLayout tb)
	{
		addTitleRow(tb, getString(R.string.str_jupiter).toUpperCase());
		for (String[] s : solarSystem.jupiter.getValues(fmrObject.VALUES_ALL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
	}

	//***********************************************************************
	// showDetailsSaturn()
	//***********************************************************************
	private void showDetailsSaturn(TableLayout tb)
	{
		addTitleRow(tb, getString(R.string.str_saturn).toUpperCase());
		for (String[] s : solarSystem.saturn.getValues(fmrObject.VALUES_ALL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
	}

	//***********************************************************************
	// showDetailsUranus()
	//***********************************************************************
	private void showDetailsUranus(TableLayout tb)
	{
		addTitleRow(tb, getString(R.string.str_uranus).toUpperCase());
		for (String[] s : solarSystem.mars.getValues(fmrObject.VALUES_ALL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
	}

	//***********************************************************************
	// showDetailsNeptune()
	//***********************************************************************
	private void showDetailsNeptune(TableLayout tb)
	{
		addTitleRow(tb, getString(R.string.str_neptune).toUpperCase());
		for (String[] s : solarSystem.mars.getValues(fmrObject.VALUES_ALL))
		{
			addRow(tb, s[0], s[1], getResources().getColor(R.color.labelcolor), getResources().getColor(R.color.textcolor));
		}
	}

	//***********************************************************************
	// showRSS()
	//***********************************************************************
	private void showRSS()
	{

	}

	//***********************************************************************
	// showCharts()
	//***********************************************************************
	private void showCharts()
	{

	}

	//***********************************************************************
	// showObjects()
	//***********************************************************************
	private void showObjects()
	{
		final ListView myListView = (ListView) findViewById(R.id.ListObjects);
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_earth));
		map.put("titre", getString(R.string.str_earth));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_earth));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_sun));
		map.put("titre", getString(R.string.str_sun));
		map.put("line1", getString(R.string.str_right_ascension) + " : " + fmrDate.dd2hms(solarSystem.sun.RightAscension));
		map.put("line2", getString((R.string.str_declination)) + " : " + fmrDate.dd2dms(solarSystem.sun.Declination));
		map.put("img", String.valueOf(R.drawable.ic_sun));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_moon));
		map.put("titre", getString(R.string.str_moon));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_moon));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_mercury));
		map.put("titre", getString(R.string.str_mercury));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_mercury));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_venus));
		map.put("titre", getString(R.string.str_venus));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_venus));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_mars));
		map.put("titre", getString(R.string.str_mars));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_mars));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_jupiter));
		map.put("titre", getString(R.string.str_jupiter));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_jupiter));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_saturn));
		map.put("titre", getString(R.string.str_saturn));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_saturn));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_uranus));
		map.put("titre", getString(R.string.str_uranus));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_uranus));
		listItem.add(map);

		map = new HashMap<String, String>();
		map.put("objectID", Integer.toString(R.string.str_neptune));
		map.put("titre", getString(R.string.str_neptune));
		map.put("line1", "Rise : ");
		map.put("line2", "Set : ");
		map.put("img", String.valueOf(R.drawable.ic_neptune));
		listItem.add(map);

		//Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
		SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.list_object_item,
				new String[]
						{
								"img", "titre", "line1", "line2"
						}, new int[]
				{
						R.id.img, R.id.titre, R.id.line1, R.id.line2
				});

		//On attribut à notre listView l'adapter que l'on vient de créer
		myListView.setAdapter(mSchedule);

		//Enfin on met un écouteur d'évènement sur notre listView
		myListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			@SuppressWarnings("unchecked")
			public void onItemClick(AdapterView<?> a, View v, int position, long id)
			{
				HashMap<String, String> map = (HashMap<String, String>) myListView.getItemAtPosition(position);
				currentObjectStringID = Integer.parseInt(map.get("objectID"));
				currentObjectImg = map.get("img");
				showDetails(currentObjectStringID, currentObjectImg);
				myTabHost.setCurrentTab(2);
			}
		});
	}

	//***********************************************************************
	// parseDBLocation()
	//***********************************************************************
	private fmrLocation parseDBLocation(String dbloc)
	{
		String loc[] = dbloc.split(":");
		return (new fmrLocation(loc[0], Double.parseDouble(loc[1]), Double.parseDouble(loc[2]), Double.parseDouble(loc[3])));
	}

	//***********************************************************************
	// computeObjects()
	//***********************************************************************
	private void computeObjects()
	{
		zeDate = new fmrDate();
		solarSystem.setDate(zeDate);
		solarSystem.setLocation(zeLocation);
	}

   /*
   //***********************************************************************
   // computeObject()
   //***********************************************************************
   private void computeObject(String object)
   {
      zeDate = new fmrDate();
      switch (object)
      {
         case "Sun":
            computeSun(zeDate, zeLocation);
            break;

         case "Moon":
            computeMoon(zeDate, zeLocation);
            break;

         case "Mercury":
            computeMercury(zeDate, zeLocation);
            break;

         case "Venus":
            computeVenus(zeDate, zeLocation);
            break;

         case "Mars":
            computeMars(zeDate, zeLocation);
            break;

         case "Jupiter":
            computeJupiter(zeDate, zeLocation);
            break;

         case "Saturn":
            computeSaturn(zeDate, zeLocation);
            break;

         case "Uranus":
            computeUranus(zeDate, zeLocation);
            break;

         case "Neptune":
            computeNeptune(zeDate, zeLocation);
            break;
      }
   }

   //***********************************************************************
   // computeSun()
   //***********************************************************************
   private void computeSun(fmrDate aDate, fmrLocation aLocation)
   {
      solarSystem.sun = new fmrSun(aDate, aLocation);
   }

   //***********************************************************************
   // computeMoon()
   //***********************************************************************
   private void computeMoon(fmrDate aDate, fmrLocation aLocation)
   {

   }

   //***********************************************************************
   // computeMercury()
   //***********************************************************************
   private void computeMercury(fmrDate aDate, fmrLocation aLocation)
   {

   }

   //***********************************************************************
   // computeVenus()
   //***********************************************************************
   private void computeVenus(fmrDate aDate, fmrLocation aLocation)
   {

   }

   //***********************************************************************
   // computeMars()
   //***********************************************************************
   private void computeMars(fmrDate aDate, fmrLocation aLocation)
   {

   }

   //***********************************************************************
   // computeJupiter()
   //***********************************************************************
   private void computeJupiter(fmrDate aDate, fmrLocation aLocation)
   {

   }

   //***********************************************************************
   // computeSaturn()
   //***********************************************************************
   private void computeSaturn(fmrDate aDate, fmrLocation aLocation)
   {

   }

   //***********************************************************************
   // computeUranus()
   //***********************************************************************
   private void computeUranus(fmrDate aDate, fmrLocation aLocation)
   {

   }

   //***********************************************************************
   // computeNeptune()
   //***********************************************************************
   private void computeNeptune(fmrDate aDate, fmrLocation aLocation)
   {

   }
   */

	//***********************************************************************
	// updateCoords()
	//***********************************************************************
	public void updateCoords()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Providers :");
		List<String> providers = locationManager.getProviders(true);

		// Will be updated if a valid location provider is found
		zeLocation = prefLocation;

		// Browse providers...
		for (String provider : providers)
		{
			stringBuilder.append("\n");
			stringBuilder.append(provider);
			stringBuilder.append(" : ");
			// ... and get location
			myLocation = locationManager.getLastKnownLocation(provider);
			if (myLocation != null)
			{
				double latitude = myLocation.getLatitude();
				double longitude = myLocation.getLongitude();
				stringBuilder.append(latitude);
				stringBuilder.append(", ");
				stringBuilder.append(longitude);
				zeLocation = new fmrLocation(latitude, longitude);
			} else
			{
				stringBuilder.append("Not available");
			}
		}
		Log.d("My_Position", stringBuilder.toString());
		// MessageBox("My position", stringBuilder.toString());
	}

	//***********************************************************************
	// MessageBox()
	//***********************************************************************
	private void MessageBox(String msg)
	{
		MessageBox("MessageBox", msg);
	}

	//***********************************************************************
	// MessageBox()
	//***********************************************************************
	private void MessageBox(String title, String msg)
	{
		AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
		adb.setTitle(title);
		adb.setMessage(msg);
		adb.setPositiveButton("Ok", null);
		adb.show();
	}

	//***********************************************************************
	// id2img()
	//***********************************************************************
	private String id2img(int id)
	{
		return String.format("<img src=\"%s\"/>", id);
	}

	//***********************************************************************
	// id2img()
	//***********************************************************************
	private String id2img(String id)
	{
		return String.format("<img src=\"%s\"/>", id);
	}

	//***********************************************************************
	// displayHtml()
	//***********************************************************************
	private void displayHtml(int id, String html)
	{
		displayHtml((TextView) findViewById(id), html);
	}

	//***********************************************************************
	// displayHtml()
	//***********************************************************************
	private void displayHtml(TextView tv, String html)
	{
		tv.setText(Html.fromHtml(html,
				new Html.ImageGetter()
				{
					@Override
					public Drawable getDrawable(final String source)
					{
						Drawable d = null;
						try
						{
							d = getResources().getDrawable(Integer.parseInt(source));
							d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
						} catch (Resources.NotFoundException e)
						{
							Log.e("Log_Tag", "Image not found. Check the ID.", e);
						} catch (NumberFormatException e)
						{
							Log.e("Log_Tag", "Source string not a valid resource ID.", e);
						}

						return d;
					}
				}, null));
	}

	//***********************************************************************
	// getClassBuildTime()
	//***********************************************************************
	private Date getClassBuildTime()
	{
		// DON'T FORGET TO ADD THE FOLLOWING LINE
		// android.keepTimestampsInApk = true
		// IN gradle.properties FILE
		Date d = null;

		try
		{
			ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), 0);
			ZipFile zf = new ZipFile(ai.sourceDir);
			ZipEntry ze = zf.getEntry("classes.dex");
			long time = ze.getTime();
			d = new java.util.Date(time);
			zf.close();
		}
		catch (Exception e)
		{
		}
		return d;
	}
}



