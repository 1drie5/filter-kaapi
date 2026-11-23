# 02 - Iterable and Iterator

## What is `Iterable`?

`Iterable<T>` is an interface in `java.lang`. It represents an object whose elements can be traversed one at a time.

Its main method is:

```java
Iterator<T> iterator();
```

An object that implements `Iterable<T>` provides an `Iterator<T>` that can traverse its elements.

The `Collection` interface extends `Iterable`, so common collections such as `List`, `Set`, and `Queue` can be used in an enhanced `for`-loop.

`Map` does **not** extend `Iterable`. However, its views do: `keySet()`, `values()`, and `entrySet()` all return collections that you can loop over.

## What is `Iterator`?

`Iterator<T>` is an interface in `java.util`. It represents the object that performs the traversal. It keeps track of the current position while moving through the elements.

Important methods:

| Method | Purpose |
| --- | --- |
| `hasNext()` | Returns `true` if another element is available. It does not advance the iterator. |
| `next()` | Returns the next element and advances the iterator. |
| `remove()` | Optionally removes the last element returned by `next()`. It is not supported by every iterator. If it is not supported, it throws `UnsupportedOperationException`. |
| `forEachRemaining(action)` | Performs the given action on every element that has not been visited yet. |

Calling `next()` when no element remains throws `NoSuchElementException`.

`hasNext()` and `next()` are the only two abstract methods of `Iterator`. `remove()` and `forEachRemaining()` are default methods (since Java 8). This is why `OurGenericListIterator` only has to write `hasNext()` and `next()`.

## `Iterable` vs `Iterator`

| `Iterable` | `Iterator` |
| --- | --- |
| Represents something that can be iterated over. | Performs the actual traversal. |
| Provides the `iterator()` method. | Provides methods such as `hasNext()` and `next()`. |
| Usually implemented by a collection or a custom container. | Usually returned by an `Iterable` object. |

Think of `Iterable` as the **book** and `Iterator` as the **bookmark** that remembers where you are while reading.

## Manual iteration

```java
Iterator<Integer> iterator = list.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

The steps are:

1. `iterator()` creates an iterator.
2. `hasNext()` checks whether another element exists.
3. `next()` returns that element and moves the iterator forward.
4. The loop stops when `hasNext()` returns `false`.

## Enhanced `for`-loop

```java
for (int number : list) {
    System.out.println(number);
}
```

For an `Iterable`, the enhanced `for`-loop uses an iterator behind the scenes. The compiler turns it into code that works like this:

```java
for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
    int number = it.next();
    System.out.println(number);
}
```

Because the loop calls `iterator()`, `hasNext()`, and `next()` for you, any class that implements `Iterable` can be used in it.

Note: `list` here is an `Iterable<Integer>`, so `next()` returns an `Integer` that is automatically unboxed to `int`. If an element were `null`, this unboxing would throw a `NullPointerException`.

## Implementing `Iterable` in a custom class

A custom class becomes usable in an enhanced `for`-loop when it implements `Iterable<T>` and provides `iterator()`.

In `OurGenericList`:

- `OurGenericList<T>` implements `Iterable<T>`.
- `iterator()` returns a new `OurGenericListIterator`.
- The iterator stores an `index` to remember its current position.
- `hasNext()` checks whether `index` is smaller than the list's `size`.
- `next()` returns the current item and increments `index`.
- `next()` throws `NoSuchElementException` when there are no more elements.

Each call to `iterator()` creates a separate iterator with its own position. Therefore, two iterators can traverse the same list independently.

## Why the iterator is an inner class

`OurGenericListIterator` is declared inside `OurGenericList` **without** the `static` keyword. This makes it an *inner class*.

An inner class object always belongs to one object of the outer class. When `iterator()` runs `new OurGenericListIterator()`, the new iterator is automatically linked to the list that `iterator()` was called on.

This link is why the iterator can use `items` and `size` directly, even though they are `private`. It reads them from the list it belongs to.

Two consequences:

- Different lists have different iterators. An iterator created from `listA` only reads `listA`.
- The iterator reads the list's **current** `size` every time `hasNext()` runs. If you add an element to the list while iterating, the iterator will reach it.

If the class were `static`, it would not have this link. You would have to pass the array and the size into its constructor yourself.

## Independent and single-use iterators

Each iterator has its own position, so two iterators on the same list do not affect each other (see `IndependentIteratorsDemo`).

An iterator can only move forward. The `Iterator` interface has no method to go back or to start again. When you want to traverse the elements again, call `iterator()` to get a new iterator. The enhanced `for`-loop does this for you each time it starts.

```java
Iterator<Integer> iterator = list.iterator();
while (iterator.hasNext()) {
    iterator.next();
}

// iterator has reached the end. To loop again, get a new one:
iterator = list.iterator();
```

## `forEach` and `forEachRemaining`

Both methods are default methods added in Java 8. They take a lambda (or method reference) and run it for each element.

`Iterable.forEach(action)` goes through all elements:

```java
list.forEach(number -> System.out.println(number));
```

This works on `OurGenericList` without any extra code, because `forEach` is inherited from `Iterable`. Its default version simply loops over `this` with an enhanced `for`-loop.

`Iterator.forEachRemaining(action)` goes through the elements that have not been visited yet:

```java
Iterator<Integer> iterator = list.iterator();
iterator.next(); // skip the first element
iterator.forEachRemaining(number -> System.out.println(number));
```

## Removing elements while iterating

### Do not change the collection inside an enhanced `for`-loop

Most standard collections, such as `ArrayList`, are **fail-fast**. If the collection is structurally modified (for example, an element is added or removed) while an iterator is running, and the change did not come from that iterator, a later call to `next()` may throw `ConcurrentModificationException`.

```java
List<String> names = new ArrayList<>(List.of("Iddu", "Grace", "Rabia"));

for (String name : names) {
    if (name.equals("Iddu")) {
        names.remove(name); // may throw ConcurrentModificationException on the next iteration
    }
}
```

Fail-fast is a best-effort check to help find bugs. It is not a guarantee, so never write code that depends on catching this exception.

### Remove safely with `Iterator.remove()`

Use the iterator's own `remove()`. It removes the element most recently returned by `next()`, and the iterator stays valid.

```java
Iterator<String> iterator = names.iterator();
while (iterator.hasNext()) {
    if (iterator.next().equals("Iddu")) {
        iterator.remove();
    }
}
```

Rules for `remove()`:

- Call `next()` first. `remove()` removes the element that `next()` just returned.
- Call it at most once per `next()`. Otherwise it throws `IllegalStateException`.

### Or use `removeIf`

For simple cases, `Collection.removeIf` (Java 8) is shorter:

```java
names.removeIf(name -> name.equals("Iddu"));
```

### `remove()` is not always supported

- `List.of(...)`, as used in `BasicIterableDemo`, creates an **unmodifiable** list. Calling `remove()` on its iterator throws `UnsupportedOperationException`.
- `OurGenericListIterator` does not override `remove()`, so it uses the default version from `Iterator`, which also throws `UnsupportedOperationException`.

## Arrays and the enhanced `for`-loop

Arrays can be used in an enhanced `for`-loop, but they do **not** implement `Iterable`.

```java
String[] names = {"Iddu", "Grace", "Rabia"};

for (String name : names) {
    System.out.println(name);
}
```

The compiler has a separate built-in rule for arrays. It does not call `iterator()`. This line will not compile:

```java
Iterable<String> iterable = names; // compile error: an array is not an Iterable
```

If you need an `Iterator` for an array of objects, wrap it in a list first: `Arrays.asList(names).iterator()`.

## Common mistakes

**1. Calling `next()` more than once per `hasNext()` check**

```java
while (iterator.hasNext()) {
    System.out.println(iterator.next());
    System.out.println(iterator.next()); // fails when the number of elements is odd
}
```

`hasNext()` only checked for one element. Call `next()` once per check, and store the result in a variable if you need to use it more than once.

**2. Calling `next()` without checking `hasNext()`**

This throws `NoSuchElementException` when the iterator has reached the end.

**3. Adding or removing elements inside an enhanced `for`-loop**

This can throw `ConcurrentModificationException`. Use `iterator.remove()` or `removeIf()` instead.

**4. Calling `remove()` at the wrong time**

Calling `remove()` before the first `next()`, or twice in a row, throws `IllegalStateException`.

**5. Expecting an iterator to restart**

An iterator cannot go back. Call `iterator()` again to get a fresh one.

**6. Assuming a fixed order**

The order comes from the collection. A `List` keeps its order, but a `HashSet` does not promise any particular order.

## Important details

- `Iterable` is in `java.lang`; `Iterator` is in `java.util`.
- `hasNext()` does not move the iterator. `next()` does.
- The iterator follows the iteration order provided by the underlying object. `Iterable` itself does not promise insertion order or sorted order.
- `Iterator.remove()` is an optional operation. It is unsupported by this custom iterator and throws `UnsupportedOperationException`.
- The custom list in this chapter has a fixed capacity of 100. It is intentionally simple so the focus stays on iteration; it is not a full replacement for `ArrayList`.
- This example does not implement fail-fast behaviour. If the list is modified during iteration, the iterator does not detect it and no `ConcurrentModificationException` is thrown.

## Key takeaways

- `Iterable` is the thing you can loop over. `Iterator` is the object that does the looping.
- The enhanced `for`-loop calls `iterator()`, `hasNext()`, and `next()` for you.
- To make your own class work in an enhanced `for`-loop, implement `Iterable<T>` and write `iterator()`.
- Every call to `iterator()` gives a new iterator with its own position, and an iterator only moves forward.
- To remove elements during a loop, use `Iterator.remove()` (only if the iterator supports it, because it is optional) or `removeIf()`, not the collection's `remove()`.

## Files in this section

- **`BasicIterableDemo.java`**: iterates over a standard `List` using both an explicit `Iterator` and an enhanced `for`-loop.
- **`OurGenericList.java`**: implements a simple generic list that also implements `Iterable<T>`.
- **`OurGenericListDemo.java`**: traverses the custom list manually and with an enhanced `for`-loop.
- **`IndependentIteratorsDemo.java`**: shows that each call to `iterator()` creates an iterator with its own traversal position.
