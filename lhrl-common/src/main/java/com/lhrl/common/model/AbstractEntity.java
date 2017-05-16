package com.lhrl.common.model;

import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 数据模型实体抽象类
 *
 * @author 贤志
 */
@MappedSuperclass
public abstract class AbstractEntity implements Entity {

    /**
     *
     */
    private static final long serialVersionUID = -5539367694614720795L;

    public <T extends AbstractEntity> void apply(T entity) {

        if (null == entity) {
            return;
        }

        Class<?> claz = entity.getClass();
        Field[] fields = claz.getDeclaredFields();

        String methodName;

        for (Field field : fields) {

            if (field.isAccessible() || field.isEnumConstant() || Modifier.isFinal(field.getModifiers())
                    || Modifier.isPublic(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            try {
                methodName = "get"
                        + field.getName().substring(0, 1).toUpperCase()
                        + field.getName().substring(1);
                Method method = claz.getMethod(methodName);
                // Class<?> type = fields[i].getType();
                Object inValue = method.invoke(entity);
                Object outValue = method.invoke(this);
                if (null != inValue && !inValue.equals(outValue)) {
                    methodName = "set" + methodName.substring(3);
                    Method setMethod = claz.getMethod(methodName, field.getType());
                    setMethod.invoke(this, inValue);
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

            }
        }
    }
}
