#include <cstdio>
#include <cstring>
#include <deque>

using namespace std;

int main(){
	FILE *fs = fopen("five.in", "r");
	int sum = 0;
	int temp;
	deque<int> stack[10];
	for(int i = 0; i < 10; ++i){
		deque<int> temp;
		stack[i] = temp;
	}
	int stackcount = -1;
	bool headermode = true;
	char buff[256];
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			buff[strlen(buff) - 1] = '\0';
			int sl = strlen(buff);
			if(stackcount == -1){
				stackcount = (sl + 1) / 4;
			}
			if(headermode){
				for(int i = 0; i < stackcount; ++i){
					if(headermode && buff[i * 4 + 1] != ' '){
						if(buff[i * 4 + 1] >= '0' && buff[i * 4 + 1] <= '9'){
							headermode = false;
							break;
						}
						stack[i].push_front(buff[i * 4 + 1]);
					}
				}
			} else {
				int count, source, dest;
				sscanf(buff, "move %d from %d to %d", &count, &source, &dest);
				--source;
				--dest;
				deque<int> tdeque;
				for(int i = 0; i < count; ++i){
					int x = stack[source].back();
					stack[source].pop_back();
					tdeque.push_back(x);
				}
				for(int i = 0; i < count; ++i){
					int x = tdeque.back();
					tdeque.pop_back();
					stack[dest].push_back(x);
				}
			}
		}
	}
	for(int i = 0; i < stackcount; ++i){
		printf("%c", stack[i].back());
	}
	printf("\n");
	return 0;
}
