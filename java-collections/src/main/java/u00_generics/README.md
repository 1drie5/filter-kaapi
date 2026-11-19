# 00 - Java Generics

Generics let classes, interfaces, and methods work with different reference types while keeping type checks at compile time. They reduce casting and help prevent values of the wrong type from being used.

## Examples

| File                             | Main ideas                                                            |
| -------------------------------- | --------------------------------------------------------------------- |
| `GenericClassesDemo.java`        | Generic classes, one or multiple type parameters, type safety         |
| `GenericInterfacesDemo.java`     | Generic interfaces and different implementations of the same contract |
| `GenericMethodsDemo.java`        | Generic methods, bounded methods, type inference, type witnesses      |
| `BoundedTypeParametersDemo.java` | Upper bounds, multiple bounds, and comparable bounds                  |
| `GenericConstructorsDemo.java`   | Generic constructors in generic and non-generic classes               |
| `GenericsInEnumDemo.java`        | Enums implementing generic interfaces and declaring generic methods   |
| `WildcardDemo.java`              | Unbounded, upper-bounded, and lower-bounded wildcards; PECS           |
| `TypeErasureDemo.java`           | Type erasure, reflection, and bridge methods                          |
| `GenericExceptionsDemo.java`     | Generic APIs that preserve checked exception types                    |

## Core concepts

### Type parameters

A type parameter is a placeholder for a type. Common names include `T` (type), `E` (element), `K` (key), and `V` (value). These names are conventions, not Java keywords.

```java
class Box<T> {              // T belongs to the class
    private T value;
}

interface Pair<K, V> { }    // A declaration can have more than one type parameter

static <T> T identity(T value) { // T belongs to this method
    return value;
}
```

Type arguments must be reference types. Use wrapper types such as `Integer` and `Double`, not primitive types such as `int` and `double`.

### Generic classes and interfaces

A class or interface declares type parameters after its name, such as `Box<T>` or `Repository<T, ID>`. The chosen type arguments are then used consistently throughout that class or interface.

A class implementing a generic interface can keep the type parameters generic (`Repository<T, ID>`) or supply concrete types (`Repository<Book, String>`). Different implementations can follow the same interface while using different internal data structures.

**Important:** Parameterized types are invariant. `List<Integer>` is not a subtype of `List<Number>`, even though `Integer` is a subtype of `Number`. Use wildcards when a method needs to accept related generic types.

### Generic methods and constructors

A generic method declares its type parameters before the return type:

```java
static <T> void print(T value) { }
```

The compiler often infers the type argument from the method arguments or target type. An explicit type witness, such as `ClassName.<String>method(...)`, is available when inference needs guidance.

A generic constructor declares its own type parameter before the constructor name. That parameter is independent of any type parameter on the enclosing class. For example, in `class Box<T>`, a constructor written as `<U extends T> Box(U value)` introduces `U` and restricts it to `T` or a subtype of `T`.

### Bounded type parameters

A bound restricts which types can be used as a type argument and tells the compiler which operations are available.

- `T extends Number` accepts `Number` and its subclasses.
- `T extends Comparable<T>` requires `T` to be comparable with another `T`.
- `T extends Number & Comparable<T>` requires both bounds. When a class bound is present, it must come before interface bounds.

Java uses `extends` for both class and interface bounds. A type parameter can have at most one class bound, followed by any number of interface bounds.

### Wildcards and PECS

A wildcard (`?`) represents an unknown type argument. Use a bounded wildcard when a method needs to accept different parameterizations of a generic type.

| Form                     | Meaning                                               | Typical use                                               |
| ------------------------ | ----------------------------------------------------- | --------------------------------------------------------- |
| `List<?>`                | A list of an unknown element type                     | Read elements as `Object` without depending on their type |
| `List<? extends Number>` | A list whose element type is `Number` or a subtype    | Read values as `Number`                                   |
| `List<? super Integer>`  | A list whose element type is `Integer` or a supertype | Add `Integer` values safely                               |

The rule of thumb is **PECS: Producer Extends, Consumer Super**. Use `extends` when a structure produces values for you to read. Use `super` when it consumes values you want to add.

With `List<? extends Number>`, you cannot add any non-null value (not even an arbitrary `Number`): the actual list might be a `List<Integer>` or `List<Double>`. With `List<? super Integer>`, you can add an `Integer`, but a value read from the list is only guaranteed to be an `Object`.

### Generics and enums

Java does not allow an enum to declare its own type parameters (for example, `enum Result<T>` is illegal). An enum can implement a generic interface with a concrete type, such as `HttpStatus implements HasCode<Integer>`, and it can declare generic methods of its own.

### Type erasure

Java implements generics using type erasure. Generic type parameters are removed from the ordinary runtime types used by the JVM:

- An unbounded type parameter such as `T` is erased to `Object`.
- A bounded type parameter is erased to its leftmost bound.
- `Box<String>` and `Box<Integer>` share the same runtime class, `Box.class`.

The class file retains generic declaration signatures as metadata. Reflection can inspect that metadata with methods such as `getGenericType()`; this does not mean an individual object retains its actual type argument at runtime.

The compiler may generate **bridge methods** so that overriding continues to work after erasure. These methods are marked as bridge and synthetic in reflection.

Because the runtime does not know the actual type argument in many generic contexts, Java does not allow operations such as `new T[10]`, `value instanceof T`, or `Box<String>.class`.

### Generics and exceptions

A generic class cannot extend `Throwable` (including `Exception` or `Error`). Exception handling relies on concrete runtime exception classes, so Java prohibits generic exception subclasses.

Generic methods and functional interfaces can still declare an exception type parameter bounded by `Exception`, such as `E extends Exception`. A method can declare `throws E` to propagate the specific checked exception type supplied by the caller. A type variable cannot be used as the type in a `catch` parameter.

## Notes when reading the examples

- The examples use wrapper types such as `Integer` because Java generics do not accept primitives.
- `Comparable<T>` defines a type's natural comparison. `Comparable<? super T>` is more flexible because it also supports a type whose comparison is defined in a supertype.
- `Arrays.asList(...)` returns a fixed-size list. It allows element replacement but does not allow adding or removing elements.
- `HashMap` does not guarantee iteration order. Do not rely on the order returned by `findAll()` in the map-backed repository.
- The library example searches its `ArrayList` by ISBN, so lookup is linear (`O(n)`). A `HashMap` is generally better suited to frequent lookup by key.
- The Java files declare `package u00_generics;`. Keep them in a matching `u00_generics` package directory under the project's Java source root.

## Suggested study order

1. Generic classes and interfaces
2. Generic methods and bounded type parameters
3. Generic constructors
4. Generics with enums
5. Wildcards and PECS
6. Type erasure and bridge methods
7. Generics and exceptions
