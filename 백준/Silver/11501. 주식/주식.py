'''
Condition
    - TL: 5s
    - 세 가지중 하나의 행동
        1. 주식을 산다
        2. 주식을 판다
        3. 아무것도 안한다
    - N: 백만

Goals
    - 각 Tcase별로 최대 이익을 나타내는 정수 하나를 출력한다 

Approach
    - 빗물 받는 문제랑 비슷한듯?
        1. max값과 그 위치를 찾음
        2. i = max_i 될 때까지 순차적으로 탐색하면서 더함
        3. 초기화
        >>> max, index를 사용하면서 O(N^2)가 됨
        
    - 그렇다면 역순으로 탐색하며 maxVal을 갱신시키고, maxVal이 갱신되지 않는다면 차이를 더해줌     
'''
from sys import stdin
input = stdin.readline

def input_data():
    n = int(input())
    stock_price = list(map(int,input().split()))
    return n, stock_price

def main():
    for _ in range(int(input())):
        n, stock_price = input_data()
        profit = 0
        max_v = 0
        # 리스트를 역순으로 탐색하며 최댓값 갱신
        for i in range(n-1, -1, -1):
            if stock_price[i] > max_v:
                max_v = stock_price[i]
            # 이익 계산
            profit += max_v - stock_price[i]  
        print(profit)

main()