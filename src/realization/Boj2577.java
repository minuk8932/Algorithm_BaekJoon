package realization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2577 {

	public static void main(String[] args) throws Exception {
		char[] check = new char[10]; // 최대 자릿수 : 1000^3
		int[] num = new int[10]; // 0~9 슛자 마다 횟수를 세어줄 배열.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int times = 1; //세 수의 곱을 저장할 변수.
		int[] alpha = new int[3];

		for (int i = 0; i < alpha.length; i++) { //세 수를 받을 루프 문.

			alpha[i] = Integer.parseInt(br.readLine().trim());

			if (alpha[i] >= 100 && alpha[i] < 1000) // 각 수의 범위는 100<= <1000
				times *= alpha[i];					//다 곱해줌.
			else
				System.exit(0);						// 만약 범위에 맞지않으면 프로그램 종료.

		}
		br.close();

		String timesStr = String.valueOf(times); // int를 string으로 전환.
		check = timesStr.toCharArray(); // string을 char배열에 자릿수 마다 한개씩 입력.
		Arrays.fill(num, 0); // num에 모두 0으로 초기화.

		//또는 나머지 연산으로 계산해준다, 조금 복잡함.
		
		for (int i = 0; i < check.length; i++) {
			// 각 자릿수 마다 횟수 체크 시작.
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
