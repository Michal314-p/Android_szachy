package com.example.szachy;

import com.example.szachy.Figury.Hetman;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HetmanTest
{

    private String[][] szachownica_testowa = {{"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","wc","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","hb","xx","xx","xx"}};

    private int xp=7;
    private int yp=4;
    private int xk=0;
    private int yk=4;
    private String gracz="b";
    private String przeciwnik="c";

    @Test
    public void sprawdz_hetmana()
    {
        Hetman hetman = new Hetman();
        assertEquals("stop", hetman.sprawdz_ruch_hetmana(szachownica_testowa,xp,yp,xk,yk,gracz,przeciwnik));
    }

}
