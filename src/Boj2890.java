import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2890 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 100;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] kajak = new char[R + 1][C];
		String[] line = new String[R + 1];
		
		for(int i = 1; i < R + 1; i++){
			line[i] = br.readLine();
			for(int j = 0; j < C; j++){
				kajak[i][j] = line[i].charAt(j);
			}
		}
		
		int[] seq = new int[R + 1];
		
		for(int i = 1; i < R + 1; i++){
			for(int j = 0; j < C; j++){
				
				char point = line[i].charAt(j);
				
				if(point != '.' && point != 'S' && point != 'F'){
					seq[i] = Character.getNumericValue(point);
				}
			}
		}
		
		int[] res = new int[R + 1];
		Arrays.fill(res, INF);
		
		for(int i = 1; i < R + 1; i++){
			for(int j = 0; j < C; j++){
				if(seq[i] != 0){
					if(seq[i] == Character.getNumericValue(kajak[i][j])){
						if(res[seq[i]] > j + 1){
							res[seq[i]] = j + 1;
						}
					}
				}
			}
		}
		
		int[] nums = new int[R + 1];
		int[] chk = new int[R + 1];
		int min = INF;
		
		for(int i = 1; i < R + 1; i++){
			if(seq[i] != 0){
				for(int j = 2; j < R + 1; j++){
					if(res[seq[i]] > res[seq[j]]){
						nums[i]++;
					}
					
					if(res[seq[i]] == res[seq[j]]){
						chk[seq[i]] = 1;
					}
				}
				
				min = Math.min(min, nums[i]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		
		for(int i = 1; i < R + 1; i++){
			if(seq[i] != 0){
				if(chk[seq[i]] == 1){
					sb.append(9 - nums[i] - cnt++).append(NEW_LINE);
				}
				else{
					sb.append(9 - nums[i] - cnt).append(NEW_LINE);
				}
			}	
		}
		
		System.out.println(sb.toString());
	}
}
