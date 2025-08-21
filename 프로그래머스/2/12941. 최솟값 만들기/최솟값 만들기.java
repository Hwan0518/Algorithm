import java.util.*;

class Solution {
    
    public int solution(int[] A, int[] B) {
        
        // set-up
        int answer = 0;
        
        // A를 오름차순, B를 내림차순해서 곱을 더한다
        int n = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        for (int i=0; i<n; i++) {
            answer += A[i] * B[n-i-1];
        }
        
        // result
        return answer;
        
    }

}