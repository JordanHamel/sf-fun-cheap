package com.sf.funcheap;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemAdapter extends BaseAdapter {
    private List<RssParser.Item> items;
    private LayoutInflater layoutInflater;

    public ItemAdapter(Activity activity) {
        layoutInflater = activity.getLayoutInflater();
        items = new ArrayList<RssParser.Item>();
    }

    public void addItem(RssParser.Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        String title = "";
        String date = "";
        String location = "";

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_feed_item, viewGroup, false);
        }

        RssParser.Item item = items.get(i);

        Pattern titleP = Pattern.compile(": (.*) \\|");
        Matcher titleM = titleP.matcher(item.title);
        if (titleM.find( )) { title = titleM.group(1); }

        Pattern dateP = Pattern.compile("([0-9/]*)");
        Matcher dateM = dateP.matcher(item.title);
        if (dateM.find( )) { date = dateM.group(0); }

        Pattern locationP = Pattern.compile("([A-Za-z]*) -");
        Matcher locationM = locationP.matcher(item.title);
        if (locationM.find( )) { location = locationM.group(1); }

        TextView titleView = (TextView) convertView.findViewById(R.id.eventtitle);
        titleView.setText(title);

        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        dateView.setText(date);

        TextView locationView = (TextView) convertView.findViewById(R.id.location);
        locationView.setText(location);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.description);
        descriptionView.setText(item.description);

        RelativeLayout itemLayout = (RelativeLayout) convertView.findViewById(R.id.itemLayout);
        switch (i % 5) {
            case 0:
                itemLayout.setBackgroundColor(convertView.getResources().getColor(R.color.ltteal));
                break;
            case 1:
                itemLayout.setBackgroundColor(convertView.getResources().getColor(R.color.teal));
                break;
            case 2:
                itemLayout.setBackgroundColor(convertView.getResources().getColor(R.color.ltblue));
                break;
            case 3:
                itemLayout.setBackgroundColor(convertView.getResources().getColor(R.color.medblue));
                break;
            case 4:
                itemLayout.setBackgroundColor(convertView.getResources().getColor(R.color.cyan));
                break;
        }

        return convertView;
    }
}

