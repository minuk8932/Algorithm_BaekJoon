import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj9519 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		
		String line = br.readLine();
		int len = line.length();
		
		ArrayList<Character> res = new ArrayList<>();
		
		for(int i = 1; i < len + 1; i++){
			res.add(line.charAt(i - 1));
		}
		
		// abcdef afbecd adfcbe
		
		if(len % 2 == 0){
			for(int i = 1; i < X + 1; i++){
				res.remove(i);
			}
		}
		else{
			
		}
	}

}
