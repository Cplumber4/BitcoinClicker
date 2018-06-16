package Game;

import Engine.GameContainer;

public class Store {

    private GameManager gm;

    private static final int pentiumCostInitial = 9700;
    private static final int QuadroCostInitial = 17500;
    private static final int ASICCostInitial = 50000;
    private static final int StorageCostInitial = 75000;

    private int pentiumCost;
    private int quadroCost;
    private int ASICCost;
    private int StorageCost;

    public Store(GameManager gm){
        this.gm = gm;
        updateCosts();
    }

    public void updateCosts(){
        pentiumCost = (int)(pentiumCostInitial * Math.pow(1.07, gm.getPlayer().getNumPentiums()));
        quadroCost = (int)(QuadroCostInitial * Math.pow(1.09, gm.getPlayer().getNumQuadro()));
        ASICCost = (int) (ASICCostInitial * Math.pow(1.12, gm.getPlayer().getNumASIC()));
        StorageCost = (int) (StorageCostInitial * Math.pow(1.095, gm.getPlayer().getNumStorage()));
    }

    public int getStorageCost(){
        return StorageCost;
    }

    public int getASICCost() {
        return ASICCost;
    }

    public int getPentiumCost() {
        return pentiumCost;
    }

    public int getQuadroCost() {
        return quadroCost;
    }

    public static int getPentiumCostInitial() {
        return pentiumCostInitial;
    }

    public static int getQuadroCostInitial() {
        return QuadroCostInitial;
    }


}
