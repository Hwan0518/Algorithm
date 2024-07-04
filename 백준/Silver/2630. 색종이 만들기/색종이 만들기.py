#재귀를 사용
from sys import stdin
N= int(input())
mapp=[]
for _ in range(N):
    mapp.append(list(map(int,stdin.readline().split())))

c_white = 0
c_blue = 0
def fcn(x,y,N):
    global c_white, c_blue
    current = mapp[x][y]
    for row in range(x,x+N):
        for col in range(y,y+N):
            if mapp[row][col] != current:
                fcn(x,y,N//2)
                fcn(x+N//2,y,N//2)
                fcn(x,y+N//2,N//2)
                fcn(x+N//2,y+N//2,N//2)
                return
    if current == 1:
        c_blue +=1
    else:
        c_white +=1
    return 

fcn(0,0,N)
print(c_white)
print(c_blue)
