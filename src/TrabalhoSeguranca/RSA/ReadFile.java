
package TrabalhoSeguranca.RSA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ReadFile {
    static long tempoInit = 0;
    static long tempoEnd = 0;
    
    public static String readFile(String url){
        tempoInit = System.currentTimeMillis();
        FileInputStream stream;
        String listaTexto = "";
        
        try {
            stream = new FileInputStream("arquivo.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            
            String linha = br.readLine();
            
            while(linha != null){
                listaTexto+=linha;
                linha = br.readLine();
            }
            
            tempoEnd = System.currentTimeMillis();
            System.out.println("Tempo Leitura Arquivo : "+ (tempoEnd - tempoInit));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Total Caracteres: "+ listaTexto.length());
        return listaTexto;
    }
}
