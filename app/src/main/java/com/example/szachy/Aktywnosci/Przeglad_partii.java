package com.example.szachy.Aktywnosci;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.szachy.R;

import java.io.*;

public class Przeglad_partii extends AppCompatActivity {

    private TextView wydarzenie;
    private TextView miejsce;
    private TextView data;
    private TextView biale;
    private TextView czarne;
    private TextView wynik;
    private TextView runda;
    private TextView przebieg_pgn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przeglad_partii);

        runda = findViewById(R.id.runda_tekst);
        biale = findViewById(R.id.bialy_tekst);
        czarne = findViewById(R.id.czarny_tekst);
        miejsce = findViewById(R.id.miejsce_tekst);
        wydarzenie = findViewById(R.id.wydarzenie_tekst);
        data = findViewById(R.id.data_tekst);
        przebieg_pgn = findViewById(R.id.przebieg_tekst);
        wynik = findViewById(R.id.wynik_tekst);

        String nazwa_pliku = getIntent().getStringExtra("klucz");
        wczytaj_z_pliku(nazwa_pliku);
    }


    private void wczytaj_z_pliku(String nazwa)
    {
        int licznik_linii = 0;
        String wydarzenie_final = "";
        String miejsce_final = "";
        String data_final = "";
        String runda_final = "";
        String biale_final = "";
        String czarne_final = "";
        String wynik_final = "";
        String zapis_pgn_final = "";

        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = openFileInput(nazwa);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String tekst;
            while ((tekst = bufferedReader.readLine()) != null)
            {
                if(licznik_linii==0){wydarzenie_final=tekst;}
                if(licznik_linii==1){miejsce_final=tekst;}
                if(licznik_linii==2){data_final=tekst;}
                if(licznik_linii==3){runda_final=tekst;}
                if(licznik_linii==4){biale_final=tekst;}
                if(licznik_linii==5){czarne_final=tekst;}
                if(licznik_linii==6){wynik_final=tekst;}
                if(licznik_linii==7){zapis_pgn_final=tekst;}
                licznik_linii++;
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (fileInputStream != null)
            {
                try
                {
                    fileInputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        wydarzenie.setText(wydarzenie_final);
        miejsce.setText(miejsce_final);
        data.setText(data_final);
        runda.setText(runda_final);
        czarne.setText(czarne_final);
        biale.setText(biale_final);
        wynik.setText(wynik_final);
        przebieg_pgn.setText(zapis_pgn_final);
    }

}
