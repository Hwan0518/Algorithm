'''
Condition
    - TL: 1s
    - Pn = IOI가 n번 번갈아서 존재하는 것
        - P1 = IOI
        - P2 = IOIOI
        - Pn = IOIOIOI...OI (O가 N개)

Goals
    - S에 Pn이 몇 개 존재하는가?

Approach
    - 투포인터
'''
n = int(input())
m = int(input())
s = input().strip()
pn = "IO"*n + "I"
cur = ""
cnt = 0
r = 0
for l in range(m):
    while len(cur) < len(pn) and r < m:
        cur += s[r]
        r +=1
    if cur == pn:
        cnt +=1
    cur = cur[1:]
print(cnt)