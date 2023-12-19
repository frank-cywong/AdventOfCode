import math
import sys
import heapq

sys.setrecursionlimit(1000000)

# testing input?
TEST_IN = True

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

edge_count = 0

for l in c:
    conv = ["R", "D", "L", "U"]
    c_dir = conv[int(l[-2])]
    c_amt = int(l[-7:-2], 16)
    print(c_amt)
    #c_dir = l[0]
    #c_amt = int(l.split(" ")[1])
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
    points.append([nx, ny])
    edge_count += c_amt
    x = nx
    y = ny

x_vals = []

for pt in points:
    pt[0] -= x_min
    pt[1] -= y_min
    if(pt[0] not in x_vals):
        x_vals.append(pt[0])

x_vals.sort()

range_lines = {}

prev = None
for p in x_vals:
    if prev != None:
        range_lines[str(prev)+","+str(p)] = []
    prev = p

print(x_vals)

prev = None
for pt in points:
    if prev != None:
        print(f"{prev}, {pt}")
        if(prev[0] != pt[0]):
            # x val changed
            cx = prev[0]
            nx = pt[0]
            if(nx < cx):
                t = cx
                cx = nx
                nx = t
            ind1 = x_vals.index(cx)
            ind2 = x_vals.index(nx)
            for i in range(ind1, ind2):
                range_lines[str(x_vals[i])+","+str(x_vals[i+1])].append(prev[1])
    prev = pt

in_cnt = 0
print(range_lines)
pk0 = None
dv_cnt = 0
for k,v in range_lines.items():
    k0 = k.split(",")
    k1 = int(k0[1])
    k0 = int(k0[0])
    dx = k1 - k0 - 1
    v.sort()
    print(f"{k, v}")
    if(len(v) % 2 == 1):
        print(f"ODD BAD {v}")
    for i in range(0, len(v), 2):
        v0 = v[i]
        v1 = v[i+1]
        dv = v1 - v0 - 1
        in_cnt += dx * dv
    if(pk0 != None):
        v_old = range_lines[str(pk0)+","+str(k0)]
        q = v[:]
        q.extend(v_old)
        q.sort()
        q = list(set(q))
        print(q)
        pvv = None
        il = False
        ir = False
        print(v_old)
        print(v)
        for i in range(len(q)):
            if(q[i] in v_old):
                il = not il
            if(q[i] in v):
                ir = not ir
            if(pvv != None):
                print(f"Bound: {k0}, i {i} ({q[i]}, {pvv}); ({il}, {ir})")
                if(il and ir):
                    print(f"Bound: {k0}, add {(q[i+1] - q[i] - 1)} ({q[i]}, {pvv})")
                    dv_cnt += (q[i+1] - q[i] - 1)
            pvv = q[i]
    pk0 = k0

print(f"Part 2: {edge_count + in_cnt + dv_cnt} ({edge_count}, {in_cnt}, {dv_cnt})")