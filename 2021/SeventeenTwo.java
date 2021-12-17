import java.util.*;
public class SeventeenTwo{
  static int t;
  static Set<Integer> nodupe = new HashSet<Integer>();
  public static double f(int x, int tcap){
    return (t >= tcap ? tcap : (((double)(2 * x)/t) + t - 1) / 2);
  }
  public static int g(int x1, int x2, int y1, int y2){
    int xvm = (int)(Math.floor(f(x2,19)));
    int xvl = (int)(Math.ceil(f(x1,17)));
    int yvm = (int)(Math.floor(f(y2, Integer.MAX_VALUE)));
    int yvl = (int)(Math.ceil(f(y1, Integer.MAX_VALUE)));
    int xc = xvm - xvl + 1;
    int yc = yvm - yvl + 1;
    for(int i = xvl; i <= xvm; i++){
      for(int j = yvl; j <= yvm; j++){
        //System.out.print("("+i+", "+j+")|");
        nodupe.add(i*10000+j);
      }
    }
    /*
    if(t == 228 || t == 227){
      System.out.println(xc);
      System.out.println(yc);
    }
    */
    return xc * yc;
  }
  public static void main(String[] args){
    int xi = Integer.parseInt(args[0]);
    int xf = Integer.parseInt(args[1]);
    int yi = Integer.parseInt(args[2]);
    int yf = Integer.parseInt(args[3]);
    int total = 0;
    for(t = 1; t <= 250; t++){
      total += g(xi,xf,yi,yf);
    }
    //System.out.println(total);
    System.out.println(nodupe.size());
  }
}
