package fonction;
import javax.swing.*;

import table.Table;

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
    String Operation;
    String Table;
    String requete;
    Vector<String> list_tab=new Vector<>();
/*public Table find_tab(String name){
    
    for (int i = 0; i < list_tab.size(); i++) {
        String str=this.list_tab.get(i).getName();
        if(name.equals(str)){
            return this.list_tab.get(i);
        }
    }
    return new Table();
}*/
public Table find_tab(String name){
    
    for (int i = 0; i < list_tab.size(); i++) {
        String[] str=this.list_tab.get(i).split("//");
        if(name.equals(str[0])){ 
            Table tabnew=new Table();
            tabnew.setName(name);
            tabnew.setCol_Name(str[1].split(","));
            tabnew.setLine(read(name));
            return tabnew;
        }
    }
    return new Table();
}
  public void print(Table tab){
          int count2=0;
          String linear="";
          for (int i = 0; i < (tab.getCol_Name().length*16); i++) {
              linear=linear+"-";
          }

      for (int i = 0; i < tab.getCol_Name().length; i++) {
          if(tab.getCol_Name()[i].length()<7){
              System.out.print(tab.getCol_Name()[i]+"\t\t"+"|"); 
          }else{
              System.out.print(tab.getCol_Name()[i]+"\t"+"|");
          }
      }
          
      System.out.println("\n"+linear);
         while(count2<tab.getLine().size()){
             String[] str=tab.getLine().get(count2).split(",");
             for(int j=0;j<str.length;j++){
                    // System.out.print(str[j]+"\t");
                     if(str[j].length()<7){
                         System.out.print(str[j]+"\t\t"+"|"); 
                       //  vecs.add(str[i]+"\t\t"+"|");
                     }else{
                         System.out.print(str[j]+"\t"+"|");
                     //    vecs.add(str[i]+"\t"+"|");
                     }
             }
             System.out.print("\n");
             
         count2++;
     }
     System.out.println(linear);
  }
public void fonction(String requete)throws Exception {
        this.requete = requete;
        String[] req=this.requete.split(" ");
        this.Operation=req[0];
        this.Table=req[3];
        //select Nom,NumCli from Produit//select Numero from Olona
       // select difference between Olona and Biby
       list_tab= read("ListeTab");
       
        Table tab_enjeu=find_tab(req[3]);
        Table tab_compare=null;
        Table vecDiv1=null;
        Table vecDiv2=null;
        if(req.length>=5 && req.length!=7){
             tab_compare=find_tab(req[5]);
        }
         if(req.length==7){
            String[] sous_req=requete.split("//");
            String[] str1=sous_req[0].split(" ");
            String[] str2=sous_req[1].split(" ");
            Table tab_1=find_tab(str1[3]) ;
            Table tab_2=find_tab(str2[3]);
            vecDiv1=projection(tab_1,str1[1]); 
            vecDiv2=projection(tab_2,str2[1]);
            
            divide(vecDiv1, vecDiv2,requete);
        }
        if(this.Operation.equals("create") || this.Operation.equals("insert")){
            Ecrire(requete);
        }
        if(this.Operation.equals("select")){
            if(req.length<=4 && req[1].equals("*")){
                selection(tab_enjeu,"");
            }
            else if(req.length>4 && req[1].equals("*")){
                String valeur=req[5]+" "+req[6]+" "+req[7];
                selection(tab_enjeu,valeur);
            }
            else if(req.length<=4 && !req[1].equals("*")){
               print(projection(tab_enjeu,req[1]));
            }
            else if(req[1].equals("difference")){
                
                 if(requete.contains("between")&&requete.contains("and")){
                   print(difference(tab_enjeu, tab_compare)); 
                }else{
                    throw new Exception("Erreur de syntaxe");
                }
            }
            else if(req[1].equals("intersection")){
                
                 if(requete.contains("between")&&requete.contains("and")){
                    print(intersection(tab_enjeu, tab_compare));
                }else{
                    throw new Exception("Erreur de syntaxe");
                }
            }
            else if(req[1].equals("produit")){
                
                 if(requete.contains("between")&&requete.contains("and")){
                    print(produit_cartesien(tab_enjeu, tab_compare));
                }else{
                    throw new Exception("Erreur de syntaxe");
                }
            }
            else if(req[1].equals("union")){
                
                 if(requete.contains("of")&&requete.contains("and")){
                    print(union(tab_enjeu, tab_compare));
                }else{
                    throw new Exception("Erreur de syntaxe");
                }
            }
            else if(req[1].equals("join")){
                
                 if(requete.contains("of")&&requete.contains("and")){
                    print(jointure(tab_enjeu, tab_compare, requete));
                }else{
                    throw new Exception("Erreur de syntaxe");
                }
            }
        }
    }
//   insert into Olona values ( okay,lesy,bg )
//   create table Olona ( Nom,Prenom,Classe )
/********Fonction necessaire */
public void Ecrire(String req) { //create table 
    
    try {
    String[] requete=req.split(" ");
    if(requete[0].equals("create")){
        File file = new File(requete[2]+".txt");
        if(!file.exists()){
            file.createNewFile();
        }
        Table table=new Table();
        table.setName(requete[2]);
        table.setCol_Name(requete[4].split(","));
      //  this.list_tab.add(table);
        System.out.println("Table créée");
        this.Ecrire("insert into ListeTab values ( "+requete[2]+"//"+requete[4]+"// )");
    }
    else if(requete[0].equals("insert")){
        File file = new File(requete[2]+".txt");
        FileWriter write;
        FileReader read;
            write = new FileWriter(file,true);
           read = new FileReader(requete[2]+".txt");
           String[] writing=requete[5].split(",");
            BufferedReader r_donnee = new BufferedReader(read);
            BufferedWriter donnee = new BufferedWriter(write);
            String verif = r_donnee.readLine();
            if(verif!=null) {
                donnee.write("\n");
            }
            for(int i=0;i< writing.length;i++) {
                donnee.write(writing[i]+",");
            }
            donnee.close();
    }

} catch (Exception e) {
 
}
}

    public Vector<String> read(String req){  //lecture fichier
        Vector<String> v=new Vector<>();
      //  String[] requete=req.split(" ");
        File file=new File(req+".txt");
        try{
            FileReader read=new FileReader(file);
            BufferedReader br=new BufferedReader(read);
            String lin=br.readLine();
            while(lin!=null){
                v.add(lin);
                lin=br.readLine();
            }
        }catch(Exception e){

        }
            return v;
    } 
    public Vector<String> readi(Table table){  //lecture fichier
        Vector<String> v=new Vector<>();
      //  String[] requete=req.split(" ");
        File file=new File(table.getName()+".txt");
        try{
            FileReader read=new FileReader(file);
            BufferedReader br=new BufferedReader(read);
            String lin=br.readLine();
            while(lin!=null){
                v.add(lin);
                lin=br.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            table.setLine(v);
            return table.getLine();   
    }

    public int getIndexField(String[] str,String name){ //indice field
        for (int i = 0; i < str.length; i++) {
            if(str[i].equals(name)){
                return i;
            }
        }
        return -1;
    }
    public int vecData(Vector<String> vect){
        int val=vect.get(0).length();
        for (int i = 0; i < vect.size(); i++) {
            if(vect.get(i).length()>val){
                val=vect.get(i).length();
            }
        }
        return val;
    }
/********SELECTION */
         public void selection(Table tab,String req)throws Exception{
            Vector<String> vect=this.readi(tab);
            int count=0;
            String line="\n";
            Vector<String> vecs=new Vector<>();
            String linear="";
            for (int i = 0; i < (tab.getCol_Name().length*16); i++) {
                linear=linear+"-";
            }

        for (int i = 0; i < tab.getCol_Name().length; i++) {
            if(tab.getCol_Name()[i].length()<7){
                System.out.print(tab.getCol_Name()[i]+"\t\t"+"|"); 
            }else{
                System.out.print(tab.getCol_Name()[i]+"\t"+"|");
            }
        }
            
        System.out.println("\n"+linear);
            while(count<vect.size()){
                String[] str=vect.get(count).split(",");
                for(int i=0;i<str.length;i++){
                    if(req!=""){
                        String[] where=req.split(" ");
                        line="";
                        if(tab.getCol_Name()[i].equals(where[0]) && str[i].equals(where[2])){  
                            str=vect.get(count).split(",");
                             for (int j = 0; j < str.length; j++) {
                                if(str[j].length()<7){
                                    System.out.print(str[j]+"\t\t"+"|");
                                }else{
                                    System.out.print(str[j]+"\t"+"|");
                                }
                            }
                            System.out.print("\n");
                        }  
                    }else{
                        if(str[i].length()<7){
                            System.out.print(str[i]+"\t\t"+"|"); 
                            vecs.add(str[i]+"\t\t"+"|");
                        }else{
                            System.out.print(str[i]+"\t"+"|");
                            vecs.add(str[i]+"\t"+"|");
                        }
                    }
                    }
                System.out.print(line);
            count++;
        }
        System.out.println(linear);

    
        }

   /*******PROJECTION */      
   public Table projection(Table tab,String req)throws Exception{
    Vector<String> vect=null;
  //  System.out.println("Io ny anarany "+tab.getName());
    if(tab.getName().equals("vide")){
        vect=tab.getLine();
    }else{
        vect=this.readi(tab);
    }
    int count=0;
    
    Vector<String> vect3=new Vector<>();
    String ecrire="";
    while(count<vect.size()){
        String[] str=vect.get(count).split(",");
        for(int i=0;i<str.length;i++){
            if(req!=""){
                String[] where=req.split(",");
              if(where.length>=2){
                if(tab.getCol_Name()[i].equals(where[0])){  
                        ecrire=str[i]+",";
                }
                if(tab.getCol_Name()[i].equals(where[1])){
                      ecrire=ecrire+str[i]+",";
                      vect3.add(ecrire);
                }
            }else if(where.length<2){
                if(tab.getCol_Name()[i].equals(where[0])){  
                        ecrire=str[i]+",";
                        vect3.add(ecrire);
                }
                }
            }
        }
    count++;
}
Table vaovao=new Table();
vaovao.setName("vide");
vaovao.setLine(vect3);
vaovao.setCol_Name(req.split(","));

return vaovao;
}   
       
 
 /*******UNION */       
        public Table union(Table tab1,Table tab2)throws Exception{
            Vector<String> vect=null;

            if(tab1.getName().equals("vide")){
                vect=tab1.getLine(); 
            }else{
                vect=this.readi(tab1);
            }
            
            Vector<String> vect2=null;
            
            if(tab2.getName().equals("vide")){
                vect2=tab2.getLine();
            }else{
                vect2=this.readi(tab2);
            }
            
                for(int i=0;i < vect2.size();i++) {
                    if(!vect.contains(vect2.get(i))){
                       vect.add(vect2.get(i));
                   }
              }

        Table vaovao=new Table();
        vaovao.setName("vide");
        vaovao.setCol_Name(tab1.getCol_Name());
        vaovao.setLine(vect);
        return vaovao;
        }

/*********INTERSECTION */
        
        public Table intersection(Table tab,Table tab2)throws Exception{
            Vector<String> vect=null;

            if(tab.getName().equals("vide")){
                vect=tab.getLine();
            }else{
                vect=this.readi(tab);
            }
            
            Vector<String> vect2=null;
            
            if(tab2.getName().equals("vide")){
                vect2=tab2.getLine();
            }else{
                vect2=this.readi(tab2);
            }

            
            Vector<String> vect3=new Vector<>();
                for(int i=0;i < vect2.size();i++) {
                    if(vect.contains(vect2.get(i))){
                        vect3.add(vect2.get(i));
                   }
              }
        Table vaovao=new Table();
        vaovao.setName("vide");
        vaovao.setCol_Name(tab.getCol_Name());
        vaovao.setLine(vect3);
        return vaovao;
        }

    /******DIFFERENCE */

        public Table difference(Table tab1,Table tab2)throws Exception{
            Vector<String> vect=null;

            if(tab1.getName().equals("vide")){
                vect=tab1.getLine(); 
            }else{
                vect=this.readi(tab1);
            }
            
            Vector<String> vect2=null;
            
            if(tab2.getName().equals("vide")){
                vect2=tab2.getLine();
            }else{
                vect2=this.readi(tab2);
            }

            
            Vector<String> vect3=new Vector<>();
                for(int i=0;i < vect.size();i++) {
                    if(!vect2.contains(vect.get(i))){
                        vect3.add(vect.get(i));
                   }
              }
            
        Table vaovao=new Table();
        vaovao.setName("vide");
        vaovao.setCol_Name(tab1.getCol_Name());
        vaovao.setLine(vect3);
        return vaovao;
        }
        

        /******PRODUIT CARTESIEN */

        public Table produit_cartesien(Table tab1,Table tab2)throws Exception{
            Vector<String> vect=null;

            if(tab1.getName().equals("vide")){
                vect=tab1.getLine();
            }else{
                vect=this.readi(tab1);
            }
            
            Vector<String> vect2=null;
            
            if(tab2.getName().equals("vide")){
                vect2=tab2.getLine();
            }else{
                vect2=this.readi(tab2);
            }

            
            Vector<String> vect3=new Vector<>();
                for(int i=0;i < vect.size();i++) {
                    for (int j = 0; j < vect2.size(); j++) {
                        vect3.add(vect.get(i)+vect2.get(j));
                    }
              }

        Table vaovao=new Table(); 
        vaovao.setName("vide");
        String[] ss=new String[tab1.getCol_Name().length+tab2.getCol_Name().length];
        int finalt=tab1.getCol_Name().length,z=0;
        for (int i = 0; i < (tab1.getCol_Name().length); i++) {
            ss[i]=tab1.getCol_Name()[i];
            System.out.println("i= "+i);
        }
        while(z < (tab2.getCol_Name().length) /*&& finalt<=(tab2.getCol_Name().length)*/) {
            ss[finalt]=tab2.getCol_Name()[z];
            System.out.println("Tab2 =  "+tab2.getCol_Name()[z]+"  length  "+tab2.getCol_Name().length+"  farany ehh"+z+" finale "+finalt);
            z++;
            finalt++;
        }
        vaovao.setCol_Name(ss);
        for (int i = 0; i < ss.length; i++) {
            System.out.println("Io ary koa "+vaovao.getCol_Name()[i]);
        }
        vaovao.setLine(vect3);
        return vaovao;
        }

       
        /*********JOINTURE */
        
        public Table jointure(Table tab1,Table tab2,String req)throws Exception{
            String[] where=req.split(" ");

            Vector<String> vect=null;

            if(tab1.getName().equals("vide")){
                vect=tab1.getLine();
            }else{
                vect=this.readi(tab1);
            }
            
            Vector<String> vect2=null;
            
            if(tab2.getName().equals("vide")){
                vect2=tab2.getLine();
            }else{
                vect2=this.readi(tab2);
            }
             
            Vector<String> vect3=new Vector<>();
                 for(int i=0;i < vect.size();i++) {
                    String[] str1=vect.get(i).split(",");
                    for (int j = 0; j < vect2.size(); j++) {
                        String[] str2=vect2.get(j).split(",");
                        if(str1[this.getIndexField(tab1.getCol_Name(),where[7])].equals(str2[this.getIndexField(tab2.getCol_Name(),where[9])])){
                            vect3.add(vect.get(i)+vect2.get(j));
                        }
                    }
              }

        Table vaovao=new Table();
        vaovao.setName("vide");
        String[] ss=new String[tab1.getCol_Name().length+tab2.getCol_Name().length];
        for (int i = 0; i < (tab1.getCol_Name().length); i++) {
            ss[i]=tab1.getCol_Name()[i];
        }
        for (int i = tab1.getCol_Name().length; i < (tab2.getCol_Name().length); i++) {
            ss[i]=tab1.getCol_Name()[i];
        }
        vaovao.setCol_Name(ss);
        vaovao.setLine(vect3);
        return vaovao;
        }

        public String getDiff(String[] str1,String[] str2){       
            
        String[] NomCol1=str1[1].split(",");
        String[] NomCol2=str2[1].split(",");

        Vector<String> vec1=new Vector<>();
        Vector<String> vec2=new Vector<>();

            for (int i = 0; i < NomCol1.length; i++) {
                vec1.add(NomCol1[i]);
            }
            for (int i = 0; i < NomCol2.length; i++) {
                vec2.add(NomCol2[i]);
            }

            for (int i = 0; i < vec1.size(); i++) {
                if(!vec2.contains(vec1.get(i))){
                    return vec1.get(i);
                }
            }

            return "";

        }
/********DIVISION */
        public void divide(Table tab1,Table tab2,String req)throws Exception{
            
            Vector<String> vect3=new Vector<>();
        String[] sous_req=req.split("//");
        String[] str1=sous_req[0].split(" ");
        String[] str2=sous_req[1].split(" "); 
        
        String NomCol=getDiff(str1, str2);
        Table table1=projection(tab1, NomCol);
        Table tabP=produit_cartesien(table1, tab2);
        Table tabW=projection(tabP,str1[1]);
        Table differe=difference(tabW, tab1);
        Table differePJ=projection(differe, NomCol);
        Table finale=difference(table1, differePJ);
        for (int i = 0; i < finale.getLine().size(); i++) {
            if(vect3.isEmpty()){
                vect3.add(finale.getLine().get(i));
            }else{
                if(!vect3.contains(finale.getLine().get(i))){
                    vect3.add(finale.getLine().get(i));
                }
            }
        }
        finale.setLine(vect3);
          print(finale);

        }
 

public String getOperation() {
    return Operation;
}


public void setOperation(String operation) {
    Operation = operation;
}


public String getTable() {
    return Table;
}


public void setTable(String table) {
    Table = table;
}


public String getRequete() {
    return requete;
}


public void setRequete(String requete) {
    this.requete = requete;
}

}
/**
 * create table Vaovao ( Nom,Numero,Prix )
 * create table Olona ( Numero,Nom,Adresse )
 * create table Produit ( Nom,Numero,Prix )
 * select Nom,Numero from Vaovao//select Nom from Produit
 */