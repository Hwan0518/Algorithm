def main():
    import sys
    input = sys.stdin.read
    data = input().split('\n')
    
    P, M = map(int, data[0].split())
    players = []
    
    for i in range(1, P + 1):
        if data[i]:
            level, nickname = data[i].split()
            players.append((int(level), nickname))
    
    rooms = []
    
    for level, nickname in players:
        placed = False
        for room in rooms:
            if room[0] - 10 <= level <= room[0] + 10 and len(room[1]) < M:
                room[1].append((level, nickname))
                placed = True
                break
        
        if not placed:
            rooms.append((level, [(level, nickname)]))
    
    for room in rooms:
        if len(room[1]) == M:
            print("Started!")
        else:
            print("Waiting!")
        for player in sorted(room[1], key=lambda x: x[1]):
            print(f"{player[0]} {player[1]}")

if __name__ == "__main__":
    main()
