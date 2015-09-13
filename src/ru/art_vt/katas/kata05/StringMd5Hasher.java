package ru.art_vt.katas.kata05;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringMd5Hasher implements IHasher<String>
{
	MessageDigest digest;

	public StringMd5Hasher()
	{
		try {
			this.digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] hash(String value)
	{
		return digest.digest(value.getBytes());
	}
}
