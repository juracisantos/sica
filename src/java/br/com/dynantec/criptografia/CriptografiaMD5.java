package br.com.dynantec.criptografia;

import java.security.NoSuchAlgorithmException;

public class CriptografiaMD5 extends CriptografiaGenerica implements
        Criptografia {

    @Override
    public String encrypt(String value) throws NoSuchAlgorithmException {
        return encryptByAlgorithm("MD5", value);
    }
}
