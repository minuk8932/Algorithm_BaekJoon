package sort;

/**
 * 
 * 	@author minchoba
 *	퀵 정렬 알고리즘을 통한 배열 정렬
 *	
 *	출처 - 엔지니어 대한민국
 *	@see https://www.youtube.com/watch?v=7BDzle2n47c
 *
 */
public class QuickSortEx {
	private static void quickSort(int[] arr){		// 정렬할 배열을 받아와서
		quickSort(arr, 0, arr.length - 1);		// 재귀함수 호출
	}
	
	private static void quickSort(int[] arr, int start, int end){
		int part2 = partition(arr, start, end);		// 시작과 끝 영역 내에서 파티션을 나누고, 오른쪽 파티션 첫번째 값을 받아옴
		
		if(start < part2 - 1){							// 시작점 바로 다음에서 시작하면 왼쪽 파티션 데이터가 하나뿐이기때문에 정렬할 필요가 없어지므로
			quickSort(arr, start, part2 - 1);		// 오른쪽 파티션이 시작 점에서 한개 이상 차이가 날때만, 함수 재귀 호출해 해당 파티션에서 다시 정렬 시도
		}														// 시작 끝 점은 돌면서 계속 조정되어야함
		
		if(part2 < end){
			quickSort(arr, part2, end);
		}
	}
	
	private static int partition(int[] arr, int start, int end){		// 해당 배열과 파티션을 나눌 시작과 끝방의 인덱스를 받아옴
		int pivot = arr[(start + end) / 2];			// 배열방 중간에 있는 값을 피봇 설정 (임의의 값)

		while(start <= end){								// start, end가 서로 교차하기 전까지만 반복
			while(arr[start] < pivot)		start++;	// start 인덱스에 해당하는 값이 피봇 인덱스의 값보다 작으면 오른쪽 인덱스로 이동
			while(arr[end] > pivot)	end--;			// end 인덱스에 해당하는 값이 피봇 인덱스의 값보다 크다면 왼쪽 인덱스로 이동
																	
																	// 위의 반복문에서, 조건에 맞지 않는다면 start, end의 인덱스 위치는 정지되고, 아래의 조건문을 실행함
			if(start <= end){								// 정지한 인덱스에서 해당 값들의 위치를 바꿔
				swap(arr, start, end);
				start++;
				end--;											// 현재 이러한 동작을 반복함으로써, 피봇 중심 왼쪽엔 피봇보다 작은, 오른쪽은 피봇보다 큰 값들이 위치하게 된다.
			}
		}
		
		return start;											// 새로 나눌 오른쪽 파티션의 첫번째 인덱스(start) 반환
	}
	
	private static void swap(int[] arr, int start, int end){	// start와 end의 값을 바꿔주는 메소드
		int tmp = arr[start];
		arr[start] = arr[end];
		arr[end] = tmp;
	}
	
	private static String printArray(int[] arr){		// 배열 출력 메소드
		StringBuilder sb = new StringBuilder();
		
		sb.append("{ ");
		
		for(int data : arr){
			sb.append(data).append(", ");
		}
		sb.append("}\n");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		int[] arr = {3, 9, 4, 7, 5, 0, 1, 6, 8, 2};
		
		System.out.println(printArray(arr));			// 정렬 전 출력
		quickSort(arr);											// 정렬
		System.out.println(printArray(arr));			// 정렬 후 출력
	}
}
