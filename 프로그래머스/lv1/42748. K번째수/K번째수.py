'''
- i부터 j까지 잘랐을 때, k번째 있는 수를 구한다
    1. i부터 j까지 자른다 = [i-1:j]
    2. 자른 배열을 정렬한다
    3. k번째 수를 구한다 = arr[k-1]  
'''

def solution(array, commands):
    answer = []
    # commands 실행
    for command in commands:
        i,j,k = command
        newArr = array[i-1:j]
        newArr.sort()
        answer.append(newArr[k-1])
    
    
    
    return answer