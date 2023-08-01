'''
Approach
    - 준현 : 살 수 있다면 무조건 최대한 많이 즉시 매수
    - 성민 : 전량매수,전량매도만 함
            3일째 연속 상승하면 무조건 매도
            3일째 연속 하락하면 무조건 매수
    - 자산 : 현금 + 1월14일의 주가*주식수
    
>>> 구현    
'''
from sys import stdin
input = stdin.readline


# function
def inputData():    
    j_cash = int(input())
    share_price = list(map(int,input().split()))
    return j_cash, share_price

def solution(j_cash, share_price):
    s_cash = j_cash
    s_stock = j_stock = 0
    up = 0
    down = 0
    for i in range(14):
        today_price = share_price[i]
        # <준현>
        # 현금이 오늘 주가보다 많다면 가능한 만큼 매수
        j_possible_cnt = j_cash//today_price
        if j_possible_cnt:
            j_stock += j_possible_cnt
            j_cash -= today_price*j_possible_cnt
        
        # <성민>
        # 어제와 주가 비교
        if i == 0:
            continue
        yesterday_price = share_price[i-1]
        if today_price > yesterday_price:
            up +=1
            down = 0
        elif today_price < yesterday_price:
            up = 0
            down +=1
        else:
            up = 0
            down = 0
        # 3일째 연속 하락중이라면 무조건 전량 매수
        if down >= 3 and s_cash:
            s_possible_cnt = s_cash // today_price
            s_stock += s_possible_cnt
            s_cash -= today_price*s_possible_cnt
        # 3일째 연속 상승중이라면 무조건 전량 매도
        if up == 3 and s_stock:
            s_cash += today_price*s_stock
            s_stock = 0

    j_asset = j_cash + j_stock*share_price[-1]
    s_asset = s_cash + s_stock*share_price[-1]
    if j_asset > s_asset:
        return "BNP"
    elif j_asset < s_asset:
        return "TIMING"
    else:
        return "SAMESAME"

print(solution(*inputData()))

