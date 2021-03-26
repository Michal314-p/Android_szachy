package com.example.szachy.PGN;

import com.example.szachy.Figury.PionekB;
import com.example.szachy.Figury.PionekC;
import com.example.szachy.Figury.Wieza;
import com.example.szachy.Figury.Skoczek;
import com.example.szachy.Figury.Goniec;
import com.example.szachy.Figury.Hetman;

public class Zapis_partii
{
    private String partia_PGN = "";
    private int nr_tury = 1;
    private int licznik = 0;
    private String bicie = ":";
    private String roszada_krotka = "O-O";
    private String roszada_dluga = "O-O-O";
    private String promocja = "=";
    private String szach = "+";
    private String mat = "X";
    private int niejasnosc = 0;


    public void zapis_pgn(String figura, String ruch, String start, String meta)
    {
        if(licznik%2==0)
        {
            partia_PGN+=nr_tury+". ";
            nr_tury++;
        }

        if(ruch.contains("ruch") && figura.contains("p"))
        {
            partia_PGN+=meta.toLowerCase()+" ";
        }

        if(ruch.contains("ruch")&& !figura.contains("p"))
        {
            if(niejasnosc>1)
            {
                partia_PGN+=figura.toUpperCase().charAt(0)+""+start.toLowerCase().charAt(0)+""+meta.toLowerCase()+" ";
            }
            else
            {
                partia_PGN+=figura.toUpperCase().charAt(0)+meta.toLowerCase()+" ";
            }
        }

        if(ruch.contains("bicie")&&figura.contains("p"))
        {
            partia_PGN+=start.toLowerCase().charAt(0)+bicie+meta.toLowerCase()+" ";
        }

        if(ruch.contains("bicie")&& !figura.contains("p")&& !ruch.contains("promocja"))
        {
            if(niejasnosc>1)
            {
                partia_PGN+=figura.toUpperCase().charAt(0)+""+start.toLowerCase().charAt(0)+""+bicie+""+meta.toLowerCase()+" ";
            }
            else
            {
                partia_PGN+=figura.toUpperCase().charAt(0)+bicie+meta.toLowerCase()+" ";
            }
        }

        if(ruch.contains("roszada_krotka"))
        {
            partia_PGN+=roszada_krotka+" ";
        }

        if(ruch.contains("roszada_dluga"))
        {
            partia_PGN+=roszada_dluga+" ";
        }

        if(ruch.contains("promocja"))
        {
            if(ruch.contains("bicie"))
            {
                partia_PGN+=start.toLowerCase().charAt(0)+bicie+meta.toLowerCase()+promocja+figura.toUpperCase().charAt(0)+" ";
            }
            else
            {
                partia_PGN+=meta.toLowerCase()+promocja+figura.toUpperCase().charAt(0)+" ";
            }
        }
        licznik++;
        niejasnosc=0;
    }

    public String pobierz_zapis_pgn()
    {
        return partia_PGN;
    }

    public void dopisz_szach()
    {
        partia_PGN = partia_PGN.substring(0, partia_PGN.length() - 1);
        partia_PGN+=szach+" ";
    }

    public void dopisz_mat()
    {
        partia_PGN = partia_PGN.substring(0, partia_PGN.length() - 1);
        partia_PGN+=mat+" ";
    }

    public void sprawdz_niejasnosci(String [][] szachownica, int xk, int yk, String figura, String gracz, String przeciwnik)
    {
        Hetman hetman = new Hetman();
        Goniec goniec = new Goniec();
        Skoczek skoczek = new Skoczek();
        Wieza wieza = new Wieza();
        PionekB pionekB = new PionekB();
        PionekC pionekC = new PionekC();

        for(int i=0;i<szachownica.length;i++)
        {
            for(int j=0;j<szachownica.length;j++)
            {
                if(szachownica[i][j].equals(figura))
                {
                    if(figura.contains("pc"))
                    {
                        if(!pionekC.sprawdz_ruch_pionka(szachownica,i,j,xk,yk).contains("stop"))
                        {
                            niejasnosc++;
                        }
                    }
                    if(figura.contains("pb"))
                    {
                        if(!pionekB.sprawdz_ruch_pionka(szachownica,i,j,xk,yk).contains("stop"))
                        {
                            niejasnosc++;
                        }
                    }
                    if(figura.contains("w"))
                    {
                        if(!wieza.sprawdz_ruch_wiezy(szachownica,i,j,xk,yk,gracz,przeciwnik).contains("stop"))
                        {
                            niejasnosc++;
                        }
                    }
                    if(figura.contains("s"))
                    {
                        if(!skoczek.sprawdz_ruch_skoczka(szachownica,i,j,xk,yk,przeciwnik,gracz).contains("stop"))
                        {
                            niejasnosc++;
                        }
                    }
                    if(figura.contains("g"))
                    {
                        if(!goniec.sprawdz_ruch_gonca(szachownica,i,j,xk,yk,gracz,przeciwnik).contains("stop"))
                        {
                            niejasnosc++;
                        }
                    }
                    if(figura.contains("h"))
                    {
                        if(!hetman.sprawdz_ruch_hetmana(szachownica,i,j,xk,yk,gracz,przeciwnik).contains("stop"))
                        {
                            niejasnosc++;
                        }
                    }
                }
            }
        }
    }


}
