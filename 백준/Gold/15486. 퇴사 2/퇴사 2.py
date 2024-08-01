'''
Condition
    - TL: 2s
    - 

Goals
    - 상담으로 얻을 수 있는 최대 이익을 출력

Approach
    - dp
        - dp[i] = i일에서 이익의 최댓값
    - 일을 할 수 있는지 여부를 확인하려면 Bottom-up으로 가야한다
'''
from sys import stdin
input = stdin.readline

n = int(input())
schedules = [(0,0)] + [tuple(map(int,input().split())) for _ in range(n)]
dp = [0]*(n+1)

for i in range(1,n+1):
    # 이전 날까지 일했을때와, 오늘까지 일했을때의 최댓값으로 갱신
    dp[i] = max(dp[i], dp[i-1])
    t,p = schedules[i]
    # 오늘의 상담을 할 수 있는 경우 -> 일을 한다
    target = i+t-1
    if target <= n:
        # 일을 마친날 최댓값
        dp[target] = max(dp[target], dp[i-1] +p)
print(dp[n])