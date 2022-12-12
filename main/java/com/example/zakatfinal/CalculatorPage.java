package com.example.zakatfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorPage extends AppCompatActivity {

    EditText goldWeightEditText, currentValueEditText;
    TextView q1TextView, q2TextView, q3TextView, q4TextView;
    RadioButton keepRadioButton, wearRadioButton;
    float goldWeight, currentValue, x, total, uruf, payable, totalZakat;
    SharedPreferences sharedPrefGold, sharedPrefValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_page);

        goldWeightEditText = findViewById(R.id.goldWeightEditText);
        currentValueEditText = findViewById(R.id.currentValueEditText);
        keepRadioButton = findViewById(R.id.keepRadioButton);
        wearRadioButton = findViewById(R.id.wearRadioButton);
        q1TextView = findViewById(R.id.q1TextView);
        q2TextView = findViewById(R.id.q2TextView);
        q3TextView = findViewById(R.id.q3TextView);
        q4TextView = findViewById(R.id.q4TextView);

        loadData();
    }

    public void loadData () {
        sharedPrefGold = this.getSharedPreferences("gWeight", Context.MODE_PRIVATE);
        goldWeight = sharedPrefGold.getFloat("gWeight", 0);
        goldWeightEditText.setText("" + goldWeight);

        sharedPrefValue = this.getSharedPreferences("cValue", Context.MODE_PRIVATE);
        currentValue = sharedPrefValue.getFloat("cValue", 0);
        currentValueEditText.setText("" + currentValue);
    }

    public void calculateButtonClick (View v) {
        try {

            goldWeight = Float.parseFloat(goldWeightEditText.getText().toString());
            currentValue = Float.parseFloat(currentValueEditText.getText().toString());

            if(keepRadioButton.isChecked()) {
                x = 85;
            }
            else if(wearRadioButton.isChecked()) {
                x = 200;
            }

            total = goldWeight * currentValue;
            uruf = goldWeight - x;

            q3TextView.setText("Uruf : " + uruf + "g");

            if (uruf <= 0) {
                uruf = 0;
            }

            payable = uruf * currentValue;
            totalZakat = (float) ( payable * 0.025);

            q1TextView.setText("Total value of gold : RM" + String.format("%.2f", total));
            q2TextView.setText("Total gold value that is zakat payable : RM" + String.format("%.2f", payable));
            q4TextView.setText("Total zakat : RM" + String.format("%.2f", totalZakat));


            //save data
            SharedPreferences.Editor editor1 = sharedPrefGold.edit();
            editor1.putFloat("gWeight", goldWeight);
            editor1.apply();

            SharedPreferences.Editor editor2 = sharedPrefValue.edit();
            editor2.putFloat("cValue", currentValue);
            editor2.apply();

        }
        catch (NumberFormatException nfe) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_LONG).show();
        }
    }

    public void clearButtonClick (View v) {

        if(goldWeightEditText.getText().toString().length() == 0) {
            if(currentValueEditText.getText().toString().length() == 0) {
                Toast.makeText(this, "Nothing to clear", Toast.LENGTH_LONG).show();
            }
        }
        else {
            goldWeightEditText.setText("");
            currentValueEditText.setText("");
            Toast.makeText(this, "Cleared", Toast.LENGTH_LONG).show();
        }
    }

}