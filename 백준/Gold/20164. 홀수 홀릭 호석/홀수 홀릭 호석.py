'''
Condition
    - TL :1s
    - 연산
        1. 각 단계에서 홀수의 개수를 종이에 적음
        2-1. 한 자리 수라면 종료
        2-2. 두 자리라면, 2개로 나눠서 합을 구하여 새로운 수로 만듦
        2-3. 세 자리 이상이면, 임의의 위치에서 끊어서 3개의 수로 분할, 3개를 더한 값을 새로운 수로 만듦
        3. 연산이 끝나면 적힌 수들을 모두 더한다 

Goals
    - 만들 수 있는 최종값 중, 최솟값과 최댓값은?

Approach
    - 백트래킹
        - n이 최대 999,999,999 = 9자리 수
        - 따라서 TC = O(9C2 * 8C2 * 7C2 * 6C2 * 5C2 * 4C2) = O(36 * 28 * 21 * 15 * 10 * 6) = O(1900만)
            - 통과
'''
def is_odd(num:int):
    return num%2 == 1

def odd_cnt(num:int):
    cnt = 0
    for n in str(num):
        if is_odd(int(n)):
            cnt +=1
    return cnt

def dfs(l:int, cnt:int, num:int):
    global min_v, max_v
    # 종료 조건
    if l == 1:
        min_v, max_v = min(min_v, cnt), max(max_v, cnt)
        return
    # 백트래킹
    s_num = str(num)
    if l >= 4:
        for i1 in range(1, l-2):
            for i2 in range(i1+1, l-1):
                new_num = int(s_num[:i1]) + int(s_num[i1:i2]) + int(s_num[i2:])
                dfs(len(str(new_num)), cnt + odd_cnt(new_num), new_num)
    elif l == 3:
        new_num = int(s_num[0]) + int(s_num[1]) + int(s_num[2])
        dfs(len(str(new_num)), cnt + odd_cnt(new_num), new_num)
    else:
        new_num = int(s_num[0]) + int(s_num[1])
        dfs(len(str(new_num)), cnt + odd_cnt(new_num), new_num)
    return

def main():
    global min_v, max_v
    min_v, max_v = 1e9, -1
    num = int(input())
    dfs(len(str(num)), odd_cnt(num), num)
    return min_v, max_v

print(*main())