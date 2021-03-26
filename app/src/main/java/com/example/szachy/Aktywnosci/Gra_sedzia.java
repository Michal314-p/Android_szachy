package com.example.szachy.Aktywnosci;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.szachy.Dialogi.*;
import com.example.szachy.R;
import com.example.szachy.Szachownica.Szachownica;

public class Gra_sedzia extends AppCompatActivity implements Promocja_pionka.Wynik_zwracania  {

    private EditText poz_poczatkowa;
    private EditText poz_koncowa;

    private ImageView obraz;
    private String ruch;
    private String wynik = "";

    private Button przycisk_zatwierdz_ruch;
    private Button przycisk_zakoncz;

    private Szachownica sz = new Szachownica(this);

    private String figura;
    private String promocja_kolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra_sedzia);

        przycisk_zakoncz = findViewById(R.id.zakoncz_partie);
        przycisk_zakoncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otworz_zakonczenie();
            }
        });

        przycisk_zatwierdz_ruch = findViewById(R.id.zatwierdz_ruch);
        przycisk_zatwierdz_ruch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                zatwierdz_ruch();
            }
        });

        poz_poczatkowa = findViewById(R.id.wspolrzedne_poczatkowe);
        poz_koncowa = findViewById(R.id.wspolrzedne_koncowe);
    }

    public void zatwierdz_ruch()
    {

        String start = poz_poczatkowa.getText().toString().toUpperCase();
        String meta = poz_koncowa.getText().toString().toUpperCase();
        ruch = sz.przypisz_figure(start,meta);
        if(ruch.equals("stop"))
        {
            wyswietl_blad_ruchu();
        }
        if(ruch.contains("promocja"))
        {
            if(ruch.contains("b"))
            {
                promocja_kolor = "b";
            }
            else
            {
                promocja_kolor = "c";
            }
            promocja_pionka();
        }
        if(ruch.contains("blad_tury"))
        {
            wyswietl_blad_tury();
        }
        if(ruch.contains("ruch_mat"))
        {
            wyswietl_blad_matu();
        }

        sz.wyswietl_plansze();
        if(sz.krol_w_szachu().equals("szach"))
        {
            sz.zapisz_szach();
            wyswietl_szach();
        }
        if(sz.krol_w_szachu().equals("mat"))
        {
            sz.zapisz_mat();
            zapisz_wynik();
            wyswietl_mat();
        }

    }


    private void wyswietl_blad_tury()
    {
        Blad_tury blad_tury = new Blad_tury();
        blad_tury.show(getSupportFragmentManager(),"informacja");
    }

    private void wyswietl_szach()
    {
        Szach szach = new Szach();
        szach.show(getSupportFragmentManager(),"informacja");
    }

    private void wyswietl_blad_ruchu()
    {
        Blad_ruchu blad_ruchu = new Blad_ruchu();
        blad_ruchu.show(getSupportFragmentManager(),"informacja");
    }

    private void wyswietl_blad_matu()
    {
        Blad_matu_krola blad_matu_krola = new Blad_matu_krola();
        blad_matu_krola.show(getSupportFragmentManager(),"informacja");
    }

    private void promocja_pionka()
    {
        Promocja_pionka promocja_pionka = new Promocja_pionka();
        promocja_pionka.show(getSupportFragmentManager(),"informacja");
    }

    private void wyswietl_mat()
    {
        Mat mat = new Mat();
        mat.show(getSupportFragmentManager(),"informacja");
    }

    private void zapisz_wynik()
    {
        String tura = sz.pobierz_ture();
        if(tura.equals("biale"))
        {
            wynik="0-1";
        }
        else
        {
            wynik="1-0";
        }
    }

    public void otworz_zakonczenie()
    {
        Intent otworz = new Intent(this, Zakonczenie_sedzia.class);
        otworz.putExtra("klucz", sz.pobierz_zapis_PGN());
        if(wynik.equals("0-1")||wynik.equals("1-0"))
        {
            otworz.putExtra("wynik",wynik);
        }
        else
        {
            otworz.putExtra("wynik","");
        }
        startActivity(otworz);
    }

    @Override
    public void aktywuj_promocje(String promocja)
    {

        String meta = poz_koncowa.getText().toString();
        figura = promocja;
        if(promocja_kolor.equals("b"))
        {
            if(figura.equals("Hetman")){figura="hb";}
            if(figura.equals("Goniec")){figura="gb";}
            if(figura.equals("Skoczek")){figura="sb";}
            if(figura.equals("Wieża")){figura="wb";}
            if(figura == null){figura="hb";}
        }
        else
        {
            if(figura.equals("Hetman")){figura="hc";}
            if(figura.equals("Goniec")){figura="gc";}
            if(figura.equals("Skoczek")){figura="sc";}
            if(figura.equals("Wieża")){figura="wc";}
            if(figura == null){figura="hc";}
        }

        System.out.println("figura:" + figura);
        System.out.println("miejsce:" +meta);

        int resId = getResources().getIdentifier(meta, "id", getPackageName());
        obraz = findViewById(resId);
        resId = getResources().getIdentifier(figura, "drawable", getPackageName());
        obraz.setImageResource(resId);
        sz.promocja_tablica(figura,meta);
    }
}
