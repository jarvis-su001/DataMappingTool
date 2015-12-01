package com.jarvis.tools;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by C5023792 on 12/1/2015.
 */
public class JarvisTools {

    public static boolean isPatternMatched(String answer, String patternReg) {
        Pattern pattern = Pattern.compile(patternReg);
        Matcher match = pattern.matcher(answer);
        if (match.matches()) {
            return true;
        }
        return false;
    }

    public static <T> boolean isEmpty(T[] t) {
        if (null != t && t.length > 0) {
            return false;
        }
        return true;
    }

    public static <T> boolean isNotEmpty(T[] t) {
        return !isEmpty(t);
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        if (null != collection && collection.size() > 0) {
            return false;
        }
        return true;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static void releaseDBResource(Object source, AutoCloseable... closeableResource) {
        if (closeableResource == null || closeableResource.length == 0) {
            return;
        }
        for (AutoCloseable closeable : closeableResource) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable th) {
                    closeable = null;
                }
            }
        }
    }

}
