/**  Demonstrates generic classes: one type parameter and multiple type parameters. */

public class GenericClassesDemo {

    /** A class whose field and methods use the same type parameter. */
    static class Box<T> {
        private T value;

        Box(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }

        void set(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Box[" + value + "]";
        }
    }

    /** A class with two independent type parameters. */
    static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    public static void main(String[] args) {
        Box<String> name = new Box<>("Java");
        String value = name.get(); // No cast is required.
        System.out.println(name + " -> " + value);

        Box<Integer> age = new Box<>(22);
        age.set(age.get() + 1);
        System.out.println(age);

        Pair<String, Integer> score = new Pair<>("Iddu", 69);
        System.out.println("Pair: " + score);
        System.out.println("Key: " + score.getKey());
        System.out.println("Value: " + score.getValue());

        // name is a Box<String>, so it cannot accept an Integer.
        // name.set(100); // Compile-time error
    }
}
