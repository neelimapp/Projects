#include<stdio.h>
#include<stdlib.h>
#define max 5

int stack[max];
int top=-1;

void traverse()
{
	int d;
   		
   	if(top==-1)
	{
   		printf("Stack is empty");
   		return;
	} 
   		  
	for(d=top;d>=0;d--)
	{
   		printf("%d ",stack[d]);
   	}
}

int pop()
{
	int element;
   		
	if(top==-1)
	{
   		return top;
   	}
      	
	element=stack[top];
	top--;
	return element;
}

void push(int value)
{   
   top++;
   stack[top]=value;
}
