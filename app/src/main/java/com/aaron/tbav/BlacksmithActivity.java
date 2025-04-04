package com.aaron.tbav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BlacksmithActivity extends AppCompatActivity {

    private int attack;
    private int defence;
    private int potions;
    private DatabaseHelper db; // Declare your database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_blacksmith);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize your database helper and fetch stats
        db = new DatabaseHelper(this);
        attack = db.getPlayerAttack(); // Get the current attack value
        // Assuming you will use this in the future
        defence = db.getPlayerDefence(); // Update to use your method
        // Assuming you will use this in the future
        potions = db.getPlayerPotions(); // Update to use your method

        // Initialize the button--------------------------------------------------------------------------
        Button refineWeapon = findViewById(R.id.btn_refine_weapon);
        Button fortifyArmor = findViewById(R.id.btn_fortify_armor);
        Button receivePotions = findViewById(R.id.btn_receive_potions);

        // Set the OnClickListener for the button---------------------------------------------------------
        refineWeapon.setOnClickListener(v -> {
            attack += 5; // Increase the attack by 5
            db.updatePlayerStat(DatabaseHelper.COLUMN_ATTACK, attack, 1); // Update the database using the individual update method
            Toast.makeText(BlacksmithActivity.this, "Weapon refined! Attack increased by 5.", Toast.LENGTH_SHORT).show();// Display confirmation
            refineWeapon.setEnabled(false);
        });

        fortifyArmor.setOnClickListener(v -> {
            defence += 5;
            db.updatePlayerStat(DatabaseHelper.COLUMN_DEFENCE, defence, 1);
            Toast.makeText(BlacksmithActivity.this, "Armor fortified! Defence increased by 5.", Toast.LENGTH_SHORT).show();
            fortifyArmor.setEnabled(false);
        });

        receivePotions.setOnClickListener(v -> {
            potions += 3;
            db.updatePlayerStat(DatabaseHelper.COLUMN_POTIONS, potions, 1);
            Toast.makeText(BlacksmithActivity.this, "3 Potions received!", Toast.LENGTH_SHORT).show();
            receivePotions.setEnabled(false);
        });
    }

    public void blackthorn(View view) {
        Intent selectblackthorn = new Intent(this, blackthorn_forest.class);
        startActivity(selectblackthorn);
    }
}
