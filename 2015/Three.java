import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
public class Three{
	public static void main(String[] args) throws IOException{
		Set<IntPair> visited = new HashSet<IntPair>();
		String path = Files.readString(Paths.get(args[0]));
		int curx = 0;
		int cury = 0;
		int curx2 = 0;
		int cury2 = 0;
		visited.add(new IntPair(curx, cury));
		for(int i = 0; i < path.length(); i++){
			if(i % 2 == 0){
				if(path.charAt(i) == '^'){
					cury++;
				} else if (path.charAt(i) == 'v'){
					cury--;
				} else if (path.charAt(i) == '<'){
					curx--;
				} else if (path.charAt(i) == '>'){
					curx++;
				}
				visited.add(new IntPair(curx,cury));
			} else {
				if(path.charAt(i) == '^'){
					cury2++;
				} else if (path.charAt(i) == 'v'){
					cury2--;
				} else if (path.charAt(i) == '<'){
					curx2--;
				} else if (path.charAt(i) == '>'){
					curx2++;
				}
				visited.add(new IntPair(curx2,cury2));
			}
		}
		System.out.println(visited.size());
	}
}