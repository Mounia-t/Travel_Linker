package travelLinker.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

	    // Méthode pour hacher le mot de passe
	    public static String hashPassword(String password) {
	        // Générez un salt (sel) aléatoire pour chaque mot de passe
	        String salt = BCrypt.gensalt();

	        // Utilisez BCrypt pour hacher le mot de passe avec le sel
	        String hashedPassword = BCrypt.hashpw(password, salt);

	        // Renvoie le mot de passe haché
	        return hashedPassword;
	    }

	    // Méthode pour vérifier si le mot de passe en clair correspond au mot de passe haché
	    public static boolean checkPassword(String password, String hashedPassword) {
	        return BCrypt.checkpw(password, hashedPassword);
	    }
	}


