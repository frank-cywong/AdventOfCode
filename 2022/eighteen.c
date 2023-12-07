#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void floodfill(char *** l, int i, int j, int k){
	//printf("i: %d, j: %d, k: %d\n", i, j, k);
	if(l[i][j][k] == 0){
		l[i][j][k] = -1;
	} else {
		return;
	}
	if(i > 0 && l[i-1][j][k] == 0){
		floodfill(l, i-1, j, k);
	}
	if(j > 0 && l[i][j-1][k] == 0){
		floodfill(l, i, j-1, k);
	}
	if(k > 0 && l[i][j][k-1] == 0){
		floodfill(l, i, j, k-1);
	}
	if(i < 24 && l[i+1][j][k] == 0){
		floodfill(l, i+1, j, k);
	}
	if(j < 24 && l[i][j+1][k] == 0){
		floodfill(l, i, j+1, k);
	}
	if(k < 24 && l[i][j][k+1] == 0){
		floodfill(l, i, j, k+1);
	}
}

int main(){
	char buff[256];
	printf("Input input file name:\n");
	fgets(buff, 256, stdin);
	buff[strlen(buff) - 1] = '\0';
	FILE * fs = fopen(buff, "r");
	char *** lava;
	lava = malloc(25 * sizeof(char **));
	int cubes = 0;
	for(int i = 0; i < 25; ++i){
		lava[i] = malloc(25 * sizeof(char *));
		for(int j = 0; j < 25; ++j){
			lava[i][j] = malloc(25);
			for(int k = 0; k < 25; ++k){
				lava[i][j][k] = 0;
			}
		}
	}
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
			int tx, ty, tz;
			sscanf(buff, "%d,%d,%d", &tx, &ty, &tz);
			lava[tx+1][ty+1][tz+1] = 1;
			cubes++;
		}
	}
	for(int i = 0; i < 25; ++i){
		for(int j = 0; j < 25; ++j){
			for(int k = 0; k < 25; ++k){
				if(lava[i][j][k] == 0 && (i == 0 || j == 0 || k == 0 || i == 24 || j == 24 || k == 24)){
					printf("test\n");
					floodfill(lava, i, j, k);
				}
			}
		}
	}
	int sidesshared = 0;
	for(int i = 0; i < 25; ++i){
		for(int j = 0; j < 25; ++j){
			for(int k = 0; k < 25; ++k){
				if(lava[i][j][k] == -1){
					if(i < 24 && lava[i+1][j][k] == 1){
						sidesshared++;
					}
					if(j < 24 && lava[i][j+1][k] == 1){
						sidesshared++;
					}
					if(k < 24 && lava[i][j][k+1] == 1){
						sidesshared++;
					}
					if(i > 0 && lava[i-1][j][k] == 1){
						sidesshared++;
					}
					if(j > 0 && lava[i][j-1][k] == 1){
						sidesshared++;
					}
					if(k > 0 && lava[i][j][k-1] == 1){
						sidesshared++;
					}
				}
			}
		}
	}
	/*
	for(int i = 0; i < 25; ++i){
		for(int j = 0; j < 25; ++j){
			for(int k = 0; k < 25; ++k){
				if(lava[i][j][k]){
					if(i < 24 && lava[i+1][j][k]){
						sidesshared++;
					}
					if(j < 24 && lava[i][j+1][k]){
						sidesshared++;
					}
					if(k < 24 && lava[i][j][k+1]){
						sidesshared++;
					}
				}
			}
		}
	}
	printf("CUBES: %d, SIDESSHARED: %d, SUBTRACT -2: %d\n", cubes, sidesshared, 6 * cubes - 2 * sidesshared);
	*/
	printf("SA: %d\n", sidesshared);
	return 0;
}
