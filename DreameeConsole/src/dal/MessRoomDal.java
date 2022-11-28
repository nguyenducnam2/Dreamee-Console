package dal;

import entity.Account;
import entity.MessRoom;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessRoomDal extends AbstractDal<MessRoom> {
    @Override
    public List<MessRoom> findAll() {
        List<MessRoom> list = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("Select * From messroom");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                list.add(new MessRoom(id, name));
            }
            resultSet.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<MessRoom> findById(int id) {
        if (existsById(id)) {
            try {
                Statement statement = connection.createStatement();
                String query = "Select * From messroom Where id=" + id;
                ResultSet resultSet = statement.executeQuery(query);
                resultSet.next();
                String name = resultSet.getString("name");
                return Optional.of(new MessRoom(id, name));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public MessRoom save(MessRoom messRoom) {
        return null;
    }

    private MessRoom create(MessRoom messRoom) throws Exception {
        Statement statement = connection.createStatement();
        String name = messRoom.getName();
        String query = "Insert Into messroom(name) Values ('" + name + "')";
        return statement.execute(query) ? messRoom : null;
    }

    private MessRoom update(MessRoom messRoom) throws Exception {
        Statement statement = connection.createStatement();
        String name = messRoom.getName();
        String query = "Update messroom Set name='" + name + "' Where id=" + messRoom.getId();
        return statement.execute(query) ? messRoom : null;
    }

    @Override
    public void delete(MessRoom messRoom) {
        try {
            Statement statement = connection.createStatement();
            String query = "Delete From messroom Where id=" + messRoom.getId();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean exists(MessRoom messRoom) {
        for (MessRoom room : findAll()) {
            if (room.getId() == messRoom.getId() && room.getName().equals(messRoom.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsById(int id) {
        for (MessRoom room : findAll()) {
            if (room.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
