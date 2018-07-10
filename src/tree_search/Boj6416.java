package tree_search;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * 	@author minchoba
 *	백준 6416번: 트리인가?
 *
 *	@see https://www.acmicpc.net/problem/6416/
 *
 */
public class Boj6416 {
	private static final String CASE = "Case ";
	private static final String IS_TREE = " is a tree.";
	private static final String IS_NOT_TREE = " is not a tree.";
	private static final String NEW_LINE = "\n";
	
	private static int isCorrect = 0;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int t = 1;

		int stop1 = -2, stop2 = -2;

		while (true) {
			ArrayList<Integer> nums = new ArrayList<>();

			stop1 = sc.nextInt();
			stop2 = sc.nextInt();

			if (stop1 == -1 || stop2 == -1)		// 입력된 숫자가 -1 -1 인경우 모든 반복문 종료
				break;

			while (true) {
				if (stop1 == 0 && stop2 == 0)	// 입력된 숫자가 0 0 인경우 입력 반복문 종료
					break;

				if (stop1 != 0 || stop1 != -1 || stop2 != 0 || stop2 != -1) {
					nums.add(stop1);
					nums.add(stop2);
				}

				stop1 = sc.nextInt();
				stop2 = sc.nextInt();
			}

			int numSize = nums.size();
			int arrSize = getSize(nums, numSize) + 1;		// 필요한 사이즈를 getSize 메소드를 통해 구해옴

			ArrayList<Integer>[] tree = new ArrayList[arrSize];
			for (int i = 0; i < arrSize; i++) {
				tree[i] = new ArrayList<>();
			}

			for (int i = 0; i < numSize; i += 2) {		// 인접리스트에 트리의 정보를 담아줌
				int from = nums.get(i);
				int to = nums.get(i + 1);

				tree[from].add(to);
			}

			isCorrect = 0;
			int root = findRoot(tree, nums, arrSize);		// findRoot 메소드를 통해 루트 노드를 구함
			boolean res = isTree(tree, root, arrSize);		// isTree 메소드를 통해 트리의 적합성 여부를 판별
			
			if(isCorrect != 1) res = false;		// 루트 노드가 1개보다 많은 경우
			if(numSize == 0) res = true;		// 트리의 정보가 들어오지 않는 경우
			
			// 테스트 케이스 별로 버퍼에 결과를 담아줌
			sb.append(CASE).append(t++).append(res ? IS_TREE : IS_NOT_TREE).append(NEW_LINE);
		}

		System.out.println(sb.toString());	// 결과값 한번에 출력
	}
	
	/**
	 * 크기 값 반환 메소드
	 * 
	 */
	private static int getSize(ArrayList<Integer> num, int size) {
		int max = 0;

		for (int i = 0; i < size; i++) {
			int elem = num.get(i);		// 리스트 내의 가장 큰 값을 받아 온 후

			if (elem > max)
				max = elem;
		}

		return max;		// 값 반환
	}

	/**
	 * 루트 노드번호 반환 메소드
	 * 
	 */
	private static int findRoot(ArrayList<Integer>[] tree, ArrayList<Integer> num, int arrSize) {
		boolean[] findRoot = new boolean[arrSize];

		int leng = num.size();

		for (int i = 0; i < leng; i++) {		// 리스트 내에 등장하는 값들을 참으로 변경
			int idx = num.get(i);

			if (!findRoot[idx])
				findRoot[idx] = true;
		}

		for (int k = 0; k < arrSize; k++) {
			if (!findRoot[k]) continue;

			for (int i = 0; i < arrSize; i++) {
				int size = tree[i].size();

				for (int j = 0; j < size; j++) {	// 참값 중 루트노드에 해당하지 않는 값들을 거짓으로 변경
					if (tree[i].get(j) == k)
						findRoot[k] = false;
				}
			}
		}

		int idx = 0;

		for (int i = 0; i < arrSize; i++) {		// 루트노드의 갯수와 노드의 번호를 받아옴
			if (findRoot[i]) {
				idx = i;
				isCorrect++;
			}
		}

		return idx;
	}
	
	/**
	 * 트리 적합성 판별 메소드
	 * 
	 */
	private static boolean isTree(ArrayList<Integer>[] tree, int r, int arrSize) {
		boolean[] isVisited = new boolean[arrSize];

		Queue<Integer> q = new LinkedList<>();
		q.offer(r);

		isVisited[r] = true;	// 루트 노드부터 탐색하여

		while (!q.isEmpty()) {
			int current = q.poll();

			for (int next : tree[current]) {
				if (next > 0 && next < arrSize) {
					if (isVisited[next])		// 만약 2회 이상 방문한 노드가 있다면, 거짓	
						return false;

					isVisited[next] = true;
					q.offer(next);
				}
			}
		}

		return true;		// 그 외, 참
	}
}
