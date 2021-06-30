package com.example.library_mgmt.config;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Security {
    private static final String STR_KEY = "aabb09182736ccdd";

    public static String encrypt(String strClearText){
        String strData="";

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(STR_KEY.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted=cipher.doFinal(strClearText.getBytes());
            strData=new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }

    public static String decrypt(String strEncrypted){
        String strData="";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(STR_KEY.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
            strData=new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }
}
