/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;


/**
 *
 * @author martin
 */
public class Test {

    private static BigInteger[] K = {
        new BigInteger("428a2f98d728ae22", 16), new BigInteger("7137449123ef65cd", 16), new BigInteger("b5c0fbcfec4d3b2f", 16), new BigInteger("e9b5dba58189dbbc", 16),
        new BigInteger("3956c25bf348b538", 16), new BigInteger("59f111f1b605d019", 16), new BigInteger("923f82a4af194f9b", 16), new BigInteger("ab1c5ed5da6d8118", 16),
        new BigInteger("d807aa98a3030242", 16), new BigInteger("12835b0145706fbe", 16), new BigInteger("243185be4ee4b28c", 16), new BigInteger("550c7dc3d5ffb4e2", 16),
        new BigInteger("72be5d74f27b896f", 16), new BigInteger("80deb1fe3b1696b1", 16), new BigInteger("9bdc06a725c71235", 16), new BigInteger("c19bf174cf692694", 16),
        new BigInteger("e49b69c19ef14ad2", 16), new BigInteger("efbe4786384f25e3", 16), new BigInteger("0fc19dc68b8cd5b5", 16), new BigInteger("240ca1cc77ac9c65", 16),
        new BigInteger("2de92c6f592b0275", 16), new BigInteger("4a7484aa6ea6e483", 16), new BigInteger("5cb0a9dcbd41fbd4", 16), new BigInteger("76f988da831153b5", 16),
        new BigInteger("983e5152ee66dfab", 16), new BigInteger("a831c66d2db43210", 16), new BigInteger("b00327c898fb213f", 16), new BigInteger("bf597fc7beef0ee4", 16),
        new BigInteger("c6e00bf33da88fc2", 16), new BigInteger("d5a79147930aa725", 16), new BigInteger("06ca6351e003826f", 16), new BigInteger("142929670a0e6e70", 16),
        new BigInteger("27b70a8546d22ffc", 16), new BigInteger("2e1b21385c26c926", 16), new BigInteger("4d2c6dfc5ac42aed", 16), new BigInteger("53380d139d95b3df", 16),
        new BigInteger("650a73548baf63de", 16), new BigInteger("766a0abb3c77b2a8", 16), new BigInteger("81c2c92e47edaee6", 16), new BigInteger("92722c851482353b", 16),
        new BigInteger("a2bfe8a14cf10364", 16), new BigInteger("a81a664bbc423001", 16), new BigInteger("c24b8b70d0f89791", 16), new BigInteger("c76c51a30654be30", 16),
        new BigInteger("d192e819d6ef5218", 16), new BigInteger("d69906245565a910", 16), new BigInteger("f40e35855771202a", 16), new BigInteger("106aa07032bbd1b8", 16),
        new BigInteger("19a4c116b8d2d0c8", 16), new BigInteger("1e376c085141ab53", 16), new BigInteger("2748774cdf8eeb99", 16), new BigInteger("34b0bcb5e19b48a8", 16),
        new BigInteger("391c0cb3c5c95a63", 16), new BigInteger("4ed8aa4ae3418acb", 16), new BigInteger("5b9cca4f7763e373", 16), new BigInteger("682e6ff3d6b2b8a3", 16),
        new BigInteger("748f82ee5defb2fc", 16), new BigInteger("78a5636f43172f60", 16), new BigInteger("84c87814a1f0ab72", 16), new BigInteger("8cc702081a6439ec", 16),
        new BigInteger("90befffa23631e28", 16), new BigInteger("a4506cebde82bde9", 16), new BigInteger("bef9a3f7b2c67915", 16), new BigInteger("c67178f2e372532b", 16),
        new BigInteger("ca273eceea26619c", 16), new BigInteger("d186b8c721c0c207", 16), new BigInteger("eada7dd6cde0eb1e", 16), new BigInteger("f57d4f7fee6ed178", 16),
        new BigInteger("06f067aa72176fba", 16), new BigInteger("0a637dc5a2c898a6", 16), new BigInteger("113f9804bef90dae", 16), new BigInteger("1b710b35131c471b", 16),
        new BigInteger("28db77f523047d84", 16), new BigInteger("32caab7b40c72493", 16), new BigInteger("3c9ebe0a15c9bebc", 16), new BigInteger("431d67c49c100d4c", 16),
        new BigInteger("4cc5d4becb3e42b6", 16), new BigInteger("597f299cfc657e2a", 16), new BigInteger("5fcb6fab3ad6faec", 16), new BigInteger("6c44198c4a475817", 16)
    };

    
    private static BigInteger[] HP = {
        new BigInteger("6a09e667f3bcc908", 16),
        new BigInteger("bb67ae8584caa73b", 16),
        new BigInteger("3c6ef372fe94f82b", 16),
        new BigInteger("a54ff53a5f1d36f1", 16),
        new BigInteger("510e527fade682d1", 16),
        new BigInteger("9b05688c2b3e6c1f", 16),
        new BigInteger("1f83d9abfb41bd6b", 16),
        new BigInteger("5be0cd19137e2179", 16)
    };
    
 

    private static BigInteger[] H = new BigInteger[8];

    private static BigInteger a;
    private static BigInteger b;
    private static BigInteger c;
    private static BigInteger d;
    private static BigInteger e;
    private static BigInteger f;
    private static BigInteger g;
    private static BigInteger h;

    public static void main(String[] arg) throws UnsupportedEncodingException {        
       int UUU = 998777966;
       int ZZZ = UUU + UUU + UUU + UUU + UUU + UUU 
               
           +    UUU + UUU + UUU + UUU + UUU + UUU
                +       UUU + UUU + UUU + UUU + UUU + UUU
                  +             UUU + UUU + UUU + UUU + UUU + UUU;
        /*
        BigInteger cislo = new BigInteger("1300");
        BigInteger dve = new BigInteger("2", 16);
        BigInteger multiply = cislo.multiply(dve.pow(3));
        String toString = multiply.toString(10);
        System.out.println();
        //------
        BigInteger rotated = ROTL(new BigInteger("81", 16), 1, 8);
        System.out.println(rotated.toString(2));
        BigInteger rotated2 = ROTR(new BigInteger("81", 16), 1, 8);
        System.out.println(rotated2.toString(2));
        //--
        BigInteger MAX = new BigInteger("7fffffffffffffff", 16).add(BigInteger.ONE);
        String toString1 = MAX.toString(16);
        long longValue = MAX.longValue();
        System.out.println(toString1);
        int zzz;
         */

 /*
        int pocetBituZpravy = 1365767898;
        Integer delkaZpravyVBitech = new Integer(pocetBituZpravy);
        byte[] poleBytuDelkyZpravyVBitech = new BigInteger(delkaZpravyVBitech.toString()).toByteArray();
         */
 /*
        String msgText = "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmn"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu";
         */
        String msgText = "abc";

        /*
        String msgText = "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmn"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu0123456789012345"
                + "hijklmnoijklmnopjklmnopqklmnopq111111111";
         */
        /*
        BigInteger mask = BigInteger.ONE.shiftLeft(64).subtract(BigInteger.ONE);
        BigInteger jedna = new BigInteger("1");
        BigInteger negate = mask.andNot(jedna);
        System.out.println(negate.toString(2));
        */
        
        BigInteger mask1 = BigInteger.ONE.shiftLeft(8).subtract(BigInteger.ONE);
        BigInteger prvni = new BigInteger("150");
        BigInteger druhy = new BigInteger("160");
        BigInteger add = prvni.add(druhy);
        BigInteger and = add.and(mask1);
        System.out.println(add);
        System.out.println(and);
        
        

        //String msgText = "123";
        byte[] msg = msgText.getBytes("UTF-8");
        String hash = hash(msg);
        System.out.println(msg.length);
        System.out.println(hash);
        /*
        BigInteger hhh = new BigInteger("81", 16);
        BigInteger rot = ROTR(hhh, 3, 8);
        System.out.println(rot.toString(2));
         */
 /*
        BigInteger CH = BSG0(H[0]);
        System.out.println(CH.toString(16) + " -->" + CH.toString(16).length());
        BigInteger x = new BigInteger("80", 16);
        System.out.format("i = %d, %s\n", 0, x.toString(2));
        for (int i = 1; i < 8; i++) {
        BigInteger c = SHR(x, i);
        System.out.format("i = %d, %s\n", i, c.toString(2));
        }
         */
    }

    //SHA-512
    public static String hash(byte[] msg) {
        boolean dalsiBlok = true;
        int N = msg.length / 128; // počet osmibytových (1024 bitových) bloků
        //BigInteger[] M =  new BigInteger[N]; // M je Nx16 pole 64-bitových celých čísel    
        byte[] vstup = null;
        int pocetBytuVPoslednimBloku = msg.length - 128 * N;
        BigInteger[][] M;

        if (pocetBytuVPoslednimBloku == 0) {
            N = N + 1;
            vstup = new byte[128 * N];
            M = new BigInteger[N][16];
            for (int i = 0; i < msg.length; i++) {
                vstup[i] = msg[i];
            }
            vstup[msg.length] = (byte) 0x80;
            int ii = 1 + msg.length;
            for (int i = 0; i < 112; i++) {
                vstup[ii] = 0x00;
                ii++;
            }
            int pocetBituZpravy = 8 * msg.length;
            Integer delkaZpravyVBitech = new Integer(pocetBituZpravy);
            byte[] poleBytuDelkyZpravyVBitech = new BigInteger(delkaZpravyVBitech.toString()).toByteArray();
            int pocetDalsichNul = 16 - poleBytuDelkyZpravyVBitech.length;
            for (int i = 0; i < pocetDalsichNul - 1; i++) {
                vstup[ii] = 0x00;
                ii++;
            }
            for (int i = 0; i < poleBytuDelkyZpravyVBitech.length; i++) {
                vstup[ii] = poleBytuDelkyZpravyVBitech[i];
                ii++;
            }

            /*
            for (int i = 0; i < vstup.length; i++) {
                System.out.printf("i = %d, vstup[ii] =  0x%02X\n", i, vstup[i]);
            }
            System.out.println("KONEC");
             */
        } else if (pocetBytuVPoslednimBloku > 111) {
            N = N + 2;
            vstup = new byte[128 * N];
            M = new BigInteger[N][16];
            for (int i = 0; i < msg.length; i++) {
                vstup[i] = msg[i];
            }
            vstup[msg.length] = (byte) 0x80;
            int pocetDalsichNul = 240 - pocetBytuVPoslednimBloku;
            int ii = 1 + msg.length;
            for (int i = 0; i < pocetDalsichNul; i++) {
                vstup[ii] = 0x00;
                ii++;
            }

            int pocetBituZpravy = 8 * msg.length;
            Integer delkaZpravyVBitech = new Integer(pocetBituZpravy);
            byte[] poleBytuDelkyZpravyVBitech = new BigInteger(delkaZpravyVBitech.toString()).toByteArray();
            pocetDalsichNul = 16 - poleBytuDelkyZpravyVBitech.length;
            for (int i = 0; i < pocetDalsichNul - 1; i++) {
                vstup[ii] = 0x00;
                ii++;
            }
            for (int i = 0; i < poleBytuDelkyZpravyVBitech.length; i++) {
                vstup[ii] = poleBytuDelkyZpravyVBitech[i];
                ii++;
            }

            /*
            for (int i = 0; i < vstup.length; i++) {
                System.out.printf("i = %d, vstup[ii] =  0x%02X\n", i, vstup[i]);
            }
            System.out.println("KONEC");
             */
        } else {
            N = N + 1;
            vstup = new byte[128 * N];
            M = new BigInteger[N][16];
            for (int i = 0; i < msg.length; i++) {
                vstup[i] = msg[i];
            }
            vstup[msg.length] = (byte) 0x80;
            int pocetDalsichNul;
            int ii = 1 + msg.length;
            for (int i = 0; i < 128 - 16 - pocetBytuVPoslednimBloku - 1; i++) {
                vstup[ii] = 0x00;
                ii++;
            }
            int pocetBituZpravy = 8 * msg.length;
            Integer delkaZpravyVBitech = new Integer(pocetBituZpravy);
            byte[] poleBytuDelkyZpravyVBitech = new BigInteger(delkaZpravyVBitech.toString()).toByteArray();
            pocetDalsichNul = 16 - poleBytuDelkyZpravyVBitech.length;
            for (int i = 0; i < pocetDalsichNul; i++) {
                vstup[ii] = 0x00;
                ii++;
            }
            for (int i = 0; i < poleBytuDelkyZpravyVBitech.length; i++) {
                vstup[ii] = poleBytuDelkyZpravyVBitech[i];
                ii++;
            }

            /*
            for (int i = 0; i < vstup.length; i++) {
                System.out.printf("i = %d, vstup[ii] =  0x%02X\n", i, vstup[i]);
            }
            System.out.println("KONEC");
             */
        }

        System.out.println("KKKKKKKKKKKKKKKKKKK");
        for (int i = 0; i < N; i++) {
            //blok 128 bytů =  1024 bytů
            for (int j = 0; j < 16; j++) {
                int lo = (vstup[128 * i + 8 * j + 0] << 24)
                        ^ (vstup[128 * i + 8 * j + 1] << 16)
                        ^ (vstup[128 * i + 8 * j + 2] << 8)
                        ^ (vstup[128 * i + 8 * j + 3]);
                int hi = (vstup[128 * i + 8 * j + 4] << 24)
                        ^ (vstup[128 * i + 8 * j + 5] << 16)
                        ^ (vstup[128 * i + 8 * j + 6] << 8)
                        ^ (vstup[128 * i + 8 * j + 7]);
                Integer loi = new Integer(lo);
                Integer hii = new Integer(hi);
                BigInteger loiBI = new BigInteger(loi.toString());
                BigInteger hiiBI = new BigInteger(hii.toString());
                BigInteger celek = loiBI.shiftLeft(32).add(hiiBI);
                M[i][j] = celek;
                System.out.format("i = %d, j = %d, M[i][j] = %s\n", i, j, celek.toString(16));

            }

        }

        for (int i = 0; i < 8; i++) {
            H[i] = HP[i];
        }
        BigInteger mask = BigInteger.ONE.shiftLeft(64).subtract(BigInteger.ONE);
        for (int i = 0; i < N; i++) {
            BigInteger[] W = new BigInteger[80];
            System.out.format(">>>>>>>>>>>>>>>>>>>>> i = %d\n", i);
            for (int j = 0; j < 16; j++) {
                W[j] = M[i][j];
                System.out.format("j = %d, W1[j] = %s, delka = %d\n", j, W[j].toString(16), W[j].toString(16).length());
            }
            for (int j = 16; j < 80; j++) {
                W[j] = ((((((SSG1(W[j - 2]).add(W[j - 7])).and(mask)).add(SSG0(W[j - 15])))).and(mask)).add(W[j - 16])).and(mask);
                //W[j] = SSG1(W[j - 2]).add(SSG0(W[j - 15])).add(W[j - 16]).mod(BigInteger.ONE.shiftLeft(64));
                System.out.format("j = %d, W2[j] = %s, delka = %d\n", j, W[j].toString(16), W[j].toString(16).length());
            }
            a = H[0];
            b = H[1];
            c = H[2];
            d = H[3];
            e = H[4];
            f = H[5];
            g = H[6];
            h = H[7];
            System.out.println("INIT");
            System.out.format("a =  0x%02X, b = 0x%02X, c =  0x%02X, d = 0x%02X\n", a, b, c, d);
            System.out.format("e =  0x%02X, f = 0x%02X, g =  0x%02X, h = 0x%02X\n", e, f, g, h);
            System.out.println("-----------------------------------------------------------------------------");
            for (int j = 0; j < 80; j++) {
                BigInteger T1
                        = (((((((h.add(BSG1(e)))
                                .and(mask))
                                .add(CH(e, f, g)))
                                .and(mask))
                                .add(K[j]))
                                .and(mask))
                                .add(W[j]))
                                .and(mask);
                //BigInteger T1 = h.add(BSG1(e)).add(CH(e, f, g)).add(K[j]).add(W[j]);
                BigInteger T2 = SSG0(a)
                        .add(MAJ(a, b, c))
                        .and(mask);
                //BigInteger T2 = SSG0(a).add(MAJ(a, b, b));
                g = f;
                f = e;
                e = (d.add(T1)).and(mask);
                d = c;
                c = b;
                b = a;
                a = (T1.add(T2)).and(mask);
                System.out.format("t = %d, a =  0x%02X, b = 0x%02X, c =  0x%02X, d = 0x%02X\n", j, a, b, c, d);
                System.out.format("t = %d, e =  0x%02X, f = 0x%02X, g =  0x%02X, h = 0x%02X\n", j, e, f, g, h);
                System.out.println("-----------------------------------------------------------------------------");

            }
            H[0] = (H[0].add(a)).and(mask);
            H[1] = (H[1].add(b)).and(mask);
            H[2] = (H[2].add(c)).and(mask);
            H[3] = (H[3].add(d)).and(mask);
            H[4] = (H[4].add(e)).and(mask);
            H[5] = (H[5].add(f)).and(mask);
            H[6] = (H[6].add(g)).and(mask);
            H[7] = (H[7].add(h)).and(mask);

        }
        String soucet = "";
        for (int i = 0; i < 8; i++) {
            soucet = soucet + H[i].toString(16);
            System.out.println(">>>" + H[i].toString(16).length());
        }
        return soucet;
    }

    public static BigInteger ROTL(BigInteger value, int shift, int bitSize) {
        // Note: shift must be positive, if necessary add checks.
        BigInteger topBits = value.shiftRight(bitSize - shift);
        BigInteger mask = BigInteger.ONE.shiftLeft(bitSize).subtract(BigInteger.ONE);
        return value.shiftLeft(shift).or(topBits).and(mask);
    }

    public static BigInteger ROTR(BigInteger value, int shift, int bitSize) {
        // Note: shift must be positive, if necessary add checks.      
        BigInteger mask = BigInteger.ONE.shiftLeft(bitSize).subtract(BigInteger.ONE);
        return value.shiftRight(shift).or(value.shiftLeft(bitSize - shift)).and(mask);

    }

    public static BigInteger SHR(BigInteger value, int shift) {
        return value.shiftRight(shift);
    }

    public static BigInteger CH(BigInteger x, BigInteger y, BigInteger z) {
        BigInteger mask = BigInteger.ONE.shiftLeft(64).subtract(BigInteger.ONE);
        return (x.and(y)).xor(mask.andNot(x).and(z));

    }

    public static BigInteger MAJ(BigInteger x, BigInteger y, BigInteger z) {
        return (x.and(y)).xor(x.and(z)).xor(y.and(z));
    }

    public static BigInteger BSG0(BigInteger x) {
        return ROTR(x, 28, 64).xor(ROTR(x, 34, 64)).xor(ROTR(x, 39, 64));
    }

    public static BigInteger BSG1(BigInteger x) {
        return ROTR(x, 14, 64).xor(ROTR(x, 18, 64)).xor(ROTR(x, 41, 64));
    }

    public static BigInteger SSG0(BigInteger x) {
        return ROTR(x, 1, 64).xor(ROTR(x, 8, 64)).xor(ROTR(x, 7, 64));
    }

    public static BigInteger SSG1(BigInteger x) {
        return ROTR(x, 19, 64).xor(ROTR(x, 61, 64)).xor(ROTR(x, 6, 64));
    }

}
