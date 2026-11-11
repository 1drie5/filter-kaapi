
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Demonstrates generic interfaces with different implementations. */
public class GenericInterfacesDemo {

    // Generic interface: T represents the entity, ID represents its identifier.
    interface Repository<T, ID> {
        void save(ID id, T entity);

        Optional<T> findById(ID id);

        List<T> findAll();
    }

    // BANK: Stores users using a HashMap.
    static class InMemoryRepository<T, ID> implements Repository<T, ID> {

        private final Map<ID, T> data = new HashMap<>();

        @Override
        public void save(ID id, T entity) {
            data.put(id, entity);
        }

        @Override
        public Optional<T> findById(ID id) {
            return Optional.ofNullable(data.get(id));
        }

        @Override
        public List<T> findAll() {
            return new ArrayList<>(data.values());
        }
    }

    static class User {
        private final String name;

        User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "'}";
        }
    }

    // LIBRARY: Stores books using an ArrayList.
    static class LibraryRepository implements Repository<Book, String> {

        private final List<Book> books = new ArrayList<>();

        @Override
        public void save(String isbn, Book book) {
            // Update the book if its ISBN already exists.
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).isbn.equals(isbn)) {
                    books.set(i, book);
                    return;
                }
            }

            books.add(book);
        }

        @Override
        public Optional<Book> findById(String isbn) {
            for (Book book : books) {
                if (book.isbn.equals(isbn)) {
                    return Optional.of(book);
                }
            }

            return Optional.empty();
        }

        @Override
        public List<Book> findAll() {
            return new ArrayList<>(books);
        }
    }

    static class Book {
        private final String isbn;
        private final String title;

        Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        @Override
        public String toString() {
            return "Book{isbn='" + isbn + "', title='" + title + "'}";
        }
    }

    public static void main(String[] args) {

        // 1. BANK: HashMap-backed repository
        Repository<User, Integer> users = new InMemoryRepository<>();

        users.save(1, new User("Iddu"));
        users.save(2, new User("Grace"));

        System.out.println("===== BANK USER REPOSITORY =====");
        System.out.println("Find user: " + users.findById(1).orElse(null));
        System.out.println("All users: " + users.findAll());


        // 2. LIBRARY: ArrayList-backed repository
        Repository<Book, String> library = new LibraryRepository();

        library.save("9780132350884",
                new Book("9780132350884", "Clean Code"));

        library.save("9780134685991",
                new Book("9780134685991", "Effective Java"));

        System.out.println("\n===== LIBRARY BOOK REPOSITORY =====");
        System.out.println("Find book: " +
                library.findById("9780132350884").orElse(null));

        System.out.println("All books: " + library.findAll());
    }
}
