'''
Condition
    - TL: 2s
    - 분할
        1. 종이가 모두 같은 수인지 확인
            - 같다 -> end
            - 같지 않다 -> 2번으로
        2. 종이를 동일한 크기로 9등분한다(r,c를 1/3으로 만듦)
        - 모든 종이가 1번이 나올 때 까지 반복

Goals
    - -1 종이의 개수, 0 종이의 개수, 1 종이의 개수를 출력

Approach
    - 분할정복
    - hint: https://yunway.tistory.com/13
'''
from collections import deque
from sys import stdin
input = stdin.readline

n = int(input())
paper = [list(map(int,input().split())) for _ in range(n)]

cnt_1, cnt0, cnt1 = 0,0,0
def divide(r, c, n):
    global cnt_1, cnt0, cnt1
    value = paper[r][c]
    
    for i in range(r, r+n):
        for j in range(c, c+n):
            # 값이 다르다면 3등분 하여 다시 탐색
            if value != paper[i][j]:
                for nr in range(3):
                    for nc in range(3):
                        divide(r+(n//3)*nr, c+(n//3)*nc, n//3)
                return
    # 모두 동일하다면 cnt증가
    if value == -1:
        cnt_1+= 1
    elif value == 0:
        cnt0 +=1
    else:
        cnt1 +=1

divide(0,0,n)
print(cnt_1)
print(cnt0)
print(cnt1)