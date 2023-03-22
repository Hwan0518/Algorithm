'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*100만)
    - 1<=n<=256 : 선수의 수
    - 부전승 여러번 가능

Question
    - 시합을 하는 선수들의 랭킹 차이의 총 합의 최솟값은?

Access
    - 예상 분류 : dp or 그리디
      실제 분류 : 그리디

    try_1)
        - 각 선수는 자신 양옆의 사람과 무조건 한번은 시합을 해야한다
          따라서 양옆 사람중 랭킹차이가 작은 사람과 시합을 해야한다
          이때 순서가 꼬이지 않도록 해야한다

            1. 랭킹이 높은 선수들이 부전승을 해야한다
               따라서 랭킹이 가장 낮은 순서대로 양옆의 사람들중 비슷한 사람과 경기를 진행
        >>>fail
    
    try_2)
        정답
    start : 1,62,5,3,4  :4
        >   1,2,53,4    :2
        >   1,2,34      :1
        >   1,23        :1
        >   12          :1

        노가다..
        <16,25,34>  12

        <1,62,53,4> 10

        <1,62,5,34> 10

        <1,6,25,34> 10
        1,6,25,34   4
        1,6,23      1
        1,62        4
        12          1

        <1,62,53,4> 10
        1,62,53,4   6
        12,34       2
        13          2

        <1,62,5,3,4>9
        1,62,5,3,4  4
        1,2,53,4    2
        1,2,34      1
        1,23        1
        12          1


        <16,2,53,4> 11
        16,2,53,4   7
        12,34       2
        13          2
        
        >>> 즉 가장 큰 수부터 차례대로 하나씩 진행한다
'''
from sys import stdin
input = stdin.readline

#define function
def game():
    reverseSorted = [i for i in range(n,0,-1)]
    rankDiff = 0
    
    for current in reverseSorted:
        if len(players) == 3:
            return rankDiff
        
        curIdx = players.index(current)
        leftIdx = curIdx-1
        rightIdx = curIdx+1
        
        rankDiff += (current-max(players[leftIdx],players[rightIdx]))
        del players[curIdx]
    



#main
n=int(input())
players = [-1]+list(map(int,input().split()))+[-1]
print(game())


