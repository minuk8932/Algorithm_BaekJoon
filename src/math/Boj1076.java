package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1076 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] ohm = new long[10];
		long[] color = new long[3];
		
		for(int i = 0; i < ohm.length; i++){
			ohm[i] = (long) Math.pow(10, i);
		}
		
		for(int i = 0; i < color.length; i++){
			switch(br.readLine()){
			case "black" :
				if(i < 2){
					color[i] = 0;
				}
				else{
					color[i] = ohm[0];
				}
				break;
				
			case "brown" :
				if(i < 2){
					color[i] = 1;
				}
				else{
					color[i] = ohm[1];
				}
				break;
				
			case "red" :
				if(i < 2){
					color[i] = 2;
				}
				else{
					color[i] = ohm[2];
				}
				break;
				
			case "orange" :
				if(i < 2){
					color[i] = 3;
				}
				else{
					color[i] = ohm[3];
				}
				break;
				
			case "yellow" :
				if(i < 2){
					color[i] = 4;
				}
				else{
					color[i] = ohm[4];
				}
				break;
				
			case "green" :
				if(i < 2){
					color[i] = 5;
				}
				else{
					color[i] = ohm[5];
				}
				break;
				
			case "blue" :
				if(i < 2){
					color[i] = 6;
				}
				else{
					color[i] = ohm[6];
				}
				break;
				
			case "violet" :
				if(i < 2){
					color[i] = 7;
				}
				else{
					color[i] = ohm[7];
				}
				break;
				
			case "grey" :
				if(i < 2){
					color[i] = 8;
				}
				else{
					color[i] = ohm[8];
				}
				break;
				
			case "white" :
				if(i < 2){
					color[i] = 9;
				}
				else{
					color[i] = ohm[9];
				}
				break;
			}
		}
		System.out.println((Integer.parseInt(String.valueOf(color[0])+String.valueOf(color[1]))) * color[2]);
	}

}
