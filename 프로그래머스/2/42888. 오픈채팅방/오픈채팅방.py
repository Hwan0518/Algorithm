'''
dict로 id에 해당하는 사람들의 닉네임 정보를 보관
이후 제일 마지막에 출력
'''
from collections import defaultdict
def solution(record):
    answer = []
    temp = []
    dct = defaultdict(str)
    for r in record:
        rec = r.split();
        if len(rec) == 2:
            state, userId = rec
        else:
            state, userId, nickname = r.split()
        if state == "Enter":
            dct[userId] = nickname
            temp.append(["E", userId])
        elif state == "Leave":
            temp.append(["F", userId])
        else:
            dct[userId] = nickname
    # 매핑
    for s, userId in temp:
        if s == "E":
            answer.append(f"{dct[userId]}님이 들어왔습니다.")
        else:
            answer.append(f"{dct[userId]}님이 나갔습니다.")
    # 결과
    return answer