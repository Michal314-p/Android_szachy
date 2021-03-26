package com.example.szachy.Figury;

import com.example.szachy.Figury.Goniec;
import com.example.szachy.Figury.Wieza;

public class Hetman
{
    public String sprawdz_ruch_hetmana(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        Goniec goniec = new Goniec();
        Wieza wieza = new Wieza();

        String ruch;

        ruch = goniec.gora_lewa(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = goniec.gora_prawa(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = goniec.dol_lewa(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = goniec.dol_prawa(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = wieza.lewo(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = wieza.prawo(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = wieza.gora(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = wieza.dol(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}


        else{return "stop";}
    }
}
