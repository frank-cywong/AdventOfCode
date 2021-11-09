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
		visited.add(new IntPair(curx, cury));
		for(int i = 0; i < path.length(); i++){
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
		}
		System.out.println(visited.size());
	}
}