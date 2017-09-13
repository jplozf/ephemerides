package fr.ozf.ephemerides;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

//******************************************************************************
// ChartActivity()
//******************************************************************************
public class ChartActivity extends Activity
{

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);

      TextView textview = new TextView(this);
      textview.setText("This is the Chart tab");
      setContentView(textview);
   }
}
