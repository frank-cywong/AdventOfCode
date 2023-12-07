
f = open("five_in.txt", "r")
c = f.readlines()

c = [l[:-1] for l in c]

seeds = c[0][7:].split(" ")
ranges = [(int(seeds[i]), int(seeds[i]) + int(seeds[i+1]) - 1) for i in range(0, len(seeds), 2)]
print(ranges)

seed_dict = {}
for v in ranges:
    seed_dict[v] = False

si = 2

def load_map(in_dict):
    global si
    i = si
    out_dict = {}
    while(i < len(c) and c[i] != ""):
        if c[i][-1] != ":":
            l = c[i].split(" ")
            dv = int(l[0])
            sv = int(l[1])
            iv = int(l[2])
            toinc = []
            for k,v in in_dict.items():
                if(v == None):
                    continue
                #print("DEBUG {}".format(k))
                
                if(k[0] < (sv + iv) and k[1] >= sv):
                    # some overlap
                    lb = sv
                    ub = sv + iv - 1
                    rbound = True
                    lbound = True
                    if(k[1] <= ub):
                        ub = k[1]
                        rbound = False
                    if(k[0] >= lb):
                        lb = k[0]
                        lbound = False
                    out_dict[(lb + dv - sv, ub + dv - sv)] = False
                    if(lbound):
                        toinc.append((k[0], lb-1))
                    if(rbound):
                        toinc.append((ub+1, k[1]))
                    in_dict[k] = None
                #if(int(v) >= sv and int(v) < (sv + iv)):
                #    out_dict[k] = int(v) + dv - sv
                    #print("moved {} from {} to {} based on rule {}".format(k, in_dict[k], out_dict[k], c[i]))
            for a in toinc:
                in_dict[a] = False
        i += 1
    for k,v in in_dict.items():
        if(v == False):
            out_dict[k] = False
    si = i + 1
    return out_dict

print(seed_dict)
cd = seed_dict
for i in range(7):
    cd = load_map(cd)
    print(cd)

mv = 100000000000
for k,v in cd.items():
    if(v != None and k[0] < mv):
        mv = k[0]

print("Part 2: {}".format(mv))