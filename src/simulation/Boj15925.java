package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15925번: 욱제는 정치쟁이야!!
 *
 *	@see https://www.acmicpc.net/problem/15925/
 *	
 */
public class Boj15925 {
	private static final String OFF = "0";
	
	private static boolean[][] room;
	private static boolean status;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		boolean flag = st.nextToken().equals(OFF) ? false: true;
		
		room = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				if(!st.nextToken().equals(OFF)) room[i][j] = true;
			}
		}
		
		System.out.println(manipulate(N, flag));
	}
	
	private static int manipulate(int n, boolean f) {		
		while(true) {
			status = false;
			
			for(int i = 0; i < n; i++) {
				int colCount = 0, rowCount = 0;
				
				for(int j = 0; j < n; j++) {
					if(room[i][j] == f) colCount++;		// 목표 상태와 같은 상태의 컴퓨터 갯수
					if(room[j][i] == f) rowCount++;
				}
				
				switching(n, colCount, i, f, true);		// 목표 상태에 맞게 컴퓨터 상태 변경
				switching(n, rowCount, i, f, false);
			}

			if(!status) break;							// 상태 변화 없으면 종료
		}
		
		return getStatus(n, f, room);					// 모두 목표대로 바뀌었는가
	}
	
	private static void switching(int n, int count, int idx, boolean f, boolean rowCol) {
		for(int j = 0; j < n; j++) {
			if(count > n / 2) {
				if(rowCol && room[idx][j] != f) {
					room[idx][j] = f;
					status = true;
				}
				
				if(!rowCol && room[j][idx] != f) {
					room[j][idx] = f;
					status = true;
				}
			}
		}
	}
	
	private static int getStatus(int n, boolean f, boolean[][] arr) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(arr[i][j] != f) return 0;
			}
		}
		
		return 1;
	}
}
