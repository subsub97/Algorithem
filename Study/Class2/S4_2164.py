from collections import deque
import sys

n = int(sys.stdin.readline().strip())

card_queue = deque()

for i in range(1,n+1):
    card_queue.append(i)

cnt = 0
while len(card_queue) != 1:
    cnt += 1
    if cnt % 2== 0:
        num = card_queue.popleft()
        card_queue.append(num)
    else:
        card_queue.popleft()

print(card_queue[0])






