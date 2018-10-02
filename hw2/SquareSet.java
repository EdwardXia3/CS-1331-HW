import java.util.Collection;
import java.util.Set;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Creates a class which implements the Set interface to be utilized
 * for the Square class
 * @author exia3
 * @version 1.0
 */
public class SquareSet implements Set<Square> {
    private Square[] arr;
    /**
     * A public constructor which initializes a backing array of type
     * Square and stores it as an instance variable.
     */
    public SquareSet() {
        this.arr = new Square[0];
    }
    /**
     * A public constructor which initializes an array of type Square
     * and inserts each non-null valid Square from Collection c.
     *
     * @param c a Collection of type Square containing Square elements
     */
    public SquareSet(Collection<Square> c) {
        this.arr = new Square[0];
        for (Square i:c) {
            if (i != null) {
                add(i);
            }
        }
    }
    @Override
    public int size() {
        int arraysize = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                arraysize++;
            }
        }
        return arraysize;
    }
    @Override
    public boolean addAll(Collection<? extends Square> c) {
        Square[] initial = this.arr;
        for (Square i:c) {
            add(i);
        }
        return (!(initial.equals(this.arr)));
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!(this.contains(o))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Set)) {
            return false;
        }
        Set that = (Set) o;
        if (!(this.size() == that.size())) {
            return false;
        }
        return this.containsAll(that);
    }
    @Override
    public boolean add(Square s) {
        if (s.equals(null)) {
            throw new NullPointerException();
        }
        if (s.toString().length() != 2) {
            throw new InvalidSquareException("" + s.getFile() + s.getRank());
        }
        if (!(s.getFile() >= 'a' && s.getFile() <= 'h')) {
            throw new InvalidSquareException("" + s.getFile() + s.getRank());
        }
        if (!(s.getRank() >= '1' && s.getRank() <= '8')) {
            throw new InvalidSquareException("" + s.getFile() + s.getRank());
        }
        if (this.contains(s)) {
            return false;
        } else {
            Square[] a = new Square[arr.length + 1];
            for (int i = 0; i < arr.length; i++) {
                a[i] = arr[i];
            }
            a[arr.length] = s;
            arr = a;
            return true;
        }
    }
    @Override
    public <Square> Square[] toArray(Square[] a) {
        if (a.length < this.size()) {
            Object[] b = new Object[this.size()];
            for (int i = 0; i < a.length; i++) {
                b[i] = a[i];
            }
            a = (Square[]) b;
        }
        for (int i = 0; i < this.size(); i++) {
            a[i] = (Square) arr[i];
        }
        return (Square[]) a;
    }
    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        array = this.arr;
        return array;
    }
    @Override
    public boolean contains(Object o) {
        boolean contain = false;
        for (int i = 0; i < arr.length; i++) {
            if (o.equals(arr[i])) {
                contain = true;
            }
        }
        return contain;
    }
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterator<Square> iterator() {
        Iterator<Square> it = new Iterator<Square>() {
            private int cursor = 0;
            @Override
            public Square next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Square ans = arr[cursor];
                    cursor++;
                    return ans;
                }
            }
            @Override
            public boolean hasNext() {
                return cursor < size();
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.size(); i++) {
            hash = hash + (arr[i]).hashCode();
        }
        return hash;
    }
    @Override
    public boolean remove(Object o) {
        boolean removed = false;
        if (this.contains(o)) {
            Square[] a = new Square[arr.length - 1];
            for (int i = 0; i < arr.length; i++) {
                if (!(((Square) o).equals(arr[i]))) {
                    if (removed) {
                        a[i - 1] = arr[i];
                    } else {
                        a[i] = arr[i];
                    }
                } else {
                    removed = true;
                }
            }
            arr = a;
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
}