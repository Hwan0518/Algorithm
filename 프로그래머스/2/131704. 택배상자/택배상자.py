'''
스택
'''
def solution(order):
    answer = 0
    parcel = [i for i in range(len(order), 0, -1)]
    stack = []
    o_i = 0
    cur_o = order[o_i]
    p = parcel.pop()
    while parcel:
        # 기존 벨트 소포의 차례인 경우
        if p == cur_o:
            o_i +=1
            cur_o = order[o_i]
            p = parcel.pop()
            answer +=1
        # 보조 벨트 소포의 차례인 경우
        elif stack and stack[-1] == cur_o:
            o_i +=1
            cur_o = order[o_i]
            stack.pop()
            answer +=1
        # 기존/보조 벨트 모두 아닌 경우
        else:
            stack.append(p)
            p = parcel.pop()
    # 마지막 p를 확인
    if p == cur_o:
        o_i +=1
        cur_o = order[o_i]
        answer +=1
    elif stack and stack[-1] == cur_o:
        o_i +=1
        cur_o = order[o_i]
        stack.pop()
        answer +=1
    else:
        stack.append(p)    
    # stack에 남은 소포들을 처분
    while stack and o_i < len(order):
        p = stack.pop()
        cur_o = order[o_i]
        if p == cur_o:
            o_i +=1
            answer +=1
        else:
            break
    return answer