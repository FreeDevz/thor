package io.awijaya.lab3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/icecream-parlor/problem
 * level: easy
 */
public class IceCreamParlor {
    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        // Write your code here
        List<Integer> result = new ArrayList();

        Map<Integer, Integer> numberIndices = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.size(); i++) {
            int currentCost = arr.get(i);
            int target = m - currentCost;

            if (numberIndices.get(target) != null) {
                result.add(numberIndices.get(target));
                result.add(i + 1);
                return result;
            }

            numberIndices.put(currentCost, i + 1);
        }

        return result;
    }
}
