package com.gdu.letranbaosuong.dhpm11.androidxmlparser.Widgets;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gdu.letranbaosuong.dhpm11.androidxmlparser.R;

public class CustomListViewAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final String[] id;

    public CustomListViewAdapter(Activity context, String[] maintitle, String[] subtitle, String[] id) {
        super(context, R.layout.custom_listview, maintitle);
        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
        this.id = id;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_listview, null, true);

        TextView titleText = rowView.findViewById(R.id.title);
        TextView imageView = rowView.findViewById(R.id.id);
        TextView subtitleText = rowView.findViewById(R.id.subtitle);

        titleText.setText(maintitle[position]);
        imageView.setText(id[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;
    }
}
