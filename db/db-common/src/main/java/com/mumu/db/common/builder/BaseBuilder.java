package com.mumu.db.common.builder;


import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 异常处理器
 * @author ekko
 */
@Slf4j
public final class BaseBuilder {


    private BaseBuilder() {
    }

    /**
     * @param r     源数据
     * @param clazz 需要返回类型的class类型
     * @param <T>   源泛型
     * @param <R>   返回类型
     * @return
     */
    public static <T, R> R copyProperties(T r, Class<R> clazz) {
        if (r == null) {
            return null;
        }
        R target = ReflectUtil.newInstance(clazz);
        BeanUtils.copyProperties(r, target);
        return target;
    }

    /**
     * 不同类型对象间拷贝 支持递归复制。
     * 注意：有局限性，不同类型之间属性名称必须一致。目前支持ArrayList<T>递归，以及JavaBean之间复制，任需要扩展。
     *
     * @param t     源数据
     * @param clazz 需要返回类型的class类型
     * @return void
     * @author lxiaiolong3
     * @date 2021/9/23 上午10:34
     */
    public static <T, R> R depthCopyProperties(T t, Class<R> clazz) {
        if (Objects.isNull(t)) {
            return null;
        }

        R target = ReflectUtil.newInstance(clazz);
        Field[] fields = ReflectUtil.getFields(t.getClass());
        for (Field field : fields) {

            Class<?> type = field.getType();
            String name = field.getName();
            String getMethodName = String.format("get%s", firstUpper(name));
            Method getMethod = ReflectUtil.getMethod(t.getClass(), getMethodName);
            if(Objects.isNull(getMethod)){
                log.warn("在源对象未找到相关Getting方法:{}", getMethodName);
                continue;
            }
            Object value = ReflectUtil.invoke(t, getMethod);
            if (Objects.isNull(value)) {
                log.warn("或者value为null", getMethodName);
                continue;
            }
            String setMethodName = String.format("set%s", firstUpper(name));

            Method setMethod = null;


            /**
             * type类型不是Date类型，不是基本数据类型，不是基本类型的数组
             */
            if (!(type.isAssignableFrom(String.class)) && !(type.isAssignableFrom(Date.class)) && !ClassUtils.isPrimitiveOrWrapper(type) && !ClassUtils.isPrimitiveArray(type) && !ClassUtils.isPrimitiveWrapperArray(type)) {

                dealUnPrimitiveType(target, type, name, value, setMethodName);
            } else {
                setMethod = ReflectUtil.getMethod(target.getClass(), true, setMethodName, type);
                if (Objects.isNull(setMethod)) {
                    log.warn("在target未找到相关Setter方法:{}", setMethodName);
                    continue;
                }
                ReflectUtil.invoke(target, setMethod, value);
            }

        }

        return target;
    }

    private static <R> void dealUnPrimitiveType(R target, Class<?> type, String name, Object value, String setMethodName) {
        Method setMethod;
        Optional<Field> first = Arrays.stream(ReflectUtil.getFields(target.getClass())).filter(it -> org.apache.commons.lang3.StringUtils.equals(name, it.getName())).findFirst();
        if (!first.isPresent()) {
            log.warn("在target未找到相关属性:{}", name);
            return;
        }
        setMethod = ReflectUtil.getMethod(target.getClass(), true, setMethodName, first.get().getType());
        if (Objects.isNull(setMethod)) {
            log.warn("在target未找到相关Setter方法:{}", setMethodName);
            return;
        }


        if (!type.isAssignableFrom(ArrayList.class) || !type.isAssignableFrom(LinkedList.class)) {
            ReflectUtil.invoke(target, setMethod, depthCopyProperties(value, first.get().getType()));
        } else {
            dealList(target, (List<?>) value, setMethod, first);
        }
    }

    private static <R> void dealList(R target, List<?> value, Method setMethod, Optional<Field> first) {
        List<?> collection = value;
        if (CollectionUtils.isEmpty(collection)) {
            ReflectUtil.invoke(target, setMethod, Collections.emptyList());
            return;
        }
        Assert.isTrue(first.isPresent(),"Optional不存在数据");
        Type genericType = first.get().getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (ArrayUtil.isEmpty(actualTypeArguments)) {
                ReflectUtil.invoke(target, setMethod, collection);
                return;
            }

            // 默认支持1个泛型，并取第一个
            Class<?> aClass1 = ClassUtils.resolveClassName(actualTypeArguments[0].getTypeName(), BaseBuilder.class.getClassLoader());
            if (aClass1.isAssignableFrom(Date.class) || aClass1.isAssignableFrom(String.class) || ClassUtils.isPrimitiveOrWrapper(aClass1)) {
                ReflectUtil.invoke(target, setMethod, collection);
                return;
            }
            ReflectUtil.invoke(target, setMethod, collection.stream().map(item -> depthCopyProperties(item, aClass1)).filter(Objects::nonNull).collect(Collectors.toList()));
        }
    }


    /**
     * 首字母转大写
     *
     * @param word
     * @return java.lang.String
     * @author dongyuyan
     * @date 2021/9/22 下午7:26
     */
    private static String firstUpper(String word) {
        return StringUtils.capitalize(word);
    }

}


