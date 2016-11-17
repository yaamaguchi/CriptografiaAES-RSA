
package TrabalhoSeguranca.RSA;

import TrabalhoSeguranca.util.ReadFile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainCriptografiaRSA {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        long timeEnd = 0 ;
        CriptografiaRSA cripRSA = new CriptografiaRSA(1024);
        String textoPuro = ReadFile.readFile("arquivo.txt");
        List<byte[]> textoEncrypto ;
        
        try {
            textoEncrypto = cripRSA.encrypt(textoPuro);
            System.out.println(cripRSA.decrypt(textoEncrypto) + "\n");
        } catch (Exception ex) {
            Logger.getLogger(MainCriptografiaRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        timeEnd = System.currentTimeMillis();
        
        System.out.println("Tempo total de execução: "+ (timeEnd-timeStart));
    }
}
