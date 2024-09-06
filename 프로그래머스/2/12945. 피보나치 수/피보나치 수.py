def solution(n):
    dp = [0,1,1,2,3,5]
    if n<6:
        return dp[n]
    for i in range(6, n+1):
        dp.append(dp[i-1]+dp[i-2])
    return dp[n]%1234567