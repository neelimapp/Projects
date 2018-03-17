import java.io.*;
import java.util.*;

public class Diff_Remove_Comments
{
	private static Diff_Remove_Comments check = new Diff_Remove_Comments();
	public static int File_Counter;
	public static String Files_Path;
	public static int Remove_Comments_Verbatim_Pair_Counter;
	public static String Set;
	public static String path;
	
	public static void main(String[] args)
	{
		path=args[0];
		path=path.replace("INPUT_FILES","");
		String string;
		Process process;
		String File_Path = path+"INTERMEDIATE_FILES/REMOVE_COMMENTS";
		check.File_List(new File(File_Path));
		String[] Files=new String[File_Counter];
		int i=0;
		
		try
		{
			File Remove_Comments_Result = new File(path+"DATABASE_FILES/Remove_Comments_Result.txt");
			FileWriter fWriter = new FileWriter(Remove_Comments_Result);
    		PrintWriter pWriter = new PrintWriter(fWriter);
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/Remove_Comments_File_List.txt"));
			while((Files_Path = br.readLine()) != null)
			{
				Files[i]=Files_Path;
				i++;				
			}
			br.close();
			
			for(i=0;i<Files.length;i++)
			{
				for(int j=1;j<Files.length;j++)
				{
					if(i<j)
					{
						process = Runtime.getRuntime().exec("diff "+Files[i]+" "+Files[j]);
						BufferedReader b = new BufferedReader(new InputStreamReader(process.getInputStream()));
            			if ((string = b.readLine()) == null)
            			{           
            				String File_1=Files[i].replace("INTERMEDIATE_FILES/REMOVE_COMMENTS","INPUT_FILES");
            				String File_2=Files[j].replace("INTERMEDIATE_FILES/REMOVE_COMMENTS","INPUT_FILES"); 
            				File_1=File_1.replace(".out","");
            				File_2=File_2.replace(".out",""); 				
            				pWriter.println(File_1+" "+File_2);
            				Remove_Comments_Verbatim_Pair_Counter++;
            			}
            			process.destroy();
					}
				}
			}
			pWriter.close();
			
		}
		catch(Exception e) {}
	}
	
	private void File_List(File File_Path)
	{
		try
		{
			File Remove_Comments_File_List = new File(path+"INTERMEDIATE_FILES/Remove_Comments_File_List.txt");
			FileWriter fWriter = new FileWriter(Remove_Comments_File_List);
    		PrintWriter pWriter = new PrintWriter(fWriter);
			File[] Files = File_Path.listFiles();
			for(int i = 0;i < Files.length;i++)
			{
				if(!Files[i].isDirectory())
				{
					pWriter.println(Files[i]);
					File_Counter++;
				}
			}
			pWriter.close();
		}
		catch(Exception e) {}
	}
	
}
