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
result = [sum(visitor[:x])]
for i in range(n-x):
    result.append(result[-1] - visitor[i] + visitor[i+x])
maxNum = max(result)
cnt = result.count(maxNum)

if maxNum:
    print(maxNum)
    print(cnt)
else:
    print('SAD')