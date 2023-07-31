'''
Approach
    - 최대 방문자 수와 기간을 구하여라
    - 최대 방문자가 0이라면 'SAD'출력

>>> 투포인터
    : l = 0, r = l+x로 잡고 r이 n-1에 도착할때까지 진행하면서 최댓값을 비교해서 cnt한다
'''
from sys import stdin
input = stdin.readline

n,x = map(int,input().split())
visitor = list(map(int,input().split()))
maxNum = sum(visitor[:x])
cur = maxNum
cnt = 1
l = 0
r = l+x
while r < n:
    cur -= visitor[l]
    cur += visitor[r]
    
    if cur > maxNum:
        cnt = 1
        maxNum = cur
    elif cur == maxNum:
        cnt +=1
    
    l +=1
    r = l+x

if maxNum:
    print(maxNum)
    print(cnt)
else:
    print('SAD')