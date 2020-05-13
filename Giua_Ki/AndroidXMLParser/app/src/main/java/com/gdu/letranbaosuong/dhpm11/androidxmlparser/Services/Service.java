package com.gdu.letranbaosuong.dhpm11.androidxmlparser.Services;

import android.content.Context;

import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Daos.IDao;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.DapImpls.DaoImpl;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Country;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class Service {
    private IDao iDao = new DaoImpl();

    public Service() {
    }

    public ArrayList<Country> parseXML(XmlPullParser parser) throws IOException, XmlPullParserException {
        return iDao.parseXML(parser);
    }

    public XmlPullParser readXML(String s, Context applicationContext) throws IOException, XmlPullParserException {
        return iDao.readXML(s, applicationContext);
    }
}
