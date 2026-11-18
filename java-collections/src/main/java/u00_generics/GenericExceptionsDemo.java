package u00_generics;
import java.io.IOException;

/**
 * Demonstrates generic APIs that preserve the concrete checked exception type.
 *
 * Java does not allow a generic class to extend Throwable (Exception/Error).
 * Instead, generic methods and functional interfaces can work with exception
 * type parameters bounded by Exception.
 *
 */
public class GenericExceptionsDemo {

    @FunctionalInterface
    interface CheckedSupplier<T, E extends Exception> {
        T get() throws E;
    }

    /** The caller sees the actual exception type E in the throws clause. */
    static <T, E extends Exception> T execute(CheckedSupplier<T, E> supplier) throws E {
        return supplier.get();
    }

    /** A generic method can rethrow an exception while preserving its type. */
    static <E extends Exception> void rethrow(E exception) throws E {
        throw exception;
    }

    public static void main(String[] args) {
        try {
            String result = execute(() -> "Loaded successfully");
            System.out.println(result);

            execute(() -> {
                throw new IOException("Simulated file-reading failure");
            });
        } catch (IOException exception) {
            System.out.println("Caught the original checked type: " + exception.getMessage());
        }

        try {
            rethrow(new IOException("Rethrown IOException"));
        } catch (IOException exception) {
            System.out.println("Rethrown: " + exception.getMessage());
        }

        // Illegal Java: a type parameter cannot be used to create a generic
        // exception class because generic classes may not extend Throwable.
        // class GenericException<T> extends Exception { }
        // Also, a type variable cannot be used as a catch parameter:
        // catch (E exception) { ... } // not legal
    }
}
