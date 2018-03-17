import java.util.*;
import java.io.*;

public class Algorithm
{
	
	public static TreeSet<String> File_1 = new TreeSet<String>();
	public static TreeSet<String> File_2 = new TreeSet<String>();
	public static TreeSet<String> file_1 = new TreeSet<String>();
	public static TreeSet<String> file_2 = new TreeSet<String>();
	
	public static String string;
	
	
    public void Subsequence(String P,String Q,String X,String Y,String A,String B,String path)
	{
		try
		{
		
		int n = X.length();
    	int m = Y.length();
    	
    	int[][] M = new int[n][m];
		
		String[] Lines_1=A.split(" ");
		String[] Lines_2=B.split(" ");
		
		for(int p=0;p<Lines_1.length;p++)
		{
			file_1.add(Lines_1[p]);
		}
		
		for(int q=0;q<Lines_2.length;q++)
		{
			file_2.add(Lines_2[q]);
		}
		
		int x=file_1.size();
		int y=file_2.size();
		int z=(x+y)/4;
		
		int i,j;
                
        for (i = 0; i < n; i++) 
        {
            for (j = 0; j < m; j++) 
            {
                if (X.charAt(i) == Y.charAt(j)) 
                {
                    M[i][j]=1;
                }
                else
                {
                	M[i][j]=0;
                }
            }
        }
        
        
        for(i = 0 ; i < n ; i++)
        {
        	for(j = 0 ; j < m ; j++)
        	{
        		if(M[i][j] == 1)
        		{
        			File_1.add(Lines_1[i]);
        			File_2.add(Lines_2[j]);
        			if(i<n&&j<m)
        			{
        				Traverse(string,X,Y,Lines_1,Lines_2,n,m,i+1,j+1,M);
        			}
        		}      		
        	}
        }
        
        File CodeBlocks_Result = new File(path+"DATABASE_FILES/CodeBlocks_Result.txt");
		FileWriter fWriter = new FileWriter(CodeBlocks_Result,true);
		PrintWriter pWriter = new PrintWriter(fWriter);
		File_1.remove(File_1.first());
		File_2.remove(File_2.first());
		P=P.replace("INTERMEDIATE_FILES/BLOCK_COPY","INPUT_FILES");
		Q=Q.replace("INTERMEDIATE_FILES/BLOCK_COPY","INPUT_FILES");
		P=P.replace(".token","");
		Q=Q.replace(".token","");
		if(File_1.size()>z&&File_2.size()>z)
			pWriter.println(P+" "+Q);
        pWriter.close();
        File_1.clear();
        File_2.clear();
        
        }
        catch(Exception e) { }
	}
	
	public static void Traverse(String string,String X,String Y,String[] Lines_1,String[] Lines_2,int n,int m,int i,int j,int[][] M)
	{
		while(i<n&&j<m)
		{
			if(M[i][j]==1)
			{
				File_1.add(Lines_1[i]);
        		File_2.add(Lines_2[j]);
        		string=string+X.charAt(i);
				i++;
				j++;
			}
			else
			{
				i++;
			}
		}
	}
	
}
