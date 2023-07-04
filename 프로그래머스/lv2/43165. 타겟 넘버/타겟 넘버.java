import java.util.*;
class Solution {
    public static int answer = 0;
    public static HashSet<List<Integer>> visited = new HashSet<>();
    
    // main 함수
    public int solution(int[] numbers, int target) {
        ArrayList<Integer> calc = new ArrayList<>();
        dfs(0, 0, calc, numbers, target);
        return answer;
    }
    
    // dfs 함수
    public static void dfs(int cnt, int result, ArrayList<Integer> calc, int[] numbers, int target){
        if (cnt == numbers.length){
            if (result == target && !visited.contains(calc)){
                answer ++;
                visited.add(calc);
            }return;
        }
        
        int cur = numbers[cnt];
        ArrayList<Integer> curAdd = new ArrayList<>(calc);
        ArrayList<Integer> curSub = new ArrayList<>(calc);
        curAdd.add(cur);
        curSub.add(-cur);

        dfs(cnt+1, result+cur, curAdd, numbers, target);
        dfs(cnt+1, result-cur, curSub, numbers, target);
        
    }
    
}