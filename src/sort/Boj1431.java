package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Boj1431 {
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		SortNum[] sn = new SortNum[T];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < T; i++) {
			sn[i] = new SortNum(br.readLine());
		}
		
		Arrays.sort(sn, SortNum.comparator);
		
		for(int i = 0; i < T; i++){
			sb.append(sn[i].num).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

	private static class SortNum {
		public String num;

		private SortNum(String num) {
			this.num = num;
			
		}

		private static Comparator<SortNum> comparator = new Comparator<SortNum>() {

			@Override
			public int compare(SortNum sn1, SortNum sn2) {
				int leng1 = sn1.num.length();
				int leng2 = sn2.num.length();
				
				if (leng1 != leng2) {
					if (leng1 < leng2) {
						return -1;
					}
				} 
				else {					
					int sum1 = 0, sum2 = 0;
					
					for(int i = 0; i < leng1; i++){
						if(sn1.num.charAt(i) < 'A' || sn1.num.charAt(i) > 'Z'){
							sum1 += (int) (sn1.num.charAt(i) - '0');
							
						}
						
						if(sn2.num.charAt(i) < 'A' || sn2.num.charAt(i) > 'Z'){
							sum2 += (int) (sn2.num.charAt(i) - '0');
							
						}
						
					}
					
					if(sum1 < sum2){
						return -1;
					}
					
					else if(sum1 == sum2){
							return sn1.num.compareTo(sn2.num);
					}
					
				} // length
				return 1;
			}
		};
	}

}
