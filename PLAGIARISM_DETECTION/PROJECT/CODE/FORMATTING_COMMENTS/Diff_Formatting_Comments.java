import java.io.*;
import java.util.*;

public class Diff_Formatting_Comments
{
	private static Diff_Formatting_Comments check = new Diff_Formatting_Comments();
	public static int File_Counter;
	public static String Files_Path;
	public static int Formatting_Comments_Verbatim_Pair_Counter;
	public static String Set;
	public static String path;
	
	public static void main(String[] args)
	{
		path=args[0];
		path=path.replace("INPUT_FILES","");
		File_Convert fc=new File_Convert();
		String string;
		Process process;
		check.File_List();
		String[] Files=new String[File_Counter];
		int i=0;
		
		try
		{
			File Remove_Comments_Result = new File(path+"DATABASE_FILES/Formatting_Comments_Result.txt");
			FileWriter fWriter = new FileWriter(Remove_Comments_Result);
    		PrintWriter pWriter = new PrintWriter(fWriter);
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/File_List.txt"));
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
						String File_1=Files[i].replace("INPUT_FILES","INTERMEDIATE_FILES/REMOVE_COMMENTS");
						String File_2=Files[j].replace("INPUT_FILES","INTERMEDIATE_FILES/REMOVE_COMMENTS");
						File_1=File_1+".out";
						File_2=File_2+".out";
						process = Runtime.getRuntime().exec("diff "+File_1+" "+File_2+" -E -b -w -B");
						BufferedReader b = new BufferedReader(new InputStreamReader(process.getInputStream()));
            			if ((string = b.readLine()) == null)
            			{            				
            				pWriter.println(Files[i]+" "+Files[j]);
            				Formatting_Comments_Verbatim_Pair_Counter++;
            			}
            			process.destroy();
					}
				}
			}
			pWriter.close();
			
		}
		catch(Exception e) {}
	}
	
	private void File_List()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/File_List.txt"));
			while((br.readLine()) != null)
			{
				File_Counter++;
			}
			br.close();			
		}
		catch(Exception e) {}
	}
	
}
