package character;

import app.Player;

public class WereWolf extends Character {
    public WereWolf() {
    }

    public WereWolf(Player player, String name) {
        super(player, name);
    }

    @Override
    public void useSkill() {
        System.out.println("WereWolf need target to kill");
    }

    @Override
    public void useSkill(Character target) {
        kill(target);
    }

    private void kill(Character target) {
        target.setDead(true);
    }
}
