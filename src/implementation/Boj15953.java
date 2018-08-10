package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15953번: 상금 헌터
 *
 *	@see https://www.acmicpc.net/problem/15953/
 *
 */
public class Boj15953 {
	private static final int[] RANK_1 = {0, 1, 3, 6, 10, 15, 21};
	private static final int[] REWARD_1 = {0, 500, 300, 200, 50, 30, 10};
	private static final int[] RANK_2 = {0, 1, 3, 7, 15, 31};
	private static final int[] REWARD_2 = {0, 512, 256, 128, 64, 32};
	
	private static final String NEW_LINE = "\n";
	private static final String EMPTY = "";
	private static final String MAAN = "0000";
    
    private static int res = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
            res = 0;
            
            getGold1(a);	// 17년 상금 메소드
			getGold2(b);	// 18년 상금 메소드
			
			// 각 경우별 상금을 담고
			sb.append(res).append(res == 0 ? EMPTY : MAAN).append(NEW_LINE);
		}
		
		// 결과값 한번에 출력
		System.out.println(sb.toString());
	}
    
	/**
	 * 17년 상금 메소드
	 * 
	 */
    private static void getGold1(int num){
        for(int i = 1; i < RANK_1.length; i++) {
			if(num > RANK_1[i - 1] && num <= RANK_1[i]) {
				res += REWARD_1[i];
					
				return;
		    }
		}        
    }
    
    /**
     * 18년 상금 메소드
     * 
     */
    private static void getGold2(int num){
        for(int i = 1; i < RANK_2.length; i++) {
			if(num > RANK_2[i - 1] && num <= RANK_2[i]) {
				res += REWARD_2[i];
					
				return;
			}
		}
    }
}