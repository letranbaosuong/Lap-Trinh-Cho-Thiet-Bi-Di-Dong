package com.gdu.letranbaosuong.dhpm11.androidxmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Country;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Product;
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

        Service service = new Service();

        try {
            // doc file xml
            XmlPullParser parser = service.readXML("product.xml"
                    , getApplicationContext());

            // xu ly xml sang ArrayList<Product> cho java xu dung
            ArrayList<Product> countries = service.parseXMLProduct(parser);

            // java xu ly countries
            String[] mainTitle = new String[countries.size()];
            String[] subTitle = new String[countries.size()];
            String[] id = new String[countries.size()];

            for (int i = 0; i < countries.size(); i++) {
                id[i] = countries.get(i).getId();
                mainTitle[i] = countries.get(i).getName();
                subTitle[i] = countries.get(i).getColor() + " - " + countries.get(i).getPrice();
            }

            // hien thi gia tri len man hinh
            CustomListViewAdapter adapter = new CustomListViewAdapter(
                    this,
                    mainTitle,
                    subTitle,
                    id);
            list = findViewById(R.id.list);
            list.setAdapter(adapter);

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

    }
}
