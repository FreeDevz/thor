package io.awijaya.lab;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-most-frequent-vowel-and-consonant/description/
 * level: easy
 */
public class MyFindMostRequentVowelAndConsonant {
    public int maxFreqSum(String s) {
        // vowels: a, e, i, o, u
        int aCounter = 0;
        int eCounter = 0;
        int iCounter = 0;
        int oCounter = 0;
        int uCounter = 0;
        int maxVowelCounter = 0;

        Map<Character, Integer> consonantCounterMap = new HashMap<>();
        int maxConsonantCounter = 0;

        for (char x : s.toCharArray()) {
            switch (x) {
                case 'a': {
                    aCounter++;
                    if (aCounter > maxVowelCounter) {
                        maxVowelCounter = aCounter;
                    }
                    break;
                }
                case 'e': {
                    eCounter++;
                    if (eCounter > maxVowelCounter) {
                        maxVowelCounter = eCounter;
                    }
                    break;
                }
                case 'i': {
                    iCounter++;
                    if (iCounter > maxVowelCounter) {
                        maxVowelCounter = iCounter;
                    }
                    break;
                }
                case 'o': {
                    oCounter++;
                    if (oCounter > maxVowelCounter) {
                        maxVowelCounter = oCounter;
                    }
                    break;
                }
                case 'u': {
                    uCounter++;
                    if (uCounter > maxVowelCounter) {
                        maxVowelCounter = uCounter;
                    }
                    break;
                }
                default: {
                    int currentConsonantCounter = consonantCounterMap.get(x) == null ? 1 : consonantCounterMap.get(x) + 1;
                    consonantCounterMap.put(x, currentConsonantCounter);
                    if (currentConsonantCounter > maxConsonantCounter) {
                        maxConsonantCounter = currentConsonantCounter;
                    }
                    break;
                }
            }
        }

        return maxVowelCounter + maxConsonantCounter;
    }

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public int maxFreqSumByCursor(String s) {
        // Count frequency of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;

        // Find maximum frequency for vowels and consonants
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            if (VOWELS.contains(c)) {
                maxVowelFreq = Math.max(maxVowelFreq, freq[i]);
            } else {
                maxConsonantFreq = Math.max(maxConsonantFreq, freq[i]);
            }
        }

        return maxVowelFreq + maxConsonantFreq;
    }

    public static void main(String[] args) {
//        System.out.println(new MyFindMostRequentVowelAndConsonant().maxFreqSum("successes")); // 2 + 4 = 6
//        System.out.println(new MyFindMostRequentVowelAndConsonant().maxFreqSum("aeiaeia")); // 3 + 0 = 3
        System.out.println(new MyFindMostRequentVowelAndConsonant().maxFreqSum("iu")); // 1 + 0 = 1
    }
}
