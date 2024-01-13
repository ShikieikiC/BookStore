package org.shiki.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import java.text.ParseException;
import java.util.Map;

public class JWTUtil {
    private static final String SECRET = "Whosyourdaddy-Shikieiki-Onmyoji-Luguangyu";

    public static String generateToken(Map<String, Object> userInfo) throws JOSEException {
        JWSObject jwsObject = new JWSObject(
                new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build(),
                new Payload(userInfo)
        );
        jwsObject.sign(new MACSigner(SECRET));

        return jwsObject.serialize();
    }

    public static Boolean verifyToken(String token) throws ParseException, JOSEException {
        return JWSObject.parse(token).verify(new MACVerifier(SECRET));
    }

    public static Map<String, Object> parseToken(String token) throws ParseException {
        return JWSObject.parse(token).getPayload().toJSONObject();
    }
    
}
