import java.util.*;
import java.io.*;

public class Diff_Blocks
{
	
	public static TreeSet<String> File_1 = new TreeSet<String>();
	public static TreeSet<String> File_2 = new TreeSet<String>();
	
    public void Check_Blocks(String P,String Q,String X,String Y,String A,String B,String path)
	{
		try
		{
		
			String[] Blocks_1=X.split("-");
			String[] Blocks_2=Y.split("-");
		
			String[] Lines_1=A.split(" - ");
			String[] Lines_2=B.split(" - ");
		
        	for(int i=0;i<Blocks_1.length;i++)
			{
				for(int j=0;j<Blocks_2.length;j++)
				{
					if(Blocks_1[i].equals(Blocks_2[j]))
					{
						String[] Line_1=Lines_1[i].split(" ");
						String[] Line_2=Lines_2[j].split(" ");
						
						for(int p=0;p<Line_1.length;p++)
						{
							File_1.add(Line_1[p]);
						}
						for(int q=0;q<Line_1.length;q++)
						{
        					File_2.add(Line_2[q]);
        				}
					}
				}
			}
			
			File BlockCopy_Result = new File(path+"DATABASE_FILES/BlockCopy_Result.txt");
			FileWriter fWriter = new FileWriter(BlockCopy_Result,true);
			PrintWriter pWriter = new PrintWriter(fWriter);
			File_1.remove(File_1.first());
			File_2.remove(File_2.first());
			P=P.replace("INTERMEDIATE_FILES/BLOCK_COPY","INPUT_FILES");
			Q=Q.replace("INTERMEDIATE_FILES/BLOCK_COPY","INPUT_FILES");
			P=P.replace(".token","");
			Q=Q.replace(".token","");
			if(File_1.size()>0&&File_2.size()>0)
			{
				pWriter.println(P+" "+Q);
	        	pWriter.println(File_1+"-"+File_2);
	        }	
	        pWriter.close();
	        File_1.clear();
	        File_2.clear();
		
			pWriter.close();
		  
        }
        catch(Exception e) { }
	}
	
}
