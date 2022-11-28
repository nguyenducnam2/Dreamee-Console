package controller;

import dal.MessRoomDal;
import dal.MessengerDal;
import entity.Messenger;
import view.admin.MessengerView;

public class MessController {
    private static MessRoomDal messRoomDal = new MessRoomDal();
    private static MessengerDal messengerDal = new MessengerDal();

    private MessController() {
    }

    public static void listRoom() {
        MessengerView.listRoom(messRoomDal.findAll());
    }

    public static void index(int messRoomId) {
        if (messRoomDal.existsById(messRoomId)) {
            MessengerView.messRoom = messRoomDal.findById(messRoomId).get();
            MessengerView.list = messengerDal.findByMessRoomId(messRoomId);
            MessengerView.index();
        }
    }

    public static void create(Messenger messenger) {
        messengerDal.save(messenger);
    }
}
