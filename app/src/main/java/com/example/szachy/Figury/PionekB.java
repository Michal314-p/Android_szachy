package com.example.szachy.Figury;

public class PionekB
{
    public String sprawdz_ruch_pionka(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk)
    {
        String ruch;

        ruch = ruch_jeden(szachownica,poz_xp,poz_yp,poz_xk,poz_yk);
        if(ruch.contains("ruch")||ruch.contains("promocja")){return ruch;}

        ruch = ruch_dwa(szachownica,poz_xp,poz_yp,poz_xk,poz_yk);
        if(ruch.contains("ruch_dwa")){return ruch;}

        ruch = bicie(szachownica,poz_xp,poz_yp,poz_xk,poz_yk);
        if(ruch.contains("bicie")||ruch.contains("promocja")||ruch.contains("bicie_przelot")){return ruch;}

        else{return "stop";}
    }

    private String ruch_jeden (String[][] szachownica, int poz_xp, int poz_yp,int poz_xk, int poz_yk)
    {
        if(szachownica[poz_xp-1][poz_yp].equals("xx")&&poz_xp-1==poz_xk&&poz_yp==poz_yk)
        {
            if(poz_xk==0)
            {
                return "promocja";
            }
            return "ruch";
        }
        else
        {
            return "stop";
        }
    }

    private String ruch_dwa (String[][] szachownica, int poz_xp, int poz_yp,int poz_xk, int poz_yk)
    {
        try
        {
            if(szachownica[poz_xp-2][poz_yp].equals("xx")&&poz_xp-2==poz_xk&&poz_yp==poz_yk&&poz_xp==6)
            {
                return "ruch_dwa";
            }
            else
            {
                return "stop";
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            return "stop";
        }

    }
    public String bicie (String[][] szachownica, int poz_xp, int poz_yp,int poz_xk, int poz_yk)
    {
        try
        {
            if(szachownica[poz_xp-1][poz_yp+1].contains("c")&&poz_xp-1==poz_xk&&poz_yp+1==poz_yk)
            {
                if(szachownica[poz_xp-1][poz_yp+1].contains("przelot"))
                {
                    return "bicie_przelot";
                }
                if(poz_xk==0)
                {
                    return "promocja_bicie";
                }
                return "bicie";
            }
            if(szachownica[poz_xp-1][poz_yp-1].contains("c")&&poz_xp-1==poz_xk&&poz_yp-1==poz_yk)
            {
                if(szachownica[poz_xp-1][poz_yp-1].contains("przelot"))
                {
                    return "bicie_przelot";
                }
                if(poz_xk==0)
                {
                    return "promocja_bicie";
                }
                return "bicie";
            }
            else
            {
                return "stop";
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            return "stop";
        }
    }
}
