package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1476 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 1 ~ 15 , 1 ~ 28, 1 ~ 19
		int[] year = new int[3];
		int[] origin = new int[3];
		int Years = 1;

		for (int i = 0; i < year.length; i++) {
			year[i] = 1;
			origin[i] = Integer.parseInt(st.nextToken());
		}

	LOOP: while (true) {
			
			if (origin[0] == year[0] && origin[1] == year[1] && origin[2] == year[2]) {
				break LOOP;
			}
			
			year[0]++;
			year[1]++;
			year[2]++;
			Years++;

			if (year[0] == 16) {
				year[0] = 1;
			}
			if (year[1] == 29) {
				year[1] = 1;
			}
			if (year[2] == 20) {
				year[2] = 1;
			}

		}
		System.out.println(Years);
	}

}
