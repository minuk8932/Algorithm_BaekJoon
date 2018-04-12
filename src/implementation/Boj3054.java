package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj3054 {
	// ���� ���ڿ� ���
	private static final String NEW_LINE = "\n";

	// * ���� ���
	private static final char ASTERISK = '*';

	// ������ ������ ���� ��� �迭
	private static final char[][] pFrame = { { '.', '.', '#', '.', '.' }, { '.', '#', '.', '#', '.' },
			{ '#', '.', 'X', '.', '#' }, { '.', '#', '.', '#', '.' }, { '.', '.', '#', '.', '.' } };

	// ���� ������ ���� ��� �迭
	private static final char[][] wFrame = { { '.', '.', '*', '.', '.' }, { '.', '*', '.', '*', '.' },
			{ '*', '.', 'X', '.', '*' }, { '.', '*', '.', '*', '.' }, { '.', '.', '*', '.', '.' } };

	public static void main(String args[]) throws Exception {
		// ���۸� ���� �Է� ���� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		br.close();

		// ���ڿ��� ���̸� ����
		int len = input.length();

		// �������� ���� ���̸� ����
		int width = len * 5 - (len - 1);

		// ĵ���� �迭 �ʱ�ȭ
		char[][] canvas = new char[5][width];

		// ���� ���� (1�� �������� �׸� �� ���� 1�� ����)
		int bias = 0;

		// ������ ���� �������� ĵ������ �׸�
		for (int i = 0; i < len; i++) {
			// ���� �������� �׷��� �ϴ� ���
			if (i != 0 && (i + 1) % 3 == 0) {
				for (int j = 0; j < 5; j++) {
					for (int k = 0; k < 5; k++) {
						canvas[j][5 * i + k - bias] = wFrame[j][k];
					}
				}
			}

			// ������ �������� �׷��� �ϴ� ���
			else {
				for (int j = 0; j < 5; j++) {
					for (int k = 0; k < 5; k++) {
						canvas[j][5 * i + k - bias] = pFrame[j][k];
					}
				}

				// ���� �������� ���� �������̶��
				if (i >= 3 && i % 3 == 0) {
					// #���� �߸� ��� �κ��� *�� ġȯ
					canvas[2][5 * i - bias] = ASTERISK;
				}
			}

			// �ܾ� ���� ����
			canvas[2][5 * i + 2 - bias] = input.charAt(i);

			// ���� ���� 1 ����
			bias++;
		}

		// ���۸� ���� ��� ���� ����
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < width; j++) {
				sb.append(canvas[i][j]);
			}

			sb.append(NEW_LINE);
		}

		// ��� �� �Ѳ����� ���
		System.out.println(sb.toString());
	}
}