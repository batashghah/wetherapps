package com.example.wetherapps;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wetherapps.weatherapps.Wetherapps;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnsrc;
    EditText insertcity;
    ImageView imghum,imgtemp;
    TextView txthum,txttemp;
TextView today,onday,teoday,threeday;
ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnsrc=(Button) findViewById(R.id.btnserch);
        insertcity=(EditText)findViewById(R.id.insertcity);
        today= (TextView) findViewById(R.id.today);
        onday= (TextView) findViewById(R.id.onday);
        teoday= (TextView) findViewById(R.id.twoday);
        threeday= (TextView) findViewById(R.id.threeday);
        btnsrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= "https://openweathermap.org/data/2.5/weather?q="+insertcity.getText().toString()+",Ir&appid=b6907d289e10d714a6e88b30761fae22";
//https://openweathermap.org/data/2.5/weather?q=yazd,ir&appid=b6907d289e10d714a6e88b30761fae22
                        //"http://api.openweathermap.org/data/2.5/weather?q='" + insertcity.getText().toString() + "',Ir&APPID=0b3f7cfd2c37262f6d4cf37d3d78d814";
                        //"http://api.openweathermap.org/data/2.5/find?q='" + insert.getText().toString() + "'&units=metric&APPID=e71048ecc7fc82593843c936fe7d95c0";
                AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
                asyncHttpClient.get(url,new JsonHttpResponseHandler(){


                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Gson gson=new Gson();
                        Wetherapps responsee=gson.fromJson(response.toString(),Wetherapps.class);
                        String h=responsee.getMain().getHumidity().toString();
                        String s=responsee.getName().toString();
                        String t=responsee.getMain().getTemp().toString();

                        today.setText("humidity: "+h+"temp: "+t);
                        onday.setText("humidity: "+s);
                       //Log.d("babtak",s);
                        }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(MainActivity.this,"babak",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
