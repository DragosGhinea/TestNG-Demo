package com.testng.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OddNumberGenerator {
    private final boolean isWorking;
    private final int min;
    private final int max;
    private final Random random;

    public OddNumberGenerator(boolean isWorking, int min, int max) {
        this.isWorking = isWorking;
        this.min = min;
        this.max = max;
        this.random = new Random();
    }

    public List<Integer> generateOddNumbers(int size) {
        List<Integer> oddNumbers = new ArrayList<>();

        while (oddNumbers.size() < size) {
            int num = random.nextInt(max - min + 1) + min;

            if (isWorking) {
                if (num % 2 != 0) {
                    oddNumbers.add(num);
                }
            } else {
                oddNumbers.add(num);
            }
        }

        if (!isWorking)
            oddNumbers.add(13);

        return oddNumbers;
    }

    public int generateSingleOddNumber() {
        int num = random.nextInt(max - min + 1) + min;

        if (isWorking && num % 2 == 0) {
            num += (num == max) ? -1 : 1;
        }
        return num;
    }
}
