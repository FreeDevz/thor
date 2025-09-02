**O(log n) is much faster than O(n log n)**.

Here's the comparison:

## Growth Difference
- **O(log n)**: Logarithmic growth
- **O(n log n)**: Linearithmic growth (linear × logarithmic)

O(n log n) essentially means you're doing an O(log n) operation for each of the n elements, making it significantly slower.

## Real Numbers Example
For n = 1,000,000 elements:
- **O(log n)**: ~20 operations (log₂ 1,000,000 ≈ 20)
- **O(n log n)**: ~20,000,000 operations (1,000,000 × 20)

For n = 1,000,000,000 elements:
- **O(log n)**: ~30 operations
- **O(n log n)**: ~30,000,000,000 operations

## Speed Ranking (fastest to slowest)
1. **O(1)** - Constant time
2. **O(log n)** - Logarithmic time ← Much faster
3. **O(n)** - Linear time
4. **O(n log n)** - Linearithmic time ← Slower
5. **O(n²)** - Quadratic time
6. **O(2ⁿ)** - Exponential time

## Common Examples

**O(log n) algorithms:**
- Binary search in sorted array
- Finding element in balanced BST
- Heap insert/delete operations

**O(n log n) algorithms:**
- Efficient sorting algorithms (merge sort, heap sort, quick sort average case)
- Building a heap from an unsorted array
- Many divide-and-conquer algorithms

The factor of n makes a huge difference - O(n log n) grows much faster than O(log n) as the input size increases, making O(log n) significantly more efficient for large datasets.