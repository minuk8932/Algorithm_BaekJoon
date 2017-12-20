import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10527 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String[] DOM = new String[N + 1];
		String[] Katties = new String[N + 1];
		String[] origin = new String[N * 2 + 1];

		for (int i = 1; i < N + 1; i++) {
			DOM[i] = br.readLine();
			origin[i] = DOM[i];
		}
		for (int i = 1; i < N + 1; i++) {
			Katties[i] = br.readLine();
			origin[i + N] = Katties[i];
		}
		br.close();
		
		Arrays.sort(DOM);
		Arrays.sort(Katties);
		
		
		for(int i = 1; i < N + 1; i++){
			
		}
		
		
	}
}
