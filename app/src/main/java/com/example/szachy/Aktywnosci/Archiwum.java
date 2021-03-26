package com.example.szachy.Aktywnosci;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.szachy.Dialogi.Blad_pustego_wyboru;
import com.example.szachy.R;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Archiwum extends AppCompatActivity {


    private List<String> lista_plikow = new ArrayList();
    private ListView lista_archiwum;
    private EditText filtr;

    private Button filtr_przyciks;
    private Button zobacz_przycisk;
    private Button usun_przycisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archiwum);

        File[] files = getFilesDir().listFiles();
        for (int i = 1; i < files.length; i++)
        {
            lista_plikow.add(i - 1, files[i].getName());
        }

        filtr = findViewById(R.id.filtrowanie_tekst);
        filtr_przyciks = findViewById(R.id.filtrowanie_przycisk);
        zobacz_przycisk = findViewById(R.id.zobacz_partie);
        usun_przycisk = findViewById(R.id.usun_partie);

        filtr_przyciks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtruj();
            }
        });

        zobacz_przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otworz_przeglad();
            }
        });

        usun_przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usun_partie();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.element_listy, lista_plikow);

        lista_archiwum = findViewById(R.id.lista_arch);
        lista_archiwum.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lista_archiwum.setSelector(android.R.color.holo_blue_light);
        lista_archiwum.setAdapter(adapter);
    }

    private void filtruj()
    {
        String filtrowanie = filtr.getText().toString();
        List lista_filtrowana = new ArrayList();
        for(int i = 0; i< lista_plikow.size(); i++)
        {
            if(lista_plikow.get(i).contains(filtrowanie))
            {
                lista_filtrowana.add(lista_plikow.get(i));
            }
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.element_listy, lista_filtrowana);

        lista_archiwum = findViewById(R.id.lista_arch);
        lista_archiwum.setAdapter(adapter);
    }

    private void usun_partie()
    {
        try
        {
            int pozycja = lista_archiwum.getCheckedItemPosition();
            String nazwa_pliku = lista_archiwum.getItemAtPosition(pozycja).toString();
            File do_usuniecia = new File(getFilesDir().toString()+"/"+nazwa_pliku);
            do_usuniecia.delete();
            lista_plikow.clear();
            lista_archiwum.setAdapter(null);
            File[] files = getFilesDir().listFiles();
            for (int i = 1; i < files.length; i++)
            {
                lista_plikow.add(i - 1, files[i].getName());
            }
            ArrayAdapter adapter = new ArrayAdapter<>(this,
                    R.layout.element_listy, lista_plikow);

            lista_archiwum = findViewById(R.id.lista_arch);
            lista_archiwum.setAdapter(adapter);
        }
        catch (NullPointerException e)
        {
            wyswietl_blad_wyboru();
        }
    }

    public void otworz_przeglad()
    {
        try
        {
            Integer pozycja = lista_archiwum.getCheckedItemPosition();
            String nazwa_pliku = lista_archiwum.getItemAtPosition(pozycja).toString();
            Intent otworz = new Intent(this, Przeglad_partii.class);
            otworz.putExtra("klucz",nazwa_pliku);
            startActivity(otworz);
        }
        catch (NullPointerException e)
        {
            wyswietl_blad_wyboru();
        }
    }

    public void wyswietl_blad_wyboru()
    {
        Blad_pustego_wyboru blad_pustego_wyboru = new Blad_pustego_wyboru();
        blad_pustego_wyboru.show(getSupportFragmentManager(),"informacja");
    }

}
