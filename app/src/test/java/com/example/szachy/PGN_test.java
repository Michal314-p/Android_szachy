package com.example.szachy;

import com.example.szachy.PGN.Zapis_partii;
import org.junit.Test;

public class PGN_test
{

    private String figura = "wc";
    private String ruch = "bicie";
    private String start = "a2";
    private String meta = "a3";


    @Test
    public void sprawdz_PGN()
    {
        Zapis_partii zapis_partii = new Zapis_partii();
        zapis_partii.zapis_pgn(figura,ruch,start,meta);
        String zapis = zapis_partii.pobierz_zapis_pgn();
        System.out.println(zapis);
    }

}
