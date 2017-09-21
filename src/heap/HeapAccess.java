package heap;

/*
 *  	what is heap
 *  			-> priority Queue : 중요도를 따져서 큐를 운용
 *  				특수한 형태의 Binary tree
 *  			
 *  
 *  	1. Max - heap : 가장 큰 값 순서로 root에 존재 할 때
 *  			push : 왼쪽부터 full b-tree 형식으로 채운다. 부모와 자신의 값을 비교하여 우선순위가 큰 것을 위로 올림, 다른 노드는 고려하지 않는다. 시간 복잡도 log2n
 *  			poll : 가장 큰 값부터, 즉  root 값을 뽑아온다. 이후 자식 노드 중 가장 아래에 있는 값을 root로 끌어온 후 왼쪽부터 자식 부모끼리 비교하여 정렬
 *  						만약 root 값보다 좌 우의 자식 노드값이 둘다 크다면 둘 중에 더 큰 값이랑 위치를 바꿈
 *  			
 *  
 *  	2. min - heap : 가장 작은 값 순서로 root에 존재 할 때,	logN
 *  
 */

public class HeapAccess {
	public static void main(String[] args) throws Exception{
		
	}
	private static class MaxHeap{
		private static final int MAX_HEAP_SIZE = 1000;
		
		// heap array
		private int[] heap;
		
		// heap size
		private int size;
		
		public MaxHeap(){
			heap = new int[MAX_HEAP_SIZE];
			
		}
		
		public void swapHeap(int idx1, int idx2){
			
		}
		
		public void offer(int data){
			int offerIdx = ++size;
			heap[offerIdx] = data;
			
			while(offerIdx > 1){		// rootIdx가 될 때 까지 바꿔라
				int rootIdx = offerIdx / 2;
				
				if(heap[rootIdx] < heap[offerIdx]){	// 부모와 나의 값을 비교해 내가 더 크다면
					swapHeap(rootIdx, offerIdx);	// 바꿔라
					
					int tmpIdx = offerIdx;
					offerIdx = rootIdx;
					rootIdx = tmpIdx;
				}
				else{
					break;
				}
			}
		}
		
		public int poll(){
			int rootIdx = 1;
			int pollData = heap[rootIdx];
			
			heap[rootIdx] = heap[size];
			heap[size--] = Integer.MIN_VALUE;
			
			while(true){
				int leftIdx = rootIdx * 2;
				int rightIdx = rootIdx * 2 + 1;
				
				if(heap[rootIdx] >= heap[leftIdx] && heap[rootIdx] >= heap[rightIdx]){
					break;
				}
				
				else if(heap[leftIdx] > heap[rightIdx]){
					swapHeap(rootIdx, leftIdx);
					rootIdx = leftIdx;
				}
			}
			return pollData;
		}
		
	}
}
