package com.aaron.tbav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class blackthorn2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_blackthorn2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void windingpath(View view) {
        Intent selectwindingpath = new Intent(this, Winding_path.class);
        startActivity(selectwindingpath);
    }

    public void whisperingtrail(View view) {
        Intent selectwhisperingtrail = new Intent(this, Whispering_trail.class);
        startActivity(selectwhisperingtrail);
    }

    public void shadowedgrove(View view) {
        Intent selectshadowedgrove = new Intent(this, Shadowed_grove.class);
        startActivity(selectshadowedgrove);
    }
}