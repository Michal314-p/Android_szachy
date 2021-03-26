package com.example.szachy.Figury;

import java.util.ArrayList;
import java.util.List;

public class Wieza
{

    public String sprawdz_ruch_wiezy(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        String ruch;

        ruch = lewo(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = prawo(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = gora(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = dol(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        else{return "stop";}
    }

    public String dol(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        try
        {
            for(int i=1;i<szachownica.length;i++)
            {
                if(szachownica[poz_xp+i][poz_yp].equals("xx")&&poz_xp+i==poz_xk&&poz_yp==poz_yk)
                {
                    return "ruch";
                }
                if(szachownica[poz_xp+i][poz_yp].contains(gracz))
                {
                    return "stop";
                }
                if(szachownica[poz_xp+i][poz_yp].contains(przeciwnik)&&poz_xp+i!=poz_xk)
                {
                    return "stop";
                }
                if(szachownica[poz_xp+i][poz_yp].contains(przeciwnik)&&poz_xp+i==poz_xk&&poz_yp==poz_yk)
                {
                    if(szachownica[poz_xp+i][poz_yp].contains("przelot"))
                    {
                        return "bicie_przelot";
                    }
                    return "bicie";
                }
            }
            return "stop";
        }
        catch (ArrayIndexOutOfBoundsException e )
        {
            return "stop";
        }
    }

    public String gora(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        try
        {
            for(int i=1;i<szachownica.length;i++)
            {
                if(szachownica[poz_xp-i][poz_yp].equals("xx")&&poz_xp-i==poz_xk&&poz_yp==poz_yk)
                {
                    return "ruch";
                }
                if(szachownica[poz_xp-i][poz_yp].contains(gracz))
                {
                    return "stop";
                }
                if(szachownica[poz_xp-i][poz_yp].contains(przeciwnik)&&poz_xp-i!=poz_xk)
                {
                    return "stop";
                }
                if(szachownica[poz_xp-i][poz_yp].contains(przeciwnik)&&poz_xp-i==poz_xk&&poz_yp==poz_yk)
                {
                    if(szachownica[poz_xp-i][poz_yp].contains("przelot"))
                    {
                        return "bicie_przelot";
                    }
                    return "bicie";
                }
            }
            return "stop";
        }
        catch (ArrayIndexOutOfBoundsException e )
        {
            return "stop";
        }
    }

    public String prawo(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {

        try
        {
            for(int i=1;i<szachownica.length;i++)
            {
                if(szachownica[poz_xp][poz_yp+i].equals("xx")&&poz_xp==poz_xk&&poz_yp+i==poz_yk)
                {
                    return "ruch";
                }
                if(szachownica[poz_xp][poz_yp+i].contains(gracz))
                {
                    return "stop";
                }
                if(szachownica[poz_xp][poz_yp+i].contains(przeciwnik)&&poz_yp+i!=poz_yk)
                {
                    return "stop";
                }
                if(szachownica[poz_xp][poz_yp+i].contains(przeciwnik)&&poz_xp==poz_xk&&poz_yp+i==poz_yk)
                {
                    if(szachownica[poz_xp][poz_yp+i].contains("przelot"))
                    {
                        return "bicie_przelot";
                    }
                    return "bicie";
                }
            }
            return "stop";
        }
        catch (ArrayIndexOutOfBoundsException e )
        {
            return "stop";
        }
    }

    public String lewo(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {

        try
        {
            for(int i=1;i<szachownica.length;i++)
            {
                if(szachownica[poz_xp][poz_yp-i].equals("xx")&&poz_xp==poz_xk&&poz_yp-i==poz_yk)
                {
                    return "ruch";
                }
                if(szachownica[poz_xp][poz_yp-i].contains(gracz))
                {
                    return "stop";
                }
                if(szachownica[poz_xp][poz_yp-i].contains(przeciwnik)&&poz_yp-i!=poz_yk)
                {
                    return "stop";
                }
                if(szachownica[poz_xp][poz_yp-i].contains(przeciwnik)&&poz_xp==poz_xk&&poz_yp-i==poz_yk)
                {
                    if(szachownica[poz_xp][poz_yp-i].contains("przelot"))
                    {

                        return "bicie_przelot";
                    }
                    return "bicie";
                }
            }
            return "stop";
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            return "stop";
        }
    }
}
