public class SevenTwo{
  public static int lsum(int c){
    return(((c+1)*c)/2);
  }
  public static void main(String[] args){
    String[] temp = args[0].split(",");
    int[] data = new int[temp.length];
    long sum = 0;
    for(int i = 0; i < temp.length; i++){
      data[i] = Integer.parseInt(temp[i]);
      sum += data[i];
    }
    int mean = (int)((double)sum / (double)temp.length);
    long count = 0;
    for(int i = 0; i < temp.length; i++){
      count += lsum(Math.abs(data[i]-mean));
    }
    System.out.println(count);
  }
}
