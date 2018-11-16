package com.example.vanya.lab_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] currencies = {"UAH","USD", "EUR", "RUB"};
    Button button;
    public static Spinner spinner;
    public static Spinner spinner2;
    public static TextView textView;
    public static EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        spinner = findViewById(R.id.currenciesList);
        spinner2 = findViewById(R.id.spinner);
        editText = findViewById(R.id.editText2);
        Spinner currenciesList =  findViewById(R.id.currenciesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currenciesList.setAdapter(adapter);
        Spinner spinner = findViewById(R.id.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if(editText.getText().length() == 0)
                {
                    Toast.makeText(getApplicationContext(),"Fill the field!!!",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    receiveData process = new receiveData();
                    process.execute();
                }
                break;

            default:

                break;
        }
    }


}
