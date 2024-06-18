'''
Condition
    - TL : 1s
    - 

Goals
    - 같은 정수를 k개 이하로 포함한 최장 연속 부분 수열의 길이는?

Approach
    - 투포인터 써볼까

'''
from collections import defaultdict

def input_data():
    n,k = map(int,input().split())
    sequence = list(map(int,input().split()))
    return n,k,sequence

def two_pointer(n:int, k:int, sequence:list):
    s, e, max_cnt = 0, 0, 0
    num_cnt = defaultdict(int)
    while e < n:
        num = sequence[e]
        if num_cnt[num] < k:
            num_cnt[num] +=1
            e +=1
        else:
            num_cnt[sequence[s]] -=1
            s +=1
        max_cnt = max(max_cnt, e-s)
    return max_cnt

def main(n:int, k:int, sequence:list):
    return two_pointer(n, k, sequence)

print(main(*input_data()))