/*
- 완탐
    -> 길이가 1부터 n까지 조건이 있으므로 백트래킹ㄱㄱ
    -> n최대가 7이니까.. 최대 7^7이므로 가능 (10**8까진 가능)
    -> 선택 하거나 안하거나로 가보자
    -> 소수 판별하는 메서드도 필요함
        -> 2~m-1(자기자신) 까지 %로 나누면서, 나머지가 0이 아니라면 true, 맞다면 false
*/

import java.util.*;

class Solution {
    
    static int answer = 0;
    static int n;
    static boolean[] visited;
    static int[] nums;
    static Set<Integer> visited2 = new HashSet<>();
    
    public int solution(String numbers) {
        
        // set-up
        n = numbers.length();
        visited = new boolean[n];
        nums = Arrays.stream(numbers.split(""))
            .mapToInt(Integer::parseInt)
            .toArray();
        
        // dfs
        StringBuilder sb = new StringBuilder();
        dfs(0, sb);
        
        // result
        return answer;
        
    }
    
    
    
    static void dfs(int size, StringBuilder sb) {
        
        if (size == n) {
            
            if (sb.length() > 0) {
                int curNum = Integer.parseInt(sb.toString());
                
                if (curNum == 1 || visited2.contains(curNum)) return;
                visited2.add(curNum);
                if (check(curNum)) answer++;
            }
            
            return;
        }
        
        for (int i=0; i<n; i++) {
            if (visited[i]) continue;
            if (sb.length()==0 && nums[i]==0) continue;
            visited[i] = true;            
            dfs(size+1, sb); // 현재 값을 선택 안함
            dfs(size+1, sb.append(nums[i])); // 현재 값을 선택함
            // reset
            visited[i] = false;
            sb.setLength(sb.length()-1);
        }
        
    }
    
    
    
    
    static boolean check(int num) {
        
        for (int i=2; i<num; i++) {
            if (num % i == 0) return false;
        }
        
        return true;
    }
    
}