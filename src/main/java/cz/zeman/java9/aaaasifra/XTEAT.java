/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;
import static cz.zeman.java9.aaaasifra.FirstAESClean.desifrujPoleCBC;
import static cz.zeman.java9.aaaasifra.FirstAESClean.sifrujPoleCBC;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 *
 * @author martin
 * Tento algoritmus skutečně chodí
 */

public class XTEAT {

    private static final int BLOCK_SIZE = 8;
    private static final int ALIGN = 16;
    private static final int DELTA = 0x9E3779B9;
    private int k0, k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12, k13, k14, k15;
    private int k16, k17, k18, k19, k20, k21, k22, k23, k24, k25, k26, k27, k28, k29, k30, k31;

    public static void main(String... args) throws IOException {
        XTEAT xtea = new XTEAT();
        
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
        for (int i = 0; i < 10000; i++) {
         txt = txt + dlouhyZdrojovyText;
        }

        byte[] zdrojovePoleTextu = txt.getBytes("UTF-8");
        String klic = "MartinZeman12345";
        byte[] bytovePoleKlice = klic.getBytes("UTF-8");
        xtea.setKey(bytovePoleKlice);     
        String ivv = "12345678";
        byte[] ivvPole = ivv.getBytes("UTF-8");
        
        byte[] sifrujPoleCBC = xtea.sifrujPoleCBC(zdrojovePoleTextu, ivvPole, tiskPovolen);
        //--
        byte[] desifrujPoleCBC = xtea.desifrujPoleCBC(sifrujPoleCBC, ivvPole, tiskPovolen);
        String vyslString = new String(desifrujPoleCBC,"UTF-8");
        System.out.println(vyslString);
       
    }

    public void setKey(byte[] b) {
        int[] key = new int[4];
        for (int i = 0; i < 16;) {
            key[i / 4] = (b[i++] << 24) + ((b[i++] & 255) << 16) + ((b[i++] & 255) << 8) + (b[i++] & 255);
        }
        int[] r = new int[32];
        for (int i = 0, sum = 0; i < 32;) {
            r[i++] = sum + key[sum & 3];
            sum += DELTA;
            r[i++] = sum + key[(sum >>> 11) & 3];
        }
        k0 = r[0];
        k1 = r[1];
        k2 = r[2];
        k3 = r[3];
        k4 = r[4];
        k5 = r[5];
        k6 = r[6];
        k7 = r[7];
        k8 = r[8];
        k9 = r[9];
        k10 = r[10];
        k11 = r[11];
        k12 = r[12];
        k13 = r[13];
        k14 = r[14];
        k15 = r[15];
        k16 = r[16];
        k17 = r[17];
        k18 = r[18];
        k19 = r[19];
        k20 = r[20];
        k21 = r[21];
        k22 = r[22];
        k23 = r[23];
        k24 = r[24];
        k25 = r[25];
        k26 = r[26];
        k27 = r[27];
        k28 = r[28];
        k29 = r[29];
        k30 = r[30];
        k31 = r[31];
    }

    public void encrypt(byte[] bytes, int off, int len) {
        assert len % ALIGN != 0 : "unaligned len " + len;

        for (int i = off; i < off + len; i += 8) {
            encryptBlock(bytes, bytes, i);
        }
    }

    public void decrypt(byte[] bytes, int off, int len) {
        assert len % ALIGN != 0 : "unaligned len " + len;
        for (int i = off; i < off + len; i += 8) {
            decryptBlock(bytes, bytes, i);
        }
    }

    private void encryptBlock(byte[] in, byte[] out, int off) {
        int y = (in[off] << 24) | ((in[off + 1] & 255) << 16) | ((in[off + 2] & 255) << 8) | (in[off + 3] & 255);
        int z = (in[off + 4] << 24) | ((in[off + 5] & 255) << 16) | ((in[off + 6] & 255) << 8) | (in[off + 7] & 255);
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k0;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k1;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k2;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k3;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k4;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k5;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k6;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k7;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k8;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k9;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k10;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k11;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k12;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k13;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k14;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k15;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k16;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k17;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k18;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k19;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k20;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k21;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k22;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k23;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k24;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k25;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k26;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k27;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k28;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k29;
        y += (((z << 4) ^ (z >>> 5)) + z) ^ k30;
        z += (((y >>> 5) ^ (y << 4)) + y) ^ k31;
        out[off] = (byte) (y >> 24);
        out[off + 1] = (byte) (y >> 16);
        out[off + 2] = (byte) (y >> 8);
        out[off + 3] = (byte) y;
        out[off + 4] = (byte) (z >> 24);
        out[off + 5] = (byte) (z >> 16);
        out[off + 6] = (byte) (z >> 8);
        out[off + 7] = (byte) z;
    }

    private void decryptBlock(byte[] in, byte[] out, int off) {
        int y = (in[off] << 24) | ((in[off + 1] & 255) << 16) | ((in[off + 2] & 255) << 8) | (in[off + 3] & 255);
        int z = (in[off + 4] << 24) | ((in[off + 5] & 255) << 16) | ((in[off + 6] & 255) << 8) | (in[off + 7] & 255);
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k31;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k30;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k29;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k28;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k27;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k26;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k25;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k24;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k23;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k22;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k21;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k20;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k19;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k18;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k17;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k16;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k15;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k14;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k13;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k12;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k11;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k10;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k9;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k8;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k7;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k6;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k5;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k4;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k3;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k2;
        z -= (((y >>> 5) ^ (y << 4)) + y) ^ k1;
        y -= (((z << 4) ^ (z >>> 5)) + z) ^ k0;
        out[off] = (byte) (y >> 24);
        out[off + 1] = (byte) (y >> 16);
        out[off + 2] = (byte) (y >> 8);
        out[off + 3] = (byte) y;
        out[off + 4] = (byte) (z >> 24);
        out[off + 5] = (byte) (z >> 16);
        out[off + 6] = (byte) (z >> 8);
        out[off + 7] = (byte) z;
    }

    public int getKeyLength() {
        return 16;
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
            byte[] vectorXOR = vectorXOR(pole, pomocneIV);
            //byte[] encoding = FirstAESClean.encoding(vectorXOR, tiskPovolen);
            byte[] encoding = new byte[BLOCK_SIZE];
            encryptBlock(vectorXOR, encoding, 0);
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
            byte[] vectorXOR = vectorXOR(pole, pomocneIV);          
            byte[] encoding = new byte[BLOCK_SIZE];
            encryptBlock(vectorXOR, encoding, 0);

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
            
            byte[] decoding = new byte[BLOCK_SIZE];
            decryptBlock(pole, decoding, 0);

            byte[] vectorXOR = vectorXOR(decoding, pomocneIV);
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
}
