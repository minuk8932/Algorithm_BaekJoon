import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11722 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		for(int i = 0; i < arr.length - 1; i++){
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(arr[i], max);			// max 값 산출
		}
		
		int[][] dp = new int[N][N];
		int maxArr = 0;
		int chk = 0;
		
		// 뽑아낸 최초 max 값이 몇번째에 존재하는지 i에 저장
		for(int i = 0; i < arr.length; i++){
			if(max == arr[i] && chk == 0){
				maxArr = i;
				chk++;
			}
		}
		
//		int cnt = 0;
//		
//		if(N == 1){
//			dp[0] = arr[0];
//			
//			for(int i = 0; i < arr.length; i++){
//				if(dp[i] != 0){
//					cnt++;
//				}
//			}
//		}
		
		// 해당 최초 max 값을 제외한 왼쪽에 존재하는 수는 모두 무시
		
	}
}
