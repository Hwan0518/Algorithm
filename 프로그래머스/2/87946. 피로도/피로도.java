import java.util.*;


/*
- 최소 필요 피로도, 소모 피로도
- 탐험할 수 있는 최대 던전 수
- dungeons 배열에서 뽑는 순서를 달리해서 완전탐색을 해보자
    -> n<=8이므로 8!이라서 가능
*/


class Solution {
    
    static int n;
    static boolean[] visited;
    static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        
        // set-up
        n = dungeons.length;
        visited = new boolean[n];
        
        // back-tracking
        dfs(0, 0, k, dungeons);
        
        // result
        return answer;    
    }
    
    
    void dfs(int cur, int size, int k, int[][] dungeons) {
        
        if (size == n) {
            answer = Math.max(answer, cur);
            return;
        }
        
        for (int i=0; i<n; i++) {
            
            if (visited[i]) continue;
            
            visited[i] = true;
            
            if (k >= dungeons[i][0]) dfs(cur+1, size+1, k-dungeons[i][1], dungeons);
            else dfs(cur, size+1, k, dungeons);
            
            visited[i] = false;
            
        }
    }
    
}