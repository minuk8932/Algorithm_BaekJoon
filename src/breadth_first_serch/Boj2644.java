package breadth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2644번 : 촌수계산
 *
 *	@see https://www.acmicpc.net/problem/2644
 *
 */
public class Boj2644 {
	public static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // total people

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int start = Integer.parseInt(st.nextToken());
		int finish = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] list = new ArrayList[n + 1];		// 인접리스트 선언

		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}

		int line = Integer.parseInt(br.readLine());

		while (line-- > 0) {
			st = new StringTokenizer(br.readLine(), SPACE);

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			list[x].add(y);										// 각 관계를 담아줌 (양방향)
			list[y].add(x);
		}

		br.close();

		int[] isVisited = new int[n + 1];
		isVisited[start] = 1;

		Queue<Integer> queue = new LinkedList<>();		// BFS 실행
		queue.offer(start);

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for(int next : list[current]){
				if(isVisited[next] == 0){
					isVisited[next] = isVisited[current] + 1;		// 배열에 해당 관계를 넘어가며 촌수를 저장
					queue.offer(next);
				}
			}
		}

		if (isVisited[finish] == 0){										// 만약 목적 관계의 촌수 계산이 불가하다면
			System.out.println(-1);
		}

		else {
			System.out.println(isVisited[finish] - 1);				// 촌수 계산이 가능한 경우 결과값 출력
		}
	}

}
