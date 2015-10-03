package org.smelser.web.oozie.utilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author TransitDroid Team
 * @class EncryptionService
 * 
 *        Provides functionality for encryption and decryption.
 * 
 */
public class EncryptionService {

    private static int derivedKeyLength = 128;
    private static int iterations = 1024;
    private static String passphrase = "VieuxDuluthQuelFestin";

    /**
     * Verifies that the String 'original' is the original representation for
     * the byte[] 'encrypted'.
     * 
     * @param original
     *            The original string that must be tested for authenticity.
     * @param encrypted
     *            The encrypted string to match.
     * @param salt
     *            The salt used to encrypt the string initially.
     * @param alg
     *            The encryption algorithm used to encrypt the string initially.
     * @return true if the original is the decrypted String representation of
     *         'encrypted' and false otherwise.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static boolean authenticate(String original, byte[] encrypted, byte[] salt, EncryptionAlgorithm alg)
	    throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
	    NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
	    InvalidAlgorithmParameterException {
	byte[] encryptedAttemptedPassword = encrypt(original, salt, alg);
	return Arrays.equals(encrypted, encryptedAttemptedPassword);
    }

    /**
     * Verifies that the String 'original' is the original representation for
     * the byte[] 'encrypted' using the default algorithm PBKDF2WithHmacSHA1.
     * 
     * @param original
     *            The original string that must be tested for authenticity.
     * @param encrypted
     *            The encrypted string to match.
     * @param salt
     *            The salt used to encrypt the string initially.
     * @return if the original is the decrypted String representation of
     *         'encrypted' and false otherwise.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws AuthenticationException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static void authenticate(String original, byte[] encrypted, byte[] salt)
	    throws NoSuchAlgorithmException, InvalidKeySpecException, AuthenticationException,
	    InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
	    InvalidAlgorithmParameterException {
	if (!authenticate(original, encrypted, salt, EncryptionAlgorithm.AES)) {
	    throw new AuthenticationException();
	}
    }

    /**
     * Verifies that each of the Strings in 'originals' match the decrypted
     * byte[] in 'encrypted' with the same index. The default algorithm
     * 'PBKDF2WithHmacSHA1' is used.
     * 
     * @param salt
     *            The salt used to encrypt the string initially.
     * @param originals
     *            The original strings that must be tested for authenticity.
     * @param encrypted
     *            The encrypted strings to match.
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static boolean authenticate(byte[] salt, List<String> originals, List<byte[]> encrypted)
	    throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException,
	    NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
	    InvalidAlgorithmParameterException {
	Iterator<String> oit = originals.iterator();
	Iterator<byte[]> eit = encrypted.iterator();
	String original = null;
	byte[] encrypt = null;
	while (oit.hasNext()) {
	    original = oit.next();
	    encrypt = eit.next();
	    try {
		authenticate(original, encrypt, salt);
	    } catch (AuthenticationException e) {
		return false;
	    } finally {
	    }
	}
	return true;
    }

    /**
     * Encrypts the String original to a byte[] using the salt and algorithm
     * passed in the parameters
     * 
     * @param toEncrypt
     *            The original string that must be tested for authenticity.
     * @param salt
     *            The salt used to encrypt the string initially.
     * @param alg
     *            The encryption algorithm to use.
     * @return The encrypted byte[].
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] encrypt(String toEncrypt, byte[] salt, EncryptionAlgorithm alg)
	    throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
	    InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
	    InvalidAlgorithmParameterException {

	Cipher cipher = Cipher.getInstance(alg.toString());
	SecretKey key = getKey(salt, alg.toString());
	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	IvParameterSpec ivspec = new IvParameterSpec(iv);
	cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
	return cipher.doFinal(toEncrypt.getBytes());
    }

    public static String encryptToString(String toEncrypt, byte[] salt, EncryptionAlgorithm alg)
	    throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
	    NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
	    InvalidAlgorithmParameterException {
	return base64Encode(encrypt(toEncrypt, salt));
    }

    public static String encryptToString(String toEncrypt, byte[] salt) throws InvalidKeyException,
	    NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
	    IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
	return encryptToString(toEncrypt, salt, EncryptionAlgorithm.SHA1);
    }

    /**
     * Encrypts the String original to a byte[] using the salt and the default
     * Algorithm PBKDF2WithHmacSHA1.
     * 
     * @param toEncrypt
     *            The original string that must be tested for authenticity.
     * @param salt
     *            The salt used to encrypt the string initially.
     * @return The encrypted byte[].
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidAlgorithmParameterException
     */
    public static byte[] encrypt(String toEncrypt, byte[] salt) throws NoSuchAlgorithmException,
	    InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException,
	    BadPaddingException, InvalidAlgorithmParameterException {
	return encrypt(toEncrypt, salt, EncryptionAlgorithm.AES);
    }

    public static String decrypt(String toDecript, byte[] salt, EncryptionAlgorithm alg) throws IOException,
	    InvalidKeyException, InvalidAlgorithmParameterException, InvalidParameterSpecException,
	    NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
	    IllegalBlockSizeException, BadPaddingException {
	Cipher cipher = Cipher.getInstance(alg.toString());
	SecretKeySpec key = getKey(salt, alg.toString());
	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	IvParameterSpec ivspec = new IvParameterSpec(iv);
	cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
	// AlgorithmParameters params = cipher.getParameters();
	// byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
	// cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
	byte[] decryptedData = base64Encode(toDecript);
	byte[] utf8 = cipher.doFinal(decryptedData);
	return new String(utf8, "UTF8");
    }

    public static String decrypt(String toDecrypt, byte[] salt) throws IOException, InvalidKeyException,
	    InvalidAlgorithmParameterException, InvalidParameterSpecException, NoSuchAlgorithmException,
	    NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
	return decrypt(toDecrypt, salt, EncryptionAlgorithm.AES);
    }

    /**
     * Generates a random byte[8] to be used as the salt for the default
     * algorithm.
     * 
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generateSalt() throws NoSuchAlgorithmException {
	SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

	// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
	byte[] salt = new byte[8];
	random.nextBytes(salt);
	return salt;
    }

    /**
     * Generates a java.security.Key using the 'DES' algorithm.
     * 
     * @return The newly generated Key.
     * @throws NoSuchAlgorithmException
     */
    public static Key generateKey() throws NoSuchAlgorithmException {
	KeyGenerator generator;
	generator = KeyGenerator.getInstance(EncryptionAlgorithm.DES.toString());
	generator.init(new SecureRandom());
	return generator.generateKey();
    }

    private static SecretKeySpec getKey(byte[] salt, String alg) throws NoSuchAlgorithmException,
	    InvalidKeySpecException {
	// KeySpec spec = new PBEKeySpec(Arrays.copyOf(passphrase.toCharArray(),
	// 16), salt,
	// iterations, derivedKeyLength);
	// SecretKeyFactory f = SecretKeyFactory.getInstance(alg);
	return new SecretKeySpec(Arrays.copyOf(passphrase.getBytes(), 16), "AES");
	// return f.generateSecret(spec);
    }

    public static String base64Encode(byte[] raw) {
	BASE64Encoder encoder = new BASE64Encoder();
	return encoder.encode(raw);
    }

    public static byte[] base64Encode(String raw) throws IOException {
	BASE64Decoder decoder = new BASE64Decoder();
	return decoder.decodeBuffer(raw);
    }

    /**
     * 
     * @param toEncrypt
     *            The original string that must be tested for authenticity.
     * @param salt
     *            The salt used to encrypt the string initially.
     * @return The encrypted byte[].
     * @throws EncryptionException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static byte[] encrypt(byte[] toEncrypt, Key salt) throws EncryptionException {
	// Get a cipher object.
	byte[] raw = null;
	try {
	    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
	    cipher.init(Cipher.ENCRYPT_MODE, salt);

	    raw = cipher.doFinal(toEncrypt);

	} catch (NoSuchAlgorithmException e) {
	    throw new EncryptionException(e);
	} catch (InvalidKeyException e) {
	    throw new EncryptionException(e);
	} catch (NoSuchPaddingException e) {
	    throw new EncryptionException(e);
	} catch (IllegalBlockSizeException e) {
	    throw new EncryptionException(e);
	} catch (BadPaddingException e) {
	    throw new EncryptionException(e);
	}
	return raw;
    }

    public static byte[] encrypt(byte[] toEncrypt, byte[] rawSaltKey) throws EncryptionException {
	byte[] result = null;
	Key salt = new SecretKeySpec(rawSaltKey, 0, rawSaltKey.length, EncryptionAlgorithm.DES.toString());
	try {
	    Cipher cipher = Cipher.getInstance(EncryptionAlgorithm.DES.toString());
	    cipher.init(Cipher.ENCRYPT_MODE, salt);

	    result = cipher.doFinal(toEncrypt);
	} catch (NoSuchAlgorithmException e) {
	    throw new EncryptionException(e);
	} catch (InvalidKeyException e) {
	    throw new EncryptionException(e);
	} catch (NoSuchPaddingException e) {
	    throw new EncryptionException(e);
	} catch (IllegalBlockSizeException e) {
	    throw new EncryptionException(e);
	} catch (BadPaddingException e) {
	    throw new EncryptionException(e);
	}
	return result;
    }

    public static String encryptToString(byte[] toEncrypt, byte[] rawSaltKey) throws EncryptionException {
	byte[] raw = encrypt(toEncrypt, rawSaltKey);
	// converts to base64 for easier display.
	return base64Encode(raw);
    }

    /**
     * Decrypts a String encrypted encrypted by the encrypt(String, Key)
     * function.
     * 
     * @param encryptedVersion
     *            The encrypted string
     * @param rawSaltKey
     *            The byte[] representation of the Key used in encryption.
     * @return The decrypted String.
     * @throws EncryptionException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public static byte[] decrypt(byte[] encryptedVersion, byte[] rawSaltKey) throws EncryptionException {
	// Get a cipher object.
	byte[] result = null;
	Key salt = new SecretKeySpec(rawSaltKey, 0, rawSaltKey.length, EncryptionAlgorithm.DES.toString());

	Cipher cipher;

	// decode the BASE64 coded message
	// BASE64Decoder decoder = new BASE64Decoder();
	// byte[] raw = decoder.decodeBuffer(encrypted);

	try {
	    cipher = Cipher.getInstance(EncryptionAlgorithm.DES.toString());

	    cipher.init(Cipher.DECRYPT_MODE, salt);

	    result = cipher.doFinal(encryptedVersion);

	} catch (NoSuchAlgorithmException e) {
	    throw new EncryptionException(e);
	} catch (NoSuchPaddingException e) {
	    throw new EncryptionException(e);
	} catch (IllegalBlockSizeException e) {
	    throw new EncryptionException(e);
	} catch (BadPaddingException e) {
	    throw new EncryptionException(e);
	} catch (InvalidKeyException e) {
	    throw new EncryptionException(e);
	}
	// If you want to return a string
	// String clear = new String(stringBytes, "UTF8");
	// return clear;

	return result;
    }

    public static byte[] decrypt(String encryptedString, String rawSaltKeyString) throws EncryptionException {
	byte[] result = null;
	try {
	    byte[] rawSaltKey = base64Encode(rawSaltKeyString);
	    byte[] encryptedVersion = base64Encode(encryptedString);
	    Key salt = new SecretKeySpec(rawSaltKey, 0, rawSaltKey.length, EncryptionAlgorithm.DES.toString());

	    result = decrypt(encryptedVersion, salt);

	} catch (IOException e) {
	    throw new EncryptionException(e);
	}
	// If you want to return a string
	// String clear = new String(stringBytes, "UTF8");
	// return clear;
	return result;
    }

    /**
     * Decrypts a String encrypted encrypted by the encrypt(String, Key)
     * function.
     * 
     * @param encryptedVersion
     *            The encrypted string
     * @param keyData
     *            The byte[] representation of the Key used in encryption.
     * @return The decrypted String.
     * @throws EncryptionException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws IOException
     */
    public static byte[] decrypt(byte[] encryptedVersion, Key salt) throws EncryptionException {
	// Get a cipher object.
	byte[] result = null;

	Cipher cipher;
	try {
	    cipher = Cipher.getInstance(EncryptionAlgorithm.DES.toString());

	    cipher.init(Cipher.DECRYPT_MODE, salt);

	    result = cipher.doFinal(encryptedVersion);

	} catch (NoSuchAlgorithmException e) {
	    throw new EncryptionException(e);
	} catch (NoSuchPaddingException e) {
	    throw new EncryptionException(e);
	} catch (InvalidKeyException e) {
	    throw new EncryptionException(e);
	} catch (IllegalBlockSizeException e) {
	    throw new EncryptionException(e);
	} catch (BadPaddingException e) {
	    throw new EncryptionException(e);
	}
	return result;
    }

    public static List<String> encryptListOfBytes(List<byte[]> origonal, byte[] salt)
	    throws EncryptionException {
	List<String> encrypted = new ArrayList<String>();
	for (byte[] key : origonal) {
	    encrypted.add(encryptToString(key, salt));
	}
	return encrypted;
    }

    /**
     * a Collection of encryption algorithms.
     * 
     * @author TransitDroid Team
     * 
     */
    public static enum EncryptionAlgorithm {
	SHA1("PBKDF2WithHmacSHA1"), DES("DES"), AES("AES"), ;

	private final String algorithm;

	private EncryptionAlgorithm(String algorithm) {
	    this.algorithm = algorithm;
	}

	@Override
	public String toString() {
	    return algorithm;
	}
    }

    public static boolean authenticate(List<String> encrypted, List<byte[]> original, String salt)
	    throws EncryptionException {
	int i = 0;
	for (byte[] org : original) {
	    byte[] saltBytes = null;
	    try {
		saltBytes = base64Encode(salt);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    System.out.println("Contract Key: " + encryptToString(org, saltBytes));
	    System.out.println("Phone Key: " + encrypted.get(i));
	    if (!Arrays.equals(org, decrypt(encrypted.get(i), salt))) {
		return false;
	    }
	    i++;
	}
	return true;
    }

    public static String decryptCookie(Cookie cookie, String salt) throws InvalidKeyException,
	    InvalidAlgorithmParameterException, InvalidParameterSpecException, NoSuchAlgorithmException,
	    NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException,
	    IOException {
	return EncryptUtils.base64decode(cookie.getValue());
    }

    public static String encrytCookie(String cookieValue, String salt) throws UnsupportedEncodingException,
	    NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException,
	    IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
	return EncryptUtils.base64encode(cookieValue);
    }

    public byte[] enrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
	    InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException,
	    BadPaddingException {
	byte[] input = null;
	byte[] keyBytes = null;
	byte[] ivBytes = null;
	SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
	IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	// create the cipher with the algorithm you choose
	// see javadoc for Cipher class for more info, e.g.
	Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
	byte[] encrypted = new byte[cipher.getOutputSize(input.length)];
	int enc_len = cipher.update(input, 0, input.length, encrypted, 0);
	enc_len += cipher.doFinal(encrypted, enc_len);
	return encrypted;
    }

}