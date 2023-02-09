'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*10만)
    - 1<=N<=50, 1<=R<=500, 1<=D<=100, 0<=X,Y<=1000

Question
    - 적이 받을 수 있는 에너지의 최댓값을 구하여라

Access
    try_1)
        - 예상분류 : 수학
        - 적을 공격할 수 있는 타워와 그럴 수 없는 타워를 분류해서 데미지를 계산한다
            1. 각각 탑과 적 사이의 거리를 구한 후, 사정거리 안에 존재하는지의 여부를 파악
            2. 사정거리 안에 적이 존재하는 탑은 그대로 공격할 수 있으므로 데미지 +를 해준다
            3. 적이 사정거리를 벗어나는 탑은, 가장 적은 횟수를 거쳐서, 적을 공격할 수 있는 타워에 에너지를 전송한다
        
        - 3번
            1. 공격 불가능한 탑에 대해, 공격 가능한 탑을 전체 탐색
            2. 두 탑의 거리가 사정거리 안에 있다면 에너지를 전송하고 다음 탑으로 넘어간다
            3. 만약 공격 불가능한 탑의 사정거리 안에, 공격 가능한 탑이 없다면?
                >> bfs로 도착 타워에 도달하는 최솟값을 구해서 전송한다
    
    try_2) 석용님꺼 보고 힌트 얻었습니다 ..!
        - 불가능한 타워에서 bfs를 시작하는것이 아니라,
          적의 위치에서부터 bfs를 시작하면 편하다!
'''
from collections import deque
from sys import stdin
input = stdin.readline

#define function
def calcuate(r1,c1,r2,c2): # 거리 계산
    return ((r1-r2)**2 + (c1-c2)**2)**0.5

def transmit(): # 적의 위치에서 몇 번 만에 각각의 탑으로 갈 수 있는지를 추적
    visited = set()
    dq = deque()
    move = 0
    damage = 0
    reduction_list = []
    dq.append((e_r,e_c,move))
    while dq:
        cr,cc,move = dq.popleft()
        for (nr,nc) in tower_location:
            distance = calcuate(cr,cc,nr,nc)
            if distance <= r and (nr,nc) not in visited:
                dq.append((nr,nc,move+1))
                visited.add((nr,nc))
                reduction_list.append(0.5**(move))
                
    for reduction in reduction_list:
        damage += d*reduction
    return damage         

#main
n,r,d,e_c,e_r = map(int,input().split())
tower_location = []
for _ in range(n):
    tower_c,tower_r = map(int,input().split())
    tower_location.append((tower_r,tower_c))
print(transmit())