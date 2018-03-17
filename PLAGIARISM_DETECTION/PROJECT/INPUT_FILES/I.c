#include<stdio.h>
#include<stdlib.h>

int main()
{
	int element;
	int choice;
	
	while(1)
	{	
		printf("1 Push 2 Pop 3 Print 4 Exit Enter your choice ");
   	  	scanf("%d",&choice);

   	    if(choice==1)
   	  	{
   	  		if(top==max-1)
   	  		{
            	printf("Overflow");
   	        }
    	    else
    	    {
    	        printf("Enter the value to insert ");
    	        scanf("%d",&element);
    	        push(element);
    	    }
		}
    	  	
		else if(choice==2)
    	{
    		if (top==-1)
    	  	{
    	        printf("Underflow");
    	    }
    	    else
            {
            	element=pop();
            }
      	}
      		
      	else if(choice==3)
      	{
      		traverse();
      	}
      		
      	else
      	{
      		exit(0);
      	}
      		
   	}
   	
   	return 0;
   	
}
#include<stdio.h>
#include<stdlib.h>
#define max 5

int stackelements[max];
int top=-1;

int pop()
{
	int element;
   		
	if(top==-1)
	{
   		return top;
   	}
      	
	element=stackelements[top];
	top--;
	return element;
} 
	
void traverse()
{
	int d;
   		
   	if(top==-1)
	{
   		printf("stackelements is empty");
   		return;
	} 
   		  
	for(d=top;d>=0;d--)
	{
   		printf("%d ",stackelements[d]);
   	}
}

void push(int value)
{   
   top++;
   stackelements[top]=value;
}

