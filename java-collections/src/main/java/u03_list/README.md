# 03 - List

## What is a `List`?

`List<E>` is an interface in `java.util`. It represents an **ordered** collection of elements.

- **Every element has an index**, starting at `0`.
- **Duplicates are allowed.** The same value can appear many times.
- **Order is kept.** Elements stay in the order you added or inserted them.
- **`null` depends on the implementation.** `ArrayList`, `LinkedList`, and `Vector` allow `null`. `List.of(...)` does not.

`List` extends `Collection`, and `Collection` extends `Iterable`. So every list can be used in an enhanced `for`-loop (see the chapter `u02_iterable_and_iterator`).

## Three implementations

`ArrayList`, `LinkedList`, and `Vector` all implement `List`, so they have the same list methods. They differ in **how they store the elements**.

| Class | Stores elements in | Thread-safe? | Status |
| --- | --- | --- | --- |
| `ArrayList` | A resizable array | No | The default choice |
| `LinkedList` | A chain of nodes (doubly linked) | No | Useful at the ends; rarely the best choice |
| `Vector` | A resizable array | Each method is `synchronized` | Legacy |

A good habit is to declare the variable with the interface type:

```java
List<String> languages = new ArrayList<>();
```

Then you can change `ArrayList` to another implementation by editing one word.

## `ArrayList`

An `ArrayList` keeps its elements in an **internal array**. When the array is full, the list creates a bigger array and copies the elements into it. You do not manage this yourself.

- `get(i)` and `set(i, x)` are fast: **O(1)**. The array can jump straight to index `i`.
- Adding at the end is **amortized O(1)**. Most adds are cheap. Once in a while the array is full and must be copied, which is slower. Averaged over many adds, the cost per add is constant.
- Inserting or removing at the front or in the middle is **O(n)**, because later elements must shift.
- `contains(x)` and `indexOf(x)` are **O(n)**, because they check elements one by one.

`new ArrayList<>(1000)` sets an initial capacity of 1000. The list is still empty: `size()` is `0`. A good initial capacity avoids repeated copying when you know roughly how many elements will come.

Use `ArrayList` unless you have a specific reason not to. See `ArrayListDemo.java`.

## `LinkedList`

A `LinkedList` stores each element in a **node**. Each node holds the element and references to the previous node and the next node.

- Adding or removing at **either end** is **O(1)**.
- `get(i)` is **O(n)**. The list must walk the nodes one by one, starting from the nearer end.
- `add(i, x)` is also **O(n)**. The cost is the walk to index `i`.
- It allows `null` elements.

`LinkedList` implements both `List` and `Deque`, so it also has methods for queues and stacks.

| Use | Methods | What they do |
| --- | --- | --- |
| Both ends | `addFirst()`, `addLast()`, `getFirst()`, `getLast()`, `removeFirst()`, `removeLast()` | Work on the first or last element. |
| Queue (first in, first out) | `offer()`, `peek()`, `poll()` | `offer` adds at the end. `peek` looks at the first element. `poll` removes the first element. |
| Stack (last in, first out) | `push()`, `pop()` | Both work at the **front**, so the newest element is on top. |

After `push(1)`, `push(2)`, `push(3)`, the list is `[3, 2, 1]`, and `pop()` returns `3`.

### What happens on an empty list?

| Call | Result |
| --- | --- |
| `poll()` | Returns `null` |
| `peek()` | Returns `null` |
| `removeFirst()` | Throws `NoSuchElementException` |
| `pop()` | Throws `NoSuchElementException` |

`ArrayDeque` is another queue and stack class. It does not allow `null`, because `poll()` and `peek()` use `null` to mean "empty". For a queue or a stack, `ArrayDeque` is usually the better choice than `LinkedList`.

See `LinkedListDemo.java`.

## `Vector`

`Vector` is like an `ArrayList`, but it is older (Java 1.0) and its methods are `synchronized`.

- `synchronized` means only one thread at a time can run a method on the same `Vector`. This protects **each single call**.
- It does **not** make a group of calls safe. For example, `if (!v.contains(x)) v.add(x);` is two calls, and another thread can act in between. Looping over a `Vector` while another thread changes it also needs care.
- Synchronization adds some cost, so it can be slower than `ArrayList`.
- It is a **legacy** class. In new code, use `ArrayList`. To share a list between threads, use `Collections.synchronizedList(...)` or `CopyOnWriteArrayList`.

Because it is old, `Vector` has extra methods that do the same jobs as list methods:

| Older `Vector` method | Same as |
| --- | --- |
| `addElement(x)` | `add(x)` |
| `elementAt(i)` | `get(i)` |
| `lastElement()` | `get(size() - 1)` |
| `elements()` | Returns an `Enumeration`, the old way to loop (`hasMoreElements()` and `nextElement()`). Today we use a `for`-each loop or an `Iterator`. |

Learn `Vector` mainly so you can recognise it in older code. See `VectorDemo.java`.

## `ArrayList` vs `LinkedList` (important)

| Operation | `ArrayList` | `LinkedList` |
| --- | --- | --- |
| `get(i)` / `set(i, x)` | O(1) | O(n) |
| Add at the end | Amortized O(1) | O(1) |
| Add or remove at the front | O(n), elements shift | O(1) |
| Add or remove in the middle by index | O(n), elements shift | O(n), must walk to the index |
| `contains(x)` / `indexOf(x)` | O(n) | O(n) |

`ArrayListVsLinkedListDemo.java` times two things on 50,000 elements:

1. **Adding at the front.** `ArrayList` must shift all elements each time. `LinkedList` just links a new node.
2. **Reading every element with `get(i)`.** `ArrayList` jumps straight to each index. `LinkedList` walks the nodes for every call.

One sample run:

```
Add at the front, 50000 times:
  ArrayList:  121 ms
  LinkedList: 5 ms
Read every element with get(i):
  ArrayList:  3 ms
  LinkedList: 1258 ms
```

Your numbers will be different on your computer, and this is only a rough test. Look at the **pattern**:

- `LinkedList` is much faster at the front.
- `ArrayList` is much faster at `get(i)`.

### Why `ArrayList` is usually the better choice

- A `LinkedList` creates a separate node object for every element, and each node stores two extra references. An `ArrayList` stores its references in one array, so it uses less memory.
- Array elements sit next to each other in memory, which computers read quickly. Nodes can be scattered, so walking them is slower.
- Big O does not tell the whole story. Two operations with the same Big O can still differ in real speed.

### A common belief

"`LinkedList` is faster for inserting in the middle." This is only partly true. The insertion itself is O(1), but you must first **reach** the position. With `add(index, x)`, that walk is O(n). It is cheap only when you already hold an iterator at that position.

### Which one should I use?

| Need | Use |
| --- | --- |
| A general-purpose list | `ArrayList` |
| A queue or a stack | `ArrayDeque` |
| A list shared between threads | `Collections.synchronizedList(...)` or `CopyOnWriteArrayList` |
| Reading old code with `Vector` | Understand it, but avoid it in new code |

## Common `List` methods

These methods are defined by `List`, so they work on every implementation. See `ListMethodsDemo.java`.

| Method | Purpose |
| --- | --- |
| `add(x)` | Adds `x` at the end. |
| `add(i, x)` | Inserts `x` at index `i`. Later elements shift right. |
| `get(i)` | Returns the element at index `i`. |
| `set(i, x)` | Replaces the element at index `i` and returns the old one. |
| `remove(i)` | Removes the element at index `i` and returns it. |
| `remove(x)` | Removes the first element equal to `x`. Returns `true` if one was found. |
| `indexOf(x)` | Returns the index of the first `x`, or `-1` if it is not found. |
| `contains(x)` | Returns `true` if `x` is in the list. |
| `size()` | Returns the number of elements. |
| `sort(null)` | Sorts the list. `null` means natural order (ascending for numbers, alphabetical for strings). |
| `removeIf(condition)` | Removes every element that matches the condition. |

### `remove(int)` vs `remove(Object)`

On a `List<Integer>`, these two calls do different things:

```java
List<Integer> numbers = new ArrayList<>(List.of(10, 20, 30, 40));

numbers.remove(1);                   // remove(int index)  -> removes the value 20
numbers.remove(Integer.valueOf(40)); // remove(Object o)   -> removes the value 40
```

A plain `int` argument is treated as an **index**. To remove a **value**, pass an `Integer` object. The result is `[10, 30]`. (`ArrayListDemo.java` shows this.)

On a `List<String>`, `remove(1)` removes by index and `remove("Cherry")` removes by value, with no confusion.

### `List.of(...)` is unmodifiable

`List.of(...)` creates a list that cannot be changed. `add`, `remove`, and `set` all throw `UnsupportedOperationException`. It also does not accept `null`.

To get a list you can change, copy it:

```java
List<String> editable = new ArrayList<>(List.of("a", "b"));
```

## `ListIterator`

`ListIterator<E>` is an iterator that only lists provide. It extends `Iterator`, and it can also:

- go **backwards**,
- **replace**, **insert**, and **remove** elements while iterating.

Get one with `list.listIterator()`.

| Method | Purpose |
| --- | --- |
| `hasNext()` / `next()` | Move forward. |
| `hasPrevious()` / `previous()` | Move backward. |
| `set(x)` | Replaces the element most recently returned by `next()` or `previous()`. |
| `remove()` | Removes the element most recently returned by `next()` or `previous()`. |
| `add(x)` | Inserts `x` at the cursor position. |

### How the cursor works

The cursor sits **between** elements, not on them:

```
     A   B   C   D
   ^   ^   ^   ^   ^
```

- `next()` returns the element **after** the cursor and moves the cursor forward.
- `previous()` returns the element **before** the cursor and moves the cursor back.
- `add(x)` puts `x` at the cursor. A following `next()` is not affected.

### What `ListIteratorDemo.java` does

The list starts as `[A, B, C, D]`.

1. **Forward:** prints A, B, C, D.
2. **Backward:** the cursor is at the end, so `previous()` prints D, C, B, A. The cursor is now back at the start.
3. **Change while iterating:** one loop that
   - replaces `B` with `B2` using `set`,
   - removes `C` using `remove`,
   - inserts `E` right after `D` using `add`.

The final list is `[A, B2, D, E]`.

### Rule for `set()` and `remove()`

`set()` and `remove()` work on the element returned by the most recent `next()` or `previous()`. So call `next()` or `previous()` first. If you call `set()` or `remove()` without that, or right after `add()`, Java throws `IllegalStateException`.

### `Iterator` vs `ListIterator`

| `Iterator` | `ListIterator` |
| --- | --- |
| Works on any `Collection`. | Works only on a `List`. |
| Moves forward only. | Moves forward and backward. |
| Can `remove()`. | Can `remove()`, `set()`, and `add()`. |

## Common mistakes

1. **Using `LinkedList` by default** because "inserting is O(1)". In most programs `ArrayList` is faster. Start with `ArrayList`.
2. **Looping over a `LinkedList` with `get(i)`.** Each call walks the nodes, so the whole loop is O(n²). Use a `for`-each loop instead. It is O(n) for every list type.
3. **Mixing up `remove(int)` and `remove(Object)`** on a `List<Integer>`. `remove(1)` removes the element at index `1`, not the value `1`.
4. **Adding or removing inside a `for`-each loop.** This may throw `ConcurrentModificationException`. Use `removeIf(...)` or `ListIterator.remove()` instead.
5. **Changing a list made by `List.of(...)`.** It is unmodifiable. Copy it into a new `ArrayList` first.
6. **Using `Vector` in new code** only for thread safety. It protects single calls, not groups of calls. Prefer `ArrayList`, or `Collections.synchronizedList(...)` / `CopyOnWriteArrayList` when sharing between threads.

## Key takeaways

- A `List` is an ordered collection with index access, and it allows duplicates.
- `ArrayList` is the default. It is fast for `get(i)` and for adding at the end.
- `LinkedList` is fast at the front and the back, but slow for `get(i)`.
- `Vector` is a legacy, synchronized version of `ArrayList`. Know it, but avoid it in new code.
- On a `List<Integer>`, `remove(1)` removes by index. Use `Integer.valueOf(...)` to remove by value.
- Declare variables as `List`, not as the concrete class.
- Use `ListIterator` when you need to go backwards or change a list while iterating.

## Files in this section

- **`ArrayListDemo.java`**: basic `ArrayList` operations, the `remove(int)` vs `remove(Object)` pitfall, duplicates and `null`, sorting, and initial capacity.
- **`LinkedListDemo.java`**: `LinkedList` as a list, a queue, and a stack, plus empty-list behaviour.
- **`VectorDemo.java`**: the `List` methods on a `Vector`, its older methods, and `Enumeration`.
- **`ArrayListVsLinkedListDemo.java`**: times adding at the front and reading with `get(i)` on both lists.
- **`ListMethodsDemo.java`**: common `List` methods, `sort`, `removeIf`, and an unmodifiable `List.of`.
- **`ListIteratorDemo.java`**: forward and backward traversal, and `set`, `remove`, and `add` while iterating.
