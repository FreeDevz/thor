package io.awijaya.lab3;

import java.util.*;

/**
 * https://leetcode.com/problems/network-delay-time/description/
 * level: medium
 */
public class NetworkDelayTime {
    /**
     * Approach 1: Dijkstra's Algorithm (Recommended)
     * <p>
     * Uses Dijkstra's algorithm to find the shortest path from node k to all other nodes in a
     * weighted directed graph. This is the optimal solution for single-source shortest paths in
     * non-negative weighted graphs.
     * <p>
     * Algorithm: 1. Build an adjacency list representation of the graph 2. Initialize distances to
     * all nodes as infinity, except source node k = 0 3. Use a priority queue (min-heap) to always
     * process the node with minimum distance 4. For each node, relax its neighbors (update
     * distances if shorter path found) 5. Return the maximum distance (longest time to reach all
     * nodes), or -1 if any node is unreachable
     * <p>
     * Key insights: - Priority queue ensures we always process the closest unvisited node first -
     * Once a node is processed, its distance is finalized (no shorter path exists) - Maximum
     * distance represents the time for signal to reach all nodes
     * <p>
     * Time Complexity: O(E log V) where E is edges and V is vertices - Each edge is processed once:
     * O(E) - Priority queue operations: O(log V) per edge - Total: O(E log V) Space Complexity: O(V
     * + E) for graph and priority queue
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // Build adjacency list: node -> [(neighbor, weight), ...]
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, w});
        }

        // Distance array: dist[i] = shortest distance from k to node i
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        // Priority queue: [distance, node] - processes nodes with smallest distance first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, k});

        // Visited set to avoid reprocessing nodes
        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int distance = current[0];
            int node = current[1];

            // Skip if already processed (duplicate entries in PQ)
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);

            // Relax all neighbors
            if (graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {
                    int neighbor = edge[0];
                    int weight = edge[1];
                    int newDist = distance + weight;

                    // If shorter path found, update distance and add to queue
                    if (newDist < dist[neighbor]) {
                        dist[neighbor] = newDist;
                        pq.offer(new int[]{newDist, neighbor});
                    }
                }
            }
        }

        // Check if all nodes are reachable and find maximum distance
        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1; // Node i is unreachable
            }
            maxDist = Math.max(maxDist, dist[i]);
        }

        return maxDist;
    }

    public static void main(String[] args) {
        NetworkDelayTime networkDelayTime = new NetworkDelayTime();
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        System.out.println(networkDelayTime.networkDelayTime(times, 4, 2));
    }
}
