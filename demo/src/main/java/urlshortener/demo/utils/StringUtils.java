package urlshortener.demo.utils;

import java.security.SecureRandom;

public class StringUtils {

    private static char[] availableChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static String randomHash(){
        return randomHash(16);
    }

    public static String randomHash(int length){
        StringBuilder str = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < length; i++){
            str.append(availableChars[secureRandom.nextInt(availableChars.length)]);
        }
        return str.toString();
    }


    private StringUtils(){
        //Let's hide the implicit public constructor as this class should never be instantiated.
    }
}
