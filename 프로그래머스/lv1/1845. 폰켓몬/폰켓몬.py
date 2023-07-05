'''
Approach
    - n/2마리를 선택함
    - 같은 종류는 같은 번호
    - 최대한 다양한 종류를 가져야함

>>> 그냥 set으로 중복을 제거하면 될 것 같음
'''

def solution(nums):
    answer = 0
    selectCnt = len(nums)//2
    noDuplCnt = len(set(nums))
    if noDuplCnt > selectCnt:
        answer = selectCnt
    else:
        answer = noDuplCnt
    
    return answer