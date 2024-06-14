'''
Condition
    - TL : 1s
    - 매칭 가능 방이 없다면 새로 생성
        - 레벨 차이 +-10
    - 매칭 가능 방 존재하면 입장시키고 정원 찰 때까지 대기
        - 가능한 방이 여러 개라면 먼저 생긴 방에 입장
    - 방 정원 모두 차면 시작
    

Goals
    - 생성된 방 게임 시작 유무(Started!, Wating!), 플레이어 레벨과 아이디를 출력
    - 방은 생성된 순서대로, 플레이어는 닉네임 사전순으로 출력

Approach
    - heapq 사용해보자
        1. 방 생성(순서대로), 방 입장 가능 여부 생성(True/False)
        2. 방 순차적 비교, 기준 레벨과 +-10 비교해서 heappush
        3. 한번 넣을 때마다 방 정원 확인 후, 모두 찼다면 입장가능 False로 바꿈. 이후 계속 진행   
'''
from heapq import *


def input_data():
    p, m = map(int,input().split())
    user_list = []
    for _ in range(p):
        user_list.append(input().split()) # l, n이 순서대로 들어감
    return p, m, user_list


def enter_available(room_level:list, room_availability:list, l:int):
    for i in range(len(room_level)):
        if room_availability[i] and (room_level[i]-10 <= l <= room_level[i]+10):
            return True, i
    return False, -1


def enter_game(p:int, m:int, user_list:list[list]):
    room_level = [] # room_level = [방의 기준 레벨]
    room_list = [] # room_list = [[방에 참여해있는 사람들]]
    room_availability = []
    # 유저 입장 시작
    for i in range(p):
        l, n = user_list[i]
        l = int(l)
        # 방이 없거나, 입장할 수 있는 방이 없다면 새로 만듦
        updated_room_num = -1
        availability, room_num = enter_available(room_level, room_availability, l)
        if not room_list or not availability:
            room_level.append(l)
            room_list.append([])
            room_availability.append(True)
            heappush(room_list[-1], (n, l))
            updated_room_num = len(room_list)-1
        # 입장할 수 있으면 순서대로 입장시킨다
        else:
            heappush(room_list[room_num], (n,l))
            updated_room_num = room_num
        # update된 방 정원 확인 후, 입장 가능 여부를 수정해줌
        if len(room_list[updated_room_num]) == m:
            room_availability[updated_room_num] = False
    # 결과 return
    return room_list, room_availability


def main(p:int, m:int, user_list:list[list]):
    room_list, room_availability = enter_game(p, m, user_list)
    for i in range(len(room_list)):
        # 방 시작 여부 출력
        if room_availability[i]:
            print("Waiting!")
        else:
            print("Started!")
        # 방에 참여한 유저 출력
        for _ in range(len(room_list[i])):
            n, l = heappop(room_list[i])
            print(l, n)
    return



main(*input_data())