'''
Condition
    - TL: 1s
    - 규칙
        1. 다른 색의 공은 여러 개 존재하더라도 한번에 뛰어넘을 수 있다
        2. 움직일 수 있는 공의 색은 한 가지이다

Goals
    - 공을 같은 색 끼리 모으기 위한 최소 이동 횟수를 출력

Approach
    - 그리디
        - 선택 사항
            1. 빨강 or 파랑
            2. 왼쪽으로 or 오른쪽으로
            -> 총 4가지 경우의 수가 존재
'''
n = int(input())
balls = list(input())

def move(b:str, d:str):
    cnt = 0
    # b의 모임을, d방향부터 카운트한다
    if d == "l":
        stt = 0
        searchRange = range(1, n)
    else:
        stt = n-1
        searchRange = range(n-2, -1, -1)
    # 탐색 시작
    before = balls[stt]
    ctn = 0
    if before == b:
        ctn +=1
    for cursor in searchRange:
        if balls[cursor] == b:
            ctn +=1
        elif balls[cursor] != b and ctn:
            cnt +=ctn
            ctn = 0
    return cnt

cnt = 1e9
for b in ["B","R"]:
    for d in ["l","r"]:
        cnt = min(cnt, move(b,d)) 

print(cnt)