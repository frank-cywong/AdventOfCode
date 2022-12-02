#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(){
	printf("TEST\n");
	FILE *fs = fopen("two.in", "r");
	int sum = 0;
	char buff[256];
	int t1;
	int t2;
	int scoreMatrix[3][3] = {{3, 1, 2}, {1, 2, 3}, {2, 3, 1}}; // scoreMatrix[t1][t2]
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			t1 = buff[0] - 'A';
			t2 = buff[2] - 'X';
			//printf("(%d, %d)\n", t1, t2);
			sum += scoreMatrix[t1][t2];
			sum += t2 * 3;
			//sum++;
		}
	}
	printf("SUM: %d\n", sum);
}
