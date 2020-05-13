package com.gdu.letranbaosuong.dhpm11.androidxmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Country;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Services.Service;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Service service = new Service();

        try {
            XmlPullParser parser = service.readXML("country.xml", getApplicationContext());

            ArrayList<Country> countries = service.parseXML(parser);

            String text = "";

            for (Country country : countries) {
                text += "id : " + country.getId() + " name : " + country.getName() + " capital : " + country.getCapital() + "\n";
            }

            textView.setText(text);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

    }
}
