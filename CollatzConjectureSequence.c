#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

/* Simple Application to print collatz conjecture 
 * sequences by creating 2 child processes
 *
 * @author Neelima Parakala
 * UNAME - neelima1
 * UID   - U03623424
 */
int main(int argc, char *argv[])
{
	int n;
	if( argc == 2 ) 
	{
		n = atoi(argv[1]);// String to number conversion
		if( n > 0 && n < 40 )// Wrong input checking
		{
			collatz( n, n+4 );// Function calling
		}
		else
		{
			printf( "please enter valid input... the input must be greater than 0 and less than 40.\n" );
		}
   	}
   	else if( argc > 2 ) 
	{
      		printf( "Too many arguments supplied.\n" );
   	}
   	else 
	{
      		printf( "One argument expected.\n" );
  	}
 	printf( "\n" );
	return 0;
}

/* This method creates 2 child processes
 * to print collatz conjecture sequence
 * @param {int} i
 * @param {int} j
 * @return {int} 
 */
int collatz(int i, int j)
 {
	pid_t child_pid1, child_pid2;
  
	// First child process creation
	child_pid1 = fork();
        if ( child_pid1 < 0 )
	{
		printf( "fork() of child_pid1 failed\n" );
	}
	else if ( child_pid1 > 0 )
	{
		// Second child process creation
		child_pid2 = fork();
		if( child_pid2 < 0 )
		{
			printf( "fork() of child_pid2 failed\n" );
		}
		else if( child_pid2 > 0 )
		{	
			wait();
			printf( "One done! \n" );
			wait();
			printf( "Children Complete \n" );	
			exit(1);
		}
		else
		{
			collatzSequence( j, 2 );		
		}
	}
	else
	{
		collatzSequence( i, 1 );
	}
	return 0;
}

/**
 * This method prints collatz conjecture
 * sequence of the given input
 * @param {int} j
 * @param {int} child
 */
int collatzSequence( int num, int child )
{
	printf( "From child %d init n=%d", child, num );		
	while( num != 1 )
	{
		printf( ", " );
		// If n is even
		if( num%2 == 0 ) 
		{
			num /= 2;
		}
		// if n is odd
		else
		{
			num = 3*num + 1;
		}
		printf( "From Child %d n=%d", child, num );
	}
	printf( "\n\n" );
	exit(1);
	return 0;
}



