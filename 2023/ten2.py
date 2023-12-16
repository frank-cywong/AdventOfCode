import math
import sys


sys.setrecursionlimit(100000)

# testing input?
TEST_IN = False

f_in_str = "ten_in.txt"
f_test_str = "ten_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

cc2 = []

for l in c:
    block1 = ""
    block2 = ""
    block3 = ""
    for s in l:
        if(s == "F"):
            block1 += "..."
            block2 += ".F-"
            block3 += ".|."
        if(s == "J"):
            block1 += ".|."
            block2 += "-J."
            block3 += "..."
        if(s == "7"):
            block1 += "..."
            block2 += "-7."
            block3 += ".|."
        if(s == "L"):
            block1 += ".|."
            block2 += ".L-"
            block3 += "..."
        if(s == "|"):
            block1 += ".|."
            block2 += ".|."
            block3 += ".|."
        if(s == "-"):
            block1 += "..."
            block2 += "---"
            block3 += "..."
        if(s == "S"):
            block1 += ".|."
            block2 += "-S-"
            block3 += ".|."
        if(s == "."):
            block1 += "..."
            block2 += "..."
            block3 += "..."
    cc2.append(block1)
    cc2.append(block2)
    cc2.append(block3)
    print(block1)
    print(block2)
    print(block3)

c = cc2
print(c)

v = [[cc for cc in l] for l in c]

start_r = -1;
start_c = -1;

for i in range(len(c)):
    for j in range(len(v[0])):
        #print(f"DEBUG {i}, {j}")
        if(v[i][j] == "S"):
            start_r = i
            start_c = j
            break
    if(start_r >= 0):
        break

print(f"S: {start_r}, {start_c}")

char_to_dirs = {"F" : 3, "J": 12, "7": 9, "L": 6, "|": 5, "-": 10}

dir_to_bin = {"SOUTH" : 1, "EAST": 2, "NORTH": 4, "WEST": 8}

dir_to_change = {1: [1, 0], 2: [0, 1], 4: [-1, 0], 8: [0, -1]}

def opposite(x):
    ox = {1: 4, 4: 1, 2: 8, 8: 2}
    return ox[x]

looped = [[0 for cc in l] for l in c]

def graph_trav(r, c2, enter_dir):
    #print(f"TESTING {r}, {c2}");
    if(r < 0 or r >= len(c) or c2 < 0 or c2 >= len(v[0])):
        return -1 # OOB
    lc = v[r][c2]
    if(lc == "S"):
        return 1
    dir_col = char_to_dirs[lc]
    if(dir_col & enter_dir == 0):
        return -1 # bad
    new_dir = dir_col ^ enter_dir # xor
    amt = dir_to_change[new_dir]
    return (graph_trav(r + amt[0], c2 + amt[1], opposite(new_dir)) + 1)

def graph_trav_mark(r, c2, enter_dir):
    #print(f"TESTING MARK {r}, {c2}");
    if(r < 0 or r >= len(c) or c2 < 0 or c2 >= len(v[0])):
        return -1 # OOB
    looped[r][c2] = 1
    lc = v[r][c2]
    if(lc == "S"):
        return 1
    dir_col = char_to_dirs[lc]
    if(dir_col & enter_dir == 0):
        return -1 # bad
    new_dir = dir_col ^ enter_dir # xor
    amt = dir_to_change[new_dir]
    return (graph_trav_mark(r + amt[0], c2 + amt[1], opposite(new_dir)) + 1)

def graph_trav_lim(r, c2, enter_dir, l):
    print(f"TRAV {r}, {c2}");
    if(l == 0):
        return [r, c2]
    if(r < 0 or r >= len(c) or c2 < 0 or c2 >= len(v[0])):
        return -1 # OOB
    lc = v[r][c2]
    #if(lc == "S"):
    #    return 1
    dir_col = char_to_dirs[lc]
    if(dir_col & enter_dir == 0):
        return -1 # bad
    new_dir = dir_col ^ enter_dir # xor
    amt = dir_to_change[new_dir]
    return graph_trav_lim(r + amt[0], c2 + amt[1], opposite(new_dir), l - 1)

#print(v)

ds = [1, 2, 4, 8]

cs = [-1, -1]

for dir_test in ds:
    v1 = graph_trav(start_r + dir_to_change[dir_test][0], start_c + dir_to_change[dir_test][1], opposite(dir_test))
    if(v1 != -1):
        opposite_pos = v1 / 2
        print("Part 1: {}".format(opposite_pos))
        graph_trav_mark(start_r + dir_to_change[dir_test][0], start_c + dir_to_change[dir_test][1], opposite(dir_test))
        #cs = graph_trav_lim(start_r + dir_to_change[dir_test][0], start_c + dir_to_change[dir_test][1], opposite(dir_test), opposite_pos)
        break

#print(looped)

def flood_fill(a, b):
    if(a < 0 or a >= len(c) or b < 0 or b >= len(v[0])):
        return
    if(looped[a][b] != 0):
        return
    looped[a][b] = 2
    adj = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    for d in adj:
        td = d
        na = a + td[0]
        nb = b + td[1]
        flood_fill(na, nb)

for i in range(len(c)):
    flood_fill(i, 0)
    flood_fill(i, len(c[0]) - 1)
for i in range(len(c[0])):
    flood_fill(0, i)
    flood_fill(len(c) - 1, i)

sv = 0

for i in range(1, len(c), 3):
    s = ""
    for j in range(1, len(v[0]), 3):
        if(looped[i][j] == 0):
            sv += 1
        s += str(looped[i][j])
    print(s)

#print(looped)

#v1 = graph_trav(start_r, start_c + 1, 8)
#print(graph_trav(start_r, start_c + 1, 8))

#print("Part 1: {}".format(cs))

print("Part 2: {}".format(sv))