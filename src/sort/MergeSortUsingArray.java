package sort;
/**
 * 
 * 	@author minchoba
 *	합병 정렬 알고리즘을 이용한 배열 정렬
 *
 * 	출처 - 엔지니어 대한민국
 * 	@see https://www.youtube.com/watch?v=QAyl79dCO_k&t=4s/
 *
 */
public class MergeSortUsingArray {
	private static final String NEXT = ", ";
	
	private static void mergeSort(int[] arr){		// 알고리즘 시작시
		int[] tmp = new int[arr.length];				// 원본배열과 같은 크기의 임시 배열을 생성
		
		mergeSort(arr, tmp, 0, arr.length - 1);	// 원본, 임시 배열과, 배열의 시작과 끝 인덱스를 받아 재귀호출
	}
	
	private static void mergeSort(int[] arr, int[] tmp, int start, int end){
		if(start < end){										// 시작 인덱스가 끝 인덱스보다 작은 동안만 재귀 실행
			int mid = (start + end) / 2;					// 중앙 인덱스를 구하고
			
			mergeSort(arr , tmp, start, mid);		// 배열의 앞부분 정렬 (start~mid)
			mergeSort(arr, tmp, mid + 1, end);		// 배열의 뒷부분 정렬	(mid~end)
			merge(arr, tmp, start, mid, end);		// 정렬한 부분 배열을 종합
		}
	}
	
	private static void merge(int[] arr, int[] tmp, int start, int mid, int end){
		for(int i = start; i <= end; i++){			// 시작부터 끝까지 임시 배열안에 원본 배열(나중에 결과를 담을)을 복사
			 tmp[i] = arr[i];
		}
		
		int part1 = start;						// 2부분으로 나누어서 앞쪽은 part1
		int part2 = mid + 1;						// 뒷쪽은 part2
		int index = start;						// 나눈 배열을 한 배열로 합치는 동작에서 다음 작은 값을 넣을 때 어떤 인덱스에 넣어야하는지 알아야 하므로 갱신 할 값 선언
		
		while(part1 <= mid && part2 <= end){	// 앞부분과 뒷부분 모두 끝날때 까지 반복
			if(tmp[part1] <= tmp[part2]){		//	임시 배열의 앞부분의 한 값이, 뒷부분의 한 값보다 작은경우
				arr[index] = tmp[part1];			// 앞부분의 값을 결과를 가져올 배열에 먼저 넣어주고
				part1++;								// 앞부분에서 데이터 하나를 가져왔으므로 인덱스를 뒤로 한칸 넘김
			}
			else{												// 반대의 경우
				arr[index] = tmp[part2];			// 임시배열의 뒷부분 값을 결과를 가져올 배열에 담아줌 (병합)
				part2++;								// 뒷배열의 인덱스값을 한칸 뒤로 넘겨줌
			}
			index++;					// 위의 두 조건중 하나가 실행될 수 밖에 없으므로, 어찌되었든 결과 배열의 인덱스는 반복 1회당 인덱스를 1씩 뒤로 밀어줘야함
		}
		
		for(int i = 0; i <= mid - part1; i++){	// 만약 앞부분 배열만 남은경우
			arr[index + i] = tmp[part1 + i];	// 남은 만큼의 길이에 해당하는 값을 임시배열에서 결과 배열로 옮겨줌
		}
		// 뒷쪽 배열이 남은 경우엔 결과배열에 어차피 뒷쪽 배열이 남아있기에 신경 쓸 필요 없다.
	}
	
	private static String printArray(int[] arr){		// 배열의 구조를 출력해 줄 메소드
		StringBuilder sb = new StringBuilder();
		
		for(int data : arr){
			sb.append(data).append(NEXT);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args){
		int[] arr = {3, 9, 4, 7, 5, 0, 1, 6, 8, 2};
		
		System.out.println(printArray(arr));			// 실행전
		mergeSort(arr);											// 합병정렬 알고리즘 실행
		System.out.println(printArray(arr));			// 실행 후
	}
}
