import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj13334 {	
	private static class Rail implements Comparable<Rail>{
		int house;
		int inter;
		
		public Rail(int house, int inter) {
			this.house = house;
			this.inter = inter;
		}

		@Override
		public int compareTo(Rail r) {
			if(this.house < r.house) {
				return -1;
			}
			else if(this.house > r.house) {
				return 1;
			}
			else {
				if(this.inter < r.inter) return -1;
				else if(this.inter > r.inter) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Rail[] rail = new Rail[n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int o = Integer.parseInt(st.nextToken());
			
			rail[i] = new Rail(Math.min(h, o), Math.abs(h - o));
		}
		
		int d = Integer.parseInt(br.readLine());
		System.out.println(getResident(n, rail, d));
	}
	
	private static int getResident(int n, Rail[] arr, long interval) {
		Arrays.sort(arr);
		
		long prev = arr[0].house;
		int leng = 0, max = 0, idx = 0;
		
		for(int i = 0; i < n; i++) {
			long total = arr[i].inter + arr[i].house;
			
			if(total <= interval + prev) {
				leng++;
			}
			else {
				if(total <= interval + arr[i].house) leng++;
				if(idx != n - 1 && arr[i].house != arr[idx].house) prev = arr[++idx].house;
				if(arr[idx].inter <= interval) leng--;
			}
			
			max = Math.max(leng, max);
		}
		
		return max;
	}
}
