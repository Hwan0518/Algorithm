'''
Condition
    - TL: 2s
    - 두 좌표 (x,y)와 (a,b)가 있을 때
        - |a-x|<=2, |b-y|<=2 -> (x,y)에서 (a,b)로 이동 가능
        - 즉, 위 아래 두 칸 이하로 차이난다면 이동할 수 있다

Goals
    - 최소 이동 회수를 출력. 도달하지 못한다면 -1을 출력

Approach
    - bfs
'''
from collections import deque
from sys import stdin
input = stdin.readline

n,t = map(int,input().split())
grooves = {tuple(map(int,input().split())) for _ in range(n)}

minCnt = -1
cnt = 0
dq = deque()
dq.append((0,0,0))
while dq:
    x,y,cnt = dq.popleft()
    if y == t:
        minCnt = cnt
        break
    for i in range(-2,3):
        for j in range(-2,3):
            a,b = x+i, y+j
            if (a,b) in grooves:
                dq.append((a,b,cnt+1))
                grooves.remove((a,b))
print(minCnt)