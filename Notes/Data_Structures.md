# 🧱 LINEAR DATA STRUCTURES (with Full Methods)

## 📑 Table of Contents
- [1. Array](#1-array)
- [2. ArrayList](#2-arraylist)
- [3. LinkedList](#3-linkedlist)
- [4. Stack](#4-stack)
- [5. Queue](#5-queue)
- [6. Deque](#6-deque)
- [🌳 NON-LINEAR DATA STRUCTURES](#🌳-non-linear-data-structures)
  - [1. HashMap](#1-hashmap)
  - [2. TreeMap](#2-treemap)
  - [3. HashSet](#3-hashset)
  - [4. LinkedHashSet](#4-linkedhashset)
  - [5. TreeSet](#5-treeset)
  - [6. PriorityQueue](#6-priorityqueue)

---

## 🔹 1. Array

✅ **What It Is**: Fixed-size, indexed collection of elements of the same type.  
Used from the `java.util.Arrays` utility class.

🔧 **Initialization**:
```java
int[] nums = new int[5];
String[] names = {"Alice", "Bob"};
int[] copy = Arrays.copyOf(nums, 10);
```

🛠️ **Common & Utility Methods**:

| Method | Description |
|--------|-------------|
| Arrays.asList(array) | Converts array to fixed-size List |
| Arrays.sort(array) | Sorts array in ascending order |
| Arrays.binarySearch(array, key) | Searches sorted array |
| Arrays.copyOf(array, newLength) | Copies with new length |
| Arrays.copyOfRange(array, from, to) | Copies a specific range |
| Arrays.equals(a1, a2) | Compares shallow content |
| Arrays.deepEquals(a1, a2) | Compares nested arrays |
| Arrays.fill(array, value) | Fills all elements with value |
| Arrays.toString(array) | String representation |
| Arrays.deepToString(array) | Nested arrays to string |
| Arrays.hashCode(array) | Hash code of flat array |
| Arrays.deepHashCode(array) | Hash code of nested arrays |

🧪 **Example**:
```java
int[] a = {1, 2, 3, 4};
int[] b = Arrays.copyOf(a, 6);
System.out.println(Arrays.toString(b)); // [1, 2, 3, 4, 0, 0]

List<Integer> list = Arrays.asList(1, 2, 3);
```

📌 **When to Use**:
- Fixed size
- Lightweight operations
- Best for primitive types

---

## 🔹 2. ArrayList

✅ **What It Is**: Resizable, ordered array that implements List.

🔧 **Initialization**:
```java
ArrayList<String> list = new ArrayList<>();
ArrayList<Integer> list = new ArrayList<>(50);
```

🛠️ **Common Methods**:

| Method | Description |
|--------|-------------|
| add(e) | Adds to end |
| add(index, e) | Adds at index |
| remove(index) | Removes by index |
| remove(Object) | Removes first occurrence |
| get(index) | Gets element |
| set(index, e) | Replaces element |
| contains(e) | Checks existence |
| indexOf(e) / lastIndexOf(e) | Index of first/last match |
| size() | Element count |
| clear() | Empties the list |
| isEmpty() | Checks if empty |
| toArray() | Converts to array |
| sort(Comparator) | Custom sort |
| subList(from, to) | Extracts a view portion |

🧪 **Example**:
```java
ArrayList<String> names = new ArrayList<>();
names.add("Amy");
names.add("Bob");
names.remove("Amy");
Collections.sort(names);
```

📌 **When to Use**:
- Frequent read by index
- Dynamic sizing
- Efficient end insertions

---

## 🔹 3. LinkedList

✅ **What It Is**: Doubly linked list. Implements List, Deque, and Queue.

🔧 **Initialization**:
```java
LinkedList<Integer> list = new LinkedList<>();
```

🛠️ **Extra Methods**:

| Method | Description |
|--------|-------------|
| addFirst(e) / addLast(e) | Add at ends |
| removeFirst() / removeLast() | Remove from ends |
| getFirst() / getLast() | Peek ends |
| offerFirst(e) / offerLast(e) | Queue-like inserts |
| pollFirst() / pollLast() | Queue-like removals |
| peekFirst() / peekLast() | Queue-like peeks |

🧪 **Example**:
```java
LinkedList<String> deck = new LinkedList<>();
deck.addFirst("King");
deck.addLast("Queen");
System.out.println(deck.pollFirst()); // King
```

📌 **When to Use**:
- Frequent insert/delete at ends or middle
- Implementing queues/stacks with Deque

---

## 🔹 4. Stack

✅ **What It Is**: LIFO data structure. Can use Stack or Deque.

🔧 **Initialization**:
```java
Stack<Integer> stack = new Stack<>();
Deque<Integer> stack = new ArrayDeque<>();
```

🛠️ **Methods**:

| Method | Description |
|--------|-------------|
| push(e) | Add to top |
| pop() | Remove top |
| peek() | View top |
| isEmpty() | Check if empty |
| search(e) | Position from top (Stack only) |

🧪 **Example**:
```java
Stack<String> s = new Stack<>();
s.push("A"); s.push("B");
System.out.println(s.pop()); // B
```

📌 **When to Use**:
- Backtracking
- Parentheses checking, undo ops

---

## 🔹 5. Queue

✅ **What It Is**: FIFO structure for tasks.

🔧 **Initialization**:
```java
Queue<Integer> q = new LinkedList<>();
Queue<String> q = new ArrayDeque<>();
```

🛠️ **Methods**:

| Method | Description |
|--------|-------------|
| offer(e) | Add to end |
| poll() | Remove from front |
| peek() | View front |
| isEmpty() | Check if empty |

🧪 **Example**:
```java
Queue<String> tasks = new LinkedList<>();
tasks.offer("Email");
tasks.offer("Call");
System.out.println(tasks.poll()); // Email
```

📌 **When to Use**:
- Order processing
- BFS traversal

---

## 🔹 6. Deque

✅ **What It Is**: Double-ended queue (stack + queue).

🔧 **Initialization**:
```java
Deque<Integer> dq = new ArrayDeque<>();
```

🛠️ **Methods**:

| Method | Description |
|--------|-------------|
| addFirst(e) / addLast(e) | Insert at ends |
| removeFirst() / removeLast() | Remove at ends |
| peekFirst() / peekLast() | View ends |

📌 **When to Use**:
- Sliding window
- Double-ended access

---

# 🌳 NON-LINEAR DATA STRUCTURES

## 🔹 1. HashMap

✅ **What It Is**: Key-value store with fast access.

🔧 **Initialization**:
```java
HashMap<String, Integer> map = new HashMap<>();
```

🛠️ **Methods**:

| Method | Description |
|--------|-------------|
| put(k, v) | Insert or update |
| get(k) | Retrieve value |
| remove(k) | Delete key |
| containsKey(k) | Key existence |
| keySet() / values() | All keys or values |
| entrySet() | Key-value pairs |

📌 **When to Use**:
- Fast lookups
- Grouping by key

---

## 🔹 2. TreeMap

✅ **What It Is**: Sorted key-value store (Red-Black Tree).

🔧 **Initialization**:
```java
TreeMap<Integer, String> tm = new TreeMap<>();
```

🛠️ **Extra Methods**:

| Method | Description |
|--------|-------------|
| firstKey() / lastKey() | Sorted bounds |
| subMap(k1, k2) | Range keys |
| higherKey(k) / lowerKey(k) | Navigable lookup |

📌 **When to Use**:
- Sorted entries
- Range queries

---

## 🔹 3. HashSet

✅ **What It Is**: Unique set backed by HashMap.

🔧 **Initialization**:
```java
HashSet<String> tags = new HashSet<>();
```

🛠️ **Methods**:

| Method | Description |
|--------|-------------|
| add(e) | Add unique element |
| remove(e) | Delete element |
| contains(e) | Check presence |

📌 **When to Use**:
- Avoid duplicates
- Fast membership test

---

## 🔹 4. LinkedHashSet

✅ **What It Is**: Like HashSet but preserves insertion order.

🔧 **Initialization**:
```java
LinkedHashSet<String> order = new LinkedHashSet<>();
```

📌 **When to Use**:
- Unique + ordered elements

---

## 🔹 5. TreeSet

✅ **What It Is**: Sorted, unique elements using Red-Black Tree.

🔧 **Initialization**:
```java
TreeSet<Integer> scores = new TreeSet<>();
```

🛠️ **Extra Methods**: Similar to TreeMap, but for values only.

📌 **When to Use**:
- Sorted + no duplicates
- Range queries on values

---

## 🔹 6. PriorityQueue

✅ **What It Is**: Min-heap based queue for priority ordering.

🔧 **Initialization**:
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

🛠️ **Methods**:

| Method | Description |
|--------|-------------|
| offer(e) | Insert element |
| poll() | Remove smallest |
| peek() | Get smallest |

📌 **When to Use**:
- Task scheduling
- Dijkstra’s, Huffman Coding
