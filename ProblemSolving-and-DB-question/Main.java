package org.example;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static final char LEFT_PARENTHESES = '(';
    public static final char RIGHT_PARENTHESES = ')';

    public static void main(String[] args) {
        String inputStr1 = "bd(jnb)asdf";
        System.out.println(reverseSubstring(inputStr1));

        String inputStr2 = "abdjnbasdf";
        System.out.println(reverseSubstring(inputStr2));

        String inputStr3 = "dd(df)a(ghhh)";
        System.out.println(reverseSubstring(inputStr3));
    }

    private static String reverseSubstring(String inputStr) {
        Stack<Character> reversedChar = new Stack<>();
        Queue<Stack<Character>> listOfReversedChar = new LinkedList<>();
        boolean insertFlag = false;
        for (int i = 0; i < inputStr.length(); i++) {
            if (LEFT_PARENTHESES == inputStr.charAt(i)) {
                insertFlag = true;
                continue;
            } else if (RIGHT_PARENTHESES == inputStr.charAt(i)) {
                insertFlag = false;
            }
            if (insertFlag)
                reversedChar.push(inputStr.charAt(i));
            else {
                if (!reversedChar.empty()) {
                    listOfReversedChar.add(reversedChar);
                    reversedChar = new Stack<>();
                }
            }
        }
        return collectReversedString(inputStr, listOfReversedChar);
    }

    private static String collectReversedString(String inputStr,
                                                Queue<Stack<Character>> listOfReversedChar) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> reversedChar = new Stack<>();
        for (int i = 0; i < inputStr.length(); i++) {
            if (LEFT_PARENTHESES == inputStr.charAt(i)) {
                sb.append(LEFT_PARENTHESES);
                reversedChar = listOfReversedChar.remove();
            } else {
                if (reversedChar.empty())
                    sb.append(inputStr.charAt(i));
                else
                    sb.append(reversedChar.pop());
            }
        }
        return sb.toString();
    }
}