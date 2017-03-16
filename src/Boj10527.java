import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10527 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String[] DOM = new String[N];
		String[] Katties = new String[N];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				if(i == 0){
					DOM[j] = br.readLine();
				}
				else {
					Katties[j] = br.readLine();
				}
			}
		}
		Arrays.sort(DOM);
		Arrays.sort(Katties);
		
		int cnt = 0;
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(DOM[i].equals(Katties[j])){					
					cnt++;
					i++;
				}
			}
		}
		System.out.println(cnt);
	}

}
