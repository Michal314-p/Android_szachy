package com.example.szachy.Szachownica;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import com.example.szachy.Figury.PionekC;
import com.example.szachy.Figury.Wieza;
import com.example.szachy.Figury.Krol;
import com.example.szachy.Figury.PionekB;
import com.example.szachy.Figury.Goniec;
import com.example.szachy.Figury.Hetman;
import com.example.szachy.Figury.Skoczek;
import com.example.szachy.PGN.Zapis_partii;

public class Szachownica
{
    Context context;
    public Szachownica(Context context)
    {
        this.context=context;
    }

    private ImageView obraz;
    private int poz_x;
    private int poz_y;
    private int poz_xk;
    private int poz_yk;
    private String mozliwy_ruch = "stop";
    private String tura = "biale";
    private String gracz = "b";
    private String przeciwnik = "c";
    private String wykonana_tura = "";
    private String pozp_do_promo ="";
    private String pozk_do_promo ="";
    private Integer przelot_x = null;
    private Integer przelot_y = null;
    private int pionek_podwojny = 0;

    private int ruch_bialy_krol = 0;
    private int ruch_czarny_krol = 0;
    private int ruch_wieza_lewa_czarna = 0;
    private int ruch_wieza_lewa_biala = 0;
    private int ruch_wieza_prawa_czarna = 0;
    private int ruch_wieza_prawa_biala = 0;

    private String[][] szachownica_startowa = {{"wc","sc","gc","hc","kc","gc","sc","wc"},
                                              {"pc","pc","pc","pc","pc","pc","pc","pc"},
                                              {"xx","xx","xx","xx","xx","xx","xx","xx"},
                                              {"xx","xx","xx","xx","xx","xx","xx","xx"},
                                              {"xx","xx","xx","xx","xx","xx","xx","xx"},
                                              {"xx","xx","xx","xx","xx","xx","xx","xx"},
                                              {"pb","pb","pb","pb","pb","pb","pb","pb"},
                                              {"wb","sb","gb","hb","kb","gb","sb","wb"}};

    private String[][] szachownica_oznaczenia = {{"A8","B8","C8","D8","E8","F8","G8","H8"},
                                                {"A7","B7","C7","D7","E7","F7","G7","H7"},
                                               {"A6","B6","C6","D6","E6","F6","G6","H6"},
                                               {"A5","B5","C5","D5","E5","F5","G5","H5"},
                                               {"A4","B4","C4","D4","E4","F4","G4","H4"},
                                               {"A3","B3","C3","D3","E3","F3","G3","H3"},
                                               {"A2","B2","C2","D2","E2","F2","G2","H2"},
                                               {"A1","B1","C1","D1","E1","F1","G1","H1"}};
    private String[][] szachownica = szachownica_startowa;
    private Zapis_partii zapis_partii = new Zapis_partii();

    public String przypisz_figure(String poz_poczatkowa, String poz_koncowa)
    {
        if (tura.equals("biale"))
        {
            gracz="b";
            przeciwnik="c";
        }
        else
        {
            gracz="c";
            przeciwnik="b";
        }

        if(mozliwy_ruch.contains("promocja"))
        {
            zapis_partii.zapis_pgn(szachownica[poz_xk][poz_yk],mozliwy_ruch,pozp_do_promo,pozk_do_promo);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA: " + zapis_partii.pobierz_zapis_pgn());
        }
        mozliwy_ruch = "stop";
        for(int i=0;i<szachownica_startowa.length;i++)
        {
            for(int j=0;j<szachownica_startowa.length;j++)
            {
                if(szachownica_oznaczenia[i][j].equals(poz_poczatkowa))
                {
                    poz_x = i;
                    poz_y = j;
                }
                if(szachownica_oznaczenia[i][j].equals(poz_koncowa))
                {
                    poz_xk = i;
                    poz_yk = j;
                }
            }
        }
        if (szachownica[poz_x][poz_y].contains("c"))
        {
            wykonana_tura = "c";
        }
        else
        {
            wykonana_tura = "b";
        }
        if(tura.contains(wykonana_tura))
        {

        }
        else
        {
            return "blad_tury";
        }

        if(tura.equals("biale"))
        {
            sprawdz_biale();
        }

        if(tura.equals("czarne"))
        {
            sprawdz_czarne();
        }

        if(mozliwy_ruch.equals("ruch")||mozliwy_ruch.equals("bicie"))
        {
            if(przelot_x!=null)
            {
                bicie_w_przelocie();
            }

            zapis_partii.sprawdz_niejasnosci(szachownica,poz_xk,poz_yk,szachownica[poz_x][poz_y],gracz,przeciwnik);
            zapis_partii.zapis_pgn(szachownica[poz_x][poz_y],mozliwy_ruch,poz_poczatkowa,poz_koncowa);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA: " + zapis_partii.pobierz_zapis_pgn());

            zmien_figure(szachownica[poz_x][poz_y],poz_koncowa);
            szachownica[poz_xk][poz_yk] = szachownica[poz_x][poz_y];
            szachownica[poz_x][poz_y] = "xx";
            zmien_figure(szachownica[poz_x][poz_y],poz_poczatkowa);
            zmien_ture();
            return "ruch";
        }

        if(mozliwy_ruch.equals("ruch_dwa"))
        {
            if(przelot_x!=null)
            {
                bicie_w_przelocie();
            }

            zapis_partii.zapis_pgn(szachownica[poz_x][poz_y],mozliwy_ruch,poz_poczatkowa,poz_koncowa);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA: " + zapis_partii.pobierz_zapis_pgn());

            if(szachownica[poz_x][poz_y].equals("pc")){pionek_podwojny=-1;}
            if(szachownica[poz_x][poz_y].equals("pb")){pionek_podwojny=1;}
            zmien_figure(szachownica[poz_x][poz_y],poz_koncowa);
            szachownica[poz_xk][poz_yk] = szachownica[poz_x][poz_y];
            szachownica[poz_x][poz_y] = "xx";
            szachownica[poz_xk+pionek_podwojny][poz_yk] = "przelot"+tura;
            przelot_x = poz_xk+pionek_podwojny;
            przelot_y = poz_yk;
            zmien_figure(szachownica[poz_x][poz_y],poz_poczatkowa);
            zmien_ture();
            return "ruch";
        }

        if(mozliwy_ruch.equals("bicie_przelot"))
        {
            zapis_partii.sprawdz_niejasnosci(szachownica,poz_xk,poz_yk,szachownica[poz_x][poz_y],gracz,przeciwnik);
            zapis_partii.zapis_pgn(szachownica[poz_x][poz_y],mozliwy_ruch,poz_poczatkowa,poz_koncowa);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA: " + zapis_partii.pobierz_zapis_pgn());

            przelot_x = null;
            przelot_y = null;
            zmien_figure(szachownica[poz_x][poz_y],poz_koncowa);
            zmien_figure(szachownica[poz_xk][poz_yk],szachownica_oznaczenia[poz_xk-pionek_podwojny][poz_yk]);
            szachownica[poz_xk-pionek_podwojny][poz_yk] = "xx";
            szachownica[poz_xk][poz_yk] = szachownica[poz_x][poz_y];
            szachownica[poz_x][poz_y] = "xx";
            zmien_figure(szachownica[poz_x][poz_y],poz_poczatkowa);
            zmien_ture();
            return "ruch";
        }

        if(mozliwy_ruch.equals("roszada_krotka"))
        {
            if(przelot_x!=null)
            {
                bicie_w_przelocie();
            }

            zapis_partii.zapis_pgn(szachownica[poz_x][poz_y],mozliwy_ruch,poz_poczatkowa,poz_koncowa);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA: " + zapis_partii.pobierz_zapis_pgn());

            zmien_figure(szachownica[poz_x][poz_y+3],szachownica_oznaczenia[poz_x][poz_y+1]);
            zmien_figure(szachownica[poz_x][poz_y+1],szachownica_oznaczenia[poz_x][poz_y+3]);
            szachownica[poz_x][poz_y+1]=szachownica[poz_x][poz_y+3];
            szachownica[poz_x][poz_y+3]="xx";
            zmien_figure(szachownica[poz_x][poz_y],poz_koncowa);
            szachownica[poz_xk][poz_yk] = szachownica[poz_x][poz_y];
            szachownica[poz_x][poz_y] = "xx";
            zmien_figure(szachownica[poz_x][poz_y],poz_poczatkowa);
            zmien_ture();
            return "ruch";
        }

        if(mozliwy_ruch.equals("roszada_dluga"))
        {
            if(przelot_x!=null)
            {
                bicie_w_przelocie();
            }

            zapis_partii.zapis_pgn(szachownica[poz_x][poz_y],mozliwy_ruch,poz_poczatkowa,poz_koncowa);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA: " + zapis_partii.pobierz_zapis_pgn());

            zmien_figure(szachownica[poz_x][poz_y-4],szachownica_oznaczenia[poz_x][poz_y-1]);
            zmien_figure(szachownica[poz_x][poz_y-1],szachownica_oznaczenia[poz_x][poz_y-4]);
            szachownica[poz_x][poz_y-1]=szachownica[poz_x][poz_y-4];
            szachownica[poz_x][poz_y-4]="xx";
            zmien_figure(szachownica[poz_x][poz_y],poz_koncowa);
            szachownica[poz_xk][poz_yk] = szachownica[poz_x][poz_y];
            szachownica[poz_x][poz_y] = "xx";
            zmien_figure(szachownica[poz_x][poz_y],poz_poczatkowa);
            zmien_ture();

            return "ruch";
        }

        if(mozliwy_ruch.contains("promocja"))
        {
            String promocja_kolor = null;
            if(przelot_x!=null)
            {
                bicie_w_przelocie();
            }
            if(szachownica[poz_x][poz_y].contains("b"))
            {
                promocja_kolor = "b";
            }
            if(szachownica[poz_x][poz_y].contains("c"))
            {
                promocja_kolor = "c";
            }
            zmien_figure(szachownica[poz_x][poz_y],poz_koncowa);
            szachownica[poz_xk][poz_yk] = szachownica[poz_x][poz_y];
            szachownica[poz_x][poz_y] = "xx";
            zmien_figure(szachownica[poz_x][poz_y],poz_poczatkowa);
            zmien_ture();
            pozp_do_promo = poz_poczatkowa;
            pozk_do_promo = poz_koncowa;
            return "promocja"+promocja_kolor;
        }

        else
        {
            return mozliwy_ruch;
        }
    }

    private void sprawdz_biale()
    {
        if(szachownica[poz_x][poz_y].equals("pb"))
        {
            PionekB pb = new PionekB();
            mozliwy_ruch = pb.sprawdz_ruch_pionka(szachownica,poz_x,poz_y,poz_xk,poz_yk);
        }

        if(szachownica[poz_x][poz_y].equals("wb"))
        {
            Wieza wieza = new Wieza();
            mozliwy_ruch = wieza.sprawdz_ruch_wiezy(szachownica,poz_x,poz_y,poz_xk,poz_yk,"b", "c");
            if(mozliwy_ruch.contains("ruch"))
            {
                if(poz_y==0)
                {
                    ruch_wieza_lewa_biala=1;
                }
                else
                {
                    ruch_wieza_prawa_biala=1;
                }
            }
        }

        if(szachownica[poz_x][poz_y].equals("gb"))
        {
            Goniec goniec = new Goniec();
            mozliwy_ruch = goniec.sprawdz_ruch_gonca(szachownica,poz_x,poz_y,poz_xk,poz_yk,"b", "c");
        }

        if(szachownica[poz_x][poz_y].equals("sb"))
        {
            Skoczek skoczek = new Skoczek();
            mozliwy_ruch = skoczek.sprawdz_ruch_skoczka(szachownica,poz_x,poz_y,poz_xk,poz_yk,"c","b");
        }

        if(szachownica[poz_x][poz_y].equals("kb"))
        {
            Krol krol = new Krol();
            mozliwy_ruch = krol.sprawdz_ruch_krola(szachownica,poz_x,poz_y,poz_xk,poz_yk,"c","b",ruch_bialy_krol,ruch_wieza_lewa_biala,ruch_wieza_prawa_biala);
            if(mozliwy_ruch.contains("ruch"))
            {
                ruch_bialy_krol=1;
            }
        }

        if(szachownica[poz_x][poz_y].equals("hb"))
        {
            Hetman hetman = new Hetman();
            mozliwy_ruch = hetman.sprawdz_ruch_hetmana(szachownica,poz_x,poz_y,poz_xk,poz_yk,"b", "c");
        }
    }

    private void sprawdz_czarne()
    {
        if(szachownica[poz_x][poz_y].equals("wc"))
        {
            Wieza wieza = new Wieza();
            mozliwy_ruch = wieza.sprawdz_ruch_wiezy(szachownica,poz_x,poz_y,poz_xk,poz_yk,"c", "b");
            if(mozliwy_ruch.contains("ruch"))
            {
                if(poz_y==0)
                {
                    ruch_wieza_lewa_czarna=1;
                }
                else
                {
                    ruch_wieza_prawa_czarna=1;
                }
            }
        }

        if(szachownica[poz_x][poz_y].equals("gc"))
        {
            Goniec goniec = new Goniec();
            mozliwy_ruch = goniec.sprawdz_ruch_gonca(szachownica,poz_x,poz_y,poz_xk,poz_yk,"c", "b");
        }

        if(szachownica[poz_x][poz_y].equals("sc"))
        {
            Skoczek skoczek = new Skoczek();
            mozliwy_ruch = skoczek.sprawdz_ruch_skoczka(szachownica,poz_x,poz_y,poz_xk,poz_yk,"b","c");
        }

        if(szachownica[poz_x][poz_y].equals("pc"))
        {
            PionekC pc = new PionekC();
            mozliwy_ruch = pc.sprawdz_ruch_pionka(szachownica,poz_x,poz_y,poz_xk,poz_yk);
        }

        if(szachownica[poz_x][poz_y].equals("hc"))
        {
            Hetman hetman = new Hetman();
            mozliwy_ruch = hetman.sprawdz_ruch_hetmana(szachownica,poz_x,poz_y,poz_xk,poz_yk,"c", "b");
        }

        if(szachownica[poz_x][poz_y].equals("kc"))
        {
            Krol krol = new Krol();
            mozliwy_ruch = krol.sprawdz_ruch_krola(szachownica,poz_x,poz_y,poz_xk,poz_yk,"b","c",ruch_czarny_krol,ruch_wieza_lewa_czarna,ruch_wieza_prawa_czarna);
            if(mozliwy_ruch.contains("ruch"))
            {
                ruch_czarny_krol=1;
            }
        }
    }

    private void bicie_w_przelocie()
    {
        szachownica[przelot_x][przelot_y]="xx";
        przelot_x = null;
        przelot_y = null;
    }

    public void zapisz_szach()
    {
        zapis_partii.dopisz_szach();
    }

    public void zapisz_mat()
    {
        zapis_partii.dopisz_mat();
    }

    private void zmien_ture()
    {
        if(tura.equals("czarne")){tura="biale";}
        else {tura="czarne";}
    }

    public void promocja_tablica(String figura, String meta)
    {
        int x = 0;
        int y = 0;
        for(int i=0;i<szachownica_startowa.length;i++)
        {
            for(int j=0;j<szachownica_startowa.length;j++)
            {
                if(szachownica_oznaczenia[i][j].equals(meta))
                {
                    x = i;
                    y = j;
                }
            }
        }

        szachownica[x][y]=figura;
    }

    private void zmien_figure(String figura, String miejsce)
    {
        int resId = context.getResources().getIdentifier(miejsce, "id", context.getPackageName());
        obraz = ((Activity) context).findViewById(resId);
        resId = context.getResources().getIdentifier(figura, "drawable", context.getPackageName());
        obraz.setImageResource(resId);
    }

    public void wyswietl_plansze()
    {
        for(int i=0;i<szachownica_startowa.length;i++)
        {
            for(int j=0;j<szachownica_startowa.length;j++)
            {
                System.out.print(szachownica[i][j]);
            }
            System.out.println();
        }
    }

    public String pobierz_zapis_PGN()
    {
        return zapis_partii.pobierz_zapis_pgn();
    }

    public String pobierz_ture(){return tura;}

    public String krol_w_szachu()
    {

        Krol krol = new Krol();

        for(int i=0;i<szachownica_startowa.length;i++)
        {
            for(int j=0;j<szachownica_startowa.length;j++)
            {
                if(tura.equals("biale"))
                {
                    if(szachownica[i][j].equals("kb"))
                    {
                        if(krol.krol_w_szachu(szachownica, i, j, "b", "c"))
                        {
                            if(krol.szach_mat(szachownica,"b","c"))
                            {
                                return "mat";
                            }
                            else
                            {
                                return "szach";
                            }
                        }
                    }
                }
                if(tura.equals("czarne"))
                {
                    if(szachownica[i][j].equals("kc"))
                    {
                        if(krol.krol_w_szachu(szachownica, i, j, "c", "b"))
                        {
                            if(krol.szach_mat(szachownica,"c","b"))
                            {
                                return "mat";
                            }
                            else
                            {
                                return "szach";
                            }
                        }
                    }
                }
            }
        }
        return "nic";
    }

}
