package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ���� �¶��� ���� 3023�� (������ �̹���) ����Ǯ��
 *
 * @see https://www.acmicpc.net/problem/3023
 * @author devetude
 */
public class Boj3023 {
	// ���ڿ� ���
	private static final String NEW_LINE = "\n";
	private static final String SHARP = "#";
	private static final String DOT = ".";

	public static void main(String args[]) throws Exception {
		// ���۸� ���� �Է� ���� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		// ������ ���� �� ī�� �迭 �ʱ�ȭ
		String[][] card = new String[R * 2][C * 2];

		// ������ ���� ��� ��, �� ������ ����
		for (int i = 0; i < R; i++) {
			String[] patterns = br.readLine().split("");

			for (int j = 0; j < C; j++) {
				card[i][j] = patterns[j];
			}

			for (int j = 0; j < C; j++) {
				card[i][2 * C - j - 1] = card[i][j];
			}
		}

		// ������ ���� �ϴ� ������ ����
		int gap = 1;

		for (int i = R; i < card.length; i++) {
			for (int j = 0; j < card[i].length; j++) {
				card[i][j] = card[R - gap][j];
			}

			gap++;
		}

		st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken()) - 1;
		int B = Integer.parseInt(st.nextToken()) - 1;

		br.close();

		// �ǵ��� ������ ����
		card[A][B] = card[A][B].equals(SHARP) ? DOT : SHARP;

		// ���۸� ���� ��� ���� ����
		StringBuilder sb = new StringBuilder();

		for (String[] row : card) {
			for (String col : row) {
				sb.append(col);
			}

			sb.append(NEW_LINE);
		}

		// ��� �� �Ѳ����� ���
		System.out.println(sb.toString());
	}
}
