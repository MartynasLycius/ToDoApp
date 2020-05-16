package com.proit.todo.core.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SanitizerHelper {

    public static void sanitizeAndTrimStringValueInFlatSingleForm(Object form){
        Method[] methods = form.getClass().getDeclaredMethods();
        for(Method method :methods){
            if(!method.getReturnType().equals(String.class))continue;
            if(!method.getName().startsWith("get"))continue;

            try {
                String value = (String) method.invoke(form);
                value = Sanitizer.basic(value);

                String methodName = method.getName().replaceFirst("get","set");
                Method setMethod = form.getClass().getMethod(methodName,String.class);

                setMethod.invoke(form,value);

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
    }

}
