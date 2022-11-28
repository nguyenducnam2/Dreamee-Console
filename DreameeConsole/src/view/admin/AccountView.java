package view.admin;

import app.Main;
import controller.MessController;
import controller.admin.AccountController;
import dal.AccountDal;
import entity.Account;
import session.Session;

import java.util.List;
import java.util.Scanner;

public class AccountView {
    private static List<Account> list;

    private AccountView() {

    }

    public static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Login Admin");
        System.out.print("Name:");
        String name = sc.nextLine();
        System.out.print("Password:");
        String password = sc.nextLine();
        AccountController.processLogin(name, password);
    }

    public static void loginError() {
        System.out.println("Login Fail");
    }

    public static void index() {
        Main.clearConsole();
        while (true) {
            if (Session.account == null)
                break;
            dashboard();
        }
    }

    private static void dashboard() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Admin: " + Session.account.getName() + "\n\n");
        System.out.println("0.Log Out");
        System.out.println("1.Account Manger");
        System.out.println("2.Room Chat");
        int choose = Integer.parseInt(sc.nextLine());
        switch (choose) {
            case 0:
                Session.account = null;
                System.out.println("You Logout");
                break;
            case 1:
                while (true) {
                    System.out.println("1.Account List");
                    System.out.println("2.Add Account");
                    System.out.println("3.Update By Id");
                    System.out.println("4.Delete By Id");
                    System.out.println("5.Exit Account Manager");
                    int subchoose = Integer.parseInt(sc.nextLine());
                    if (subchoose == 1) {
                        Main.clearConsole();
                        System.out.println("Account List");
                        list = new AccountDal().findAll();
                        System.out.println("Total: " + list.size());
                        list.forEach(System.out::println);
                    }
                    if (subchoose == 2) {
                        AccountController.create();
                    }
                    if (subchoose == 3) {
                        System.out.println("Enter id:");
                        AccountController.update(Integer.parseInt(sc.nextLine()));
                    }
                    if (subchoose == 4) {
                        System.out.println("Enter id:");
                        AccountController.delete(Integer.parseInt(sc.nextLine()));
                    }
                    if (subchoose == 5) {
                        break;
                    }
                }
                break;
            case 2:
                MessController.listRoom();
                break;
        }
    }

    public static void create(Account account) {
        Main.clearConsole();
        Scanner sc = new Scanner(System.in);
        System.out.println("Create Account");
        System.out.print("Name: ");
        String name = sc.nextLine();
        String password;
        while (true) {
            System.out.print("Password:");
            password = sc.nextLine();
            System.out.print("ReEnter Password:");
            String repassword = sc.nextLine();
            if (password.equals(repassword)) {
                break;
            }
        }
        System.out.print("Ingame:");
        String ingame = sc.nextLine();
        System.out.print("Derik:");
        double derik = Double.parseDouble(sc.nextLine());
        System.out.print("Derok:");
        double derok = Double.parseDouble(sc.nextLine());
        account.setName(name);
        account.setPassword(password);
        account.setIngame(ingame);
        account.setDerik(derik);
        account.setDerok(derok);
        AccountController.save(account);
    }

    public static void update(Account account) {
        Scanner scanner = new Scanner(System.in);
        int choose;
        while (true) {
            Main.clearConsole();
            System.out.println(account);
            System.out.println("1.Update Name");
            System.out.println("2.Update Ingame");
            System.out.println("3.Update Password");
            System.out.println("4.Update Derik");
            System.out.println("5.Update Derok");
            System.out.println("6.Save Update");
            switch ((choose = Integer.parseInt(scanner.nextLine()))) {
                case 1:
                    System.out.print("Name:");
                    account.setName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Ingame:");
                    account.setIngame(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Password:");
                    account.setPassword(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Derik:");
                    account.setDerik(Double.parseDouble(scanner.nextLine()));
                    break;
                case 5:
                    System.out.print("Derok:");
                    account.setDerok(Double.parseDouble(scanner.nextLine()));
                    break;
            }
            if (choose == 6) {
                AccountController.save(account);
                break;
            }
        }
    }

    public static void setList(List<Account> list) {
        AccountView.list = list;
    }
}
