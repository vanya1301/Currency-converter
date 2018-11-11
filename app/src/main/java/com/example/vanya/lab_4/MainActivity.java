package com.example.vanya.lab_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] currencies = {"USD", "EUR", "RUB"};
    Button button;
    Spinner spinner;
    Spinner spinner2;
    EditText editText;
    public static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("stroka", "KAKASHKA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        spinner = findViewById(R.id.currenciesList);
        spinner2 = findViewById(R.id.spinner);
        editText = findViewById(R.id.editText2);
        Spinner currenciesList = (Spinner) findViewById(R.id.currenciesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currenciesList.setAdapter(adapter);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
               receiveData process = new receiveData();
               process.execute();

                break;

            default:

                break;
        }
    }
}
