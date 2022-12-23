package Encryption;

public class FileNameEncryptor {
    public static String encrypt(String plaintext, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                // Shift the character by the specified number of positions
                char encryptedChar = (char) ((c + shift - 'a') % 26 + 'a');
                encrypted.append(encryptedChar);
            } else {
                // Leave non-letter characters unchanged
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }





}
