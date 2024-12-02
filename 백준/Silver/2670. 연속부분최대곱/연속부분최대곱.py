'''
dp[i]   = 0~i까지중 곱의 최댓값
        = max(dp[i-1], dp[i-1]*cur(연속일때만 가능), cur)
'''
from sys import stdin
input = stdin.readline

n = int(input())
lst = [float(input()) for _ in range(n)]

for i in range(1, n):
    lst[i] = max(lst[i], lst[i]*lst[i-1])
    
print('%0.3f' % max(lst))