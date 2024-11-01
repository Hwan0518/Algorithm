'''
dict쓰자
'''
import math
from collections import defaultdict


def calc_time(in_t:str, out_t:str):
    i_h,i_m = map(int,in_t.split(":"))
    o_h,o_m = map(int,out_t.split(":"))
    result = 0
    # m먼저 비교
    if o_m >= i_m:
        result += o_m-i_m
    else:
        result += 60+o_m-i_m
        o_h -= 1
    # h비교
    result += (o_h-i_h)*60
    return result


def calc_fee(fee_dct:dict, total_time:dict):
    for number in total_time:
        tt = total_time[number]
        fee_dct[number] += sf
        # 기본 시간 비교
        if tt <= st:
            continue
        else:
            tt -= st
        # 추가 시간 비교
        fee_dct[number] += math.ceil(tt/ut) * uf
    return fee_dct, total_time
        
def solution(fees, records):
    global st, sf, ut, uf
    answer = []
    # 요금
    st, sf, ut, uf = fees
    fee_dct = defaultdict(int)
    time_dct = defaultdict(str)
    total_time = defaultdict(int)
    for r in records:
        time,number,state = r.split()
        # 들어올 때
        if number not in time_dct:
            time_dct[number] = time
        # 나갈 때
        else:
            time_diff = calc_time(time_dct[number], time)
            total_time[number] += time_diff
            time_dct.pop(number)
    # 아직 나가지 않은 차량 -> 23:59에 나간것으로 간주
    for number in time_dct:
        time_diff = calc_time(time_dct[number], "23:59")
        total_time[number] += time_diff
    # 요금 계산
    fee_dct, total_time = calc_fee(fee_dct, total_time)
    # 번호 빠른순으로 answer에 집어넣음
    for number in sorted(fee_dct.keys()):
        answer.append(fee_dct[number])
    #정답
    return answer