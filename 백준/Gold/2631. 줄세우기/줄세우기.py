'''
Condition
    - TL : 1s(2000만)
      ML : 128(32*100만)
    - 2<=n<=200

Question
    - 옮겨야 하는 아이의 수가 최소가 되도록 정렬하여라

Access
    - 예상 분류 : LIS
      실제 분류 : dp

    try_1)
        - 이미 정렬된 아이들을 빼고 정렬시키면 된다 > LIS를 구하면 된다
            >>> dp[i] = i번째 문자까지 왔을 때, 가장 긴 LIS의 길이
                    
        
'''
from sys import stdin
input = stdin.readline
#define function
def solution():		
    # LIS를 구할때, 각각에 해당하는 아이를 무조건 선택한다고 가정하기 때문에 1로 초기화
    LIS = [1] * n
    
    # LIS[i] = max(j번째 아이를 선택했을때 vs j번째 아이를 선택하지 않았을 때)
    for i in range(n):
        for j in range(i):
            if InputChild[i] > InputChild[j]:
                LIS[i] = max(LIS[i], LIS[j] + 1)

    # n-LIS의 최댓값을 리턴
    return n-max(LIS)



#main
n=int(input())
InputChild = [int(input()) for _ in range(n)]
print(solution())