package Game;

import Engine.SaveData.Read;

public class Player {
    private int balanceCents;
    private double balanceBitcoin;
    private double totalBitcoinEarned = 0.0;

    private int numPentiums = 0;
    private int numQuadro = 0;
    private int numASIC = 0;
    private int numStorage = 0;

    private double clickValue;
    private double passiveValue;

    public Player() {
        this.balanceCents = (int)Read.load("cents");
        this.balanceBitcoin = Read.load("bitcoin");
        this.clickValue = .00001;
        passiveValue = 0.0000;
    }

    public void click(){
        balanceBitcoin += clickValue;
        totalBitcoinEarned += clickValue;
    }

    public int getNumStorage() {
        return numStorage;
    }

    public void setNumStorage(int numStorage) {
        this.numStorage = numStorage;
    }

    public void setBalanceBitcoin(double balanceBitcoin) {
        this.balanceBitcoin = balanceBitcoin;
    }

    public double getPassiveValue() {
        return passiveValue;
    }

    public void setPassiveValue(double passiveValue) {
        this.passiveValue = passiveValue;
    }

    public int getNumASIC() {
        return numASIC;
    }

    public void setNumASIC(int numASIC) {
        this.numASIC = numASIC;
    }

    public void addBitcoin(double amount){
        this.balanceBitcoin += amount;
    }

    public int getBalanceCents() {
        return balanceCents;
    }

    public void setBalanceCents(int balanceCents) {
        this.balanceCents = balanceCents;
    }

    public double getBalanceBitcoin() {
        return balanceBitcoin;
    }

    public void setBalanceBitcoin(int balanceBitcoin) {
        this.balanceBitcoin = balanceBitcoin;
    }

    public double getClickValue() {
        return clickValue;
    }

    public void setClickValue(double clickValue) {
        this.clickValue = clickValue;
    }

    public int getNumPentiums() {
        return numPentiums;
    }

    public int getNumQuadro() {
        return numQuadro;
    }

    public void setNumPentiums(int numPentiums) {
        this.numPentiums = numPentiums;
    }

    public void setNumQuadro(int numQuadro) {
        this.numQuadro = numQuadro;
    }

    public double getTotalBitcoinEarned() {
        return totalBitcoinEarned;
    }

    public void setTotalBitcoinEarned(double totalBitcoinEarned) {
        this.totalBitcoinEarned = totalBitcoinEarned;
    }
}
