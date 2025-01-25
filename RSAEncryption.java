import java.security.*;
import java.util.*;

import javax.crypto.Cipher;

public class RSAEncryption {
  public static void main(String[] args) throws Exception {
    // gets the user input
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a message to encrypt:");
    String userMessage = input.nextLine();
    
    // generates RSA key pair
    KeyPair keyPair = generateRsaKey();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();
    
    // implements the encryption
    byte [] cipherText = encrypt(userMessage, publicKey);
    
    // prints cipher text
    System.out.println("Plaintext is: " + userMessage);
    System.out.println("Ciphertext is: " + new String(cipherText));
    
    //implements the decryption
    String originalMessage = decrypt(cipherText, privateKey);
    System.out.println("Decrypted text from cipher is: " + originalMessage);
    
    
  }
  
  public static KeyPair generateRsaKey() throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    // define size of the key
    keyPairGenerator.initialize(2048);
    return keyPairGenerator.generateKeyPair();
  }
  
  public static byte [] encrypt(String message, PublicKey publicKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    return cipher.doFinal(message.getBytes());
  }
  
  public static String decrypt(byte [] cipherText, PrivateKey privateKey) throws Exception {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte [] decryptedArray = cipher.doFinal(cipherText);
    return new String(decryptedArray);
  }
  
}