package controller;

import app.Main;
import app.Player;
import app.Vote;
import character.Character;
import character.Villager;
import character.WereWolf;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Bo kiem soat tro choi
 */
public class GameController {
    // danh sach cac nhan vat trong van game
    private static List<Character> characters;
    // user
    private static Player user;
    // luu lai so ngay da troi qua trong tro choi

    private static int day = 1;

    private GameController() {

    }

    public static void run() {
        setUp();
        System.out.println(" Character of " + user.getName() + " :" + user.getCharacter().getName());
        Main.pauseScreenCmd(2000);
        System.out.println("First Day!Everything good!");
        Main.pauseScreenCmd(1000);
        while (true) {
            nightTime();
            dayTime();
            day++;
        }
    }

    private static void dayTime() {
        System.out.println("So nguoi song sot:" + countSurvival());
        System.out.println("Day Time");
        Main.pauseScreenCmd(500);
    }

    public static void nightTime() {
        System.out.println("So nguoi song sot:" + countSurvival());
        System.out.println("Night Time");
        if (user.getCharacter() instanceof Villager) {
            System.out.println("You sleeping");
            Main.pauseScreenCmd(2000);
        }
        if (user.getCharacter() instanceof WereWolf) {
            System.out.println("Time Hunting");
            Main.pauseScreenCmd(1000);
            System.out.println("Vote Who Die Tonight");
        }
    }

    private static int countSurvival() {
        int count = 0;
        for (Character character : characters) {
            if (!character.isDead()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Chuan bi cac nhan vat trong tro choi
     */
    private static void setUp() {
        characters = new ArrayList<>();
        List<Character> defaultCharacter = defaultCharacterList(4, 2);
        // phan chia nhan vat cho cac player
        for (Player player : Admin.getPlayers()) {
            Character character = defaultCharacter.get(getRandomInt(0, defaultCharacter.size() - 1));
            character.setPlayer(player);
            player.setCharacter(character);
            defaultCharacter.remove(character);
            characters.add(player.getCharacter());
        }
        user = Admin.user;
        checkGameOverThread();
    }

    private static void checkGameOverThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Main.pauseScreenCmd(1000);
                        int countVillager = 0;
                        int countWereWolf = 0;
                        for (Character character : characters) {
                            if (character instanceof Villager && !character.isDead()) {
                                countVillager++;
                            }
                            if (character instanceof WereWolf && !character.isDead()) {
                                countWereWolf++;
                            }
                        }
                        if (countWereWolf == 0) {
                            join();
                            System.out.println("Game Over!Villager Tean Win");
                            Thread.sleep(10000);
                            System.exit(0);
                        }
                        if (countVillager <= countWereWolf) {
                            join();
                            System.out.println("Game Over!WereWolf Team Win");
                            Thread.sleep(10000);
                            System.exit(0);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    private static List<Character> defaultCharacterList(int countVillager, int countWereWolf) {
        List<Character> defaultCharacter = new ArrayList<>();
        for (int i = 0; i < countVillager; i++) {
            defaultCharacter.add(new Villager(null, "Villager"));
        }
        for (int i = 0; i < countWereWolf; i++) {
            defaultCharacter.add(new WereWolf(null, "WereWolf"));
        }
        return defaultCharacter;
    }

    private static int getRandomInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
