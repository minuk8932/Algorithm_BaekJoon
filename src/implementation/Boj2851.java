package implementation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ���� �¶��� ���� 2851�� (���� ������) ����Ǯ��
 *
 * @see https://www.acmicpc.net/problem/2851
 * @author devetude
 */
public class Boj2851 {
	public static void main(String args[]) throws Exception {
		// ���۸� ���� �Է� ���� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// �� ���� ���� ����
		int totalPoint = 0;

		for (int i = 0; i < 10; i++) {
			int currentPoint = Integer.parseInt(br.readLine());
			int currentDistance = 100 - totalPoint;

			// 100���� �־��� ����� ���� ��� ������ Ż��
			if (Math.abs(currentDistance) < Math.abs(currentDistance - currentPoint)) {
				break;
			}

			// �� ������ ���� ������ ����
			totalPoint += currentPoint;

		}

		br.close();

		// ��� �� ���
		System.out.println(totalPoint);
	}
}
