'''
- TL : 1s
- 1<=n<=300, 1<=score<=1만
- 계단 규칙
    - 한번에 하나 or 두 개씩 오를 수 있다
    - 세 개를 연속해서 밟을 수 없다 (시작점은 계단x)
    - 마지막 계단은 반드시 밟아야한다
이 게임에서 얻을 수 있는 총 점수의 최댓값은?

>>> 브루트포스, 백트래킹, dp등으로 풀 수 있을 것 같음
>>> dp로 해결해보자

1. dp
    - Object(Bottom-up Or Top-down) 
        : dp 끝 원소의 최댓값을 구한다 -> bottom-up으로 갱신하자
    - Array(Dimension of Array)
        : i번째 계단을 밟았을 때, 최댓값을 계속 갱신하면 되므로 1차원만 존재하면 됨
    - Approach
        - 현재 인덱스에서 +1 or +2 안에서만 조회해야함
        - 연속 cnt를 관리하며, 연속 cnt==2라면 무조건 +2를 밟아야함
        - +2가 연속계단일때, cnt가 2보다 작다면 -> +1과 +2를 모두 밟고
                         cnt가 2라면 -> +2만 밟는다
        - dp[i] = i번째 계단에서 최댓값
                = max(stair[i])
    >>> 접근을 잘못함. top-down으로 가야함

2. top-down
    - dp[i] = max(dp[i-1], dp[i-2])
    - if cnt = 2 -> dp[i] = dp[i-2] + stair[i]
    else -> dp[i] = max(dp[i],dp[i-2]) + stair[i]
    
3. depth-2에서는 연속cnt를 1로, depth-1에서는 연속cnt를 +1로 해야한다

4. depth==1일때는, 첫 번째 계단을 밟던가 아니면 자기 자신만 밟을 수 있다

5. Array -> 연속cnt = 2일때와 1일때로 분리해야하므로, dp = 2차원배열로 해야한다
'''
from sys import stdin
input = stdin.readline

def inputData():
    n = int(input())
    stair = [int(input()) for _ in range(n)]
    return n, stair


def dfs(depth, cnt_continue, stair):
    # 첫 번째 계단이라면 그대로 값을 리턴
    if depth == 0:
        return stair[0]
    elif depth == 1:
        # 만약 연속cnt ==2라면 첫 번째 계단을 밟을 수 없음
        if cnt_continue == 2:
            dp[1][1] = stair[1]
        # 2미만이라면 첫 번째 계단도 밟음
        else:
            dp[1][0] = stair[0]+stair[1]
            
    # dp[depth]를 한번도 밟은적이 없다면 최댓값 구하기
    if not dp[depth][cnt_continue-1]:
        # 연속cnt <2라면, depth-1과 depth-2중 최댓값을 계산
        if cnt_continue < 2:
            # depth-1에서는 연속cnt를 +1, depth-2에서는 연속cnt를 1로
            dp[depth][cnt_continue-1] = max(
                dfs(depth-1, cnt_continue+1, stair), 
                dfs(depth-2, 1, stair)
            )+stair[depth]
        # 연속cnt ==2라면, depth-2만 가능
        else:
            dp[depth][cnt_continue-1] = dfs(depth-2, 1, stair)+stair[depth]
            
    # dp[depth]를 return
    return dp[depth][cnt_continue-1]


def solution(n, stair):
    global dp
    dp = [[0,0] for _ in range(n)]
    dp[0] = [stair[0], stair[0]]
    # 마지막 계단에서 cnt_continue가 1일때와 2일때를 모두 구해야한다
    dfs(n-1, cnt_continue:=1, stair)
    dfs(n-1, cnt_continue:=2, stair)
    return max(dp[-1])
    

print(solution(*inputData()))

