package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17143번: 낚시왕
 *
 *	@see https://www.acmicpc.net/problem/17143/
 *
 */
public class Boj17143 {
	private static PriorityQueue<Shark> sharks = new PriorityQueue<>();
	private static int move;
	
	private static class Shark implements Comparable<Shark>{
		int row;
		int col;
		int speed;
		int dir;
		int size;
		
		public Shark(int row, int col, int speed, int dir, int size) {
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public int compareTo(Shark s) {
			if(this.col < s.col) {
				return -1;
			}
			else if(this.col > s.col) {
				return 1;
			}
			else {
				if(this.row < s.row) return -1;
				else if(this.row > s.row) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			sharks.offer(new Shark(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		System.out.println(fishing(R, C));
	}
	
	private static int fishing(int r, int c) {
		int sum = 0;
		
		for(int i = 0; i < c; i++) {
			sum += getShark(i);			// 잡은 상어 크기 합
			sharkMove(r, c);			// 상어 움직임
			eat(r, c);					// 잡아 먹히는 상어
		}
		
		return sum;
	}
	
	private static int getShark(int target) {
		PriorityQueue<Shark> pq = new PriorityQueue<>();
		Shark catched = new Shark(0, 0, 0, 0, 0);
		
		while(!sharks.isEmpty()) {
			Shark current = sharks.poll();
			
			if(current.col == target) {
				catched = new Shark(current.row, current.col, current.speed, current.dir, current.size);
				break;
			}
			
			pq.offer(current);
		}
		
		while(!pq.isEmpty()) sharks.offer(pq.poll());
		return catched.size;
	}
	
	private static void sharkMove(int r, int c) {
		PriorityQueue<Shark> pq = new PriorityQueue<>();
		
		while(!sharks.isEmpty()) {
			Shark current = sharks.poll();
			move = current.speed;
			
			while(move > 0) {
				switch (current.dir) {
				case 1:
					current = wandering(r, current, 1, true);
					break;
					
				case 2:			
					current = wandering(r, current, 2, true);
					break;
					
				case 3:
					current = wandering(c, current, 3, false);
					break;
					
				case 4:
					current = wandering(c, current, 4, false);
					break;
				}
			}

			pq.offer(current);
		}

		while(!pq.isEmpty()) sharks.offer(pq.poll());
	}
	
	private static Shark wandering(int leng, Shark s, int dir, boolean flag) {
		int value = (leng - 1) * 2;
		
		if(flag) {								// 수직
			if(move >= value) move %= value;
			
			if(dir == 1) {
				if(move <= s.row) {					// 배열 끝까지 못가는 경우
					s.row -= move;
					move = 0;
				}
				else {								// 배열 크기를 넘어선다면, 일단 끝까지만 붙여줌
					move = Math.abs(move - s.row);
					s.row = 0;
					
					dir = 2;						// 끝까지 붙었으니 방향 전환
				}
			}
			else {
				if(move + s.row <= leng - 1) {
					s.row += move;
					move = 0;
				}
				else {
					move -= Math.abs(s.row - (leng - 1));
					s.row = leng - 1;

					dir = 1;
				}
			}
		}
		else {									// 수평
			if(move >= value) move %= value;
			
			if(dir == 3) {
				if(move + s.col <= leng - 1) {
					s.col += move;
					move = 0;
				}
				else {
					move -= Math.abs(s.col - (leng - 1));
					s.col = leng - 1;
					
					dir = 4;
				}
			}
			else {
				if(move <= s.col) {
					s.col -= move;
					move = 0;
				}
				else {
					move = Math.abs(move - s.col);
					s.col = 0;
					
					dir = 3;
				}
			}
		}
		
		return new Shark(s.row, s.col, s.speed, dir, s.size);
	}
	
	private static void eat(int r, int c) {
		if(sharks.isEmpty()) return;
		
		PriorityQueue<Shark> pq = new PriorityQueue<>();
		Shark head = sharks.peek();
		
		while(!sharks.isEmpty()) {
			Shark current = sharks.poll();

			if(head.row == current.row && head.col == current.col) {
				if(head.size < current.size) {							// 잡아 먹힘
					head = new Shark(current.row, current.col, current.speed, current.dir, current.size);
				}
			}
			else {
				pq.offer(head);											// 안먹힌 경우 다음 상어로 바꿔줌
				head = new Shark(current.row, current.col, current.speed, current.dir, current.size);
			}
			
			if(sharks.isEmpty()) pq.offer(head);			// 마지막에 무조건 한마리 가져옴 (남겨진 상어)
		}

		while(!pq.isEmpty()) sharks.offer(pq.poll());
	}
}
