package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static boolean checkpassword(String original,String hash){
        System.out.println(original);
        System.out.println(hash);
        return BCrypt.checkpw(original, hash);

    }
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));

    }

}
