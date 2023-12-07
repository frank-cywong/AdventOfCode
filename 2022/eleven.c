#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "vector.h"

#define OLD_DEF_MAGIC (-1001)

struct monkey{
	int index;
	vector *items;
	int divcheck;
	char op;
	int arg2;
	int targettrue;
	int targetfalse;
};

struct monkey * new_monkey(int index){
	struct monkey * o = calloc(1, sizeof(struct monkey));
	o->index = index;
	o->items = init_vector();
	return o;
}

int main(){
	char buff[256];
	printf("Input input file name:\n");
	fgets(buff, 256, stdin);
	buff[strlen(buff) - 1] = '\0';
	FILE * fs = fopen(buff, "r");
	int curmonkey = 0;
	struct monkey ** monkeys = malloc(sizeof(struct monkey *) * 10);
	for(int i = 0; i < 10; ++i){
		monkeys[i] = new_monkey(i);
	}
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
			if(buff[0] == 'M'){ // defining a new monkey
				sscanf(buff, "Monkey %d:", &curmonkey);
			} else {
				if(buff[2] == 'S'){ // starting items
					char temp[3];
					int index = 18;
					int sl = strlen(buff);
					int num;
					while(index < sl){
						strncpy(temp, buff + index, 2);
						temp[2] = '\0';
						sscanf(temp, "%d", &num);
						vadd(monkeys[curmonkey]->items, num);
						index += 4;
					}
				} else if (buff[2] == 'O'){ // operation
					char * tempp = buff + 23;
					monkeys[curmonkey]->op = *tempp;
					if(*(tempp + 2) == 'o'){ // 2nd arg is old
						monkeys[curmonkey]->arg2 = OLD_DEF_MAGIC;
					} else {
						int tempi;
						sscanf(tempp + 2, "%d", &tempi);
						monkeys[curmonkey]->arg2 = tempi;
					}
				} else if (buff[2] == 'T'){ // Test
					int tempi;
					sscanf(buff + 21, "%d", &tempi); // + 21
					monkeys[curmonkey]->divcheck = tempi;
					//printf("TEMPI: %d\n", tempi);
				} else {
					if(buff[7] == 't'){
						int tempi;
						sscanf(buff + 29, "%d", &tempi);
						monkeys[curmonkey]->targettrue = tempi;
					} else if (buff[7] == 'f'){
						int tempi;
						sscanf(buff + 30, "%d", &tempi);
						monkeys[curmonkey]->targetfalse = tempi;
					} else {
						printf("UNRECOGNIZED LINE: %s\n", buff);
					}
				}
			}
		}
	}
	int monkeycount = curmonkey + 1;
	for(int i = 0; i < monkeycount; ++i){
		vprint(monkeys[i]->items);
		printf("%d %d %d %d\n", monkeys[i]->divcheck, monkeys[i]->arg2, monkeys[i]->targettrue, monkeys[i]->targetfalse);
	}
	return 0;
}
