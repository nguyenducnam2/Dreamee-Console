package app;

import controller.Admin;
import controller.GameController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        while (true) {
            System.out.println("1.Play");
            System.out.println("2.Exit");
            System.out.print("Choose:");
            choose = Integer.parseInt(scanner.nextLine());
            if (choose == 1) {
                System.out.println("Enter your name:");
                // set up mac dinh 6 nguoi choi tat ca ( 5 bot + 1 user )
                Admin.user = new Player(999, scanner.nextLine());
                Admin.setUpPlayers(6);
                GameController.run();
            }
            if (choose == 2) {
                break;
            }
        }
    }

    public static void pauseScreenCmd(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}