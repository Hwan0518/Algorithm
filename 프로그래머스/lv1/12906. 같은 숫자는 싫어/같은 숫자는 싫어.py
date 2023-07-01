'''
- 원소 0~9까지
- 연속적 숫자 하나만 남기고 전부 제거
- return값은 순서를 유지해서
- n= 100만
'''

def solution(arr):    
    answer=[arr[0]]
    for i in arr:
        if answer[-1] != i:
            answer.append(i)
    
    
    return answer