package basepackage.generic;

import java.util.List;

public interface CRUD<T>
{
    T get(Long id);
    T get(String s);
    void save(T t);
    void update(T t);
    void delete(Long id);
    List<T> getAll();
}
