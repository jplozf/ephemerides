package fr.ozf.ephemerides;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RssAdapter extends BaseAdapter
{
   private final List<RssItem> items;
   private final Context context;
   private int[] TitleColors = new int[]{Color.parseColor("#ACC9F2"), Color.parseColor("#BFBFBF")};
   private int[] TextColors = new int[]{Color.parseColor("#DFE8F5"), Color.parseColor("#F0F0F0")};

   public RssAdapter(Context context, List<RssItem> items)
   {
      this.items = items;
      this.context = context;
   }

   @Override
   public int getCount()
   {
      return items.size();
   }

   @Override
   public Object getItem(int position)
   {
      return items.get(position);
   }

   @Override
   public long getItemId(int id)
   {
      return id;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent)
   {
      ViewHolder holder;
      if (convertView == null)
      {
         convertView = View.inflate(context, R.layout.rss_item, null);
         holder = new ViewHolder();
         holder.itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
         holder.itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
         convertView.setTag(holder);
      }
      else
      {
         holder = (ViewHolder) convertView.getTag();
      }
      holder.itemTitle.setText(items.get(position).getTitle());
      holder.itemDescription.setText(items.get(position).getDescription());

      int colorPos = position % TextColors.length;
      holder.itemTitle.setBackgroundColor(TitleColors[colorPos]);
      holder.itemDescription.setBackgroundColor(TextColors[colorPos]);

      // convertView.setBackgroundColor(colors[colorPos]);

      return convertView;
   }

   static class ViewHolder
   {
      TextView itemTitle;
      TextView itemDescription;
   }
}