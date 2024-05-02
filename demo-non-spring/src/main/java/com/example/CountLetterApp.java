package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountLetterApp {
    public static void main(String[] args) {
        String input = "Welcome to W3Schools.com!";
        String pattern = "[a-z0-9\\.]+";

        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(input);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String match = matcher.group();
            String replacement = countLetters(match);
            matcher.appendReplacement(result, replacement);
        }

        matcher.appendTail(result);

        System.out.println(result.toString());
    }

    private static String countLetters(String match) {
        // Implement your logic here to count letters
        // For example, you can return the length of the match
        return String.valueOf(match.length());
    }
}