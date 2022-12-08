#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void uInc(int * x){
	for(int i = 0; i < 10; ++i){
		++(*(x + i));
	}
}

int main(){
	FILE *fs = fopen("eight.in", "r");
	char buff[256];
	signed char board[100][100];
	//signed char vis[100][100];
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
	/*
	for(int i = 0; i < rCount; ++i){
		for(int j = 0; j < cCount; ++j){
			vis[i][j] = 0;
		}
	}
	*/
	int scoreBoard[100][100];
	int viewCount[10];
	for(int i = 0; i < rCount; ++i){
		memset(viewCount, 0, sizeof(int) * 10);
		for(int j = 0; j < cCount; ++j){
			scoreBoard[i][j] = viewCount[board[i][j]];
			for(int k = board[i][j]; k >= 0; --k){
				viewCount[k] = 0;
			}
			uInc(viewCount);
		}
	}
	for(int i = 0; i < rCount; ++i){
		memset(viewCount, 0, sizeof(int) * 10);
		for(int j = cCount - 1; j >= 0; --j){
			scoreBoard[i][j] *= viewCount[board[i][j]];
			for(int k = board[i][j]; k >= 0; --k){
				viewCount[k] = 0;
			}
			uInc(viewCount);
		}
	}
	for(int j = 0; j < cCount; ++j){
		memset(viewCount, 0, sizeof(int) * 10);
		for(int i = 0; i < rCount; ++i){
			scoreBoard[i][j] *= viewCount[board[i][j]];
			for(int k = board[i][j]; k >= 0; --k){
				viewCount[k] = 0;
			}
			uInc(viewCount);
		}
	}
	for(int j = 0; j < cCount; ++j){
		memset(viewCount, 0, sizeof(int) * 10);
		for(int i = rCount - 1; i >= 0; --i){
			scoreBoard[i][j] *= viewCount[board[i][j]];
			for(int k = board[i][j]; k >= 0; --k){
				viewCount[k] = 0;
			}
			uInc(viewCount);
		}
	}
	int sum = 0;
	//printf("rc: %d, cc: %d\n", rCount, cCount);
	for(int i = 0; i < rCount; ++i){
		//printf("TEST\n");
		for(int j = 0; j < cCount; ++j){
			if(scoreBoard[i][j] > sum){
				sum = scoreBoard[i][j];
			}
			printf("%d ", scoreBoard[i][j]);
		}
		printf("\n");
	}
	printf("MAX: %d\n", sum);
	return 0;
}
