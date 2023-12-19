import math
import sys
import heapq

sys.setrecursionlimit(1000000)

# testing input?
TEST_IN = False

f_in_str = "eighteen_in.txt"
f_test_str = "eighteen_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

print("TEST")

#cardinal directions

d_arr = {"U": 0, "R": 1, "D": 2, "L": 3}
d_to_offset = [[-1, 0], [0, 1], [1, 0], [0, -1]]

n_op = [[1, 3], [0, 2], [1, 3], [0, 2]]

# returns opposite of a direction
def rev(d):
    d_rev = [2, 3, 0, 1]
    return d_rev[d]

# recursive flood fill on array c
def flood_fill(a, b, c):
    if(a < 0 or a >= len(c) or b < 0 or b >= len(c[0])):
        return
    if(c[a][b] != 0):
        return
    c[a][b] = 2 # out area
    adj = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    for d in adj:
        td = d
        na = a + td[0]
        nb = b + td[1]
        flood_fill(na, nb, c)

points = []
x = 0
y = 0
x_min = 0
x_max = 0
y_min = 0
y_max = 0
points.append([0, 0])
for l in c:
    c_dir = l[0]
    c_amt = int(l.split(" ")[1])
    nx = x + c_amt * d_to_offset[d_arr[c_dir]][0]
    ny = y + c_amt * d_to_offset[d_arr[c_dir]][1]
    if(nx > x_max):
        x_max = nx
    if(ny > y_max):
        y_max = ny
    if(ny < y_min):
        y_min = ny
    if(nx < x_min):
        x_min = nx
    for t in range(1, c_amt + 1):
        points.append([x + t * d_to_offset[d_arr[c_dir]][0], y + t * d_to_offset[d_arr[c_dir]][1]])
    x = nx
    y = ny

#print(f"{x_min}, {y_min}, {x_max}, {y_max}")

board = []
for i in range(x_max - x_min + 1):
    board.append([0] * (y_max - y_min + 300))

for pt in points:
    pt[0] -= x_min
    pt[1] -= y_min
    if(pt[0] < 0 or pt[1] < 0):
        print(f"BAD {pt}")
    #print(f"{x_max - x_min + 1}, {y_max - y_min + 1}, {pt[0]}, {pt[1]}")
    board[pt[0]][pt[1]] = 1

#print(board)

for i in range(len(board)):
    flood_fill(i, 0, board)
    flood_fill(i, len(board[0]) - 1, board)
for i in range(len(board[0])):
    flood_fill(0, i, board)
    flood_fill(len(board) - 1, i, board)

print(board)
cnt = 0
for i in board:
    for j in i:
        if(j == 0 or j == 1):
            cnt += 1

print(f"Part 1: {cnt}")