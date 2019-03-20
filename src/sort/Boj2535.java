package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2535번: 아시아 정보 올림피아드
 *
 *	@see https://www.acmicpc.net/problem/2535/
 *
 */
public class Boj2535 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static class AIO implements Comparable<AIO>{
		int country;
		int number;
		int score;
		
		public AIO(int country, int number, int score) {
			this.country = country;
			this.number = number;
			this.score = score;
		}

		@Override
		public int compareTo(AIO a) {
			if(this.score > a.score) return -1;
			else if(this.score < a.score) return 1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		AIO[] oly = new AIO[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			oly[i] = new AIO(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(oly);
		System.out.print(medals(N, oly));
	}
	
	private static StringBuilder medals(int n, AIO[] arr) {
		StringBuilder sb = new StringBuilder();
		int[] limit = new int[101];
		int count = 0;
		
		for(AIO prize: arr) {
			if(count == 3) break;			// 금 은 동
			
			if(limit[prize.country] >= 2) continue;		// 나라 당 최대 메달 갯수 = 2
			limit[prize.country]++;
			count++;
			
			sb.append(prize.country).append(SPACE).append(prize.number).append(NEW_LINE);
		}
		
		return sb;
	}
}
