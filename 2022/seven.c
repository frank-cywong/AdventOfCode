#include <stdio.h>
#include <stdlib.h>

struct dir{
	char * name;
	struct dir ** children;
	struct dir * parent;
	int size;
	int child_count = 0;
	char trav;
};

struct dir * new_dir(char * name){
	struct dir * o = malloc(sizeof(struct dir));
	dir->name = calloc(strlen(name) + 1, sizeof(char));
	strncpy(dir->name, name, strlen(name) + 1);
	dir->size = 0;
	dir->children = calloc(1000, sizeof(struct dir *));
	dir->parent = 0;
	dir->child_count = 0;
	dir->trav = 0;
	return o;
}

int main(){
	FILE *fs = fopen("seven-test.in", "r");
	char buff[256];
	struct dir * cur_dir;
	cur_dir = new_dir("/");
	while(fgets(buff, 16384, fs)){
		if(buff[0] != '\n'){
			if(buff[0] == '$'){
				if(buff[2] == 'c' && buff[3] == 'd'){
					if(buff[5] == '/'){
						// do nothing
					} else if (buff[5] == '.' && buff[6] == '.'){
						if(!(cur_dir->parent)){
							printf("BAD: TRIED TO DO .. ON A DIRECTORY WITH NO PARENT\n");
							exit(0);
						}
						if(!dir->trav){
							dir->trav = 1;
							cur_dir->parent->size += cur_dir->size;
						}
						cur_dir = cur_dir->parent;
					} else {
						char * dname = buff + 5;
						// yes this is slow but how bad can it be
						char child_found = 0;
						for(int i = 0; i < cur_dir->child_count; ++i){
							if(strcmp(cur_dir->
						}
					}
				} else if (buff[2] == 'l' && buff[3] == 's'){
					// don't actually need to do anything
				}
			} else {
				// assume its ls input
			}
		}
	}
	return 0;
}
