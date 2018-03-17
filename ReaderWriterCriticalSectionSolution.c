#include<stdio.h>
#include<pthread.h>
#include<semaphore.h>
#include <stdlib.h>
#include <sys/types.h>
#include <fcntl.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <unistd.h>
/* compile with gcc -o readerWriter.out readerWriter.c -lpthread */
/* Simple Application which solves reader writer critical section problem.
 * 
 *   Neelima Parakala
 *   UNAME - neelima1
 *   UID   - U03623424
 
 */
/* semaphore for maintaining read count */
sem_t readCountAccess;
/* semaphore for maintaining database access */
sem_t databaseAccess;
int readCount = 0;
/* shared counter */
int counter = 0;
/* shared flag */
int shared_flag = 0;
void * Reader(void *temp);
void * Writer(void *arg);
void relaxandspendtime();

int main(int argc, char *argv[])
{
	int i = 0, k;
	int numOfReaders = 0;
	/* initializes the reader writer semaphores */
	sem_init(&readCountAccess, 0, 1);
        sem_init(&databaseAccess, 0, 1);
	
        pthread_t readers[12], writer[1];
	/* attribute pointer array */
	pthread_attr_t  attr[1];     
	/* Required to schedule thread independently.
	 * Otherwise use NULL in place of attr. */
        pthread_attr_init(&attr[0]);
	/* system-wide contention */
        pthread_attr_setscope(&attr[0], PTHREAD_SCOPE_SYSTEM);
        if( argc == 2 )
        {
		/* String to number conversion */
                numOfReaders = atoi(argv[1]); 
		/* Input validation */
                if( numOfReaders > 0 && numOfReaders < 13 )
                {
			k = numOfReaders / 2;
			/* Creates first half of the readers threads */
			for(i = 0; i < k; i++)
			{
				pthread_create(&readers[i], &attr[0], Reader, (void*) i);
			}
			/* Create the writer thread */
			pthread_create(&writer[0], &attr[0], Writer, NULL);
			/* Creates second half of the writers thread */
			for(i = k; i < numOfReaders; i++) 
			{
				pthread_create(&readers[i], &attr[0], Reader, (void*) i);
			}
			/* joins writer thread */
       			pthread_join(writer[0],NULL);
			/* joins readers threads */
        		for(i=0;i<numOfReaders;i++)
        		{
                		pthread_join(readers[i],NULL);
        		}
        		sem_destroy(&databaseAccess);
        		sem_destroy(&readCountAccess);               
                }
                else
                {
                        printf( "please enter valid input... the input must be greater than 0 and less than 13.\n" );
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

/* This method allows the writer to write to the database */
void *Writer(void *arg)
{
	for(int i = 0; i < 25000; i++)
	{
		/* writer critical section */
		sem_wait(&databaseAccess);
		shared_flag = 1;
		counter++;
		shared_flag = 0;
	        sem_post(&databaseAccess);
	} 
	printf("\nwriter done with counter value = %d", counter);
}

/* This method allows the reader to read the database */
void *Reader(void *arg)
{
	int temp = (int)arg;
	int value;
	/* critical section for readCount */
	sem_wait(&readCountAccess);
	readCount++;
	if(readCount == 1)
	{
		sem_wait(&databaseAccess);
	}
	sem_post(&readCountAccess);
	/* critical section for the reader to read the database */
	if(shared_flag == 1)
        {
        	printf("\n reader can't access the shared variable as writer is in the critical section");
        }
	else
	{
		relaxandspendtime();
	}
	/* critical section for readcount */
	sem_wait(&readCountAccess);
	readCount--;
	if(readCount == 0)
	{
		sem_post(&databaseAccess);
	}
	sem_post(&readCountAccess);
	printf("\nreader %d done", temp + 1);
}

/* this method allows the reader to take some time while reading */
void relaxandspendtime()
{
	int i;
	for(i = 0; i < 250000000; i++)
	i = i;
}
