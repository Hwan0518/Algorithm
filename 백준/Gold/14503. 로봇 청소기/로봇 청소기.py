'''
Condition
    - TL : 2s(4000만)
      ML : 512mb(*)
    - 3<=N,M<=50
    - d : (0,1,2,3) == (북,동,남,서)
    - 빈칸은 0, 벽은 1
    - 지도의 외각은 항상 벽
    - 작동
        1. 현재 위치를 청소
        2. 현재 위치에서, 주변 4칸중 청소되지 않은 빈 칸이 없는경우
        2-1. 바라보는 방향을 유지하고, 후진할 수 있다면 후진
             다시 1번으로
        2-2. 후진할 수 없다면 작동 종료
        3. 현재 위치에서, 주변 4칸중 청소되지 않은 빈 칸이 있는 경우
        3-1. 반시계 방향으로 90도 회전
        3-2. 바라보는 방향에서, 앞쪽 칸이 청소되지 않은 빈칸이라면 한칸 전진
        3-3. 1번으로 돌아감 
        
        >>> 현재 방향에서, 왼쪽 방향에 청소 공간이 나올때까지 왼쪽으로 회전하며 탐색
            이후 네 방향 모두가 안된다면, 방향 유지하고 후진
                후진 할 수 있다면, 다시 탐색을 반복
                후진 할 수 없다면, 작동을 멈춤
    - 청소 돼있는 칸을 또 청소X
    - 벽 통과 X
    
Question
    - 로봇 청소기가 청소하는 칸의 개수는?
    
Access
    try_1)
        - 필요한 기능 : 탐색 기능, 회전 기능, 이동 기능(전진,후진)
        - 탐색 기능 : bfs를 이용한다
        - 회전 기능 : bfs안에서 dq안에 현재 방향을 기록해준다
        - 이동 기능 : bfs를 이용한다
        
'''
from collections import deque
from sys import stdin
input = stdin.readline

#define function
def run():
    clean = 0
    dq=deque()
    dq.append(stt)
    while dq:
        cant_clean = True
        cr,cc,cd = dq.popleft()
        #1. 현재 위치 청소
        if not visited[cr][cc]:
            clean +=1
            visited[cr][cc] = True
        #2. 왼쪽부터 탐색
        for _ in range(4):
            nd = (cd+3)%4
            cd = nd
            nr,nc = cr+dirt[nd][0], cc+dirt[nd][1]
            # 범위를 벗어나거나, 벽인경우
            if not(0<=nr<n and 0<=nc<m) or mapp[nr][nc]==1 or visited[nr][nc]:
                continue
            # 청소되지 않은 빈칸인 경우
            dq.append((nr,nc,nd))
            cant_clean = False
            break
        if not cant_clean:
            continue
        # 청소되지 않은 빈 칸이 없는 경우   
        od = opposite_dirt[nd]
        op_r,op_c = cr+dirt[od][0], cc+dirt[od][1]
        #2-3. 네 방향 모두 X일 때, 후진할 수 있다면
        if (0<=op_r<n and 0<=op_c<m) and mapp[op_r][op_c] != 1:
            dq.append((op_r,op_c,nd))
        #2-4. 후진할 수 없다면
        else:
            break                
    return clean

#main
n,m = map(int,input().split())
stt = list(map(int,input().split()))
# stt[0] -=1
# stt[1] -=1
mapp = [list(map(int,input().split())) for _ in range(n)]
visited = [[False for _ in range(m)] for _ in range(n)] # 청소 여부를 확인
dirt = {0:(-1,0), 1:(0,1), 2:(1,0), 3:(0,-1)} # 북,동,남,서
opposite_dirt = {0:2, 1:3, 2:0, 3:1}
print(run())
