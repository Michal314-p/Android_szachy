package com.example.szachy.Figury;

public class Goniec
{
    public String sprawdz_ruch_gonca(String [][] szachownica_do_testow, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        String ruch;

        ruch = gora_lewa(szachownica_do_testow,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = gora_prawa(szachownica_do_testow,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = dol_lewa(szachownica_do_testow,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}

        ruch = dol_prawa(szachownica_do_testow,poz_xp,poz_yp,poz_xk,poz_yk,gracz,przeciwnik);
        if(ruch.contains("ruch")||ruch.contains("bicie")){return ruch;}
        else{return "stop";}
    }

    public String gora_lewa(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        try
        {
            for(int i=1;i<szachownica.length;i++)
            {

                if(szachownica[poz_xp-i][poz_yp+i].equals("xx")&&poz_xp-i==poz_xk&&poz_yp+i==poz_yk)
                {
                    return "ruch";
                }
                if(szachownica[poz_xp-i][poz_yp+i].contains(gracz))
                {
                    return "stop";
                }
                if(szachownica[poz_xp-i][poz_yp+i].contains(przeciwnik)&&poz_xp-i!=poz_xk)
                {
                    return "stop";
                }
                if(szachownica[poz_xp-i][poz_yp+i].contains(przeciwnik)&&poz_xp-i==poz_xk&&poz_yp+i==poz_yk)
                {
                    if(szachownica[poz_xp-i][poz_yp+i].contains("przelot"))
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

    public String gora_prawa(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        try
        {
            for(int i=1;i<szachownica.length;i++)
            {

                if(szachownica[poz_xp-i][poz_yp-i].equals("xx")&&poz_xp-i==poz_xk&&poz_yp-i==poz_yk)
                {
                    return "ruch";
                }
                if(szachownica[poz_xp-i][poz_yp-i].contains(gracz))
                {
                    return "stop";
                }
                if(szachownica[poz_xp-i][poz_yp-i].contains(przeciwnik)&&poz_xp-i!=poz_xk)
                {
                    return "stop";
                }
                if(szachownica[poz_xp-i][poz_yp-i].contains(przeciwnik)&&poz_xp-i==poz_xk&&poz_yp-i==poz_yk)
                {

                    if(szachownica[poz_xp-i][poz_yp-i].contains("przelot"))
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

    public String dol_lewa(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        try
        {
            for(int i=1;i<szachownica.length;i++)
            {

                if(szachownica[poz_xp+i][poz_yp+i].equals("xx")&&poz_xp+i==poz_xk&&poz_yp+i==poz_yk)
                {
                    return "ruch";
                }
                if(szachownica[poz_xp+i][poz_yp+i].contains(gracz))
                {
                    return "stop";
                }
                if(szachownica[poz_xp+i][poz_yp+i].contains(przeciwnik)&&poz_xp+i!=poz_xk)
                {
                    return "stop";
                }
                if(szachownica[poz_xp+i][poz_yp+i].contains(przeciwnik)&&poz_xp+i==poz_xk&&poz_yp+i==poz_yk)
                {
                    if(szachownica[poz_xp+i][poz_yp+i].contains("przelot"))
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

    public String dol_prawa(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String gracz, String przeciwnik)
    {
        try
        {
            for(int i=1;i<szachownica.length;i++)
            {

                if(szachownica[poz_xp+i][poz_yp-i].equals("xx")&&poz_xp+i==poz_xk&&poz_yp-i==poz_yk)
                {
                    return "ruch";
                }
                if(szachownica[poz_xp+i][poz_yp-i].contains(gracz))
                {
                    return "stop";
                }
                if(szachownica[poz_xp+i][poz_yp-i].contains(przeciwnik)&&poz_xp+i!=poz_xk)
                {
                    return "stop";
                }
                if(szachownica[poz_xp+i][poz_yp-i].contains(przeciwnik)&&poz_xp+i==poz_xk&&poz_yp-i==poz_yk)
                {
                    if(szachownica[poz_xp+i][poz_yp-i].contains("przelot"))
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
}
