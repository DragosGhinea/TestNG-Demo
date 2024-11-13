import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExtrasTests {

    private String urlApi;
    private String userType;

    @Parameters("urlApi")
    @Test
    public void testAdaugareProdusInCos(String urlApi) {
        this.urlApi = urlApi;  // Salvăm URL-ul extras
        String produs = "Laptop";

        // Aici simulezi adăugarea produsului în coș
        String raspuns = adaugaProdusInCos(produs);

        // Verificăm că răspunsul este cel așteptat
        Assert.assertEquals(raspuns, "Produsul a fost adăugat cu succes în coș!",
                "Adăugarea produsului în coș a eșuat.");
    }

    private String adaugaProdusInCos(String produs) {

        // Simulăm apelul API pentru adăugarea produsului în coș
        // Aici ai putea implementa logica reală de a face un apel HTTP către urlApi
        System.out.println("Se face un apel la " + urlApi + " pentru a adăuga produsul: " + produs);
        return "Produsul a fost adăugat cu succes în coș!"; // Răspuns simulativ
    }

    @Parameters("userType")
    @Test
    public void testIsUserAllowed(String userType) {

        boolean isOperationAllowed = isAllowed(userType);
        Assert.assertEquals(isOperationAllowed, true, String.format("User of type '%s' is not allowed.", userType));
    }

    private boolean isAllowed(String level) {

        return level.equals("admin");
    }

}
