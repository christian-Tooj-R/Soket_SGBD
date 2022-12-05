package serveur;

import java.io.OutputStream;
import java.io.PrintWriter;

import table.Table;

public class MyPrint extends PrintWriter {

    public MyPrint(OutputStream out) {
        super(out);
    }

    public void println(Table tab) {
        int count2 = 0;
        String linear = "";
        for (int i = 0; i < (tab.getCol_Name().length * 16); i++) {
            linear = linear + "-";
        }

        for (int i = 0; i < tab.getCol_Name().length; i++) {
            if (tab.getCol_Name()[i].length() < 7) {
                this.print(tab.getCol_Name()[i] + "\t\t" + "|");
            } else {
                this.print(tab.getCol_Name()[i] + "\t" + "|");
            }
        }

        this.println("\n" + linear);
        while (count2 < tab.getLine().size()) {
            String[] str = tab.getLine().get(count2).split(",");
            for (int j = 0; j < str.length; j++) {
                if (str[j].length() < 7) {
                    this.print(str[j] + "\t\t" + "|");
                } else {
                    this.print(str[j] + "\t" + "|");
                }
            }
            this.print("\n");

            count2++;
        }
        this.println(linear);
    }

}
