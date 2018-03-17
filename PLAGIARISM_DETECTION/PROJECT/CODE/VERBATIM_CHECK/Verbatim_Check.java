import java.io.*;
import java.util.*;

public class Verbatim_Check
{
	private static Verbatim_Check check = new Verbatim_Check();
	public static String Files_Path;
	public static int File_Counter;
	public static int Verbatim_Pair_Counter;
	public static String Set;
	public static String path;
	
	public static void main(String[] args)
	{
		String string;
		Process process;
		String File_Path = args[0];
		path=File_Path.replace("INPUT_FILES/","");
		check.File_List(new File(File_Path));
		String[] Files=new String[File_Counter];
		int i=0;
		try
		{
			File Verbatim_Check_Result = new File(path+"DATABASE_FILES/Verbatim_Check_Result.txt");
			FileWriter fWriter = new FileWriter(Verbatim_Check_Result);
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
						process = Runtime.getRuntime().exec("diff "+Files[i]+" "+Files[j]);
						BufferedReader b = new BufferedReader(new InputStreamReader(process.getInputStream()));
            			if ((string = b.readLine()) == null)
            			{
            				pWriter.println(Files[i]+" "+Files[j]);
            				Verbatim_Pair_Counter++;
            			}
            			process.destroy();
					}
				}
			}
			pWriter.close();
			
		}
		catch(Exception e){ }
	}
	
	private void File_List(File File_Path)
	{
		try
		{
			File File_List = new File(path+"INTERMEDIATE_FILES/File_List.txt");
			FileWriter fWriter = new FileWriter(File_List);
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
