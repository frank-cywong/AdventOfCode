#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct dir{
	char * name;
	struct dir ** children;
	struct dir * parent;
	long long size;
	int child_count;
	char trav;
};

struct dir * new_dir(char * name){
	struct dir * o = malloc(sizeof(struct dir));
	o->name = calloc(strlen(name) + 1, sizeof(char));
	strncpy(o->name, name, strlen(name) + 1);
	o->size = 0;
	o->children = calloc(1000, sizeof(struct dir *));
	o->parent = 0;
	o->child_count = 0;
	o->trav = 0;
	return o;
}

long long dfs(struct dir * cur){
	long long sum = 0;
	for(int i = 0; i < cur->child_count; ++i){
		sum += dfs(cur->children[i]);
	}
	if(cur->size <= 100000){
		sum += cur->size;
	}
	printf("SIZE %lld for DIR %s\n", cur->size, cur->name);
	return sum;
}

int main(){
	FILE *fs = fopen("seven.in", "r");
	char buff[256];
	struct dir * cur_dir;
	cur_dir = new_dir("/");
	while(fgets(buff, 256, fs)){
		if(buff[0] != '\n'){
			if(buff[strlen(buff) - 1] == '\n'){
				buff[strlen(buff) - 1] = '\0';
			}
			if(buff[0] == '$'){
				if(buff[2] == 'c' && buff[3] == 'd'){
					if(buff[5] == '/'){
						// do nothing
					} else if (buff[5] == '.' && buff[6] == '.'){
						printf("CD ..\n");
						if(!(cur_dir->parent)){
							printf("BAD: TRIED TO DO .. ON A DIRECTORY WITH NO PARENT\n");
							exit(0);
						}
						if(!cur_dir->trav){
							cur_dir->trav = 1;
							cur_dir->parent->size += cur_dir->size;
						}
						//printf("size of parent: %d\n", cur_dir->parent->size);
						//printf("parent name: %s\n", cur_dir->parent->name);
						cur_dir = cur_dir->parent;
					} else {
						char * dname = buff + 5;
						// yes this is slow but how bad can it be
						char child_found = 0;
						struct dir * found_dir = 0;
						for(int i = 0; i < cur_dir->child_count; ++i){
							if(strcmp(cur_dir->children[i]->name, dname) == 0){
								found_dir = cur_dir->children[i];
								child_found = 1;
								break;
							}
						}
						if(!child_found){
							found_dir = new_dir(dname);
							printf("ADDING DIR %s\n", dname);
							found_dir->parent = cur_dir;
							cur_dir->children[cur_dir->child_count] = found_dir;
							cur_dir->child_count++;
						}
						cur_dir = found_dir;
						if(cur_dir->trav){
							printf("WARNING: CD-ed into a traversed dir\n");
						}
					}
				} else if (buff[2] == 'l' && buff[3] == 's'){
					// don't actually need to do anything
				}
			} else {
				// assume its ls input
				if(strlen(buff) >= 3 && buff[0] == 'd' && buff[1] == 'i' && buff[2] == 'r'){
					// dir
					char *dname = buff + 4;
					// yes this is slow but how bad can it be
					char child_found = 0;
					struct dir * found_dir = 0;
					for(int i = 0; i < cur_dir->child_count; ++i){
						if(strcmp(cur_dir->children[i]->name, dname) == 0){
							found_dir = cur_dir->children[i];
							child_found = 1;
							break;
						}
					}
					if(!child_found){
						found_dir = new_dir(dname);
						//printf("ADDING DIR %s\n", dname);
						found_dir->parent = cur_dir;
						cur_dir->children[cur_dir->child_count] = found_dir;
						cur_dir->child_count++;
					}
				} else {
					// file
					int s;
					sscanf(buff, "%d", &s);
					//printf("Adding file size %d to %s\n", s, cur_dir->name);
					cur_dir->size += s;
				}
			}
		}
	}
	while(strcmp(cur_dir->name, "/") != 0){
		if(!cur_dir->trav){
			cur_dir->trav = 1;
			cur_dir->parent->size += cur_dir->size;
		}
		cur_dir = cur_dir->parent;
	}
	long long dfs_sum = dfs(cur_dir);
	printf("SUM: %lld\n", dfs_sum);
	return 0;
}
