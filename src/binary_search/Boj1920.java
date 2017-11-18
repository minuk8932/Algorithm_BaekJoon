package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1920 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int EXIST = 1;
	private static final int NOT_EXIST = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numN = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i =0; i < numN.length; i++){
			numN[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numN);
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), SPACE);
		while(M-- > 0){
			int key = Integer.parseInt(st.nextToken());
			int left = 0, right = N - 1;
			int mid = 0, cnt = 0;
			
LOOP:	while(right - left >= 0){
				mid = (left + right) / 2;
				
				if(key == numN[mid]){
					sb.append(EXIST).append(NEW_LINE);
					cnt++;
					break LOOP;
				}
				else if(key > numN[mid]){
					left = mid + 1;
				}
				else{
					right = mid - 1;
				}
			}
			
			if(cnt == 0){
				sb.append(NOT_EXIST).append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());
	}
}
