package TrabalhoSeguranca.AES;

import TrabalhoSeguranca.util.ReadFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class MainCriptografiaAES {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        
        // new CriptografiaAES ( 128 ) CRIPTOGRAFIA 128 bits
        // new CriptografiaAES ( 256 ) CRIPTOGRAFIA 256 bits 
        
        CriptografiaAES cripto = new CriptografiaAES(128);
        String textoPuro = ReadFile.readFile("arquivo.txt");
        byte[] encripto = new byte[256];
        
        try {
            encripto = cripto.encrypt(textoPuro);
            System.out.println(cripto.decrypt(encripto) + "\n");
        } catch (Exception ex) {
            Logger.getLogger(MainCriptografiaAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long timeEnd = System.currentTimeMillis();
        
        System.out.println("Tempo total de execução: "+ (timeEnd-timeStart));
    }
}
