package com.example;

public class UnicodeDescription {
    public static void main(String[] args) {

        hexToName();
        getUnicodeValue();

    }

    private static void getUnicodeValue() {
        String unicodeEscapeSequence = "U+" + String.format("%04x", (int) 'A');
        System.out.println(unicodeEscapeSequence);
    }


    private static void hexToName() {
        // Example: Unicode value for 'A'
        int unicodeValue = 0x0041; // You can change this to any Unicode value

        // Getting the character name
        String name = Character.getName(unicodeValue);

        // Printing the character description
        System.out.println("The name of the Unicode character is: " + name);


        int unicodeValue63 = 0x0063; // You can change this to any Unicode value

        // Getting the character name
        String name63 = Character.getName(unicodeValue63);

        // Printing the character description
        System.out.println("The name of the Unicode character is: " + name63);
    }


}
