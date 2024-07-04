from sys import stdin
input = stdin.readline
n,m=map(int,input().split())
nums = list(map(int,input().split()))
prefix_sum = [0]
for i in range(n):
    prefix_sum.append(prefix_sum[i] + nums[i])
for _ in range(m):
    stt,end = map(int,input().split())
    print(prefix_sum[end]-prefix_sum[stt]+nums[stt-1])