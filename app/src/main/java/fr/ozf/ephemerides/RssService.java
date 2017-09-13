package fr.ozf.ephemerides;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;


public class RssService extends IntentService
{
	// private static final String RSS_LINK = "https://news.google.fr/news?cf=all&hl=fr&pz=1&ned=fr&output=rss";
	private static final String RSS_LINK = "http://feeds2.feedburner.com/LeJournalduGeek";
	public static final String ITEMS = "items";
	public static final String RECEIVER = "receiver";

	public RssService()
	{
		super("RssService");
	}

	@Override
	protected void onHandleIntent(Intent intent)
	{
		Log.d(Constants.TAG, "Service started");
		List<RssItem> rssItems = null;
		try
		{
			RssParser parser = new RssParser();
			rssItems = parser.parse(getInputStream(MainActivity.prefRSSLink));
		} catch (XmlPullParserException e)
		{
			Log.w(e.getMessage(), e);
		} catch (IOException e)
		{
			Log.w(e.getMessage(), e);
		}
		Bundle bundle = new Bundle();
		bundle.putSerializable(ITEMS, (Serializable) rssItems);
		ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
		receiver.send(0, bundle);
	}

	public InputStream getInputStream(String link)
	{
		try
		{
			URL url = new URL(link);
			return url.openConnection().getInputStream();
		} catch (IOException e)
		{
			Log.w(Constants.TAG, "Exception while retrieving the input stream", e);
			return null;
		}
	}
}