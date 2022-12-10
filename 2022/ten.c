#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define INSTR_NOOP (1)
#define INSTR_ADDX (2)

int main(){
	char buff[256];
	printf("Input input file name:\n");
	fgets(buff, 256, stdin);
	buff[strlen(buff) - 1] = '\0';
	FILE * fs = fopen(buff, "r");
	int cyc = 1;
	char instrtype = 0;
	int regX = 1;
	int output = 0;
	char screen[6][40];
	memset(screen, '.', 240);
	while(fgets(buff, 256, fs)){
		//printf("test\n");
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
			int instrcycs = 1;
			instrtype = 0;
			while(instrcycs > 0){
				//printf("test2\n");
				// start of cycle, read instr
				if(!instrtype){
					if(strncmp(buff, "noop", 4) == 0){
						//printf("NOOP\n");
						instrcycs = 1;
						instrtype = INSTR_NOOP;
					} else if (strncmp(buff, "addx", 4) == 0){
						//printf("ADDX\n");
						instrcycs = 2;
						instrtype = INSTR_ADDX;
					}
					if(!instrtype){
						break;
					}
				}
				//printf("INSTR TYPE: %d\n", instrtype);
				// during cycle, check for output
				/*
				if((cyc % 40) == 20){
					//printf("ADDING TO OUTPUT\n");
					printf("REGX: %d\n", regX);
					output += regX * cyc;
				}
				*/
				int cX = ((cyc - 1) % 40);
				if(cX == regX - 1 || cX == regX || cX == regX + 1){
					int cY = (cyc - 1) / 40;
					screen[cY][cX] = '#';
				}
				// end of cycle, run instr if correct timing
				--instrcycs;
				if(instrcycs == 0){
					//printf("test\n");
					if(instrtype == INSTR_ADDX){
						//printf("test2\n");
						int arg;
						sscanf(buff, "addx %d", &arg);
						regX += arg;
						//printf("ADDED %d TO REGX AT END OF CYC %d\n", arg, cyc);
					}
				}
				++cyc;
			}
		}
	}
	//printf("OUTPUT: %d\n", output);
	for(int i = 0; i < 6; ++i){
		printf("%.40s\n", screen[i]);
	}
	return 0;
}
