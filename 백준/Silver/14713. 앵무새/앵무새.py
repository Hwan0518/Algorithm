'''
Condition
    - TL: 1s
    - 규칙
        - 앵무새는 n마리
        1. 앵무새 하나에 한 문장. 한 문장은 여러 단어로.
        2. 단어와 단어 사이에 다른 앵무새가 말할 수 있음
        3. 단어 중복 불가
        4. 한 문장에서는 단어 순서가 일치해야함

Goals
    - 문장 L이 가능하면 Possible, 불가능하면 Impossible 출력

Approach
    - 문자열
    - dict사용
    - 사전에 각 단어의 위치를 기억. 이후 순서가 맞는지 확인
'''
from sys import stdin
input = stdin.readline

n = int(input())
dt = []
sentence_len = []
for i in range(n):
    sentence = input().rstrip().split()
    sentence_len.append(len(sentence))
    dt.append(dict())
    # dict[단어] = 순서
    for j in range(len(sentence)):
        word = sentence[j]
        dt[i][word] = j

L = input().rstrip().split()
dt_idx = [0]*len(dt)
# 탐색
end = False
for w in L:
    exist = False
    if end:
        break
    for i in range(len(dt)):
        # word가 사전에 포함되는지 확인
        if w in dt[i]:
            exist = True
            cur_idx = dt_idx[i]
            next_idx = dt[i][w]
            if next_idx == cur_idx:
                dt_idx[i] +=1
                break
            else:
                end = True
    if not exist:
        end = True
        break
print("Possible") if not end and sentence_len == dt_idx else print("Impossible")