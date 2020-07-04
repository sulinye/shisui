package com.su.shisui.auth.common;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

/**
 * author sly
 */
public class WebTokenUtil {

//    private static final String sector = CryptUtil.encrypt("dbmediacal@huaque.V1.0");
private static final String sector = CryptUtil.encrypt("su@shisui.V1.0");
    private static final String JWT_SEPARATOR = "Bearer ";

    public WebTokenUtil() {
    }

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(sector);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public static String createJavaWebToken(Map<String, Object> claims, Integer duration) {
        DateTime iat = DateTime.now();
        DateTime exp = null;
        if (duration != null) {
            exp = iat.plusSeconds(duration);
        } else {
            exp = iat.plusSeconds(7200);
        }

        return "Bearer " + Jwts.builder().setClaims(claims).setIssuedAt(iat.toDate()).setExpiration(exp.toDate()).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
    }

    public static Map<String, Object> parserJavaWebToken(String jwtToken) throws Exception {
        try {
            Map<String, Object> jwtClaims = (Map)Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken).getBody();
            return jwtClaims;
        } catch (Exception var2) {
            throw new Exception(var2);
        }
    }
}
