package service;

import model.User;

public class UserCreator {

    public static final String USER_EMAIL = "ilyintests@gmail.com";
    public static final String USER_PASSWORD = "1722dDkK3371";

    public static User withCredentialsFromProperty() {
        return new User(USER_EMAIL, USER_PASSWORD);
    }
}
