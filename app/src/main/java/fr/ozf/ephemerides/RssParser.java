package fr.ozf.ephemerides;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RssParser
{
   // We don't use namespaces
   private final String ns = null;

   public List<RssItem> parse(InputStream inputStream) throws XmlPullParserException, IOException
   {
      try
      {
         XmlPullParser parser = Xml.newPullParser();
         parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
         parser.setInput(inputStream, null);
         parser.nextTag();
         return readFeed(parser);
      } finally
      {
         inputStream.close();
      }
   }

   private List<RssItem> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException
   {
      parser.require(XmlPullParser.START_TAG, null, "rss");
      String title = null;
      String link = null;
      String description = null;
      List<RssItem> items = new ArrayList<RssItem>();
      boolean inItem = false;
      while (parser.next() != XmlPullParser.END_DOCUMENT)
      {
         if (parser.getEventType() != XmlPullParser.START_TAG)
         {
            continue;
         }
         String name = parser.getName();
         if (name.equals(("item")))
         {
            inItem = true;
         }
         if (name.equals("title"))
         {
            title = readTitle(parser);
         }
         else if (name.equals("link"))
         {
            link = readLink(parser);
         }
         else if (name.equals("description"))
         {
            description = readDescription(parser);
         }
         if (title != null && link != null && description != null && inItem == true)
         {
            RssItem item = new RssItem(title, link, description);
            items.add(item);
            title = null;
            link = null;
            description = null;
            inItem = false;
         }
      }
      return items;
   }

   private String readLink(XmlPullParser parser) throws XmlPullParserException, IOException
   {
      parser.require(XmlPullParser.START_TAG, ns, "link");
      String link = readText(parser);
      parser.require(XmlPullParser.END_TAG, ns, "link");
      return link;
   }

   private String readTitle(XmlPullParser parser) throws XmlPullParserException, IOException
   {
      parser.require(XmlPullParser.START_TAG, ns, "title");
      String title = readText(parser);
      parser.require(XmlPullParser.END_TAG, ns, "title");
      return android.text.Html.fromHtml(title).toString();
   }

   private String readDescription(XmlPullParser parser) throws XmlPullParserException, IOException
   {
      parser.require(XmlPullParser.START_TAG, ns, "description");
      String description = readText(parser);
      parser.require(XmlPullParser.END_TAG, ns, "description");
      return android.text.Html.fromHtml(description.replaceAll("<img.+?>", "")).toString();
   }

   // For the tags title and link, extract their text values.
   private String readText(XmlPullParser parser) throws IOException, XmlPullParserException
   {
      String result = "";
      if (parser.next() == XmlPullParser.TEXT)
      {
         result = parser.getText();
         parser.nextTag();
      }
      return result;
   }
}