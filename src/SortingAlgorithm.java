import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SortingAlgorithm {
	private static final String SPACE = " ";
	private static int[] arr = null;
	
	public static void main(String[] args) throws Exception{
		System.out.print("정렬 할 데이터의 갯수(단, 10000개 이하)를 입력해주세요: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println();
		
		arr = new int[N];
		int[] tmpArr = new int[N];
		
		System.out.print(N + " 개의 데이터를 공백으로 구분지어 입력해 주세요: ");
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i= 0; i < N; i++) arr[i] = tmpArr[i] = Integer.parseInt(st.nextToken());
		
		System.out.println();
		System.out.println(N + "개의 데이터가 입력이 완료되었습니다.");
		StringBuilder sb = new StringBuilder();
		for(int i= 0; i < N; i++) sb.append(arr[i]).append(SPACE);
		
		System.out.println(sb);
		System.out.println();
		
		while(true) {
			init(tmpArr);
			
			System.out.println("1. 버블정렬 \n2. 선택정렬 \n3. 삽입정렬 \n4. 퀵정렬 \n5. 합병정렬 \n6. 힙정렬 \n7. 종료");
			System.out.print("실행하실 알고리즘을 선택해 주세요: ");
			int testNumber = Integer.parseInt(br.readLine());
			
			if(testNumber == 7) break;
			
			System.out.println();
			
			switch (testNumber) {
			case 1:
				System.out.println("======== 버블 정렬 ========");
				System.out.println();
				
				bubbleSort();
				System.out.println(print(sb));
				
				System.out.println();
				break;
				
			case 2:
				System.out.println("======== 선택 정렬 ========");
				System.out.println();
				
				selectionSort();
				System.out.println(print(sb));
				
				System.out.println();
				break;
				
			case 3:
				System.out.println("======== 삽입 정렬 ========");
				System.out.println();
				
				insertionSort();
				System.out.println(print(sb));
				
				System.out.println();
				break;
				
			case 4:
				System.out.println("======== 퀵   정렬 ========");
				System.out.println();
				
				quickSort(0, N - 1);
				System.out.println(print(sb));
				
				System.out.println();
				break;
				
			case 5:
				System.out.println("======== 합병 정렬 ========");
				System.out.println();
				
				mergeSort(0, N - 1);
				System.out.println(print(sb));
				
				System.out.println();
				break;
			
			case 6:
				System.out.println("======== 힙   정렬 ========");
				System.out.println();
				
				heapSort(N);
				for(int i = arr.length - 1; i > 0; i--) {
					swap(0, i);
					heapSort(i);
				}
				System.out.println(print(sb));
				
				System.out.println();
				break;
			}
		}
		
		System.out.println("프로세스 종료");
	}
	
	private static void bubbleSort() {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 1; j < arr.length - i; j++) {
				if(arr[j - 1] <= arr[j]) continue;
				swap(j, j - 1);
			}
		}
	}
	
	private static void selectionSort() {
		for(int i = 0; i < arr.length; i++) {
			int min = arr[i], minIdx = -1;
			
			for(int j = i + 1; j < arr.length; j++) {
				if(min <= arr[j]) continue;
				
				min = arr[j];
				minIdx = j;
			}
			
			if(minIdx == -1) continue;
			
			int tmp = arr[i];
			arr[i] = min;
			arr[minIdx] = tmp;
		}
	}
	
	private static void insertionSort() {
		for(int i = 0; i < arr.length; i++) {
			for(int j = i; j > 0; j--) {
				if(arr[j] > arr[j - 1]) break;
				swap(j, j - 1);
			}
		}
	}
	
	private static void quickSort(int left, int right) {
		int low = left, high = right;
		int pivot = arr[(left + right) / 2];
		
		while(low <= high) {
			while(arr[low] < pivot) low++;
			while(arr[high] > pivot) high--;
			
			if(low <= high) swap(low++, high--);
		}
		
		if(left < high) quickSort(left, high);
		if(low < right) quickSort(low, right);
	}
	
	private static void mergeSort(int left, int right) {
		if(left < right) {
			int mid = (left + right) / 2;
			
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			merge(left, right, mid);
		}
	}
	
	private static void merge(int left, int right, int mid) {
		int[] arrLeft = Arrays.copyOfRange(arr, left, mid + 1);
		int[] arrRight = Arrays.copyOfRange(arr, mid + 1, right + 1);
		
		int i = 0, j = 0, k = left;
		
		while(i < arrLeft.length && j < arrRight.length) {
			if(arrLeft[i] <= arrRight[j]) {
				arr[k] = arrLeft[i++];
			}
			else {
				arr[k] = arrRight[j++];
			}
			
			k++;
		}
		
		while(i < arrLeft.length) arr[k++] = arrLeft[i++];
		while(j < arrRight.length) arr[k++] = arrRight[j++];
	}
	
	private static void heapSort(int num) {
		for(int i = 1; i < num; i++) {
			int child = i;
			
			while(child > 0) {
				int parent = (child - 1) / 2;
				
				if(arr[child] > arr[parent]) {
					swap(child, parent);
				}
				
				child = parent;
			}
		}
	}
	
	private static void swap(int arr1, int arr2) {
		int tmp = arr[arr1];
		arr[arr1] = arr[arr2];
		arr[arr2] = tmp;
	}
	
	private static String print(StringBuilder sb) {
		sb = new StringBuilder();
		for(int i = 0; i < arr.length; i++) sb.append(arr[i]).append(SPACE);
		
		return sb.toString();
	}
	
	private static void init(int[] tmp) {
		for(int i = 0; i < arr.length; i++) arr[i] = tmp[i];
	}
}
