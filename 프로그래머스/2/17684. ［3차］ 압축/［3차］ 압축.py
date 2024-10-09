'''
- 한글자씩 현재 입력으로 옮김
- (현재입력 + 다음글자)가 dict에 있으면 
        -> 현재입력에 (현재입력 + 다음글자)로 설정하고 반복
- (현재입력 + 다음글자)가 dict에 없으면 
        -> 현재입력을 색인번호로 등록 + (현재입력+다음글자)를 사전에추가 + 다음글자를 현재입력으로 설정
'''
from string import ascii_uppercase
def solution(msg):
    # 사전 초기화
    d = {}
    for i in range(len(ascii_uppercase)):
        d[ascii_uppercase[i]] = i+1
    # 압축
    answer = []
    cur = msg[0]
    for i in range(len(msg)):
        if i < len(msg)-1:
            nxt = msg[i+1]
        else:
            nxt = ''
        # cur + nxt가 dict에 있는경우
        s = cur+nxt
        if s in d and i != len(msg)-1:
            cur = s
        # cur + nxt가 dict에 없는 경우
        else:
            answer.append(d[cur])
            d[s] = len(d)+1
            cur = nxt
    return answer