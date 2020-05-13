package com.gdu.letranbaosuong.dhpm11.androidxmlparser.Daos;

import android.content.Context;

import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Country;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public interface IDao {
    XmlPullParser readXML(String fileName, Context context) throws XmlPullParserException, IOException;
    ArrayList<Country> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException;
}
