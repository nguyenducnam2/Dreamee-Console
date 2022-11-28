package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDal<T> {
    protected static Connection connection = getConnection();

    private static Connection getConnection() {
        try {
            String username = "sql6580591";
            String password = "2EecwhIrAq";
            String database = "sql6580591";
            String hostname = "sql6.freesqldatabase.com";
            String port = "3306";
            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?user=" + username;
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract List<T> findAll();

    public abstract Optional<T> findById(int id);

    public abstract T save(T t);

    public abstract void delete(T t);

    public abstract boolean exists(T t);

    public boolean existsById(int id) {
        throw new UnsupportedOperationException();
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
