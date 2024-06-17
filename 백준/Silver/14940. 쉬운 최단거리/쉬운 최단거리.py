'''
Condition
    - TL : 1s
    - 2<=n,m <=1000

Goals
    - 각 지점에서 목표지점까지의 거리를 출력
    - 0은 0으로, 1이지만 갈 수 없으면 -1로

Approach
    - 그냥 bfs로 풀어보자
    - 처음 만나는 지점이 최소라서 visited 처리만 잘 해주면 될듯
'''
from collections import deque
from sys import stdin
input = stdin.readline


def input_data():
    n,m = map(int,input().split())
    mapp = []
    # map 채우기
    for i in range(n):
        mapp.append(list(map(int,input().split())))
        for j in range(m):
            if mapp[i][j] == 2:
                goal = (i,j)
    return n, m, mapp, goal


def bfs(n:int, m:int, mapp:list[list], goal:tuple):
    dr = [-1,1,0,0]
    dc = [0,0,-1,1]
    stt_r, stt_c = goal
    result = [[0]*m for _ in range(n)]
    # goal에서부터 역으로 찾아간다
    dq = deque()
    dq.append([stt_r,stt_c,0])
    visited = [[False]*m for _ in range(n)]
    visited[stt_r][stt_c] = True
    cnt = 0
    while dq:
        cr, cc, cnt = dq.popleft()
        for i in range(4):
            nr = cr + dr[i]
            nc = cc + dc[i]
            # 조건
            if not(0<=nr<n and 0<=nc<m) or visited[nr][nc] or mapp[nr][nc] == 0:
                continue
            visited[nr][nc] = True
            result[nr][nc] = cnt +1
            dq.append((nr, nc, cnt +1))
    # visited = False이고 mapp에서 1이라면 -1로 바꿈
    for i in range(n):
        for j in range(m):
            if not visited[i][j] and mapp[i][j] == 1:
                result[i][j] = -1
    # 결과 반환
    return result


def main(n:int, m:int, mapp:list[list], goal:tuple):
    result = bfs(n, m, mapp, goal)
    for r in result:
        print(*r)
    return


main(*input_data())