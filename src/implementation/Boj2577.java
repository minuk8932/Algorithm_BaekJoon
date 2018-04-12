package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2577 {

	public static void main(String[] args) throws Exception {
		char[] check = new char[10]; // �ִ� �ڸ��� : 1000^3
		int[] num = new int[10]; // 0~9 ���� ���� Ƚ���� ������ �迭.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int times = 1; //�� ���� ���� ������ ����.
		int[] alpha = new int[3];

		for (int i = 0; i < alpha.length; i++) { //�� ���� ���� ���� ��.

			alpha[i] = Integer.parseInt(br.readLine().trim());

			if (alpha[i] >= 100 && alpha[i] < 1000) // �� ���� ������ 100<= <1000
				times *= alpha[i];					//�� ������.
			else
				System.exit(0);						// ���� ������ ���������� ���α׷� ����.

		}
		br.close();

		String timesStr = String.valueOf(times); // int�� string���� ��ȯ.
		check = timesStr.toCharArray(); // string�� char�迭�� �ڸ��� ���� �Ѱ��� �Է�.
		Arrays.fill(num, 0); // num�� ��� 0���� �ʱ�ȭ.

		//�Ǵ� ������ �������� ������ش�, ���� ������.
		
		for (int i = 0; i < check.length; i++) {
			// �� �ڸ��� ���� Ƚ�� üũ ����.
			if (check[i] == '0')
				num[0]++;
			else if (check[i] == '1')
				num[1]++;
			else if (check[i] == '2')
				num[2]++;
			else if (check[i] == '3')
				num[3]++;
			else if (check[i] == '4')
				num[4]++;
			else if (check[i] == '5')
				num[5]++;
			else if (check[i] == '6')
				num[6]++;
			else if (check[i] == '7')
				num[7]++;
			else if (check[i] == '8')
				num[8]++;
			else if (check[i] == '9')
				num[9]++;

		}

		for (int i = 0; i < num.length; i++) {
			System.out.println(num[i]);
		}
	}

}
