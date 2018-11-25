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
public class Beale8 {

    private final byte[][] tabulka;
    private final int n;

    public Beale8(int n) {
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

    public static void main(String[] arg) throws UnsupportedEncodingException {
        Beale8 bbb = new Beale8(256);       
        String klic = "Růžovoučký kůň tiše řehtal v maštali";
        String salt = "353882435388358999787777534578909888123456789";
        String text = "Růžovou ti krinolínu koupím ditě mé, až půjdem spolu na veliký bál, aby každý věděl ...";       
        byte[] bytesKlic = klic.getBytes("UTF-8");
        byte[] bytesSalt = salt.getBytes("UTF-8");
        byte[] bytesText = text.getBytes("UTF-8");
        System.out.println("bytesKlic = " + bytesKlic.length);
        System.out.println("bytesKlic = " + bytesSalt.length);
        byte[] shipher = bbb.shipher(bytesKlic, bytesSalt, bytesText);        
        //---        
        byte[] encoded = Base64.getEncoder().encode(shipher);
        String ggg = new String(encoded);   // převod zašifrovyných bytů do řetězce znaků v kódu base64
        //--- 
        byte[] decoded = Base64.getDecoder().decode(encoded);
        //decoded = shipher
        //---
        byte[] deshipher = bbb.deshipher(bytesKlic, bytesSalt, shipher);
        String abc = new String(deshipher, "UTF-8");
        System.out.println("HHHHHHHHH");
    }

}
