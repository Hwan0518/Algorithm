'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*10만)
    - 1<=N<=9
    - 입력되는 각 문자열의 길이는 4N-1

Question
    - 입력된 안내판에서 꺼져있는 전구들을 켜서 만들 수 있는, 모든 번호의 평균을 구하라
      어떠한 번호도 가능하지 않다면 -1을 출력하여라

Access
    try_1)
        - 예상 분류 : 구현
        - 켜져있는 전구들이 속해있는 번호를 고른 후,
          그 리스트로 만들 수 있는 모든 수를 만들어본다
        - 필요기능 : 가능한 수 찾기, 수 조합
            - 가능한 수 찾기 : dict를 활용해서, (입력에서 켜진전구 위치 in 숫자에서 켜진 전구 위치)가 False 라면 가능한 목록에 추가하지 않음
            - 수 조합 : 각 자리별로 가능한 숫자를 sum


다시구현하기
    - 입력에서 켜진 전구는 0~9에서 켜진 전구보다 작아도 상관 없지만
      더 많으면 안된다! 전구는 끌 수 없기 때문에
    1. 입력에서 #(켜진전구)의 위치를 기억한다
    2. 0~9에서 #(켜진전구)의 위치를 기억한다
    3. 이후, for 숫자 in range(10)에서 
       (입력_켜진전구) in (0~9_켜진전구) == False라면 
       >>> flag == False로 하고, 이후 break, 다음 숫자로 넘어간다
           flag의 첫 시작을 True로 놓고 시작
    4. flag == True라면 후보지에 10**(n-1)*숫자 를 추가해준다
'''
from sys import stdin
input = stdin.readline

#define function

def bulb_location():
    for i in range(10):
        bulb_nums[i] = []
    data = [
'###...#.###.###.#.#.###.###.###.###.###',
'#.#...#...#...#.#.#.#...#.....#.#.#.#.#',
'#.#...#.###.###.###.###.###...#.###.###',
'#.#...#.#.....#...#...#.#.#...#.#.#...#',
'###...#.###.###...#.###.###...#.###.###']
    for r in range(5):
        for i in range(10):
            for j in range(4*i,4*i+3):
                turn_on = data[r][j]
                if turn_on == '#':
                    bulb_nums[i].append((r,j%4))
    return

def input_data():
    for r in range(5):
        data = input().strip()
        for i in range(n):
            for j in range(4*i,4*i+3):
                turn_on = data[j]
                if turn_on == '#':
                    bulb_input[i].append((r,j%4))  
    return

def compare_bulb():
    for i in range(n):
        compare = bulb_input[i]
        for j in range(10):
            standard = bulb_nums[j]
            #입력받은 안내판의 켜진 전구 위치가, 각 숫자에서 켜진 전구의 위치안에 포함된다면
            #전구를 켜서 만들 수 있는 숫자이므로 후보지에 추가   
            flag = False
            for location in compare:
                if location not in standard:
                    flag = True
                    break
            if flag:
                continue
            candidate[i].append(j*(10**(n-i-1)))
    return

def solution():
    bulb_location()
    input_data()
    compare_bulb()
    result = 0
    for nums in candidate:
        if nums:
            result += sum(nums)/len(nums)
        else:
            return -1
    if result:
        return result

#main
n=int(input())
bulb_nums = {} # 각 숫자에서, 켜진 전구들의 위치를 담을 딕셔너리
bulb_input = [[] for _ in range(n)] # 입력받은 안내판에서, 켜진 전구들의 위치
candidate = [[] for _ in range(n)] # 각 자리수에서 가능한 숫자들을 담을 리스트
print(solution())
