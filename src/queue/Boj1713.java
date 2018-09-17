package queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1713번: 후보 추천하기
 *
 *	@see https://www.acmicpc.net/problem/1713/
 *
 */
public class Boj1713 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int recomCnt = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Queue<Integer> q = new LinkedList<>();
		int[] album = new int[recomCnt + 1];	// 제거 할 배열 포함

		for (int i = 0; i < recomCnt; i++) {
			int p = Integer.parseInt(st.nextToken());
			album[p]++;			// 앨범에 후보 추가

			if (!q.contains(p)) {			// 큐 내부에 현재 들어온 후보가 존재하는가
				if (q.size() >= N) {				// 큐가 앨범의 갯수 한도를 초과한 경우
					Iterator<Integer> it = q.iterator();
					int removeCandidate = it.next();

					while (it.hasNext()) {				// 이터레이터를 통해 큐 내부의 값들을 가져와서
						int addCandidate = it.next();

						// 제거될 후보를 남기고 앞으로 당겨줌 
						if (album[addCandidate] < album[removeCandidate]) removeCandidate = addCandidate;
					}

					q.remove(removeCandidate);	// 후보제거
					album[removeCandidate] = 0;
				}

				q.offer(p);		// 큐에 앨범에 존재하는 후보를 추가
			}
		}

		Collections.sort((LinkedList<Integer>) q);		// 큐 내부 정렬
		StringBuilder sb = new StringBuilder();

		while (!q.isEmpty()) sb.append(q.poll()).append(SPACE);		// 큐의 값들을 오름 차순으로 버퍼에 담음

		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
}
