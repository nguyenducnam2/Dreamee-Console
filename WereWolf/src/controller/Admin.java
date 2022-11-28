package controller;

import app.Player;
import app.Vote;
import character.WereWolf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Nguoi Quan Tro
 */
public class Admin {
    // nguoi choi chinh
    public static Player user;
    // danh sach nguoi choi tham gia
    private static List<Player> players = new ArrayList<>();
    // danh sach phieu bau
    // danh sach phieu bau khi vote treo co buoi sang hoac la chon muc tieu de giet vao ban dem
    // quan tro se giu danh sach phieu bau,sau do ra quyet dinh
    private static List<Vote> voteList = new ArrayList<>();

    private Admin() {

    }

    /**
     * Chuan bi danh sach nguoi choi trong van dau ( user + bot )
     *
     * @param countPlayer so luong nguoi choi
     */
    public static void setUpPlayers(int countPlayer) {
        for (int i = 0; i < countPlayer - 1; i++) {
            players.add(new Player(i, "BOT " + i));
        }
        players.add(user);
    }

    /**
     * Quan tro lay thu phieu bau tu nguoi choi
     *
     * @param vote phieu bau se lay
     */
    public static void getVote(Vote vote) {
        voteList.add(vote);
    }

    /**
     * Mo mot cuoc bo phieu cho cac nguoi choi bot
     *
     * @return danh sach phieu vote
     */
    public static List<Vote> openVoteDayTime() {
        // bot bo phieu ngau nhien
        for (Player bot : players) {
            if (bot.getName().contains("BOT")) {
                while (true) {
                    Player target = players.get(getRandomInt(0, players.size() - 1));
                    if (target.getId() != bot.getId() && !target.getCharacter().isDead()) {
                        bot.vote(target, Vote.AGREE);
                        break;
                    }
                }
            }
        }
        return voteList;
    }

    public static List<Vote> openVoteHuntNightTime() {
        // bot bo phieu ngau nhien
        for (Player bot : players) {
            if (bot.getName().contains("BOT") && bot.getCharacter() instanceof WereWolf) {
                while (true) {
                    Player target = players.get(getRandomInt(0, players.size() - 1));
                    if (target.getId() != bot.getId() && !target.getCharacter().isDead() && !(target.getCharacter() instanceof WereWolf)) {
                        bot.vote(target, Vote.AGREE);
                        break;
                    }
                }
            }
        }
        return voteList;
    }

    public static void clearVoteList() {
        voteList.clear();
    }

    /**
     * Lay danh sach nguoi choi
     *
     * @return players
     */
    public static List<Player> getPlayers() {
        return players;
    }

    /**
     * Lay so nguyen ngau nhien
     *
     * @param min gia tri nho nhat
     * @param max gia tri lon nhat
     * @return so nguyen ngau nhien
     */
    private static int getRandomInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
