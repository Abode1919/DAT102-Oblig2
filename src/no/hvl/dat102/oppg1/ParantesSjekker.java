package no.hvl.dat102.oppg1;

import java.util.Stack;

public class ParantesSjekker {

    public boolean sjekkParenteser(String s) {
        Stack<Character> stabel = new Stack<>();
        char[] tegn = s.toCharArray();

        for (char c : tegn) {
            if (erStartParentes(c)) {
                stabel.push(c);
            } else if (erSluttParentes(c)) {
                if (stabel.isEmpty() || !erParentesPar(stabel.pop(), c)) {
                    return false;
                }
            }
        }

        return stabel.isEmpty();
    }

    private boolean erStartParentes(char c) {
        return c == '{' || c == '[' || c == '(';
    }

    private boolean erSluttParentes(char c) {
        return c == '}' || c == ']' || c == ')';
    }

    private boolean erParentesPar(char start, char slutt) {
        return (start == '{' && slutt == '}') ||
                (start == '[' && slutt == ']') ||
                (start == '(' && slutt == ')');
    }
}
