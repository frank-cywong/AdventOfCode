def proc(s):
    if(len(s) == 1):
        return int(ord(s) - ord('0'))
    sarr = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]
    for i in range(len(sarr)):
        if(s == sarr[i]):
            return int(i)
    return -1

import re
f = open("one_in.txt", "r")
c = f.readlines()
tot = 0
lc = 0
for s in c:
    ref = r"(?=(one|two|three|four|five|six|seven|eight|nine|[1-9]))"
    rl = re.findall(ref, s)
    #print(rl)
    f = rl[0]
    l = rl[-1]
    f = proc(f)
    l = proc(l)
    #print(f)
    #print(l)
    '''
    for c2 in s:
        if(ord('0') <= ord(c2) <= ord('9')):
            if(f == -1):
                f = ord(c2) - ord('0')
            l = ord(c2) - ord('0')
    '''
    if(f < 1 or f > 9 or l < 1 or l > 9):
        print("AHASDA")
        print(rl)
    if(10 * f + l > 99 or 10 * f + l < 10):
        print("AHHH")
        print(rl)
    
    tot += (10 * f + l)
    print(10 * f + l)
    lc += 1
print(lc)
print(tot)
