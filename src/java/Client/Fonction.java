package fonc;

import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.JTable;

import table.Table;

public class Fonction {

    public int getPort() throws Exception {
        BufferedReader bff = new BufferedReader(new FileReader("../../conf/Myconfig.conf"));
        int port = Integer.parseInt(bff.readLine().split(":")[1]);
        return port;
    }

    public String getIp() throws Exception {
        BufferedReader bff = new BufferedReader(new FileReader("../../conf/Myconfig.conf"));
        String lin = bff.readLine();
        if (lin != null) {
            lin = bff.readLine();
        }
        return lin.split(":")[1];
    }

    public JTable getTable(Table table) {
        return new JTable(VectortoObject(table.getLine()), table.getCol_Name());
    }

    public Object[][] VectortoObject(Vector<String> vect) {
        int i = 0;
        Object[][] donnee = new Object[vect.get(0).split(",").length][vect.size()];
        while (i < vect.size()) {
            for (int j = 0; j < donnee.length; j++) {
                for (int y = 0; y < donnee[j].length; y++) {
                    donnee[j][y] = vect.get(i);
                }
                i++;
            }
        }
        return donnee;
    }

}
