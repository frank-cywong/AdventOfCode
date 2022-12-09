#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int absc(int x){
	return (x < 0 ? -x : x);
}

int main(){
	FILE *fs = fopen("nine.in", "r");
	char buff[256];
	int Hx = 0;
	int Hy = 0;
	int temp;
	int Tx = 0;
	int Ty = 0;
	int aX[11];
	int aY[11];
	memset(aX, 0, sizeof(int) * 11);
	memset(aY, 0, sizeof(int) * 11);
	char * vis = calloc(10000000, sizeof(char));
	int encodingFactor = 3000;
	// y * encodingFactor + x + 3000 = index
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
			sscanf(buff + 2, "%d", &temp);
			//vis[3000] = 1;
			for(int i = 0; i < temp; ++i){
				if(buff[0] == 'U'){
					++aY[0];
				} else if (buff[0] == 'D'){
					--aY[0];
				} else if (buff[0] == 'L'){
					--aX[0];
				} else if (buff[0] == 'R'){
					++aX[0];
				}
				for(int j = 0; j < 9; ++j){
					Hx = aX[j];
					Hy = aY[j];
					Tx = aX[j+1];
					Ty = aY[j+1];
					if(Hx == Tx){
						if(Ty - 2 == Hy){
							--Ty;
						} else if (Ty + 2 == Hy){
							++Ty;
						}
					} else if (Hy == Ty){
						if(Tx - 2 == Hx){
							--Tx;
						} else if (Tx + 2 == Hx){
							++Tx;
						}
					} else if (absc(Hx - Tx) >= 2 || absc(Hy - Ty) >= 2){
						if (Hx > Tx && Hy > Ty){
							++Tx;
							++Ty;
						} else if (Hx > Tx && Hy < Ty){
							++Tx;
							--Ty;
						} else if (Hx < Tx && Hy > Ty){
							--Tx;
							++Ty;
						} else if (Hx < Tx && Hy < Ty){
							--Tx;
							--Ty;
						}
					}
					aX[j+1] = Tx;
					aY[j+1] = Ty;
					//printf("COORDS: %d, %d, %d\n", j, Tx, Ty);
				}
				//printf("FINAL COORDS: %d, %d\n", Tx, Ty);
				vis[(Ty + 1500) * encodingFactor + Tx + 3000] = 1;
			}
		}
	}
	int sum = 0;
	for(int i = 0; i < 10000000; ++i){
		if(vis[i]){
			//printf("Visited x=%d, y=%d\n", (i - 3000) % encodingFactor, (i - 3000) / encodingFactor - 1500);
			++sum;
		}
	}
	printf("SUM: %d\n", sum);
	return 0;
}
