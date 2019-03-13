package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15820번: 맞았는데 왜 틀리죠??
 *
 *	@see https://www.acmicpc.net/problem/15820/
 *
 */
public class Boj15820 {
	private static class Pair{
		int ans, rep;
		
		public Pair(int ans, int rep) {
			this.ans = ans;
			this.rep = rep;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] S = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		
		Pair[] sample = new Pair[S[0]];
		Pair[] system = new Pair[S[1]];
		
		for(int i = 0; i < S[0]; i++) {
			st = new StringTokenizer(br.readLine());
			sample[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < S[1]; i++) {
			st = new StringTokenizer(br.readLine());
			system[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getAnswer(S, sample, system));
	}
	
	private static StringBuilder getAnswer(int[] size, Pair[] sam, Pair[] sys) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size[0]; i++) {									// 틀
			if(sam[i].ans != sam[i].rep) return sb.append("Wrong Answer");
		}
		
		for(int i = 0; i < size[1]; i++) {									// 맞 왜 틀?
			if(sys[i].ans != sys[i].rep) return sb.append("Why Wrong!!!");
		}
		
		return sb.append("Accepted");			// 맞
	}
}
