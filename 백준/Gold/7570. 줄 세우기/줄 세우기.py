'''
Condition
    - TL : 1s (2000만)
      ML : 256(64*100만)
    - 1<=어린이<=100만

Question
    - 줄세우기위해 이동하는 어린이의 최솟값을 출력하여라

Access
    - 예상 분류 : dp
      실제 분류 : dp, 그리디

    try_1)
        - 연속된 부분정렬의 최대길이를 찾아서 n에서 빼준다
        
    try_2)
        - clone coding : https://sangm1n.github.io/problem-solving/boj-7570/
'''
from sys import stdin
input = stdin.readline



#define function
def solution():		
    LIS = [0]*(n+1)
    
    # LIS[num] = 부분수열의 끝이 num일때의 길이 = LIS[num-1] +1 
    for i in range(n):
        num = InputChild[i]
        LIS[num] = LIS[num-1] +1
    
    # n-LIS의 최댓값을 리턴
    return n-max(LIS)



#main
n=int(input())
InputChild = list(map(int,input().split()))
print(solution())