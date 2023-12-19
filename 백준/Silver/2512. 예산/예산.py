'''
- TL : 1s
- 3<=N<=1만
- 1<=M<=10억
- 1<=예산<=10만
- 가능한 최대의 총 예산
    1. 모든 요청이 가능한 경우 그대로 배정
    2. 불가능한 경우, 상한액을 정해두고 그 이상을 요청하면 다 상한액으로 처리
        상한액 이하에서는 그대로 배정
상한액 최댓값을 출력하라

>>> 이분탐색

1. 이분탐색
    - 합 <= 총예산 : 최댓값을 return
    - 합 > 총예산
        - 배열 정렬
        - 최저 상한선 : 총예산/n
        - 최대 상한선 : 총예산
        - 상한선 : 초기~최대 를 이분탐색으로
        - 예산들중 상한선 이상을 상한선으로 만들어서 탐색한다

2. 이분탐색
    - 상한선은 항상 중간값이된다
    - 꼭 배열안의 중간값이 될 필요는 없다. 임의의 중간값도 가능하다
'''
from sys import stdin
input = stdin.readline

def input_data():
    n = int(input())
    budget_list = list(map(int,input().split()))
    possible = int(input())
    return n, budget_list, possible

def binary_search(n, possible):
    # 초깃값
    l = 0
    r = possible
    # 탐색
    while True:
        # 결과값 리스트
        v_list = budgets[:]
        # 상한선 설정
        m = (l+r)//2
        # 상한선보다 큰 예산을 모두 상한선으로 바꿔버림
        for i in range(n):
            v = budgets[i]
            if v > m:
                v_list[i] = m
        result = sum(v_list)
        # 조건을 만족하면 return한다
        if l>r:
            return v_list[-1]
        # 결과가 조건보다 큰 경우 -> middle을 줄임
        if result > possible:
            r = m-1
        # 결과가 조건보다 작은 경우 -> middle을 높임
        else:
            l = m+1

def solution(n, budget_list, possible):
    global budgets
    # 합 <= 총예산 인 경우
    if sum(budget_list) <= possible:
        return max(budget_list)
    
    # 이분탐색 실시
    budgets = sorted(budget_list)
    return binary_search(n, possible)
    

print(solution(*input_data()))