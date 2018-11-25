/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 * NECHODÍ !!!
 */
public class XTEA {

    private static final int DELTA = -1640531527;
    private static final int SUM = -957401312;
    private static final int NUM_ROUNDS = 32;

    public XTEA() {
    }

    private static int[] decipher4(int[] block, int[] key) {
        long[] blockv = new long[2];
        int[] vysl = new int[2];
        long[] klic = new long[4];
        blockv[0] = block[0];
        blockv[1] = block[1];
        klic[0] = key[0];
        klic[1] = key[1];
        klic[2] = key[2];
        klic[3] = key[3];
        long sum = SUM;
        for (int i = 0; i < NUM_ROUNDS; i++) {
            blockv[1] -=  ((klic[(int) ((sum & 0x1933) >>> 11)] + sum ^ blockv[0] + (blockv[0] << 4 ^ blockv[0] >>> 5)) & 0xFFFFFFFF);
            sum -= DELTA;
            blockv[0] -= ((((blockv[1] << 4 ^ blockv[1] >>> 5) & 0xFFFFFFFF) + (blockv[1] ^ ((klic[(int) (sum & 0x3)] + sum) & 0xFFFFFFFF)) & 0xFFFFFFFF) & 0xFFFFFFFF);
        }
        vysl[0] = (int) blockv[0];
        vysl[1] = (int) blockv[1];
        return vysl;
    }

    /*
    private static int[] decipher4(int[] block, int[] key) {
        int[] blockv = new int[2];
        blockv[0] = block[0];
        blockv[1] = block[1];
        long sum = SUM;
        for (int i = 0; i < NUM_ROUNDS; i++) {
            blockv[1] -= (key[(int) ((sum & 0x1933) >>> 11)] + sum ^ blockv[0] + (blockv[0] << 4 ^ blockv[0] >>> 5));
            sum -= DELTA;
            blockv[0] -= ((blockv[1] << 4 ^ blockv[1] >>> 5) + blockv[1] ^ key[(int) (sum & 0x3)] + sum);
        }
        return blockv;
    }
     */
 /*
    private static int[] encipher4(int[] block, int[] key) {
        int[] blockv = new int[2];
        blockv[0] = block[0];
        blockv[1] = block[1];
        long sum = 0;
        for (int i = 0; i < NUM_ROUNDS; i++) {
            blockv[0] += ((blockv[1] << 4 ^ blockv[1] >>> 5) + blockv[1] ^ key[(int) (sum & 0x3)] + sum);
            sum += DELTA;
            blockv[1] += (key[(int) ((sum & 0x1933) >>> 11)] + sum ^ blockv[0] + (blockv[0] << 4 ^ blockv[0] >>> 5));
        }
        return blockv;
    }
     */
    private static int[] encipher4(int[] block, int[] key) {
        long[] blockv = new long[2];
        int[] vysl = new int[2];
        long[] klic = new long[4];
        blockv[0] = block[0];
        blockv[1] = block[1];
        klic[0] = key[0];
        klic[1] = key[1];
        klic[2] = key[2];
        klic[3] = key[3];
        
        long sum = 0;
        for (int i = 0; i < NUM_ROUNDS; i++) {
            blockv[0] += (((blockv[1] << 4 ^ blockv[1] >>> 5) & 0xFFFFFFFF) + ((blockv[1] ^ klic[(int) (sum & 0x3)]) & 0xFFFFFFFF) + (sum & 0xFFFFFFFF)) & 0xFFFFFFFF;
            sum += DELTA;
            blockv[1] += (((klic[(int) ((sum & 0x1933) >>> 11)] & 0xFFFFFFFF) + ((sum ^ blockv[0]) & 0xFFFFFFFF) + (blockv[0] << 4 ^ blockv[0] >>> 5) & 0xFFFFFFFF) & 0xFFFFFFFF);
        }
        vysl[0] = (int) blockv[0];
        vysl[1] = (int) blockv[1];
        return vysl;
    }

    public static byte[] sifrovani(byte[] pole, byte[] klic) throws UnsupportedEncodingException {
        int pocetBloku = pole.length >> 3;
        int pocetBytuZbyva = pole.length - (pocetBloku << 3);
        int[] blocks = new int[(pocetBloku + 1) << 1];

        //-----------------------------------------------------------------------------------------
        //   Naplnění datových bloků
        //-----------------------------------------------------------------------------------------
        if (pocetBytuZbyva == 0) {
            blocks = new int[pocetBloku << 1];
            for (int i = 0; i < pocetBloku; i++) {
                int jj = i << 3;
                int ii = i << 1;
                int ii1 = ii + 1;
                blocks[ii] = pole[8 * i + 0] << 24;
                blocks[ii] = blocks[ii] + (pole[jj + 1] << 16);
                blocks[ii] = blocks[ii] + (pole[jj + 2] << 8);
                blocks[ii] = blocks[ii] + pole[jj + 3];
                blocks[ii1] = pole[jj + 4] << 24;
                blocks[ii1] = blocks[ii1] + (pole[jj + 5] << 16);
                blocks[ii1] = blocks[ii1] + (pole[jj + 6] << 8);
                blocks[ii1] = blocks[ii1] + pole[jj + 7];
            }

        } else {
            pocetBloku = pocetBloku + 1;
            blocks = new int[pocetBloku << 1];
            for (int i = 0; i < pocetBloku - 1; i++) {
                int jj = i << 3;
                int ii = i << 1;
                int ii1 = ii + 1;
                blocks[ii] = pole[jj + 0] << 24;
                blocks[ii] = blocks[ii] + (pole[jj + 1] << 16);
                blocks[ii] = blocks[ii] + (pole[jj + 2] << 8);
                blocks[ii] = blocks[ii] + pole[jj + 3];
                blocks[ii1] = pole[jj + 4] << 24;
                blocks[ii1] = blocks[ii1] + (pole[jj + 5] << 16);
                blocks[ii1] = blocks[ii1] + (pole[jj + 6] << 8);
                blocks[ii1] = blocks[ii1] + pole[jj + 7];
                String toString = BigInteger.valueOf(blocks[ii]).toString(16);
                System.out.println(toString);

                String toString2 = BigInteger.valueOf(blocks[ii1]).toString(16);
                System.out.println(toString2);
            }

            //int pocetBloku8 = pocetBloku << 3; //8 * pocetBloku
            //int pocetBloku81 = (pocetBloku - 1) << 3;
            int jj = 8 * (pocetBloku - 1);
            int ii = 2 * (pocetBloku - 1);
            int ii1 = ii + 1;
            blocks[ii] = pole[jj + 0] << 24;
            blocks[ii1] = 0;
            if (pocetBytuZbyva > 1) {
                blocks[ii] = blocks[ii] + (pole[jj + 1] << 16);
            }
            if (pocetBytuZbyva > 2) {
                blocks[ii] = blocks[ii] + (pole[jj + 2] << 8);
            }
            if (pocetBytuZbyva > 3) {
                blocks[ii] = blocks[ii] + (pole[jj + 3]);
            }
            //---
            if (pocetBytuZbyva > 4) {
                blocks[ii1] = pole[jj + 0] << 24;
            }
            if (pocetBytuZbyva > 5) {
                blocks[ii1] = blocks[ii1] + (pole[jj + 1] << 16);
            }
            if (pocetBytuZbyva > 6) {
                blocks[ii1] = blocks[ii1] + (pole[jj + 2] << 8);
            }
            if (pocetBytuZbyva > 7) {
                blocks[ii1] = blocks[ii1] + pole[jj + 3];
            }
            String toString = BigInteger.valueOf(blocks[ii]).toString(16);
            System.out.println(toString);

            String toString2 = BigInteger.valueOf(blocks[ii1]).toString(16);
            System.out.println(toString2);

        }
        //-----------------------------------------------------------------------------------------
        // Naplnění klíče
        //-----------------------------------------------------------------------------------------
        int[] klic2 = new int[4];
        for (int i = 0; i < 4; i++) {
            klic2[i] = 0;
        }

        for (int i = 0; i < klic.length; i++) {
            int j = i >> 2;
            int k = i % 4;
            switch (k) {
                case 0:
                    klic2[j] = klic[i] << 24;
                    break;
                case 1:
                    klic2[j] = klic2[j] + (klic[i] << 16);
                    break;
                case 2:
                    klic2[j] = klic2[j] + (klic[i] << 8);
                    break;
                case 3:
                    klic2[j] = klic2[j] + klic[i];
                    break;

            }

        }
        System.out.println("KLIC");
        String toStringX = BigInteger.valueOf(klic2[0]).toString(16);
        System.out.println(toStringX);

        String toStringY = BigInteger.valueOf(klic2[1]).toString(16);
        System.out.println(toStringY);

        String toStringZ = BigInteger.valueOf(klic2[2]).toString(16);
        System.out.println(toStringZ);

        String toStringW = BigInteger.valueOf(klic2[3]).toString(16);
        System.out.println(toStringW);
        System.out.println("------------------------------------------");

        byte[] vystup = new byte[pocetBloku << 3];
        int k = 0;
        for (int i = 0; i < pocetBloku; i++) {
            int[] block3 = new int[2];
            int ii = i << 1;
            block3[0] = blocks[ii + 0];
            block3[1] = blocks[ii + 1];
            String toString = BigInteger.valueOf(block3[0]).toString(16);
            System.out.println(toString);

            String toString2 = BigInteger.valueOf(block3[1]).toString(16);
            System.out.println(toString2);
            //--
            int[] block4 = encipher4(block3, klic2);
            //--
            vystup[k] = (byte) ((block4[0] >> 24) & 0xff);
            vystup[k + 1] = (byte) ((block4[0] >> 16) & 0xff);
            vystup[k + 2] = (byte) ((block4[0] >> 8) & 0xff);
            vystup[k + 3] = (byte) (block4[0] & 0xff);
            //--
            vystup[k + 4] = (byte) ((block4[1] >> 24) & 0xff);
            vystup[k + 5] = (byte) ((block4[1] >> 16) & 0xff);
            vystup[k + 6] = (byte) ((block4[1] >> 8) & 0xff);
            vystup[k + 7] = (byte) (block4[1] & 0xff);
            k = k + 8;
        }

        return vystup;

    }

    public static byte[] desifrovani(byte[] pole, byte[] klic) throws UnsupportedEncodingException, Exception {
        int pocetBloku = pole.length >> 3;
        int pocetBytuZbyva = pole.length - (pocetBloku << 3);
        int[] blocks = new int[pocetBloku << 1];

        //-----------------------------------------------------------------------------------------
        //   Naplnění datových bloků
        //-----------------------------------------------------------------------------------------
        if (pocetBytuZbyva == 0) {
            blocks = new int[pocetBloku << 1];
            for (int i = 0; i < pocetBloku; i++) {
                int jj = i << 3;
                int ii = i << 1;
                int ii1 = ii + 1;
                blocks[ii] = pole[8 * i + 0] << 24;
                blocks[ii] = blocks[ii] + (pole[jj + 1] << 16);
                blocks[ii] = blocks[ii] + (pole[jj + 2] << 8);
                blocks[ii] = blocks[ii] + pole[jj + 3];
                blocks[ii1] = pole[jj + 4] << 24;
                blocks[ii1] = blocks[ii1] + (pole[jj + 5] << 16);
                blocks[ii1] = blocks[ii1] + (pole[jj + 6] << 8);
                blocks[ii1] = blocks[ii1] + pole[jj + 7];
            }

        } else {
            throw new Exception("Délka dešifrované zprávy musí být celistvým násoblem 8 bytů!");
        }

        //-----------------------------------------------------------------------------------------
        // Naplnění klíče
        //-----------------------------------------------------------------------------------------
        int[] klic2 = new int[4];
        for (int i = 0; i < 4; i++) {
            klic2[i] = 0;
        }

        for (int i = 0; i < klic.length; i++) {
            int j = i >> 2;
            int k = i % 4;
            switch (k) {
                case 0:
                    klic2[j] = klic[i] << 24;
                    break;
                case 1:
                    klic2[j] = klic2[j] + (klic[i] << 16);
                    break;
                case 2:
                    klic2[j] = klic2[j] + (klic[i] << 8);
                    break;
                case 3:
                    klic2[j] = klic2[j] + klic[i];
                    break;

            }

        }

        System.out.println("KLIC");
        String toStringX = BigInteger.valueOf(klic2[0]).toString(16);

        System.out.println(toStringX);

        String toStringY = BigInteger.valueOf(klic2[1]).toString(16);

        System.out.println(toStringY);

        String toStringZ = BigInteger.valueOf(klic2[2]).toString(16);

        System.out.println(toStringZ);

        String toStringW = BigInteger.valueOf(klic2[3]).toString(16);

        System.out.println(toStringW);

        System.out.println("------------------------------------------");

        byte[] vystup = new byte[pocetBloku << 3];
        int k = 0;
        for (int i = 0; i < pocetBloku; i++) {
            int[] block3 = new int[2];
            int ii = i << 1;
            block3[0] = blocks[ii + 0];
            block3[1] = blocks[ii + 1];
            String toString = BigInteger.valueOf(block3[0]).toString(16);
            System.out.println(toString);

            String toString2 = BigInteger.valueOf(block3[1]).toString(16);
            System.out.println(toString2);
            //--
            int[] block4 = decipher4(block3, klic2);
            System.out.println("Po dešifrování...");
            String toString3 = BigInteger.valueOf(block4[0]).toString(16);
            System.out.println(toString3);

            String toString4 = BigInteger.valueOf(block4[1]).toString(16);
            System.out.println(toString4);
            //--
            vystup[k] = (byte) ((block4[0] >> 24) & 0xff);
            vystup[k + 1] = (byte) ((block4[0] >> 16) & 0xff);
            vystup[k + 2] = (byte) ((block4[0] >> 8) & 0xff);
            vystup[k + 3] = (byte) (block4[0] & 0xff);
            //--
            vystup[k + 4] = (byte) ((block4[1] >> 24) & 0xff);
            vystup[k + 5] = (byte) ((block4[1] >> 16) & 0xff);
            vystup[k + 6] = (byte) ((block4[1] >> 8) & 0xff);
            vystup[k + 7] = (byte) (block4[1] & 0xff);
            k = k + 8;
        }

        return vystup;

    }

    public static void main(String[] arg) throws UnsupportedEncodingException {
        /*
        BigInteger bi1 = new BigInteger("61626346", 16);
        BigInteger bi2 = new BigInteger("65666768", 16);
         */
        BigInteger bi1 = new BigInteger("81828386", 16);
        BigInteger bi2 = new BigInteger("f5f6f7f8", 16);

        BigInteger key1 = new BigInteger("71727374", 16);
        BigInteger key2 = new BigInteger("81828384", 16);

        BigInteger key3 = new BigInteger("70726465", 16);
        BigInteger key4 = new BigInteger("6d726461", 16);

        int[] plain = new int[2];
        plain[0] = bi1.intValue();
        plain[1] = bi2.intValue();

        int[] keys = new int[4];
        keys[0] = key1.intValue();
        keys[1] = key2.intValue();
        keys[2] = key3.intValue();
        keys[3] = key4.intValue();

        int[] encipher = encipher4(plain, keys);
        int[] decipher = decipher4(encipher, keys);

        String toString = BigInteger.valueOf(decipher[0]).toString(16);
        System.out.println(toString);

        String toString2 = BigInteger.valueOf(decipher[1]).toString(16);
        System.out.println(toString2);

        //String plain = "Byl pozdní večer, první máj, večerní máj, byl lásky čas, hrdliččin zval ku lásce hlas, tak kde borový zaváněl háj";
        //String plain = "12345678";
        String klic = "0123456789123456";
        //byte[] plainBytes = plain.getBytes("UTF-8");
        /*
        byte[] klicBytes = klic.getBytes("UTF-8");
        for (int i = 0; i < plainBytes.length; i++) {
            System.out.format("i = %d, zdroj = 0x%02X\n", i, plainBytes[i]);
        }
         */

        int uu = 0;
        /*
        byte[] zasifrovynyRetezec = sifrovani(plainBytes, klicBytes);
        byte[] desifrovanyRetezec = null;
        try {
             desifrovanyRetezec = desifrovani(zasifrovynyRetezec, klicBytes);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        for (int i = 0; i < desifrovanyRetezec.length; i++) {
            System.out.format("i = %d, zdroj = 0x%02X\n", i, desifrovanyRetezec[i]);
        }
         */

    }

}
