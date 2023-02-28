'''
Condition
    - TL : 1s (2000만)
      ML : 256 (64*100만)
    - 1<=N,K<=10만
    - -1억<=샘터 위치<=1억
    - 불행도 : 집과, 집에서 가장 가까운 샘터까지의 거리
    
Question
    - 샘터의 위치가 고정되어 있을 때 ,모든 집에 대한 불행도 합의 최솟값을 출력하여라
    
    
Access
    try_1)
        - 예상 분류 : dp
          문제 분류 : bfs
          
        - 만약 dp라면?
            >>> dp[k]= k번째 집이 있을 때 불행도 합, 모든 position에 넣어보고 최솟값을 가져온다
                : position 범위가 너무 크다..!!
        
        - bfs
            >>> 각 샘터를 bfs 시작지점으로 두고, 하나씩 채워나가면 된다!
                1. bfs 시작점을 샘터 시작점으로 잡는다
                2. 하나의 샘터 양쪽으로 집을 짓고, 그 다음 샘터로 넘어가서 양쪽으로 집을 짓는다
                3. 샘터의 개수만큼 2번을 반복한다
                4. 집의 개수만큼 2~3번을 반복한다

            문제점
                1. 각 집에서 가장 가까운 샘터를 어떻게 알아낼 것인가?
                    - 각 집마다 모든 샘터를 비교 >> n*k = 최대 10만*10만 = TLE 발생
                    - 각 집에서 bfs를 시전해서 가까운 샘터를 찾아야한다 !!! >> 집위치가 샘터와 멀어지면 무조건 TLE
                    

'''
from collections import deque
from sys import stdin
input = stdin.readline



#define function
def updateUnhappiness(position): #불행도 갱신 
    # position은 현재 건설한 집의 위치이다
    rrrrright = springSite[-1]
    llllleft = springSite[0]
    
    # 건설위치가 가장 오른쪽 샘터보다 오른쪽에 있다면
    if position > rrrrright:
        return abs(position - rrrrright)
    
    # 건설위치가 가장 왼쪽 샘터보다 왼쪽에 있다면
    if position < llllleft:
        return abs(position - llllleft)
    
    # 그 외의 경우
    dq = deque
    
    return
    
    
        

def doVisit(position,dist):
    global cnt
    visited.add(position)       #방문처리
    possible.append((position,dist+1))   #다음건설위치 추가
    cnt +=1                     #건설된 집의 수 증가



def solution():
    global cnt, visited, possible
    
    cnt = 0                   # 건설된 집의 수
    unhappiness = 0           # 불행도
    visited = set(springSite) # 건설된 집이나 샘의 위치를 방문처리
    
    # possible은 집 위치 후보로, (위치,거리)를 포함한다
    possible = deque()        
    for spring in springSite:
        possible.append((spring,1))
    
    # 건설된 집의 수가 k개가 될때까지 반복
    while True:
        posit,dist = possible.popleft()
        positRight = posit +1
        positLeft = posit -1
        
        # posit 오른편에 집을 지음 : 방문처리 + 다음 건설위치에 추가 + 건설된 집의 수 증가 + 불행도 갱신
        if positRight not in visited:
            doVisit(positRight,dist)
            unhappiness += dist # 불행도 갱신
                
        # 건설된 집의 개수가 k개라면 종료
        if cnt == k:
            return unhappiness
        
        # posit 왼쪽편에 집을 지음
        if positLeft not in visited:
            doVisit(positLeft,dist)    
            unhappiness += dist # 불행도 갱신
                
        # 건설된 집의 개수가 k개라면 종료
        if cnt == k:
            return unhappiness            
    



#main
n,k = map(int,input().split())
springSite = list(map(int,input().split())) # 샘터 위치
print(solution())
