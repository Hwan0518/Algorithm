'''
Condition
    - TL : 1s
    - 1<=h,w<=500

Question
    - 고이는 빗물의 총량을 출력하여라
    - 빗물이 전혀 고이지 않는다면 0을 출력하여라

Approach
    - 분류 : 구현
    - 가장 높은곳을 기준으로 왼쪽, 오른쪽에서 접근한다

try_1) 
    - 가장 높은곳의 위치 인덱스를 구한다
    - 가장 높은곳으로 접근, 가장 높은곳까지 도달하면 종료
    - 가운데로 이동
        - 양 끝점 높이를 기준으로 잡고 시작
        - 현재높이가 기준보다 낮은경우 : 기준 - 현재높이 를 더함
        - 현재높이가 기준보다 높은 경우 : 기준을 현재높이로 변경 
'''
def input_data():
    h,w = map(int,input().split())
    block_list = list(map(int,input().split()))
    return w, block_list

def rain_drop(w, stt, block_list):
    global rain
    standard = block_list[stt]
    if stt>=0:
        stt = 1
        step = 1
    else:
        stt = w-1
        step = -1
    for i in range(stt, max_idx, step):
        cur_h = block_list[i]
        if cur_h > standard:
            standard = cur_h
        else:
            rain += standard-cur_h

def solution(w, block_list:list):
    global rain, max_idx
    rain = 0
    # 가장 높은곳의 위치 인덱스
    max_h = max(block_list)
    max_idx = block_list.index(max_h)
    # 왼쪽에서 접근
    rain_drop(w, 0, block_list)
    # 오른쪽에서 접근
    rain_drop(w, -1, block_list)
    # 결과 반환
    return rain

print(solution(*input_data()))
