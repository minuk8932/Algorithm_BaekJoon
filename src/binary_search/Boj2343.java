package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2343번: 기타 레슨
 *
 *	@see https://www.acmicpc.net/problem/2343/
 *
 */
public class Boj2343 {
	private static final int INF = 1_000_000_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] media = new int[N];
		int max = 0;
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			media[i] = Integer.parseInt(st.nextToken());
			if(media[i] > max) max = media[i];
		}
		
		System.out.println(binarySearch(N, M, media, max));
	}
	
	private static int binarySearch(int n, int target, int[] arr, int limit) {
		int start = 0, end = INF;
		int result = INF;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			int section = getSize(arr, mid);		// 블루레이 크기가 설정 되었을 때 몇개의 구간이 나오는가
			
			if(section <= target) {
				end = mid - 1;
				result = Math.min(result, mid);		// 그때 최소 블루레이 크기
			}
			else if(section > target) {
				start = mid + 1;
			}
		}
		
		return result < limit ? limit : result;		// 만약 최소 크기가 가장 큰 레슨 보다 작은 경우
	}
	
	private static int getSize(int[] arr, int capacity) {		
		long sum = 0;
		int count = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(sum + arr[i] > capacity) {
				count++;
				sum = 0;
			}
			
			sum += arr[i];
		}
		
		if(sum <= capacity) count++;		// 최종 값이 설정된 블루레이 크기보다 작거나 같은 경우: 1개만 추가
		else count += 2;			// 큰 경우 현재 sum과 나머지 1개를 각각 따로 저장
		
		return count;
	}
}
