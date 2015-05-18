/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author tabasj
 */
public abstract class Encryption {

    Cipher encrypt = null;
    Cipher decrypt = null;
    SecretKeySpec secretKeySpec = null;
     SecretKey secretKey = null;

    public abstract void encryptFile(File f, File encrypted);

    public abstract void decryptFile(File encrypted, File decrypted);

    public void writeBytes(InputStream input, OutputStream output) throws IOException {

        byte[] writeBuffer = new byte[512];
        int readBytes = 0;
        while ((readBytes = input.read(writeBuffer)) >= 0) {
            output.write(writeBuffer, 0, readBytes);
        }
        output.close();
        input.close();
    }

}
