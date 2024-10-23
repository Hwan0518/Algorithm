'''
w 위력 합, b 위력 합 (위력 = 모인 인원**2)
'''
from collections import deque
from sys import stdin
input = stdin.readline

def input_data():
    m,n = map(int,input().split())
    mapp = [list(input()) for _ in range(n)]
    return n,m,mapp
    
def bfs(n,m,mapp,color):
    power = 0
    visited = [[False]*m for _ in range(n)]
    # (0,0)부터 탐색
    for r in range(n):
        for c in range(m):
            if visited[r][c] or mapp[r][c] != color:
                continue
            if (r,c) == (3,3):
                pass
            visited[r][c] = True
            cnt = 0
            dq = deque()
            dq.append((r,c))
            while dq:
                cr,cc = dq.popleft()
                cnt +=1
                # 탐색
                for i in range(4):
                    nr,nc = cr+dr[i], cc+dc[i]
                    # 통과 못하는 조건
                    if not(0<=nr<n and 0<=nc<m) or visited[nr][nc] or mapp[nr][nc] != color:
                        continue
                    # 통과했다면
                    visited[nr][nc] = True
                    dq.append((nr,nc))
            # 주위에 우리팀 없다면 power 계산
            power += cnt**2
    # 최종 결과
    return power

def solution(n,m,mapp):
    global dr,dc
    dr = [-1,1,0,0]
    dc = [0,0,-1,1]
    return bfs(n,m,mapp,"W"), bfs(n,m,mapp,"B")

print(*solution(*input_data()))