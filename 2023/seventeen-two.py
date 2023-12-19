import math
import sys
import heapq

sys.setrecursionlimit(100000)

# testing input?
TEST_IN = False

f_in_str = "seventeen_in.txt"
f_test_str = "seventeen_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

print("TEST")

#dp[x][y][dir][left]
#left = # of moves in that direction left

d_arr = {"N": 0, "E": 1, "S": 2, "W": 3}
d_to_offset = [[-1, 0], [0, 1], [1, 0], [0, -1]]

n_op = [[1, 3], [0, 2], [1, 3], [0, 2]]

def rev(d):
    d_rev = [2, 3, 0, 1]
    return d_rev[d]

dp = []
visited = [];
for i in range(len(c)):
    l = []
    l2 = []
    for j in range(len(c[0])):
        u = []
        u2 = []
        for k in range(4):
            u.append([1000000000000] * 10)
            u2.append([False] * 10)
        l.append(u)
        l2.append(u2);
    dp.append(l)
    visited.append(l2)

pq = []

# step-wise reverse all positions that lead here and update dp
def rev_dp(x, y, d, l):
    if(visited[x][y][d][l]):
        return
    visited[x][y][d][l] = True
    move_d = d_to_offset[rev(d)]
    prevx = x + move_d[0]
    prevy = y + move_d[1]
    if(prevx < 0 or prevy < 0 or prevx >= len(c) or prevy >= len(c[0])):
        return
    if(l == 9):
        # d must be different direction that can incoming (ie. turn)
        # any l works
        rev_d_arr = n_op[d]
        nvc = dp[x][y][d][l] + int(c[prevx][prevy])
        for rev_d in rev_d_arr:
            # must have at most 6 to turn
            for old_l in range(7):
                if(dp[prevx][prevy][rev_d][old_l] > nvc):
                    dp[prevx][prevy][rev_d][old_l] = nvc
                    heapq.heappush(pq, (nvc, [prevx, prevy, rev_d, old_l]))
                
    else:
        # d must be same dir
        # l + 1
        nvc = dp[x][y][d][l] + int(c[prevx][prevy])
        if(dp[prevx][prevy][d][l+1] > nvc):
            dp[prevx][prevy][d][l+1] = nvc
            heapq.heappush(pq, (nvc, [prevx, prevy, d, l + 1]))

rvend = int(c[len(c)-1][len(c[0])-1])

for d in range(4):
    for l in range(7):
        dp[len(c)-1][len(c[0])-1][d][l] = rvend
        heapq.heappush(pq, (rvend, [len(c)-1, len(c[0])-1, d, l]))

cpqv = 0

def check_vis():
    vismin = 1000000000000
    allvis = True
    for d in range(4):
        for l in range(9, 10):
            if not visited[0][1][d][l]:
                allvis = False
            else:
                vismin = min(vismin, dp[0][1][d][l])
            if not visited[1][0][d][l]:
                allvis = False
            else:
                vismin = min(vismin, dp[1][0][d][l])
    return(allvis or (vismin != 1000000000000 and vismin < cpqv))

while not check_vis():
    pq_extr = heapq.heappop(pq)
    #if(pq_extr[1][0] == 0 or pq_extr[1][1] == 10):
        #print(pq_extr)
    cpqv = pq_extr[0]
    pq_extr = pq_extr[1]
    #visited[pq_extr[0]][pq_extr[1]][pq_extr[2]][pq_extr[3]] = True
    rev_dp(pq_extr[0], pq_extr[1], pq_extr[2], pq_extr[3])

vismin = 1000000000000
vismin = min(vismin, dp[0][1][1][9], dp[1][0][2][9])

print(f"Part 2: {vismin}")
