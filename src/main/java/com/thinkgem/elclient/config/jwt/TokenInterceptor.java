package com.thinkgem.elclient.config.jwt;

import com.thinkgem.elclient.config.enums.ResultEnum;
import com.thinkgem.elclient.config.Constant;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.elclient.config.redis.RedisUtil;
import com.thinkgem.elclient.utils.SpringContextUtils;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author zhaoqingjie
 */
public class TokenInterceptor implements HandlerInterceptor{

	private static Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return true;
		}
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Iot ")) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null ;
			JSONObject res=new JSONObject();
			res.put("code", ResultEnum.LOGIN_TOKEN_ERROR.getCode());
			res.put("msg", ResultEnum.LOGIN_TOKEN_ERROR.getMessage());
		    out = response.getWriter();
		    out.append(res.toString());
		    return false;
		}
		// 取得token
		String token = authHeader.substring(11);
		Claims claims;
		try {
			claims = Jwts.parser()
			        .setSigningKey(Constant.JWT_SECRETE_KEY)
			        .parseClaimsJws(token).getBody();
		//在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
		//在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null ;
			JSONObject res=new JSONObject();
			if(e instanceof SignatureException){
				res.put("code",ResultEnum.LOGIN_TOKEN_ERROR.getCode());
				res.put("msg", ResultEnum.LOGIN_TOKEN_ERROR.getMessage());
			}else if(e instanceof ExpiredJwtException){
				res.put("code",ResultEnum.LOGIN_TOKEN_INVALID.getCode());
				res.put("msg", ResultEnum.LOGIN_TOKEN_INVALID.getMessage());
			}else{
				res.put("code",ResultEnum.ERROR.getCode());
				res.put("msg", ResultEnum.ERROR.getMessage());
				log.error("JWT秘钥解析其他异常！", e);
			}
		    out = response.getWriter();
		    out.append(res.toString());
		    return false;
		}

		RedisUtil redisUtil = (RedisUtil)SpringContextUtils.getBean("redisUtil");
		Map<Object,Object> map = redisUtil.hmget(token);
		if(map == null || map.isEmpty()){  // 服务器端 主动注销
			response.setStatus(HttpServletResponse.SC_OK);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = null;
			JSONObject res = new JSONObject();
			res.put("code",ResultEnum.LOGIN_TOKEN_INVALID.getCode());
			res.put("msg", ResultEnum.LOGIN_TOKEN_INVALID.getMessage());
			out = response.getWriter();
			out.append(res.toString());
			return false;
		}else{
			String id = String.valueOf(map.get("id"));
			String name = String.valueOf(map.get("name"));
			String dayTime = String.valueOf(map.get("dayTime"));
			if(StringUtils.isBlank(id) || StringUtils.isBlank(name) || StringUtils.isBlank(dayTime)) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = null;
				JSONObject res = new JSONObject();
				res.put("code",ResultEnum.CACHE_SERVER_EXCEPTION.getCode());
				res.put("msg", ResultEnum.CACHE_SERVER_EXCEPTION.getMessage());
				out = response.getWriter();
				out.append(res.toString());
				return false;
			}

//			if (!LocalDate.parse(dayTime).isEqual(LocalDate.now())) {
//				JwtConfigBean jwtConfigBean = (JwtConfigBean) SpringContextUtils.getBean("jwtConfigBean");
//				LoginResponse loginResponse = new LoginResponse();
//				Date now = new Date();
//				String newToken = Jwts.builder().setSubject(name)
//						.signWith(SignatureAlgorithm.HS256, Constant.JWT_SECRETE_KEY)
//						.setIssuedAt(now)
//						.claim("id", id)
//						.claim("name", name)
//						.setExpiration(Date.from(Instant.now().plus(jwtConfigBean.getValidity(), Constant.jwtValidityMap.get(jwtConfigBean.getValidityType()))))
//						.compact();
//				loginResponse.setToken(newToken);
//
//				BaseResponse baseResponse = BaseResponseUtil.success(loginResponse);
//				response.setStatus(HttpServletResponse.SC_OK);
//				response.setCharacterEncoding("UTF-8");
//				response.setContentType("application/json; charset=utf-8");
//				PrintWriter out = null;
//				out = response.getWriter();
//				out.append(JSON.toJSON(baseResponse).toString());
//			}

			request.setAttribute("claims", claims);
			log.info("ID: " + claims.getId());
			log.info("Subject: " + claims.getSubject());
			log.info("Issuer: " + claims.getIssuer());
			log.info("Expiration: " + claims.getExpiration());

			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}
	
	public static Claims parseJWT(String token){  
        try  
        {  
            Claims claims = Jwts.parser()  
                       .setSigningKey(Constant.JWT_SECRETE_KEY)
                       .parseClaimsJws(token).getBody();  
            return claims;  
        }  
        catch(Exception ex)  
        {  
        	ex.printStackTrace();
            return null;  
        }  
    }  

}
