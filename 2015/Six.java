import java.io.*;
public class Six{
	static boolean[][] state = new boolean[1000][1000];
	public static void toggle(int x1, int y1, int x2, int y2){
		for(int i = x1; i <= x2; i++){
			for(int j = y1; j <= y2; j++){
				if(state[i][j]){
					state[i][j] = false;
				} else {
					state[i][j] = true;
				}
			}
		}
	}
	public static void off(int x1, int y1, int x2, int y2){
		for(int i = x1; i <= x2; i++){
			for(int j = y1; j <= y2; j++){
				state[i][j] = false;
			}
		}
	}
	public static void on(int x1, int y1, int x2, int y2){
		for(int i = x1; i <= x2; i++){
			for(int j = y1; j <= y2; j++){
				state[i][j] = true;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		FileInputStream f = new FileInputStream(args[0]);
		String temp = "";
		char curchar = 0;
		int mode = 0;
		String[] coords;
		int x1,x2,y1,y2;
		while(curchar != (char)-1){
			curchar = (char)f.read();
			if(curchar == '\n'){
				coords = temp.split(" through ");
				x1 = coords[0].split(",")[0];
				y1 = coords[0].split(",")[1];
				x2 = coords[1].split(",")[0];
				y2 = coords[1].split(",")[1];
				switch(mode){
					case 1:
						mode = 0;
						on(x1,y1,x2,y2);
						break;
					case 2:
						mode = 0;
						off(x1,y1,x2,y2);
						break;
					case 3:
						mode = 0;
						toggle(x1,y1,x2,y2);
						break;
				}
			}
			temp += curchar;
			if(temp.equals("turn on ")){
				mode = 1;
				temp = "";
			}
			if(temp.equals("turn off ")){
				mode = 2;
				temp = "";
			}
			if(temp.equals("toggle ")){
				mode = 3;
				temp = "";
			}
		}
		int count = 0;
		for(int i = 0; i < 1000; i++){
			for(int j = 0; j < 1000; j++){
				if(state[i][j]){
					count++;
				}
			}
		}
		System.out.println(count);
	}
}