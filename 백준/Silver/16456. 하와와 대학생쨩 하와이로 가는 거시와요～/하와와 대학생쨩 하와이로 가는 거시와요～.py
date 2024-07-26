'''
Condition
    - TL: 1s
    - 첫 번째 섬에서 시작
        1. 바로 다음섬으로
        2. 한 섬을 건너뛰고 그 다음 섬으로
        3. 이전 섬으로

Goals
    - 라가가 여행할 수 있는 방법의 수

Approach
    - dp?
        - 점화식을 직접 찾자
            n=1 -> 1
            n=2 -> 1
            n=3 -> 2
            n=4 -> 3
            n=5 -> 4
            n=6 -> 6
            ... dp[i] = dp[i-3] + dp[i-1]

'''
n = int(input())

dp = [0]*(n+1)
if n>=1:
    dp[0] = 1
if n>=2:
    dp[1] = 1
if n>=3:
    dp[2] = 2

for i in range(3,n):
    dp[i] = dp[i-3] + dp[i-1]
print(dp[n-1]%1000000009)