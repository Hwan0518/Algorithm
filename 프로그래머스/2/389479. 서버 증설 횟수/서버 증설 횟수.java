import java.util.*;

/*
conditions
- 같은 시간대, 사람이 m명 늘어날 때마다 서버 1대 추가해야함, m명 미만이라면 필요x
- 즉, n*m <= people <= (n+1)*m 인 경우 -> n대의 서버가 필요
- 한번 증설한 서버는 k 시간 동안만 운영 (시작시간 ~ 시작시간+k)
- 모든 게임 이용자가 게임을 하기 위해서, 서버를 최소 몇 번 증설해야 하는가?
- 이용자수(players = 24), 1<=m<=1000, 1<=k<=24

approach
- 완탐하면 될듯
    - 시간당 서버 개수를 나타내는 int[] server와, 증설횟수 cnt를 다루자
        - server의 default value = 1로 설정
    - if (players[i] >= server[i]*m) -> 증설 해야함
        server[i] ~ server[lastTime]까지 (diff/m)+1 개만큼 증설
        answer += (diff/m)+1
        - lastTime = Math.min(23, i+k);
        - diff = (players[i] - server[i]*m)
*/


class Solution {
    
    public int solution(int[] players, int m, int k) {
        
        // setup
        int answer = 0;
        int[] server = new int[24];
        for (int i=0; i<24; i++) server[i] ++;
        
        // search
        for (int i=0; i<24; i++) {
            if (players[i] >= server[i]*m) {
                int plus = (players[i] - server[i]*m)/m +1;
                for (int j=i; j<Math.min(24, i+k); j++) {
                    server[j] += plus;
                }
                answer += plus;
            }
        }
        
        // result
        return answer;
    }
    
    
}