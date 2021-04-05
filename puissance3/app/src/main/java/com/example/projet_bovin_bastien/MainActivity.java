package com.example.projet_bovin_bastien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView nomTextView;
    ImageView logoImageView;
    EditText firstEditText;
    EditText secondEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomTextView = findViewById(R.id.nomTextView);
        logoImageView = findViewById(R.id.logoImageView);
        firstEditText = findViewById(R.id.firstEditText);
        secondEditText = findViewById(R.id.secondEditText);
    }

    public void navigateToJeuActivity(View v) {
        Intent jeuActivityIntent = new Intent(this, JeuActivity.class);
        String p1 = firstEditText.getText().toString();
        String p2 = secondEditText.getText().toString();
        if (p1.length() != 0 && p2.length() != 0) {
            jeuActivityIntent.putExtra("J1", p1);
            jeuActivityIntent.putExtra("J2", p2);
            startActivity(jeuActivityIntent);
        }
        else{
            if(p1.length() == 0){
                firstEditText.setText("Nom Manquant !");
            }
            if(p2.length() == 0){
                secondEditText.setText("Nom manquant !");
            }
        }
        Intent EcranGagnantActivity = new Intent(this, JeuActivity.class);
        EcranGagnantActivity.putExtra("J1", p1);
        EcranGagnantActivity.putExtra("J2", p2);
    }
}