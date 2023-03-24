'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*100만)
    - 1<=w,h<=10^9
      0<=f<=w
      0<=c<=1000
      c+1은 h의 약수 == 정수로 떨어짐
      0<=x1<=x2<=max(f,w-f)
      0<=y1<=y2<=h/(c+1)


Question
    - 

Access
    - 예상 분류 : 구현
      실제 분류 : 구현, 수학

    try_1)
        - 색칠된 면적 = 겹칠때,안겹칠떄로 나뉨
            1. 겹칠때 = x2가 (x+x)보다 작을때
                : 면적 = 2*(c+1)*(x2-x1)*(y2-y1)
            2. 안겹칠때
                : 면적 = (c+1)*((h/(c+1))*(w-x) - (x2-x1)*(y2-y1))        
        
        색칠된 면적 = (c+1)*(max(2*f,x2)-min(2*f,x2))*(y2-y1) 
                 + (c+1)*(min(2*f,x2)-x1)*(y2-y1)
        
        
'''
from sys import stdin
input = stdin.readline

#define function
def solution(x1,x2,f):
    f=min(f,w-f)   
    x1 +=f
    x2 +=f
    
    # 2*f가 x2 이상인 경우 = 전체가 겹치는 경우
    if 2*f >= x2:
        s = 2*(c+1)*(x2-x1)*(y2-y1)
    
    # 2*f가 x1초과 x2미만인 경우 = 일부만 겹치는 경우
    elif x1<2*f<x2:
        s = (c+1)*(max(2*f,x2)-min(2*f,x2))*(y2-y1)\
           +(c+1)*(min(2*f,x2)-x1)*(y2-y1)*2
    
    # 2*f가 x1이하인 경우 = 겹치지 않는 경우
    else:
        s = (c+1)*(x2-x1)*(y2-y1)

    return w*h-s

#main
w,h,f,c,x1,y1,x2,y2 = map(int,input().split())
print(solution(x1,x2,f))