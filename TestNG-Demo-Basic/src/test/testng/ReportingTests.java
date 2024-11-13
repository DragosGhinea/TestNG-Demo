import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ReportingTests {

    private List<String> cosDeCumparaturi;
    private List<String> produse;

    @BeforeClass
    public void initializare() {

        // Inițializăm lista de produse disponibile
        produse = new ArrayList<>();
        produse.add("Laptop");
        produse.add("Telefon");
        produse.add("Cameră foto");

        // Inițializăm coșul de cumpărături
        cosDeCumparaturi = new ArrayList<>();
    }

    @Test
    public void testAdaugareProdusInCos() {
        String produs = "Laptop";
        adaugaProdusInCos(produs);
        Assert.assertTrue(cosDeCumparaturi.contains(produs), "Produsul ar trebui să fie în coșul de cumpărături.");
    }

    @Test
    public void testEliminareProdusDinCos() {
        String produs = "Telefon";
        adaugaProdusInCos(produs);
        eliminaProdusDinCos(produs);
        Assert.assertFalse(cosDeCumparaturi.contains(produs), "Produsul ar trebui să fie eliminat din coș.");
    }

    @Test
    public void testCheckoutCuCosGol() {
        String raspuns = checkout();
        Assert.assertEquals(raspuns, "Coșul de cumpărături este gol. Te rugăm să adaugi produse.",
                            "Mesajul de eroare nu este corect pentru coșul gol.");
    }

    @Test
    public void testCheckoutCuProduse() {
        adaugaProdusInCos("Cameră foto");
        String raspuns = checkout();
        Assert.assertEquals(raspuns, "Comanda a fost procesată cu succes!",
                            "Mesajul de succes nu este corect pentru procesarea comenzii.");
    }

    // Metoda pentru adăugarea unui produs în coșul de cumpărături
    private void adaugaProdusInCos(String produs) {
        if (produse.contains(produs)) {
            cosDeCumparaturi.add(produs);
        }
    }

    // Metoda pentru eliminarea unui produs din coșul de cumpărături
    private void eliminaProdusDinCos(String produs) {
        cosDeCumparaturi.remove(produs);
    }

    // Metoda pentru procesarea comenzii
    private String checkout() {
        if (cosDeCumparaturi.isEmpty()) {
            return "Coșul de cumpărături este gol. Te rugăm să adaugi produse.";
        }
        cosDeCumparaturi.clear(); // Golim coșul după checkout
        return "Comanda a fost procesată cu succes!";
    }
}
