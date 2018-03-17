#define _REENTRANT
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <fcntl.h>
#include <sys/time.h>
#include <sys/resource.h>
/* compile with gcc -o threads.out threads.c -lpthread */
/* Simple Application to print counter value which is shared
 * among two threads and incremented using trylocks
 * 
 * Neelima Parakala
 * UNAME - neelima1
 * UID   - U03623424
 */

struct rusage mytiming;
struct timeval mytimeval;
struct shared_dat
{
	 int value;     /* shared variable to store result*/
};
struct shared_dat  *counter;
int temp;
pthread_mutex_t mutex;

/****************************************************************
 * * This function increases the value of shared variable "counter"
 *   by one 2000000 times
 *   ****************************************************************/
void * thread1(void *arg)
{
	int line = 0;
	int updates = 0;	
	while (line < 2000000)
	{
		/* Critical Section */
		/* If lock is acquired by mutex already then trylock
 		 * remains in busy wait untill the lock is released */
		while(!(pthread_mutex_trylock(&mutex) == 0));	
		if(counter->value % 100 == 0)
		{
			 if(line + 100 < 2000000)
                         {
                          	updates++;
				counter->value = counter->value + 100;
				line = line + 100;
                                counter->value = counter->value * 2;
              			counter->value = counter->value / 2;			
                         }
		}
		else
		{
			line++;
			counter->value = counter->value + 1;
	    		counter->value = counter->value * 2;
	    		counter->value = counter->value / 2;
		}
		/* mutex is unlocked after the operation on shared variable is completed */
		pthread_mutex_unlock(&mutex); 		
        }	
	printf("from process1 counter  =  %d\n", counter->value);
	printf("No. of times the counter value updated by 100 in thread1 = %d\n", updates); 
	return(NULL);
}


/****************************************************************
 * This function increases the value of shared variable "counter"
 * by one 2000000 times
 * ****************************************************************/
void * thread2(void *arg)
{
	int line = 0;	
	while (line < 2000000)
	{
		/* Critical Section */
	    	while(!(pthread_mutex_trylock(&mutex) == 0));
		line ++;            	
	    	counter->value = counter->value + 1;
	    	counter->value = counter->value * 2;
	    	counter->value = counter->value / 2;          	
		pthread_mutex_unlock(&mutex);
	}
	printf("from process2 counter = %d\n", counter->value); 
	return(NULL);
}


/****************************************************************
 * *                  Main Body                                    *
 * ****************************************************************/
main()
{
 	int r = 0;
	pthread_t	tid1[1];     /* process id for thread 1 */
        pthread_t	tid2[1];     /* process id for thread 2 */
        pthread_attr_t	attr[1];     /* attribute pointer array */

        counter = (struct shared_dat *) malloc(sizeof(struct shared_dat));

	/* initialize shared memory to 0 */
	counter->value = 0 ;
        printf("1 - I am here %d in pid %d\n",r,getpid());
    	printf("counter initialised= %d\n", counter->value);
	fflush(stdout);
	/* Required to schedule thread independently.
 	*  Otherwise use NULL in place of attr. */
        pthread_attr_init(&attr[0]);
        pthread_attr_setscope(&attr[0], PTHREAD_SCOPE_SYSTEM);  /* system-wide contention */ 

	/* end to schedule thread independently */

	/* Create the threads */
        pthread_create(&tid1[0], &attr[0], thread1, NULL);
        pthread_create(&tid2[0], &attr[0], thread2, NULL);

	/* Wait for the threads to finish */
    	pthread_join(tid1[0], NULL);
    	pthread_join(tid2[0], NULL);
	printf("from parent counter  =  %d\n", counter->value);
	printf("---------------------------------------------------------------------------\n");
	/* Timer to find the time used for the entire program to finish */
	getrusage(RUSAGE_SELF, &mytiming);
	printf("Time used is sec: %d, usec %d\n",mytiming.ru_utime.tv_sec,
	mytiming.ru_utime.tv_usec);
	printf("System Time used is sec: %d, usec %d\n",mytiming.ru_stime.tv_sec,
	mytiming.ru_stime.tv_usec);
	printf("---------------------------------------------------------------------------\n");
        printf("\t\t    End of simulation\n");
	exit(0);		
}

