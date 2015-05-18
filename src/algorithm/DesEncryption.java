/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author tabasj
 */
public class DesEncryption extends Encryption {

    private static final byte[] initialization_vector = {22, 33, 11, 44, 55, 99, 66, 77};

    private AlgorithmParameterSpec alogrithm_specs = null;

    public DesEncryption() {
        try {
            secretKey = KeyGenerator.getInstance("DES").generateKey();
            alogrithm_specs = new IvParameterSpec(initialization_vector);

            // set encryption mode ...
            encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // set decryption mode
            decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
        }
    }

    @Override
    public void encryptFile(File clearFile, File encrypted) {
        try {

            encrypt.init(Cipher.ENCRYPT_MODE, secretKey, alogrithm_specs);

            // encrypt file
            OutputStream fos = new FileOutputStream(encrypted);
            fos = new CipherOutputStream(fos, encrypt);
            writeBytes(new FileInputStream(clearFile), fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(DesEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void decryptFile(File encrypted, File decrypted) {
        try {

            decrypt.init(Cipher.DECRYPT_MODE, secretKey, alogrithm_specs);

            //    decrypt(new FileInputStream(encrypted), new FileOutputStream(decrypted));
            InputStream is = new FileInputStream(encrypted);
            is = new CipherInputStream(is, decrypt);
            writeBytes(is, new FileOutputStream(decrypted));
            is.close();

        } catch (InvalidKeyException | InvalidAlgorithmParameterException | IOException ex) {
            Logger.getLogger(DesEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
