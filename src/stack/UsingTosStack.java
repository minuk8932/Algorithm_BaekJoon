package stack;

public class UsingTosStack {
	public static int[] stack = new int[100_000];
	public static int tos = 0;
	
	public static void main(String[] args)throws Exception{
		push(3);
		push(2);
		push(1);
		
		for(int i = 0; i < tos; i++){
			System.out.println(stack[i] + " ");
		}
		
		System.out.println("popped item : " + pop());
		
		for(int i = 0; i < tos; i++){
			System.out.println(stack[i] + " ");
		}
		
	}
	
	public static void push(int value){
		stack[tos++] = value;
	}
	
	public static int pop(){
		tos--;
		int res = stack[tos];
		stack[tos] = 0; 	//비어있을 스택에 초기화
		return res;
	}
}
