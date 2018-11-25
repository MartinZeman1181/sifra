
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
public class SHA2 {

    private static final int nrRounds = 80;

    private static final long[] hp
            = {0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL, 0xa54ff53a5f1d36f1L,
                0x510e527fade682d1L, 0x9b05688c2b3e6c1fL, 0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L};

    private static final long[] k
            = {0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL, 0x3956c25bf348b538L,
                0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L, 0xd807aa98a3030242L, 0x12835b0145706fbeL,
                0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L, 0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L,
                0xc19bf174cf692694L, 0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L,
                0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L, 0x983e5152ee66dfabL,
                0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L, 0xc6e00bf33da88fc2L, 0xd5a79147930aa725L,
                0x06ca6351e003826fL, 0x142929670a0e6e70L, 0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL,
                0x53380d139d95b3dfL, 0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL,
                0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L, 0xd192e819d6ef5218L,
                0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L, 0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L,
                0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L, 0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L,
                0x682e6ff3d6b2b8a3L, 0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL,
                0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL, 0xca273eceea26619cL,
                0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L, 0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L,
                0x113f9804bef90daeL, 0x1b710b35131c471bL, 0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL,
                0x431d67c49c100d4cL, 0x4cc5d4becb3e42b6L, 0x597f299cfc657e2aL, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L};

    public SHA2() {
    }

    public String tiskSlova(long x) throws UnsupportedEncodingException {
        byte[] bp = new byte[8];
        bp[0] = (byte) (((x & 0xffffffffffffffffL) >> 56) & 0xff);
        bp[1] = (byte) (((x & 0xffffffffffffffffL) >> 48) & 0xff);
        bp[2] = (byte) (((x & 0xffffffffffffffffL) >> 40) & 0xff);
        bp[3] = (byte) (((x & 0xffffffffffffffffL) >> 32) & 0xff);
        bp[4] = (byte) (((x & 0xffffffffffffffffL) >> 24) & 0xff);
        bp[5] = (byte) (((x & 0xffffffffffffffffL) >> 16) & 0xff);
        bp[6] = (byte) (((x & 0xffffffffffffffffL) >> 8) & 0xff);
        bp[7] = (byte) (((x & 0xffffffffffffffffL)) & 0xff);
        StringBuilder sb = new StringBuilder();
        for (byte b : bp) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

    private long shiftRight8(long x, int shift) {

        return ((x) >>> (shift % 64));
    }

    public long rotateRight8(long x, int shift) {
        return (((x & 0xffffffffffffffffL) >>> (shift % 64)) | ((x & 0xffffffffffffffffL) << (64 - (shift % 64))));
    }

    public long rotateLeft8(long x, int shift) {
        return (((x & 0xffffffffffffffffL) << (shift % 64)) | ((x & 0xffffffffffffffffL) >>> (64 - (shift % 64))));
    }

  
    
    
    public void hash() throws UnsupportedEncodingException {
        long[] w = new long[nrRounds];
        long[] hx = new long[nrRounds];

        /* VYPLNĚNÍ JEDNOHO BLOKU S OBSAHEM abc*/
 
        w[0] = 0x6162638000000000L;
        w[1] = 0x0000000000000000L;
        w[2] = 0x0000000000000000L;
        w[3] = 0x0000000000000000L;
        w[4] = 0x0000000000000000L;
        w[5] = 0x0000000000000000L;
        w[6] = 0x0000000000000000L;
        w[7] = 0x0000000000000000L;
        w[8] = 0x0000000000000000L;
        w[9] = 0x0000000000000000L;
        w[10] = 0x0000000000000000L;
        w[11] = 0x0000000000000000L;
        w[12] = 0x0000000000000000L;
        w[13] = 0x0000000000000000L;
        w[14] = 0x0000000000000000L;
        w[15] = 0x0000000000000018L;
      
        

        int pocetBloku = 1;
        for (int i = 0; i < 8; i++) {
            hx[i] = hp[i];
        }

        /*
         Extend the first 16 words into the remaining 48 words w[16..63] of the message schedule array:
        for i from 16 to 63
            s0 := (w[i-15] rightrotate 7) xor (w[i-15] rightrotate 18) xor (w[i-15] rightshift 3)
            s1 := (w[i-2] rightrotate 17) xor (w[i-2] rightrotate 19) xor (w[i-2] rightshift 10)
            w[i] := w[i-16] + s0 + w[i-7] + s1
         */
        for (int i = 16; i < nrRounds; i++) {
            /* W[t] = (Sha512.σ1(W[t-2]).add(W[t-7]).add(Sha512.σ0(W[t-15])).add(W[t-16]));*/
            long s0 = (rotateRight8(w[i - 15], 1) & 0xffffffffffffffffL) ^ (rotateRight8(w[i - 15], 8) & 0xffffffffffffffffL) ^ (shiftRight8(w[i - 15], 7) & 0xffffffffffffffffL);
            long s1 = (rotateRight8(w[i - 2], 19) & 0xffffffffffffffffL) ^ (rotateRight8(w[i - 2], 61) & 0xffffffffffffffffL) ^ (shiftRight8(w[i - 2], 6) & 0xffffffffffffffffL);
            w[i] = (w[i - 16] & 0xffffffffffffffffL) + (s0 & 0xffffffffffffffffL) + (w[i - 7] & 0xffffffffffffffffL) + (s1 & 0xffffffffffffffffL);
        }

        long a = hp[0] & 0xffffffffffffffffL;
        long b = hp[1] & 0xffffffffffffffffL;
        long c = hp[2] & 0xffffffffffffffffL;
        long d = hp[3] & 0xffffffffffffffffL;
        long e = hp[4] & 0xffffffffffffffffL;
        long f = hp[5] & 0xffffffffffffffffL;
        long g = hp[6] & 0xffffffffffffffffL;
        long h = hp[7] & 0xffffffffffffffffL;
        System.out.format(">>>a = %s, b = %s, c = %s, d = %s\n", tiskSlova(a), tiskSlova(b), tiskSlova(c), tiskSlova(d));
        System.out.format(">>>e = %s, f = %s, g = %s, h = %s\n\n", tiskSlova(e), tiskSlova(f), tiskSlova(g), tiskSlova(h));
        System.out.println("================================================================================================");

        /*
        SHA-512 má shodnou strukturu jako SHA-256, ale:

        zpráva je rozdělena na 1024bitové bloky,
        počáteční hodnoty hash a kruhové konstanty jsou rozšířeny na 64 bitů,
        tam je 80 kol namísto 64,
        plánovací pole pošty w má 80 64bitových slov namísto 64 32bitových slov,
        rozšiřovat pole s plánem zpráv w, smyčka je 16 až 79 místo 16 až 63,
        kruhové konstanty jsou založeny na prvních 80 prvcích 2..409,
        velikost slova použitá pro výpočty je dlouhá 64 bitů,
        připojená délka zprávy (před předběžným zpracováním) v bitech je 128bitové velké endiánové číslo a
        použitá množství posunu a otáčení jsou odlišná.
         */
        //bloky 1024 bitů = 128 bytů = 16  8-mi bytových slov
        for (int i = 0; i < nrRounds; i++) {
            /*
                long S0 = rotateRight8(a, 28) ^ rotateRight8(a, 34) ^ rotateRight8(a, 39);
                long S1 = rotateRight8(e, 14) ^ rotateRight8(e, 18) ^ rotateRight8(e, 41);                
             */
            long S1 = (rotateRight8(e, 14) & 0xffffffffffffffffL) ^ (rotateRight8(e, 18) & 0xffffffffffffffffL) ^ (rotateRight8(e, 41) & 0xffffffffffffffffL);
            System.out.format("1. i = %d; e = %s, S1 = %s\n", i, tiskSlova(e), tiskSlova(S1) );
            /* ch := (e and f) xor ((not e) and g) */
            long ch = ((e & 0xffffffffffffffffL) & (f & 0xffffffffffffffffL)) ^ ((~(e & 0xffffffffffffffffL)) & (g & 0xffffffffffffffffL));
            System.out.format("2. i = %d; e = %s, f = %s, g = %s, ch = %s\n", i, tiskSlova(e), tiskSlova(f),  tiskSlova(g), tiskSlova(ch) );
            long temp1 = (h & 0xffffffffffffffffL)
                    + (S1 & 0xffffffffffffffffL)
                    + (ch & 0xffffffffffffffffL)
                    + (k[i] & 0xffffffffffffffffL)
                    + (w[i] & 0xffffffffffffffffL);
            System.out.format("3. i = %d; h = %s, S1 = %s, ch = %s, k[i] = %s, w[i] = %s, tmp1 = %s\n", i, tiskSlova(h), tiskSlova(S1),  tiskSlova(ch),  tiskSlova(k[i]),  tiskSlova(w[i]), tiskSlova(temp1) );
            long S0 = (rotateRight8(a, 28) & 0xffffffffffffffffL) ^ (rotateRight8(a, 34) & 0xffffffffffffffffL) ^ (rotateRight8(a, 39) & 0xffffffffffffffffL);
            System.out.format("4. i = %d; a = %s, S0 = %s\n", i, tiskSlova(a), tiskSlova(S0));
            /*maj := (a and b) xor (a and c) xor (b and c)*/
            long maj = ((a & 0xffffffffffffffffL) & (b & 0xffffffffffffffffL)) ^ ((a & 0xffffffffffffffffL) & (c & 0xffffffffffffffffL)) ^ ((b & 0xffffffffffffffffL) & (c & 0xffffffffffffffffL));
            System.out.format("5. i = %d, a = %s, b = %s, c = %s, maj = %s\n", i, tiskSlova(a), tiskSlova(b), tiskSlova(c), tiskSlova(maj));
            long temp2 = (S0 & 0xffffffffffffffffL) + (maj & 0xffffffffffffffffL);
            System.out.format("6. i = %d, S0 = %s, maj = %s, temp2 = %s\n", i, tiskSlova(S0), tiskSlova(maj), tiskSlova(temp2));
            h = g & 0xffffffffffffffffL;
            System.out.format("7. i = %d, h = %s\n", i, tiskSlova(h));
            g = f & 0xffffffffffffffffL;
            System.out.format("8. i = %d, g = %s\n", i, tiskSlova(g));
            f = e & 0xffffffffffffffffL;
            System.out.format("9. i = %d, f = %s\n", i, tiskSlova(f));
            e = (d & 0xffffffffffffffffL) + (temp1 & 0xffffffffffffffffL);
            System.out.format("10. i = %d, e = %s\n", i, tiskSlova(e));
            d = c & 0xffffffffffffffffL;
            System.out.format("11. i = %d, d = %s\n", i, tiskSlova(d));
            c = b & 0xffffffffffffffffL;
            System.out.format("12. i = %d, c = %s\n", i, tiskSlova(c));
            b = a & 0xffffffffffffffffL;
            System.out.format("13. i = %d, b = %s\n", i, tiskSlova(b));
            a = (temp1 & 0xffffffffffffffffL) + (temp2 & 0xffffffffffffffffL);
            System.out.format("14. i = %d, a = %s\n", i, tiskSlova(a));
            /*
            System.out.format("round = %d\n", i);
            System.out.format("S0 = %s, S1 = %s, ch = %s, maj = %s, temp1 = %s, temp2 = %s\n", tiskSlova(S0), tiskSlova(S1), tiskSlova(ch), tiskSlova(maj), tiskSlova(temp1), tiskSlova(temp2));
            System.out.format("a = %s, b = %s, c = %s, d = %s\n", tiskSlova(a), tiskSlova(b), tiskSlova(c), tiskSlova(d));
            System.out.format("e = %s, f = %s, g = %s, h = %s\n\n", tiskSlova(e), tiskSlova(f), tiskSlova(g), tiskSlova(h));
            */
            
        }

        hx[0] = (hx[0] & 0xffffffffffffffffL) + (a & 0xffffffffffffffffL);
        hx[1] = (hx[1] & 0xffffffffffffffffL) + (b & 0xffffffffffffffffL);
        hx[2] = (hx[2] & 0xffffffffffffffffL) + (c & 0xffffffffffffffffL);
        hx[3] = (hx[3] & 0xffffffffffffffffL) + (d & 0xffffffffffffffffL);
        hx[4] = (hx[4] & 0xffffffffffffffffL) + (e & 0xffffffffffffffffL);
        hx[5] = (hx[5] & 0xffffffffffffffffL) + (f & 0xffffffffffffffffL);
        hx[6] = (hx[6] & 0xffffffffffffffffL) + (g & 0xffffffffffffffffL);
        hx[7] = (hx[7] & 0xffffffffffffffffL) + (h & 0xffffffffffffffffL);

        String SHA2_512 = tiskSlova(hx[0]) + "-"
                + tiskSlova(hx[1]) + "-"
                + tiskSlova(hx[2]) + "-"
                + tiskSlova(hx[3]) + "-"
                + tiskSlova(hx[4]) + "-"
                + tiskSlova(hx[5]) + "-"
                + tiskSlova(hx[6]) + "-"
                + tiskSlova(hx[7]);
        System.out.println("Výsledný hash SHA2_512 = " + SHA2_512);

    }

    public static void main(String[] arg) throws UnsupportedEncodingException {
        SHA2 sha2 = new SHA2();
        sha2.hash();
        /*
        long lng = 0xbb67ae8584caa73bL;
        String tiskSlova = sha2.tiskSlova(lng);
        int iii;
         */

    }

}
