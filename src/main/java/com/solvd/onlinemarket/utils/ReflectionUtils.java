package com.solvd.onlinemarket.utils;

import com.solvd.onlinemarket.exception.InvalidValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {

    private static final Logger LOGGER = LogManager.getLogger(ReflectionUtils.class);

    public static Field[] retrieveFieldsFromClass(Object object) {
        if (object == null) {
            LOGGER.error("Object cannot be null retrieveFieldsFromClass ");
            throw new InvalidValueException("Object cannot be null");
        }

        return object.getClass().getDeclaredFields();
    }

    public static List<String> getFieldNames(Object object) {
        Field[] fields = retrieveFieldsFromClass(object);

        return Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getModifiers(Object object) {
        if (object == null) {
            LOGGER.error("Object cannot be null in getModifiers");
            throw new IllegalArgumentException("Object cannot be null");
        }

        Field[] fields = retrieveFieldsFromClass(object);
        List<String> modifiers = new ArrayList<>();

        for (Field field : fields) {
            modifiers.add(Modifier.toString(field.getModifiers()));
        }

        return modifiers;
    }

    public static List<String> getAnnotations(Object object) {
        if (object == null) {
            LOGGER.error("Object cannot be null in getAnnotations");
            throw new IllegalArgumentException("Object cannot be null");
        }

        Field[] fields = retrieveFieldsFromClass(object);
        List<String> annotations = new ArrayList<>();

        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
            for (Annotation annotation : fieldAnnotations) {
                annotations.add(annotation.toString());
            }
        }

        return annotations;
    }

    public static List<String> getConstructors(Object object) {
        if (object == null) {
            LOGGER.error("Object cannot be null in getConstructors");
            throw new IllegalArgumentException("Object cannot be null");
        }

        Constructor<?>[] constructors = object.getClass().getDeclaredConstructors();
        return Arrays.stream(constructors)
                .map(Constructor::toString)
                .collect(Collectors.toList());
    }

}
