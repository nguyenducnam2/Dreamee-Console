package app;

import controller.admin.AccountController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choose = 0;
        while (true) {
            System.out.println("1.Login Admin");
            System.out.println("2.Exit");
            choose = Integer.parseInt(sc.nextLine());
            if (choose == 1) {
                AccountController.login();
            }
            if (choose == 2) {
                break;
            }
        }
    }

    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}