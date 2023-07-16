'''
Approach
    - 노랑,갈색 격자수가 주어질대 카펫의 가로,세로 길이를 return해라
      이때 가로>=세로
    
>>> 노란색 가로,세로를 정한다(카펫은 노란색 가로세로에 +2씩)
    : 하나씩 다해본다
'''
def solution(brown, yellow):
    answer = []
    if yellow == 1:
        cnt = (yellow+brown)**0.5
        answer.extend([cnt, cnt])
        return answer

    y_length = 1
    y_width = yellow
    cnt_brown = 2*(y_width+2) + 2*(y_length)
    while cnt_brown != brown or y_width*y_length != yellow:
        y_width -= 1
        y_length = yellow//y_width
        cnt_brown = 2*(y_width+2) + 2*(y_length)
    answer.extend([y_width+2,y_length+2])
    
    return answer