import java.util.*;
import java.io.*;

/**
 * 1. 정렬
 * 2. [1,2,3,...]과 정렬한 배열을 비교하여 다르다면 그 수가 정답
 */

public class Main {
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // input
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        // 정렬
        Arrays.sort(arr);
        // 비교
        int ticket = 1;
        int answer = n+1;
        for (int i=0; i<n; i++) {
            if (arr[i] != ticket) {
                answer = ticket;
                break;
            }
            ticket ++;
        }
        System.out.print(answer);
    }
    
}