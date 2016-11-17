/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabalhoSeguranca.AES;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus
 */
public class ReadFile {
    static long tempoInit = 0;
    static long tempoEnd = 0;
    
    public static String readFile(String url){
        tempoInit = System.currentTimeMillis();
        FileInputStream stream;
        String mensagem = "";
        try {
            stream = new FileInputStream("arquivo.txt");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);

            String linha = br.readLine();
            
            while(linha != null){
                mensagem += linha;
                linha = br.readLine();
            }
            
            tempoEnd = System.currentTimeMillis();
            System.out.println("Tempo Leitura Arquivo : "+ (tempoEnd - tempoInit));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Total Caracteres: "+ mensagem.length());
        return mensagem;
    }
}