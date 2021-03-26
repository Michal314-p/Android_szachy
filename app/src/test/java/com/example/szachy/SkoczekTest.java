package com.example.szachy;

import com.example.szachy.Figury.Skoczek;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SkoczekTest
{

    private String[][] szachownica_testowa = {{"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","wb","wb","wb","xx","xx"},
            {"xx","xx","xx","wb","sb","wb","xx","xx"}};

    private int xp=7;
    private int yp=4;
    private int xk=5;
    private int yk=3;
    private String gracz="b";
    private String przeciwnik="c";

    @Test
    public void sprawdz_skoczka()
    {
        Skoczek skoczek = new Skoczek();
        assertEquals("ruch", skoczek.sprawdz_ruch_skoczka(szachownica_testowa,xp,yp,xk,yk,przeciwnik,gracz));
    }

}
