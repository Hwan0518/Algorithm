'''
Approach
    - sigma(1,n) = s, 모든 수는 다르다
    - 1<=s<=2*int 정도크기
    >>> s가 주어졌을 때, n의 최댓값은?

>>> 이분탐색
    : n이 가장 커질때를 구한다
     = 가장 많은 수들을 더해야 한다
     = 1부터 n까지 차례대로 더해본다
     = n을 1~1/s 범위에서 이분탐색으로 구해본다

>>> 무조건 1~n까지 1씩 증가하면서 사용한다는 보장이 없다
    : s보다 첫번째로 작아지는 수가 정답이다
      왜냐면, 그 sigma값에서 맨 마지막 수를 빼고, 정답이 되는 수를 넣으면 되기 때문
'''
s=int(input())
l=0
r=s
mid = 0
maxInMin = 0
flag = False
while l<=r:
    mid = (l+r)//2
    summation = mid*(mid+1)//2
    if summation < s:
        l = mid+1
        maxInMin = max(maxInMin, mid)
    elif summation >s:
        r = mid-1
    else:
        flag = True
        print(mid)
        break
if not flag : print(maxInMin)
