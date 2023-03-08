'''
Condition
    - TL : 1s (2000만)
      ML : 256 (64*100만)
    - 1<=N<=1000
    - 0은 딸기, 1은 초코, 2는 바나나
    - 딸기 > 초코 > 바나나 > 딸기

Question
    - 규칙을 지키며 마실 수 있는 우유의 최대 개수는?

Access
    try_1)
        - 예상 분류 : 그래프, BFS
          문제 분류 : dp

        - dp[i][j] = (i,j)에 도달했을 때 마신 우유 개수
                   = max(dp[위에서 왔을때], dp[왼쪽에서 왔을때])
                   = max(dp[i-1][j], dp[i][j-1])
                   
        >>> top-down 방식은 첫번째 우유의 상태를 모르므로 fail
        
    
    try_2)
        - bottom-up으로 진행
        >>> 연속되지 않은 칸을 카운트 하지 못함
        
    try_3)
        - 3차원 dp로 진행해서 다음에 먹을 수 있는 우유를 표시해줘야 한다
          : dp[i][j][k] = [(i,j)까지 마신 우유, 오른쪽으로 갈 때 마셔야 할 우유, 아래로 갈 때 마셔야 할 우유]
        >>> fail..
        
    try_4)
        - clone coding : https://orchemi.github.io/boj%20gold%20iv/BOJ_TD01_14722/

'''
from sys import stdin
ipnut = stdin.readline




#define fucntion
def solution():
    for i in range(1,n+1):
        for j in range(1,n+1):
            # i,j의 왼쪽, 위쪽 값
            left, up = dp[i][j-1], dp[i-1][j]
            
            # 조건을 만족시키면 count =1, 그렇지 않으면 0이다
            leftWant = (left[1]+1) %3
            upWant = (up[1]+1) %3
            
            leftCount = 1 if leftWant == mapp[i-1][j-1] else 0
            upCount = 1 if upWant == mapp[i-1][j-1] else 0
            
            # 왼쪽과 위쪽에서 마신 우유의 개수를 갱신
            leftMilk = left[0] + leftCount
            upMilk = up[0] + upCount
            
            location = [left, up]
            count = [leftCount, upCount]
            milk = [leftMilk, upMilk]
            
            # left가 크다면 0, up이 크다면 1
            maxVal = max(leftMilk,upMilk) != leftMilk
            
            # 둘중 큰 값으로 현재값을 갱신한다
            dp[i][j][0] = milk[maxVal]
            
            # 조건을 만족했다면 want를 갱신
            if count[maxVal]:
                dp[i][j][1] = mapp[i-1][j-1]
            else:
                dp[i][j][1] = location[maxVal][1]
    return




#main
n=int(input())
mapp = [list(map(int,input().split())) for _ in range(n)]
dp = [[[0,-1] for _ in range(n+1)] for _ in range(n+1)]

solution()
print(dp[n][n][0])

