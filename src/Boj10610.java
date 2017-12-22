import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10610 {
	private static final int UNREAL = -1;
	private static final char HAS_ZERO = '0';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nums = br.readLine();
		int leng = nums.length();
		boolean chkTen = false;
		
		char[] num = nums.toCharArray();
		int[] mid = new int[num.length];
		
		for(int i = 0; i < num.length; i++){
			mid[i] = Character.getNumericValue(num[i]);
		}
		Arrays.sort(mid);
		
		for(int i = 0; i < leng; i++){
			if(HAS_ZERO == nums.charAt(i)){
				chkTen = true;
			}
		}
		
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		
		if(chkTen){
			for(int i = 0; i < mid.length; i++){
				sum += mid[i];
			}
			
			if(sum % 3 == 0){
				for(int i = mid.length - 1; i >= 0; i--){
					sb.append(mid[i]);
				}
				System.out.println(sb.toString());
			}
			else{
				System.out.println(UNREAL);
			}
		
		}
		else{
			System.out.println(UNREAL);
		}
		
	}
}
