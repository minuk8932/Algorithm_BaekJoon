import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1062 {
	private static boolean[][] alpha;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		alpha = new boolean[N][26];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			int leng = line.length();
			
			alpha[i][0] = alpha[i][2] = alpha[i][8] = alpha[i]['n' - 'a'] = alpha[i]['t' - 'a'] = true;
			
			for(int j = 4; j < leng - 4; j++) {
				alpha[i][line.charAt(j)] = true;
			}
		}
		
		System.out.println(getWord(N, K));
	}
	
	private static int getWord(int n, int k) {
		
		
		return 0;
	}
}
