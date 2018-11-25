/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author martin
 */
public class PrecendenceTest {

    public static void main(String[] arg) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/NoPadding", "SunJCE");
        System.out.println(cipher.getProvider());
        cipher = Cipher.getInstance("Blowfish/ECB/NoPadding", "BC");
        System.out.println(cipher.getProvider());
        cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
        System.out.println(cipher.getProvider());
    }

}
