package TrabalhoSeguranca.AES;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;


public class CriptografiaAES {
    private static long timeStart = 0;
    private static long timeEnd = 0;
    private KeyGenerator keygenerator = null;  
    private SecretKey chaveAES;
    private Cipher cifraAES;
    private IvParameterSpec ivspec = null;
    

    public CriptografiaAES(int valueBits) {
        timeStart = System.currentTimeMillis();
        try {  
            keygenerator = KeyGenerator.getInstance("AES");
            keygenerator.init(valueBits);  
            chaveAES = keygenerator.generateKey();  
            cifraAES = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
            ivspec = new IvParameterSpec (new byte[16]);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CriptografiaAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CriptografiaAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        timeEnd = System.currentTimeMillis();
        System.out.println("Tempo de geração da chave: "+ (timeEnd-timeStart));
    }
    
    
    public byte[] encrypt(String textopuro) throws Exception {
        timeStart = System.currentTimeMillis();
        
        cifraAES.init(Cipher.ENCRYPT_MODE, chaveAES, ivspec);
        byte[] encript = cifraAES.doFinal(textopuro.getBytes("UTF-8"));
        
        timeEnd = System.currentTimeMillis();
        System.out.println("Tempo Encrip: "+ (timeEnd - timeStart));
        
        return encript;
    }
    
    public String decrypt(byte[] textoencriptado) throws Exception{
        timeStart = System.currentTimeMillis();
        
        cifraAES.init(Cipher.DECRYPT_MODE, chaveAES, ivspec);
        String decript = new String(cifraAES.doFinal(textoencriptado),"UTF-8");;

        timeEnd = System.currentTimeMillis();
        System.out.println("Tempo Decrip: "+ (timeEnd - timeStart));
        System.out.println("");
        return decript;
    }
}
