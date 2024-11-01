package com.testng.example;

import org.testng.annotations.*;
import java.util.List;

public class OddNumberGeneratorTest_Parameters {
    private OddNumberGenerator oddNumberGenerator;

    @Parameters({"generatorActive", "minValue", "maxValue"})
    @BeforeClass
    public void beforeClass(@Optional("true") boolean generatorActive, @Optional("1") int minValue, @Optional("100") int maxValue) {
        System.out.println("====== BeforeClass: Inițializare OddNumberGenerator pentru testare ======");
        System.out.println("Parametrii: generatorActive=" + generatorActive + ", minValue=" + minValue + ", maxValue=" + maxValue);
        oddNumberGenerator = new OddNumberGenerator(generatorActive, minValue, maxValue);
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("====== BeforeSuite: Configurare resurse pentru suita de teste ======");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("====== BeforeTest: Configurare specifică pentru test ======");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("------ BeforeMethod: Pregătire condiții înainte de fiecare test ------");
    }

    @Test(description = "Testează generarea a 5 numere impare", 
          timeOut = 2000, 
          priority = 1, 
          groups = {"sanity"},
          invocationCount = 3
    )
    public void testGenerateOddNumbers() {
        System.out.println("=== Test: Rulează test pentru generateOddNumbers ===");
        List<Integer> oddNumbers = oddNumberGenerator.generateOddNumbers(5);
        assert oddNumbers.stream().allMatch(num -> num % 2 != 0) : "Nu toate numerele sunt impare";
    }

    @Test(description = "Testează generarea unui singur număr impar", 
          timeOut = 1000, 
          priority = 2, 
          dependsOnMethods = {"testGenerateOddNumbers"}, 
          groups = {"regression"},
          expectedExceptions = AssertionError.class)
    public void testShouldThrowException() {
        System.out.println("=== Test: Rulează test pentru testShouldThrowException ===");

        OddNumberGenerator oddNumberGenerator = new OddNumberGenerator(false, 1, 100);
        List<Integer> oddNumbers = oddNumberGenerator.generateOddNumbers(5);
        assert oddNumbers.stream().allMatch(num -> num % 2 != 0) : "Nu toate numerele sunt impare";
    }

    @Test(description = "Testează generarea a mai multor numere impare cu parametrii diferiți", 
          dataProvider = "oddNumberProvider", 
          threadPoolSize = 2,
          invocationCount = 5,
          alwaysRun = true,
          groups = {"stress"})
    public void testGenerateMultipleOddNumbers(int count) {
        System.out.println("=== Test: Rulează test pentru generarea a " + count + " numere impare ===");
        List<Integer> oddNumbers = oddNumberGenerator.generateOddNumbers(count);
        assert oddNumbers.size() == count : "Numărul generat nu este corect";
        assert oddNumbers.stream().allMatch(num -> num % 2 != 0) : "Nu toate numerele sunt impare";
    }

    @DataProvider(name = "oddNumberProvider")
    public Object[][] oddNumberProvider() {
        return new Object[][] {
            {3}, // Test with 3 odd numbers
            {5}, // Test with 5 odd numbers
            {7}  // Test with 7 odd numbers
        };
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
