/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author tabasj
 */
public class AesEncryption extends Encryption {

    private byte[] key;

    private String decryptedString;
    private String encryptedString;

    public AesEncryption(String function) {
        setKey(function);
        try {
            encrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
            decrypt = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
            Logger.getLogger(AesEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TODO: Check settings!!!
    private void setKey(String function) {

        MessageDigest sha = null;
        try {
            //File f = new File("Aes_key");
            key = new byte[16];
         //   KeyGenerator kgen = KeyGenerator.getInstance("AES");

            //TODO : SET 128/196/256 keysize
            //generise kljuc od 128
            //         kgen.init(128);
            //NE KORISTI SE!!!
            //      secretKey = kgen.generateKey();
            //TODO : Set md5/SHA / SHA-1/ SHA-256
            //   sha = MessageDigest.getInstance("SHA-256");
            sha = MessageDigest.getInstance(function);
            key = sha.digest(key);

            // use only first 128 bit
            key = Arrays.copyOf(key, 16);

            secretKeySpec = new SecretKeySpec(key, "AES");

            //  new FileOutputStream(f).write(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void encryptFile(File clearFile, File encrypted) {

        try {

            encrypt.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            encryptedString = Base64.encodeBase64String(encrypt.doFinal(buildString(clearFile).getBytes("UTF-8")));

            writeBytes(new ByteArrayInputStream(encryptedString.getBytes(StandardCharsets.UTF_8)), new FileOutputStream(encrypted));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(AesEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void decryptFile(File encrypted, File decrypted) {
        try {
            decrypt.init(Cipher.DECRYPT_MODE, secretKeySpec);

            decryptedString = new String(decrypt.doFinal(Base64.decodeBase64(buildString(encrypted))));

            writeBytes(new ByteArrayInputStream(decryptedString.getBytes(StandardCharsets.UTF_8)), new FileOutputStream(decrypted));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            Logger.getLogger(AesEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String buildString(File file) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
