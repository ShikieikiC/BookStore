package org.shiki.service;

import com.nimbusds.jose.JOSEException;

import java.util.Map;

public interface UserService {


    Map<String, Object> login(String username, String password) throws JOSEException;
}
