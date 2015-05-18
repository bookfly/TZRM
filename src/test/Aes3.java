/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author tabasj
 */
public class Aes3 {

    public static void main(String[] args) {
        try {
            getKeySpec();
            sacuvaj("C:/Users/tabasj/Desktop/input.txt", enkript(iscitaj("C:/Users/tabasj/Desktop/input.txt")));
            Scanner keyboard = new Scanner(System.in);
            System.out.println("enter an integer");
            int myint = keyboard.nextInt();
            sacuvaj("C:/Users/tabasj/Desktop/input.txt", dekript(iscitaj("C:/Users/tabasj/Desktop/input.txt")));
        } catch (IOException ex) {
            Logger.getLogger(Aes3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Aes3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Aes3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SecretKeySpec getKeySpec() throws IOException, NoSuchAlgorithmException {
        byte[] bytes = new byte[16];
        File f = new File("Aes kljuc");
        SecretKey key = null;
        SecretKeySpec spec = null;
        if (f.exists()) {
            new FileInputStream(f).read(bytes);
        } else {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(192);
            key = kgen.generateKey();
            bytes = key.getEncoded();
            new FileOutputStream(f).write(bytes);
        }
        spec = new SecretKeySpec(bytes, "AES");
        return spec;
    }

    public static String enkript(String text) throws Exception {
        SecretKeySpec spec = getKeySpec();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, spec);
        BASE64Encoder enc = new BASE64Encoder();
        return enc.encode(cipher.doFinal(text.getBytes()));
    }

    public static String dekript(String text) throws Exception {
        SecretKeySpec spec = getKeySpec();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, spec);
        BASE64Decoder dec = new BASE64Decoder();
        return new String(cipher.doFinal(dec.decodeBuffer(text)));
    }

    public static String iscitaj(String fajl) {
        StringBuffer reci = new StringBuffer();
        try {

            DataInputStream dis = new DataInputStream(new FileInputStream(fajl));

            while (dis.available() != 0) {
                reci.append((char) dis.readByte());
            }
            dis.close();
        } catch (FileNotFoundException fnf) {
        } catch (IOException io) {
        }

        return reci.toString();
    }

    public static void sacuvaj(String fajl, String tekst) {
        FileOutputStream fos = null;

        try {
            FileOutputStream out = new FileOutputStream(fajl);
            DataOutputStream dos = new DataOutputStream(out);

            for (int i = 0; i < tekst.length(); i++) {
                dos.writeByte((byte) tekst.charAt(i));
            }
            dos.close();

        } catch (IOException e) {
            System.out.println("Greska");
        }

    }
}
