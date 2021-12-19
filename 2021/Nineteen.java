public class Nineteen{
  static int getzorientation(int x, int y){
    if((x > 0 && y > 0) || (x < 0 && y < 0)){ // right-hand rule
      return 1;
    }
    return -1;
  }
  static int[] gettransformationfromnumber(int t){
    int basecase = t / 4; // 1,2 | 1,3 | 2,1 | 2,3 | 3,1 | 3,2
    int newx;
    int newy;
    int newz;
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
      newx *= 1;
    }
    newz *= getzorientation(newx, newy);
    int[] output = new int[3];
    output[0] = newx;
    output[1] = newy;
    output[2] = newz;
    return output;
  }
}