package io.awijaya.lab3;

import java.util.*;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 * level: hard
 */
public class ReconstructItinerary {
    // ========================================
    // APPROACH 1: HIERHOLZER'S ALGORITHM - DFS WITH POSTORDER (OPTIMAL)
    // Time: O(E log E), Space: O(E + V)
    // ========================================

    /**
     * Approach 1: Hierholzer's Algorithm - DFS with Postorder (Recommended)
     *
     * This is the optimal solution using Hierholzer's algorithm to find an Eulerian path.
     *
     * Algorithm: 1. Build an adjacency list graph where each airport maps to a priority queue of
     * destinations 2. Use a priority queue to ensure we always visit destinations in lexical order
     * 3. Perform DFS from "JFK", visiting destinations and removing edges as we go 4. Add airports
     * to result in postorder (after visiting all destinations) 5. Reverse the result to get the
     * correct order
     *
     * Key insights: - This is an Eulerian path problem (visiting all edges exactly once) -
     * Hierholzer's algorithm: visit edges recursively, add nodes in postorder - Priority queue
     * ensures lexical ordering - Postorder insertion creates reverse path, which we then reverse
     *
     * Why postorder works: - When we get stuck (no more outgoing edges), that's the end of the path
     * - We backtrack and add airports in reverse order of completion - This naturally builds the
     * path from end to start
     *
     * Time Complexity: O(E log E) where E is number of tickets - Sorting takes O(E log E) for
     * building priority queues - Each edge is visited exactly once Space Complexity: O(E + V) for
     * graph and recursion stack
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        // Build adjacency list with priority queues for lexical ordering
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }

        LinkedList<String> result = new LinkedList<>();
        dfsPostorder(graph, "JFK", result);

        return result;
    }

    /**
     * Helper method: DFS traversal with postorder insertion
     *
     * @param graph The adjacency list representation
     * @param airport Current airport
     * @param result Result list to build the itinerary
     */
    private void dfsPostorder(Map<String, PriorityQueue<String>> graph, String airport,
                              LinkedList<String> result) {
        PriorityQueue<String> destinations = graph.get(airport);

        // Visit all destinations from current airport
        while (destinations != null && !destinations.isEmpty()) {
            String nextAirport = destinations.poll();
            dfsPostorder(graph, nextAirport, result);
        }

        // Add to front (postorder = reverse order)
        result.addFirst(airport);
    }
}
