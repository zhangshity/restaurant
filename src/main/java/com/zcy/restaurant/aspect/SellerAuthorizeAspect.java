//package com.zcy.restaurant.aspect;
//
//import com.zcy.restaurant.constant.CookieConstant;
//import com.zcy.restaurant.constant.RedisConstant;
//import com.zcy.restaurant.exception.SellerAuthorizeException;
//import com.zcy.restaurant.utils.CookieUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @ Author: chunyang.zhang
// * @ Description:          AOP实现身份验证
// * @ Date: Created in
// * @ Modified: By:
// */
//@Aspect
//@Component
//@Slf4j
//public class SellerAuthorizeAspect {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//
//    //定义切点(后台seller所有controller 除了SellerUserController验证本身)
//    @Pointcut("execution(public * com.zcy.restaurant.controller.Seller*.*(..))" +
//            "&& !execution(public * com.zcy.restaurant.controller.SellerUserController.*(..))")
//    public void verify() {
//    }
//
//    @Pointcut("execution(public * com.zcy.restaurant.controller.EnvironmentInfoController.*(..))")
//    public void environment() {
//    }
//
//    @Pointcut("execution(public * com.zcy.restaurant.controller.QueueInfoController.*(..)) " +
//            "&& !execution(public * com.zcy.restaurant.controller.QueueInfoController.*Number(..))")
//    public void queue() {
//    }
//
//
//    @Before("verify() || environment() ||queue()")
//    public void doVerify() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //查询cookie
//        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
//        if (cookie == null) { //从客户端request中查不到cookie抛异常
//            log.warn("【登录校验】Cookie中查不到token");
//            throw new SellerAuthorizeException();
//        }
//        //去redis里查询                                   //value = get(key)
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        if (StringUtils.isEmpty(tokenValue)) {
//            log.warn("【登录校验】Redis中查不到token");
//            throw new SellerAuthorizeException();
//        }
//    }
//}
