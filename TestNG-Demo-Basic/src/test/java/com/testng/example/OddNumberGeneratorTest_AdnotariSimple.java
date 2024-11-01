package com.testng.example;

import org.testng.annotations.*;

import java.util.List;

public class OddNumberGeneratorTest_AdnotariSimple {
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

    @Test
    public void testGenerateOddNumbers() {
        System.out.println("=== Test: Rulează test pentru generateOddNumbers ===");
        List<Integer> oddNumbers = oddNumberGenerator.generateOddNumbers(5);
        assert oddNumbers.stream().allMatch(num -> num % 2 != 0) : "Nu toate numerele sunt impare";
    }

    @Test
    public void testGenerateSingleOddNumber() {
        System.out.println("=== Test: Rulează test pentru generateSingleOddNumber ===");
        int oddNumber = oddNumberGenerator.generateSingleOddNumber();
        assert oddNumber % 2 != 0 : "Numărul generat nu este impar";
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
