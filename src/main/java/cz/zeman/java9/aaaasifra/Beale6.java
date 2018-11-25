/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *
 * @author martin
 */
public class Beale6 {

    private final byte[][] tabulka;
    private final int n;

    public Beale6(int n) {
        this.n = n;
        this.tabulka = this.init(n);
    }

    public byte[][] init(int n) {
        byte[][] tabulka = new byte[n][n];
        for (int i = 0; i < n; i++) {

            int k = i;
            for (int j = 0; j < n - i; j++) {
                tabulka[i][j] = (byte) k++;
            }

            k = 0;
            for (int j = n - i; j < n; j++) {
                tabulka[i][j] = (byte) k++;
            }
        }
        return tabulka;
    }

    public byte getElement(int i, int j) {
        return tabulka[i][j];
    }

    public byte getSloupcovyIndex(int radek, byte hodnota) {
        for (int j = 0; j < n; j++) {
            if (this.tabulka[radek][j] == hodnota) {
                return (byte) j;
            }
        }
        return -1;
    }

    public byte[] shipher(byte[] klic, byte[] salt, byte[] sifrovany_text) {
        byte[] zasifrovano = new byte[sifrovany_text.length];
        int indxklic = 0;
        for (int i = 0; i < sifrovany_text.length; i++) {
            byte znak = sifrovany_text[i];
            znak = (byte) (znak ^ salt[indxklic]);
            int radek = klic[indxklic] & 0xFF;
            int sloupec = znak & 0xFF;
            byte element = this.getElement(radek, sloupec);
            element = (byte) (element ^ klic[indxklic]);
            zasifrovano[i] = element;
            indxklic++;
            if (indxklic >= klic.length) {
                indxklic = 0;
            }
        }
        return zasifrovano;
    }

    public byte[] deshipher(byte[] klic, byte[] salt, byte[] desifrovany_text) {
        byte[] desifrovano = new byte[desifrovany_text.length];
        int indxklic = 0;
        for (int i = 0; i < desifrovany_text.length; i++) {
            byte znak = desifrovany_text[i];
            znak = (byte) (znak ^ klic[indxklic]);
            int radek = klic[indxklic] & 0xFF;
            byte element = this.getSloupcovyIndex(radek, znak);
            element = (byte) (element ^ salt[indxklic]);
            desifrovano[i] = element;
            indxklic++;
            if (indxklic >= klic.length) {
                indxklic = 0;
            }
        }
        return desifrovano;

    }

    public byte[] doplnKlicNeboSul(byte[] klic) {
        byte[] korigovany;
        int pocetBloku = klic.length / 3;
        int pocetBytuPoslednihoBloku = klic.length - 3 * pocetBloku;
        if (pocetBytuPoslednihoBloku == 0) {
            korigovany = new byte[3 * pocetBloku];
            for (int i = 0; i < 3 * pocetBloku; i++) {
                korigovany[i] = klic[i];
            }
            return korigovany;
        } else {
            korigovany = new byte[3 * (pocetBloku + 1)];
            for (int i = 0; i < 3 * pocetBloku; i++) {
                korigovany[i] = klic[i];
            }
            int pocetBytuPridat = 3 - pocetBytuPoslednihoBloku;           
            for (int i = 3 * pocetBloku; i < 3 * pocetBloku + pocetBytuPoslednihoBloku; i++) {
                korigovany[i] = klic[i];
            }
            int k = 3 * pocetBloku + pocetBytuPoslednihoBloku;
            int kk = 0;
            for (int i = 0; i < pocetBytuPridat; i++) {
                korigovany[k++] = klic[kk++];
                if (kk > klic.length) {
                    kk = 0;
                }
            }
        }
        return korigovany;
    }

    public byte[] doplnText(byte[] klic) {
        byte[] korigovany;
        int pocetBloku = klic.length / 3;
        int pocetBytuPoslednihoBloku = klic.length - 3 * pocetBloku;
        if (pocetBytuPoslednihoBloku == 0) {
            korigovany = new byte[3 * pocetBloku];
            for (int i = 0; i < 3 * pocetBloku; i++) {
                korigovany[i] = klic[i];
            }
            return korigovany;
        } else {
            korigovany = new byte[3 * (pocetBloku + 1)];
            for (int i = 0; i < 3 * pocetBloku; i++) {
                korigovany[i] = klic[i];
            }
            int pocetBytuPridat = 3 - pocetBytuPoslednihoBloku;
            int k = 3 * pocetBloku;
            for (int i = 0; i < pocetBytuPoslednihoBloku; i++) {
                korigovany[k++] = klic[i + 3 * pocetBloku];
            }
            for (int i = 0; i < pocetBytuPridat; i++) {
                korigovany[k++] = 0x31;
            }
            return korigovany;
        }
    }

    public byte[] vytvorSestibity(byte b1, byte b2, byte b3) {
        byte[] vysl = new byte[4];
        vysl[0] = (byte) ((b1 & 0xfc) >> 2);
        //System.out.printf("vysl[0] = 0x%02X\n", vysl[0]);
        //--
        vysl[1] = (byte) (((b1 & 0x03) << 4) + ((b2 & 0xf0) >> 4));
        //System.out.printf("vysl[1] = 0x%02X\n", vysl[1]);
        //--
        vysl[2] = (byte) (((b2 & 0x0f) << 2) + ((b3 & 0xc0) >> 6));
        //System.out.printf("vysl[2] = 0x%02X\n", vysl[2]); 
        //--
        vysl[3] = (byte) (b3 & 0x3f);
        //System.out.printf("vysl[3] = 0x%02X\n", vysl[3]);
        return vysl;
    }

    public byte[] slozTriByty(byte b1, byte b2, byte b3, byte b4) {
        byte[] vysl = new byte[3];
        vysl[0] = (byte) (((b1 & 0xff) << 2) + ((b2 & 0xff) >> 4));
        //System.out.printf("vysl[0] = 0x%02X\n", vysl[0]);
        vysl[1] = (byte) (((b2 & 0x0f) << 4) + ((b3 & 0xff) >> 2));
        //System.out.printf("vysl[0] = 0x%02X\n", vysl[1]);
        vysl[2] = (byte) (((b3 & 0x03) << 6) + (b4 & 0x3f));
        //System.out.printf("vysl[0] = 0x%02X\n", vysl[2]);
        return vysl;
    }

    /**
     * Vytvoř pole šestibitu (4 prvky) ze 3 prvků osmibitů
     *
     * @param beale6
     * @param pole
     * @return
     */
    public byte[] vytvorPoleSestibituText(Beale6 beale6, byte[] pole) {
        System.out.println("vytvorPoleSestibitu\n");
        System.out.format("Délka vstupního pole osmibitu = %d\n", pole.length);
        byte[] doplnTxt = beale6.doplnText(pole);
        System.out.format("Délka vstupního pole po doplnění = %d\n", doplnTxt.length);
        int pocetBloku = doplnTxt.length / 3;
        System.out.format("Počet 3-bytových bloků = %d\n", pocetBloku);
        byte[] vysl = new byte[4 * pocetBloku];
        System.out.format("Délka výsledného pole = %d\n", 4 * pocetBloku);
        int k = 0;
        for (int i = 0; i < pocetBloku; i++) {
            int j1 = 3 * i;
            int j2 = j1 + 1;
            int j3 = j2 + 1;
            byte[] sestibit = beale6.vytvorSestibity(doplnTxt[j1], doplnTxt[j2], doplnTxt[j3]);
            vysl[k++] = sestibit[0];
            vysl[k++] = sestibit[1];
            vysl[k++] = sestibit[2];
            vysl[k++] = sestibit[3];
        }
        return vysl;
    }

    public byte[] vytvorPoleSestibituKlic(Beale6 beale6, byte[] pole) {
        System.out.println("vytvorPoleSestibitu\n");
        System.out.format("Délka vstupního pole osmibitu = %d\n", pole.length);
        byte[] doplnKlic = beale6.doplnKlicNeboSul(pole);
        System.out.format("Délka vstupního pole po doplnění = %d\n", doplnKlic.length);
        int pocetBloku = doplnKlic.length / 3;
        System.out.format("Počet 3-bytových bloků = %d\n", pocetBloku);
        byte[] vysl = new byte[4 * pocetBloku];
        System.out.format("Délka výsledného pole = %d\n", 4 * pocetBloku);
        int k = 0;
        for (int i = 0; i < pocetBloku; i++) {
            int j1 = 3 * i;
            int j2 = j1 + 1;
            int j3 = j2 + 1;
            byte[] sestibit = beale6.vytvorSestibity(doplnKlic[j1], doplnKlic[j2], doplnKlic[j3]);
            vysl[k++] = sestibit[0];
            vysl[k++] = sestibit[1];
            vysl[k++] = sestibit[2];
            vysl[k++] = sestibit[3];
        }
        return vysl;
    }

    /**
     * Vytvoř pole osmibitu (3 prvky) ze 4 prvku sestbitu
     *
     * @param beale6
     * @param pole
     * @return
     */
    public byte[] vytvorPoleOsmibituText(Beale6 beale6, byte[] pole) {
        System.out.println("vytvorPoleOsmibitu\n");
        System.out.format("Délka vstupního pole šestibitu = %d\n", pole.length);
        int pocetBloku = pole.length / 4;
        System.out.format("Počet 4-bytových bloků = %d\n", pocetBloku);
        byte[] vysl = new byte[3 * pocetBloku]; // výsledné pole osmibitu
        System.out.format("Délka výsledného pole = %d\n", 3 * pocetBloku);
        int k = 0;
        for (int i = 0; i < pocetBloku; i++) {
            int j1 = 4 * i;
            int j2 = j1 + 1;
            int j3 = j2 + 1;
            int j4 = j3 + 1;
            byte[] osmibit = beale6.slozTriByty(pole[j1], pole[j2], pole[j3], pole[j4]);
            vysl[k++] = osmibit[0];
            vysl[k++] = osmibit[1];
            vysl[k++] = osmibit[2];
        }
        return vysl;
    }

    public byte[] zasifrovani(Beale6 beale6, byte[] klic, byte[] salt, byte[] sifrovany_text) {
        byte[] doplnKlic = beale6.vytvorPoleSestibituKlic(beale6, klic);
        byte[] doplnSul = beale6.vytvorPoleSestibituKlic(beale6, salt);
        byte[] doplnTxt = beale6.vytvorPoleSestibituText(beale6, sifrovany_text);
        byte[] zasifrovano = beale6.shipher(doplnKlic, doplnSul, doplnTxt);
        return zasifrovano;
    }

    public byte[] odsifrovani(Beale6 beale6, byte[] klic, byte[] salt, byte[] desifrovany_text) {
        byte[] doplnKlic = beale6.vytvorPoleSestibituKlic(beale6, klic);
        byte[] doplnSul = beale6.vytvorPoleSestibituKlic(beale6, salt);
        byte[] odsifrovano = beale6.deshipher(doplnKlic, doplnSul, desifrovany_text);
        byte[] odsifrovanoOsmibit = beale6.vytvorPoleOsmibituText(beale6, odsifrovano);
        return odsifrovanoOsmibit;
    }

    public static void main(String[] arg) throws UnsupportedEncodingException {
        Beale6 beale6 = new Beale6(64);

        /*
        byte b1 = (byte) 0xaa;
        byte b2 = (byte) 0xbb;
        byte b3 = (byte) 0xcc;
        
        byte[] vysl = bbb.vytvorSestibity(b1, b2, b3);
        byte[] vysl1 = bbb.slozTriByty(vysl[0], vysl[1], vysl[2], vysl[3]);
         */
 
        String klic = "Růžovoučký kůň tiše řehtal v maštali";
        String salt = "353882435388358999787777534578909888123456789";
        String text = "Růžovou ti krinolínu koupím ditě mé, až půjdem spolu na veliký bál, aby každý věděl ...";
         
        

        byte[] bytesKlic = klic.getBytes("UTF-8");
        byte[] bytesSalt = salt.getBytes("UTF-8");
        byte[] bytesTxt = text.getBytes("UTF-8");
       
        byte[] zasifrovani = beale6.zasifrovani(beale6, bytesKlic, bytesSalt, bytesTxt);        
        byte[] odsifrovani = beale6.odsifrovani(beale6, bytesKlic, bytesSalt, zasifrovani);
        String abc = new String(odsifrovani, "UTF-8");
        
        /*
        byte[] doplnKlic = beale6.vytvorPoleSestibituKlic(beale6, bytesKlic);
        byte[] doplnSul = beale6.vytvorPoleSestibituKlic(beale6, bytesSalt);
         */
        /*
        byte[] doplnKlic = beale6.vytvorPoleSestibituKlic(beale6, bytesKlic);
        byte[] odsifrovanoOsmibit = beale6.vytvorPoleOsmibituText(beale6, doplnKlic);
        String abc = new String(odsifrovanoOsmibit, "UTF-8");
        */
        /*
        byte[] doplnTxt = beale6.vytvorPoleSestibituText(beale6, bytesTxt);
        byte[] odsifrovanoOsmibit = beale6.vytvorPoleOsmibituText(beale6, doplnTxt);
        String abc = new String(odsifrovanoOsmibit, "UTF-8");
         */
        //System.out.println("bytesKlic = " + bytesKlic.length);
        //System.out.println("bytesKlic = " + bytesSalt.length);
        //byte[] shipher = bbb.shipher(bytesKlic, bytesSalt, bytesText);        
        //---        
        //byte[] encoded = Base64.getEncoder().encode(shipher);
        //String ggg = new String(encoded);   // převod zašifrovyných bytů do řetězce znaků v kódu base64
        //--- 
        //byte[] decoded = Base64.getDecoder().decode(encoded);
        //decoded = shipher
        //---
        //byte[] deshipher = bbb.deshipher(bytesKlic, bytesSalt, shipher);
        //String abc = new String(deshipher, "UTF-8");
        System.out.println("HHHHHHHHH");

    }

}
