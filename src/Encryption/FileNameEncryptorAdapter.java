package Encryption;
public class FileNameEncryptorAdapter implements Encryptable {
    private FileNameEncryptor encryptor;
    private static final int SHIFT=8;

    public FileNameEncryptorAdapter(FileNameEncryptor encryptor) {
        this.encryptor = encryptor;
    }
    @Override
    public String encrypt(String fileName) {
        return encryptor.encrypt(fileName,SHIFT);
    }
}
