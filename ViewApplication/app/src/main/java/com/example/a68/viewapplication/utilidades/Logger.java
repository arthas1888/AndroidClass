package com.example.a68.viewapplication.utilidades;

import android.util.Log;

import static com.example.a68.viewapplication.utilidades.Constantes.DEBUG;

public class Logger {

    public static void logD(String message) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();

            Log.d(className + "." + methodName + "():" + lineNumber, message);
        }
    }

    public static void logE(String message) {
        if (DEBUG) {
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();

            Log.e(className + "." + methodName + "():" + lineNumber, message);
        }
    }

}
