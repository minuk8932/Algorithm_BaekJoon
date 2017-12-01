import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2863 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		double[][] nums = new double[2][2];
		double[][] tmp = new double[2][2];
		
		for(int i = 0 ; i < 2; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 0; j < 2; j++){
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		double[][] res = new double[2][2];
		double max = 0;
		
		for(int i = 0 ; i < 2; i++){
			for(int j = 0; j < 2; j++){
				res[i][j] = (nums[0][0] / nums[1][0]) + (nums[0][1] / nums[1][1]);
				
				tmp[0][0] = nums[1][0];
				tmp[0][1] = nums[0][0];
				tmp[1][1] = nums[0][1];
				tmp[1][0] = nums[1][1];
				
				nums[0][0] = tmp[0][0];
				nums[0][1] = tmp[0][1];
				nums[1][0] = tmp[1][0];
				nums[1][1] = tmp[1][1];
				
			}
		}
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				max = Math.max(max, res[i][j]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){	
				if(max == res[i][j]){
					if(i == 0){
						if(j == 0){
							sb.append(0);
						}
						else{
							sb.append(1);
						}
					}
					else{
						if(j == 1){
							sb.append(3);
							System.out.println(res[1][1]);
						}
						else{
							sb.append(2);
						}
					}
				}
			}
		}
		System.out.println(sb.toString());
	}
}
