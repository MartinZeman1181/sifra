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
public class Beale4 {

    private final byte[][] tabulka;
    private final int n;

    public Beale4(int n) {
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
        System.out.println("*************** šifrování *******************\n");
        int indxklic = 0;
        for (int i = 0; i < sifrovany_text.length; i++) {            
            byte znak1 = sifrovany_text[i];    
            //System.out.printf("Zdrojový byt = 0x%02X\n", znak1);
            //System.out.printf("Klíč = 0x%02X\n", klic[indxklic]);
            //System.out.printf("Sůl = 0x%02X\n", salt[indxklic]);    
            //System.out.printf("------------------------HORNI PULBYT------------------------------------------------\n");
            znak1 = (byte) ((znak1 & 0xf0) >> 4); 
            //System.out.printf("Horní půlbyt zdrojového bytu = 0x%02X\n", znak1);            
            //System.out.printf("Horní půlbyt soli = 0x%02X\n", (salt[indxklic] & 0xf0) >> 4);            
            znak1 = (byte) (znak1 ^ ((salt[indxklic] & 0xf0) >> 4));
            //System.out.printf("XOR zdrojového horního půlbytu s horním půlbytem soli = 0x%02X\n", znak1);            
            int radek1 = (klic[indxklic] & 0xf0) >> 4;
            //System.out.printf("Horní půlbyt klíče, což je řádkový index do šifrovací tabulky = %d\n", radek1);    
            int sloupec1 = znak1;
            //System.out.printf("Sloupec šifrovací tabulky = %d\n", sloupec1);  
            byte element1 = this.getElement(radek1, sloupec1);
            //System.out.printf("Odečtený horní půlbyt z tabulky horní půlbyt = 0x%02X\n", element1);           
            element1 = (byte) (element1 ^ ((klic[indxklic] & 0xf0) >> 4));     
            //System.out.printf("Zašifrovaný horní půlbyt => XOR odečteného horního půlbytu z tabulky a horního půlbytu klíčei = 0x%02X\n", element1);    
            //System.out.printf("------------------------DOLNI PULBYT------------------------------------------------\n");
            //---------------------------------------------------            
            byte znak2 = sifrovany_text[i];
            znak2 = (byte) (znak2 & 0x0f);
            //System.out.printf("Dolní půlbyt zdrojového bytu = 0x%02X\n", znak2);
            znak2 = (byte) (znak2 ^ (salt[indxklic] & 0x0f));                     
            //System.out.printf("Dolní půlbyt soli = 0x%02X\n", salt[indxklic] & 0x0f);  
            int radek2 = klic[indxklic] & 0x0f;
            //System.out.printf("Dolní půlbyt klíče, což je řádkový index do šifrovací tabulky = %d\n", radek2); 
            int sloupec2 = znak2;
            //System.out.printf("Sloupec šifrovací tabulky = %d\n", sloupec2); 
            byte element2 = this.getElement(radek2, sloupec2);
            //System.out.printf("Odečtený dolní půlbyt z tabulky horní půlbyt = 0x%02X\n", element2);  
            element2 = (byte) (element2 ^ (klic[indxklic] & 0x0f));
            //System.out.printf("Zašifrovaný dolní půlbyt = 0x%02X\n", element2);
            //---------------------------------------------------
            zasifrovano[i] = (byte) (element2 + (element1 << 4));
            //System.out.printf("Zašifrovaný byt = 0x%02X\n", zasifrovano[i]);
            //System.out.printf("-------------------------------------------------------------------------------------\n");
            //---------------------------------------------------
            indxklic++;
            if (indxklic >= klic.length) {
                indxklic = 0;
            }
        }
        return zasifrovano;
    }
    
     
     public byte[] deshipher(byte[] klic, byte[] salt, byte[] desifrovany_text) {
        byte[] desifrovano = new byte[desifrovany_text.length];
        System.out.println("*********************** dešifrování ****************************\n");
        int indxklic = 0;
        for (int i = 0; i < desifrovany_text.length; i++) {
            byte znak1 = (byte) (desifrovany_text[i] & 0xff);           
            //System.out.printf("Dešifrovaný byt = 0x%02X\n", znak1);
            //System.out.printf("Klíč = 0x%02X\n", klic[indxklic]);
            //System.out.printf("Sůl = 0x%02X\n", salt[indxklic]);    
            //System.out.printf("------------------------HORNI PULBYT------------------------------------------------\n");
            //----------------------------------------------------------
            znak1 = (byte) ((znak1 & 0xf0) >> 4);
            //System.out.printf("Horní půlbyt dešifrovyného bytu = 0x%02X\n", znak1);
            //System.out.printf("Horní půlbyt klíče = 0x%02X\n", (klic[indxklic] & 0xf0) >> 4);
            znak1 = (byte) (znak1 ^ ((klic[indxklic] & 0xf0) >> 4));
            //System.out.printf("XOR horního půlbytu dešifrovaného textu s horním půlbytem klíče, což je hledaná hodnota v dešifrovací tabulce = 0x%02X\n", znak1);            
            int radek1 = (klic[indxklic] & 0xf0) >> 4;
            //System.out.printf("Řádek dešifrovací tabulky jako horní byt klíče = %d\n", radek1);
            byte element1 = this.getSloupcovyIndex(radek1, znak1);
            //System.out.printf("Nalezený sloupec v dešifrovací tabulce  = 0x%02X\n", element1);
            //System.out.printf("Horní půlbyt soli  = 0x%02X\n", (salt[indxklic] & 0xf0) >> 4);
            element1 = (byte) (element1 ^ (salt[indxklic] & 0xf0) >> 4);
            //System.out.printf("Dešifrovaný horní půlbyt = Horní půlbyt zdrojového textu  = 0x%02X\n", element1);
            //System.out.printf("-------------------------------------------------------------------------------------\n");
            //---------------------------------------------------------
            byte znak2 = desifrovany_text[i];
            znak2 = (byte) (znak2 & 0x0f);
            //System.out.printf("Dolní půlbyt dešifrovyného bytu = 0x%02X\n", znak2);
            //System.out.printf("Dolní půlbyt klíče = 0x%02X\n", klic[indxklic] & 0x0f);
            znak2 = (byte) (znak2 ^ (klic[indxklic] & 0x0f));
            //System.out.printf("XOR dolního půlbytu dešifrovaného textu s dolním půlbytem klíče, což je hledaná hodnota v dešifrovací tabulce = 0x%02X\n", znak2);
            int radek2 = klic[indxklic] & 0x0f;
            //System.out.printf("Řádek dešifrovací tabulky jako dolní byt klíče = %d\n", radek2);
            byte element2 = this.getSloupcovyIndex(radek2, znak2);
            element2 = (byte) (element2 ^ (salt[indxklic] & 0x0f));
            //System.out.printf("Dešifrovaný dolní půlbyt = Dolní půlbyt zdrojového textu  = 0x%02X\n", element2);           
            //System.out.printf("-------------------------------------------------------------------------------------\n");
            //---------------------------------------------------------
            desifrovano[i] = (byte) ((element1 << 4) + element2);
            //System.out.printf("Výsledek dešifrování, což by měl být zdrojový byt = 0x%02X\n", desifrovano[i]);
            indxklic++;
            if (indxklic >= klic.length) {
                indxklic = 0;
            }
        }
        return desifrovano;
    }
    
    public static void main(String[] arg) throws UnsupportedEncodingException {
        Beale4 bbb = new Beale4(16);   
      
        String klic = "Růžovoučký kůň tiše řehtal v maštali";
        String salt = "353882435388358999787777534578909888123456789";
        String text = "Růžovou ti krinolínu koupím ditě mé, až půjdem spolu na veliký bál, aby každý věděl ...";    
       /*
        String klic = "A";
        String salt = "B";
        String text = "C";    
       */ 
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
