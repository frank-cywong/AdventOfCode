import java.util.Arrays;
public class Seven{
  public static void main(String[] args){
    String[] temp = args[0].split(",");
    int[] data = new int[temp.length];
    for(int i = 0; i < temp.length; i++){
      data[i] = Integer.parseInt(temp[i]);
    }
    Arrays.sort(data);
    long count = 0;
    int half = temp.length / 2;
    for(int i = 0; i < temp.length; i++){
      if(i < half){
        count -= data[i];
        continue;
      }
      if(!(i == half && temp.length % 2 == 1)){
        count += data[i];
      }
    }
    System.out.println(count);
  }
}