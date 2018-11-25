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
public class RC5 {

    private static final int BLOCK_SIZE = 8;
    private static int c = 256;
    private static int r = 0;  //počet rund
    private int[] S;
    private int L[];
    private static final int w = 32; //počet bitů slova
    private static final int u = w / 8; //počet bytů slova
    private static final int P32 = 0xb7e15163;
    private static final int Q32 = 0x9e3779b9;

    public RC5() {
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
        S = new int[((r + 1) << 1)]; //stav vytvářený z bytů klíče
        //celý klíč se přetáthe do pomocného pole POM a dékla se doplní na 256 bytů, opakováním znaků klíče
        for (int i = 0; i < 256; i++) {
            POM[i] = key[i % key.length];
            //počáteční vynulování pole L, aby fungoval následující algoritmus
            L[i] = 0;
        }
        // vyplnění pomocného pole L
        for (int i = 255; i >= 0; i--) {
            L[i / u] = (rotateLeft4(L[i / u], 8) & 0xffffffff) + (POM[i] & 0xffffffff);
        }
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
        int t = (r + 1) << 1;
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
        }

    }

    //------------------------------------------------------------------------------------------
    //   ENCRYPTION
    // šifrovaný text vstupuje ve dvou integer slovech po 4 bxtech (tedy celkem 8 bytů na vstupu
    // a do stejného pole vystupuje šifrovaný text
    //------------------------------------------------------------------------------------------
    public void encrypt(int[] pole) {
        int A = pole[0];
        int B = pole[1];
        A = (A & 0xffffffff) + (S[0] & 0xffffffff);
        B = (B & 0xffffffff) + (S[1] & 0xffffffff);
        for (int i = 1; i <= r; i++) {
            int p1 = (A & 0xffffffff) ^ (B & 0xffffffff);
            int shift = B & 0xffffffff;
            int p2 = S[2 * i] & 0xffffffff;
            A = (rotateLeft4(p1, shift) & 0xffffffff) + (p2 & 0xffffffff);
            p1 = (B & 0xffffffff) ^ (A & 0xffffffff);
            shift = A & 0xffffffff;
            p2 = S[2 * i + 1] & 0xffffffff;
            B = (rotateLeft4(p1, shift) & 0xffffffff) + (p2 & 0xffffffff);
        }
        pole[0] = A;
        pole[1] = B;
    }

    //------------------------------------------------------------------------------------------
    //   DECRYPTION
    // dešifrovaný text vstupuje ve dvou integer slovech po 4 bxtech (tedy celkem 8 bytů na vstupu
    // a do stejného pole vystupuje dešifrovaný text
    //------------------------------------------------------------------------------------------
    public void decrypt(int[] pole) {
        int A = pole[0];
        int B = pole[1];
        for (int i = r; i >= 1; i--) {
            B = (rotateRight4((B & 0xffffffff) - (S[(i << 1) + 1] & 0xffffffff), (A & 0xffffffff)) & 0xffffffff) ^ (A & 0xffffffff);
            A = (rotateRight4((A & 0xffffffff) - (S[i << 1] & 0xffffffff), (B & 0xffffffff)) & 0xffffffff) ^ (B & 0xffffffff);
        }
        B = (B & 0xffffffff) - (S[1] & 0xffffffff);
        A = (A & 0xffffffff) - (S[0] & 0xffffffff);
        pole[0] = A;
        pole[1] = B;
    }

    private byte[] int4tobytearray(int[] pole) {
        byte[] bb = new byte[8];
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
        return bb;
    }

    private int[] bytearraytoint4(byte[] pole) {
        int[] vystup = new int[2];
        vystup[0] = 0;
        vystup[0] = vystup[0] + (pole[0] << 24) + (pole[1] << 16) + (pole[2] << 8) + pole[3];
        vystup[1] = 0;
        vystup[1] = vystup[1] + (pole[4] << 24) + (pole[5] << 16) + (pole[6] << 8) + pole[7];
        return vystup;
    }

    /**
     * ***************************************************************************************
     */
    public byte[] vectorXOR(byte[] plainTextBlock, byte[] iv) {
        byte[] vysledek = new byte[plainTextBlock.length];
        for (int i = 0; i < plainTextBlock.length; i++) {
            vysledek[i] = (byte) (plainTextBlock[i] ^ iv[i]);
        }
        return vysledek;
    }
    
    private void tiskBytovehoPole(byte[] pole, String nazev,  boolean tiskPovolen) {    
         if (tiskPovolen) {
            System.out.println("\n" + nazev + "\n");
            for (int i = 0; i < pole.length; i++) {
             System.out.format("0x%02X, ", pole[i]);
            }             
         }    
    }
    
    private void tiskInt4Pole(int[] pole, String nazev, boolean tiskPovolen) {        
        byte[] int4tobytearray = int4tobytearray(pole);
        tiskBytovehoPole(int4tobytearray, "INT => " + nazev, tiskPovolen);
    }

    public byte[] sifrujPoleCBC(byte[] plainSourceTextField, byte[] iv, boolean tiskPovolen) {
        int index;
        byte[] pole = new byte[BLOCK_SIZE];
        byte[] pomocneIV = new byte[BLOCK_SIZE];
        byte[] encryptedTextField;
        encryptedTextField = new byte[plainSourceTextField.length];
        int pocetBloku = plainSourceTextField.length / BLOCK_SIZE;
        int posledniByty = plainSourceTextField.length - BLOCK_SIZE * pocetBloku;
        if (posledniByty > 0) {
            encryptedTextField = new byte[plainSourceTextField.length + BLOCK_SIZE];
        } else {
            encryptedTextField = new byte[plainSourceTextField.length];
        }
        for (int i = 0; i < BLOCK_SIZE; i++) {
            pomocneIV[i] = iv[i];
        }
        for (int i = 0; i < pocetBloku; i++) {
            for (int j = 0; j < BLOCK_SIZE; j++) {
                pole[j] = plainSourceTextField[i * BLOCK_SIZE + j];
            }
            System.out.println("\n========================================");
            System.out.println(i + ". šifrovaný blok");
            System.out.println("========================================");
            tiskBytovehoPole(pole, "šifrování pole", tiskPovolen);
            byte[] vectorXOR = vectorXOR(pole, pomocneIV);
            tiskBytovehoPole(pomocneIV, "pomocneIV", tiskPovolen);
            tiskBytovehoPole(vectorXOR, "vectorXOR", tiskPovolen);
            //byte[] encoding = FirstAESClean.encoding(vectorXOR, tiskPovolen);
            byte[] encoding = new byte[BLOCK_SIZE];
            //---------------------------------------------------------
            int[] byte4 = bytearraytoint4(vectorXOR);
            encrypt(byte4);           
            encoding = int4tobytearray(byte4);
            tiskBytovehoPole(encoding, "encoding", tiskPovolen);
            //---------------------------------------------------------
            //encryptBlock(vectorXOR, encoding, 0);
            for (int j = 0; j < BLOCK_SIZE; j++) {
                pomocneIV[j] = encoding[j];
                encryptedTextField[i * BLOCK_SIZE + j] = encoding[j];
            }
        }
        if (posledniByty > 0) {
            for (int j = 0; j < posledniByty; j++) {
                pole[j] = plainSourceTextField[pocetBloku * BLOCK_SIZE + j];
            }
            //výplň šifrovaného bloku
            for (int j = posledniByty; j < BLOCK_SIZE; j++) {
                pole[j] = 0x00;
            }
            System.out.println("\n========================================");
            System.out.println("poslední doplněný šifrovaný blok");
            System.out.println("========================================");
            tiskBytovehoPole(pole, "zdrojové pole", tiskPovolen);
            byte[] vectorXOR = vectorXOR(pole, pomocneIV);
            tiskBytovehoPole(pomocneIV, "pomocneIV", tiskPovolen);
            tiskBytovehoPole(vectorXOR, "vectorXOR", tiskPovolen);
            byte[] encoding = new byte[BLOCK_SIZE];
            //encryptBlock(vectorXOR, encoding, 0);
            //---------------------------------------------------------
            int[] byte4 = bytearraytoint4(vectorXOR);
            encrypt(byte4);
            tiskInt4Pole(byte4, "byte4", tiskPovolen);
            encoding = int4tobytearray(byte4);
            tiskBytovehoPole(encoding, "encoding", tiskPovolen);
            //---------------------------------------------------------
            for (int j = 0; j < BLOCK_SIZE; j++) {
                pomocneIV[j] = encoding[j];
                encryptedTextField[pocetBloku * BLOCK_SIZE + j] = encoding[j];
            }
        }

        return encryptedTextField;
    }

    public byte[] desifrujPoleCBC(byte[] encryptedField, byte[] iv, boolean tiskPovolen) {
        byte[] pole = new byte[BLOCK_SIZE];
        byte[] pomocneIV = new byte[BLOCK_SIZE];
        byte[] decryptedTextField = new byte[encryptedField.length];
        int pocetBloku = encryptedField.length / BLOCK_SIZE;
        for (int i = 0; i < BLOCK_SIZE; i++) {
            pomocneIV[i] = iv[i];
        }
        for (int i = 0; i < pocetBloku; i++) {
            for (int j = 0; j < BLOCK_SIZE; j++) {
                pole[j] = encryptedField[i * BLOCK_SIZE + j];
            }
            System.out.println("\n========================================");
            System.out.println(i + ". dešifrovaný blok");
            System.out.println("========================================");
            tiskBytovehoPole(pole, "dešifrované pole", tiskPovolen);

            byte[] decoding = new byte[BLOCK_SIZE];
            //decryptBlock(pole, decoding, 0);
            //---------------------------------------------------------
            int[] byte4 = bytearraytoint4(pole);
            decrypt(byte4);
            decoding = int4tobytearray(byte4);
            tiskBytovehoPole(decoding, "decoding", tiskPovolen);
            //---------------------------------------------------------
            byte[] vectorXOR = vectorXOR(decoding, pomocneIV);
            tiskBytovehoPole(pomocneIV, "pomocneIV", tiskPovolen);
            tiskBytovehoPole(vectorXOR, "vectorXOR, dešifrované pole", tiskPovolen);
            for (int j = 0; j < BLOCK_SIZE; j++) {
                pomocneIV[j] = pole[j];
                decryptedTextField[i * BLOCK_SIZE + j] = vectorXOR[j];
            }

        }
        return decryptedTextField;
    }

    /**
     * ***************************************************************************************
     */
    public static void main(String[] arg) throws UnsupportedEncodingException {
        RC5 rc = new RC5();
        
        String klic = "MartinZeman1181";
        rc.init(klic.getBytes("UTF-8"), 1000);
        int[] zdroj = new int[2];
        zdroj[0] = 0x61626364;
        zdroj[1] = 0x71727374;
        System.out.println(new String(rc.int4tobytearray(zdroj), "UTF-8"));
        rc.encrypt(zdroj);
        rc.init(klic.getBytes("UTF-8"), 1000);
        rc.decrypt(zdroj);
        byte[] bb = new byte[8];
        //--
        bb[0] = (byte) ((zdroj[0] >> 24) & 0xff);
        bb[1] = (byte) ((zdroj[0] >> 16) & 0xff);
        bb[2] = (byte) ((zdroj[0] >> 8) & 0xff);
        bb[3] = (byte) (zdroj[0] & 0xff);
        //--
        bb[4] = (byte) ((zdroj[1] >> 24) & 0xff);
        bb[5] = (byte) ((zdroj[1] >> 16) & 0xff);
        bb[6] = (byte) ((zdroj[1] >> 8) & 0xff);
        bb[7] = (byte) (zdroj[1] & 0xff);
        System.out.println(new String(bb, "UTF-8"));
        System.out.println("KKKKKKKKKKKKKKKK");
        
        /*
        boolean tiskPovolen = true;
         
        String dlouhyZdrojovyText = "Byl pozdní večer – první máj –\n"
                + "večerní máj – byl lásky čas.\n"
                + "Hrdliččin zval ku lásce hlas,\n"
                + "kde borový zaváněl háj.\n"
                + "O lásce šeptal tichý mech;\n"
                + "květoucí strom lhal lásky žel,\n"
                + "svou lásku slavík růži pěl,\n"
                + "růžinu jevil vonný vzdech.\n"
                + "Jezero hladké v křovích stinných\n"
                + "zvučelo temně tajný bol,\n"
                + "břeh je objímal kol a kol;\n"
                + "a slunce jasná světů jiných\n"
                + "bloudila blankytnými pásky,\n"
                + "planoucí tam co slzy lásky.\n"
                + "\n"
                + "I světy jich v oblohu skvoucí\n"
                + "co ve chrám věčné lásky vzešly;\n"
                + "až se – milostí k sobě vroucí\n"
                + "změnivše se v jiskry hasnoucí –\n"
                + "bloudící co milenci sešly.\n"
                + "Ouplné lůny krásná tvář –\n"
                + "tak bledě jasná, jasně bledá,\n"
                + "jak milence milenka hledá –\n"
                + "ve růžovou vzplanula zář;\n"
                + "na vodách obrazy své zřela\n"
                + "a sama k sobě láskou mřela.\n"
                + "Dál blyštil bledý dvorů stín,\n"
                + "jenž k sobě šly vzdy blíž a blíž,\n"
                + "jak v objetí by níž a níž\n"
                + "se vinuly v soumraku klín,\n"
                + "až posléze šerem v jedno splynou.\n"
                + "S nimi se stromy k stromům vinou. –\n"
                + "Nejzáze stíní šero hor,\n"
                + "tam bříza k boru, k bříze bor\n"
                + "se kloní. Vlna za vlnou\n"
                + "potokem spěchá. Vře plnou –\n"
                + "v čas lásky – láskou každý.";

        String txt = "";
        for (int i = 0; i < 1; i++) {
            txt = txt + dlouhyZdrojovyText;
        }
        */
        /*
        String ttt = "12345678";
        byte[] byty = ttt.getBytes("UTF-8");
        String string = new String(rc.int4tobytearray(rc.bytearraytoint4(byty)), "UTF-8");
        System.out.println(string);
        */
        /*
        String txt = "abcdefgh12345678";
        byte[] zdrojovePoleTextu = txt.getBytes("UTF-8");
        String klic = "MartinZeman12345";
        byte[] bytovePoleKlice = klic.getBytes("UTF-8");
        rc.init(klic.getBytes("UTF-8"), 5);
        String ivv = "wwwwwwww";
        byte[] ivvPole = ivv.getBytes("UTF-8");        
        byte[] sifrujPoleCBC = rc.sifrujPoleCBC(zdrojovePoleTextu, ivvPole, tiskPovolen);        
        byte[] desifrujPoleCBC = rc.desifrujPoleCBC(sifrujPoleCBC, ivvPole, tiskPovolen);
        String vyslString = new String(desifrujPoleCBC, "UTF-8");
        System.out.println(vyslString);
        */

    }

}
