package com.tianjian.util.security;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import com.tianjian.util.comm.Util;

public class VersionDecrypt {

	public RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		try {
			keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
		}
		catch (NoSuchAlgorithmException ex) {
			throw new Exception(ex.getMessage());
		}
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		try {
			return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		}
		catch (InvalidKeySpecException ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public byte[] decrypt(Key key, byte[] raw) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(cipher.DECRYPT_MODE, key);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;
			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static String decData(String hexenc) throws Exception {
		String serverPubKeyMod = "00B76A87391FB9FEF9F18D50508C730DCDCE56F82F48569BCF8094442ACDA069BAC21DD9E577B35B1F7E800F71D4F7F7483F2FBEE1E4A32F9325D80F7F255136A19621AC57692FE1A97AD9331B5218BDEC7A74ED6C70845D70D7A07DDFFA3D76CE854C75D0D2E4253192863FA0DB9E100A1A695530FD0818ED4BD28F876523C371";
		String serverPubKeyExp = "010001";
		VersionDecrypt vd = new VersionDecrypt();
		RSAPublicKey recoveryPubKey = vd.generateRSAPublicKey(Util.hex2byte(serverPubKeyMod), Util.hex2byte(serverPubKeyExp));
		byte[] orgData = Util.hex2byte(hexenc);
		byte[] data = vd.decrypt(recoveryPubKey, orgData);
		return new String(data);
	}
}
