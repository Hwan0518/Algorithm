'''
Condition
    - TL: 1s
    - 하루 지나면 -> 익은 토마토 인접해있는 익지않은 토마토가 익음(상하좌우,앞뒤 총 6개)

Goals
    - 토마토가 모두 익을때까지 최소 며칠이 걸리는가?
    - 불가능하다면 -1

Approach
    - bfs
'''
from sys import stdin
from collections import deque
input = stdin.readline

m,n,h = map(int,input().split())
tomatoes = []
ripe = []
unripe = 0
visited = [[[False]*m for _ in range(n)] for _ in range(h)]
for z in range(h):
    space = []
    for r in range(n):
        space.append(list(map(int,input().split())))
        for c in range(m):
            if space[r][c] == 1:
                ripe.append((0,z,r,c))
                visited[z][r][c] = True
            elif space[r][c] == 0:
                unripe +=1
    tomatoes.append(space)
dr = [-1,1,0,0,0,0]
dc = [0,0,-1,1,0,0]
dz = [0,0,0,0,-1,1]

def bfs():
    global unripe
    min_day = 1e9
    dq = deque(ripe)
    while dq:
        if not unripe:
            min_day = max(dq)[0]
            break
        day,z,r,c = dq.popleft()
        for i in range(6):
            nz,nr,nc = z+dz[i], r+dr[i], c+dc[i]
            if not(0<=nz<h and 0<=nr<n and 0<=nc<m) or visited[nz][nr][nc] or tomatoes[nz][nr][nc] != 0:
                continue
            visited[nz][nr][nc] = True
            tomatoes[nz][nr][nc] = 1
            unripe -=1
            dq.append((day+1,nz,nr,nc))
    return min_day

def main():
    if not unripe:
        return 0
    need_day = bfs()
    return need_day if not unripe else -1

print(main())