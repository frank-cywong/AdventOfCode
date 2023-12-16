import math

# testing input?
TEST_IN = False

f_in_str = "seven_in.txt"
f_test_str = "seven_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

'''
types:
7 5 ALL
6 4 ALL
5 3, 2
4 3, 1, 1
3 2, 2, 1
2 2, 1, 1, 1
1 5 1 dist
'''

def get_type_char(s):
    freq_dict = {}
    j_c = 0
    for c in s:
        if(c == "J"):
            j_c += 1
            continue
        if c in freq_dict:
            freq_dict[c] += 1
        else:
            freq_dict[c] = 1
    #print(freq_dict)
    vals = [v for v in freq_dict.values()]
    vals.sort(reverse=True)
    if(len(vals) == 0):
        return "7"
    vals[0] += j_c
    #print(vals)
    if(vals[0] == 5):
        return "7"
    if(vals[0] == 4):
        return "6"
    if(vals[0] == 3):
        if(vals[1] == 2):
            return "5"
        return "4"
    if(vals[0] == 2):
        if(vals[1] == 2):
            return "3"
        return "2"
    return "1"

v = []

def c_map(c):
    if(c == "T"):
        return "A"
    if(c == "J"):
        return "1"
    if(c == "Q"):
        return "C"
    if(c == "K"):
        return "D"
    if(c == "A"):
        return "E"
    return c

def proc_map(s):
    d = ""
    for c in s:
        d += c_map(c)
    return d

for s in c:
    s0 = s.split(" ")
    s1 = s0[1]
    s0 = s0[0]
    #print(s0)
    print(get_type_char(s0))
    s2 = get_type_char(s0) + proc_map(s0)
    v.append([s2, int(s1)])

v.sort(key=(lambda x : x[0]))
print(v)

mult = 0
ind = 1
for s in v:
    mult += s[1] * ind
    ind += 1

print("Part 2: {}".format(mult))