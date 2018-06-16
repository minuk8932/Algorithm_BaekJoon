import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	리버스가 들어오면 커서지정..?
 */
public class Boj5430 {
	private static final String SEPARATE = ",";
	private static final String NEW_LINE = "\n";
	private static final String ERROR = "error";
	
	private static final char REV = 'R';
	private static final char DEL = 'D';
	private static final char PRE = '[';
	private static final char POS = ']';
	
	private static int[] nums = null;
	private static int cursor = 0;
	private static int times = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			char[] command = br.readLine().toCharArray();
			int N = Integer.parseInt(br.readLine());
			boolean isBreak = false, pass = false, isVisited = false;
			
			cursor = 0;
			times = 0;
			
			nums = new int[N];
			String arr = br.readLine();
			
			StringTokenizer st = new StringTokenizer(arr.substring(1, arr.length() - 1), SEPARATE);
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			if(nums.length == 0) {
				pass = true;
			}
			else {
				for(int i = 0; i < command.length; i++) {
					if(command[i] == REV) {
						if(!isVisited) {
							cursor = nums.length - 1 - times;
							isVisited = true;
						}
						else {
							cursor = 0;
							isVisited = false;
						}
					}
					
					else if(command[i] == DEL) {
						int chk = isRight();
						
						if(chk == 0) {
							isBreak = true;
							break;			
						}
						delete();
						times++;
					}
				}
			}
			
			if(nums.length == 0 && !pass) {
				sb.append(PRE).append(POS).append(NEW_LINE);
				continue;
			}
			
			if(isBreak || pass) sb.append(ERROR).append(NEW_LINE);
			else {
				sb.append(PRE);
				for(int i = 0; i < nums.length; i++) {
					if(nums[i] == -1) break;
					sb.append(nums[i]).append(SEPARATE);
				}
				
				sb.deleteCharAt(sb.length() - 1);
				sb.append(POS).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());
	}
	
	private static void delete() {
		nums[cursor] = 0;
		
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] == 0) break;
			if(nums[i] != 0) break;
			nums[i - 1] = nums[i];
			nums[i] = -1;
		}		
	}
	
	private static int isRight() {
		int isEmpty = 0;
		
		if(nums.length == 0) return -1;
		if(nums.length > 0 && nums[0] != 0) isEmpty = 1;
		
		return isEmpty;
	}
}
