'''
Condition
    - TL : 1s
    - 스티커를 떼면, 상하좌우 모두 사용x

Goals
    - 각 케이스마다 스티커 점수 최댓값 출력

Approach
    - n이 크니까 dp로 해보자
        - dp[i][j] = i열의 j행을 선택했을 때 스티커의 최대 값
            - 각 열에서 무조건 하나는 선택을 해야한다 (ㄱ or ㄴ 자로 visited가 되기 때문)
            - 따라서 0열에서 첫 번째를 선택하는 경우와, 두 번째를 선택하는 경우중 최대값을 고르면 된다
            - 0행을 마지막으로 고르는 경우
                - i-1열과 i-2열중 1행을 고르는 경우다
                - dp[0][i] = max(i-1열 1행을 선택하는 경우, i-2열 1행을 선택하는 경우) + i열 0행 선택
                           = max(dp[1][i-1], dp[1][i-2]) + score[0][i]
            - 1행을 마지막으로 고르는 경우
                - i-1열과 i-2열중 0행을 고르는 경우다
                - dp[1][i] = max(i-1열 0행을 선택하는 경우, i-2열 0행을 선택하는 경우) + i열 1행 선택
                           = max(dp[0][i-1], dp[0][i-2]) + score[1][i]
                           
    - 참고 블로그: https://beyond-common-sense.tistory.com/10
'''
import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(2)]

    # 2행 DP배열 형성
    DP = [[0] * N for _ in range(2)]

    # 스티커 길이가 1일 경우
    DP[0][0] = arr[0][0]
    DP[1][0] = arr[1][0]
    if N == 1:
        print(max(DP[0][0], DP[1][0]))
        continue

    # 스티커 길이가 2일 경우
    DP[0][1] = arr[1][0] + arr[0][1]
    DP[1][1] = arr[0][0] + arr[1][1]
    if N == 2:
        print(max(DP[0][1], DP[1][1]))
        continue

    # 스티커 길이가 3이상일 경우
    for i in range(2, N):
        # 메인 아이디어
        DP[0][i] = max(DP[1][i - 2], DP[1][i - 1]) + arr[0][i]
        DP[1][i] = max(DP[0][i - 2], DP[0][i - 1]) + arr[1][i]

    print(max(DP[0][-1], DP[1][-1]))