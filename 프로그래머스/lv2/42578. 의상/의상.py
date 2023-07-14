'''
Approach
    - 종류별로 1개만
    - 일부가 겹치지 않거나 추가된 경우 다른옷으로 봄
    - closthes = [이름, 종류]
    - 1<=str<=20
    - 1<=의상<=30
    >>> 서로 다른 옷의 조합의 수를 return

>>> dictionary를 사용
    : 종류를 key, 이름을 value로 두고, 각 종류의 개수를 모두 곱해주면 됨
'''
from itertools import combinations
def solution(clothes):
    answer = 1
    types = {}
    for data in clothes:
        name, t = data
        if not t in types:
            types[t] = []
        types[t].append(name)
    
    for t in types:
        cnt = len(types[t]) +1
        answer *= cnt
        
    return answer-1