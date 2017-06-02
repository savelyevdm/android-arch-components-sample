package ru.developsdm.moneywithlivedata.model;

import java.util.UUID;

/**
 * Created by Daniil Savelyev in 2017.
 */
public class Account {

    private String uuid;
    private String user;
    private int firstDeposit;
    private int secondDeposit;

    private Account(String uuid, String username, int firstDeposit, int secondDeposit) {
        this.uuid = uuid;
        this.user = username;
        this.firstDeposit = firstDeposit;
        this.secondDeposit = secondDeposit;
    }

    public static Account newInstance(String username, int firstDeposit, int secondDeposit) {
        return new Account(UUID.randomUUID().toString(), username, firstDeposit, secondDeposit);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getFirstDeposit() {
        return firstDeposit;
    }

    public void setFirstDeposit(int firstDeposit) {
        this.firstDeposit = firstDeposit;
    }

    public int getSecondDeposit() {
        return secondDeposit;
    }

    public void setSecondDeposit(int secondDeposit) {
        this.secondDeposit = secondDeposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (firstDeposit != account.firstDeposit) return false;
        if (secondDeposit != account.secondDeposit) return false;
        if (!uuid.equals(account.uuid)) return false;
        return user != null ? user.equals(account.user) : account.user == null;

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + firstDeposit;
        result = 31 * result + secondDeposit;
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uuid='" + uuid + '\'' +
                ", user='" + user + '\'' +
                ", firstDeposit=" + firstDeposit +
                ", secondDeposit=" + secondDeposit +
                '}';
    }
}
