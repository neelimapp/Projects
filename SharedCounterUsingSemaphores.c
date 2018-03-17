/* Time taken by semaphores to increment shared counter via two threads is greater than the time taken by mutex try locks.
 * The difference in time is due to the following reasons.
 * 1. Semaphore will block if the lock is not available, while try_lock() returns straight away even if the lock is not available.
 * 2. In semaphores if the lock is not available, process is blocked and placed in the waiting queue, once the lock is available 
 *    the process is placed in the ready queue. This transition of queues consumes the time.
 * 3. Semaphore use an associated queue of processes that are waiting on the semaphore, allowing the semaphore to block the process 
 *    and then wake it when the semaphore is incremented. The operating system provide the block() system call, which suspends 
 *    the process that calls it, and the wakeup(P <Process>) system call which resumes the execution of blocked process. If a 
 *    process calls wait() on a semaphore with a value of zero, the process is added to the semaphores queue and then blocked. 
 *    The state of the process is switched to the waiting state, and control is transferred to the CPU scheduler, which selects 
 *    another process to execute. When another process increments the semaphore by calling signal() and there are tasks on the 
 *    queue, one is taken off of it and resumed.
 * 4. Hence the overhead of sleeping, maintaining the wait queue, and waking up, outweigh the  total lock hold time in semaphore.
 */
#define _REENTRANT
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <fcntl.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <semaphore.h>

/* compile with gcc -o semaphores.out semaphores.c -lpthread */
/* Simple Application to print counter value which is shared
 * among three threads and incremented in critical section 
 * using semaphores.
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
struct shared_dat *counter;
int temp;
sem_t semaphore;

/****************************************************************
 * This function increases the value of shared variable "counter"
 * by one 2000000 times
 * *************************************************************/
void * thread1(void *arg)
{
	int line = 0;
	int updates = 0;	
	while (line < 2000000)
	{
		/* Critical Section */
		/* sem_wait() decrements (locks) the semaphore pointed to by semaphore.*/
		sem_wait(&semaphore);	
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
		/* sem_post() increments (unlocks) the semaphore pointed to by semaphore */
		sem_post(&semaphore); 		
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
		/* sem_wait() decrements (locks) the semaphore pointed to by semaphore.*/
	    	sem_wait(&semaphore);
		line ++;            	
	    	counter->value = counter->value + 1;
	    	counter->value = counter->value * 2;
	    	counter->value = counter->value / 2;
		/* sem_post() increments (unlocks) the semaphore pointed to by semaphore */          	
		sem_post(&semaphore);
	}
	printf("from process2 counter = %d\n", counter->value); 
	return(NULL);
}


/****************************************************************
 * This function increases the value of shared variable "counter"
 * by one 2000000 times
 * ****************************************************************/
void * thread3(void *arg)
{
        int line = 0;
        while (line < 2000000)
        {
                /* Critical Section */
		/* sem_wait() decrements (locks) the semaphore pointed to by semaphore.*/
                sem_wait(&semaphore);
                line ++;
                counter->value = counter->value + 1;
                counter->value = counter->value * 2;
                counter->value = counter->value / 2;
		/* sem_post() increments (unlocks) the semaphore pointed to by semaphore */
                sem_post(&semaphore);
        }
        printf("from process3 counter = %d\n", counter->value);
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
	pthread_t       tid3[1];     /* process id for thread 3 */
        pthread_attr_t	attr[1];     /* attribute pointer array */

        counter = (struct shared_dat *) malloc(sizeof(struct shared_dat));

	/* initialize shared memory to 0 */
	counter->value = 0 ;
        printf("1 - I am here %d in pid %d\n",r,getpid());
    	printf("counter initialised = %d\n", counter->value);
	fflush(stdout);

	/* sem_init() initializes the unnamed semaphore at the address pointed to by
         * semaphore. The 3rd argument(value) specifies the initial value for the
         * semaphore, The 2nd argument(pshared) indicates whether this semaphore is
         * to be shared between the threads of a process, or between processes. */
	sem_init(&semaphore, 0, 1);

	/* Required to schedule thread independently.
 	*  Otherwise use NULL in place of attr. */
        pthread_attr_init(&attr[0]);
        pthread_attr_setscope(&attr[0], PTHREAD_SCOPE_SYSTEM);  /* system-wide contention */ 

	/* end to schedule thread independently */

	/* Create the threads */
        pthread_create(&tid1[0], &attr[0], thread1, NULL);
        pthread_create(&tid2[0], &attr[0], thread2, NULL);
	pthread_create(&tid3[0], &attr[0], thread3, NULL);

	/* Wait for the threads to finish */
    	pthread_join(tid1[0], NULL);
    	pthread_join(tid2[0], NULL);
	pthread_join(tid3[0], NULL);
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
 
