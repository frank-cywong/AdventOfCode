import java.io.*;
import java.util.*;
import java.util.HashMap;
public class Seven{
  Map nodes = new HashMap();
  public static SevenTreeNode parseNode(String value){
    try {
      int directvalue = parseInt(value);
      return(new SevenTreeNode((char)directvalue),nodes);
    } catch (NumberFormatException e){
      String[] temp = value.split(" ");
      if(temp[0].equals("NOT")){
        return(new SevenTreeNode(temp[1],null,5,nodes));
      }
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
    }
  }
}
