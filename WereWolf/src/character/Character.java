package character;

import app.Player;

/**
 * Nhan vat
 */
public abstract class Character {
    // nguoi choi dieu khien
    private Player player;
    // ten nhan vat
    private String name;
    // trang thai dead
    private boolean dead;

    public Character() {
    }

    public Character(Player player, String name) {
        this.setPlayer(player);
        this.setName(name);
    }

    public abstract void useSkill();

    public abstract void useSkill(Character target);

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "Character{" +
                "player=" + player +
                ", name='" + name + '\'' +
                '}';
    }
}
