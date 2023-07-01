import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        // 배열을 리스트로
        List<Integer> arrList = Arrays.stream(scoville)
            .boxed()
            .collect(Collectors.toList());
        
        // 최소힙 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(arrList);
        
        while (pq.peek() < K){
            int fst = pq.poll();
            int sec = pq.poll();
            int newS = fst + sec*2;
            answer ++;
            pq.add(newS);
            // 2개 미만이라면 중지
            if (pq.size() < 2){
                break;
            }
        }
        
        // 만약 가장 작은 수가 k이상이라면 answer를 리턴, 그렇지 않으면 -1을 리턴
        return pq.peek() >= K ? answer : -1;
    }
}