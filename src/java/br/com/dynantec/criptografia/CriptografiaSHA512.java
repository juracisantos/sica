package br.com.dynantec.criptografia;

import java.security.NoSuchAlgorithmException;

public class CriptografiaSHA512 extends CriptografiaGenerica implements
        Criptografia {

    @Override
    public String encrypt(String value) throws NoSuchAlgorithmException {
        return encryptByAlgorithm("SHA-512", value);
    }
}
