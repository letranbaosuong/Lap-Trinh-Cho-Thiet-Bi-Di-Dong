package com.gdu.letranbaosuong.dhpm11.androidxmlparser.DapImpls;

import android.content.Context;

import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Daos.IDao;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Country;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DaoImpl implements IDao {
    @Override
    public XmlPullParser readXML(String fileName, Context context) throws XmlPullParserException, IOException {
        XmlPullParserFactory pullParserFactory;
        pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullParserFactory.newPullParser();

        InputStream in_s = context.getApplicationContext().getAssets().open(fileName);
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(in_s, null);

        return parser;
    }

    @Override
    public ArrayList<Country> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Country> countries = null;
        int eventType = parser.getEventType();
        Country country = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    countries = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("country")) {
                        country = new Country();
                        country.setId(parser.getAttributeValue(null, "id"));
                    } else if (country != null) {
                        if (name.equals("name")) {
                            country.setName(parser.nextText());
                        } else if (name.equals("capital")) {
                            country.setCapital(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("country") && country != null) {
                        countries.add(country);
                    }
            }
            eventType = parser.next();
        }

        return countries;
    }
}