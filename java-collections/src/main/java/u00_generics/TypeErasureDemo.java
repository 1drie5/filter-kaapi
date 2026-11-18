package u00_generics;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/** Demonstrates type erasure and compiler-generated bridge methods. */
public class TypeErasureDemo {

    static class Box<T> {
        private T value;

        Box(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }
    }

    interface Processor<T> {
        T process(T input);
    }

    static class StringProcessor implements Processor<String> {
        @Override
        public String process(String input) {
            return input.toUpperCase();
        }
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        Box<String> stringBox = new Box<>("Java");
        Box<Integer> integerBox = new Box<>(69);
        System.out.println("String box: " + stringBox.get());
        System.out.println("Integer box: " + integerBox.get());

        // At runtime, both variables refer to the same raw class: Box.class.
        System.out.println("Same runtime class: " + (stringBox.getClass() == integerBox.getClass()));

        Field valueField = Box.class.getDeclaredField("value");
        Method getter = Box.class.getDeclaredMethod("get");
        System.out.println("Erased field type: " + valueField.getType().getTypeName());
        System.out.println("Generic field signature: " + valueField.getGenericType().getTypeName());
        System.out.println("Erased getter return type: " + getter.getReturnType().getTypeName());
        System.out.println("Generic getter signature: " + getter.getGenericReturnType().getTypeName());

        // The compiler generates a bridge method process(Object) so that
        // StringProcessor remains compatible with the erased Processor interface.
        System.out.println("Methods declared by StringProcessor:");
        Arrays.stream(StringProcessor.class.getDeclaredMethods())
                .forEach(method -> System.out.println(
                        "  " + method
                                + " | bridge=" + method.isBridge()
                                + " | synthetic=" + method.isSynthetic()));

        System.out.println(new StringProcessor().process("hello generics"));

        // These are not allowed because T is erased and cannot be checked this way:
        // if (stringBox.get() instanceof T) { ... }
        // T[] array = new T[10];
        // Box<String>.class
    }
}
