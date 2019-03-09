import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1494 {
	private static final String NEW_LINE = "\n";
	private static long loop = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long first = Long.parseLong(st.nextToken());
		long second = Long.parseLong(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		long limit = Process(first, second);
		
		while(N-- > 0) {
			sb.append(Long.parseLong(br.readLine())).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static long Process(long f, long s) {		
		ArrayList<Long> list = new ArrayList<>();
		list.add(f);
		list.add(s);
		
		long value = Math.abs(f - s);
		long count = 2;
		
		while(!list.contains(value)) {
			list.add(value);

			f = s;
			s = value;
			value = Math.abs(f - s);
			count++;
		}
		
		int size = list.size();
		int start = 0;
		
		for(int i = 0; i < size; i++) {
			if(list.get(i) == value) {
				start = i;
				break;
			}
		}
		
		loop = count - value + 1;
		
		return start;
	}
}
