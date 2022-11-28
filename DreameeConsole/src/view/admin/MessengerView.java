package view.admin;

import app.Main;
import controller.MessController;
import dal.MessengerDal;
import entity.MessRoom;
import entity.Messenger;
import session.Session;

import java.util.List;
import java.util.Scanner;

public class MessengerView {
    public static MessRoom messRoom;
    public static List<Messenger> list;

    private MessengerView() {

    }

    public static void listRoom(List<MessRoom> list) {
        Main.clearConsole();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("List Messenger Room");
            list.forEach(o -> System.out.println("Id:" + o.getId() + "  " + o.getName()));
            System.out.println("\n\n");
            System.out.println("0.Exit");
            System.out.print("Enter id to join:");
            int messRoomId = Integer.parseInt(sc.nextLine());
            if (messRoomId == 0) {
                break;
            }
            MessController.index(messRoomId);
        }
    }

    public static void index() {
        Main.clearConsole();
        Scanner sc = new Scanner(System.in);
        String content = "";
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(10000);
                    Main.clearConsole();
                    System.out.println("----   " + messRoom.getName() + "   ----");
                    list = new MessengerDal().findByMessRoomId(messRoom.getId());
                    list.forEach(o -> {
                        System.out.println(o.getAccount().getIngame() + ": " + o.getContent());
                    });
                    System.out.println("0.Out Room");
                    System.out.print("Enter content:");
                }
            } catch (Exception e) {

            }
        }).start();
        while (true) {
            content = sc.nextLine();
            if (content.equals("0")) {
                break;
            }
            MessController.create(new Messenger(0, content, Session.account.getId(), messRoom.getId()));
            Main.clearConsole();
            System.out.println("----   " + messRoom.getName() + "   ----");
            list = new MessengerDal().findByMessRoomId(messRoom.getId());
            list.forEach(o -> {
                System.out.println(o.getAccount().getIngame() + ": " + o.getContent());
            });
            System.out.println("0.Out Room");
            System.out.print("Enter content:");
        }
    }
}
