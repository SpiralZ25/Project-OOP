package bookmanagement.storage;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    private List<T> items;

    public Repository() {
        items = new ArrayList<T>();
    }

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}