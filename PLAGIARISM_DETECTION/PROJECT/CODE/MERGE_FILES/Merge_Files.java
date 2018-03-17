import java.io.*;
import java.util.*;

public class Merge_Files
{
	private static Merge_Files merge = new Merge_Files();
	public static String Files_Path;
	public static int Directory_Counter;
	public static String Directory_Path;
	public static String path;
	
	public static void main(String[] args)
	{
		Directory_Path = args[0];
		path=Directory_Path.replace("INPUT_FILES/","");
		merge.Directory_List(new File(Directory_Path));
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/Directory_List.txt"));
			String Directory_Name=null;
			while((Files_Path = br.readLine()) != null)
			{
				StringTokenizer stk=new StringTokenizer(Files_Path,"/");
        		while(stk.hasMoreTokens())
        		{
            		Directory_Name=stk.nextToken();
        		}
				merge.File_List(new File(Files_Path),Directory_Name);		
			}
			br.close();
		}
		catch(Exception e){ }
	}
	
	private void Directory_List(File Directory_Path)
	{
		try
		{
			File Directory_List = new File(path+"INTERMEDIATE_FILES/Directory_List.txt");
			FileWriter fWriter = new FileWriter(Directory_List);
    		PrintWriter pWriter = new PrintWriter(fWriter);
			File[] Directory = Directory_Path.listFiles();
			for(int i = 0;i < Directory.length;i++)
			{
				if(Directory[i].isDirectory())
				{
					pWriter.println(Directory[i]);
				}
			}
			pWriter.close();
		}
		catch(Exception e) {}
	}
	
	private void File_List(File File_Path,String Directory_Name)
	{
		try
		{
			File Merged_File_Path = new File(Directory_Path+Directory_Name+".txt");
			Directory_Counter++;
			FileWriter fWriter = new FileWriter(Merged_File_Path);
    		PrintWriter pWriter = new PrintWriter(fWriter);
			File_Convert fc = new File_Convert();
			String Merged_File = null;
			String Temp_File = null;
			String Path[] = File_Path.list();
			for(int i = 0;i < Path.length;i++)
			{
				File file = new File(Path[i]);
				if(!file.isDirectory())
				{
  					Temp_File = fc.readFile(Files_Path+"/"+file);
					Merged_File = concat(Merged_File,Temp_File);
				}
				
			}
    		pWriter.println(Merged_File);
    		pWriter.close();
		}
		catch(Exception e) {}
	}
	
	public static String concat(String string_1, String string_2) 
	{
  		return string_1 == null ? string_2 : string_2 == null ? string_1 : string_1 + string_2;
	}
}
