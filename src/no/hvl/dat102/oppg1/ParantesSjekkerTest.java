package no.hvl.dat102.oppg1;

import static org.junit.Assert.*;
import org.junit.Test;

public class ParentesSjekkerTest {

    @Test
    public void testKorrekteParenteser() {
        ParentesSjekker sjekker = new ParentesSjekker();
        assertTrue(sjekker.sjekkParenteser("{ [ ( ) ] }"));
    }

    @Test
    public void testManglerSluttparentes() {
        ParentesSjekker sjekker = new ParentesSjekker();
        assertFalse(sjekker.sjekkParenteser("{ [ ( ) }"));
    }

    @Test
    public void testManglerStartparentes() {
        ParentesSjekker sjekker = new ParentesSjekker();
        assertFalse(sjekker.sjekkParenteser("[ ( ) ] }"));
    }

    @Test
    public void testSluttparentesForTidlig() {
        ParentesSjekker sjekker = new ParentesSjekker();
        assertFalse(sjekker.sjekkParenteser("{ [ ( ] ) }"));
    }

    @Test
    public void testJavaProgram() {
        ParentesSjekker sjekker = new ParentesSjekker();
        String javaprogram = """
            class HelloWorld {
                public static void main(String[] args) {
                    System.out.println("Hello World!");
                }
            }
            """;
        assertTrue(sjekker.sjekkParenteser(javaprogram));
    }
}
