package com.gdu.letranbaosuong.dhpm11.androidxmlparser.DapImpls;

import android.content.Context;

import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Daos.IDao;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Country;
import com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models.Product;

// Bước 1: Import các thư viện
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DaoImpl implements IDao {
    @Override
    public XmlPullParser readXML(String fileName, Context context)
            throws XmlPullParserException, IOException {

        // Tạo đối tượng parser từ class XmlPullParser
        XmlPullParserFactory pullParserFactory;
        pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullParserFactory.newPullParser();

        // Tạo InputStream từ xml source (XML để trong assets)
        InputStream in_s = context.getApplicationContext().getAssets().open(fileName);
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(in_s, null);

        // Trả về đối tượng parser
        return parser;
    }

    @Override
    public ArrayList<Country> parseXMLCountry(XmlPullParser parser)
            throws XmlPullParserException, IOException {
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

    @Override
    public ArrayList<Product> parseXMLProduct(XmlPullParser parser)
            throws XmlPullParserException, IOException {

        ArrayList<Product> products = null; // Tạo danh sách rỗng kiểu Product
        int eventType = parser.getEventType(); // Lấy kiểu sự kiện trả về từ parser
        Product product = null; // Tạo đối tượng product rỗng

        while (eventType != XmlPullParser.END_DOCUMENT) { // Chưa kết thúc tài liệu
            String name;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT: // Bắt đầu tài liệu <products>
                    products = new ArrayList();
                    break;
                case XmlPullParser.START_TAG: // Bắt đầu TAG <product>
                    name = parser.getName(); // lấy tên của TAG, name = product
                    if (name.equals("product")) { // kiểm tra đúng là thẻ product ko
                        product = new Product();
                        // lấy giá trị của thuộc tính có name là "id"
                        product.setId(parser.getAttributeValue(null, "id"));
                    } else if (product != null) { // duyệt các thẻ bên trong của thẻ procduct
                        if (name.equals("name")) { // kiểm tra đúng là thẻ name ko
                            product.setName(parser.nextText()); // lấy nội dung trong thẻ name
                        } else if (name.equals("color")) { // kiểm tra đúng là thẻ color ko
                            product.setColor(parser.nextText()); // lấy nội dung trong thẻ color
                        } else if (name.equals("price")) { // kiểm tra đúng là thẻ price ko
                            product.setPrice(parser.nextText()); // lấy nội dung trong thẻ price
                        }
                    }
                    break;
                case XmlPullParser.END_TAG: // Kết thúc TAG </product>
                    name = parser.getName(); // lấy tên của TAG, name = product
                    if (name.equalsIgnoreCase("product") && product != null) {
                        products.add(product);
                    }
            }
            eventType = parser.next();
        }

        return products;
    }
}
