/*
Conditions
- 가로길이 >= 세로길이
- 8 <= b <= 5천
- 가로, 세로 크기를 배열에 담아 return하여라

Approach
- 가로길이 >= 세로길이가 가능한 모든 경우의 수를 구해보자
    -> 가로/세로길이 검증 : (b-가로*2)%2=0 이면 가능함 (세로길이가 정수로 떨어짐)
    -> 노란색 검증 : (가로-2) * (세로-2) == 노란색갯수 라면 가능함
*/

import java.util.*;

class Solution {
    
    public int[] solution(int brown, int yellow) {
        
        int[] answer = new int[2];
        
        for (int x=3; x<=brown/2; x++) {
            int y = (brown-2*x)/2 +2;

            if (!checkLength(x, y, brown)) continue;
            
            if (checkYellow(x, y, yellow)) {
                answer[0] = x;
                answer[1] = y;
                break;
            }
        }
        
        return answer;
        
    }
    
    
    
    boolean checkLength(int x, int y, int brown) {
        boolean result = true;
        
        if ((brown-x*2)%2 != 0) result = false;
        if (x < y) result = false;
        
        return result;
    }
    
    
    
    boolean checkYellow(int x, int y, int yellow) {
        
        return (x-2) * (y-2) == yellow;
        
    }
    
}