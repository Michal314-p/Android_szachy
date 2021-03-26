package com.example.szachy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.szachy.Aktywnosci.Archiwum;
import com.example.szachy.Aktywnosci.Gra_sedzia;

import java.nio.BufferUnderflowException;

public class MainActivity extends AppCompatActivity
{
    private Button przycisk_gra_sedzia;
    private Button przycisk_archwium;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        przycisk_gra_sedzia = findViewById(R.id.przycisk_sedzia);
        przycisk_gra_sedzia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                otworz_sedziowanie();
            }
        });

        przycisk_archwium = findViewById(R.id.otworz_archiwum);
        przycisk_archwium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otworz_arch();
            }
        });
    }

    public void otworz_sedziowanie()
    {
        Intent otworz = new Intent(this, Gra_sedzia.class);
        startActivity(otworz);
    }

    public void otworz_arch()
    {
        Intent otworz = new Intent(this, Archiwum.class);
        startActivity(otworz);
    }
}
