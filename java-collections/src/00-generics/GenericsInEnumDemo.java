/**
 * Demonstrates how enums can participate in generic APIs.
 *
 * Important: Java does NOT allow declaring a generic enum such as enum Result<T>.
 * An enum can, however, implement a generic interface with a concrete type,
 * and it can declare generic methods.
 *
 */
public class GenericsInEnumDemo {

    interface HasCode<T> {
        T code();
    }

    enum HttpStatus implements HasCode<Integer> {
        OK(200),
        NOT_FOUND(404),
        SERVER_ERROR(500);

        private final Integer code;

        HttpStatus(Integer code) {
            this.code = code;
        }

        @Override
        public Integer code() {
            return code;
        }

        /** An enum may also declare a generic method. */
        public <T> T convertCode(CodeConverter<T> converter) {
            return converter.convert(code);
        }
    }

    @FunctionalInterface
    interface CodeConverter<T> {
        T convert(Integer code);
    }

    public static void main(String[] args) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        Integer numericCode = status.code();
        String textCode = status.convertCode(code -> "HTTP-" + code);

        System.out.println("Status: " + status);
        System.out.println("Numeric code: " + numericCode);
        System.out.println("Text code: " + textCode);

        // Illegal Java syntax; enums cannot declare their own type parameters:
        // enum Result<T> { SUCCESS, FAILURE }
    }
}
