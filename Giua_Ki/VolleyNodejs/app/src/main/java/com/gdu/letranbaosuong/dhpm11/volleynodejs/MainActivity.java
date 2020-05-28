package com.gdu.letranbaosuong.dhpm11.volleynodejs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gdu.letranbaosuong.dhpm11.volleynodejs.Models.Product;
import com.gdu.letranbaosuong.dhpm11.volleynodejs.Widgets.CustomListViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private EditText name, price, color, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        color = findViewById(R.id.color);
        id = findViewById(R.id.id);
    }

    public void onClick(View v) {
//        getAll();
//        getID(id.getText().toString().trim());
//        themID();
        suaID(id.getText().toString().trim());
//        xoaID(id.getText().toString().trim());
    }

    private void getAll() {
        String url = "http://192.168.43.234:3000/products/"; // giao thuc http
//        String url = "http://192.168.1.100:3000/products/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Lấy 1 product
//                        Gson gson = new Gson();
//                        Product product = gson.fromJson(response, Product.class);
//                        Toast.makeText(MainActivity.this, product.toString(), Toast.LENGTH_SHORT).show();
//                        String[] mainTitle = new String[1];
//                        String[] subTitle = new String[1];
//                        String[] id = new String[1];
//                        for (int i = 0; i < 1; i++) {
//                            id[i] = String.valueOf(product.getId());
//                            mainTitle[i] = product.getName();
//                            subTitle[i] = product.getColor()
//                                    + " - "
//                                    + product.getPrice();
//                        }
//                        CustomListViewAdapter adapter = new CustomListViewAdapter(
//                                MainActivity.this,
//                                mainTitle,
//                                subTitle,
//                                id);
//                        list = findViewById(R.id.list);
//                        list.setAdapter(adapter);

                        // Lấy tất cả product
//                        Gson gson = new Gson();
//                        Product[] products = gson.fromJson(response, Product[].class);
//                        for (Product product : products) {
//                            Toast.makeText(MainActivity.this, product.toString(), Toast.LENGTH_SHORT).show();
//                        }
                        Gson gson = new Gson();
                        Type produstListType = new TypeToken<ArrayList<Product>>() {
                        }.getType();

                        ArrayList<Product> productArrayList = gson.fromJson(
                                response, produstListType
                        );

                        String[] mainTitle = new String[productArrayList.size()];
                        String[] subTitle = new String[productArrayList.size()];
                        String[] id = new String[productArrayList.size()];
                        for (int i = 0; i < productArrayList.size(); i++) {
                            id[i] = String.valueOf(productArrayList.get(i).getId());
                            mainTitle[i] = productArrayList.get(i).getName();
                            subTitle[i] = productArrayList.get(i).getColor()
                                    + " - "
                                    + productArrayList.get(i).getPrice();
                        }
                        // hiển thị lên ListView
                        CustomListViewAdapter adapter = new CustomListViewAdapter(
                                MainActivity.this,
                                mainTitle,
                                subTitle,
                                id);
                        list = findViewById(R.id.list);
                        list.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap();
                param.put("name", name.getText().toString().trim());
                param.put("color", color.getText().toString().trim());
                param.put("price", price.getText().toString().trim());
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getID(String _id) {
        String url = "http://192.168.43.234:3000/products/";
//        String url = "http://192.168.1.100:3000/products/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url + _id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Lấy 1 product
                        Gson gson = new Gson();
                        Product product = gson.fromJson(response, Product.class);
                        Toast.makeText(MainActivity.this, product.toString(), Toast.LENGTH_SHORT).show();
                        String[] mainTitle = new String[1];
                        String[] subTitle = new String[1];
                        String[] id = new String[1];
                        for (int i = 0; i < 1; i++) {
                            id[i] = String.valueOf(product.getId());
                            mainTitle[i] = product.getName();
                            subTitle[i] = product.getColor()
                                    + " - "
                                    + product.getPrice();
                        }
                        CustomListViewAdapter adapter = new CustomListViewAdapter(
                                MainActivity.this,
                                mainTitle,
                                subTitle,
                                id);
                        list = findViewById(R.id.list);
                        list.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void themID() {
        String url = "http://192.168.43.234:3000/products/";
//        String url = "http://192.168.1.100:3000/products/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        getAll();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap();
                param.put("name", name.getText().toString().trim());
                param.put("color", color.getText().toString().trim());
                param.put("price", price.getText().toString().trim());
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void suaID(String _id) {
        String url = "http://192.168.43.234:3000/products/";
//        String url = "http://192.168.1.100:3000/products/";

        StringRequest stringRequest = new StringRequest(Request.Method.PUT,
                url + _id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        getAll();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap();
                param.put("name", name.getText().toString().trim());
                param.put("color", color.getText().toString().trim());
                param.put("price", price.getText().toString().trim());
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void xoaID(String _id) {
        String url = "http://192.168.43.234:3000/products/";
//        String url = "http://192.168.1.100:3000/products/";

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE,
                url + _id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        getAll();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
