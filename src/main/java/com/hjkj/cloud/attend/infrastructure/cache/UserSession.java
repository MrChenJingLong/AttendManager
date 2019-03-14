package com.hjkj.cloud.attend.infrastructure.cache;


import java.util.concurrent.ConcurrentHashMap;

public class UserSession extends ConcurrentHashMap<String,UserState> {

    private static final long serialVersionUID = -4195136375106785272L;

    private static class UserSessionHandle {
        private static UserSession userSession = new UserSession();
    }

    private UserSession() {

    }

    public static UserSession getInstance() {
        return UserSessionHandle.userSession;
    }

    public static void add(String key,UserState value) {
        getInstance().put(key,value);
    }

    public static UserState find(String key) {
        return getInstance().get(key);
    }

    public static void delete(String key) {
        if (find(key) != null) {
            getInstance().remove(key);
        }

    }

}
