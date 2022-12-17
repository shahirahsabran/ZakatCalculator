package com.example.zakatfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        activityHyperlink();
    }

    private void activityHyperlink () {
        TextView textViewLink = findViewById(R.id.textView9);
        textViewLink.setMovementMethod(LinkMovementMethod.getInstance());
        textViewLink.setLinkTextColor(Color.RED);
    }
}