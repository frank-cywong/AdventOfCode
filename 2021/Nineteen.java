import java.util.*;
import java.io.*;
public class Nineteen{
  static ArrayList<int[][]> data = new ArrayList<int[][]>();
  static NineteenThreePair getvector(int x){
    NineteenThreePair output = null;
    int sig = (x > 0 ? 1 : -1);
    switch(Math.abs(x)){
      case 1:
        output = new NineteenThreePair(sig,0,0);
        break;
      case 2:
        output = new NineteenThreePair(0,sig,0);
        break;
      case 3:
        output = new NineteenThreePair(0,0,sig);
        break;
    }
    return output;
  }
  static int getzorientation(int x, int y){
    /* this does not actually work
    if((x > 0 && y > 0) || (x < 0 && y < 0)){ // right-hand rule
      return 1;
    }
    return -1;
    */
    NineteenThreePair vectora = getvector(x);
    NineteenThreePair vectorb = getvector(y);
    //System.out.println("cross product of "+x+", and y "+y+" gave dir "+vectora.crossProduct(vectorb).hashCode());
    return(vectora.crossProduct(vectorb).hashCode() > 0 ? 1 : -1);
  }
  static int[] gettransformationfromnumber(int t){
    int basecase = t / 4; // 1,2 | 1,3 | 2,1 | 2,3 | 3,1 | 3,2
    int newx = 0;
    int newy = 0;
    int newz = 0;
    switch(basecase){
      case 0:
        newx = 1;
        newy = 2;
        newz = 3;
        break;
      case 1:
        newx = 1;
        newy = 3;
        newz = 2;
        break;
      case 2:
        newx = 2;
        newy = 1;
        newz = 3;
        break;
      case 3:
        newx = 2;
        newy = 3;
        newz = 1;
        break;
      case 4:
        newx = 3;
        newy = 1;
        newz = 2;
        break; 
      case 5:
        newx = 3;
        newy = 2;
        newz = 1;
        break;
    }
    int signs = t % 4; // +,+ +,- -,+ -,-
    if(signs % 2 == 1){
      newy *= -1;
    }
    if(signs / 2 == 1){
      newx *= -1;
    }
    newz *= getzorientation(newx, newy);
    int[] output = new int[3];
    output[0] = newx;
    output[1] = newy;
    output[2] = newz;
    return output;
  }
  static int[][] copy2DArray(int[][] a){
    int[][] output = new int[a.length][];
    for(int i = 0; i < a.length; i++){
      output[i] = a[i].clone();
    }
    return output;
  }
  static int[][] returnTransformedCopy(int[][] a, int t){
    int[][] output = new int[a.length][3];
    int[] transformation = gettransformationfromnumber(t);
    for(int i = 0; i < a.length; i++){
      for(int j = 0; j < 3; j++){
        output[i][j] = a[i][(Math.abs(transformation[j]) - 1)];
        if(transformation[j] < 0){
          output[i][j] *= -1;
        }
      }
    }
    return output;
  }
  static NineteenThreePair gendiff(int[] a, int[] b){
    return new NineteenThreePair(a[0]-b[0],a[1]-b[1],a[2]-b[2]);
  }
  static NineteenThreePair testIfTransformed(int ai, int bi){
    int[][] a = data.get(ai);
    int[][] b = data.get(bi);
    for(int t = 0; t < 24; t++){
      int[][] tb = returnTransformedCopy(b, t);
      //System.out.println("testing t = "+t);
      /*
      if(t == 6 && a.length == 65){
        System.out.println(Arrays.deepToString(tb));
      }
      */
      HashMap<NineteenThreePair, Integer> diffpairs = new HashMap<NineteenThreePair, Integer>();
      for(int i = 0; i < a.length; i++){
        for(int j = 0; j < tb.length; j++){
          NineteenThreePair diffpair = gendiff(a[i], tb[j]);
          /*
          if(t == 6 && j == 0 && a[i][0] == 456){
            System.out.println(Arrays.toString(a[i]));
            System.out.println(Arrays.toString(tb[j]));
            System.out.println(diffpair);
          }
          NineteenThreePair testing = new NineteenThreePair(1105,-1205,1229);
          if(testing.equals(diffpair)){
            System.out.println("Case works with t = " + t + ", i = " + i + ", j = " +j);
          }
          */
          diffpairs.put(diffpair, diffpairs.getOrDefault(diffpair, 0) + 1);
        }
      }
      Iterator<NineteenThreePair> it = diffpairs.keySet().iterator();
      NineteenThreePair gooddiff = null;
      while(it.hasNext()){
        NineteenThreePair temp = it.next();
        /*
        if(diffpairs.get(temp) > 1){
          System.out.println(temp + " has appeared " + diffpairs.get(temp) + " times for ai = "+ai+", bi = "+bi);
        }
        */
        if(diffpairs.get(temp) >= 12){
          gooddiff = temp;
          break;
        }
      }
      if(gooddiff != null){
        System.out.println("Position of b rel to a is: " + gooddiff);
        data.set(ai,combinedata(a,tb,gooddiff));
        data.remove(bi);
        return gooddiff;
      }
    }
    return null;
  }
  static int[] parseLine(String s){
    String[] temp = s.split(",");
    int[] output = new int[3];
    for(int i = 0; i < 3; i++){
      output[i] = Integer.parseInt(temp[i]);
    }
    return output;
  }
  static int[][] arrlisttoarr(ArrayList<int[]> temp){
    int[][] output = new int[temp.size()][];
    for(int i = 0; i < temp.size(); i++){
      output[i] = temp.get(i);
    }
    return output;
  }
  static List<Integer> arrtolist(int[] a){
    List<Integer> output = new ArrayList<Integer>(a.length);
    for(int i = 0; i < a.length; i++){
      output.add(a[i]);
    }
    return output;
  }
  static int[][] combinedata(int[][] a, int[][] b, NineteenThreePair t){
    Set<List<Integer>> norepeat = new HashSet<List<Integer>>();
    ArrayList<int[]> tempoutput = new ArrayList<int[]>(a.length);
    for(int i = 0; i < a.length; i++){
      tempoutput.add(a[i]);
      norepeat.add(arrtolist(a[i]));
    }
    for(int i = 0; i < b.length; i++){
      int[] temp = b[i];
      temp[0] += t.x;
      temp[1] += t.y;
      temp[2] += t.z;
      List<Integer> templ = arrtolist(temp);
      if(!norepeat.contains(templ)){
        norepeat.add(templ);
        tempoutput.add(temp);
      }
    }
    return arrlisttoarr(tempoutput);
  }
  public static void main(String[] args) throws FileNotFoundException{
    File f = new File(args[0]);
    Scanner in = new Scanner(f);
    String temps = null;
    ArrayList<int[]> temp = null;
    while(in.hasNextLine()){
      temps = in.nextLine();
      if(temps.equals("")){
        continue;
      }
      if(temps.charAt(1) == '-'){
        if(temp != null){
          data.add(arrlisttoarr(temp));
        }
        temp = new ArrayList<int[]>();
        continue;
      }
      temp.add(parseLine(temps));
    }
    data.add(arrlisttoarr(temp));
    // temporary testing
    /*
    testIfTransformed(0,2);
    testIfTransformed(1,2);
    testIfTransformed(3,2);
    testIfTransformed(4,2);
    */
    while(data.size() != 1){
      boolean immediatebreak = false;
      for(int i = 0; i < data.size(); i++){
        for(int j = 0; j < data.size(); j++){
          if(i == j){
            continue;
          }
          NineteenThreePair returnval = testIfTransformed(i,j);
          if(returnval != null){
            immediatebreak = true;
            break;
          }
        }
        if(immediatebreak){break;}
      }
    }
    System.out.println(data.get(0).length);
    /*
    testIfTransformed(0,1);
    testIfTransformed(0,3);
    testIfTransformed(0,2);
    //System.out.println(data.size());
    //System.out.println(data.get(0).length);
    //System.out.println(data.get(1).length);
    testIfTransformed(0,1);
    */
    //System.out.println(Arrays.deepToString(data.get(0)));
    //System.out.println(Arrays.deepToString(data.get(1)));
    /*
    System.out.println(Arrays.deepToString(data.get(0)));
    System.out.println(Arrays.deepToString(data.get(1)));
    System.out.println(Arrays.toString(gettransformationfromnumber(2)));
    System.out.println(Arrays.deepToString(returnTransformedCopy(data.get(1),2)));
    */
  }
}