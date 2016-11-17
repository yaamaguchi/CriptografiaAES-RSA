
package TrabalhoSeguranca.util;

import TrabalhoSeguranca.RSA.CriptografiaRSA;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class TransformClass {
    public static List<byte[]> transforStringToByte64(String listaTexto, int valueKey){
        List<byte[]>texto = new ArrayList<>();
        byte[] abc = new byte[512];
        try {
            abc = listaTexto.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int x = 0; x < abc.length;){
            texto.add(Arrays.copyOfRange(abc, x, x+((valueKey/8)-11)));
            x+=((valueKey/8)-11);
        }
        return texto;
    }
    
    public static String transformByte64ToString(List<byte[]> lista){
        String textoCripto = "";
        for(byte[]a : lista){
            try {
                textoCripto += new String(a ,"UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CriptografiaRSA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return textoCripto;
    }
}
