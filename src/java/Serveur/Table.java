package table;

import java.io.Serializable;
import java.util.Vector;

public class Table implements Serializable {
    String Name;
    String[] Col_Name;
    Vector<String> line;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String[] getCol_Name() {
        return Col_Name;
    }

    public void setCol_Name(String[] col_Name) {
        Col_Name = col_Name;
    }

    public Vector<String> getLine() {
        return line;
    }

    public void setLine(Vector<String> line) {
        this.line = line;
    }

}
