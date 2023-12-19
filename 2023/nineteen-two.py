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

# range specs: r["x", "m", "a", "s"] -> [min, max] (incl.)

def cpd(o):
    return {"x": o["x"][:], "m": o["m"][:], "a": o["a"][:], "s": o["s"][:]}

def ran_split(x, instr):
    # returns [cont range, pass range, redir]
    i = instr.split(":")
    if(len(i) == 1):
        return [None, x, i[0]]
    redir = i[1]
    #print(f"REDIR: {redir}")
    i = i[0]
    v = i.split("<")
    if(len(v) == 2):
        cmpv = int(v[1])
        arg = v[0]
        dv = x[arg]
        if(dv[0] >= cmpv):
            # everything fails instr
            return [x, None, None]
        if(dv[1] < cmpv):
            # everything passes instr
            return [None, x, redir]
        q = cpd(x)
        # pass q
        q[arg] = [q[arg][0], cmpv - 1]
        # fail u
        u = cpd(x)
        u[arg] = [cmpv, u[arg][1]]
        return [u, q, redir]
    else:
        v = i.split(">")
        cmpv = int(v[1])
        arg = v[0]
        dv = x[arg]
        if(dv[1] <= cmpv):
            # everything fails instr
            return [x, None, None]
        if(dv[0] > cmpv):
            # everything passes instr
            return [None, x, redir]
        q = cpd(x)
        # fail q
        q[arg] = [q[arg][0], cmpv]
        # pass u
        u = cpd(x)
        u[arg] = [cmpv + 1, u[arg][1]]
        return [q, u, redir]
    

procs = {}

i = 0
while True:
    if(c[i] != ""):
        q = c[i].split("{")
        procn = q[0]
        q = q[1]
        q = q[:-1]
        q = q.split(",")
        '''
        instrs = []
        for instr in q:
            temps = instr.split(":")
            if(len(temps) == 1):
                instrs.append([true_func, temps[0]])
            else:
                instrs.append([parse_instr(temps[0]), temps[1]])
        '''
        procs[procn] = q
    else:
        i = i + 1
        break
    i = i + 1

print(procs)

queue_to_proc = []
s = ["x", "m", "a", "s"]
o = {}
for v in s:
    o[v] = [1, 4000]
queue_to_proc.append([o, "in"])

cnt = 0

def proc_obj(o, sp):
    global cnt
    c_proc = sp
    qvd = cpd(o)
    instrs = procs[c_proc]
    for instr in instrs:
        rvq = ran_split(qvd, instr)
        #print(f"RET RVQ: {rvq}")
        qvd = rvq[0]
        redir = rvq[2]
        if(rvq[1] != None):
            if(redir == "R"):
                pass
            elif (redir == "A"):
                pvqs = 1
                for ds in "xmas":
                    pvqs *= (rvq[1][ds][1] - rvq[1][ds][0] + 1)
                cnt += pvqs
            else:
                print(f"{rvq[1]}, {redir}")
                queue_to_proc.append([rvq[1], redir])
        if qvd == None:
            break

while(len(queue_to_proc) > 0):
    v = queue_to_proc.pop(0)
    proc_obj(v[0], v[1])

print(f"Part 2: {cnt}")

'''
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
'''
