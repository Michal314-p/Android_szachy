package com.example.szachy;

import com.example.szachy.Figury.Goniec;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoniecTest
{

    private String[][] szachownica_testowa = {{"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","gb","xx","xx","xx"}};

    private int xp=7;
    private int yp=4;
    private int xk=6;
    private int yk=3;
    private String gracz="b";
    private String przeciwnik="c";

    @Test
    public void sprawdz_gonca()
    {
        Goniec goniec = new Goniec();
        assertEquals("ruch", goniec.sprawdz_ruch_gonca(szachownica_testowa,xp,yp,xk,yk,gracz,przeciwnik));
    }

}
