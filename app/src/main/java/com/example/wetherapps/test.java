package com.example.wetherapps;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wetherapps.weatherapps.Wetherapps;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

public class test extends AppCompatActivity {
Button btnserch;
EditText insertcity;
ImageView imghum,imgtemp;
TextView txthum,txttemp,txtname;

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);btnserch=(Button) findViewById(R.id.btnserch);
        insertcity=(EditText)findViewById(R.id.insertcity);
        hideSoftKeyboard(insertcity);

        txthum=(TextView)findViewById(R.id.txthum);
        txttemp=(TextView)findViewById(R.id.txttemp);
        txtname=(TextView)findViewById(R.id.txtname);
        imghum=(ImageView)findViewById(R.id.imghum);
        imgtemp=(ImageView)findViewById(R.id.imgtemp);
        insertcity=(EditText)findViewById(R.id.insertcity);
        btnserch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                insertcity.onEditorAction(EditorInfo.IME_ACTION_DONE);
                String url= "https://openweathermap.org/data/2.5/weather?q="+insertcity.getText().toString()+",Ir&appid=b6907d289e10d714a6e88b30761fae22";
                AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                asyncHttpClient.get(url,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                       JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Gson gson=new Gson();
                        Wetherapps responsee=gson.fromJson(response.toString()
                                ,Wetherapps.class);

                        //String hum =responsee.getMain().getHumidity().toString();
                        txthum.setText(responsee.getMain().getHumidity().toString());
                        txttemp.setText(responsee.getMain().getTemp().toString());
                        txtname.setText(responsee.getName().toString());
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                          JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(test.this,"babak",Toast.LENGTH_LONG).show();
                    }


                });
            }
        });
    }
}
