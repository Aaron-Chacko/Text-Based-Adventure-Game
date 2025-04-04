package com.aaron.tbav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cl_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_classselect);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void selectknight(View view) {
        Intent selectknightscr = new Intent(this, knight.class);
        startActivity(selectknightscr);
    }

    public void selectwizard(View view){
        Intent selectwizscr;
        selectwizscr = new Intent(this, wizard.class);
        startActivity(selectwizscr);
    }

    public void selectguardian(View view) {
        Intent selectguardianscr = new Intent(this, guardian.class);
        startActivity(selectguardianscr);
    }

    public void selectelf(View view) {
        Intent selectelfscr = new Intent(this, elf.class);
        startActivity(selectelfscr);
    }
}