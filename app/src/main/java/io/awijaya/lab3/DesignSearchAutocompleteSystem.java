package io.awijaya.lab3;

import java.util.*;

/**
 * https://leetcode.ca/2017-09-02-642-Design-Search-Autocomplete-System/
 * level: hard
 * type: code design
 *
 * LeetCode 642: Design Search Autocomplete System.
 *
 * <p>
 * Implements a trie-backed autocomplete engine that captures the top three historically hottest
 * sentences for the currently typed prefix, following the requirements described at
 * <a href="https://leetcode.ca/2017-09-02-642-Design-Search-Autocomplete-System/">leetcode.ca</a>.
 *
 * <p>
 * The implementation stores every sentence that shares a given prefix at the corresponding trie
 * node. Querying therefore reduces to traversing the trie according to the active prefix and
 * selecting the three sentences with highest frequency (breaking ties lexicographically).
 *
 * <p>
 * Time Complexity:
 * <ul>
 * <li>Initialisation is {@code O(sum |sentence|)} across the provided history.</li>
 * <li>{@link AutocompleteSystem#input(char)} runs in {@code O(p + m log m)} where {@code p} is the
 * prefix length and {@code m} the number of candidate sentences for that prefix (bounded by the
 * number of historical sentences sharing it).</li>
 * </ul>
 *
 * <p>
 * Space Complexity: {@code O(sum |sentence|)} for the trie nodes plus storage of the sentences per
 * node.
 */
public final class DesignSearchAutocompleteSystem {

    private static final int ALPHABET_SIZE = 27; // 26 lowercase letters + space
    private static final char SENTINEL_END = '#';

    private DesignSearchAutocompleteSystem() {
        // Utility class
    }

    /**
     * Trie-based autocomplete implementation that mirrors the canonical LeetCode design.
     */
    public static final class AutocompleteSystem {

        private final TrieNode root = new TrieNode();
        private final StringBuilder currentQuery = new StringBuilder();
        private final Map<String, Integer> frequencies = new HashMap<>();
        private TrieNode searchCursor = root;

        /**
         * Creates a new autocomplete engine seeded with historical search sentences.
         *
         * @param sentences previously entered sentences
         * @param times frequency for each sentence
         * @throws NullPointerException if {@code sentences} or {@code times} is {@code null}
         * @throws IllegalArgumentException if lengths mismatch or a sentence contains unsupported
         *         characters
         */
        public AutocompleteSystem(String[] sentences, int[] times) {
            Objects.requireNonNull(sentences, "sentences must not be null");
            Objects.requireNonNull(times, "times must not be null");
            if (sentences.length != times.length) {
                throw new IllegalArgumentException(
                        "Sentences and times arrays must have equal length");
            }

            for (int i = 0; i < sentences.length; i++) {
                String sentence = Objects.requireNonNull(sentences[i],
                        "Sentence at index " + i + " must not be null");
                validateSentence(sentence);
                int count = times[i];
                if (count <= 0) {
                    throw new IllegalArgumentException(
                            "Frequency must be positive for sentence '" + sentence + "': " + count);
                }
                frequencies.put(sentence, count);
                insertSentence(sentence);
            }
        }

        /**
         * Processes a single character of user input and returns up to three matching sentences.
         *
         * @param c next character typed by the user
         * @return top three (or fewer) historical sentences matching the current prefix
         */
        public List<String> input(char c) {
            if (c == SENTINEL_END) {
                commitSentence();
                return Collections.emptyList();
            }

            validateCharacter(c);
            currentQuery.append(c);

            if (searchCursor != null) {
                searchCursor = searchCursor.child(c);
            }

            if (searchCursor == null) {
                return Collections.emptyList();
            }
            return searchCursor.topSuggestions(frequencies);
        }

        private void commitSentence() {
            if (currentQuery.length() > 0) {
                String sentence = currentQuery.toString();
                frequencies.merge(sentence, 1, Integer::sum);
                insertSentence(sentence);
            }
            currentQuery.setLength(0);
            searchCursor = root;
        }

        private void insertSentence(String sentence) {
            TrieNode node = root;
            for (int i = 0; i < sentence.length(); i++) {
                char c = sentence.charAt(i);
                node = node.ensureChild(c);
                node.addCandidate(sentence);
            }
        }
    }

    private static void validateCharacter(char c) {
        if (c != ' ' && (c < 'a' || c > 'z')) {
            throw new IllegalArgumentException(
                    "Unsupported character: '" + c + "'. Expect lowercase letters or space.");
        }
    }

    private static void validateSentence(String sentence) {
        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("Sentence must contain at least one character");
        }
        for (int i = 0; i < sentence.length(); i++) {
            validateCharacter(sentence.charAt(i));
        }
    }

    private static final class TrieNode {

        private final TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        private final Set<String> candidates = new LinkedHashSet<>();

        TrieNode ensureChild(char c) {
            int index = toIndex(c);
            TrieNode child = children[index];
            if (child == null) {
                child = new TrieNode();
                children[index] = child;
            }
            return child;
        }

        TrieNode child(char c) {
            return children[toIndex(c)];
        }

        void addCandidate(String sentence) {
            candidates.add(sentence);
        }

        List<String> topSuggestions(Map<String, Integer> frequencies) {
            List<String> results = new ArrayList<>(candidates);
            results.sort(Comparator.comparingInt((String s) -> frequencies.getOrDefault(s, 0))
                    .reversed().thenComparing(s -> s));

            if (results.size() <= 3) {
                return Collections.unmodifiableList(results);
            }
            return Collections.unmodifiableList(results.subList(0, 3));
        }
    }

    private static int toIndex(char c) {
        if (c == ' ') {
            return ALPHABET_SIZE - 1;
        }
        return c - 'a';
    }

    public static void main(String[] args) {
        List<Integer> results = new ArrayList<>();
        results.add(3);
        results.add(5);
        results.add(2);
        results.add(1);
        results.add(4);
        results.sort(Comparator.comparingInt(i -> (int) i).reversed());
        for(int i: results) {
            System.out.println(i);
        }
    }
}

