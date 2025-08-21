/*
conditions
- 최솟값 최댓값 찾아서 반환
*/

import java.util.*;
import java.io.*;

class Solution {
    
    public String solution(String s) {
        
        int minV = Integer.MAX_VALUE;
        int maxV = Integer.MIN_VALUE;
        for (String ss: s.split(" ")) {
            int cur = Integer.parseInt(ss);
            minV = Math.min(minV, cur);
            maxV = Math.max(maxV, cur);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(minV).append(" ").append(maxV);
        return sb.toString();
    
    }
    
}