import java.io.*;
import java.util.*;

public class Tokenizer
{

	public static String path;

	private static Tokenizer check = new Tokenizer();	
	
	public static void main(String[] args)
	{
	
		path=args[0];
		path=path.replace("INPUT_FILES/","");
				
		String Preprocessed_File_List;
		String OutPath = path+"INTERMEDIATE_FILES/TOKENIZED_FILES/";
		String File_Name=null;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/File_List.txt"));
			while((Preprocessed_File_List = br.readLine()) != null)
			{
				StringTokenizer stk=new StringTokenizer(Preprocessed_File_List,"/");
        		while(stk.hasMoreTokens())
        		{
            		File_Name=stk.nextToken();
        		}
				Process process = Runtime.getRuntime().exec("./Tokenizer "+Preprocessed_File_List+" "+OutPath+File_Name+".token "+OutPath+File_Name+".line");
			}
			
			String File_Path = path+"INTERMEDIATE_FILES/TOKENIZED_FILES/";
			check.File_List(new File(File_Path));
			
		}
		catch(Exception e) {}
	}
	
	private void File_List(File File_Path)
	{
		try
		{
			File Tokenized_File_List = new File(path+"INTERMEDIATE_FILES/Tokenized_File_List.txt");
			FileWriter fWriter = new FileWriter(Tokenized_File_List);
    		PrintWriter pWriter = new PrintWriter(fWriter);
			File[] Files = File_Path.listFiles();
			for(int i = 0;i < Files.length;i++)
			{
				if(!Files[i].isDirectory())
				{
					String str=Files[i].toString();
					if(str.contains(".token"))
						pWriter.println(Files[i]);
				}
			}
			pWriter.close();
		}
		catch(Exception e) {}
	}
	
}
