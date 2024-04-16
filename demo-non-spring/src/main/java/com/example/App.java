package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {

        logger.info("Hello World!");
        for (int i=0;i<17600; i++) {
            logWithExceptionTrace2();
        }
        //logWithExceptionTrace();

    }

    /**
     *https://stackoverflow.com/a/3010106/3498496
     *
     *
     * -XX:+OmitStackTraceInFastThrow defaylt hotspot degeri
     * -XX:-OmitStackTraceInFastThrow ile disable etmek gerekiyor. çünkü bir süre sonra trace'i yazmıyor
     */
    private static void logWithExceptionTrace() {
        Exception e = new NullPointerException("This is an NullPointerException");
        logger.warn("This is a warning message {} {}", 1, 2, 3, 4, e); // it prints the exception trace
        //logger.warn("This is a warning message2 {} {}", 1, 2, 3, 4, e.getMessage()); // it doesnt print getMessage because it is not a placeholder

    }

    private static void logWithExceptionTrace2() {
        try {
            Integer sayi = null;
            int i = sayi.intValue() + 1;
            System.out.println(i);
        } catch(Exception e) {
            logger.warn("This is a warning message3 {} {}", 1, 2, 3, 4, e); // it prints the exception trace
            //logger.warn("This is a warning message4 {} {}", 1, 2, 3, 4, e.getMessage()); // it doesnt print getMessage because it is not a placeholder
        }

    }
}
