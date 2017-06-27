package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StackEx {
	private static int[] nums = new int[0];
	private static String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int commandCnt = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < commandCnt; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			
			switch(st.nextToken()){
			case "push":
				push(Integer.parseInt(st.nextToken()));
				break;
				
			case "pop":
				sb.append(pop()).append(NEW_LINE);
				break;
				
			case "size":
				sb.append(size()).append(NEW_LINE);
				break;
				
			case "empty":
				sb.append(empty()).append(NEW_LINE);
				break;
				
			case "top":
				sb.append(top()).append(NEW_LINE);
				break;
				
			}
			
		}
		
		System.out.println(sb.toString());
		
	}
	
	private static void push(int num){
		int[] tmp = new int[nums.length + 1];
		
		for(int i = 0; i < nums.length; i++){
			tmp[i] = nums[i];
		}
		
		tmp[nums.length] = num;
		nums = tmp;
	}
	
	private static int pop(){
		if(size() == 0){
			return -1;
		}
		
		int[] tmp = new int[nums.length - 1];
		
		for(int i = 0; i < nums.length - 1; i++){
			tmp[i] = nums[i];
		}
		
		int top = top();
		nums = tmp;
		
		return top;
	}
	
	private static int size(){
		return nums.length;
	}
	
	private static int empty(){
		return size() == 0 ? 1 : 0;
	}
	
	private static int top(){
		if(size() == 0){
			return -1;
		}
		
		return nums[nums.length - 1];
	}
}
