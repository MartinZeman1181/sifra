/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;


import java.math.BigInteger;

/**
 *
 * @author martin
 */
public class RSA2 {

    public static void main(String[] arg) {
        
        BigInteger[] prvocisla = najdiPrvocisla(10, 64);
        
        BigInteger m = new BigInteger("123456788889123456789123456789123456789");      
        System.out.println("šifrovaná zpráva = " + m.toString());
        BigInteger p =  prvocisla[0]; // new BigInteger("319901");
        BigInteger q =  prvocisla[9]; //new BigInteger("319993");       
        BigInteger n = p.multiply(q);
        if (m.bitLength() >= n.bitLength()) {
           System.out.println("Hodnota m musí být MENŠÍ než n.");
           return;
        }
        BigInteger z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));        
        BigInteger e = new BigInteger("100");
       
        boolean nalezen = false;
        while (true) {
            if (gcd(e, z).equals(BigInteger.ONE)) {
                 nalezen = true;
                 System.out.println("e = " + e.toString());
                 break;
            }              
            if (e.equals(z.subtract(BigInteger.ONE))) {
               break;
            }
            e = e.add(BigInteger.ONE);     
        }
        if (!nalezen) {
            System.out.println("Nebyla nalezena žádná hodnota e");
            return;
        }
      
       
        System.out.println("e = " + e.toString() + ", z = " + z.toString());
        
       
        
        BigInteger r = e.subtract(BigInteger.ONE).multiply(z.pow(e.intValue() - 2));
        BigInteger d = r.multiply(z).add(BigInteger.ONE).divide(e);
        
        BigInteger verejnyKlic[]    = new BigInteger[2];
        BigInteger soukromyKlic[]   = new BigInteger[2];
        //--
        verejnyKlic[0] = n;
        verejnyKlic[1] = e;
        System.out.println("verejny klic = (" + n.toString() + ", " + e.toString() + ")");
        //--
        soukromyKlic[0] = n;
        soukromyKlic[1] = d;
        System.out.println("soukromy klic = (" + n.toString() + ", " + d.toString() + ")");
        //-- zašifrování zprávy
        BigInteger c =  m.modPow(e, n);
        System.out.println("sifrovana zprava = " + c.toString());
        //-- dešifrování zprávy        
        m =  c.modPow(d, n);
        System.out.println("desifrovana zprava = " + m.toString());
        System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKK");  
        

    }
    
    public static BigInteger[] najdiPrvocisla(int pocetNalezenych, int minDelkaPrvocisla) {
        int horniHranice = 320000;
        int[] prvocisla = new int[horniHranice];
        for (int i = 1; i <= horniHranice; i++) {
            prvocisla[i - 1] = i;
        }
        for (int i = 1; i < horniHranice; i++) {
            if (prvocisla[i] > 0) {
                int delitel = prvocisla[i];
                for (int k = i + 1; k < horniHranice; k++) {
                    if ((prvocisla[k] % delitel) == 0) {
                        prvocisla[k] = 0;
                    }
                }
            }
        }

        BigInteger[] vysledek = new BigInteger[pocetNalezenych];
        int pocetVysledku = 0;
        int k = 1;
        int pocet = 1;
        BigInteger dve = new BigInteger("2");
        for (int i = 1; i < 32000; i++) {
            if (prvocisla[i] > 0) {
                prvocisla[pocet++] = prvocisla[i];
                BigInteger zaklad = new BigInteger("2");
                BigInteger navrh = zaklad.pow(prvocisla[i]).subtract(BigInteger.ONE);
                //System.out.println(navrh.toString().length());
                if (navrh.toString().length() > minDelkaPrvocisla) {
                    if (navrh.isProbablePrime(100)) {
                        //System.out.println("PRVOCISLO");
                        for (int l = 1; l < 10000; l++) {
                            BigInteger indx = new BigInteger(new Integer(l).toString());
                            BigInteger natest = dve.multiply(indx).multiply(navrh).add(BigInteger.ONE);
                            if (natest.isProbablePrime(100)) {
                               vysledek[pocetVysledku] = natest;
                               if (pocetVysledku >= pocetNalezenych - 1) {
                                  return vysledek;
                               }
                               pocetVysledku = pocetVysledku + 1;
                            }

                        }
                    }
                }

            }
        }
        
        return vysledek;
    }

    
     public static BigInteger gcd(BigInteger e, BigInteger z) {
        if (e.equals(BigInteger.ZERO) ) {
            return z;
        } else {
            //return gcd(z % e, e);
            return gcd(z.mod(e), e);
        }
    }
    
}
