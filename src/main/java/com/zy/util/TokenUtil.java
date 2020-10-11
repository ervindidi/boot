package com.zy.util;

import java.util.Date;
import java.util.HashMap;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zy.entity.UserInfo;

public class TokenUtil {
	 private static final long EXPIRE_TIME= 20*24*60*60*1000;//20天
	 private static final String TOKEN_SECRET="tokenjoinlabs321";  //密钥盐

	 /**
	 * 签名生成  生成加密字符串
	 * @param user
	 * @return
			 */
	public static String sign(UserInfo user){

		String token = null;
		try {
			Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			token = JWT.create()
					.withIssuer("zy")
					.withClaim("userName", user.getUserName())
					.withClaim("id", ""+user.getId())
					.withExpiresAt(expiresAt)
					// 使用了HMAC256加密算法。
					.sign(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (Exception e){
			e.printStackTrace();
		}
		return token;

	}

	/**
	 * 签名验证
	 * @param token
	 * @return
	 */
	public static HashMap<String,String> verify(String token){
		HashMap<String,String> map = new HashMap<String,String>();

		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("zy").build();
			DecodedJWT jwt = verifier.verify(token);
			System.out.println("认证通过：");
			map.put("id",jwt.getClaim("id").asString());
			map.put("userName",jwt.getClaim("userName").asString());
			return map;
		} catch (Exception e){
			System.out.println("认证失败：");
			return null;
		}

	}

	/**
	 * 签名生成  生成加密字符串
	 * @param imageCode
	 * @return
	 */
	public static String signImageCode(String imageCode){

		String token = null;
		try {
			Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			token = JWT.create()
					.withIssuer("zy")
					.withClaim("imageCode", imageCode)
					.withExpiresAt(expiresAt)
					// 使用了HMAC256加密算法。
					.sign(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (Exception e){
			e.printStackTrace();
		}
		return token;

	}

	/**
	 * 获取用户数据
	 * @param token
	 * @return
	 */
	public static String getImageCode(String token){
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("zy").build();
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getClaim("imageCode").asString();
		} catch (Exception e){
		}
		return null;
	}

    /**
     * 签名生成  生成加密字符串
     * @param value
     * @return
     */
    public static String signMsg(String key,String value){

        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("zy")
                    .withClaim(key, value)
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;

    }

    /**
     * 获取用户数据
     * @param token
     * @return
     */
    public static String getMsg(String key,String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("zy").build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(key).asString();
        } catch (Exception e){
        }
        return null;
    }
}
