/** Demonstrates constructors that declare their own type parameters. */
public class GenericConstructorsDemo {

    /** A non-generic class can have a generic constructor. */
    static class Message {
        private final String label;
        private final String valueAsText;

        <T> Message(String label, T value) {
            this.label = label;
            this.valueAsText = String.valueOf(value);
        }

        @Override
        public String toString() {
            return label + ": " + valueAsText;
        }
    }

    /**
     * The class has type parameter T, while the constructor independently
     * declares U and restricts it to be a subtype of T.
     */
    static class Box<T> {
        private final T value;

        <U extends T> Box(U value) {
            this.value = value;
        }

        T get() {
            return value;
        }
    }

    public static void main(String[] args) {
        Message numberMessage = new Message("attempts", 3);
        Message textMessage = new Message("language", "Java");
        System.out.println(numberMessage);
        System.out.println(textMessage);

        Box<Number> integerInNumberBox = new Box<>(69); // U is Integer; T is Number.
        Box<CharSequence> textBox = new Box<>(new StringBuilder("Generics"));

        System.out.println("Number: " + integerInNumberBox.get());
        System.out.println("Text: " + textBox.get());
    }
}
