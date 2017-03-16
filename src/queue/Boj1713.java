package queue;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * ���� �¶��� ���� 1713�� (�ĺ� ��õ�ϱ�) ����Ǯ��
 *
 * @see https://www.acmicpc.net/problem/1713
 * @author devetude
 */
public class Boj1713 {
	// ���� ���ڿ� ���
	private static final String SPACE = " ";

	public static void main(String args[]) throws Exception {
		// �Է� ���� ����
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = sc.nextInt();

		// ť ��ü ���� �ʱ�ȭ
		Queue<Integer> queue = new LinkedList<>();

		// �ĺ� ��õ �� ���� �迭 �ʱ�ȭ
		int[] points = new int[cnt + 1];

		for (int i = 0; i < cnt; i++) {
			int idx = sc.nextInt();
			points[idx]++;

			// �̹� �ش� �ĺ��� ������ �ɷ����� ���� ��쿡��
			if (!queue.contains(idx)) {
				// �ĺ��� ������ ����Ʋ�� ���� ���� ���� ��쿡��
				if (queue.size() >= N) {
					Iterator<Integer> iterator = queue.iterator();
					int minPointIdx = iterator.next();

					// ���� ��õ ���� ���� �ĺ��� ã�Ƴ� (��õ ���� ���� ��� ���� �ɷ��ִ� �������� ã�Ƴ�)
					while (iterator.hasNext()) {
						int comparingIdx = iterator.next();

						if (points[comparingIdx] < points[minPointIdx]) {
							minPointIdx = comparingIdx;
						}
					}

					// �ش� �ĺ��� ť���� ���� �� ���� 0���� ����
					queue.remove(minPointIdx);
					points[minPointIdx] = 0;
				}

				// �ش� �ĺ��� ������ ����Ʋ�� �߰�
				queue.offer(idx);
			}
		}

		sc.close();

		// ť�� ������������ ����
		Collections.sort((LinkedList<Integer>) queue);

		// ���۸� ���� ��� ���� ����
		StringBuilder sb = new StringBuilder();

		while (!queue.isEmpty()) {
			sb.append(queue.poll()).append(SPACE);
		}

		// ��� �� �Ѳ����� ���
		System.out.println(sb.toString());
	}
}