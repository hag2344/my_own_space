package com.nhs.firstProject.common.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nhs.firstProject.common.util.AesCrypto;
import com.nhs.firstProject.common.exception.ProjectException;

public class PropertiesEncrypt {

  private static Logger log = LoggerFactory.getLogger("AESTEST");
  /**
   * @param args
 * @throws KAMASException 
 * @throws BadPaddingException 
 * @throws IllegalBlockSizeException 
 * @throws NoSuchPaddingException 
 * @throws NoSuchAlgorithmException 
 * @throws InvalidKeyException 
   */
  public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, ProjectException {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    String test = "";
    try {
		test = AesCrypto.encrypt("zkvnclsh1!");
	} catch (InvalidKeyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (BadPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ProjectException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

}