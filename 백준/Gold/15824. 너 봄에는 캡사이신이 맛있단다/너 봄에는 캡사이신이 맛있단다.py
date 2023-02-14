'''
Condition
    - TL : 1s(2000만)
      ML : 512(128*100만)
    - 1<=N<=30만
    - 0<=스코빌지수<=(2^31)-1
    
Question
    - 모든 조합에서의 주헌고통지수 합을 1_000_000_007로 나눈 나머지를 출력하여라

Access
    try_1)
        - 예상 분류 : dp/수학
        - 실제 분류 : 수학
        - 가능한 모든 조합들에서, [최댓값-최솟값]의 합을 구한다
            : i를 모든 scoville에 대해 순차탐색, i를 고정시켰을 때, i가 최대or최소인 경우의 수를 구해봄 
        >>> fail(구현실패)
        
    try_2) clone coding : 
        - (각 조합에서의 최댓값의 합) - (각 조합에서의 최솟값의 합)
        - 숫자들을 정렬한 후, 각 숫자가 최솟값일떄와 최댓값일때 경우의 수를 계산해본다
            1. 숫자 정렬
            2. i가 최솟값인 경우의 수가 q개 >> 최솟값들의 합 += i*q
                    : q = 2**(i보다 큰 수의 개수)
               i가 최댓값인 경우의 수가 p개 >> 최댓값들의 합 += i*p
                    : p = 2**(i보다 작은 수의 개수)
                
'''
from sys import stdin
input = stdin.readline


#define function
def case_i_max(i): 
    cnt = 2 ** len(scoville[:i])
    return cnt


def case_i_min(i):
    cnt = 2 ** len(scoville[i+1:])
    return cnt    


def solution():
    sum_max = 0
    sum_min = 0
    
    # i를 모든 scoville에 대해 탐색
    for i in range(len(scoville)):
        # i가 고정되어 있을 때, i가 최소인 경우와 최대인 경우
        sum_max += scoville[i] * case_i_max(i)
        sum_min += scoville[i] * case_i_min(i)

    return sum_max-sum_min

#main
n=int(input())
scoville = list(map(int,input().split()))
scoville.sort()

print(solution()%1_000_000_007)