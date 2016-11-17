package TrabalhoSeguranca.classesTestes;



import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Matheus
 */
public class CriptografiaDES {
    public String encriptografia(String texto){
        KeyGenerator keygenerator;
        byte[] textoEncriptado = new byte[256];
        try {
            keygenerator = KeyGenerator.getInstance("DES");
            SecretKey chaveDES = keygenerator.generateKey();
            Cipher cifraDES;
            // Cria a cifra 
            cifraDES = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // Inicializa a cifra para o processo de encriptação
            cifraDES.init(Cipher.ENCRYPT_MODE, chaveDES);
            // Texto puro
            byte[] textoPuro = "Exemplo de texto puro".getBytes();         
            textoEncriptado = cifraDES.doFinal(textoPuro);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CriptografiaDES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CriptografiaDES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CriptografiaDES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CriptografiaDES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CriptografiaDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(textoEncriptado);
    }
}
