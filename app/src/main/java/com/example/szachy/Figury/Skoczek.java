package com.example.szachy.Figury;

public class Skoczek
{
    public String sprawdz_ruch_skoczka(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String przeciwnik, String gracz)
    {
        String ruch;

        ruch = ruchy_skoczka(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,przeciwnik,gracz);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}
        else return "stop";
    }

    private String ruchy_skoczka(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String przeciwnik, String gracz)
    {
        for(int i=-2;i<=2;i++)
        {
            for(int j=-2;j<=2;j++)
            {
                if(i==0||j==0||Math.abs(j)==Math.abs(i)) { }
                else
                {
                    try
                    {
                        if (szachownica[poz_xp+i][poz_yp+j].contains(gracz)&&poz_xp+i==poz_xk&&poz_yp+j==poz_yk)
                        {
                            return "stop";
                        }
                        if((szachownica[poz_xp+i][poz_yp+j].equals("xx"))&&poz_xp+i==poz_xk&&poz_yp+j==poz_yk)
                        {
                            return "ruch";
                        }
                        if(szachownica[poz_xp+i][poz_yp+j].contains(przeciwnik)&&poz_xp+i==poz_xk&&poz_yp+j==poz_yk)
                        {
                            if(szachownica[poz_xp+i][poz_yp+j].contains("przelot"))
                            {
                                return "bicie_przelot";
                            }
                            else
                            {
                                return "bicie";
                            }

                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                    }
                }
            }
        }
        return "stop";
    }
}
