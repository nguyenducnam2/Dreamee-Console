package app;

import character.Character;
import controller.Admin;

/**
 * Nguoi choi
 */
public class Player {
    // ma so dinh danh
    private int id;
    // ten nguoi choi
    private String name;
    // nhan vat nguoi choi dieu khien
    private Character character;

    public Player() {
    }

    public Player(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    /**
     * Bo phieu nguoi choi khac
     *
     * @param target nguoi choi duoc bo phieu
     * @param vote   y kien bo phieu
     */
    public void vote(Player target, String vote) {
        if (vote.equals(Vote.AGREE)) {
            // nop phieu bau cho quan tro
            Admin.getVote(new Vote(target, this, Vote.AGREE));
        } else if (vote.equals(Vote.DIS_AGREE)) {
            Admin.getVote(new Vote(target, this, Vote.DIS_AGREE));
        } else {
            Admin.getVote(new Vote(target, this, Vote.NO_VOTE));
        }
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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "id=" + getId() + ", name='" + getName() + '\'';
    }
}
