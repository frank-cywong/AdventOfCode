import java.io.*;
import java.util.*;
public class Fifteen{
  static HashMap<Integer,Integer> minRisk = new HashMap<Integer,Integer>(); // slightly less efficient than the ideal implementation but much easier to just use an array
  static boolean[][] visited = null; //placeholder
  static ArrayList<int[]> riskmap = new ArrayList<int[]>();
  static int colcount = 0;
  static int rowcount = 0;
  public static boolean inRange(int col, int row){
    return(col >= 0 && row >= 0 && col < colcount && row < rowcount);
  }
  public static int djikstra(int startcol, int startrow, int targetcol, int targetrow){
    visited[startcol][startrow] = true;
    int col = startcol;
    int row = startrow;
    int currisk = 0;
    int temprisk;
    int tcol;
    int trow;
    int lclindex;
    int cellcount = 0;
    while(col != targetcol || row != targetrow){
      tcol = col-1;
      trow = row;
      //System.out.println("testing col "+col+", row "+row);
      if(cellcount % 100 == 0){
        System.out.println("testing col "+col+", row "+row+", for cellcount "+cellcount);
      }
      lclindex = tcol*rowcount+trow;
      if(inRange(tcol, trow) && !visited[tcol][trow]){
        temprisk = currisk + riskmap.get(tcol)[trow];
        minRisk.put(lclindex,Math.min(temprisk,minRisk.getOrDefault(lclindex, Integer.MAX_VALUE)));
      }
      tcol = col+1;
      trow = row;
      lclindex = tcol*rowcount+trow;
      if(inRange(tcol, trow) && !visited[tcol][trow]){
        temprisk = currisk + riskmap.get(tcol)[trow];
        minRisk.put(lclindex,Math.min(temprisk,minRisk.getOrDefault(lclindex, Integer.MAX_VALUE)));
      }
      tcol = col;
      trow = row-1;
      lclindex = tcol*rowcount+trow;
      if(inRange(tcol, trow) && !visited[tcol][trow]){
        temprisk = currisk + riskmap.get(tcol)[trow];
        minRisk.put(lclindex,Math.min(temprisk,minRisk.getOrDefault(lclindex, Integer.MAX_VALUE)));
      }
      tcol = col;
      trow = row+1;
      lclindex = tcol*rowcount+trow;
      if(inRange(tcol, trow) && !visited[tcol][trow]){
        temprisk = currisk + riskmap.get(tcol)[trow];
        minRisk.put(lclindex,Math.min(temprisk,minRisk.getOrDefault(lclindex, Integer.MAX_VALUE)));
      }
      int tempminrisk = Integer.MAX_VALUE;
      int tempminindex = 0;
      Iterator<Integer> it = minRisk.keySet().iterator();
      Integer tempindex;
      while(it.hasNext()){
        tempindex = it.next();
        if(minRisk.get(tempindex) < tempminrisk){
          tempminrisk = minRisk.get(tempindex);
          tempminindex = tempindex;
        }
      }
      currisk = tempminrisk;
      col = tempminindex / rowcount;
      row = tempminindex % rowcount;
      visited[col][row] = true;
      minRisk.remove(tempminindex);
      cellcount++;
    }
    return currisk;
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temp;
    int[] temprow;
    while(in.hasNextLine()){
      temp = in.nextLine();
      if(rowcount == 0){
        rowcount = temp.length();
      }
      temprow = new int[rowcount];
      for(int i = 0; i < rowcount; i++){
        temprow[i] = Integer.parseInt(temp.substring(i,i+1));
      }
      riskmap.add(temprow);
      colcount++;
    }
    visited = new boolean[colcount][rowcount];
    int tcol = colcount-1;
    int trow = rowcount-1;
    if(args.length > 1){
      tcol = Integer.parseInt(args[1]);
      trow = Integer.parseInt(args[2]);
    }
    System.out.println(djikstra(0,0,tcol,trow));
  }
}