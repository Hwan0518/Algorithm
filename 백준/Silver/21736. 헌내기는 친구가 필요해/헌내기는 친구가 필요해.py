from collections import deque
from sys import stdin
input = stdin.readline

n,m = map(int,input().split())
campus = []
for r in range(n):
    campus.append(list(input().strip()))
    for c in range(m):
        if campus[r][c] == "I":
            stt = (r,c)

dr = [-1,1,0,0]
dc = [0,0,-1,1]
cnt = 0
dq = deque()
dq.append(stt)
visited = [[False]*m for _ in range(n)]
visited[stt[0]][stt[1]] = True
while dq:
    cr,cc = dq.popleft()
    for i in range(4):
        nr,nc = cr+dr[i], cc+dc[i]
        if not(0<=nr<n and 0<=nc<m) or campus[nr][nc] == "X" or visited[nr][nc]:
            continue
        if campus[nr][nc] == "P":
            cnt +=1
        visited[nr][nc] = True
        dq.append((nr,nc))

print(cnt) if cnt else print("TT")