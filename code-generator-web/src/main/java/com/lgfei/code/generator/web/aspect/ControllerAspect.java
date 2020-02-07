//package com.lgfei.code.generator.web.aspect;
//
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Field;
//import java.net.URLDecoder;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.lgfei.betterme.framework.common.vo.RequestVO;
//
//@Aspect
//@Component
//public class ControllerAspect
//{
//    private static final Logger LOG = LoggerFactory.getLogger(ControllerAspect.class);
//    
//    private static final String ENTITY = "entity";
//    
//    private static final String ENTITY_POINT = "entity.";
//    
//    private static final String ENTITY_CLASS_NAME = "entityClassName";
//    
//    ThreadLocal<Long> startTime = new ThreadLocal<>();
//    
//    public ControllerAspect()
//    {
//        LOG.info("init ControllerAspect...");
//    }
//    
//    @Pointcut("execution(public * com.lgfei..web.controller.*Controller.*(..)) or execution(public * com.lgfei..api.controller.*Controller.*(..))")
//    public void excudeAspect()
//    {
//    }
//    
//    @Before("excudeAspect()")
//    public void doBefore(JoinPoint joinPoint)
//    {
//        LOG.info("doBefore");
//    }
//    
//    @After("excudeAspect()")
//    public void doAfter(JoinPoint joinPoint)
//    {
//        LOG.info("doAfter");
//    }
//    
//    @AfterReturning("excudeAspect()")
//    public void doAfterReturning(JoinPoint joinPoint)
//    {
//        LOG.info("doAfterReturning");
//    }
//    
//    @AfterThrowing("excudeAspect()")
//    public void deAfterThrowing(JoinPoint joinPoint)
//    {
//        LOG.info("deAfterThrowing");
//    }
//    
//    @Around("excudeAspect()")
//    public Object deAround(ProceedingJoinPoint joinPoint)
//        throws Throwable
//    {
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        
//        Object[] args = joinPoint.getArgs();
//        if (null == args || args.length == 0)
//        {
//            return joinPoint.proceed();
//        }
//        else if(args.length == 1 && args[0] instanceof RequestVO) 
//        {
//            Class<?> entityClass = null;
//            try
//            {
//                entityClass = getEntityClass(joinPoint);
//            }
//            catch (Exception e)
//            {
//                LOG.error("无法取到entityClassName,不处理,异常消息{}", e.getMessage());
//                return joinPoint.proceed();
//            }
//            
//            Object reqDataObj =  args[0];
//            
//            // 提取get请求的参数
//            JSONObject paramsJson = new JSONObject();
//            JSONObject getQueryJson = getParamsJson4Get(request.getQueryString());
//            if (null != getQueryJson)
//            {
//                paramsJson.putAll(getQueryJson);
//            }
//            // 提取post请求的参数
//            JSONObject postQueryJson = new JSONObject();
//            String method = request.getMethod();
//            if ("POST".equals(method))
//            {
//                postQueryJson = getParamsJson4Post(reqDataObj, entityClass);
//                if (null != postQueryJson)
//                {
//                    paramsJson.putAll(postQueryJson);
//                }
//            }
//            
//            RequestVO reqData = new RequestVO<>();
//            reqData = JSONObject.toJavaObject(paramsJson, reqData.getClass());
//            
//            // 转化entity参数
//            JSONObject entityJson = new JSONObject();
//            JSONObject getEntityJson = JSONObject.parseObject(JSON.toJSONString(getQueryJson.get(ENTITY)));
//            if (null != getEntityJson)
//            {
//                entityJson.putAll(getEntityJson);
//            }
//            JSONObject postEntityJson = JSONObject.parseObject(JSON.toJSONString(postQueryJson.get(ENTITY)));
//            if (null != postEntityJson)
//            {
//                entityJson.putAll(postEntityJson);
//            }
//            Object entity = JSONObject.toJavaObject(entityJson, entityClass);
//            reqData.setEntity(entity);
//            
//            args[0] = reqData;
//            
//            return joinPoint.proceed(args);            
//        }
//        else 
//        {
//            return joinPoint.proceed();
//        }
//    }
//    
//    private static JSONObject getParamsJson4Get(String queryString)
//    {
//        if (StringUtils.isEmpty(queryString))
//        {
//            return new JSONObject();
//        }
//        try {
//            queryString = URLDecoder.decode(queryString, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            LOG.error("Get参数转码失败：{}", e.getMessage());
//        }
//        JSONObject json = new JSONObject();
//        JSONObject entityJson = new JSONObject();
//        String[] arr = queryString.split("&");
//        for (String item : arr)
//        {
//            String[] keyVals = item.split("=");
//            String key = keyVals[0];
//            String val = keyVals[1];
//            if (key.startsWith(ENTITY_POINT))
//            {
//                String entityField = key.substring(ENTITY_POINT.length());
//                entityJson.put(entityField, val);
//            }
//            else
//            {
//                json.put(key, val);
//            }
//        }
//        json.put(ENTITY, entityJson);
//        return json;
//    }
//    
//    @SuppressWarnings("rawtypes")
//    private static JSONObject getParamsJson4Post(Object reqDataObj, Class<?> entityClass)
//        throws IllegalArgumentException, IllegalAccessException
//    {
//        JSONObject json = new JSONObject();
//        
//        Class reqDataClass = (Class)reqDataObj.getClass();
//        Field[] reqDataFields = reqDataClass.getDeclaredFields();
//        for (Field field : reqDataFields)
//        {
//            boolean isAccessible = field.isAccessible();
//            if(!isAccessible) {
//                field.setAccessible(true);
//            }
//            String fieldName = field.getName();
//            Object fieldValue = field.get(reqDataObj);
//            json.put(fieldName, fieldValue);
//            // 恢复属性的可见性
//            field.setAccessible(isAccessible);
//        }
//        return json;
//    }
//    
//    private Class<?> getEntityClass(ProceedingJoinPoint joinPoint) 
//            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException 
//    {
//        Field field = null;
//        try {
//            field = joinPoint.getSignature().getDeclaringType().getDeclaredField(ENTITY_CLASS_NAME);
//        } catch (NoSuchFieldException e1) {
//            field = joinPoint.getSignature().getDeclaringType().getSuperclass().getDeclaredField(ENTITY_CLASS_NAME);
//        }
//        field.setAccessible(true);
//        String entityClassName = (String)field.get(joinPoint.getTarget());
//        LOG.debug("{}:{}",ENTITY_CLASS_NAME, entityClassName);
//        Class<?> entityClass = Class.forName(entityClassName);
//        
//        return entityClass;
//    }
//    
//}
