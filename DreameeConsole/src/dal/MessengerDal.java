package dal;

import entity.Messenger;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessengerDal extends AbstractDal<Messenger> {
    @Override
    public List<Messenger> findAll() {
        List<Messenger> list = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("Select * From messenger");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                int accountId = resultSet.getInt("accountid");
                int messroomid = resultSet.getInt("messroomid");
                Messenger messenger = new Messenger(id, content, accountId, messroomid);
                messenger.setAccount(new AccountDal().findById(accountId).get());
                list.add(messenger);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Messenger> findById(int id) {
        if (existsById(id)) {
            try {
                Statement statement = connection.createStatement();
                String query = "Select * From messenger Where id=" + id;
                ResultSet resultSet = statement.executeQuery(query);
                resultSet.next();
                String content = resultSet.getString("content");
                int accountId = resultSet.getInt("accountid");
                int messroomid = resultSet.getInt("messroomid");
                Messenger messenger = new Messenger(id, content, accountId, messroomid);
                messenger.setAccount(new AccountDal().findById(accountId).get());
                return Optional.of(messenger);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Messenger> findByMessRoomId(int messRoomId) {
        if (new MessRoomDal().existsById(messRoomId)) {
            List<Messenger> list = new ArrayList<>();
            try {
                Statement statement = connection.createStatement();
                String query = "Select * From messenger Where messroomid=" + messRoomId;
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String content = resultSet.getString("content");
                    int accountId = resultSet.getInt("accountid");
                    Messenger messenger = new Messenger(id, content, accountId, messRoomId);
                    messenger.setAccount(new AccountDal().findById(accountId).get());
                    list.add(messenger);
                }
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Messenger save(Messenger messenger) {
        try {
            if (!existsById(messenger.getId())) {
                create(messenger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Messenger create(Messenger messenger) throws Exception {
        Statement statement = connection.createStatement();
        String query = "Insert Into messenger(content,accountid,messroomid) Values ('" + messenger.getContent() + "','" + messenger.getAccountId() + "','" + messenger.getMessroomId() + "')";
        return statement.execute(query) ? messenger : null;
    }

    @Override
    public void delete(Messenger messenger) {
        try {
            Statement statement = connection.createStatement();
            String query = "Delect From messenger Where id=" + messenger.getId();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(Messenger messenger) {
        return false;
    }

    @Override
    public boolean existsById(int id) {
        for (Messenger messenger : findAll()) {
            if (messenger.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
