package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj16506 {
	private static final char NEW_LINE ='\n';
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashMap<String, String> strToOpcode = new HashMap<>();
		strToOpcode = init();
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String opCode = st.nextToken();
			int rD = Integer.parseInt(st.nextToken());
			int rA = Integer.parseInt(st.nextToken());
			int rB = Integer.parseInt(st.nextToken());
			
			cmdToMachineCode(opCode, rD, rA, rB, strToOpcode);
		}
		
		System.out.println(sb);
	}
	
	private static HashMap<String, String> init(){
		HashMap<String, String> hm = new HashMap<>();
		
		hm.put("ADD", "000000");
		hm.put("ADDC", "000010");
		hm.put("SUB", "000100");
		hm.put("SUBC", "000110");
		hm.put("MOV", "001000");
		hm.put("MOVC", "001010");
		hm.put("AND", "001100");
		hm.put("ANDC", "001110");
		hm.put("OR", "010000");
		hm.put("ORC", "010010");
		hm.put("NOT", "010100");
		hm.put("MULT", "011000");
		hm.put("MULTC", "011010");
		hm.put("LSFTL", "011100");
		hm.put("LSFTLC", "011110");
		hm.put("LSFTR", "100000");
		hm.put("LSFTRC", "100010");
		hm.put("ASFTR", "100100");
		hm.put("ASFTRC", "100110");
		hm.put("RL", "101000");
		hm.put("RLC", "101010");
		hm.put("RR", "101100");
		hm.put("RRC", "101110");
		
		return hm;
	}
	
	private static void cmdToMachineCode(String cmd, int d, int a, int b, HashMap<String, String> hm) {
		sb.append(hm.get(cmd)).append(integerToBinary(d, 1, false)).append(integerToBinary(a, 1, false))
			.append(cmd.contains("C") ? integerToBinary(b, 0, false) : integerToBinary(b, 1, true)).append(NEW_LINE);
	}
	
	private static StringBuilder integerToBinary(int value, int start, boolean hasC) {
		StringBuilder bin = new StringBuilder();
		char[] binary = new char[4];
		
		for(int i = 3; i >= 0; i--) {
			binary[i] = (char) ((value % 2) + '0');
			value /= 2;
		}
		
		for(int i = start; i < 4; i++) {
			bin.append(binary[i]);
		}
		
		if(hasC) bin.append(0);
		return bin;
	}
}
