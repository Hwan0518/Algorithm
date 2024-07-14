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
move = dict()
for _ in range(n+m):
    stt,end = map(int,input().split())
    move[stt] = end
visited = [False]*101

def bfs():
    min_cnt = 0
    dq = deque()
    dq.append((1,0))
    visited[1] = True
    while dq:
        cur,cnt = dq.popleft()
        if cur == 100:
            min_cnt = cnt
            break
        for i in range(1, 7):
            new = cur+i
            if new in move:
                new = move[new]
            if new > 100 or visited[new]:
                continue
            visited[new] = True
            dq.append((new,cnt+1))
    return min_cnt

print(bfs())