import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj1078 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(flipping(br.readLine()));
    }

    private static String flipping(String input){
        char[] nums = input.toCharArray();
        long val = Long.parseLong(input);

        for(int i = nums.length - 1; i >= nums.length / 2; i--){
            int tmp = (nums[i] - '0');

            int[] pair = {tmp * 2 % 10, tmp};                // 0, 10 - tmp

        }

        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
