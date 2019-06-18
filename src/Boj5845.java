import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 5845번: Message Relay
 *
 *	@see https://www.acmicpc.net/problem/5845/
 *
 */
public class Boj5845 {
	private static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] msg = new int[N];		
		for(int i = 0; i < N; i++) {
			msg[i] = Integer.parseInt(br.readLine()) - 1;
		}
		
		int count = 0;
		for(int i = 0; i < N; i++) {
			visit = new boolean[N];
			if(!dfs(msg, i)) count++;		// 루프 돌지 않는 메세지 갯수
		}
		
		System.out.println(count);
	}
	
	private static boolean dfs(int[] arr, int current) {
		if(arr[current] == -1) return false;	// 메세지 전달하지 않는 소로 도달한 경우
		
		if(visit[current]) return true;			// 이미 방문한 소: loopy
		visit[current] = true;
		
		return dfs(arr, arr[current]);
	}
}
