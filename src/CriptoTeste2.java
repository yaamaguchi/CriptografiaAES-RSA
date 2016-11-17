
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class CriptoTeste2 {
    static String IV = "AAAAAAAAAAAAAAAA";
    static String textopuro = "";          
    static String chaveencriptacao = "0123456789ABCDEF";
    
    public static void main(String [] args) {
        long tempInicialEncrip = 0;
        long tempInicialDecrip = 0;
        
        long tempFinalEncrip = 0;
        long tempFinalDecrip = 0;
        
        try {
            System.out.println("Texto Puro: " + textopuro);
            
            tempInicialEncrip = System.currentTimeMillis();
            byte[] textoencriptado = encrypt(textopuro, chaveencriptacao);
            tempFinalEncrip = System.currentTimeMillis();
            
            tempInicialDecrip = System.currentTimeMillis();
            decrypt(textoencriptado, chaveencriptacao);
            tempFinalDecrip = System.currentTimeMillis();
            
            System.out.println("Tempo total de Encripto: "+ (tempFinalEncrip - tempInicialEncrip));
            System.out.println("Tempo total de Decripto: "+ (tempFinalDecrip - tempInicialDecrip));
            
        } catch (Exception e) {
               e.printStackTrace();
        }
    }

    public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(textopuro.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(decripta.doFinal(textoencriptado),"UTF-8");
    }
         
}
