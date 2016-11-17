package TrabalhoSeguranca.RSA;
import TrabalhoSeguranca.util.TransformClass;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CriptografiaRSA {
    private KeyPair key = null;
    private long timeStart = 0;
    private long timeEnd = 0;
    private Cipher cipher;
    private KeyPairGenerator keyGen = null;
    private int valueKey;
    
    public CriptografiaRSA(int valueBits) {
        timeStart = System.currentTimeMillis();
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(valueBits);
            key = keyGen.generateKeyPair();
            cipher = Cipher.getInstance("RSA");
            valueKey = valueBits;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CriptografiaRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CriptografiaRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timeEnd = System.currentTimeMillis();
        System.out.println("Tempo de geração da chave: "+ (timeEnd-timeStart));
    }
    
    public List<byte[]> encrypt(String listaTexto) {
        timeStart = System.currentTimeMillis();
        
        //Transforma o texto recebido em "blocos" 
        //  (os blocos já são modelados de acordo com a capacidade RSA)
        List<byte[]> texto = TransformClass.transforStringToByte64(listaTexto, valueKey);
        List<byte[]> cipherText = new ArrayList<>();
        
        try {
            // Criptografa o texto puro usando a chave Púlica
            cipher.init(Cipher.ENCRYPT_MODE, this.key.getPublic());
            for(byte[]a : texto)
                cipherText.add(cipher.doFinal(a));
            
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CriptografiaRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CriptografiaRSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(CriptografiaRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timeEnd = System.currentTimeMillis();
        System.out.println("Tempo de Encripto: "+ (timeEnd-timeStart));
        return cipherText;
    }
    
    public String decrypt(List<byte[]> texto) throws UnsupportedEncodingException{
        timeStart = System.currentTimeMillis();
        List<byte[]> textoDecripto = new ArrayList<>();
        
        try {
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, this.key.getPrivate());
            for(byte[] a : texto)
                textoDecripto.add(cipher.doFinal(a));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        timeEnd = System.currentTimeMillis();
        System.out.println("Tempo de Decripto: "+ (timeEnd-timeStart));
        return TransformClass.transformByte64ToString(textoDecripto);
    }
}
    
    
    
    
    
    
