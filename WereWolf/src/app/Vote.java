package app;

/**
 * Phieu bau
 */
public class Vote {
    public static final String AGREE = "AGREE";
    public static final String DIS_AGREE = "DIS AGREE";
    public static final String NO_VOTE = "NO VOTE";
    // nguoi choi duoc vote
    private Player target;
    // nguoi choi vote phieu bau nay
    private Player voter;
    // y kien vote (dong y,phan doi,khong vote)
    private String vote;

    public Vote() {
    }

    public Vote(Player target, Player voter, String vote) {
        this.setTarget(target);
        this.setVoter(voter);
        this.setVote(vote);
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public Player getVoter() {
        return voter;
    }

    public void setVoter(Player voter) {
        this.voter = voter;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "target=" + target +
                ", voter=" + voter +
                ", vote='" + vote + '\'' +
                '}';
    }
}
