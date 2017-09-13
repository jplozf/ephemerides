package fr.ozf.ephemerides;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RssFragment extends Fragment implements OnItemClickListener
{
   private ProgressBar progressBar;
   private ListView listView;
   private View view;
   private Spinner spinner;
   private EditText txtRSSLabel;
   private EditText txtRSSValue;
   private ArrayList<String> RSSFeeds = new ArrayList<String>();

   //***********************************************************************
   // onCreate()
   //***********************************************************************
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setRetainInstance(true);
   }

   //***********************************************************************
   // onCreateView()
   //***********************************************************************
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
   {
      if (view == null)
      {
         view = inflater.inflate(R.layout.fragment_layout, container, false);
         progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
         listView = (ListView) view.findViewById(R.id.listView);
         listView.setOnItemClickListener(this);

         spinner = (Spinner) view.findViewById(R.id.spinRSS);
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
         {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
               MainActivity.prefRSSLink = getResources().getStringArray(R.array.valRSS)[spinner.getSelectedItemPosition()];
               // Toast.makeText(getActivity(), "changed", Toast.LENGTH_LONG).show();
               startService();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
               //
            }
         });

         ImageView imgRSS = (ImageView) view.findViewById(R.id.imgRSS);
         imgRSS.setOnClickListener(new View.OnClickListener()
                                   {
                                      @Override
                                      public void onClick(View view)
                                      {
                                         showInputDialog();
                                      }
                                   }

         );


         startService();
      }
      else
      {
         // If we are returning from a configuration change:
         // "view" is still attached to the previous view hierarchy
         // so we need to remove it and re-attach it to the current one
         ViewGroup parent = (ViewGroup) view.getParent();
         parent.removeView(view);
      }
      return view;
   }

   //***********************************************************************
   // startService()
   //***********************************************************************
   private void startService()
   {
      Intent intent = new Intent(getActivity(), RssService.class);
      intent.putExtra(RssService.RECEIVER, resultReceiver);
      getActivity().startService(intent);
   }

   //***********************************************************************
   // showInputDialog()
   //***********************************************************************
   /**
    * Once the {@link RssService} finishes its task, the result is sent to this
    * ResultReceiver.
    */
   private final ResultReceiver resultReceiver = new ResultReceiver(new Handler())
   {
      @SuppressWarnings("unchecked")
      @Override
      protected void onReceiveResult(int resultCode, Bundle resultData)
      {
         progressBar.setVisibility(View.GONE);
         List<RssItem> items = (List<RssItem>) resultData.getSerializable(RssService.ITEMS);
         if (items != null)
         {
            RssAdapter adapter = new RssAdapter(getActivity(), items);
            listView.setAdapter(adapter);
         }
         else
         {
            Toast.makeText(getActivity(), "An error occured while downloading the rss feed.",
               Toast.LENGTH_LONG).show();
         }
      }
   };

   //***********************************************************************
   // onItemClick()
   //***********************************************************************
   @Override
   public void onItemClick(AdapterView<?> parent, View view, int position, long id)
   {
      RssAdapter adapter = (RssAdapter) parent.getAdapter();
      RssItem item = (RssItem) adapter.getItem(position);
      Uri uri = Uri.parse(item.getLink());
      Intent intent = new Intent(Intent.ACTION_VIEW, uri);
      startActivity(intent);
   }

   //***********************************************************************
   // showInputDialog()
   //***********************************************************************
   protected void showInputDialog()
   {
      // get prompts.xml view
      LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
      View promptView = layoutInflater.inflate(R.layout.input_rss_layout, null);
      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
      alertDialogBuilder.setView(promptView);

      txtRSSLabel = (EditText) promptView.findViewById(R.id.txtLabelRSS);
      txtRSSValue = (EditText) promptView.findViewById(R.id.txtValueRSS);

      // setup a dialog window
      alertDialogBuilder.setCancelable(false)
         .setPositiveButton("OK", new DialogInterface.OnClickListener()
         {
            public void onClick(DialogInterface dialog, int id)
            {
               // resultText.setText("Hello, " + editText.getText());
               addRSSFeed(txtRSSLabel.getText().toString(), txtRSSValue.getText().toString());
               saveArray();

            }
         })
         .setNegativeButton("Annuler", new DialogInterface.OnClickListener()
         {
            public void onClick(DialogInterface dialog, int id)
            {
               dialog.cancel();
            }
         });

      // create an alert dialog
      AlertDialog alert = alertDialogBuilder.create();
      alert.show();
   }


   //***********************************************************************
   // addRSSFeed()
   //***********************************************************************
   private void addRSSFeed(String label, String url)
   {
      RSSFeeds.add(label + "," + url);
   }


   //***********************************************************************
   // saveArray()
   //***********************************************************************
   private boolean saveArray()
   {
      SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
      SharedPreferences.Editor mEdit1 = sp.edit();
      mEdit1.putInt("Status_size", RSSFeeds.size());

      for (int i = 0; i < RSSFeeds.size(); i++)
      {
         mEdit1.remove("Status_" + i);
         mEdit1.putString("Status_" + i, RSSFeeds.get(i));
      }

      return mEdit1.commit();
   }

   //***********************************************************************
   // loadArray()
   //***********************************************************************
   private void loadArray(Context mContext)
   {
      SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
      RSSFeeds.clear();
      int size = mSharedPreference1.getInt("Status_size", 0);

      for (int i = 0; i < size; i++)
      {
         RSSFeeds.add(mSharedPreference1.getString("Status_" + i, null));
      }
      /*
      ArrayAdapter<ArrayList<String>> adapter = new ArrayAdapter<ArrayList<String>>(this, android.R.layout.simple_spinner_item, RSSFeeds);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(adapter);
      */
   }
}