'''
dp[i] = i일 때 최소 연산 수

'''


n = int(input())
INF = int(1e9)
dp = [INF]*(n+1)
dp[n] = 0
methods = [INF]*(n+1)

for x in range(n, 0, -1):
    if not x%3 and dp[x]+1 < dp[x//3]:
        dp[x//3] = dp[x]+1
        methods[x//3] = 0
    if not x%2 and dp[x]+1 < dp[x//2]:
        dp[x//2] = dp[x]+1
        methods[x//2] = 1
    if dp[x]+1 < dp[x-1]:
        dp[x-1] = dp[x]+1
        methods[x-1] = 2

print(dp[1])
result = []
i=1
while i < n:
    result.append(i)
    if methods[i] == 0:
        i *=3
    elif methods[i] == 1:
        i *=2
    else:
        i +=1
result.append(n)
print(*result[::-1])