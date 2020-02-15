import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Default {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        date(nums);
    }

    private static void date(int[] arr){
        Arrays.sort(arr);
        print(search(arr));
    }

    private static void print(int[] arr){
        Arrays.sort(arr);
        System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
    }

    private static int[] search(int[] arr){
        int min = Integer.MAX_VALUE;
        int[] result = new int[3];

        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                int start = j + 1, end = arr.length - 1;

                while(start <= end){
                    int mid = (start + end) / 2;
                    int sum = arr[i] + arr[j] + arr[mid];

                    if(sum < 0){
                        start = mid + 1;

                        if(Math.abs(sum) < min){
                            min = Math.abs(sum);
                            result[0] = arr[i];
                            result[1] = arr[j];
                            result[2] = arr[mid];
                        }
                    }
                    else if(sum > 0){
                        end = mid - 1;

                        if(Math.abs(sum) < min){
                            min = Math.abs(sum);
                            result[0] = arr[i];
                            result[1] = arr[j];
                            result[2] = arr[mid];
                        }
                    }
                    else{
                        min = 0;
                        result[0] = arr[i];
                        result[1] = arr[j];
                        result[2] = arr[mid];
                        return result;
                    }
                }
            }
        }

        return result;
    }
}