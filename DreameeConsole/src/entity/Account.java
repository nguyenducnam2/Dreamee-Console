package entity;

import java.util.Scanner;

public class Account {
    private int id;
    private String name;
    private String password;
    private String ingame;
    private double derik;
    private double derok;

    public Account() {
    }

    public Account(int id, String name, String password, String ingame, double derik, double derok) {
        this.id = id;
        this.setName(name);
        this.setPassword(password);
        this.setIngame(ingame);
        this.setDerik(derik);
        this.setDerok(derok);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIngame() {
        return ingame;
    }

    public void setIngame(String ingame) {
        this.ingame = ingame;
    }

    public double getDerik() {
        return derik;
    }

    public void setDerik(double derik) {
        this.derik = derik;
    }

    public double getDerok() {
        return derok;
    }

    public void setDerok(double derok) {
        this.derok = derok;
    }

    @Override
    public String toString() {
        return "Id:" + id + " " + "Name:" + name + " Ingame:" + ingame + " Derik:" + derik + " Derok:" + derok;
    }
}
