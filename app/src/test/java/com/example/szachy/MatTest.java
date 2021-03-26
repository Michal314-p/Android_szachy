package com.example.szachy;

import com.example.szachy.Figury.Krol;
import org.junit.Test;

public class MatTest
{

    private static String[][] szachownica_testowa = {
            {"kc","xx","xx","xx","xx","xx","wb","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","kb","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"},
            {"xx","xx","xx","xx","xx","xx","xx","xx"}};


    private String gracz="c";
    private String przeciwnik="b";

    @Test
    public void sprawdz_matowanie()
    {
        Krol krol = new Krol();
        boolean wynik = krol.szach_mat(szachownica_testowa,gracz,przeciwnik);
        System.out.println(wynik);
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