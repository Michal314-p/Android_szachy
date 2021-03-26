package com.example.szachy;

import com.example.szachy.Figury.Krol;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KrolTest
{

    private String[][] szachownica_testowa = {{"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","kc","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","kb","xx","xx","xx"}};

    private int xp=7;
    private int yp=4;
    private int xk=6;
    private int yk=4;
    private String gracz="b";
    private String przeciwnik="c";

    @Test
    public void sprawdz_krola()
    {
        Krol krol = new Krol();
        System.out.println(krol.sprawdz_ruch_krola(szachownica_testowa,xp,yp,xk,yk,przeciwnik,gracz,0,0,0));
        for(int i=0;i<szachownica_testowa.length;i++)
        {
            for(int j=0;j<szachownica_testowa.length;j++)
            {
                System.out.print(szachownica_testowa[i][j]);
            }
            System.out.println();
        }
    }

}
