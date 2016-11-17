
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.*;

public class CriptografiaAES {
    private byte[] mensagem = new byte[256];
    private SecretKey key;
    
    public String encripta(String texto, String chave){
        byte[] encrypted = new byte[256];
        
        /*"32 caracteres = chave com 256 bits"+
                "\n24 caracteres = chave com 192 bits"+
                "16 caracteres = chave com 128 bits"+
                "\n Infomre uma Chave: "*/
        try {
            key = new SecretKeySpec(chave.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            mensagem = texto.getBytes();
            
            encrypted = cipher.doFinal(mensagem);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CriptografiaAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CriptografiaAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CriptografiaAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CriptografiaAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CriptografiaAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(encrypted);
    }
    
}
