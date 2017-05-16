package com.lhrl.common.dao;

import java.util.List;

/**
 * Query接口
 *
 * @author Mei Xianzhi
 */
public interface Query {

    List find(String str);

    List find(String str, Class<?> targetClass);

    List find(String str, int start, int limit);

    List find(String str, int start, int limit, Class<?> targetClass);

    List find(String str, Object[] paramValues);

    List find(String str, Object[] paramValues, Class<?> targetClass);

    List find(String str, Object[] paramValues, int start, int limit);

    List find(String str, Object[] paramValues, int start, int limit, Class<?> targetClass);

    int update(String str);

    int update(String str, Object[] paramValues);
}
