def solution(s):
    answer = 0
    is_x = True
    x_cnt = 0
    y_cnt = 0
    for alpha in s:
        if is_x: 
            x = alpha
            x_cnt +=1
            is_x = False
        else:
            if alpha == x:
                x_cnt +=1
            else:
                y_cnt +=1
        if x_cnt == y_cnt:
            answer +=1
            is_x = True
            x_cnt = 0
            y_cnt = 0
    if x_cnt != y_cnt:
        answer +=1
        
        
    return answer