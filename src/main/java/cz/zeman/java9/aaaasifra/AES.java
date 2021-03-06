/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

/**
 *
 * @author martin
 */
public class AES {

      private static final byte[][] kodovaciTabulka = new byte[][]{
        {(byte) 0x63, (byte) 0x7c, (byte) 0x77, (byte) 0x7b, (byte) 0xf2, (byte) 0x6b, (byte) 0x6f, (byte) 0xc5, (byte) 0x30, (byte) 0x01, (byte) 0x67, (byte) 0x2b, (byte) 0xfe, (byte) 0xd7, (byte) 0xab, (byte) 0x76},
        {(byte) 0xca, (byte) 0x82, (byte) 0xc9, (byte) 0x7d, (byte) 0xfa, (byte) 0x59, (byte) 0x47, (byte) 0xf0, (byte) 0xad, (byte) 0xd4, (byte) 0xa2, (byte) 0xaf, (byte) 0x9c, (byte) 0xa4, (byte) 0x72, (byte) 0xc0},
        {(byte) 0xb7, (byte) 0xfd, (byte) 0x93, (byte) 0x26, (byte) 0x36, (byte) 0x3f, (byte) 0xf7, (byte) 0xcc, (byte) 0x34, (byte) 0xa5, (byte) 0xe5, (byte) 0xf1, (byte) 0x71, (byte) 0xd8, (byte) 0x31, (byte) 0x15},
        {(byte) 0x04, (byte) 0xc7, (byte) 0x23, (byte) 0xc3, (byte) 0x18, (byte) 0x96, (byte) 0x05, (byte) 0x9a, (byte) 0x07, (byte) 0x12, (byte) 0x80, (byte) 0xe2, (byte) 0xeb, (byte) 0x27, (byte) 0xb2, (byte) 0x75},
        {(byte) 0x09, (byte) 0x83, (byte) 0x2c, (byte) 0x1a, (byte) 0x1b, (byte) 0x6e, (byte) 0x5a, (byte) 0xa0, (byte) 0x52, (byte) 0x3b, (byte) 0xd6, (byte) 0xb3, (byte) 0x29, (byte) 0xe3, (byte) 0x2f, (byte) 0x84},
        {(byte) 0x53, (byte) 0xd1, (byte) 0x00, (byte) 0xed, (byte) 0x20, (byte) 0xfc, (byte) 0xb1, (byte) 0x5b, (byte) 0x6a, (byte) 0xcb, (byte) 0xbe, (byte) 0x39, (byte) 0x4a, (byte) 0x4c, (byte) 0x58, (byte) 0xcf},
        {(byte) 0xd0, (byte) 0xef, (byte) 0xaa, (byte) 0xfb, (byte) 0x43, (byte) 0x4d, (byte) 0x33, (byte) 0x85, (byte) 0x45, (byte) 0xf9, (byte) 0x02, (byte) 0x7f, (byte) 0x50, (byte) 0x3c, (byte) 0x9f, (byte) 0xa8},
        {(byte) 0x51, (byte) 0xa3, (byte) 0x40, (byte) 0x8f, (byte) 0x92, (byte) 0x9d, (byte) 0x38, (byte) 0xf5, (byte) 0xbc, (byte) 0xb6, (byte) 0xda, (byte) 0x21, (byte) 0x10, (byte) 0xff, (byte) 0xf3, (byte) 0xd2},
        {(byte) 0xcd, (byte) 0x0c, (byte) 0x13, (byte) 0xec, (byte) 0x5f, (byte) 0x97, (byte) 0x44, (byte) 0x17, (byte) 0xc4, (byte) 0xa7, (byte) 0x7e, (byte) 0x3d, (byte) 0x64, (byte) 0x5d, (byte) 0x19, (byte) 0x73},
        {(byte) 0x60, (byte) 0x81, (byte) 0x4f, (byte) 0xdc, (byte) 0x22, (byte) 0x2a, (byte) 0x90, (byte) 0x88, (byte) 0x46, (byte) 0xee, (byte) 0xb8, (byte) 0x14, (byte) 0xde, (byte) 0x5e, (byte) 0x0b, (byte) 0xdb},
        {(byte) 0xe0, (byte) 0x32, (byte) 0x3a, (byte) 0x0a, (byte) 0x49, (byte) 0x06, (byte) 0x24, (byte) 0x5c, (byte) 0xc2, (byte) 0xd3, (byte) 0xac, (byte) 0x62, (byte) 0x91, (byte) 0x95, (byte) 0xe4, (byte) 0x79},
        {(byte) 0xe7, (byte) 0xc8, (byte) 0x37, (byte) 0x6d, (byte) 0x8d, (byte) 0xd5, (byte) 0x4e, (byte) 0xa9, (byte) 0x6c, (byte) 0x56, (byte) 0xf4, (byte) 0xea, (byte) 0x65, (byte) 0x7a, (byte) 0xae, (byte) 0x08},
        {(byte) 0xba, (byte) 0x78, (byte) 0x25, (byte) 0x2e, (byte) 0x1c, (byte) 0xa6, (byte) 0xb4, (byte) 0xc6, (byte) 0xe8, (byte) 0xdd, (byte) 0x74, (byte) 0x1f, (byte) 0x4b, (byte) 0xbd, (byte) 0x8b, (byte) 0x8a},
        {(byte) 0x70, (byte) 0x3e, (byte) 0xb5, (byte) 0x66, (byte) 0x48, (byte) 0x03, (byte) 0xf6, (byte) 0x0e, (byte) 0x61, (byte) 0x35, (byte) 0x57, (byte) 0xb9, (byte) 0x86, (byte) 0xc1, (byte) 0x1d, (byte) 0x9e},
        {(byte) 0xe1, (byte) 0xf8, (byte) 0x98, (byte) 0x11, (byte) 0x69, (byte) 0xd9, (byte) 0x8e, (byte) 0x94, (byte) 0x9b, (byte) 0x1e, (byte) 0x87, (byte) 0xe9, (byte) 0xce, (byte) 0x55, (byte) 0x28, (byte) 0xdf},
        {(byte) 0x8c, (byte) 0xa1, (byte) 0x89, (byte) 0x0d, (byte) 0xbf, (byte) 0xe6, (byte) 0x42, (byte) 0x68, (byte) 0x41, (byte) 0x99, (byte) 0x2d, (byte) 0x0f, (byte) 0xb0, (byte) 0x54, (byte) 0xbb, (byte) 0x16}
    };

    private static final byte[][] dekodovaciTabulka = new byte[][]{
        {(byte) 0x52, (byte) 0x09, (byte) 0x6a, (byte) 0xd5, (byte) 0x30, (byte) 0x36, (byte) 0xa5, (byte) 0x38, (byte) 0xbf, (byte) 0x40, (byte) 0xa3, (byte) 0x9e, (byte) 0x81, (byte) 0xf3, (byte) 0xd7, (byte) 0xfb},
        {(byte) 0x7c, (byte) 0xe3, (byte) 0x39, (byte) 0x82, (byte) 0x9b, (byte) 0x2f, (byte) 0xff, (byte) 0x87, (byte) 0x34, (byte) 0x8e, (byte) 0x43, (byte) 0x44, (byte) 0xc4, (byte) 0xde, (byte) 0xe9, (byte) 0xcb},
        {(byte) 0x54, (byte) 0x7b, (byte) 0x94, (byte) 0x32, (byte) 0xa6, (byte) 0xc2, (byte) 0x23, (byte) 0x3d, (byte) 0xee, (byte) 0x4c, (byte) 0x95, (byte) 0x0b, (byte) 0x42, (byte) 0xfa, (byte) 0xc3, (byte) 0x4e},
        {(byte) 0x08, (byte) 0x2e, (byte) 0xa1, (byte) 0x66, (byte) 0x28, (byte) 0xd9, (byte) 0x24, (byte) 0xb2, (byte) 0x76, (byte) 0x5b, (byte) 0xa2, (byte) 0x49, (byte) 0x6d, (byte) 0x8b, (byte) 0xd1, (byte) 0x25},
        {(byte) 0x72, (byte) 0xf8, (byte) 0xf6, (byte) 0x64, (byte) 0x86, (byte) 0x68, (byte) 0x98, (byte) 0x16, (byte) 0xd4, (byte) 0xa4, (byte) 0x5c, (byte) 0xcc, (byte) 0x5d, (byte) 0x65, (byte) 0xb6, (byte) 0x92},
        {(byte) 0x6c, (byte) 0x70, (byte) 0x48, (byte) 0x50, (byte) 0xfd, (byte) 0xed, (byte) 0xb9, (byte) 0xda, (byte) 0x5e, (byte) 0x15, (byte) 0x46, (byte) 0x57, (byte) 0xa7, (byte) 0x8d, (byte) 0x9d, (byte) 0x84},
        {(byte) 0x90, (byte) 0xd8, (byte) 0xab, (byte) 0x00, (byte) 0x8c, (byte) 0xbc, (byte) 0xd3, (byte) 0x0a, (byte) 0xf7, (byte) 0xe4, (byte) 0x58, (byte) 0x05, (byte) 0xb8, (byte) 0xb3, (byte) 0x45, (byte) 0x06},
        {(byte) 0xd0, (byte) 0x2c, (byte) 0x1e, (byte) 0x8f, (byte) 0xca, (byte) 0x3f, (byte) 0x0f, (byte) 0x02, (byte) 0xc1, (byte) 0xaf, (byte) 0xbd, (byte) 0x03, (byte) 0x01, (byte) 0x13, (byte) 0x8a, (byte) 0x6b},
        {(byte) 0x3a, (byte) 0x91, (byte) 0x11, (byte) 0x41, (byte) 0x4f, (byte) 0x67, (byte) 0xdc, (byte) 0xea, (byte) 0x97, (byte) 0xf2, (byte) 0xcf, (byte) 0xce, (byte) 0xf0, (byte) 0xb4, (byte) 0xe6, (byte) 0x73},
        {(byte) 0x96, (byte) 0xac, (byte) 0x74, (byte) 0x22, (byte) 0xe7, (byte) 0xad, (byte) 0x35, (byte) 0x85, (byte) 0xe2, (byte) 0xf9, (byte) 0x37, (byte) 0xe8, (byte) 0x1c, (byte) 0x75, (byte) 0xdf, (byte) 0x6e},
        {(byte) 0x47, (byte) 0xf1, (byte) 0x1a, (byte) 0x71, (byte) 0x1d, (byte) 0x29, (byte) 0xc5, (byte) 0x89, (byte) 0x6f, (byte) 0xb7, (byte) 0x62, (byte) 0x0e, (byte) 0xaa, (byte) 0x18, (byte) 0xbe, (byte) 0x1b},
        {(byte) 0xfc, (byte) 0x56, (byte) 0x3e, (byte) 0x4b, (byte) 0xc6, (byte) 0xd2, (byte) 0x79, (byte) 0x20, (byte) 0x9a, (byte) 0xdb, (byte) 0xc0, (byte) 0xfe, (byte) 0x78, (byte) 0xcd, (byte) 0x5a, (byte) 0xf4},
        {(byte) 0x1f, (byte) 0xdd, (byte) 0xa8, (byte) 0x33, (byte) 0x88, (byte) 0x07, (byte) 0xc7, (byte) 0x31, (byte) 0xb1, (byte) 0x12, (byte) 0x10, (byte) 0x59, (byte) 0x27, (byte) 0x80, (byte) 0xec, (byte) 0x5f},
        {(byte) 0x60, (byte) 0x51, (byte) 0x7f, (byte) 0xa9, (byte) 0x19, (byte) 0xb5, (byte) 0x4a, (byte) 0x0d, (byte) 0x2d, (byte) 0xe5, (byte) 0x7a, (byte) 0x9f, (byte) 0x93, (byte) 0xc9, (byte) 0x9c, (byte) 0xef},
        {(byte) 0xa0, (byte) 0xe0, (byte) 0x3b, (byte) 0x4d, (byte) 0xae, (byte) 0x2a, (byte) 0xf5, (byte) 0xb0, (byte) 0xc8, (byte) 0xeb, (byte) 0xbb, (byte) 0x3c, (byte) 0x83, (byte) 0x53, (byte) 0x99, (byte) 0x61},
        {(byte) 0x17, (byte) 0x2b, (byte) 0x04, (byte) 0x7e, (byte) 0xba, (byte) 0x77, (byte) 0xd6, (byte) 0x26, (byte) 0xe1, (byte) 0x69, (byte) 0x14, (byte) 0x63, (byte) 0x55, (byte) 0x21, (byte) 0x0c, (byte) 0x7d}
    };


    private static final byte[][] rcon = {
        {(byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80, 0x1b, 0x36},
        {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 0x00, 0x00},
        {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 0x00, 0x00},
        {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 0x00, 0x00}
    };

    private static final int[][] rizeniMixovani = {
        {2, 3, 1, 1},
        {1, 2, 3, 1},
        {1, 1, 2, 3},
        {3, 1, 1, 2}
    };

    private static final int[][] rizeniOdmixovani = {
        {14, 11, 13, 9},
        {9, 14, 11, 13},
        {13, 9, 14, 11},
        {11, 13, 9, 14}
    };

    private static byte[][] roundKeys = new byte[4][44];

    public AES() {
    }

    
    public static void main(String[] arg) {

        byte[] stav = {
            (byte) 0x32, (byte) 0x43, (byte) 0xf6, (byte) 0xa8,
            (byte) 0x88, (byte) 0x5a, (byte) 0x30, (byte) 0x8d,
            (byte) 0x31, (byte) 0x31, (byte) 0x98, (byte) 0xa2,
            (byte) 0xe0, (byte) 0x37, (byte) 0x07, (byte) 0x34
        };

        byte[] klic = {
            (byte) 0x2b, (byte) 0x7e, (byte) 0x15, (byte) 0x16,
            (byte) 0x28, (byte) 0xae, (byte) 0xd2, (byte) 0xa6,
            (byte) 0xab, (byte) 0xf7, (byte) 0x15, (byte) 0x88,
            (byte) 0x09, (byte) 0xcf, (byte) 0x4f, (byte) 0x3c
        };

        boolean tiskPovolen = true;

        vytvorRoundKeys(klic, tiskPovolen);

        tiskVektoru(tiskPovolen, "Před dekódováním ====> kopieMaticeDoVektoru", stav);

        byte[][] encoding = encoding(stav, tiskPovolen);
        tisk(tiskPovolen, 10, "**************** finále ****************", encoding);
        byte[] kopieMaticeDoVektoru = kopieMaticeDoVektoru(encoding);
        tiskVektoru(tiskPovolen, "Před dekódováním ====> kopieMaticeDoVektoru", kopieMaticeDoVektoru);
        System.out.println(">>>>>>>>>>>>>>>>>>>> dekódování <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        byte[][] decoded2 = decoding(kopieMaticeDoVektoru, tiskPovolen);
        tisk(tiskPovolen, 10, "**************** finále dekódování ****************", decoded2);

        byte[] kopieMaticeDoVektoru2 = kopieMaticeDoVektoru(decoded2);
        tiskVektoru(tiskPovolen, "Po dekódování ====> kopieMaticeDoVektoru2", kopieMaticeDoVektoru2);

    }
    
    
    /**
     * **************************************************************************************************
     */
    public static void vytvorRoundKeys(byte[] klic, boolean tiskPovolen) {
        byte[][] klicMatice = new byte[4][50];
        klicMatice = vyplnMatici(klic);
        tisk(tiskPovolen, -1, "ROUND KEY", klicMatice);
        KeySchedule(klicMatice);
        tiskKlicu(tiskPovolen, "KLICE ROUNDU");
    }

    public static void tiskRoundKey(boolean tiskPovolen, String nadpis, int round) {
        System.out.println(nadpis);
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.format("0x%02X, ", roundKeys[i][j + 4 * round]);
            }
            System.out.println();
        }
    }

    public static byte[] kopieMaticeDoVektoru(byte[][] matice) {
        byte[] vysl = new byte[4 * 4];
        int k = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                vysl[k++] = matice[i][j];
            }
        }
        return vysl;
    }

    public static void tiskVektoru(boolean tiskPovolen, String nadpis, byte[] vektor) {
        System.out.println(nadpis);
        for (int i = 0; i < 16; i++) {
            System.out.format("%d. 0x%02X\n", i, vektor[i]);
        }
    }

    public static void tiskKlicu(boolean tiskPovolen, String nadpis) {
        for (int round = 0; round < 11; round++) {
            System.out.println("***************************************************");
            System.out.format("%d. cykl    %s\n", round, nadpis);
            System.out.println("***************************************************");
            System.out.println();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.printf("0x%02X, ", roundKeys[i][4 * round + j]);
                }
                System.out.println();

            }

        }

    }

    public static byte[][] encoding(byte[] stav, boolean tiskPovolen) {
        byte[][] status = vyplnMatici(stav);
        /*
        byte[][] klicMatice = new byte[4][50];
        klicMatice = vyplnMatici(klic);
        tisk(tiskPovolen, -1, "ROUND KEY", klicMatice);
        byte[][] kliceRoundu = KeySchedule(klicMatice);
        tiskKlicu(tiskPovolen, "KLICE ROUNDU", kliceRoundu);
         */
        System.out.println("*********** start algoritmu ***********************");
        tisk(tiskPovolen, -1, "STATUS", status);

        status = AddRoundKey(status, 0);
        tisk(tiskPovolen, -1, "STATUS po startovacím roundu (první AddRoundKey)", status);
        System.out.println("************** zahájení cyklů *********************");
        for (int round = 0; round < 9; round++) {
            byte[][] subBytes = SubBytes(status);
            tisk(tiskPovolen, round, "SubBytes", subBytes);
            byte[][] shiftRows = ShiftRows(subBytes);
            tisk(tiskPovolen, round, "ShiftRows", shiftRows);
            byte[][] mixColumns = MixColumns(shiftRows);
            tisk(tiskPovolen, round, "MixColumns", mixColumns);
            byte[][] addRoundKey = AddRoundKey(mixColumns, round + 1);
            tisk(tiskPovolen, round, "AddRoundKey", addRoundKey);
            status = kopirujMatici(addRoundKey);
        }
        // závěrečný běh
        System.out.println("************** závěrečný běh *********************");
        int round = 9;
        byte[][] subBytes = SubBytes(status);
        tisk(tiskPovolen, round, "SubBytes", subBytes);
        byte[][] shiftRows = ShiftRows(subBytes);
        tisk(tiskPovolen, round, "ShiftRows", shiftRows);
        byte[][] addRoundKey = AddRoundKey(shiftRows, round + 1);
        tisk(tiskPovolen, round, "AddRoundKey", addRoundKey);
        status = kopirujMatici(addRoundKey);
        return status;
    }

    public static byte[][] decoding(byte[] stav, boolean tiskPovolen) {
        byte[][] status = vyplnMatici(stav);
        tisk(tiskPovolen, -1, "STATUS", status);
        /*
        byte[][] klicMatice = new byte[4][50];
        klicMatice = vyplnMatici(klic);
        tisk(tiskPovolen, -1, "ROUND KEY", klicMatice);
        byte[][] kliceRoundu = KeySchedule(klicMatice);
        
        tiskKlicu(tiskPovolen, "KLICE ROUNDU", kliceRoundu);
         */
        //-------------
        tiskRoundKey(tiskPovolen, "Klice tohoto roundu", 10);
        status = AddRoundKey(status, 10);
        tisk(tiskPovolen, 9, "STATUS po startovacím roundu (první AddRoundKey)", status);
        byte[][] shiftRowsInv = ShiftRowsInv(status);
        tisk(tiskPovolen, 9, "ShiftRowsInv", shiftRowsInv);
        byte[][] subBytesInv = SubBytesInv(shiftRowsInv);
        tisk(tiskPovolen, 9, "SubBytesInv", subBytesInv);
        status = kopirujMatici(subBytesInv);
        for (int round = 8; round >= 0; round--) {
            tiskRoundKey(tiskPovolen, "Klice tohoto roundu", round + 1);
            byte[][] addRoundKey = AddRoundKey(status, round + 1);
            tisk(tiskPovolen, round, "AddRoundKey", addRoundKey);
            byte[][] mixColumnsInv = MixColumnsInv(addRoundKey);
            tisk(tiskPovolen, round, "MixColumnsInv", mixColumnsInv);
            shiftRowsInv = ShiftRowsInv(mixColumnsInv);
            tisk(tiskPovolen, round, "ShiftRowsInv", shiftRowsInv);
            subBytesInv = SubBytesInv(shiftRowsInv);
            tisk(tiskPovolen, round, "SubBytesInv", subBytesInv);
            status = kopirujMatici(subBytesInv);
        }
        tiskRoundKey(tiskPovolen, "Klice tohoto roundu", 0);
        byte[][] addRoundKey = AddRoundKey(status, 0);
        tisk(tiskPovolen, 0, "AddRoundKey", addRoundKey);
        return addRoundKey;

    }

    //tisk tabulka 4 x 4
    public static void tisk(boolean tiskPovolen, int runda, String nadpis, byte[][] matice) {
        if (tiskPovolen) {
            System.out.println("*****************************************");
            System.out.printf("*    %d. cyklus,    %s                 \n", runda + 1, nadpis);
            //System.out.println();
            System.out.println("*****************************************");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.printf("0x%02X", matice[i][j]);
                    System.out.print(", ");
                }
                System.out.println();
            }
        }
    }

    public static byte[][] kopirujMatici(byte[][] mat) {
        byte[][] matice = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matice[i][j] = mat[i][j];
            }
        }
        return matice;
    }

    // vytvoř z pole 16 bytů matici 4x4
    public static byte[][] vyplnMatici(byte[] pole) {
        byte[][] matice = new byte[4][4];
        int j = 0;
        int k = 0;
        for (int i = 1; i <= 16; i++) {
            matice[j][k] = pole[i - 1];
            k = i / 4;
            j = i - 4 * k;
        }
        return matice;
    }

    public static byte[][] SubBytes(byte[][] pole) {
        byte tb[][] = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                byte byt = pole[i][j];
                int radek = ((byt & 0xf0) >> 4);
                int sloupec = byt & 0x0f;
                tb[i][j] = kodovaciTabulka[radek][sloupec];
                //System.out.printf("i = %d, j = %d  tab = 0x%02X\n", i, j, tb[i][j]);
            }
            //System.out.println("------------------------------------------------------------------------------------");
        }
        return tb;
    }

    private static byte[][] SubBytesInv(byte[][] pole) {
        byte tb[][] = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                byte byt = pole[i][j];
                int radek = ((byt & 0xf0) >> 4);
                int sloupec = byt & 0x0f;
                tb[i][j] = dekodovaciTabulka[radek][sloupec];
            }
        }
        return tb;
    }

    public static byte[][] ShiftRows(byte[][] pole) {
        byte[][] vystup = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                vystup[i][j] = pole[i][j];
            }
            for (int m = 0; m < i; m++) {
                byte hlavni = vystup[i][0];
                for (int k = 0; k < 3; k++) {
                    vystup[i][k] = vystup[i][k + 1];
                }
                vystup[i][3] = hlavni;

            }
        }
        return vystup;

    }

    public static byte[][] ShiftRowsInv(byte[][] pole) {
        byte[][] vystup = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                vystup[i][j] = pole[i][j];
            }
            for (int m = 0; m < i; m++) {
                byte hlavni = vystup[i][3];
                for (int k = 0; k < 3; k++) {
                    vystup[i][3 - k] = vystup[i][3 - k - 1];
                }
                vystup[i][0] = hlavni;

            }
        }
        return vystup;

    }

    public static byte[][] AddRoundKey(byte[][] status, /*byte[][] roundKey, */ int round) {
        byte[][] vysl = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //vysl[j][i] = (byte) (status[j][i] ^ roundKey[j + 4 * round][i]);
                vysl[i][j] = (byte) (status[i][j] ^ roundKeys[i][j + 4 * round]);
            }
        }
        return vysl;
    }

    public static void /*byte[][]*/ KeySchedule(byte[][] key) {
        //byte[][] roundKeys = new byte[4][44];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                roundKeys[i][j] = key[i][j];
            }

        }
        boolean zacatekRundy = false;
        byte[] b2 = new byte[4];
        for (int runda = 0; runda < 10; runda++) {
            //System.out.printf(">>>>>>>>>>>>>>>>>> runda = %d  <<<<<<<<<<<<<<<<<<\n", runda);
            zacatekRundy = true;
            for (int m = 0; m < 4; m++) {
                if (zacatekRundy) {
                    zacatekRundy = false;
                    for (int i = 0; i < 4; i++) {
                        b2[i] = roundKeys[i][4 * runda + 3];
                        //System.out.printf("%d = 0x%02X\n", i, b2[i]);
                    }
                    //System.out.println("-------------");
                    byte hlavni = b2[0];
                    for (int i = 0; i < 3; i++) {
                        b2[i] = b2[i + 1];
                        //System.out.printf("%d = 0x%02X\n", i, b2[i]);
                    }
                    b2[3] = hlavni;
                    //System.out.printf("b2[3] = %d\n", hlavni);
                    //System.out.println("==================");
                    for (int i = 0; i < 4; i++) {
                        byte byt = b2[i];
                        int radek = ((byt & 0xf0) >> 4);
                        int sloupec = byt & 0x0f;
                        b2[i] = kodovaciTabulka[radek][sloupec];
                        //System.out.printf("i = %d, b2 = 0x%02X\n", i, b2[i]);
                    }
                    //System.out.println("*******************");                    
                    for (int i = 0; i < 4; i++) {
                        //System.out.printf("i = %d, ey[i][4 * runda] = 0x%02X\n", i, roundKeys[i][4 * runda]);
                        roundKeys[i][4 * (runda + 1) + m] = (byte) (roundKeys[i][4 * runda] ^ b2[i] ^ rcon[i][runda]);
                        //System.out.printf("i = %d, key[i][4 * (runda + 1) + m] = 0x%02X\n", i, key[i][4 * (runda + 1) + m]);
                    }
                    //System.out.println("@@@@@@@@@@@@@@@@@@@");
                } else {
                    //System.out.println("####################################");
                    //System.out.printf("m = %d\n", m);
                    //System.out.println("####################################");
                    for (int i = 0; i < 4; i++) {
                        roundKeys[i][4 * (runda + 1) + m] = (byte) (roundKeys[i][4 * runda + m] ^ roundKeys[i][4 * (runda + 1) + m - 1]);
                        //System.out.printf("0x%02X  0x%02X     0x%02X\n ", key[i][4 * runda + m], key[i][4 * (runda + 1) + m - 1], key[i][4 * (runda + 1) + m]);
                    }
                    //System.out.println("####################################");

                }
            }
        }
        //return roundKeys;
    }

    public static byte getBit(byte b, int position) {
        return (byte) ((b >> position) & 0x01);
    }

    public static byte nasob2(byte par) {
        ////System.out.printf("par = 0x%02X\n", par);
        byte hb = getBit(par, 7);
        byte b = (byte) (par & 0x7f);
        b = (byte) (b << 1);
        if (hb != 0) {
            b = (byte) (b ^ 0x1b);
        }
        ////System.out.printf("b = 0x%02X\n", b);
        return b;
    }

    public static byte nasob3(byte par) {
        byte byte1 = nasob2(par);
        return (byte) (byte1 ^ par);
    }

    public static int provestModulo(int par) {
        //System.out.println("********začátek modulo ********");
        int par2 = par;
        int posunVlevo;
        int maska = 283;
        int maskaPosunuta;
        while (true) {
            int prvni = prvniBit(par2);
            //System.out.format("prvni = %d\n", prvni);
            if (prvni < 9) {
                //System.out.println("********konec*********");
                return par;
            } else {
                posunVlevo = prvni - 9;
                maskaPosunuta = maska << posunVlevo;
                //System.out.format("prozkoumej prvni bit -> posunVlevo = %d, par2 = %s, posunutá maska = %s\n", posunVlevo, Integer.toBinaryString(par2), Integer.toBinaryString(maskaPosunuta));
                par2 = par2 ^ maskaPosunuta;
                //System.out.format("posunVlevo = %d, par2 = %s, posunutá maska = %s\n", posunVlevo, Integer.toBinaryString(par2), Integer.toBinaryString(maskaPosunuta));
                if (par2 < 256) {
                    //System.out.println("********konec modulo*********");
                    return par2;
                }

            }
        }

    }

    public static int prvniBit(int par) {
        int par2 = par;
        int prvni = 0;
        for (int i = 0; i < 30; i++) {
            int bit = par2 & 1;
            if (bit > 0) {
                prvni = i + 1;
            }
            par2 = par2 >> 1;
        }
        return prvni;
    }

    public static int[] binarniMocniny(byte par, int mocnina) {
        int[] mocniny = new int[32];
        int celeCislo = par;
        if (celeCislo < 0) {
            celeCislo = 256 + celeCislo;
        }
        int m = 1;
        int i = 1;
        while (m <= mocnina) {
            mocniny[i] = provestModulo(celeCislo);
            //System.out.format("Výsledek ====>  index = %d, mocnina = %d, mocnina = %s\n", i, m, Integer.toBinaryString(mocniny[i]));
            celeCislo = celeCislo << 1;
            m = 2 * m;
            i = i + 1;
        }
        mocniny[0] = i - 1;
        int mn = 1;
        for (int j = 1; j <= mocniny[0]; j++) {
            //System.out.format("j = %d, mocnina = %d, %s,     \n", j, mn, Integer.toHexString(mocniny[j]));
            mn = mn << 1;
        }

        return mocniny;
    }

    public static byte galoisMultiplication(byte par, int mocnina) {
        int[] mocniny = binarniMocniny(par, mocnina);
        int index = mocniny[0];
        int stupenMocniny = 1 << (index - 1);
        //int zbytekMocniny = mocnina;
        int nabehlaMocnina = 0;
        byte vysledek = 0x00;
        while (index > 0) {
            nabehlaMocnina = nabehlaMocnina + stupenMocniny;
            if (nabehlaMocnina <= mocnina) {
                vysledek = (byte) (vysledek ^ mocniny[index]);
                //System.out.format("index = %d, stupenMocniny = %d,  %s,     \n", index, stupenMocniny, Integer.toHexString(mocniny[index]));
            } else {
                nabehlaMocnina = nabehlaMocnina - stupenMocniny;
            }
            stupenMocniny = stupenMocniny / 2;
            index = index - 1;
        }
        return vysledek;
    }

    /*
    public static byte galoisMultiplicationHex(byte par, byte mocnina) {
        int mocninaDekadicka = 16 * (mocnina >>> 4) + (mocnina & 0x0f);
        return galoisMultiplication(par, mocnina);
    }
     */
    public static byte[][] MixColumns(byte[][] matice) {
        byte[][] vysl = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //násobení j- tého řádku matice mat a j-tého sloupce matice
                vysl[i][j] = 0x00;
                for (int k = 0; k < 4; k++) {
                    vysl[i][j] = (byte) (vysl[i][j] ^ galoisMultiplication(matice[k][j], rizeniMixovani[i][k]));
                    /*
                    switch (rizeniMixovani[i][k]) {
                        case 1:
                            vysl[i][j] = (byte) (vysl[i][j] ^ matice[k][j]);
                            break;
                        case 2:
                            vysl[i][j] = (byte) (vysl[i][j] ^ nasob2(matice[k][j]));
                            break;
                        case 3:
                            vysl[i][j] = (byte) (vysl[i][j] ^ nasob3(matice[k][j]));
                            break;

                    }
                     */
                }
            }
        }
        return vysl;
    }

    public static byte[][] MixColumnsInv(byte[][] matice) {
        byte[][] vysl = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //násobení j- tého řádku matice mat a j-tého sloupce matice
                vysl[i][j] = 0x00;
                for (int k = 0; k < 4; k++) {
                    byte mocnina = (byte) rizeniOdmixovani[i][k];
                    byte nasobek = galoisMultiplication(matice[k][j], mocnina);
                    vysl[i][j] = (byte) (vysl[i][j] ^ nasobek);
                    //System.out.format("k = %d, matice[k][j] = 0x%02X, rizeniOdmixovani[i][k] = 0x%02X, nasobek = 0x%02X\n", k, matice[k][j], rizeniOdmixovani[i][k], nasobek);
                }
                //System.out.format("i = %d, j = %d, vysl[i][j] = 0x%02X\n", i, j, vysl[i][j]);
            }
        }
        return vysl;
    }

   
}
