package TrabalhoSeguranca.AES;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class MainCriptografiaAES {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        
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