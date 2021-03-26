package com.example.szachy.Figury;

public class Krol
{
    public String sprawdz_ruch_krola(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String przeciwnik, String gracz, int ruch_krola, int ruch_wiezy_l, int ruch_wiezy_p)
    {
        String ruch;

        ruch = ruchy_krola(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,przeciwnik,gracz);
        if(ruch.contains("ruch")||ruch.contains("bicie"))
        {
            if(ruch_na_mat(szachownica,poz_xk,poz_yk,przeciwnik,gracz)==true)
            {
                return "ruch_mat";
            }
            else
            {
                return ruch;
            }
        }

        ruch = roszada_dluga(szachownica,poz_xp,poz_yp,poz_xk,poz_yk, ruch_wiezy_l,ruch_krola);
        if(ruch.contains("roszada_dluga"))
        {
            if(ruch_na_mat(szachownica,poz_xk,poz_yk,przeciwnik,gracz)==true)
            {
                return "ruch_mat";
            }
            else
            {
                return ruch;
            }
        }

        ruch = roszada_krotka(szachownica,poz_xp,poz_yp,poz_xk,poz_yk, ruch_wiezy_p,ruch_krola);
        if(ruch.contains("roszada_krotka"))
        {
            if(ruch_na_mat(szachownica,poz_xk,poz_yk,przeciwnik,gracz)==true)
            {
                return "ruch_mat";
            }
            else
            {
                return ruch;
            }
        }
        else return "stop";
    }

    private String ruchy_krola(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String przeciwnik, String gracz)
    {
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
                if(i==0&&j==0) { }
                else
                {
                    try
                    {
                        if((szachownica[poz_xp+i][poz_yp+j].contains(gracz))&&poz_xp+i==poz_xk&&poz_yp+j==poz_yk)
                        {
                            return "stop";
                        }
                        if((szachownica[poz_xp+i][poz_yp+j].contains(przeciwnik))&&poz_xp+i==poz_xk&&poz_yp+j==poz_yk)
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
                        if(szachownica[poz_xp+i][poz_yp+j].equals("xx")&&poz_xp+i==poz_xk&&poz_yp+j==poz_yk)
                        {
                            return "ruch";
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

    private String roszada_krotka(String[][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, int ruch_wieza, int ruch_krol)
    {
        if(ruch_wieza==1 || ruch_krol==1)
        {
            return "stop";
        }
        if(szachownica[poz_xp][poz_yp+1].contains("b")||szachownica[poz_xp][poz_yp+1].contains("c"))
        {
            return "stop";
        }
        if(szachownica[poz_xp][poz_yp+2].contains("b")||szachownica[poz_xp][poz_yp+2].contains("c"))
        {
            return "stop";
        }
        if(szachownica[poz_xk][poz_yk].equals("xx")&&(poz_yk)==6)
        {
            return "roszada_krotka";
        }
        return "stop";
    }

    private String roszada_dluga(String[][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, int ruch_wieza, int ruch_krol)
    {
        if(ruch_wieza==1 || ruch_krol==1)
        {
            return "stop";
        }
        if(szachownica[poz_xp][poz_yp-1].contains("b")||szachownica[poz_xp][poz_yp-1].contains("c"))
        {
            return "stop";
        }
        if(szachownica[poz_xp][poz_yp-2].contains("b")||szachownica[poz_xp][poz_yp-2].contains("c"))
        {
            return "stop";
        }
        if(szachownica[poz_xp][poz_yp-3].contains("b")||szachownica[poz_xp][poz_yp-3].contains("c"))
        {
            return "stop";
        }
        if(szachownica[poz_xk][poz_yk].equals("xx")&&(poz_yk)==2)
        {
            return "roszada_dluga";
        }
        return "stop";
    }

    public boolean ruch_na_mat(String [][] szachownica, int poz_xk, int poz_yk, String przeciwnik, String gracz)
    {
        Goniec goniec = new Goniec();
        Skoczek skoczek = new Skoczek();
        Hetman hetman = new Hetman();
        Wieza wieza = new Wieza();
        PionekC pionekC = new PionekC();
        PionekB pionekB = new PionekB();

        int xp;
        int yp;
        String ruch_przeciwnika;
        for(int i=0;i<szachownica.length;i++)
        {
            for(int j=0;j<szachownica.length;j++)
            {
                if(szachownica[i][j].contains(przeciwnik))
                {
                    xp = i;
                    yp = j;


                    if(szachownica[xp][yp].contains("k"))
                    {
                        ruch_przeciwnika = sprawdz_bez_konsekwencji(szachownica,xp,yp,poz_xk,poz_yk,przeciwnik,gracz);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("g"))
                    {
                        ruch_przeciwnika = goniec.sprawdz_ruch_gonca(szachownica,xp,yp,poz_xk,poz_yk,gracz,przeciwnik);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("h"))
                    {
                        ruch_przeciwnika = hetman.sprawdz_ruch_hetmana(szachownica,xp,yp,poz_xk,poz_yk,gracz,przeciwnik);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("s"))
                    {
                        ruch_przeciwnika = skoczek.sprawdz_ruch_skoczka(szachownica,xp,yp,poz_xk,poz_yk,przeciwnik,gracz);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("w"))
                    {
                        ruch_przeciwnika = wieza.sprawdz_ruch_wiezy(szachownica,xp,yp,poz_xk,poz_yk,przeciwnik,gracz);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("p"))
                    {
                        if(przeciwnik.equals("c"))
                        {
                            ruch_przeciwnika = pionekC.bicie(szachownica,xp,yp,poz_xk,poz_yk);
                            if(ruch_przeciwnika.equals("promocja")||ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                            {
                                return true;
                            }
                        }
                        if(przeciwnik.equals("b"))
                        {
                            ruch_przeciwnika = pionekB.bicie(szachownica,xp,yp,poz_xk,poz_yk);
                            if(ruch_przeciwnika.equals("promocja")||ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                            {
                                return true;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }


    public boolean krol_w_szachu(String [][] szachownica, int poz_xk, int poz_yk, String przeciwnik, String gracz)
    {
        Goniec goniec = new Goniec();
        Skoczek skoczek = new Skoczek();
        Hetman hetman = new Hetman();
        Wieza wieza = new Wieza();
        PionekC pionekC = new PionekC();
        PionekB pionekB = new PionekB();

        int xp;
        int yp;
        String ruch_przeciwnika;
        for(int i=0;i<szachownica.length;i++)
        {
            for(int j=0;j<szachownica.length;j++)
            {
                if(szachownica[i][j].contains(gracz))
                {
                    xp = i;
                    yp = j;


                    if(szachownica[xp][yp].contains("g"))
                    {
                        ruch_przeciwnika = goniec.sprawdz_ruch_gonca(szachownica,xp,yp,poz_xk,poz_yk,gracz,przeciwnik);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("h"))
                    {
                        ruch_przeciwnika = hetman.sprawdz_ruch_hetmana(szachownica,xp,yp,poz_xk,poz_yk,gracz,przeciwnik);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("s"))
                    {
                        ruch_przeciwnika = skoczek.sprawdz_ruch_skoczka(szachownica,xp,yp,poz_xk,poz_yk,przeciwnik,gracz);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("w"))
                    {

                        ruch_przeciwnika = wieza.sprawdz_ruch_wiezy(szachownica,xp,yp,poz_xk,poz_yk,gracz,przeciwnik);
                        if(ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                        {
                            return true;
                        }
                    }
                    if(szachownica[xp][yp].contains("p"))
                    {
                        if(przeciwnik.equals("c"))
                        {
                            ruch_przeciwnika = pionekC.bicie(szachownica,xp,yp,poz_xk,poz_yk);
                            if(ruch_przeciwnika.equals("promocja")||ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                            {
                                return true;
                            }
                        }
                        if(przeciwnik.equals("b"))
                        {
                            ruch_przeciwnika = pionekB.bicie(szachownica,xp,yp,poz_xk,poz_yk);
                            if(ruch_przeciwnika.equals("promocja")||ruch_przeciwnika.equals("ruch")||ruch_przeciwnika.equals("bicie"))
                            {
                                return true;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }

    public boolean szach_mat(String [][] szachownica, String gracz, String przeciwnik)
    {
        Goniec goniec = new Goniec();
        Skoczek skoczek = new Skoczek();
        Hetman hetman = new Hetman();
        Wieza wieza = new Wieza();
        PionekC pionekC = new PionekC();
        PionekB pionekB = new PionekB();
        int x_krola = 0;
        int y_krola = 0;

        int []lokalizacja_krola = znajdz_krola(szachownica,gracz);
        x_krola = lokalizacja_krola[0];
        y_krola = lokalizacja_krola[1];

        for (int i=0;i<8;i++)
        {
            for (int j=0;j<8;j++)
            {
                try
                {
                    if(szachownica[i][j].contains(gracz))
                    {
                        for(int a=0;a<8;a++)
                        {
                            for (int b=0;b<8;b++)
                            {
                                try
                                {
                                    if(szachownica[i][j].contains("k"))
                                    {
                                        String [][] kopia = kopiuj(szachownica);
                                        kopia[i][j]="xx";
                                        if(sprawdz_ruch_krola(kopia,i,j,a,b,przeciwnik,gracz,1,1,1).equals("ruch")||sprawdz_ruch_krola(kopia,i,j,a,b,przeciwnik,gracz,1,1,1).contains("bicie"))
                                        {
                                            System.out.println(sprawdz_ruch_krola(kopia,i,j,a,b,przeciwnik,gracz,1,1,1));
                                            System.out.println(a);
                                            System.out.println(b);
                                            return false;
                                        }
                                    }
                                    if(szachownica[i][j].contains("w"))
                                    {
                                        String [][] kopia = kopiuj(szachownica);

                                        if(wieza.sprawdz_ruch_wiezy(kopia,i,j,a,b,gracz,przeciwnik).equals("ruch")||
                                            wieza.sprawdz_ruch_wiezy(kopia,i,j,a,b,gracz,przeciwnik).contains("bicie"))
                                        {
                                            kopia[i][j]="xx";
                                            kopia[a][b]="w"+gracz;
                                            if(!krol_w_szachu(kopia, x_krola, y_krola,gracz,przeciwnik))
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                    if(szachownica[i][j].contains("g"))
                                    {
                                        String [][] kopia = kopiuj(szachownica);

                                        if(goniec.sprawdz_ruch_gonca(kopia,i,j,a,b,gracz,przeciwnik).equals("ruch")||
                                                goniec.sprawdz_ruch_gonca(kopia,i,j,a,b,gracz,przeciwnik).contains("bicie"))
                                        {
                                            kopia[i][j]="xx";
                                            kopia[a][b]="w"+gracz;
                                            if(!krol_w_szachu(kopia, x_krola, y_krola, gracz, przeciwnik))
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                    if(szachownica[i][j].contains("h"))
                                    {
                                        String [][] kopia = kopiuj(szachownica);

                                        if(hetman.sprawdz_ruch_hetmana(kopia,i,j,a,b,gracz,przeciwnik).equals("ruch")||
                                                hetman.sprawdz_ruch_hetmana(kopia,i,j,a,b,gracz,przeciwnik).contains("bicie"))
                                        {
                                            kopia[i][j]="xx";
                                            kopia[a][b]="w"+gracz;
                                            if(!krol_w_szachu(kopia, x_krola, y_krola, gracz, przeciwnik))
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                    if(szachownica[i][j].contains("s"))
                                    {
                                        String [][] kopia = kopiuj(szachownica);

                                        if(skoczek.sprawdz_ruch_skoczka(kopia,i,j,a,b,przeciwnik,gracz).equals("ruch")||
                                                skoczek.sprawdz_ruch_skoczka(kopia,i,j,a,b,przeciwnik,gracz).contains("bicie"))
                                        {
                                            kopia[i][j]="xx";
                                            kopia[a][b]="w"+gracz;
                                            if(!krol_w_szachu(kopia, x_krola, y_krola, gracz, przeciwnik))
                                            {
                                                return false;
                                            }
                                        }
                                    }
                                }
                                catch (IndexOutOfBoundsException e) { }
                            }
                        }
                    }
                }
                catch (IndexOutOfBoundsException e) { }
            }
        }
        return true;
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

    public int[] znajdz_krola(String[][] szachownica,String gracz)
    {
        for (int i=0;i<szachownica.length;i++)
        {
            for (int j=0;j<szachownica.length;j++)
            {
                if (szachownica[i][j].equals("k"+gracz))
                {
                    int [] lokalizacja= {i,j};
                    return lokalizacja;
                }
            }
        }
        int [] blad={10,10};
        return blad;
    }

    public String sprawdz_bez_konsekwencji(String [][] szachownica, int poz_xp, int poz_yp, int poz_xk, int poz_yk, String przeciwnik, String gracz)
    {
        String ruch;

        ruch = ruchy_krola(szachownica,poz_xp,poz_yp,poz_xk,poz_yk,przeciwnik,gracz);
        if(ruch.contains("ruch")||ruch.contains("bicie"))
        {
            return ruch;
        }
        else
        {
            return "stop";
        }
    }
}
