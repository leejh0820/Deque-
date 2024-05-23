
package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    @Override
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

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(Node prev, T itme, Node next) {
            this.prev = prev;
            this.item = itme;
            this.next = next;
        }
    }
    private Node sentinel;
    private int size;

    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node node = sentinel.next;
        while (node != sentinel) {
            System.out.println(node.item + " ");
            node = node.next;
        }
    }
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            Node node = sentinel.next;
            sentinel.next = sentinel.next.next;
            node.next = null;
            node.prev = null;
            sentinel.next.prev = sentinel;
            size -= 1;
            return node.item;
        }
    }
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        } else {
            Node node = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            node.prev = null;
            node.next = null;
            sentinel.prev.next = sentinel;
            size -= 1;
            return node.item;
        }
    }
    public T get(int index) {
        if (index > this.size() - 1) {
            return null;
        }
        Node node = sentinel.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
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
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(0, index, sentinel.next);
    }
    private T getRecursiveHelper(int i, int index, Node node) {
        if (i == index) {
            return node.item;
        }
        return getRecursiveHelper(i + 1, index, node.next);
    }
}
