/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zeman.java9.aaaasifra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author martin
 */
public class Hokus {

    public static void main(String[] arg) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object source = ae.getSource();
               
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        };
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Object value = ov.getValue();
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }            
        };
        
        new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent he) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        };
        HierarchyListener hierarchyListener = new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent he) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        AdjustmentListener adjustmentListener = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        ListDataListener listDataListener = new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent lde) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void intervalRemoved(ListDataEvent lde) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void contentsChanged(ListDataEvent lde) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        //--------------------------------------------------------------------------------------
        //--    Část první:
        //--    veřejný klíč (p,  q, g, y)
        //--    soukromý klíč (p, q, g, x)
        //--------------------------------------------------------------------------------------
        // Výpočet q
        BigInteger zaklad = BigInteger.ONE.shiftLeft(159);
        //String toString = zaklad.toString(10);
        //System.out.println(toString);
        //BigInteger x = new BigInteger("0");
        BigInteger q = new BigInteger("0");

        Date dd = new Date();    //dd.getTime()

        BigInteger pom2 = zaklad
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(112))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(56))
                .add(BigInteger.valueOf(dd.getTime()));
        while (true) {
            q = pom2.multiply(BigInteger.ONE.shiftLeft(1)).add(BigInteger.ONE);
            //String toString2 = q.toString(10);
            //System.out.println(toString2);
            if (q.isProbablePrime(32000)) {
                break;
            }
            pom2 = pom2.add(BigInteger.ONE);
        }
        System.out.println("Q = " + q.toString(16));
        //--   
        // Výpočet p
        BigInteger p = BigInteger.ONE.shiftLeft(1023)
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(1008))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(952))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(896))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(840))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(784))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(728))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(672))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(616))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(560))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(504))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(448))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(392))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(336))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(280))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(224))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(168))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(112))
                .add(BigInteger.valueOf(dd.getTime()).shiftLeft(56))
                .add(BigInteger.valueOf(dd.getTime()));        
        //p = q;
        int pq = 1;
        while (true) {
            if (p.add(BigInteger.ONE).isProbablePrime(32000)) {
                //String toString3 = p.toString(10);
                //System.out.println(toString3);
                break;
            }
            p = p.add(BigInteger.ONE);
            pq++;
        }
        p = p.add(BigInteger.ONE); // (p - 1) / q
        System.out.println("P = " + p.toString(16) + ", (P-1)/Q = " + String.valueOf(pq));

        /* výpočet g */
        BigInteger g ;
        int hx = 5562;  // h zvoleno libovolně    
        while (true) {
            BigInteger hh = new BigInteger(String.valueOf(hx));
            g = hh.pow(pq);
            //toto je pouze kontrola, která by měla vždy vyjít g.modPow(q, p) = 1 !!!!
            BigInteger pom = g.modPow(q, p);
            if (!pom.equals(BigInteger.ONE)) {
                break;
            }
            hx++;
        }
        System.out.println("G = " + g.toString(16));
        // Výpočet x a y 
        BigInteger x = q.shiftRight(1);
        BigInteger y = g.modPow(x, p);
        System.out.println("X = " + x.toString(16) + ", Y = " + y.toString(16));
        //-----------------------------------------------------------------------------------
        //--
        //-- Vytvoření digitálního podpisu (r, s)
        //-----------------------------------------------------------------------------------        
        SHA2NBLOCK nb = new SHA2NBLOCK();
        String hash = "";
        BigInteger s, i, r;
        try {
            hash = nb.vytvorHash("abc");
            //System.out.println("hash = " + hash);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("CHYBA!!!");
        }
        BigInteger h = new BigInteger(hash, 16);
        System.out.println("h = " + h.toString(16) );
        // vygenerování r
        BigInteger k = q.shiftRight(1);
        while (true) {
            r = new BigInteger("0");
            while (r.equals(BigInteger.ZERO)) {
                k.add(BigInteger.ONE);
                r = g.modPow(k, p).mod(q);
            }
            System.out.println("R = " + r.toString(16));
            //Výpočet i            
            i = k.modInverse(q);
            System.out.println("I = " + r.toString(16));
            //System.out.println("KKKK");
            /* 
        BigInteger hhh = (k.multiply(i)).mod(q);
        System.out.println("hhh = " + r.toString(10));
             */

 /*
        BigInteger bi1 = new BigInteger("7");
        BigInteger bi2 = new BigInteger("20");
        BigInteger bi3 = bi1.modInverse(bi2);
        System.out.println("BI3 = " + bi3.toString(16));
             */
            s = i.multiply(h.add(r.multiply(x)));
            System.out.println("S = " + s.toString(16));
            if (!s.equals(BigInteger.ZERO)) {
                break;
            }
        }
        //-----------------------------------------------------------------------
        //--
        //-- Verifikace podpisu
        //-----------------------------------------------------------------------
        // stejný hash
        BigInteger w = s.modInverse(q);
        System.out.println("W = " + w.toString(16));
        BigInteger u1 = (h.multiply(w)).mod(q);
        System.out.println("U1 = " + u1.toString(16));
        BigInteger u2 = (r.multiply(w)).mod(q);
        System.out.println("U2 = " + u2.toString(16));

        BigInteger powa = power(g, u1);

        BigInteger v = ((g.modPow(u1, BigInteger.ONE.shiftRight(4000)).multiply(y.modPow(u2, BigInteger.ONE.shiftRight(4000)))).mod(p)).mod(q);

        if (v.equals(r)) {
            System.out.println("KKKKKKKKKKKKKKKKKKKKKK");
        }

    }

    private static BigInteger power(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) {
                result = result.multiply(base);
            }
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

}
