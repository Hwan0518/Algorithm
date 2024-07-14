'''
Condition
    - TL: 1s
    - 

Goals
    - 

Approach
    - bfs로 가능?
'''
from collections import deque
from sys import stdin
input = stdin.readline

n,m = map(int,input().split())
move = [i for i in range(101)]
for _ in range(n+m):
    stt,end = map(int,input().split())
    move[stt] = end
visited = [False]*101

dq = deque()
dq.append((1,0))
visited[1] = int(1e9)
while dq:
    cur,cnt = dq.popleft()
    if cur == 100:
        break
    if visited[cur] and cnt >= visited[cur]:
        continue
    visited[cur] = cnt
    for i in range(1, 7):
        if cur+i > 100:
            continue
        new = move[cur+i]
        dq.append((new,cnt+1))

print(cnt)