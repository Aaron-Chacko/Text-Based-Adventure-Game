package com.aaron.tbav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import android.widget.Toast;

public class knight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.knight);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int currentHealth = 100;
        int maxHealth = 100;
        int attack = 50;
        int defence = 30;
        int speed = 15;
        int intelligence = 20;
        int potions = 0;

        try (DatabaseHelper db = new DatabaseHelper(this)) {
            db.insertPlayerStats(currentHealth, maxHealth, attack, defence, speed, intelligence, potions);
        }
        catch (Exception e) {
            Log.e("Database Error", "Failed to insert player stats", e);
            Toast.makeText(this, "Failed to save your data. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }


    public void entervaloria(View view) {
        Intent valoria = new Intent(this, com.aaron.tbav.valoria.class);
        startActivity(valoria);
    }
}