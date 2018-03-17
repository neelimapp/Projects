import java.util.*;
import java.io.*;

public class Run_Algorithm
{

	public static String X,Y;
	public static String A,B;
	public static String path;
	
	public static void main(String[] args)
	{
		try
		{
			path=args[0];
			path=path.replace("INPUT_FILES/","");
		
			File_Convert fc = new File_Convert();
			
			String File_List = fc.readFile(path+"INTERMEDIATE_FILES/Blockcopy_File_List.txt");
			String[] Files=File_List.split("\n");
			
				for(int i=0;i<Files.length;i++)
				{
					for(int j=1;j<Files.length;j++)
					{
						if(i<j)
						{
							X=fc.readFile(Files[i]);
							Y=fc.readFile(Files[j]);
							
							X=X.replace("-","");
							Y=Y.replace("-","");
							
							String Line_1=Files[i].replace("token","line");
							String Line_2=Files[j].replace("token","line");
							A=fc.readFile(Line_1);
							B=fc.readFile(Line_2);
							
							X=X.replace(" -","");
							Y=Y.replace(" -","");
							Algorithm a=new Algorithm();
							a.Subsequence(Files[i],Files[j],X,Y,A,B,path);
						}
					}
				}
			
		}
		catch(Exception e) {}
	}
}
