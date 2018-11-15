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
    String JS = "JSON FAILED";
    String json;
    double result;
    @Override
    protected Void doInBackground(Void... voids) {


            try {

                URL url = new URL("http://resources.finance.ua/ru/public/currency-cash.json");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

                //JSONObject jsonObj = new JSONObject(jsonStr);


                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }
                JSONObject JO = new JSONObject(data);
                JSONArray JA = JO.getJSONArray("organizations");
                JSONObject organization = JA.getJSONObject(0);
                JSONObject currency = organization.getJSONObject("currencies");
                JSONObject EUR = currency.getJSONObject("EUR");
                JSONObject USD = currency.getJSONObject("USD");
                JSONObject RUB = currency.getJSONObject("RUB");
                Currency EUR1 = new Currency(EUR.getDouble("ask"),EUR.getDouble("bid"));
                Currency USD1 = new Currency(USD.getDouble("ask"),USD.getDouble("bid"));
                Currency RUB1 = new Currency(RUB.getDouble("ask"),RUB.getDouble("bid"));
                String eu = "EURO "+EUR1.getAsk()+" "+EUR1.getBid();
                Log.e("EUR1",eu);
                String us = "DOLLAR "+USD1.getAsk()+" "+USD1.getBid();
                Log.e("USD1",us);
                String ru = "RUB "+RUB1.getAsk()+" "+RUB1.getBid();
                Log.e("RUB1",ru);
                json = MainActivity.editText.getText().toString();
                result = EUR1.ask * Double.parseDouble(json);
                json = result+"";


               /* for (int i = 0; i < JA.length(); i++) {
                    JSONObject J = (JSONObject) JA.get(i);

                    line = J.getString("ask");
                    Log.e(i+"st currency",line);
                    line = J.getString("bid");
                    Log.e(i+"st currency",line);
                    if (J.getString("title").equals("А-Банк")) {

                        Log.e("A-BANK", "AVAILABLE");

                    }
                }*/


            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }



        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.textView.setText(this.json);
    }
}
