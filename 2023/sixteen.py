import math
import sys

#sys.setrecursionlimit(100000)

# testing input?
TEST_IN = False

f_in_str = "sixteen_in.txt"
f_test_str = "sixteen_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

print("TEST")

def transpose(l1, l2):
 
    # we have nested loops in comprehensions
    # value of i is assigned using inner loop
    # then value of item is directed by row[i]
    # and appended to l2
    l2 =[[row[i] for row in l1] for i in range(len(l1[0]))]
    return l2

b = []
for s in c:
    b.append([False] * len(s))

# number of laser states = board tiles * 4 for dir 
repcount = len(c) * len(c[0]) * 4 + 5

beams = []



c_move = {"R": [0, 1], "L": [0, -1], "U": [-1, 0], "D": [1, 0]}

dd = {"R": "U", "D": "L", "L": "D", "U": "R"}
dd2 = {"R": "D", "D": "R", "L": "U", "U": "L"}

prev = []

def in_range(x):
    if((str(x[0]) + "." + str(x[1]) + "." + str(x[2])) not in prev):
        prev.append((str(x[0]) + "." + str(x[1]) + "." + str(x[2])))
        return(x[0] >= 0 and x[0] < len(c) and x[1] >= 0 and x[1] < len(c[0]));
    return False

def test_x(i, j, d):
    global b
    global prev
    global beams
    b = []
    for s in c:
        b.append([False] * len(s))
    beams = []
    prev = []
    beams.append([i, j, d])

    for cntv in range(repcount):
        newbeams = []
        #print(cntv)
        for beam in beams:
            x = beam[0]
            y = beam[1]
            b[x][y] = True
            if(c[x][y] == "."):
                dx = c_move[beam[2]][0]
                dy = c_move[beam[2]][1]
                newbeam = [x + dx, y + dy, beam[2]]
                if(in_range(newbeam)):
                    newbeams.append(newbeam)
            elif (c[x][y] == "/"):
                nd = dd[beam[2]]
                dx = c_move[nd][0]
                dy = c_move[nd][1]
                newbeam = [x + dx, y + dy, nd]
                if(in_range(newbeam)):
                    newbeams.append(newbeam)
            elif (c[x][y] == "\\"):
                nd = dd2[beam[2]]
                dx = c_move[nd][0]
                dy = c_move[nd][1]
                newbeam = [x + dx, y + dy, nd]
                if(in_range(newbeam)):
                    newbeams.append(newbeam)
            elif (c[x][y] == "|"):
                if(beam[2] in ["U", "D"]):
                    dx = c_move[beam[2]][0]
                    dy = c_move[beam[2]][1]
                    newbeam = [x + dx, y + dy, beam[2]]
                    if(in_range(newbeam)):
                        newbeams.append(newbeam)
                else:
                    bds = ["U", "D"]
                    for ddd in bds:
                        nd = ddd
                        dx = c_move[nd][0]
                        dy = c_move[nd][1]
                        newbeam = [x + dx, y + dy, nd]
                        if(in_range(newbeam)):
                            newbeams.append(newbeam)
            elif (c[x][y] == "-"):
                if(beam[2] in ["L", "R"]):
                    dx = c_move[beam[2]][0]
                    dy = c_move[beam[2]][1]
                    newbeam = [x + dx, y + dy, beam[2]]
                    if(in_range(newbeam)):
                        newbeams.append(newbeam)
                else:
                    bds = ["L", "R"]
                    for ddd in bds:
                        nd = ddd
                        dx = c_move[nd][0]
                        dy = c_move[nd][1]
                        newbeam = [x + dx, y + dy, nd]
                        if(in_range(newbeam)):
                            newbeams.append(newbeam)
        beams = newbeams
        if(len(beams) == 0):
            break
    cntc = 0
    for s in b:
        for x in s:
            if(x):
                cntc += 1
    print(cntc)
    return cntc

print(f'Part 1: {test_x(0, 0, "R")}')

mvt = 0
for i in range(len(c)):
    print(i)
    tv = test_x(i, 0, "R");
    if(tv > mvt):
        mvt = tv;
    tv = test_x(i, len(c[0]) - 1, "L");
    if(tv > mvt):
        mvt = tv;

for j in range(len(c[0])):
    print(j)
    tv = test_x(0, j, "D");
    if(tv > mvt):
        mvt = tv;
    tv = test_x(len(c) - 1, j, "U");
    if(tv > mvt):
        mvt = tv;

print(f"Part 2:{mvt}")
