package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1551번: 수열의 변화
 *
 *	@see https://www.acmicpc.net/problem/1551/
 *
 */
public class Boj1551 {
	private static final String SEPARATE = ",";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int loop = K;
		
		int[] seq = new int[N];
		String origin = br.readLine();
		st = new StringTokenizer(origin, SEPARATE);
		
		for(int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		int range = 0;
		
		while(loop-- > 0) {
			for(int i = 0; i < seq.length - 1 - range; i++) {	// 수열의 규칙에따라 계산
				seq[i] = seq[i + 1] - seq[i];
				
				if(loop == 0) {
					sb.append(seq[i]).append(SEPARATE);	// 만약 마지막 수열 계산인 경우 버퍼에 해당 수열의 값들을 담아줌
				}
			}
			range++;	// 수열이 진행 될 때마다 변경해야할 범위
		}
		
		// K == 0인 경우 본래의 수열을, 그 외의 경우엔 버퍼에 담긴 수열을 출력
		System.out.println(K == 0 ? origin : sb.toString().substring(0, sb.length() - 1));
	}
}
