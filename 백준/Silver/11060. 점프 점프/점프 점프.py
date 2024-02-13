'''
Condition
    - TL : 1s
    - 1<=N<=1000, 0<=A_i<=100

Question
    - 미로의 가장 왼쪽 끝에서 가장 오른쪽 끝으로 갈 때, 최소 몇 번 점프해야하는가?
    - 불가능한 경우 -1을 출력

Approach
    - 백트래킹으로는 TLE 발생 -> O(M^N) = O(100^1000)
    - dp로 풀어보자

try_1) DP, Bottom-up
'''
n = int(input())
maze = list(map(int, input().split()))
InF = int(1e9)
dp = [0] + [InF] * (2*n)
for i in range(n):
    for jump in range(1, maze[i]+1):
        dp[i+jump] = min(dp[i+jump], dp[i]+1)
if dp[n-1] == InF:
    print(-1)
else:
    print(dp[n-1])