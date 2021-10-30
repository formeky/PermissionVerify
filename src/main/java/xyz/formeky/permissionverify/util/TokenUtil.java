package xyz.formeky.permissionverify.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import xyz.formeky.permissionverify.exception.JwtExpireException;
import xyz.formeky.permissionverify.exception.JwtNotExistException;
import xyz.formeky.permissionverify.exception.JwtVerifyException;
import xyz.formeky.permissionverify.token.TokenBuilder;
import xyz.formeky.permissionverify.token.TokenMsg;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zcw
 */
public class TokenUtil {

    public static final String TOKEN_HEADER="Authorization";
    private static final String ID="id";
    private static final String ROLE = "role";
    private static final String USERNAME = "username";

    public static String tokens(Map<String, Object> claims, String secretKey, int millisecond, String jwtIssuer, String jwtAud) {

        //获取当前的时间
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        //向后退后秒数
        calendar.add(Calendar.MILLISECOND, millisecond);
        Date endTime = calendar.getTime();
        JwtBuilder builder = Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(endTime)
                .setIssuer(jwtIssuer)
                .setAudience(jwtAud);
        return builder.compact();
    }

    public static Object tokenValueOf(String jwt, String secretKey, String key) throws JwtVerifyException, JwtNotExistException, JwtExpireException {
        Claims claims = parse(jwt, secretKey);
        if (claims == null || claims.get(key) == null) {
            return null;
        }
        return claims.get(key);
    }

    /**
     * 获取信息
     * @param jwt
     * @param secretKey
     * @return
     * @throws JwtExpireException token过期
     * @throws JwtNotExistException token不存在
     * @throws JwtVerifyException token验证错误
     * @throws IllegalArgumentException 使用Assert检查jwt时抛出的异常，jwt为空
     */
    public static Claims parse(String jwt, String secretKey) throws JwtExpireException, JwtVerifyException , JwtNotExistException{
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException expiredJwtException) {
            throw new JwtExpireException("token过期");
        } catch (JwtException jwtException) {
            throw new JwtVerifyException("JWT验证错误");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new JwtNotExistException("Token为空");
        }
        return claims;
    }

    /**
     * 获取一个token
     * @return
     */
    public static String buildOJToken(TokenBuilder tokenBuilder) {
        Map<String, Object> map =new HashMap<>();
        TokenMsg tokenMsg = tokenBuilder.getMsg();
        map.put(ID, tokenMsg.getId());
        map.put(ROLE, tokenMsg.getRole());
        map.put(USERNAME, tokenMsg.getUsername());
        String token = tokens(map, tokenBuilder.getSecretKey(), tokenBuilder.getMillisecond(), tokenBuilder.getJwtIssuer(), tokenBuilder.getJwtAud());
        return token;
    }
    /**
     * 从http请求的Header中获取token
     * @param httpServletRequest
     * @return
     */
    public static String getToken(HttpServletRequest httpServletRequest) throws JwtNotExistException {
        String token =httpServletRequest.getHeader(TOKEN_HEADER);
        if (token == null) {
            throw new JwtNotExistException("Token不存在");
        }
        return token;
    }

    public static boolean isTokenExisted(HttpServletRequest httpServletRequest) {
        String token =httpServletRequest.getHeader(TOKEN_HEADER);
        return !StringUtils.isEmpty(token);
    }
    /**
     * 解析token，获取TokenMsg
     * 若Token中不包含该项目，则会填充为空null
     * 必需解析本工具包所加密的token
     * @param token
     * @param secretKey
     * @return 解析结果
     * @throws JwtExpireException token过期
     * @throws JwtNotExistException token不存在
     * @throws JwtVerifyException token验证错误
     * @throws IllegalArgumentException 使用Assert检查jwt时抛出的异常，jwt为空
     */
    public static TokenMsg getMsgFromToken(String token,String secretKey) throws JwtExpireException, JwtVerifyException , JwtNotExistException{
        TokenMsg tokenMsg =new TokenMsg();
        tokenMsg.setId((Integer) tokenValueOf(token,secretKey,ID));
        tokenMsg.setRole((Integer)tokenValueOf(token,secretKey,ROLE));
        tokenMsg.setUsername((String) tokenValueOf(token, secretKey, USERNAME));
        return tokenMsg;
    }
    public static TokenMsg getMsgFromToken(HttpServletRequest httpServletRequest,String secretKey) throws JwtExpireException, JwtVerifyException , JwtNotExistException{
        String token = getToken(httpServletRequest);
        TokenMsg tokenMsg =new TokenMsg();
        tokenMsg.setId((Integer) tokenValueOf(token,secretKey,ID));
        tokenMsg.setRole((Integer)tokenValueOf(token,secretKey,ROLE));
        tokenMsg.setUsername((String) tokenValueOf(token, secretKey, USERNAME));
        return tokenMsg;
    }
}
