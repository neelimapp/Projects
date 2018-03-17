package findSumPair;

import java.util.Scanner;

/**
 * Simple Application to find the pair(left,right) from a given
 * Array that gives us user expected SUM (left + right = SUM).
 *
 * @author Neelima Parakala
 */
public class FindSumPair {
		
	    /* static variable N holds array length while heap sorting */
		private static int heapLength;
		
		/**
		 * This method does a basic Binary Search
		 * @param {int} diff
		 * @param {int} array
		 * @param {int} curArrayIndex
		 * @return {int}
		 */
		private static int binarySearch(int diff, int array[], int curArrayIndex)
		{
			int aryLowIndex = 0;
			int aryHighIndex = array.length - 1;
			int aryMidIndex = (aryLowIndex + aryHighIndex)/2;
			while(aryLowIndex <= aryHighIndex && array[aryMidIndex] != diff)
			{
				if(array[aryMidIndex] < diff)
				{
					aryLowIndex = aryMidIndex + 1;
				}
				else
				{
					aryHighIndex = aryMidIndex - 1;
				}
				aryMidIndex = (aryLowIndex + aryHighIndex)/2;
			}
			if(aryLowIndex > aryHighIndex)
			{
				aryMidIndex = -1;
			}
			return aryMidIndex;
		}
			
		/**
		 * This method does a basic Heap Sort
		 * @param {int} array
		 */
		private static void heapSort(int array[])
		{       
			heapify(array);        
			for (int i = heapLength; i > 0; i--)
			{
				swap(array,0, i);
				heapLength = heapLength - 1;
				maxheap(array, 0);
			}
		}
		
		/**
		 * This method builds a Heap
		 * @param {int} array
		 */
	    private static void heapify(int array[])
	    {
	        heapLength = array.length - 1;
	        for (int i = heapLength/2; i >= 0; i--)
	        {
	        	maxheap(array, i);        
	        }
	    }
	    
	    /**
	     * This method Swaps the largest element in heap
	     * @param {int} array
	     * @param {int} i
	     */
	    private static void maxheap(int array[], int i)
	    { 
	        int left = 2*i ;
	        int right = 2*i + 1;
	        int max = i;
	        if (left <= heapLength && array[left] > array[i])
	        {
	        	max = left;
	        }
	        if (right <= heapLength && array[right] > array[max])        
	        {
	        	max = right;
	        }
	        if (max != i)
	        {
	            swap(array, i, max);
	            maxheap(array, max);
	        }
	    }    
	    
	    /**
	     * This method swaps two numbers in an Array
	     * @param {int} array
	     * @param {int} left
	     * @param {int} right
	     */
	    private static void swap(int array[], int i, int j)
	    {
	    	array[i] = array[i] + array[j];
	    	array[j] = array[i] - array[j];
	    	array[i] = array[i] - array[j];
	    }
		
	    /**
	     * Starting point of the Application
	     * @param {Sring} args
	     */
		public static void main(String args[])
		{ 
			Scanner scanner = new Scanner( System.in );        
	        Short inputArrayLength, sum;
	    
	        /* Get the sum value */
	        System.out.println("Enter the 'sum' value of the Pair:");
	        sum = scanner.nextShort();    
	        
	        /* Get number of elements to be stored in the array */
	        System.out.println("Please provide the length of the input:");
	        inputArrayLength = scanner.nextShort();
	        
	        /* declare an array of length noOfElements */
	        int inputArray[] = new int[ inputArrayLength ];
	        
	        /* Get the input array */
	        System.out.println("\nPlease enter your "+ inputArrayLength +" input number:");
	        for (int i = 0; i < inputArrayLength; i++)
	        {
	            inputArray[i] = scanner.nextInt();
	        }
	        
	        scanner.close();
	        
	        /* Heap Sort method call */
	        heapSort(inputArray);
	        
	        int pairCount = 0;
	        for(int pairLeftIndex = 0; pairLeftIndex < inputArray.length;
	        		pairLeftIndex ++)
	        {
	            int diff = sum - inputArray[pairLeftIndex];
	        
	            // binary search returns the index of other value in the pair
	            int pairRightIndex = binarySearch(diff,inputArray,pairLeftIndex); 
	            if((0 <= pairRightIndex)&& (pairRightIndex < inputArray.length) 
	            		&& (pairRightIndex != pairLeftIndex) )
	            {
	            	pairCount ++;
	                System.out.println("Pair" + pairCount + ":" + " " 
	            	+ inputArray[pairLeftIndex] + ", " + inputArray[pairRightIndex]);
	            }
	        }
			if(pairCount == 0)
			{
				System.out.println("Sorry ! Pair not found for the given input");
			}
		}	
	}



