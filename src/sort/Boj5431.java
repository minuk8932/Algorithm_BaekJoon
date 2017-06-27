package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj5431 {
	public static int chk = 0;
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		 ArrayList<Integer> arr = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++){
			int M = Integer.parseInt(br.readLine());
			//Number[] num = new Number[M];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++){
				arr.add(Integer.parseInt(st.nextToken()));
				//num[j] = new Number(Integer.parseInt(st.nextToken()));				
			}
			
			for(int j = 0; j < M; j++){
				if(arr.get(j) > arr.get(j+1)){
					//
				}
			}
			//Arrays.sort(num, Number.comparator);
			sb.append(chk).append(NEW_LINE);
		}
		
		
		
		
		System.out.println(sb.toString());
		
	}
	

}
