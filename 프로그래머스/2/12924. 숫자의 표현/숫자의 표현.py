# 투포인터
def solution(n):
    # 자기 자신을 포함하는 1개로 시작
    answer = 1
    stt, end = 1, 2
    summ = 1
    # 탐색
    while stt < end and end <= n:
        if summ < n:
            summ += end
            end +=1
            continue
        elif summ == n:
            answer +=1
        summ -= stt
        stt += 1
    # 결과
    return answer