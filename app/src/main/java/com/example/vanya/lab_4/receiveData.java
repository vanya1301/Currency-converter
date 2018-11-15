package com.example.vanya.lab_4;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;


public class receiveData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String convertedSum;
    double result;
    Currency EUR;
    Currency USD;
    Currency RUB;

    @Override
    protected Void doInBackground(Void... voids) {


        try {

            URL url = new URL("http://resources.finance.ua/ru/public/currency-cash.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            // Я не знаю правильно ли я это сделал
            // I don't know if this is right {
            JSONObject JO = new JSONObject(data);
            JSONArray JA = JO.getJSONArray("organizations");
            JSONObject organization = JA.getJSONObject(0);
            JSONObject currency = organization.getJSONObject("currencies");
            JSONObject EURobj = currency.getJSONObject("EUR");
            JSONObject USDobj = currency.getJSONObject("USD");
            JSONObject RUBobj = currency.getJSONObject("RUB");
            EUR = new Currency(EURobj.getDouble("ask"), EURobj.getDouble("bid"));
            USD = new Currency(USDobj.getDouble("ask"), USDobj.getDouble("bid"));
            RUB = new Currency(RUBobj.getDouble("ask"), RUBobj.getDouble("bid"));
            // } But this is what i did
            // Но я сделал это так

            result = Convert(MainActivity.spinner.getSelectedItemPosition(), MainActivity.spinner2.getSelectedItemPosition());
            convertedSum = String.format("%.2f", result);



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
        MainActivity.textView.setText(this.convertedSum);
    }

    double Convert(int id1, int id2) {
        switch (id1) {
            case 0:
                if (id2 == 0)
                    return Double.parseDouble(MainActivity.editText.getText().toString());
                if (id2 == 1)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) / USD.ask;
                if (id2 == 2)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) / EUR.ask;
                if (id2 == 3)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) / RUB.ask;
                break;
            case 1:
                if (id2 == 0)
                    return (Double.parseDouble(MainActivity.editText.getText().toString())) * USD.ask;
                if (id2 == 1)
                    return Double.parseDouble(MainActivity.editText.getText().toString());
                if (id2 == 2)
                    return (Double.parseDouble(MainActivity.editText.getText().toString())) * USD.ask / EUR.ask;
                if (id2 == 3)
                    return (Double.parseDouble(MainActivity.editText.getText().toString())) * USD.ask / RUB.ask;
                break;

            case 2:
                if (id2 == 0)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) * EUR.ask;
                if (id2 == 1)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) * EUR.ask / USD.ask;
                if (id2 == 2)
                    return Double.parseDouble(MainActivity.editText.getText().toString());
                if (id2 == 3)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) * EUR.ask / RUB.ask;

                break;
            case 3:
                if (id2 == 0)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) * RUB.ask;
                if (id2 == 1)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) * RUB.ask / USD.ask;
                if (id2 == 2)
                    return Double.parseDouble(MainActivity.editText.getText().toString()) * RUB.ask / EUR.ask;
                if (id2 == 3)
                    return Double.parseDouble(MainActivity.editText.getText().toString());
                break;
        }
        return 0;
    }
}
// P.S. My apologize to man who will try check this out
// Мои извенения тому кто будет это проверять
