package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj2605 {
	public static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> idx = new ArrayList<>();
		int[] num = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		idx.add(0, 1);
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(j == num[i]){
					idx.add(i - num[i], i+1);
				}
			}
		}

		for(int i = 0; i < N; i++){
			sb.append(idx.get(i)).append(SPACE);
		}
		
		System.out.println(sb.toString());
	}

}
