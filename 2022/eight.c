#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
	FILE *fs = fopen("eight.in", "r");
	char buff[256];
	signed char board[100][100];
	signed char vis[100][100];
	int r = 0;
	int c = 0;
	int rCount = -1;
	int cCount = -1;
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
			if(cCount == -1){
				cCount = strlen(buff);
			}
			for(int i = 0; i < cCount; ++i){
				board[r][i] = buff[i] - '0';
				//printf("%d ", board[r][i]);
			}
			++r;
			//printf("\n");
		}
	}
	rCount = r;
	for(int i = 0; i < rCount; ++i){
		for(int j = 0; j < cCount; ++j){
			vis[i][j] = 0;
		}
	}
	signed char cMax = -1;
	for(int i = 0; i < rCount; ++i){
		cMax = -1;
		for(int j = 0; j < cCount; ++j){
			if(cMax < board[i][j]){
				vis[i][j] = 1;
				cMax = board[i][j];
				//printf("SET CMAX TO %d\n", board[i][j]);
			}
		}
	}
	for(int i = 0; i < rCount; ++i){
		cMax = -1;
		for(int j = cCount - 1; j >= 0; --j){
			if(cMax < board[i][j]){
				vis[i][j] = 1;
				cMax = board[i][j];
			}
		}
	}
	for(int i = 0; i < cCount; ++i){
		cMax = -1;
		for(int j = 0; j < rCount; ++j){
			if(cMax < board[j][i]){
				vis[j][i] = 1;
				cMax = board[j][i];
			}
		}
	}
	for(int i = 0; i < cCount; ++i){
		cMax = -1;
		for(int j = rCount - 1; j >= 0; --j){
			if(cMax < board[j][i]){
				vis[j][i] = 1;
				cMax = board[j][i];
			}
		}
	}
	int sum = 0;
	//printf("rc: %d, cc: %d\n", rCount, cCount);
	for(int i = 0; i < rCount; ++i){
		//printf("TEST\n");
		for(int j = 0; j < cCount; ++j){
			if(vis[i][j]){
				++sum;
			}
			//printf("%d ", vis[i][j]);
		}
		//printf("\n");
	}
	printf("SUM: %d\n", sum);
	return 0;
}
