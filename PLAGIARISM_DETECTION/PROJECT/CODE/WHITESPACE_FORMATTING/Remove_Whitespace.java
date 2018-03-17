import java.io.*;
import java.util.*;

public class Remove_Whitespace
{
	private static Remove_Whitespace check = new Remove_Whitespace();
	public static int File_Counter;
	public static int Whitespace_Formatting_Verbatim_Pair_Counter;
	public static String Set;
	public static String path;
	
	public static void main(String[] args)
	{
		path = args[0];
		path=path.replace("INPUT_FILES/","");
		String Preprocessed_File_List;
		String Files_Path;
		String string;
		Process process;
		
		try
		{
			File Remove_Comments_Result = new File(path+"DATABASE_FILES/Whitespace_Formatting_Result.txt");
			FileWriter fWriter = new FileWriter(Remove_Comments_Result);
    		PrintWriter pWriter = new PrintWriter(fWriter);
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/File_List.txt"));
			int i=0;
			
			while((Preprocessed_File_List = br.readLine()) != null)
			{
				File_Counter++;	
			}
			br.close();
			
			String[] Files=new String[File_Counter];
			BufferedReader b = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/File_List.txt"));
			while((Files_Path = b.readLine()) != null)
			{
				Files[i]=Files_Path;
				i++;				
			}
			b.close();
			
			for(i=0;i<Files.length;i++)
			{
				for(int j=1;j<Files.length;j++)
				{
					if(i<j)
					{
						process = Runtime.getRuntime().exec("diff "+Files[i]+" "+Files[j]+" -E -b -w -B");
						BufferedReader Br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            			if ((string = Br.readLine()) == null)
            			{
            				pWriter.println(Files[i]+" "+Files[j]);
            				Whitespace_Formatting_Verbatim_Pair_Counter++;
            			}
            			process.destroy();
					}
				}
			}
			pWriter.close();
			
		}
		catch(Exception e) {}
	}
	
}
