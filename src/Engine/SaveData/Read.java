package Engine.SaveData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Read {

    private static BufferedReader br;
    private static String str;

    public static double load(String s){
        double num = 0;
        try {
            br = new BufferedReader(new FileReader("C:/Users/Cplum/Desktop/BitcoinClicker/src/Engine/SaveData/save.txt"));
            for(String line = br.readLine(); line != null; line = br.readLine()){
                if(line.contains(s)){
                    for (int i = 0; i < line.length(); i++) {
                        if (line.substring(i, i + 1).equals(":")) {
                            num = Double.parseDouble(line.substring(i + 2));
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    private static String find(String target){
        try {
            br = new BufferedReader(new FileReader("C:/_Temp/LifeSimulator/data/PlayerData.txt"));
            for(String line = br.readLine(); line != null; line = br.readLine()){
                if(line.contains(target)){
                    for(int i = 0; i < line.length(); i++) {
                        if (line.substring(i, i + 1).equals(" ")) {
                            return line.substring(i + 1);
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
