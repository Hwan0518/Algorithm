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
    - 투포인터 (슬라이딩 윈도우) -> TLE (패턴과 현재문자열 비교하는 과정이 O(N)이라 불가능)
    - flag를 가지고 다니면서 true인지 false인지 확인하자
'''
n,m = int(input()), int(input())
s = input().strip()
# IO 패턴
pn = "IO"*n + "I"
cnt = 0
p_cnt = 0
# 탐색
i=0
while i < m:
    cur = s[i:i+3]
    if cur == "IOI":
        cnt +=1
        i +=2
        if cnt == n:
            p_cnt +=1
            cnt -=1
    else:
        cnt = 0
        i +=1

print(p_cnt)