package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7453번: 합이 0인 네 정수
 *
 *	@see https://www.acmicpc.net/problem/7453/
 *
 */
public class Boj7453 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(binarySearch(n, A, B ,C ,D));
	}
	
	private static long binarySearch(int N, int[] a, int[] b, int[] c, int[] d) {
		long res = 0;
		int[] one = twoSum(N, a, b, -1);		// 2개씩 합친 배열
		int[] two = twoSum(N, c, d, 1);
		
		Arrays.sort(one);
		Arrays.sort(two);

		for(int i = 0; i < one.length; i++) {
			int start = 0, end = one.length - 1, mid = 0;
			int count = 0;
			
			while(start <= end) {
				mid = (start + end) / 2;
				
				if(two[mid] < one[i]) {
					start = mid + 1;
				}
				else if(two[mid] > one[i]) {
					end =  mid - 1;
				}
				else {
					count++;
					break;
				}
			}
			
			if(count == 0) continue;
			
			int lowerBound = getBound(two, 0, mid, two[mid], false);					// mid와 같은 값을 갖는 최저 인덱스
			int upperBound = getBound(two, mid + 1, two.length - 1, two[mid], true);	// mid와 같은 값을 갖는 최고 인덱스
			
			res += (upperBound - lowerBound + 1);
		}
		
		return res;
	}
	
	private static int getBound(int[] arr, int start, int end, int target, boolean select) {
		int mid = 0;
		
		while(start <= end) {
			mid = (start + end) / 2;
			
			if(target > arr[mid]) {
				start = mid + 1;
			}
			else if(target < arr[mid]) {
				end = mid - 1;
			}
			else {
				while(select && arr[mid] != arr[end]) {		// 이분 탐색 후 값을 늘리거나 줄이며, 해당하는 인덱스를 찾는다.
					end--;
				}
				
				while(!select && arr[mid] != arr[start]) {
					start++;
				}
				
				break;
			}
		}
		
		return select ? end: start;
	}
	
	private static int[] twoSum(int N, int[] a, int[] b, int rev) {
		int[] arr = new int[N * N];
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b.length; j++) {
				arr[i * N + j] = (a[i] + b[j]) * rev;
			}
		}
		
		return arr;
	}
}
