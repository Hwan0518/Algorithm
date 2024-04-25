'''
Goals
- 다음 달에 선물을 가장 많이 받을 친구가 받을 선물의 수

Conditions
- A,B 중 다음달 누가 선물을 받는가?
    - 선물 주고받은 수가 다른 경우
        - 둘 중, 덜 받은 사람이 다음달에 하나를 받음
    - 선물 주고받은 수가 같거나 없는 경우
        * 선물지수: (이번달까지) 선물 보낸 수 - 선물 받은 수
        - 선물 지수가 더 큰 사람이 선물을 받음
        - 선물 지수가 같다면 서로 주고받지 않음

Approach
- 구현
- 필요
    - A,B 선물을 '준' 횟수를 기록한 dict
    - 선물 지수 계산
    - 다음달 받을 선물 개수 계산
'''
from collections import defaultdict

def calc_give_count(friends:list, gifts:list, n:int):
    give_cnt_map = defaultdict(lambda: defaultdict(int))
    for data in gifts:
        giver, receiver = data.split()
        give_cnt_map[giver][receiver] +=1
    return give_cnt_map
    
def calc_gift_score(give_cnt_map:dict, friends:list):
    gift_score = defaultdict(int)
    for giver in friends:
        for receiver in give_cnt_map[giver]:
            give_cnt = give_cnt_map[giver][receiver]
            gift_score[giver] += give_cnt
            gift_score[receiver] -= give_cnt
    return gift_score
    
def calc_next_receive(give_cnt_map:dict, gift_score:dict, friends:list):
    best_receive_cnt = 0
    for name in friends:
        next_receive_cnt = 0
        for friend in friends:
            if name == friend: 
                continue
            give_cnt = give_cnt_map[name][friend]
            receive_cnt = give_cnt_map[friend][name]
            if give_cnt > receive_cnt: 
                next_receive_cnt +=1
            elif give_cnt < receive_cnt:
                continue
            else:
                if gift_score[name] > gift_score[friend]: 
                    next_receive_cnt +=1
        if next_receive_cnt > best_receive_cnt:
            best_receive_cnt = next_receive_cnt
    return best_receive_cnt
    
def solution(friends, gifts):
    n = len(friends)
    give_cnt_map = calc_give_count(friends, gifts, n)
    gift_score = calc_gift_score(give_cnt_map, friends)
    answer = calc_next_receive(give_cnt_map, gift_score, friends)
    return answer