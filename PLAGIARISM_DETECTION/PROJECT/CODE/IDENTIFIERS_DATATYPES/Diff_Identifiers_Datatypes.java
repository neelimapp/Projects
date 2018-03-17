import java.io.*;
import java.util.*;

public class Diff_Identifiers_Datatypes
{
	private static Diff_Identifiers_Datatypes check = new Diff_Identifiers_Datatypes();
	public static int File_Counter;
	public static String Files_Path;
	public static int Identifiers_Datatypes_Verbatim_Pair_Counter;
	public static String Set;
	public static String path;
	
	public static void main(String[] args)
	{
		path=args[0];
		path=path.replace("INPUT_FILES/","");
		String string;
		Process process;
		check.File_List();
		String[] Files=new String[File_Counter];
		int i=0;
		
		try
		{
			File Identifiers_Datatypes_Result = new File(path+"DATABASE_FILES/Identifiers_Datatypes_Result.txt");
			FileWriter fWriter = new FileWriter(Identifiers_Datatypes_Result);
    		PrintWriter pWriter = new PrintWriter(fWriter);
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/Tokenized_File_List.txt"));
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
            				String File_1=Files[i].replace("INTERMEDIATE_FILES/TOKENIZED_FILES","INPUT_FILES");
            				String File_2=Files[j].replace("INTERMEDIATE_FILES/TOKENIZED_FILES","INPUT_FILES"); 
            				File_1=File_1.replace(".token","");
            				File_2=File_2.replace(".token","");       				
            				pWriter.println(File_1+" "+File_2);
            				Identifiers_Datatypes_Verbatim_Pair_Counter++;
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
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/Tokenized_File_List.txt"));
			while((br.readLine()) != null)
			{
				File_Counter++;
			}
			br.close();			
		}
		catch(Exception e) {}
	}
	
}
