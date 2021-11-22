import java.io.*;
import java.util.*;
import java.util.HashMap;
public class Seven{
  static Map<String, SevenTreeNode> nodes = new HashMap<String, SevenTreeNode>();
  public static SevenTreeNode parseNode(String value){
    try {
      int directvalue = Integer.parseInt(value);
      return(new SevenTreeNode((char)directvalue,nodes));
    } catch (NumberFormatException e){
      String[] temp = value.split(" ");
      if(temp[0].equals("NOT")){
        return(new SevenTreeNode(temp[1],null,5,nodes));
      }
      if(temp.length == 1){
        return(new SevenTreeNode(temp[0],null,6,nodes));
      }
      if(temp[1].equals("AND")){
        return(new SevenTreeNode(temp[0],temp[2],1,nodes));
      }
      if(temp[1].equals("OR")){
        return(new SevenTreeNode(temp[0],temp[2],2,nodes));
      }
      if(temp[1].equals("LSHIFT")){
        return(new SevenTreeNode(temp[0],temp[2],3,nodes));
      }
      return(new SevenTreeNode(temp[0],temp[2],4,nodes));
    }
  }
  public static void main(String[] args) throws IOException{
    FileInputStream f = new FileInputStream(args[0]);
    char curchar = 0;
    String curinstruction = "";
    String[] temp;
    while(curchar != (char)-1){
      curchar = (char)f.read();
      if(curchar != '\n'){
        curinstruction += curchar;
        continue;
      }
      temp = curinstruction.split(" -> ");
      nodes.put(temp[1], parseNode(temp[0]));
      curinstruction = "";
    }
    System.out.println((int)nodes.get(args[1]).getValue());
  }
}
