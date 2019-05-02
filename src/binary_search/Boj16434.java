package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16434번: 드래곤 앤 던전
 *
 *	@see https://www.acmicpc.net/problem/16434/
 *
 */
public class Boj16434 {
	private static final long INF = Long.MAX_VALUE;
	
	private static class Room{
		int query;
		int atk;
		int life;
		
		public Room(int query, int atk, int life) {
			this.query = query;
			this.atk = atk;
			this.life = life;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int hAtk = Integer.parseInt(st.nextToken());
		
		Room[] r = new Room[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			r[i] = new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(expedition(N, hAtk, r));
	}
	
	private static long expedition(int n, long atk, Room[] arr) {		
		long start = 0, end = INF;
		long result = 0;
		
		while(start <= end) {
			long mid = (start + end) / 2;			// 최소 체력
			
			if(expedition(arr, mid, mid, atk)) {	// 정복
				end = mid - 1;
				result = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		return result;
	}
	
	private static boolean expedition(Room[] arr, long ml, long cl, long atk) {
		for(Room state: arr) {
			if(state.query == 1) {
				long count = state.life % atk == 0 ? state.life / atk - 1: state.life / atk;		// 타격 횟수
				cl -= count * state.atk;															// 체력 감소
				
				if(cl <= 0) return false;			// 몬스터한테 졌을 경우
			}
			else {
				cl = cl + state.life >= ml ? ml: cl + state.life;
				atk += state.atk;
			}
		}
		
		return true;
	}
}
