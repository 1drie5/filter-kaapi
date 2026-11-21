# 01 - Collections Introduction

## What is the Java Collections Framework?

The Java Collections Framework is a set of interfaces, implementations, and utility algorithms in `java.util` for storing and working with groups of objects. It has been part of Java since version 1.2.

It has three main parts:

| Part            | What it is                                                   | Examples                                                           |
| --------------- | ------------------------------------------------------------ | ------------------------------------------------------------------ |
| Interfaces      | Define the operations and behaviour a data structure exposes | `Collection`, `List`, `Set`, `Queue`, `Map`                        |
| Implementations | Classes that provide the actual storage and behaviour        | `ArrayList`, `HashSet`, `HashMap`                                  |
| Algorithms      | Reusable operations provided by utility classes              | `Collections.sort()`, `Collections.max()`, `Collections.reverse()` |

## Why does it exist?

Arrays have a fixed length and do not provide collection operations such as `add`, `remove`, and `contains`. Before the Collections Framework, Java had arrays and older classes such as `Vector` and `Hashtable`, but there was no unified framework of collection interfaces and implementations.

The framework provides:

- **A common API.** Operations such as `add`, `remove`, `contains`, and `size` are defined by collection interfaces and can be used across compatible implementations. Some operations are optional; an unmodifiable collection throws `UnsupportedOperationException` when a modification is attempted.
- **Dynamic sizing.** General-purpose collections such as `ArrayList`, `HashSet`, and `HashMap` can grow or shrink at runtime. Fixed-size and unmodifiable collections are exceptions.
- **Replaceable implementations.** You can often replace `ArrayList` with `LinkedList` without changing code that uses only the `List` interface. Choose the implementation based on the operations your code needs.
- **Ready-made data structures and algorithms.** Common storage structures and operations are already implemented in the JDK.

Collections store objects (reference types), not primitive values. Use `List<Integer>`, not `List<int>`. Java supports autoboxing (converting `int` to `Integer`) and unboxing (converting `Integer` to `int`) automatically.

## The two main hierarchies

```text
Iterable                                      <- java.lang
└── Collection                                <- a group of individual elements
    ├── List                                  (ArrayList, LinkedList, Vector -> Stack)
    ├── Set                                   (HashSet, LinkedHashSet, SortedSet -> NavigableSet -> TreeSet)
    └── Queue                                 (PriorityQueue, Deque -> ArrayDeque, LinkedList)

Map                                           <- separate; NOT a Collection
├── HashMap
├── LinkedHashMap
└── SortedMap -> NavigableMap -> TreeMap
```

- **`Iterable`** provides the `iterator()` method, which makes the enhanced `for` loop (`for (Type element : collection)`) possible. `Collection` extends `Iterable`, so its subinterfaces such as `List`, `Set`, and `Queue` are iterable.
- **`Map` is separate.** A `Collection` represents individual elements and has operations such as `add(E)`. A `Map` stores key-value associations and has operations such as `put(K, V)`. Therefore, `Map` does not extend `Collection` and is not itself `Iterable`.
- **`Deque` extends `Queue`.** It supports insertion and removal at both ends and can also be used as a stack.
- **`LinkedList`** implements both `List` and `Deque`, so it appears in both branches.

## Core interfaces

| Interface | Main rule                                                                                 | Duplicates            | Common access operations                               |
| --------- | ----------------------------------------------------------------------------------------- | --------------------- | ------------------------------------------------------ |
| `List`    | Elements have positions and can be accessed by index                                      | Allowed               | `get(index)`, `add(element)`                           |
| `Set`     | Does not contain duplicate elements                                                       | Not allowed           | `contains(element)`, `add(element)`                    |
| `Queue`   | Holds elements waiting to be processed; often FIFO, but some queues use priority ordering | Usually allowed       | `peek()`, `poll()`, `offer()`                          |
| `Deque`   | A double-ended queue; elements can be added or removed at either end                      | Allowed               | `addFirst()`, `addLast()`, `pollFirst()`, `pollLast()` |
| `Map`     | Associates each unique key with one value                                                 | Keys: no; values: yes | `put(key, value)`, `get(key)`                          |

### Ordering terms

- **Position order** (`List`, `Deque`): iteration follows the elements' current positions. Adding at the end preserves the existing positions; operations such as `add(0, element)` or `addFirst(element)` insert at the front.
- **Insertion order** (`LinkedHashSet`, `LinkedHashMap` by default): iteration follows the order in which elements or keys were first inserted. Re-adding an existing `LinkedHashSet` element does not move it. In a default insertion-order `LinkedHashMap`, updating an existing key does not move it either. `LinkedHashMap` can also be configured for access order; accessing an entry moves it toward the end, which is useful when implementing an LRU cache.
- **Sorted order** (`TreeSet`, `TreeMap`): iteration follows natural ordering (`Comparable`) or a supplied `Comparator`.
- **No guaranteed order** (`HashSet`, `HashMap`): do not rely on the iteration order.

## Common implementations

| Class           | Implements      | Internal structure                                                       | Order                                                                                        | Typical time complexity                                                                     |
| --------------- | --------------- | ------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| `ArrayList`     | `List`          | Resizable array                                                          | Position order                                                                               | `get(index)` O(1); append O(1) amortized; insert/remove in the middle O(n); `contains` O(n) |
| `LinkedList`    | `List`, `Deque` | Doubly linked list                                                       | Position order                                                                               | Add/remove at either end O(1); `get(index)` O(n)                                            |
| `HashSet`       | `Set`           | Hash table (backed by `HashMap` in the standard JDK implementation)      | No guaranteed order                                                                          | `add`, `contains`, `remove` O(1) on average                                                 |
| `LinkedHashSet` | `Set`           | Hash table plus linked list                                              | Insertion order                                                                              | `add`, `contains`, `remove` O(1) on average                                                 |
| `TreeSet`       | `NavigableSet`  | Balanced search tree (red-black tree in the standard JDK implementation) | Sorted                                                                                       | `add`, `contains`, `remove` O(log n)                                                        |
| `ArrayDeque`    | `Deque`         | Resizable array                                                          | Position order                                                                               | Add/remove at either end O(1) amortized; does not allow `null`                              |
| `PriorityQueue` | `Queue`         | Binary heap                                                              | Head is the least element according to its ordering; remaining elements are not fully sorted | `offer`, `poll` O(log n); `peek` O(1)                                                       |
| `HashMap`       | `Map`           | Hash table                                                               | No guaranteed order                                                                          | `get`, `put`, `remove` O(1) on average                                                      |
| `LinkedHashMap` | `Map`           | Hash table plus linked list                                              | Insertion order by default; can use access order                                             | `get`, `put`, `remove` O(1) on average                                                      |
| `TreeMap`       | `NavigableMap`  | Balanced search tree (red-black tree in the standard JDK implementation) | Sorted by key                                                                                | `get`, `put`, `remove` O(log n)                                                             |

Time complexities are typical expectations, not guarantees for every operation or input. Hash-based operations are O(1) on average; collisions and resizing can affect individual operations.

`Vector`, `Stack`, and `Hashtable` are legacy classes. They are synchronized, but that does not make them the default choice for modern applications. Prefer `ArrayList`, `ArrayDeque`, and `HashMap` for ordinary use unless you specifically need the legacy class's behaviour or synchronization characteristics.

## How duplicates are determined

- **`HashSet` and `HashMap`** use `hashCode()` and `equals()` to identify equal elements or keys. For custom classes, implement these methods consistently when objects are compared by their data.
- **`TreeSet` and `TreeMap`** use natural ordering (`compareTo()`) or a supplied `Comparator`. If the comparison returns `0`, the set treats the elements as duplicates, or the map treats the keys as the same key.
- For sorted sets and maps, the ordering should ideally be consistent with `equals()`. If it is not, the collection can behave differently from what the general `Set` or `Map` contract suggests.
- `Set.add(element)` returns `false` if an equal element is already present.

```java
Set<String> names = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
names.add("Iddu");    // true
names.add("IDDU");    // false: comparison returns 0
```

## Program to the interface

Declare a variable using the interface type and create an object using a concrete implementation:

```java
List<String> names = new ArrayList<>();
```

This is usually preferable to tying the variable to one implementation:

```java
ArrayList<String> names = new ArrayList<>();
```

The interface-based declaration lets you replace the implementation when appropriate, as long as the rest of the code uses operations and behaviour provided by `List`.

A method should also accept the most general interface that provides the operations it needs:

```java
static void describe(String label, Collection<Integer> values) {
    // Works with any compatible Collection implementation.
}
```

## Map is not a Collection, but it provides Collection views

| Method       | Returns                | Why                                             |
| ------------ | ---------------------- | ----------------------------------------------- |
| `keySet()`   | `Set<K>`               | Map keys are unique                             |
| `values()`   | `Collection<V>`        | Values may repeat                               |
| `entrySet()` | `Set<Map.Entry<K, V>>` | Each entry represents one key-value association |

These are **views backed by the map**, not independent copies:

- Removing a key or entry through a view removes the corresponding mapping from the map.
- Changes made to the map are reflected in its views.
- Adding elements through these views is not supported; for example, `keySet().add(key)` throws `UnsupportedOperationException`.

## `Collection`, `Collections`, and `Arrays`

- **`Collection`** (no `s`) is the root interface for groups of individual elements.
- **`Collections`** (with `s`) is a utility class with static methods such as `sort()`, `reverse()`, `max()`, and `unmodifiableList()`.
- **`Arrays`** is a utility class with array operations such as `Arrays.sort()` and `Arrays.asList()`.

## Common mistakes and details to remember

1. **Using primitives as type arguments.** `List<int>` does not compile. Use `List<Integer>`.

2. **Confusing `remove(int)` with `remove(Object)` on a `List<Integer>`.**

   ```java
   List<Integer> nums = new ArrayList<>(List.of(10, 20, 30));
   nums.remove(1);                   // Removes the element at index 1: [10, 30]
   nums.remove(Integer.valueOf(10)); // Removes the value 10: [30]
   ```

3. **Modifying a collection directly while iterating over it.** A for-each loop that calls `list.remove(...)` can cause `ConcurrentModificationException`. Use `removeIf()` or `Iterator.remove()` when appropriate:

   ```java
   names.removeIf(name -> name.equals("Iddu"));
   ```

4. **Relying on `HashSet` or `HashMap` iteration order.** Their iteration order is not guaranteed.

5. **Modifying an unmodifiable collection.** `List.of()`, `Set.of()`, and `Map.of()` return unmodifiable collections; attempts to modify them throw `UnsupportedOperationException`. `Arrays.asList()` is fixed-size: it allows `set()`, but not `add()` or `remove()`. `Collections.unmodifiableList(list)` returns an unmodifiable view, not a copy. Changes made to the original list are still visible through that view.

6. **Assuming a `PriorityQueue` prints in sorted order.** Only its head is guaranteed to be the least element according to the queue's ordering. With natural ordering this is usually the smallest element; with `Comparator.reverseOrder()` it is the largest. Repeated calls to `poll()` retrieve elements in priority order, but iteration and `toString()` do not provide a sorted sequence.

7. **Using `null`.** `ArrayDeque` and `PriorityQueue` do not allow null elements. `TreeSet` using natural ordering does not allow null elements, and `TreeMap` using natural ordering does not allow null keys. A `TreeSet` or `TreeMap` with a custom comparator may allow null if that comparator supports it. `HashSet` permits one null element. `HashMap` permits one null key and multiple null values.

## Files in this section

- **`CollectionHierarchy.java`**: passes five different collection implementations to one `describe()` method. The output demonstrates how they handle duplicates and ordering. It also shows programming to interfaces and iteration through `Iterable`.
- **`CollectionVsMap.java`**: compares a `List` of names with a `Map` of names to marks. It demonstrates replacing a value using an existing key, the three Map views, how a view is backed by the map, and `getOrDefault()` for a lookup with a fallback value.
