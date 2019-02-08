package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2251번: 물통
 *
 *	@see https://www.acmicpc.net/problem/2251/
 *
 */
public class Boj2251 {
	private static final String SPACE = " ";
	
	private static class Point{
		int a;
		int b;
		
		public Point(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(A, B, C));
	}
	
	private static StringBuilder bfs(int a, int b, int c) {
		StringBuilder sb = new StringBuilder();
		boolean[][] isVisited = new boolean[c + 1][c + 1];
		boolean[] cbottle = new boolean[c + 1];
		
		
		Queue<Point> q = new LinkedList<>();
		isVisited[0][0] = true;
		cbottle[c] = true;
		
		q.offer(new Point(0, 0));
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int water = c - (current.a + current.b);
			
			final int[][] WATER_VOLUME = 
				{{current.a, current.b}, {current.a, water}, {current.b, current.a}, {current.b, water}, {water, current.a}, {water, current.b}};
			final int[][] BOTTLE_VOLUME =
				{{a, b}, {a, c}, {b, a}, {b, c}, {c, a}, {c, b}};
			
			for(int i = 0; i < 6; i++) {
				int waterSum = WATER_VOLUME[i][0] + WATER_VOLUME[i][1];
				int remainder = 0;
				
				if(waterSum > BOTTLE_VOLUME[i][1]) {
					remainder = waterSum - BOTTLE_VOLUME[i][1];
					waterSum = BOTTLE_VOLUME[i][1];
				}
				
				int[] nextArr = getNext(remainder, waterSum, current.a, current.b, water, i);		// 병에 들어갈 다음 물의 양 계산
				
				if(!isVisited[nextArr[0]][nextArr[1]]){			// a, b에 이미 채운 양일 경우
					isVisited[nextArr[0]][nextArr[1]] = true;
					
					if(nextArr[0] == 0) cbottle[nextArr[2]] = true;		// a가 0일때 c에 존재가능 한 물의 양
					q.offer(new Point(nextArr[0], nextArr[1]));
				}
			}
		}
		
		for(int i = 0; i < cbottle.length; i++) {
			if(cbottle[i]) sb.append(i).append(SPACE);
		}
		
		return sb;
	}
	
	private static int[] getNext(int r, int w, int a, int b, int c, int idx) {
		int[] arr = new int[3];
		
		arr[0] = idx <= 1 ? r : idx % 2 == 0 ? w : a;
		arr[1] = idx == 0 || idx == 5 ? w : idx == 2 || idx == 3 ? r : b;
		arr[2] = idx == 1 || idx == 3 ? w : idx >= 4 ? r : c;
		
		return arr;
	}
}
