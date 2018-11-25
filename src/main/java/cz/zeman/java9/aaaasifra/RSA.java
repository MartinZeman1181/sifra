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
public class RSA {

    public RSA() {
    }
    
    public BigInteger gcd(BigInteger e, BigInteger z) {
        if (e.equals(BigInteger.ZERO) ) {
            return z;
        } else {
            //return gcd(z % e, e);
            return gcd(z.mod(e), e);
        }
    }

    public static void main(String[] arg) {
        /*
        BigInteger zaklad = new BigInteger("3");
        BigInteger a = new BigInteger("5");
        BigInteger b = new BigInteger("8");
        BigInteger m = new BigInteger("100");
        BigInteger modPow = zaklad.modPow(a.add(b), m); // exp(a + b) mod(m)
        String toString2 = modPow.toString();
        System.out.println("modPow = " + toString2);
        */
        
      
        RSA taj = new RSA();
        taj.zasifrovaniZpravy(taj);
        //BigInteger[] najdiPrvo = taj.najdiPrvocisla(100, 256);
        //byte[] zasifrovaniZpravy = taj.zasifrovaniZpravy(taj);
        
        System.out.println("KKKKKKKKKKK");

    }
    
    
    public byte[] zasifrovaniZpravy(RSA taj) {    
     
               
        //BigInteger[] pecka = taj.najdiPrvocisla(10, 60);
        BigInteger m = new BigInteger("123");
        //BigInteger[] prvocisla = taj.najdiPrvocisla(10, 128);
        BigInteger p = new BigInteger("319901"); //prvocisla[10];
        BigInteger q = new BigInteger("319993"); //prvocisla[90];
        BigInteger n = p.multiply(q);
        BigInteger psi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        int e = 7;
        String et = new Integer(e).toString();
        BigInteger ett = new BigInteger(et);
        /*
        boolean nesoudelne = false;
        for (int j = 0; j < pecka.length; j++) {
            BigInteger[] divideAndRemainder = psi.divideAndRemainder(pecka[j]);
            if (!divideAndRemainder[1].equals(BigInteger.ZERO)) {
                e = pecka[j]; 
                nesoudelne = true;
                break;
            }         
        }
        
        if (!nesoudelne) {
           System.out.println("Vsechna cisla v tabulce jsou dělitelé čísla psi");
           return null; 
        }
        */
        BigInteger ee = ett.subtract(BigInteger.ONE);
        BigInteger pom = ett.subtract(new BigInteger("2"));
        BigInteger pom1 = psi.pow(e);
        BigInteger r = ee.multiply(pom1);
        
        BigInteger one = new BigInteger("1");
        BigInteger d = one.add(r.multiply(psi)).divide(ett);
        
             
        BigInteger verejnyKlic[]    = new BigInteger[2];
        BigInteger soukromyKlic[]   = new BigInteger[2];
        //--
        verejnyKlic[0] = n;
        verejnyKlic[1] = ett;
        //--
        soukromyKlic[0] = n;
        soukromyKlic[1] = d;
        //-- zašifrování zprávy
        BigInteger c =  m.modPow(ett, n);
        //-- dešifrování zprávy        
        m =  c.modPow(d, n);
        System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKK");       
        return null;
        
        
    }

    public BigInteger[] najdiPrvocisla(int pocetNalezenych, int minDelkaPrvocisla) {
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
                System.out.println(navrh.toString().length());
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

}
