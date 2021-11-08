import java.io.*;
public class Two{
  public static void main(String[] args) throws IOException{
    FileInputStream in = new FileInputStream(args[0]);
    String rawdata = "";
    int curchar = 0;;
    while(curchar != -1){
      curchar = in.read();
      rawdata += (char)curchar;
    }
    String[] data = rawdata.split("\n");
    int total = 0;
    int sa,sb,sc,a,b,c;
    String[] temp;
    for(int i = 0; i < data.length; i++){
      if(data[i].indexOf("x") == -1){
        break;
      }
      temp = data[i].split("x");
      a = Integer.parseInt(temp[0]);
      b = Integer.parseInt(temp[1]);
      c = Integer.parseInt(temp[2]);
      sa = a * b;
      sb = a * c;
      sc = b * c;
      total += (2 * sa);
      total += (2 * sb);
      total += (2 * sc);
      total += Math.min(Math.min(sa,sb),sc);
    }
    System.out.println(total);
  }
}
