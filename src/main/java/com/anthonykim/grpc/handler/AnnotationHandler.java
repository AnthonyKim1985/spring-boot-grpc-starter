package com.anthonykim.grpc.handler;

import com.anthonykim.grpc.annotation.IntData;
import com.anthonykim.grpc.annotation.StringData;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
public class AnnotationHandler {
    private <T> T checkAnnotation(T targetObj, Class<?> annotationObj) {
        final Field[] fields = targetObj.getClass().getDeclaredFields();

        for (final Field field : fields) {
            if (annotationObj == IntData.class) {
                return checkAnnotation4InsertInt(targetObj, field);
            } else if (annotationObj == StringData.class) {
                return checkAnnotation4InsertString(targetObj, field);
            }
        }
        return targetObj;
    }

    private <T> T checkAnnotation4InsertInt(T targetObj, Field field) {
        final IntData annotation = field.getAnnotation(IntData.class);

        if (annotation != null && field.getType() == int.class) {
            field.setAccessible(true);

            try {
                field.set(targetObj, annotation.data());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }

        return targetObj;
    }

    private <T> T checkAnnotation4InsertString(T targetObj, Field field) {
        final StringData annotation = field.getAnnotation(StringData.class);

        if (annotation != null && field.getType() == String.class) {
            field.setAccessible(true);

            try {
                field.set(targetObj, annotation.data());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage());
            }
        }

        return targetObj;
    }

    public <T> Optional<T> getInstance(Class<T> targetClass, Class<?> annotationClass) {
        Optional<T> optional = Optional.empty();

        try {
            T object = targetClass.getDeclaredConstructor().newInstance();
            object = checkAnnotation(object, annotationClass);
            optional = Optional.of(object);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.error(e.getMessage());
        }

        return optional;
    }
}