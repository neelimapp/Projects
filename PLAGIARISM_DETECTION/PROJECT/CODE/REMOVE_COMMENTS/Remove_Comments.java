import java.io.*;
import java.util.*;

public class Remove_Comments
{

public static String path;	
	
	public static void main(String[] args)
	{
		path=args[0];
		path=path.replace("INPUT_FILES/","");
		String Preprocessed_File_List;
		String OutPath = path+"INTERMEDIATE_FILES/REMOVE_COMMENTS/";
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
				Process process = Runtime.getRuntime().exec("./Remove_Comments "+Preprocessed_File_List+" "+OutPath+File_Name+".out");
			}
		}
		catch(Exception e) {}
	}
}
