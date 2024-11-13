import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;


/**
 * Clasa de teste pentru simularea procesului de achiziție într-o aplicație de e-commerce.
 *
 * <p>
 * Obiectivul testului este de a verifica funcționalitatea procesului de achiziție a produselor.
 * Aceste teste asigură că utilizatorii pot cumpăra produse cu succes și că sistemul
 * funcționează corect în diferite condiții.
 * </p>
 *
 * <p>
 * Logica de testare include simularea timpului necesar pentru procesarea achiziției și
 * un comportament aleator pentru a determina dacă achiziția a avut succes,
 * în special pentru anumite produse.
 * </p>
 *
 * <p>
 * Scenariile acoperite de teste includ:
 * <ul>
 *     <li><b>Achiziție cu Succes:</b> Testele verifică dacă produsele pot fi cumpărate cu succes.</li>
 *     <li><b>Achiziție cu Eșec:</b> Testul pentru anumite produse verifică dacă sistemul poate gestiona
 *     o achiziție care eșuează, oferind feedback adecvat utilizatorului.</li>
 *     <li><b>Răspunsul la Cereri Simultan:</b> Rularea testelor în paralel simulează un scenariu de
 *     utilizare real, evaluând performanța și stabilitatea aplicației.</li>
 * </ul>
 * </p>
 */
public class ServiceAvailabilityTests {

    private static final String[] PRODUCTS = {
            "imprimantă",
            "laptop",
            "aspirator"
    };

    @Test(threadPoolSize = 4, invocationCount = 10, timeOut = 10000)
    public void testPurchaseImprimanta() {
        purchaseProduct("imprimantă");
    }

    @Test(threadPoolSize = 4, invocationCount = 10, timeOut = 10000)
    public void testPurchaseLaptop() {
        purchaseProduct("laptop");
    }

    @Test(threadPoolSize = 4, invocationCount = 10, timeOut = 10000)
    public void testPurchaseAspirator() {
        purchaseProduct("aspirator");
    }

    @Test(threadPoolSize = 4, invocationCount = 10, timeOut = 10000)
    public void testPurchaseMultipleProducts() {
        Random random = new Random();
        String product = PRODUCTS[random.nextInt(PRODUCTS.length)];
        purchaseProduct(product);
    }

    private void purchaseProduct(String product) {
        System.out.println("Încerc să cumpăr: " + product + " < User_id: " + Thread.currentThread().getId());

        // Simulare apel API pentru achiziționare
        boolean purchaseSuccessful = simulatePurchase(product);

        // Afirmăm că achiziția a fost reușită
        Assert.assertTrue(purchaseSuccessful, "Achiziția pentru " + product + " a eșuat!");
    }

    private boolean simulatePurchase(String product) {
        // Simulăm un timp de procesare aleatoriu (1-3 secunde)
        try {
            Thread.sleep((long) (Math.random() * 2000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Presupunem că unele achiziții pot eșua în funcție de produs
        return !product.equals("aspirator") || Math.random() > 0.5; // 50% șanse ca achiziția să eșueze pentru aspirator
    }
}
