import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj16198 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> arr = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) arr.add(Integer.parseInt(st.nextToken()));
		
		int res = 0;
		
		while(arr.size() != 2) {
			PriorityQueue<MaximumInfo> pq = new PriorityQueue<>();
			int max = 0;
			int size = arr.size();
			
			for(int i = 0; i < size - 2; i++){
				int tmp = arr.get(i) * arr.get(i + 2);
				
				if(tmp > max) {
					max = tmp;
				}
			}
			
			for(int i = 0; i < size - 2; i++){
				int tmp = arr.get(i) * arr.get(i + 2);
				
				if(tmp == max) {
					pq.offer(new MaximumInfo(tmp, i + 1));
				}
			}
			
			MaximumInfo first = pq.poll();
			
			res += first.value;
			arr.remove(first.index);
		}
		
		System.out.println(res);
	}
	
	private static class MaximumInfo implements Comparable<MaximumInfo>{
		int value;
		int index;
		
		public MaximumInfo(int value, int index) {
			this.value = value;
			this.index = index;
		}

		@Override
		public int compareTo(MaximumInfo mi) {
			return mi.value > this.value ? -1 : 1;
		}
	}
}
