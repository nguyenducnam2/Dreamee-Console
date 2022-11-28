package controller.admin;

import dal.AccountDal;
import entity.Account;
import session.Session;
import view.admin.AccountView;

import java.util.Optional;

public class AccountController {

    private static AccountDal accountDal = new AccountDal();

    private AccountController() {
    }

    public static void login() {
        AccountView.login();
    }

    public static void processLogin(String name, String password) {
        Optional<Account> account = accountDal.findByNameAndPassword(name, password);
        if (account != null) {
            Session.account = account.get();
            index();
        } else {
            loginError();
        }
    }

    public static void loginError() {
        AccountView.loginError();
    }

    public static void index() {
        AccountView.setList(accountDal.findAll());
        AccountView.index();
    }

    public static void create() {
        Account account = new Account();
        AccountView.create(account);
    }

    public static void update(int id) {
        if (accountDal.existsById(id)) {
            Account account = accountDal.findById(id).get();
            AccountView.update(account);
        } else {
            System.out.println("Id does not exists");
        }
    }

    public static void delete(int id) {
        if (accountDal.existsById(id)) {
            accountDal.delete(accountDal.findById(id).get());
        } else {
            System.out.println("Id does not exists");
        }
    }

    public static void save(Account account) {
        accountDal.save(account);
    }

}
