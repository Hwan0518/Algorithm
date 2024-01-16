'''
요구사항
    - TL : 2s
    - r,c에서 이동가능한 곳
        - (r-2,c-1), (r-2,c+1)
        - (r, c-2), (r, c+2)
        - (r+2, c-1), (r+2, c+1)
    - (r1,c1)에서 (r2,c2)로 이동하는 최소 이동 횟수는?

접근
    - 최소이므로 bfs 사용

1. bfs
'''
from collections import deque

def input_data():
    n = int(input())
    object = map(int,input().split())
    return n, object

def bfs(n):
    visited = [[False]*n for _ in range(n)]
    dq = deque()
    dq.append((r1,c1,0))
    while dq:
        cr,cc,cnt = dq.popleft()
        # 목적지에 도달했다면, 가장 빠른 경로이므로 바로 cnt를 return해준다
        if (cr,cc) == (r2,c2):
            return cnt
        for i in range(6):
            nr = cr+dr[i]
            nc = cc+dc[i]
            # 예외처리
            if not(0<=nr<n and 0<=nc<n) or visited[nr][nc]:
                continue
            # 이동
            visited[nr][nc] = True
            dq.append((nr,nc,cnt+1))
    # while이 끝났다면, 이동할 수 없는 경우이므로 -1 출력
    return -1

def solution(n, object):
    global r1,c1,r2,c2,dr,dc
    r1,c1,r2,c2 = object
    # 방향 설정
    dr = [-2,-2,0,0,2,2]
    dc = [-1,1,-2,2,-1,1]
    # bfs
    return bfs(n)

print(solution(*input_data()))