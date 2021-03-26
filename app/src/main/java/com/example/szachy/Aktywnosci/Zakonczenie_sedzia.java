package com.example.szachy.Aktywnosci;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.example.szachy.Dialogi.Blad_pliku;
import com.example.szachy.Dialogi.Blad_pozwolenia;
import com.example.szachy.R;
import com.example.szachy.Dialogi.Niewypelnione_pole;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Zakonczenie_sedzia extends AppCompatActivity
{
    private EditText wydarzenie;
    private EditText miejsce;
    private EditText data;
    private EditText runda;
    private EditText biale;
    private EditText czarne;
    private EditText wynik;
    private EditText nazwa_pliku;

    private Button zatwierdz;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakonczenie_sedzia);

        wydarzenie = findViewById(R.id.wydarzenie_sedzia);
        miejsce = findViewById(R.id.miejsce_sedzia);
        data = findViewById(R.id.data_sedzia);
        runda = findViewById(R.id.runda_sedzia);
        biale = findViewById(R.id.bialy_sedzia);
        czarne = findViewById(R.id.czarny_sedzia);
        wynik = findViewById(R.id.wynik_sedzia);
        nazwa_pliku = findViewById(R.id.nazwa_pliku_sedzia);

        String wynik_pobrany = getIntent().getStringExtra("wynik");
        if(wynik_pobrany.equals("1-0")||wynik_pobrany.equals("0-1"))
        {
            wynik.setText(wynik_pobrany);
        }

        zatwierdz = findViewById(R.id.zatwierdz_koniec_partii_sedzia);
        zatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                zakoncz_partie_sedzia();
            }
        });

        int check = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (check != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1024);
        }
    }

    private void zakoncz_partie_sedzia()
    {
        if
        (
                wydarzenie.getText().toString().equals("") ||
                miejsce.getText().toString().equals("") ||
                data.getText().toString().equals("") ||
                runda.getText().toString().equals("") ||
                biale.getText().toString().equals("") ||
                czarne.getText().toString().equals("") ||
                wynik.getText().toString().equals("") ||
                nazwa_pliku.getText().toString().equals("")
        )
        {
            wyswietl_blad();
        }
        else
        {
            String wyd = wydarzenie.getText().toString();
            String mie = miejsce.getText().toString();
            String dat = data.getText().toString();
            String run = runda.getText().toString();
            String bia = biale.getText().toString();
            String cza = czarne.getText().toString();
            String wyn = wynik.getText().toString();
            String naz = nazwa_pliku.getText().toString();
            String przebieg = getIntent().getStringExtra("klucz");

            String str = wyd+System.lineSeparator()+
                    mie+System.lineSeparator()+
                    dat+System.lineSeparator()+
                    run+System.lineSeparator()+
                    bia+System.lineSeparator()+
                    cza+System.lineSeparator()+
                    wyn+System.lineSeparator()+
                    przebieg+System.lineSeparator();

            int check = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (check == PackageManager.PERMISSION_GRANTED)
            {
                List<String> lista_plikow = new ArrayList();
                String nazwa_pliku = "";
                File[] files = getFilesDir().listFiles();
                for (int i = 1; i < files.length; i++)
                {
                    lista_plikow.add(i - 1, files[i].getName());
                }

                for (int i = 0; i < lista_plikow.size(); i++)
                {

                    if(lista_plikow.get(i).equals(naz))
                    {
                        nazwa_pliku = lista_plikow.get(i);
                    }
                }

                if(nazwa_pliku.equals(naz))
                {
                    wyswietl_blad_pliku();
                }
                else
                {
                    zapisz_do_pliku(naz,str);
                }
            }
            else {
                wyswietl_blad_zgody();
            }
        }
    }

    public void wyswietl_blad()
    {
        Niewypelnione_pole niewypelnione_pole = new Niewypelnione_pole();
        niewypelnione_pole.show(getSupportFragmentManager(),"informacja");
    }

    public void wyswietl_blad_pliku()
    {
        Blad_pliku blad_pliku = new Blad_pliku();
        blad_pliku.show(getSupportFragmentManager(),"informacja");
    }

    public void wyswietl_blad_zgody()
    {
        Blad_pozwolenia blad_pozwolenia = new Blad_pozwolenia();
        blad_pozwolenia.show(getSupportFragmentManager(),"informacja");
    }

    private void zapisz_do_pliku(String nazawa_pliku, String dane_pliku)
    {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(nazawa_pliku, MODE_PRIVATE);
            fileOutputStream.write(dane_pliku.getBytes());

            Toast.makeText(this, "Zapisano w " + getFilesDir() + "/" + nazawa_pliku,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
