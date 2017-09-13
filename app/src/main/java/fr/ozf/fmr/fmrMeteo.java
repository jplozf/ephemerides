package fmr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

// import javax.swing.ImageIcon;

//******************************************************************************
// ATTENTION - CAUTION - ACHTUNG - ATTENTION - CAUTION - ACHTUNG - ATTENTION - C
//******************************************************************************
// THIS CLASS CONTAINS SPECIFIC CODE FOR THE FOLLOWING WEATHER PROVIDER :
// meteorologic.net
//******************************************************************************
public class fmrMeteo
{
   public static final String METEOFILE_NAME = "meteo.xml";
   public static String WEATHER_CITY = "77330";
   public static String WEATHER_CITY_LAT = "48.7789";
   public static String WEATHER_CITY_LON = "-2.6850";
   public static String WEATHER_COUNTRY = "France";
   public static String WEATHER_ICONSET = "1";
   public static String WEATHER_TEMPERATURE = "CELSIUS";

   private static String ZePath = "";
   private File fMeteo = new File(this.METEOFILE_NAME);
   private final Logger logger = Logger.getLogger("");
/*
//******************************************************************************
// Meteo_1()
//******************************************************************************
   public fmrMeteo()
   {
      this(this.WEATHER_CITY, this.WEATHER_COUNTRY);
   }

//******************************************************************************
// Meteo_1()
//******************************************************************************
   public fmrMeteo(String zipcode, String country)
   {
      URL url = null;
      try
      {
         File jarFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
         ZePath = jarFile.getParent() + File.separator;
      } catch (Exception e)
      {
         logger.severe(e.getMessage());
      }

      // Read the contents:
      try
      {
         url = new URL("http://api.meteorologic.net/forecarss?p=" + zipcode + "&cn=" + country);
      } catch (MalformedURLException e)
      {
         // When the URL was not well-formed
         logger.severe(e.getMessage());
      }
      String content = readContents(url);
      // Write the contents:
      File output_file = new File(this.METEOFILE_NAME);
      writeContents(output_file, content);
   }

//******************************************************************************
// getCountries()
//******************************************************************************
   public ArrayList<String> getCountries()
   {
      ArrayList<String> countries = new ArrayList<String>();
      countries.add("France");
      countries.add("Belgique");
      countries.add("Suisse");

      return countries;
   }

//******************************************************************************
// getCity()
//******************************************************************************
   public String getCity()
   {
      try
      {
         Document doc = Jsoup.parse(fMeteo, "utf-8");
         Element city = doc.select("title").first();
         return city.text();
      } catch (Exception e)
      {
         logger.warning(e.getMessage());
         return "N/A";
      }
   }

//******************************************************************************
// getPubDate()
//******************************************************************************
   public String getPubDate()
   {
      try
      {
         Document doc = Jsoup.parse(fMeteo, "utf-8");
         Element update = doc.select("pubDate").first();
         return update.text();
      } catch (Exception e)
      {
         logger.warning(e.getMessage());
         return "N/A";
      }
   }

//******************************************************************************
// getWeatherReportFull()
//******************************************************************************
   public String getWeatherReportFull()
   {
      String html = "<html>\n";

      String p = getClass().getResource(JavardinApp.Ze_Logo_URL).toString();
      html += "<table width='100%'><tr><td><img src='" + p + "'></img></td>\n";
      html += "<td align='RIGHT' valign='TOP'><b>" + getCity() + "</b><br>\n";
      html += JavardinView.getI18NString("UPDATED_ON") + getPubDate() + "</td></tr></table>\n";

      html += getMeteoFull();

      html += "</html>\n";
      return html;
   }

//******************************************************************************
// getWeatherReport()
//******************************************************************************
   public String getWeatherReport()
   {
      String html = "<html>\n";

      String p = getClass().getResource(JavardinApp.Ze_Logo_URL).toString();
      html += "<table width='100%'><tr><td><img src='" + p + "'></img></td>\n";
      html += "<td align='RIGHT' valign='TOP'><b>" + getCity() + "</b><br>\n";
      html += JavardinView.getI18NString("UPDATED_ON") + getPubDate() + "</td></tr></table>\n";

      html += getMeteoFull();

      html += "</html>\n";
      return html;
   }

//******************************************************************************
// showWeatherReportLite()
//******************************************************************************
   public String showWeatherReportLite()
   {
      String html = "<table width='100%' cellspacing='2'>\n";
      html += "<tr>\n";
      html += "<th>" + JavardinView.getI18NString("MORNING") + "</th>\n";
      html += "<th>" + JavardinView.getI18NString("NOON") + "</th>\n";
      html += "<th>" + JavardinView.getI18NString("AFTERNOON") + "</th>\n";
      html += "<th>" + JavardinView.getI18NString("EVENING") + "</th>\n";
      html += "</tr>\n";

      try
      {
         Document doc = Jsoup.parse(fMeteo, "utf-8");
         Element meteo = doc.getElementsByTag("meteo:weather").first();
         html += "<tr>\n";
         html += "<td align='center'><table><tr><td>" + convertTemp(meteo.attr("tempe_matin")) + "</td><td><img src='" + getPictoIcon(meteo.attr("pictos_matin")) + "'</img></td></tr><tr><td colspan='2' align='center'>" + meteo.attr("namepictos_matin") + "</td></tr></table></td>\n";
         html += "<td align='center'><table><tr><td>" + convertTemp(meteo.attr("tempe_midi")) + "</td><td><img src='" + getPictoIcon(meteo.attr("pictos_midi")) + "'</img></td></tr><tr><td colspan='2' align='center'>" + meteo.attr("namepictos_midi") + "</td></tr></table></td>\n";
         html += "<td align='center'><table><tr><td>" + convertTemp(meteo.attr("tempe_apmidi")) + "</td><td><img src='" + getPictoIcon(meteo.attr("pictos_apmidi")) + "'</img></td></tr><tr><td colspan='2' align='center'>" + meteo.attr("namepictos_apmidi") + "</td></tr></table></td>\n";
         html += "<td align='center'><table><tr><td>" + convertTemp(meteo.attr("tempe_soir")) + "</td><td><img src='" + getPictoIcon(meteo.attr("pictos_soir")) + "'</img></td></tr><tr><td colspan='2' align='center'>" + meteo.attr("namepictos_soir") + "</td></tr></table></td>\n";
         html += "</tr>\n";
         html += "<tr><td colspan=\"4\" align=\"center\"><strong>" + getCity() + "</strong>, " + JavardinView.getI18NString("UPDATED_ON") + getPubDate() + "</td></tr>\n";
         html += "</table>\n";
         return html;
      } catch (Exception e)
      {
         logger.warning(e.getMessage());
         return "N/A";
      }
   }

//******************************************************************************
// getMeteoFull()
//******************************************************************************
   private String getMeteoFull()
   {
      String html = "<table width='100%' cellspacing='2'>\n";
      html += "<tr>\n";
      html += "<th bgcolor='" + JavardinApp.Ze_Color_1 + "'>" + JavardinView.getI18NString("DATE") + "</th>\n";
      html += "<th bgcolor='" + JavardinApp.Ze_Color_1 + "'>" + JavardinView.getI18NString("MORNING") + "</th>\n";
      html += "<th bgcolor='" + JavardinApp.Ze_Color_1 + "'>" + JavardinView.getI18NString("NOON") + "</th>\n";
      html += "<th bgcolor='" + JavardinApp.Ze_Color_1 + "'>" + JavardinView.getI18NString("AFTERNOON") + "</th>\n";
      html += "<th bgcolor='" + JavardinApp.Ze_Color_1 + "'>" + JavardinView.getI18NString("EVENING") + "</th>\n";
      html += "</tr>\n";

      try
      {
         Document doc = Jsoup.parse(fMeteo, "utf-8");
         Elements meteos = doc.getElementsByTag("meteo:weather");
         for (Element meteo : meteos)
         {
            html += "<tr>\n";
            html += "<th bgcolor='" + JavardinApp.Ze_Color_1 + "'>" + getDate(meteo.attr("date") + " " + Calendar.getInstance().get(Calendar.YEAR)) + "</th>\n";
            html += "<td bgcolor='" + JavardinApp.Ze_Color_2 + "' align='center'><table><tr><td>" + convertTemp(meteo.attr("tempe_matin")) + "</td><td><img src='" + getPictoIcon(meteo.attr("pictos_matin")) + "'</img></td></tr><tr><td colspan='2' align='center'>" + meteo.attr("namepictos_matin") + "</td></tr></table></td>\n";
            html += "<td bgcolor='" + JavardinApp.Ze_Color_2 + "' align='center'><table><tr><td>" + convertTemp(meteo.attr("tempe_midi")) + "</td><td><img src='" + getPictoIcon(meteo.attr("pictos_midi")) + "'</img></td></tr><tr><td colspan='2' align='center'>" + meteo.attr("namepictos_midi") + "</td></tr></table></td>\n";
            html += "<td bgcolor='" + JavardinApp.Ze_Color_2 + "' align='center'><table><tr><td>" + convertTemp(meteo.attr("tempe_apmidi")) + "</td><td><img src='" + getPictoIcon(meteo.attr("pictos_apmidi")) + "'</img></td></tr><tr><td colspan='2' align='center'>" + meteo.attr("namepictos_apmidi") + "</td></tr></table></td>\n";
            html += "<td bgcolor='" + JavardinApp.Ze_Color_2 + "' align='center'><table><tr><td>" + convertTemp(meteo.attr("tempe_soir")) + "</td><td><img src='" + getPictoIcon(meteo.attr("pictos_soir")) + "'</img></td></tr><tr><td colspan='2' align='center'>" + meteo.attr("namepictos_soir") + "</td></tr></table></td>\n";
            html += "</tr>\n";
         }
         html += "</table>\n";
         return html;
      } catch (Exception e)
      {
         logger.warning(e.getMessage());
         return "N/A";
      }
   }

//******************************************************************************
// getDate()
//******************************************************************************
   private String getDate(String date)
   {
      Date d = null;
      SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMMM yyyy");
      SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE dd MMMM Y");

      try
      {
         d = sdf1.parse(date);
         return (sdf2.format(d).toString());
      } catch (Exception e)
      {
         logger.warning(e.toString());
         return ("N/A");
      }
   }

//******************************************************************************
// convertTemp()
//******************************************************************************
   private String convertTemp(String t)
   {
      // Tc = (5/9)*(Tf-32)
      // Tf = (9/5)*(Tc+32)
      String out = t + "°C";
      if (this.WEATHER_TEMPERATURE.equals("FAHRENHEIT"))
      {
         Double f = Double.valueOf(t.trim()).doubleValue();
         f = (9.0 / 5.0) * (f + 32.0);
         DecimalFormat df = new DecimalFormat("########.00");
         out = df.format(f).toString() + "°F";
      }
      return out;
   }

//******************************************************************************
// getPictoIcon()
//******************************************************************************
   private String getPictoIcon(String name)
   {
      ImageIcon img = null;

      try
      {
         img = (new ImageIcon(getClass().getResource("resources/meteo/" + JavardinApp.WEATHER_ICONSET + "/" + name + ".png")));
      } catch (Exception e)
      {
         logger.warning(e.getMessage());
         img = (new ImageIcon(getClass().getResource("resources/meteo/na.png")));
      }
      String p = img.toString();
      return p;
   }

//******************************************************************************
// writeContents()
//******************************************************************************
   private void writeContents(File file, String content)
   {
      BufferedWriter out = null;
      try
      {
         out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
         // Write:
         out.write(content);
            /*
            Normally, you don't need to call this manually, because it is called when
            the "close()"-method is called. I like to do it anyhow...
             */
   /*
         out.flush();
      } catch (FileNotFoundException e)
      {
         // The given file could not be found
         logger.severe(e.getMessage());
      } catch (IOException e)
      {
         // There was a problem writing to the File
         logger.severe(e.getMessage());
      } finally
      {
         // Close the Streams to prevent memory-leaks:
         if (out != null)
         {
            try
            {
               out.close();
            } catch (IOException e)
            {
               logger.severe(e.getMessage());
            }
         }
      }
   }

//******************************************************************************
// readContents()
//******************************************************************************

   /**
    * Reads the raw-data from a given {@code URL}.</p>
    * This method has undefined behaviour for reading binary-
    * files. It is intended for plain-text files only.</p>
    * All possible exceptions are handled internally, this should
    * be changed when in real-use-scenarios...
    *
    * @param url the {@code URL} to read from.
    * @return the raw-content of the document at the given {@code URL}.
    */
   /*
   private String readContents(URL url)
   {
      BufferedReader in = null;
      try
      {
         URLConnection con = url.openConnection();
         in = new BufferedReader(new InputStreamReader(con.getInputStream()));
         // Load the contents:
         String line = in.readLine();
            /*
            You should use a StringBuilder when appending multiple times
            because it's faster then using normal concation (+=) with Strings.
             */
   /*
         StringBuilder builder = new StringBuilder();
         do
         {
            builder.append(line + "\n"); // Keep the line-endings (pretty print)
         }
         while ((line = in.readLine()) != null);
         // Return the contents:
         return builder.toString();
      } catch (IOException e)
      {
         // Problem reading from the URL
         logger.severe(e.getMessage());
      } finally
      {
         // Close the Streams to prevent memory-leaks:
         if (in != null)
         {
            try
            {
               in.close();
            } catch (IOException e)
            {
               logger.severe(e.getMessage());
            }
         }
      }
      return ""; // Better way to do that...
   }
   */
}
