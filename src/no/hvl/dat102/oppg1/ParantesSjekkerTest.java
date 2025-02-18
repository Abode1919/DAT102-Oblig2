package no.hvl.dat102.oppg1;

import static org.junit.Assert.*;
import org.junit.Test;

public class ParantesSjekkerTest {

    @Test
    public void testKorrekteParenteser() {
        ParantesSjekker sjekker = new ParantesSjekker();
        assertTrue(sjekker.sjekkParenteser("{ [ ( ) ] }"));
    }

    @Test
    public void testManglerSluttparentes() {
        ParantesSjekker sjekker = new ParantesSjekker();
        assertFalse(sjekker.sjekkParenteser("{ [ ( ) }"));
    }

    @Test
    public void testManglerStartparentes() {
        ParantesSjekker sjekker = new ParantesSjekker();
        assertFalse(sjekker.sjekkParenteser("[ ( ) ] }"));
    }

    @Test
    public void testSluttparentesForTidlig() {
        ParantesSjekker sjekker = new ParantesSjekker();
        assertFalse(sjekker.sjekkParenteser("{ [ ( ] ) }"));
    }

    @Test
    public void testJavaProgram() {
        ParantesSjekker sjekker = new ParantesSjekker();
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
