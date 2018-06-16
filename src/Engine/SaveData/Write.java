package Engine.SaveData;

import Engine.GameContainer;
import Game.GameManager;
import Game.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Write {

    private static FileWriter fw;
    private static PrintWriter pw;

    private static double[] atti = new double[2];
    private static String[] names = {"bitcoin", "cents"};
    private static GameManager gm;

    public Write(GameManager gm){
        this.gm = gm;
    }

    public static void save(){
        atti[0] = gm.getPlayer().getBalanceBitcoin();
        atti[1] = gm.getPlayer().getBalanceCents();
        try {
            fw = new FileWriter("C:/Users/Cplum/Desktop/BitcoinClicker/src/Engine/SaveData/save.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw = new PrintWriter(fw);
        for(int i = 0; i < atti.length; i++){
            pw.write(names[i]+ ": " + atti[i] + "\n");
        }
        pw.close();
    }

}