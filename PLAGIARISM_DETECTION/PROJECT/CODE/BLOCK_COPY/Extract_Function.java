import java.io.*;
import java.util.*;

public class Extract_Function
{

	public static String path;
	
	public static void main(String[] args) 
	{ 
		try
		{
			path=args[0];
			path=path.replace("INPUT_FILES/","");
			String Tokenized_File_List;
			String File_Name=null;
			BufferedReader br = new BufferedReader(new FileReader(path+"INTERMEDIATE_FILES/File_List.txt"));
			while((Tokenized_File_List = br.readLine()) != null)
			{
				StringTokenizer stk=new StringTokenizer(Tokenized_File_List,"/");
        			while(stk.hasMoreTokens())
        			{
            			File_Name=stk.nextToken();
        			}
        		File_Name=File_Name.replace(".token","");
				RetrieveFunction(File_Name);
			}
			String File_Path = path+"INTERMEDIATE_FILES/BLOCK_COPY/";
			File_List(new File(File_Path));
		}
		catch(Exception e) {}
		
	}

	public static void RetrieveFunction(String File_Name)
	{
		File_Convert fc = new File_Convert();
		try
		{
			String Token_File = fc.readFile(path+"INTERMEDIATE_FILES/TOKENIZED_FILES/"+File_Name+".token");
			String Line_File = fc.readFile(path+"INTERMEDIATE_FILES/TOKENIZED_FILES/"+File_Name+".line");
			FileWriter fWriter_1 = new FileWriter(path+"INTERMEDIATE_FILES/BLOCK_COPY/"+File_Name+".token");
    		PrintWriter pWriter_1 = new PrintWriter(fWriter_1);
    		FileWriter fWriter_2 = new FileWriter(path+"INTERMEDIATE_FILES/BLOCK_COPY/"+File_Name+".line");
    		PrintWriter pWriter_2 = new PrintWriter(fWriter_2);
    		
			Stack<Character> stack = new Stack<Character>();
			String[] Line=Line_File.split(" ");
			
			for(int i=0;i<Token_File.length();i++)
			{
		
				char PrevToken;
				if(i==0) PrevToken=' ';
				else PrevToken=Token_File.charAt(i-1);
			
				if((PrevToken=='A'||PrevToken=='s')&&Token_File.charAt(i)=='u'&&stack.empty())
				{	
					stack.push(Token_File.charAt(i));
				}
				else if(Token_File.charAt(i)=='u'&&!stack.empty())
				{
					stack.push(Token_File.charAt(i));
					pWriter_1.print(Token_File.charAt(i));
					pWriter_2.print(Line[i]);
					pWriter_2.print(" ");
				}
				else if(Token_File.charAt(i)=='v'&&stack.size()>1)
				{
					stack.pop();
					pWriter_1.print(Token_File.charAt(i));
					pWriter_2.print(Line[i]);
					pWriter_2.print(" ");
				}
				else if(Token_File.charAt(i)=='v'&&stack.size()==1)
				{
					stack.pop();
					pWriter_1.print("-");
					pWriter_2.print("-");
					pWriter_2.print(" ");
				}
				else if(Token_File.charAt(i)!='u'&&Token_File.charAt(i)!='v'&&!stack.empty())
				{
					pWriter_1.print(Token_File.charAt(i));
					pWriter_2.print(Line[i]);
					pWriter_2.print(" ");
				}
			}
			pWriter_1.close();
			pWriter_2.close();
		}
		catch(Exception e) {}
	}
	
	private static void File_List(File File_Path)
	{
		try
		{
			File Tokenized_File_List = new File(path+"INTERMEDIATE_FILES/Blockcopy_File_List.txt");
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
