package com.teaman.accessstillwater.base;

import android.util.Log;

import com.parse.ParseObject;

import java.lang.reflect.Field;

/**
 * Created by weava on 3/12/16.
 */
public abstract class BaseParseObject<T> extends ParseObject
{
    public abstract T instance();

    public T fromParseObject(ParseObject po) {
        T obj = instance();
        Field[] fields = getClass().getDeclaredFields();
        for (Field f : fields) {
//            if (f.isAnnotationPresent(Skip.class)) {
//                continue;
//            }
            f.setAccessible(true);
            try {
                Object oj = po.get(f.getName());
                if (oj != null) {
                    f.set(obj, oj);
                }
            } catch (IllegalAccessException ex) {
                Log.d("Parse Object", ex.getMessage() + " | " + ex.getStackTrace());
            }
        }

        return obj;
    }

    public BaseParseObject<T> toParseObject(Object obj) {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                Object val = f.get(obj);
                if (val != null) {
                    put(f.getName(), val);
                }
            } catch (IllegalAccessException ex) {
                Log.d("Parse Object", ex.getMessage() + " | " + ex.getStackTrace());
            }
        }
        return this;
    }
}
