'''
요구사항
    - TL : 1s
    - 1<=사용할 금액<=1만
    - 1<= m<=n <=10만
    - n일동안 m번만 통장에서 돈을 빼 씀
    - 한번에 k원을 인출
        - 충분하면 그대로 사용
        - 모자라면 남은 금액을 통장으로 넣고 다시 k원 인출
    - k의 최소금액을 구하라
    
접근
    - k는 사용할 금액의 최댓값보다 크거나 같아야한다
    - 이분탐색으로 접근해보자
        - 최솟값을 모든 날중 사용할 금액의 최댓값으로 설정
        - 최댓값을 모든 날의 사용할 금액의 합으로

1. 이분탐색
    - l = max(사용할 금액 리스트)
    - r = sum(사용할 금액 리스트)
    - m = (l+r)//2
'''
from sys import stdin
input = stdin.readline

def input_data():
    n,m = map(int,input().split())
    use_list = [int(input()) for _ in range(n)]
    return n, m, use_list

def binary_search(n, m, use_list):
    l = max(use_list)
    r = sum(use_list)
    while l<=r:
        cnt = 0
        cur = 0
        k = (l+r)//2
        # 조건을 만족하는지 확인
        for i in range(n):
            today_use = use_list[i]
            if cur < today_use:
                cnt +=1
                cur = k-today_use
            else:
                cur -= today_use
        # 인출횟수가 m보다 더 작거나 같다면 금액을 줄인다
        if cnt <= m:
            r = k-1
        # 인출횟수가 m보다 더 크다면 금액을 올린다
        else:
            l = k+1
    return l
    
def solution(n, m, use_list):
    min_k = binary_search(n, m, use_list)
    return min_k

print(solution(*input_data()))