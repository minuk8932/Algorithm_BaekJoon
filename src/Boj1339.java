import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1339 {	
	private static int max = 0;
	private static boolean[] has = new boolean[26];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] alpha = new String[N];	
		for(int i = 0; i < N; i++) {
			alpha[i] = br.readLine();
			
			int leng = alpha[i].length();
			
			for(int j = 0; j < leng; j++) {
				if(has[alpha[i].charAt(j) - 'A']) continue;
				has[alpha[i].charAt(j) - 'A'] = true;
			}
		}

		for(int i = 0; i < N; i++) {
			backTracking(N, '9', alpha, i, 0);
		}
		
		System.out.println(max);
	}
	
	private static void backTracking(int N, char change, String[] alpha, int idx, int count) {
		if(complete()) {
			int value = 0;
			
			for(int i = 0; i < N; i++) {
				value += Integer.parseInt(alpha[i]);
			}
			
			if(max < value) max = value;
			
			return;
		}
		
		if(idx == N) return;
		if(count == alpha[idx].length()) return;
		
		char start = alpha[idx].charAt(count);
		
		for(int i = 0; i < N; i++) {
			if(start < 'A' || start > 'Z') continue;
			alpha[i] = alpha[i].replace(start, change);
			has[start - 'A'] = false;
			System.out.println(alpha[i]);
		}
		
		for(int next = 0; next < N; next++) {
			backTracking(N, (char) (change - 1), alpha, next, count + 1);
		}
		
		
//		backTracking(N, (char) (change - 1), alpha, idx + 1, 0);
	}
	
	private static boolean complete() {
		for(int i = 0; i < has.length; i++) {
			if(has[i]) return false;
		}
		
		return true;
	}
}
