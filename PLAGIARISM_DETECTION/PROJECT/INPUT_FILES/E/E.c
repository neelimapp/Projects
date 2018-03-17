#include<stdio.h>
#include<stdlib.h>

	int main()// Stack Implementation
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
