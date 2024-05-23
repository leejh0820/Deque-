package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> t;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        t = c;
    }
    public T max() {
        if (size() == 0) {
            return null;
        }
        T t1 = get(0);
        for (int i = 0; i < size(); i++) {
            if ((t.compare(get(i), t1) > 0)) {
                t1 = get(i);
            }
        }
        return t1;
    }
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T t2 = get(0);
        for (int i = 0; i < size(); i++) {
            if ((c.compare(get(i), t2) > 0)) {
                t2 = get(i);
            }
        }
        return t2;
    }
}

