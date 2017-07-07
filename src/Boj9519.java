import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boj9519 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine());
		String line = br.readLine();
		int len = line.length();
		
		char[] words = new char[len];
		words = line.toCharArray();
		
		ArrayList<Character> list = new ArrayList<>();
		
		for(int i = 0; i < len; i++){
			list.add(words[i]);
		}
		
		
		
	}

}
