package com.aaron.tbav;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StatsFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private TextView tvHealth, tvAttack, tvDefence, tvSpeed, tvIntelligence, tvPotions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        // Init DB
        dbHelper = new DatabaseHelper(getContext());

        // Bind TextViews
        tvHealth = view.findViewById(R.id.tvHealth);
        tvAttack = view.findViewById(R.id.tvAttack);
        tvDefence = view.findViewById(R.id.tvDefence);
        tvSpeed = view.findViewById(R.id.tvSpeed);
        tvIntelligence = view.findViewById(R.id.tvIntelligence);
        tvPotions = view.findViewById(R.id.tvPotions);

        // Load stats
        loadStats();

        return view;
    }

    private void loadStats() {
        Cursor cursor = dbHelper.getPlayerStats();
        if (cursor != null && cursor.moveToFirst()) {
            int currentHealth = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CURRENT_HEALTH));
            int maxHealth = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MAX_HEALTH));
            int attack = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ATTACK));
            int defence = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DEFENCE));
            int speed = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SPEED));
            int intelligence = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INTELLIGENCE));
            int potions = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_POTIONS));

            tvHealth.setText("Health: " + currentHealth + "/" + maxHealth);
            tvAttack.setText("Attack: " + attack);
            tvDefence.setText("Defence: " + defence);
            tvSpeed.setText("Speed: " + speed);
            tvIntelligence.setText("Intelligence: " + intelligence);
            tvPotions.setText("Potions: " + potions);

            cursor.close();
        }
    }

    // Call this whenever stats should refresh (e.g. after damage/potion)
    public void refreshStats() {
        loadStats();
    }
}
