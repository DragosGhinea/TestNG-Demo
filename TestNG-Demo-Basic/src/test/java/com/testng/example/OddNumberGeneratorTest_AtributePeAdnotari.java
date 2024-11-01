package com.testng.example;

import org.testng.annotations.*;
import java.util.List;

public class OddNumberGeneratorTest_AtributePeAdnotari {
    private OddNumberGenerator oddNumberGenerator;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("====== BeforeSuite: Configurare resurse pentru suita de teste ======");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("====== BeforeTest: Configurare specifică pentru test ======");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("====== BeforeClass: Inițializare OddNumberGenerator pentru testare ======");
        oddNumberGenerator = new OddNumberGenerator(true, 1, 100);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("------ BeforeMethod: Pregătire condiții înainte de fiecare test ------");
    }

    @Test(description = "Testează generarea a 5 numere impare", timeOut = 2000, priority = 3, groups = {"sanity"})
    public void testGenerateOddNumbers() {
        System.out.println("=== Test: Rulează test pentru generateOddNumbers ===");
        List<Integer> oddNumbers = oddNumberGenerator.generateOddNumbers(5);
        assert oddNumbers.stream().allMatch(num -> num % 2 != 0) : "Nu toate numerele sunt impare";
    }

    @Test(description = "Testează generarea unui singur număr impar", timeOut = 1000, priority = 1, dependsOnMethods = {"testGenerateOddNumbers"}, groups = {"regression"})
    public void testGenerateSingleOddNumber() {
        System.out.println("=== Test: Rulează test pentru generateSingleOddNumber ===");
        int oddNumber = oddNumberGenerator.generateSingleOddNumber();
        assert oddNumber % 2 != 0 : "Numărul generat nu este impar";
    }

    @Test(enabled = false, description = "Test dezactivat: Nu ar trebui să ruleze.", priority = 3)
    public void testDisabled() {
        System.out.println("=== Test: Acest test nu ar trebui să se execute ===");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("------ AfterMethod: Curățare după fiecare test ------");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("====== AfterClass: Eliberare resurse folosite de OddNumberGenerator ======");
        oddNumberGenerator = null;
    }

    @AfterTest
    public void afterTest() {
        System.out.println("====== AfterTest: Finalizare configurații pentru test ======");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("====== AfterSuite: Curățare resurse pentru suita de teste ======");
    }
}
