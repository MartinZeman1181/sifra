/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author martin
 */
public class RC6 {

    private static final int BLOCK_SIZE = 8;
    private static int c = 256;
    private static int r = 0;  //počet rund
    private int[] S;
    private int L[];
    private static final int w = 32; //počet bitů slova
    private static final int u = w / 8; //počet bytů slova
    private static final int P32 = 0xb7e15163;
    private static final int Q32 = 0x9e3779b9;

    public RC6() {
    }

    private void tiskBytovehoPole(byte[] pole, String nazev, boolean tiskPovolen) {
        if (tiskPovolen) {
            System.out.println("\n\n" + nazev + "\n");
            for (int i = 0; i < pole.length; i++) {
                System.out.format("0x%02X, ", pole[i]);
            }
        }
    }

    private void tiskInt4Pole(int[] pole, String nazev, boolean tiskPovolen) {
        byte[] int4tobytearray = int4tobytearray(pole);
        tiskBytovehoPole(int4tobytearray, "INT => " + nazev, tiskPovolen);
    }

    public int shiftRight4(int x, int shift) {
        return ((x & 0xffffffff) << shift) & 0xffffffff;
    }

    public long rotateRight8(long x, int shift) {
        return (((x & 0xffffffffffffffffL) >>> (shift % 64)) | ((x & 0xffffffffffffffffL) << (64 - (shift % 64))));
    }

    public long rotateLeft8(long x, int shift) {
        return (((x & 0xffffffffffffffffL) << (shift % 64)) | ((x & 0xffffffffffffffffL) >>> (64 - (shift % 64))));
    }

    public int rotateRight4(int x, int shift) {
        return (((x & 0xffffffff) >>> (shift % 32)) | ((x & 0xffffffff) << (32 - (shift % 32))));
    }

    public int rotateLeft4(int x, int shift) {
        return (((x & 0xffffffff) << (shift % 32)) | ((x & 0xffffffff) >>> (32 - (shift % 32))));
    }

    public void init(byte[] key, int nrRounds) {
        int POM[] = new int[256];
        this.r = nrRounds;
        L = new int[256];
        S = new int[(r << 1) + 4]; //stav vytvářený z bytů klíče
        //celý klíč se přetáthe do pomocného pole POM a dékla se doplní na 256 bytů, opakováním znaků klíče
        for (int i = 0; i < 256; i++) {
            POM[i] = key[i % key.length];
            //počáteční vynulování pole L, aby fungoval následující algoritmus
            L[i] = 0;
        }
        // vyplnění pomocného pole L
        /*
        for (int i = 255; i >= 0; i--) {
            L[i / u] = (rotateLeft4(L[i / u], 8) & 0xffffffff) + (POM[i] & 0xffffffff);
        }
         */
        //vyplnění pomocného pole S
        S[0] = P32;
        for (int i = 1; i < ((r + 1) << 1) - 1; i++) {
            S[i] = (S[i - 1] & 0xffffffff) + (Q32 & 0xffffffff);
        }
        // mixing 
        int i = 0;
        int j = 0;
        int A = 0;
        int B = 0;
        int t = (r << 1) + 4;
        int max = t;
        if (max < c) {
            max = c;
        }
        for (int ii = 0; ii < 3 * max; ii++) {
            S[i] = rotateLeft4((S[i] & 0xffffffff) + (A & 0xffffffff) + (B & 0xffffffff), 3);
            A = S[i];
            L[j] = rotateLeft4((L[j] & 0xffffffff) + (A & 0xffffffff) + (B & 0xffffffff), ((A & 0xffffffff) + (B & 0xffffffff)));
            i = (i + 1) % t;
            j = (j + 1) % c;
            //System.out.format("i = %d, j = %d, s.length = %d, L.length = %d\n", i, j, S.length, L.length);
        }

    }

    private byte[] int4tobytearray(int[] pole) {
        byte[] bb = new byte[16];
        //--
        bb[0] = (byte) ((pole[0] >> 24) & 0xff);
        bb[1] = (byte) ((pole[0] >> 16) & 0xff);
        bb[2] = (byte) ((pole[0] >> 8) & 0xff);
        bb[3] = (byte) (pole[0] & 0xff);
        //--
        bb[4] = (byte) ((pole[1] >> 24) & 0xff);
        bb[5] = (byte) ((pole[1] >> 16) & 0xff);
        bb[6] = (byte) ((pole[1] >> 8) & 0xff);
        bb[7] = (byte) (pole[1] & 0xff);
        //--
        bb[8] = (byte) ((pole[2] >> 24) & 0xff);
        bb[9] = (byte) ((pole[2] >> 16) & 0xff);
        bb[10] = (byte) ((pole[2] >> 8) & 0xff);
        bb[11] = (byte) (pole[2] & 0xff);
        //--
        bb[12] = (byte) ((pole[3] >> 24) & 0xff);
        bb[13] = (byte) ((pole[3] >> 16) & 0xff);
        bb[14] = (byte) ((pole[3] >> 8) & 0xff);
        bb[15] = (byte) (pole[3] & 0xff);
        //--          
        return bb;
    }

    private int[] bytearraytoint4(byte[] pole) {
        int[] vystup = new int[4];
        vystup[0] = 0;
        vystup[0] = vystup[0] + (pole[0] << 24) + (pole[1] << 16) + (pole[2] << 8) + pole[3];
        vystup[1] = 0;
        vystup[1] = vystup[1] + (pole[4] << 24) + (pole[5] << 16) + (pole[6] << 8) + pole[7];
        vystup[2] = 0;
        vystup[2] = vystup[2] + (pole[8] << 24) + (pole[9] << 16) + (pole[10] << 8) + pole[11];
        vystup[3] = 0;
        vystup[3] = vystup[3] + (pole[12] << 24) + (pole[13] << 16) + (pole[14] << 8) + pole[15];
        return vystup;
    }

    public void encrypt(int[] pole) {
        int A = pole[0];
        int B = pole[1];
        int C = pole[2];
        int D = pole[3];
        //--
        B = (B & 0xffffffff) + (S[0] & 0xffffffff);
        D = (D & 0xffffffff) + (S[1] & 0xffffffff);
        //--
        for (int i = 1; i <= r; i++) {
            int pom1 = (B & 0xffffffff) * ((shiftRight4(B, 1) & 0xffffffff) + (1 & 0xffffffff) & 0xffffffff);
            int t = rotateLeft4(pom1, 5);
            int pom2 = (D & 0xffffffff) * ((shiftRight4(D, 1) & 0xffffffff) + (1 & 0xffffffff) & 0xffffffff);
            int u = rotateLeft4(pom2, 5);
            A = (rotateLeft4((A ^ t), u) & 0xffffffff) + (S[i << 1] & 0xffffffff);
            C = (rotateLeft4((C ^ u), t) & 0xffffffff) + (S[(i << 1) + 1] & 0xffffffff);
            int zalohaA = A;
            int zalohaB = B;
            int zalohaC = C;
            int zalohaD = D;
            A = zalohaB;
            B = zalohaC;
            C = zalohaD;
            D = zalohaA;
        }
        //--
        A = (A & 0xffffffff) + (S[(r << 1) + 2] & 0xffffffff);
        C = (C & 0xffffffff) + (S[(r << 1) + 3] & 0xffffffff);

        pole[0] = A;
        pole[1] = B;
        pole[2] = C;
        pole[3] = D;

    }

    public void decrypt(int[] pole) {
        int A = pole[0];
        int B = pole[1];
        int C = pole[2];
        int D = pole[3];
        //--
        C = (C & 0xffffffff) - (S[(r << 1) + 3] & 0xffffffff);
        A = (A & 0xffffffff) - (S[(r << 1) + 2] & 0xffffffff);
        //--
        for (int i = r; i >= 1; i--) {
            int zalohaA = A;
            int zalohaB = B;
            int zalohaC = C;
            int zalohaD = D;
            A = zalohaD;
            B = zalohaA;
            C = zalohaB;
            D = zalohaC;
            int pom1 = (D & 0xffffffff) * ((shiftRight4(D, 1) & 0xffffffff) + (1 & 0xffffffff) & 0xffffffff);
            int u = rotateLeft4(pom1, 5);
            int pom2 = (B & 0xffffffff) * ((shiftRight4(B, 1) & 0xffffffff) + (1 & 0xffffffff) & 0xffffffff);
            int t = rotateLeft4(pom2, 5);
            C = rotateRight4((C & 0xffffffff) - (S[(i << 1) + 1] & 0xffffffff), t % 32) ^ (u & 0xffffffff);
            A = rotateRight4((A & 0xffffffff) - (S[(i << 1)] & 0xffffffff), u % 32) ^ (t & 0xffffffff);
        }
        D = (D & 0xffffffff) - (S[1] & 0xffffffff);
        B = (B & 0xffffffff) - (S[0] & 0xffffffff);
        //--
        pole[0] = A;
        pole[1] = B;
        pole[2] = C;
        pole[3] = D;

    }

    public static void main(String[] arg) throws UnsupportedEncodingException {
        RC6 rc = new RC6();
        String klic = "MartinZeman1181";
        byte[] klicArray = klic.getBytes("UTF-8");
        String plainText = "ABCDEFGHIJKLMNOP";
        byte[] plainArray = plainText.getBytes("UTF-8");
        //--
        rc.init(klicArray, 5);
        //--
        rc.tiskBytovehoPole(plainArray, "zdrojový text", true);
        int[] vstup = rc.bytearraytoint4(plainArray);
        rc.encrypt(vstup);
        rc.tiskInt4Pole(vstup, "zašifrovaný text", true);
        //System.out.println("KKKKKKKKKKKKKK");
        rc.decrypt(vstup);
        byte[] int4tobytearray = rc.int4tobytearray(vstup);
        rc.tiskBytovehoPole(int4tobytearray, "dešifrovaný text", true);
        String zdroj = new String(int4tobytearray, "UTF-8");
        System.out.println("\nvysledek = " + zdroj);

    }

}
