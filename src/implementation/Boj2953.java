package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2953 {
	public static final String SPACE = " ";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int att = 5;
		int score  = 4;
		int[] sum = new int[att];
		int[] tmp = new int[att];
		
		for(int j = 0; j < att; j++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0 ; i < score; i++){
				sum[j] += Integer.parseInt(st.nextToken());
				if(i == score - 1){
					tmp[j] = sum[j];
					
				}
			}
		}
		
		Arrays.sort(tmp);
		
		for(int i = 0; i < att; i++){
			if(tmp[att-1] == sum[i]){
				sb.append(i+1).append(SPACE).append(tmp[att-1]);
			}
		}
		System.out.println(sb.toString());
	}

}
