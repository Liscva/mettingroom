package com.liscva.mettingroom.cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionCache implements Cache{

    private HttpServletRequest request ;

    public SessionCache(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public Object getCache(String key) {
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    @Override
    public void setCache(String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key,value);
    }
}
