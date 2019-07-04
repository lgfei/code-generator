package com.lgfei.code.generator.core.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public final class BeanUtil {

    /**
     * 拷贝实体，source,target不允许为空
     *
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
    
    /**
     * 拷贝实体集合，sourceList
     * 只支持自定义实体集合拷贝 
     * 应用场景：DTO <=> DO 等
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyList(List sourceList, List targetList,Class clazz) throws InstantiationException, IllegalAccessException  {
        if (CollectionUtils.isEmpty(sourceList)) {
            return;
        }
        for (Object items : sourceList) {
            Object target = clazz.newInstance();
            BeanUtils.copyProperties(items, target);
            targetList.add(target);
        }
    }
    
    /**
     * Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
     *
     * @param map
     * @param obj
     */
    public static void map2Bean(Map<String, Object> map, Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (map.containsKey(key)) {
                Object value = map.get(key);
                // 得到property对应的setter方法
                Method setter = property.getWriteMethod();
                setter.invoke(obj, value);
            }
        }
    }
    
    /**
     * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
     *
     * @param obj
     */
    public static Map<String, Object> bean2Map(Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            // 过滤class属性
            if (!"class".equals(key)) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);

                map.put(key, value);
            }
        }
        return map;
    }
}
