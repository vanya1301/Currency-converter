package com.example.vanya.lab_4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class receiveData extends AsyncTask<Void,Void,Void>  {
    String data = "";
    String dataParsed;
    String singleParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {
        String json;
        try {

            URL url = new URL("http://resources.finance.ua/ru/public/currency-cash.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            String line = "";
            while(line != null)
            {
                line = bufferedReader.readLine();
                data = data + line;
            }

            Log.d("JSONREADER",data);

            JSONArray JA = new JSONArray(data);
            JSONObject JS = new JSONObject(data);   
            for(int i=0;i< JA.length();i++)
            {
                JSONObject JO = (JSONObject) JA.get(i);
                if(JO.getString("title").equals("\\u0410-\\u0411\\u0430\\u043d\\u043a"))
                {

                    Log.d("A-BANK","AVAILABLE");
                }
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.textView.setText(this.data);
        Log.d("stroka", this.data);
    }
}
