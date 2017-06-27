package sort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj11651 {
	public static final String SPACE = " ";
	public static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		AxisSort[] as = new AxisSort[T];
		
		for (int i = 0; i < T; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
			
			as[i] = new AxisSort(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(as, AxisSort.comparator);
		
		for(int i = 0; i < T; i++){
			sb.append(as[i].x).append(SPACE).append(as[i].y).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class AxisSort{
		public int x;
		public int y;
		
		private AxisSort(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		private static Comparator<AxisSort> comparator = new Comparator<AxisSort>() {

			@Override
			public int compare(AxisSort as1, AxisSort as2) {
				if(as1.y < as2.y){
					return -1;
				}
				else if(as1.y == as2.y){
					if(as1.x < as2.x){
						return -1;
					}
					else{
						return 1;
					}
					
				}
				else{
					return 1;
				}
				
			}
			
		};
	}

}
