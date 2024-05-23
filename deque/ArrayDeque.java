package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int nextFirst;
    private int nextLast;
    private int size;
    private T[] items;
    private static final int E = 8;

    public ArrayDeque() {
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        items = (T[]) new Object[E];
    }
    private void resize(int s) {
        T[] t = (T[]) new Object[s];
        int size1 = size;
        for (int i = 0; i < size1; i++) {
            T size2 = get(i);
            t[i + 1] = size2;
        }
        items = t;
        nextFirst = 0;
        nextLast = size() + 1;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        size += 1;
        if (nextFirst == 0) {
            nextFirst = items.length;
        }
        nextFirst -= 1;
    }
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        if (nextLast == items.length) {
            nextLast = 0;
        }
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        int i = nextFirst + 1;
        if (i == items.length) {
            i = 0;
        }
        while (items[i] != null) {
            System.out.print(items[i] + " ");
            i += 1;
            if (i == items.length) {
                i = 0;
            }
        }
        System.out.println();
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (((double) size() / items.length) < 0.25) {
            resize(items.length / 2);
        }
        int i = nextFirst + 1;
        if (i >= items.length) {
            i = 0;
            nextFirst = -1;
        }
        T i1 = items[i];
        items[i] = null;
        size -= 1;
        nextFirst += 1;
        if (size == 0) {
            nextFirst = 0;
            nextLast = 1;
        }
        return i1;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (((double) size() / items.length) < 0.25) {
            resize(items.length / 2);
        }
        int i = nextLast - 1;
        T i1 = items[i];

        items[i] = null;
        size -= 1;
        nextLast -= 1;

        if (nextLast == 0) {
            nextLast = items.length;
        }
        if (size == 0) {
            nextFirst = 0;
            nextLast = 1;
        }
        return i1;
    }
    public T get(int index) {
        int i = items.length - nextFirst - 1;
        if (index < i) {
            return items[index + nextFirst + 1];
        }
        return items[index - i];
    }
    public boolean equals(Object o) {
        if (!(o instanceof Deque deque)) {
            return false;
        }
        if (this.size() != deque.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!(get(i).equals(deque.get(i)))) {
                return false;
            }
        }
        return true;
    }
    public Iterator<T> iterator() {
        return new IteratorLink();
    }
    private class IteratorLink implements Iterator<T> {
        private int i;
        private IteratorLink() {
            i = 0;
        }
        public boolean hasNext() {
            return i < size();
        }
        public T next() {
            T t = get(this.i);
            i += 1;
            return t;
        }
    }
}
