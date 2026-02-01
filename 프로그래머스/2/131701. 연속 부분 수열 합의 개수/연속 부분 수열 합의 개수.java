/*
슬라이딩윈도우
-> window size = 1~n까지로 탐색
*/

import java.util.*;

class Solution {

    public int solution(int[] elements) {
    
        // search
        int answer = slidingwindow(elements);
        
        // result
        return answer;
    }
    
    
    int slidingwindow(int[] elements) {
        
        int n = elements.length;
        Set<Integer> result = new HashSet<>();
        
        // 원형 배열 생성
        int[] arr = new int[2*n];
        for (int i=0; i<2*n; i++) arr[i] = elements[i%n];
        
        // 탐색
        for (int size=1; size<=n; size++) {
        
            int stt = 0;
            int end = stt+size;
            int sum = 0;
            
            for (int i=stt; i<end; i++) {
                sum += arr[i];
            }
            
            // 원형이므로 stt가 끝까지 가야함
            while (stt < n) {
                
                // 결과 저장
                result.add(sum);
                
                // sum, stt, end 갱신
                sum -= arr[stt];
                sum += arr[end];
                
                stt ++;
                end ++;
            }
        }
        
        // 결과
        return result.size();
    }
    
}