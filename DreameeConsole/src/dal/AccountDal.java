package dal;

import entity.Account;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDal extends AbstractDal<Account> {

    @Override
    public List<Account> findAll() {
        List<Account> list = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("Select * From account");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String ingame = resultSet.getString("ingame");
                double derik = resultSet.getDouble("derik");
                double derok = resultSet.getDouble("derok");
                list.add(new Account(id, name, password, ingame, derik, derok));
            }
            resultSet.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Account> findById(int id) {
        if (existsById(id)) {
            try {
                Statement statement = connection.createStatement();
                String query = "Select * From account Where id=" + id;
                ResultSet resultSet = statement.executeQuery(query);
                resultSet.next();
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String ingame = resultSet.getString("ingame");
                double derik = resultSet.getDouble("derik");
                double derok = resultSet.getDouble("derok");
                return Optional.of(new Account(id, name, password, ingame, derik, derok));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Optional<Account> findByNameAndPassword(String name, String password) {
        try {
            Statement statement = connection.createStatement();
            String query = "Select * From account Where name='" + name + "' AND password='" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ingame = resultSet.getString("ingame");
                double derik = resultSet.getDouble("derik");
                double derok = resultSet.getDouble("derok");
                return Optional.of(new Account(id, name, password, ingame, derik, derok));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account save(Account account) {
        try {
            if (!existsById(account.getId())) {
                return create(account);
            } else {
                return update(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Account create(Account account) throws Exception {
        Statement statement = connection.createStatement();
        String name = account.getName();
        String password = account.getPassword();
        String ingame = account.getIngame();
        double derik = account.getDerik();
        double derok = account.getDerok();
        String query = "Insert Into account(name,password,ingame,derik,derok) Values ('" + name + "','" + password + "','" + ingame + "'," + derik + "," + derok + ")";
        return statement.execute(query) ? account : null;
    }

    private Account update(Account account) throws Exception {
        Statement statement = connection.createStatement();
        String name = account.getName();
        String password = account.getPassword();
        String ingame = account.getIngame();
        double derik = account.getDerik();
        double derok = account.getDerok();
        String query = "Update account Set name='" + name + "',password='" + password + "',ingame='" + ingame + "',derik=" + derik + ",derok=" + derok + " Where id=" + account.getId();
        return statement.execute(query) ? account : null;
    }

    @Override
    public void delete(Account account) {
        try {
            Statement statement = connection.createStatement();
            String query = "Delete From account Where id=" + account.getId();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(Account account) {
        for (Account acc : findAll()) {
            if (acc.getId() == account.getId() && acc.getName().equalsIgnoreCase(account.getName()) && acc.getPassword().equalsIgnoreCase(account.getPassword()) && acc.getIngame().equalsIgnoreCase(account.getIngame()) && acc.getDerik() == account.getDerik() && acc.getDerok() == account.getDerok()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsById(int id) {
        for (Account acc : findAll()) {
            if (acc.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
