package bandwidth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2644 {
	public static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // total people

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int start = Integer.parseInt(st.nextToken());
		int finish = Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] list = new ArrayList[n + 1];

		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}

		int line = Integer.parseInt(br.readLine());

		while (line-- > 0) {
			st = new StringTokenizer(br.readLine(), SPACE);

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			list[x].add(y);
			list[y].add(x);
		}

		br.close();

		int[] isVisited = new int[n + 1];
		isVisited[start] = 1;

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for(int next : list[current]){
				if(isVisited[next] == 0){
					isVisited[next] = isVisited[current] + 1;
					queue.offer(next);
				}
			}
		}

		if (isVisited[finish] == 0){
			System.out.println(-1);
		}

		else {
			System.out.println(isVisited[finish] - 1);
		}
	}

}
