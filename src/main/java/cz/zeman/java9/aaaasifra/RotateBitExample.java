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
public class RotateBitExample {

   
    public static byte rotateRight(byte bits, int shift) {
        return (byte) (((bits & 0xff) >>> shift) | ((bits & 0xff) << (8 - shift)));
    }

    public static byte rotateLeft(byte bits, int shift) {
        return (byte) (((bits & 0xff) << shift) | ((bits & 0xff) >>> (8 - shift)));
    }

    public static void main(String[] args) {        
        byte a = (byte) 0x8f;
        
        byte[] vysledek = new byte[8];
        
        for (int i = 0; i < 8; i++) {
            vysledek[i] = a;
            //System.out.printf("rotateRight = 0x%02X\n", a);            
            a = rotateRight(a, 1);
        }
    }
    
}
