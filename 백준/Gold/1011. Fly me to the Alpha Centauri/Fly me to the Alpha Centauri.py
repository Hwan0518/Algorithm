'''
Approach
    - https://newtoner.tistory.com/51
    - 규칙이 존재
        - 거리 차이           = 1 2 3 4 5 6 7 8 9 10 ...
        - 작동 횟수           = 1 2 3 3 4 4 5 5 5 6 6 6 7 7 7 7 8 8 8 8 ... 
        - 반복 횟수           = 1-1-2---2---3-----3-----4-----4--------  
        - '거리 차이'가 어떤 '반복횟수 범위'에 존재하는지 찾으면 됨
        - 작동 횟수가 홀수 = 반복횟수 +1 증가시켜줌 
'''

t = int(input())
for _ in range(t):
    x, y = map(int, input().split())
    distance = y - x    # y와 x사이의 거리
    move = 0            # 이동 거리
    repeat_cnt = 0      # 반복 횟수
    cnt = 0             # 공간 장치 작동 횟수

    while move < distance:
        cnt += 1
        if cnt % 2 != 0:
            repeat_cnt += 1
        move += repeat_cnt
    print(cnt)