package u02_iterable_and_iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OurGenericList<T> implements Iterable<T> {

    private static final int CAPACITY = 100;

    private final T[] items;
    private int size;

    @SuppressWarnings("unchecked")
    public OurGenericList() {
        items = (T[]) new Object[CAPACITY];
        size = 0;
    }

    public void add(T item) {
        if (size == items.length) {
            throw new IllegalStateException("List is full");
        }

        items[size++] = item;
    }

    public T getItemAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        return items[index];
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new OurGenericListIterator();
    }

    // This inner iterator can access the outer list's items and size.
    private class OurGenericListIterator implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }

            return items[index++];
        }
    }
}
