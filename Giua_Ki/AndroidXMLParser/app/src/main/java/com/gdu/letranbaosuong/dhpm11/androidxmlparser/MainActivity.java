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
            // Bước 1: Đọc file xml
            XmlPullParser parser = service.readXML("product.xml"
                    , getApplicationContext());

            // Bước 2: Xử lý xml sang ArrayList<Product> cho java sử dụng
            ArrayList<Product> products = service.parseXMLProduct(parser);

            // Bước 3: Java xư lý products
            String[] mainTitle = new String[products.size()];
            String[] subTitle = new String[products.size()];
            String[] id = new String[products.size()];

            for (int i = 0; i < products.size(); i++) {
                id[i] = products.get(i).getId();
                mainTitle[i] = products.get(i).getName();
                subTitle[i] = products.get(i).getColor()
                        + " - "
                        + products.get(i).getPrice();
            }

            // Bước 4: Hiển thị các thông tin lên màn hình
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
