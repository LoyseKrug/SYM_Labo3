package ch.heigvd.iict.sym.a3dcompassapp.Services;

public class LoginService {
    private static final String usernameSaved = "A";
    private static final String passwordSaved = "a";
    private static final String tagSaved = "test";

    // TODO Change with list of username, password, tag
    public static boolean logged(String username, String password, String tag){
        return username.equals(usernameSaved) && password.equals(passwordSaved) && isTagCorrect(tag);
    }

    public static boolean isTagCorrect(String tag){
        return tag.equals("test");
    }
}
