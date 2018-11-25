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
public class RC4 {

    private byte[] S = new byte[256];
    private byte[] T = new byte[256];

    public static void main(String[] arg) throws UnsupportedEncodingException {
        String heslo = "MartinZeman";
        //String plainText = "hhhhhhhhhhhhhhhhhhhhhhhhhhhh";
        //byte[] plainArray = plainText.getBytes("UTF-8");
        
        
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
        
        
        
        RC4 rc = new RC4(heslo.getBytes("UTF-8"));
        rc.encrypt(zdrojovePoleTextu);
        rc.init(heslo.getBytes("UTF-8"));
        rc.encrypt(zdrojovePoleTextu);
        //--
        System.out.println(new String(zdrojovePoleTextu, "UTF-8"));

    }

    public RC4(byte[] key) {
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
            T[i] = key[i % key.length];
        }
        zamichat();
    }

    public RC4(byte[] key, byte[] state) {
        for (int i = 0; i < 256; i++) {
            S[i] = state[i % state.length];
            T[i] = key[i % key.length];
        }
        zamichat();
    }
    
    public void init(byte[] key) {
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
            T[i] = key[i % key.length];
        }
        zamichat();
    }
    
    public void init(byte[] key, byte[] state) {
        for (int i = 0; i < 256; i++) {
            S[i] = state[i % state.length];
            T[i] = key[i % key.length];
        }
        zamichat();
    }

    private void zamichat() {
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + (S[i] & 0xff) + (T[i] & 0xff)) % 256;
            byte W = S[i];
            S[i] = S[j];
            S[j] = W;
        }
    }

    public void encrypt(byte[] P) {
        int i = 0;
        int j = 0;
        byte K;
        for (int ii = 0; ii < P.length; ii++) {
            i = ii;
            i = (i + 1) % 256;
            j = (j + (S[i] & 0xff)) % 256;
            byte W = S[i];
            S[i] = S[j];
            S[j] = W;
            K = S[((S[i] & 0xff) + (S[j] & 0xff)) % 256];
            P[ii] = (byte) (P[ii] ^ K);

        }

    }

}
