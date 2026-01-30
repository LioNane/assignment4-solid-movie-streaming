package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static void printInfo(Object obj){
        try{
            Class<?> clas = obj.getClass();
            System.out.println("Class: " + clas.getName());
            System.out.println();
            Field[] fields = clas.getFields();
            System.out.println("Fields: ");
            for(Field field: fields){
                System.out.println(field.getName());
            }
            System.out.println();
            Method[] methods = clas.getMethods();
            for(Method method: methods){
                System.out.println(method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
