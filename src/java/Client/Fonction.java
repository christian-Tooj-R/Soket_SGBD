package fonction;

import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

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
}
