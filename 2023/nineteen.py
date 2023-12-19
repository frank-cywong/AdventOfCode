import math
import sys
import heapq

sys.setrecursionlimit(1000000)

# testing input?
TEST_IN = False

f_in_str = "nineteen_in.txt"
f_test_str = "nineteen_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

#remove newlines
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

def transpose(l1, l2):
 
    # we have nested loops in comprehensions
    # value of i is assigned using inner loop
    # then value of item is directed by row[i]
    # and appended to l2
    l2 =[[row[i] for row in l1] for i in range(len(l1[0]))]
    return l2

# in_range for 2d array arr_v
def in_range(c, r, arr_v):
    return (c >= 0 and r >= 0 and c < len(arr_v) and r < len(arr_v[c]))

# start actual code

procs = {}

def true_func(x):
    return True

def parse_instr(i):
    v = i.split("<")
    if(len(v) == 2):
        cmpv = int(v[1])
        arg = v[0]
        return (lambda x: x[arg] < cmpv)
    else:
        v = i.split(">")
        cmpv = int(v[1])
        arg = v[0]
        return (lambda x: x[arg] > cmpv)

procs = {}

i = 0
while True:
    if(c[i] != ""):
        q = c[i].split("{")
        procn = q[0]
        q = q[1]
        q = q[:-1]
        q = q.split(",")
        instrs = []
        for instr in q:
            temps = instr.split(":")
            if(len(temps) == 1):
                instrs.append([true_func, temps[0]])
            else:
                instrs.append([parse_instr(temps[0]), temps[1]])
        procs[procn] = instrs
    else:
        i = i + 1
        break
    i = i + 1

print(procs)

def proc_obj(o):
    c_proc = "in"
    while True:
        #print(f"{o}, {c_proc}")
        if(c_proc == "A"):
            return True
        if(c_proc == "R"):
            return False
        c_proc_instr = procs[c_proc]
        for instr in c_proc_instr:
            if((instr[0])(o)):
                c_proc = instr[1]
                break

cnt = 0

while(i < len(c)):
    objq = {}
    l = c[i]
    l = l[:-1]
    l = l.split(",")
    orgs = ["x", "m", "a", "s"]
    for p in range(len(l)):
        pv = l[p]
        tv = orgs[p]
        pv = int(pv.split("=")[1])
        objq[tv] = pv
    if(proc_obj(objq)):
        cnt += (objq["x"] + objq["m"] + objq["a"] + objq["s"])
    i = i + 1

print(f"Part 1: {cnt}")
