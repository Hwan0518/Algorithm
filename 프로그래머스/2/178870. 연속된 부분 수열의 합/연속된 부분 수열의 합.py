'''
Conditions
    - 수열은 연속되어야 한다
    - 조건
        - 수열의 합은 k
        - 가장 짧은 수열
        - 시작 인덱스가 작은 수열
        
Goals
    - 조건을 만족하는 수열에서 [시작인덱스, 마지막인덱스]를 return

Approach
    - 투포인터로 해보자
'''
def solution(sequence, k):
    s,e,summ = 0,0,sequence[0]
    size = len(sequence)
    min_s,min_e = -1, size
    # 투포인터 시작
    for s in range(size):
        # summ이 k보다 크거나 같을때까지 e를 이동
        while e < size-1 and summ <k:
            e +=1
            summ += sequence[e]
        # summ == k일 때
        if summ == k:
            new_l = e-s+1
            cur_l = min_e-min_s+1
            if (new_l < cur_l) or (new_l == cur_l and s < min_s) :
                min_s, min_e = s, e
        # s만큼 이동
        summ -= sequence[s]
    # 결과 return
    return [min_s,min_e]