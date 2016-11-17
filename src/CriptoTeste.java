import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;  
import javax.crypto.*;  
import javax.crypto.spec.*;  

public class CriptoTeste { 
    KeyGenerator keygenerator = null;  
    Cipher cifraAES = null;  
    SecretKey chaveAES = null;  
    static String IV = "AAAAAAAAAAAAAAA";  

    public CriptoTeste(int valorKey) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException, NoSuchPaddingException {  
        keygenerator = KeyGenerator.getInstance("AES");  
        keygenerator.init(valorKey);  
        chaveAES = keygenerator.generateKey();  
        System.out.println("aaa"+((chaveAES.toString()).getBytes("UTF-8")).length);  
        cifraAES = Cipher.getInstance("AES/CBC/PKCS5Padding"); // Cria a cifra   
        System.out.println(cifraAES.getBlockSize());  
    }  

    public void encrypt(String srcPath, String destPath) throws UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException {  
        File rawFile = new File(srcPath);  
        File imagemEncriptada = new File(destPath);  
        InputStream inStream = null;  
        OutputStream outStream = null;  
        cifraAES.init(Cipher.ENCRYPT_MODE, chaveAES, new IvParameterSpec(IV.getBytes("UTF-8"))); //Inicializa a cifra para o processo de encriptação  
        inStream = new FileInputStream(rawFile); //Inicializa o input e o output streams  
        outStream = new FileOutputStream(imagemEncriptada);  
        byte[] buffer = new byte[256];  
        int len;  
        while ((len = inStream.read(buffer)) > 0) {  
            outStream.write(cifraAES.update(buffer, 0, len)); //Para criptografar/descriptografar vários blocos usa-se o método update().   
            outStream.flush();  
        }  
        outStream.write(cifraAES.doFinal()); //Depois de tudo feito chamamos o método doFinal().   
        inStream.close();  
        outStream.close();  
    }  

    public void decrypt(String srcPath, String destPath) throws InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException {  
        File encryptedFile = new File(srcPath);  
        File decryptedFile = new File(destPath);  
        InputStream inStream = null;  
        OutputStream outStream = null;  
        cifraAES.init(Cipher.DECRYPT_MODE, chaveAES, new IvParameterSpec(IV.getBytes("UTF-8"))); //Inicializa o cipher para decriptografar  
        inStream = new FileInputStream(encryptedFile); //Inicializa o input e o output streams  
        outStream = new FileOutputStream(decryptedFile);  
        byte[] buffer = new byte[256];  
        int len;  
        while ((len = inStream.read(buffer)) > 0) {  
            outStream.write(cifraAES.update(buffer, 0, len));  
            outStream.flush();  
        }  
        outStream.write(cifraAES.doFinal());  
        inStream.close();  
        outStream.close();  
    }  

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, NoSuchProviderException, InvalidKeyException, InvalidAlgorithmParameterException, IOException, FileNotFoundException, IllegalBlockSizeException, BadPaddingException {  
        String directoryPath = "D:\\Área de Trabalho\\"; 

        long tempInicial = 0;  
        long tempFinal = 0;  
        long dif = 0;  

        //CriptoTeste chave = new CriptoTeste(128); //Passa como parametro o tamanho da chave de 128 bits  
        //CriptoTeste chave = new CriptoTeste(256); //chave de 192 bits  
        CriptoTeste chave = new CriptoTeste(256); //chave de 256 bits  

        System.out.println("Iniciando Codificação...");   
        tempInicial = System.currentTimeMillis();  
        
        for (int i = 1; i <= 10; i++) {   
            String imgOriginal = "veiculo" + i + ".jpg";  
            String imgEncriptada = "ImagensCrip\\imgEncripAES_" + i + ".jpg"; //Nome do arquivo encriptado  
            chave.encrypt(directoryPath + imgOriginal, directoryPath + imgEncriptada);   
        }   
        
        tempFinal = System.currentTimeMillis();  
        dif = (tempFinal - tempInicial);  
        System.out.println(String.format("Tempo de codificação: %02d segundos", dif/60));  
        System.out.println("Codificação Finalizada...");  

        tempInicial = 0;  
        tempFinal = 0;  
        dif = 0;  

        System.out.println("Iniciando Decodificação...");  
        tempInicial = System.currentTimeMillis();  
        
        for (int i = 1; i <= 10; i++) {  
            String imgEncriptada = "ImagensCrip\\imgEncripAES_" + i + ".jpg"; //Nome do arquivo encriptado  
            String imgDecriptada = "ImagensDecrip\\imgDecripAES_" + i + ".jpg"; //Nome do arquivo descriptado  
            chave.decrypt(directoryPath + imgEncriptada, directoryPath + imgDecriptada);  
        }   
        
        tempFinal = System.currentTimeMillis();  
        dif = (tempFinal - tempInicial);  
        System.out.println(String.format("Tempo de codificação: %02d segundos", dif/60));  
        System.out.println("Decodificação Finalizada...");  
    }  
}  