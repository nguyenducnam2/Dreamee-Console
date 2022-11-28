package character;

import app.Player;

/**
 * Nhan vat dan lang
 */
public class Villager extends Character {
    public Villager() {
    }

    public Villager(Player player, String name) {
        super(player, name);
    }

    @Override
    public void useSkill() {
        System.out.println("Villager don't has skill");
    }

    @Override
    public void useSkill(Character target) {
        System.out.println("Villager don't has skill");
    }
}
