'''
Approach
    - 상대팀 진영으로 가는 최소칸의 개수를 return
    - 도착못하면 -1을 return
    - 1<=n,m<=100
    - 0은 벽, 1은 빈공간
    - 항상 캐릭터는(0,0), 상대진영은 (n-1,m-1)에 위치함

>>> dfs,bfs 모두 구현해보자
    
'''
#bfs
from collections import deque
dr = [-1,1,0,0]
dc = [0,0,-1,1]

def solution(maps):
    answer = 1e9
    n = len(maps)
    m = len(maps[0])
    finish = False
    # bfs 시작
    dq = deque()
    visited = [[False]*m for _ in range(n)]
    stt = (0,0)
    cnt = 1
    dq.append((stt,cnt))
    print(visited)
    while dq:
        (r,c), cnt = dq.popleft()
        if visited[r][c] or cnt >= answer:
            continue
        visited[r][c] = True
        for i in range(4):
            nr = r+dr[i]
            nc = c+dc[i]
            if not(0<=nr<n and 0<=nc<m) or visited[nr][nc] or not maps[nr][nc]:
                continue
            # 상대 진영에 도착했다면 answer와 cnt를 비교
            if (nr,nc) == (n-1,m-1):
                finish = True
                answer = min(answer,cnt+1)
                continue
            dq.append(((nr,nc), cnt+1))
    if not finish:
        return -1
    return answer