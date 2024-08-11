'''
Condition
    - TL: 1s
    - n초동안 싸움
    - a 시전시간 1초, b는 m초

Goals
    - n초동안 가능한 스킬 조합 수를 1_000_000_007로 나눈 나머지 값을 출력

Approach
    - dp
        - dp[i] = i초 동안 사용할 수 있는 스킬 조합 수
'''
n,b = map(int,input().split())
MOD = 1_000_000_007
dp = [0]*(n+1)
dp[0] = 1
for i in range(1,n+1):
    # a사용 -> dp[i-1]에서 a를 썼을 때의 경우의 수
    dp[i] = dp[i-1]
    # b사용 -> dp[i-b]에서 b를 썼을 때의 경우의 수
    if i >= b:
        dp[i] += dp[i-b]
print(dp[-1]%MOD)