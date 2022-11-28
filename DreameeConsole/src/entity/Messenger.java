package entity;

import dal.AccountDal;

public class Messenger {
    private int id;
    private String content;
    private int accountId;
    private int messroomId;

    //bonus
    private Account account;

    public Messenger() {
    }

    public Messenger(int id, String content, int accountId, int messroomId) {
        this.setId(id);
        this.setContent(content);
        this.setAccountId(accountId);
        this.setMessroomId(messroomId);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getMessroomId() {
        return messroomId;
    }

    public void setMessroomId(int messroomId) {
        this.messroomId = messroomId;
    }

    @Override
    public String toString() {
        return "Messenger{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", accountId=" + accountId +
                ", messroomId=" + messroomId +
                '}';
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
