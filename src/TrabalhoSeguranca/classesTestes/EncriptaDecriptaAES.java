package TrabalhoSeguranca.classesTestes;



import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncriptaDecriptaAES{
    public static void main(String[] argv) {
       CriptografiaAES cript = new CriptografiaAES();
       
       System.out.println(cript.encripta("eusouloko", "12345678901234567890123456789012"));
    }
}