package com.example.szachy;

import com.example.szachy.Figury.PionekB;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PionekTest
{

    private String[][] szachownica_testowa = {{"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","pb","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"}};

    private int xp=6;
    private int yp=4;
    private int xk=4;
    private int yk=4;

    @Test
    public void sprawdz_pionek()
    {
        PionekB pionekB = new PionekB();
        assertEquals("ruch_dwa", pionekB.sprawdz_ruch_pionka(szachownica_testowa,xp,yp,xk,yk));
    }

}
