'''
Condition
    - TL: 6s 
    - command
        - d: n*=2, if n>9999: n=n%10000
        - s: s -=1, if n==0: n=9999
        - l: lotate(-1)
        - r: lotate(1)

Goals
    - A를 B로 변경하기 위한 최소한의 명령어 나열을 출력

Approach
    - 시간 넉넉하니까 백트래킹
    -> MLE
        1. dslr 함수에서 deque 사용x
        2. bfs에서 commands와 min_commands를 list가 아닌 str로 다룸
        3. visited 사용
'''
from collections import deque
from sys import stdin
input = stdin.readline

def d(n:int):
    n *= 2
    if n > 9999: n = n%10000
    return n

def s(n:int):
    return 9999 if n == 0 else n-1

def l(n:int):
    n = str(n)
    while len(n) <4:
        n = '0'+n
    new = n[1:] + n[0]
    return int(new)

def r(n:int):
    n = str(n)
    while len(n) <4:
        n = '0'+n
    new = n[-1] + n[:len(n)-1]
    return int(new)

def bfs():
    visited = [False] * 10000
    dq = deque()
    dq.append((a, ""))
    while dq:
        n, commands = dq.popleft()
        if n == b:
            return commands
        for i in range(4):
            new = command[i](n)
            if visited[new]:
                continue
            visited[new] = True
            dq.append((new, commands + c_str[i]))

command = [d,s,l,r]
c_str = ['D','S','L','R']
for _ in range(int(input())):
    global a,b
    a, b = map(int,input().split())
    print(bfs())