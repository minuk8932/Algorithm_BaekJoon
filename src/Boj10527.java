import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Boj10527 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayList<String> dom = new ArrayList<>();
		ArrayList<String> kat = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			dom.add(line);
		}
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			kat.add(line);
		}
		br.close();
		
		Collections.sort(dom);
		Collections.sort(kat);
		
		int res = 0;
		
		
	}
}
