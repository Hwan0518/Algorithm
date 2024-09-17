'''
- dp[i] = i번째 칸에 도달할 수 있는 방법의 수
        = dp[i-1] + dp[i-2]
- dp[0] = 0, dp[1] = 1, dp[2] = 2
'''


def solution(n):
    dp = [0]*(2001)
    dp[1] = 1
    dp[2] = 2
    for i in range(3,n+1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n]%1234567