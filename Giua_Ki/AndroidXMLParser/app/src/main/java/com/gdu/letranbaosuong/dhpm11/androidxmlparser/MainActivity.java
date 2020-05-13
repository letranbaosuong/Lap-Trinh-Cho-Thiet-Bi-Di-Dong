package com.gdu.letranbaosuong.dhpm11.androidxmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Country;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Services.Service;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Widgets.CustomListViewAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = findViewById(R.id.textView);

        Service service = new Service();

        try {
            XmlPullParser parser = service.readXML("country.xml", getApplicationContext());

            ArrayList<Country> countries = service.parseXML(parser);

            String text = "";

            String[] mainTitle = new String[countries.size()];
            String[] subTitle = new String[countries.size()];
            String[] id = new String[countries.size()];

            for (Country country : countries) {
                text += "id : " + country.getId() + " name : " + country.getName() + " capital : " + country.getCapital() + "\n";
            }

            for (int i = 0; i < countries.size(); i++) {
                id[i] = countries.get(i).getId();
                mainTitle[i] = countries.get(i).getName();
                subTitle[i] = countries.get(i).getCapital();
            }

//            textView.setText(text);

            CustomListViewAdapter adapter = new CustomListViewAdapter(this, mainTitle, subTitle, id);
            list = findViewById(R.id.list);
            list.setAdapter(adapter);

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

    }
}
