/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author martin
 */
public class SimpleProviderTest {

    public static void main(String[] arg) {
        Security.addProvider(new BouncyCastleProvider());
        String providerName = "BC";
        if (Security.getProvider(providerName) == null) {
            System.out.println(providerName + " provider not installed");
        } else {
            System.out.println(providerName + " provider is installed");
        }
        
        
    }
}
