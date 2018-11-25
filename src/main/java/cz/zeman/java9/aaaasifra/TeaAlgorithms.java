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
public class TeaAlgorithms {

    private static final int POCET_BYTU_SLOVA = 4;
    private static final int POCET_BITU_SLOVA = POCET_BYTU_SLOVA << 3;
    private static final int ITERATIONS = 32;

    public static void main(String[] arg) {

        BigInteger[] plain = {
            new BigInteger("11223344", 16),
            new BigInteger("51525354", 16),
            new BigInteger("61626364", 16),
            new BigInteger("71727374", 16)
        };
        BigInteger[] key = {
            new BigInteger("61616161", 16),
            new BigInteger("62626262", 16),
            new BigInteger("63636363", 16),
            new BigInteger("64646464", 16),
            new BigInteger("65656565", 16),
            new BigInteger("66666666", 16),
            new BigInteger("67676767", 16),
            new BigInteger("68686868", 16)
        };

        BigInteger[] decode = {
            new BigInteger("4d1ec81d", 16),
            new BigInteger("7e37aef8", 16),
            new BigInteger("ebc3afaa", 16),
            new BigInteger("06626c89", 16)
        };

        //BigInteger[] xtea3 = xtea3(key, plain);
        
        BigInteger[] dxtea3 = dxtea3(key, decode);
        System.out.println("KKKKKKKKKKKKKK");

    }

    public static BigInteger[] dxtea3(BigInteger[] key, BigInteger[] plain) {
        BigInteger KONST = new BigInteger("9E3779B9", 16);
        BigInteger a, b, c, d, sum;
        BigInteger t = BigInteger.ZERO;
        BigInteger[] splain = new BigInteger[4];
        sum = BigInteger.ZERO;
        for (int i = 0; i < 4; i++) {
            splain[i] = plain[i];
        }
        a = splain[0].xor(key[4]);
        b = splain[1].xor(key[5]);
        c = splain[2].xor(key[6]);
        d = splain[3].xor(key[7]);
       
        System.out.format("a = %s\n", a.toString(16));
        System.out.format("b = %s\n", b.toString(16));
        System.out.format("c = %s\n", c.toString(16));
        System.out.format("d = %s\n", d.toString(16));
       
        for (int iter = ITERATIONS - 1; iter >= 0; iter--) {     
            //System.out.format("iter = %d, a = %s, b = %s, c = %s, d = %s, t = %s\n", iter, a.toString(16), b.toString(16), c.toString(16), d.toString(16), t.toString(16));
            
            t = d;
            System.out.format("iter = %d, t = %s\n", iter, t.toString(16));
            d = c;
            System.out.format("iter = %d, d = %s\n", iter, d.toString(16));
            c = b;
            System.out.format("iter = %d, c = %s\n", iter, c.toString(16));
            b = a;
            System.out.format("iter = %d, b = %s\n", iter, b.toString(16));
            a = t;                
            System.out.format("iter = %d, a = %s\n", iter, a.toString(16));
            //System.out.format("iter = %d, a = %s, b = %s, c = %s, d = %s, t = %s\n", iter, a.toString(16), b.toString(16), c.toString(16), d.toString(16), t.toString(16));
            System.out.println("FUNKCE");
            BigInteger funkce2 = FCE2(b, d, sum, key);    
            System.out.format("iter = %d, funkce2 = %s\n", iter, funkce2.toString(16));
            c = SUB(c, funkce2);
            System.out.format("iter = %d, c = %s\n", iter, c.toString(16));
            
            sum = SUB(sum, KONST);
            System.out.format("iter = %d, sum = %s\n", iter, sum.toString(16));
            
            BigInteger funkce1 = FCE1(b, d, sum, key);
            System.out.format("iter = %d, funkce1 = %s\n", iter, funkce1.toString(16));
            a = SUB(a, funkce1); 
            System.out.format("iter = %d, a = %s\n", iter, a.toString(16));
            
            //System.out.format("iter = %d, funkce1 = %s, funkce2 = %s\n", iter, funkce1.toString(16), funkce2.toString(16));
            //System.out.format("iter = %d, a = %s, b = %s, c = %s, d = %s, t = %s\n", iter, a.toString(16), b.toString(16), c.toString(16), d.toString(16), t.toString(16));
        }
        splain[0] = SUB(a, key[0]);
        splain[1] = SUB(a, key[1]);
        splain[2] = SUB(a, key[2]);
        splain[3] = SUB(a, key[3]);
        System.out.println("Finále\n");
        System.out.format("splain[0] = %s\n", splain[0].toString(16));
        System.out.format("splain[1] = %s\n", splain[1].toString(16));
        System.out.format("splain[2] = %s\n", splain[2].toString(16));
        System.out.format("splain[3] = %s\n", splain[3].toString(16));
        return splain;

    }

    public static BigInteger[] xtea3(BigInteger[] key, BigInteger[] plain) {
        BigInteger KONST = new BigInteger("9E3779B9", 16);
        BigInteger a, b, c, d, sum, t;
        BigInteger[] splain = new BigInteger[4];
        sum = BigInteger.ZERO;
        for (int i = 0; i < 4; i++) {
            splain[i] = plain[i];
        }
        a = ADD(splain[0], key[0]);
        b = ADD(splain[1], key[1]);
        c = ADD(splain[2], key[2]);
        d = ADD(splain[3], key[3]);
        System.out.format("a = %s\n", a.toString(16));
        System.out.format("b = %s\n", b.toString(16));
        System.out.format("c = %s\n", c.toString(16));
        System.out.format("d = %s\n", d.toString(16));

        for (int iter = 0; iter < ITERATIONS; iter++) {
            BigInteger funkce1 = FCE1(b, d, sum, key);
            a = ADD(a, funkce1);
            
            sum = ADD(sum, KONST);
            
            BigInteger funkce2 = FCE2(b, d, sum, key);
            c = ADD(c, funkce2);
            
            //System.out.format("iter = %d, a = %s, c = %s\n", iter, a.toString(16), c.toString(16));
            System.out.format("iter = %d, funkce1 = %s, funkce2 = %s\n", iter, funkce1.toString(16), funkce2.toString(16));
            t = a;
            a = b;
            b = c;
            c = d;
            d = t;
            System.out.format("iter = %d, a = %s, b = %s, c = %s, d = %s, t = %s\n", iter, a.toString(16), b.toString(16), c.toString(16), d.toString(16), t.toString(16));
        }

        splain[0] = a.xor(key[4]);
        splain[1] = b.xor(key[5]);
        splain[2] = c.xor(key[6]);
        splain[3] = d.xor(key[7]);
        System.out.println("Finále\n");
        System.out.format("splain[0] = %s\n", splain[0].toString(16));
        System.out.format("splain[1] = %s\n", splain[1].toString(16));
        System.out.format("splain[2] = %s\n", splain[2].toString(16));
        System.out.format("splain[3] = %s\n", splain[3].toString(16));
        return splain;
    }

    public static BigInteger SHIFTR(BigInteger value, int shift) {
        if (shift <= 0) {
            return value;
        }
        BigInteger MASKA = BigInteger.ONE.shiftLeft(POCET_BITU_SLOVA).subtract(BigInteger.ONE);
        return (value.shiftRight(shift)).and(MASKA);
    }

    public static BigInteger SHIFTL(BigInteger value, int shift) {
        if (shift <= 0) {
            return value;
        }
        BigInteger MASKA = BigInteger.ONE.shiftLeft(POCET_BITU_SLOVA).subtract(BigInteger.ONE);
        return (value.shiftLeft(shift)).and(MASKA);
    }

    public static BigInteger ROTL(BigInteger value, int shift) {
        if (shift <= 0) {
            return value;
        }
        BigInteger MASKA = BigInteger.ONE.shiftLeft(POCET_BITU_SLOVA).subtract(BigInteger.ONE);
        BigInteger topBits = value.shiftRight(POCET_BITU_SLOVA - shift);
        return value.shiftLeft(shift).or(topBits).and(MASKA);
    }

    public static BigInteger ADD(BigInteger op1, BigInteger op2) {
        BigInteger MASKA = BigInteger.ONE.shiftLeft(POCET_BITU_SLOVA).subtract(BigInteger.ONE);
        return (op1.add(op2)).and(MASKA);
    }

    public static BigInteger SUB(BigInteger op1, BigInteger op2) {
        BigInteger MASKA = BigInteger.ONE.shiftLeft(POCET_BITU_SLOVA).subtract(BigInteger.ONE);
        return (op1.subtract(op2)).and(MASKA);
    }

    public static BigInteger FCE1(BigInteger b, BigInteger d, BigInteger sum, BigInteger[] key) {
        BigInteger scitanec1 = SHIFTL(b, 4); // b << 4
        //--       
        int index = (sum.mod(BigInteger.ONE.shiftLeft(2))).intValue(); // sum%4
        int index2 = index + 4;  // + 4
        BigInteger klic = key[index2]; // hodnota klíče key[(sum%4) + 4]
        BigInteger pocetBituCyklPosunu = b.mod(BigInteger.valueOf(POCET_BITU_SLOVA)); // b%32
        int otocit = pocetBituCyklPosunu.intValue();  // extrakce int hodnoty cyklického posunu
        //System.out.format("otocit = %d\n", otocit);
        BigInteger scitanec2 = ROTL(klic, otocit);
        BigInteger doxoru1 = ADD(scitanec1, scitanec2); //(b << 4) + ROTL(klic, b) .....)
        //--        
        BigInteger doxoru2 = ADD(d, sum); // (d + sum
        //--
        BigInteger scitanec41 = SHIFTR(b, 5); // b >> 5
        //...
        BigInteger klic2 = key[index]; //key[sum%4]
        BigInteger pocetBituCyklPosunu2 = b.shiftRight(27); //b >> 27
        int otocit2 = pocetBituCyklPosunu2.intValue();     // extrakce int hodnoty cyklického posunu
        //System.out.format("otocit2 = %d\n", otocit2);
        BigInteger scitanec42 = ROTL(klic2, otocit2);  //ROTL(klic[sum%4], b>> 27)
        BigInteger doxoru3 = ADD(scitanec41, scitanec42); //((b >> 5) + rol(key[sum%4], b >> 27)
        //--
        BigInteger v = doxoru1.xor(doxoru2).xor(doxoru3);
        return v;
    }

    public static BigInteger FCE2(BigInteger b, BigInteger d, BigInteger sum, BigInteger[] key) {
        BigInteger scitanec11 = SHIFTL(d, 4);
        //..
        BigInteger pom = SHIFTR(sum, 11);
        BigInteger indexPolotovar = pom.mod(BigInteger.ONE.shiftLeft(2));
        int index = indexPolotovar.intValue();
        int index2 = index + 4;
        BigInteger klic = key[index];
        BigInteger pocetBituCyklPosunu = d.mod(BigInteger.valueOf(POCET_BITU_SLOVA)); // d%32
        int otocit = pocetBituCyklPosunu.intValue();  // extrakce int hodnoty cyklického posunu
        //System.out.format("FCE2 otocit = %d\n", otocit);
        BigInteger scitanec12 = ROTL(klic, otocit);
        BigInteger doxoru1 = ADD(scitanec11, scitanec12);
        //--
        BigInteger doxoru2 = ADD(b, sum);
        //--
        BigInteger scitanec31 = SHIFTR(d, 5);
        //..
        BigInteger klic2 = key[index];
        BigInteger pocetBituCyklPosunu2 = SHIFTR(d, 27);
        //BigInteger pocetBituCyklPosunu2 = dx.mod(BigInteger.valueOf(POCET_BITU_SLOVA)); // d%32
        int otocit2 = pocetBituCyklPosunu2.intValue();  // extrakce int hodnoty cyklického posunu
        //System.out.format("FCE2 otocit2 = %d\n", otocit2);
        BigInteger scitanec32 = ROTL(klic2, otocit2);
        BigInteger doxoru3 = ADD(scitanec31, scitanec32);
        //--
        BigInteger v = doxoru1.xor(doxoru2).xor(doxoru3);
        return v;
    }

}
