package com.example.szachy;

import com.example.szachy.Figury.Wieza;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class WiezaTest
{

    private String[][] szachownica_testowa = {{"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","wc","xx","wb","xx","xx","xx"}};

    private int xp=7;
    private int yp=4;
    private int xk=7;
    private int yk=7;
    private String gracz="b";
    private String przeciwnik="c";

    @Test
    public void sprawdz_wieze()
    {
        String [][] kopia;
        kopia=kopiuj(szachownica_testowa);
        kopia[7][4]="xx";
        Wieza wieza = new Wieza();
        System.out.println(wieza.sprawdz_ruch_wiezy(szachownica_testowa,xp,yp,xk,yk,gracz,przeciwnik));
        for(int i=0;i<szachownica_testowa.length;i++)
        {
            for(int j=0;j<szachownica_testowa.length;j++)
            {
                System.out.print(szachownica_testowa[i][j]);
            }
            System.out.println();
        }
    }

    public String[][] kopiuj(String[][] szachownica)
    {
        String [][] kopia = new String[8][8];
        for(int i=0;i<szachownica.length;i++)
        {
            for(int j=0;j<szachownica.length;j++)
            {
                kopia[i][j]=szachownica[i][j];
            }
        }
        return kopia;
    }

}
