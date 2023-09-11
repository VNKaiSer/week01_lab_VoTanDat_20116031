package vn.edu.iuh.fit.lab_week01.respositories;

import java.sql.SQLException;
import java.util.List;

public interface IFRespository<T> {
    boolean insert(T object) throws SQLException;
    boolean update(String id, T object);
    T getOne(String id);
    List<T> getALL(Class<T> clazz);
    boolean delete(String id);
}
