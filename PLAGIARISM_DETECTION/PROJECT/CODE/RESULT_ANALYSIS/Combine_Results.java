import java.io.*;
import java.util.*;

public class Combine_Results
{

	public static TreeSet<String> Verbatim_Check = new TreeSet<String>();
	public static TreeSet<String> Whitespace_Formatting = new TreeSet<String>();
	public static TreeSet<String> Remove_Comments = new TreeSet<String>();
	public static TreeSet<String> Formatting_Comments = new TreeSet<String>();
	public static TreeSet<String> Identifiers_Datatypes = new TreeSet<String>();
	public static TreeSet<String> Block_Copy = new TreeSet<String>();
	public static TreeSet<String> Code_Blocks = new TreeSet<String>();
	public static String path;
	
	public static void main(String[] args)
	{
		path=args[0];
		path=path.replace("INPUT_FILES/","");
		File_Convert fc = new File_Convert();
		String File_1,File_2,File_3,File_4,File_5,File_6,File_7;
		try
		{
			File_1=fc.readFile(path+"DATABASE_FILES/Verbatim_Check_Result.txt");
			String[] Verbatim=File_1.split("\n");
			for(int i=0;i<Verbatim.length;i++)
			{
				Verbatim_Check.add(Verbatim[i]);
			}
			
			File_2=fc.readFile(path+"DATABASE_FILES/Whitespace_Formatting_Result.txt");
			String[] Whitespace=File_2.split("\n");
			String Whitespace_Reverse;
			for(int i=0;i<Whitespace.length;i++)
			{
				String[] Reverse=Whitespace[i].split(" ");
				Whitespace_Reverse=Reverse[1]+" "+Reverse[0];
				
				if(!Verbatim_Check.contains(Whitespace[i])&&!Verbatim_Check.contains(Whitespace_Reverse))
				{
					Whitespace_Formatting.add(Whitespace[i]);
				}
			}
			
			File_3=fc.readFile(path+"DATABASE_FILES/Remove_Comments_Result.txt");
			String[] Comments=File_3.split("\n");
			String Comments_Reverse;
			for(int i=0;i<Comments.length;i++)
			{
				String[] Reverse=Comments[i].split(" ");
				Comments_Reverse=Reverse[1]+" "+Reverse[0];
				
				if(!Verbatim_Check.contains(Comments[i])&&!Verbatim_Check.contains(Comments_Reverse))
				{
					Remove_Comments.add(Comments[i]);
				}
			}
			
			File_4=fc.readFile(path+"DATABASE_FILES/Formatting_Comments_Result.txt");
			String[] Formatting=File_4.split("\n");
			String Formatting_Reverse;
			for(int i=0;i<Formatting.length;i++)
			{
				String[] Reverse=Formatting[i].split(" ");
				Formatting_Reverse=Reverse[1]+" "+Reverse[0];
				
				if(!Verbatim_Check.contains(Formatting[i])&&!Verbatim_Check.contains(Formatting_Reverse)&&!Whitespace_Formatting.contains(Formatting[i])&&!Whitespace_Formatting.contains(Formatting_Reverse)&&!Remove_Comments.contains(Formatting[i])&&!Remove_Comments.contains(Formatting_Reverse))
				{
					Formatting_Comments.add(Formatting[i]);
				}
			}
			
			File_5=fc.readFile(path+"DATABASE_FILES/Identifiers_Datatypes_Result.txt");
			String[] Identifiers=File_5.split("\n");
			String Identifiers_Reverse;
			for(int i=0;i<Identifiers.length;i++)
			{
				String[] Reverse=Identifiers[i].split(" ");
				Identifiers_Reverse=Reverse[1]+" "+Reverse[0];
				
				if(!Verbatim_Check.contains(Identifiers[i])&&!Verbatim_Check.contains(Identifiers_Reverse)&&!Whitespace_Formatting.contains(Identifiers[i])&&!Whitespace_Formatting.contains(Identifiers_Reverse)&&!Remove_Comments.contains(Identifiers[i])&&!Remove_Comments.contains(Identifiers_Reverse)&&!Formatting_Comments.contains(Identifiers[i])&&!Formatting_Comments.contains(Identifiers_Reverse))
				{
					Identifiers_Datatypes.add(Identifiers[i]);
				}
			}
			
			File_6=fc.readFile(path+"DATABASE_FILES/BlockCopy_Result.txt");
			String[] Blocks=File_6.split("\n");
			String Blocks_Reverse;
			for(int i=0;i<Blocks.length;i=i+2)
			{
				String[] Reverse=Blocks[i].split(" ");
				Blocks_Reverse=Reverse[1]+" "+Reverse[0];
				
				if(!Verbatim_Check.contains(Blocks[i])&&!Verbatim_Check.contains(Blocks_Reverse)&&!Whitespace_Formatting.contains(Blocks[i])&&!Whitespace_Formatting.contains(Blocks_Reverse)&&!Remove_Comments.contains(Blocks[i])&&!Remove_Comments.contains(Blocks_Reverse)&&!Formatting_Comments.contains(Blocks[i])&&!Formatting_Comments.contains(Blocks_Reverse)&&!Identifiers_Datatypes.contains(Blocks[i])&&!Identifiers_Datatypes.contains(Blocks_Reverse))
				{
					Block_Copy.add(Blocks[i]);
				}
			}
			
			File_7=fc.readFile(path+"DATABASE_FILES/CodeBlocks_Result.txt");
			String[] Structure=File_7.split("\n");
			String Structure_Reverse;
			for(int i=0;i<Structure.length;i++)
			{
				String[] Reverse=Structure[i].split(" ");
				Structure_Reverse=Reverse[1]+" "+Reverse[0];
				
				if(!Verbatim_Check.contains(Structure[i])&&!Verbatim_Check.contains(Structure_Reverse)&&!Whitespace_Formatting.contains(Structure[i])&&!Whitespace_Formatting.contains(Structure_Reverse)&&!Remove_Comments.contains(Structure[i])&&!Remove_Comments.contains(Structure_Reverse)&&!Formatting_Comments.contains(Structure[i])&&!Formatting_Comments.contains(Structure_Reverse)&&!Identifiers_Datatypes.contains(Structure[i])&&!Identifiers_Datatypes.contains(Structure_Reverse)&&!Block_Copy.contains(Structure[i])&&!Block_Copy.contains(Structure_Reverse))
				{
					Code_Blocks.add(Structure[i]);
				}
			}
			
			File Report = new File(path+"DATABASE_FILES/Final_Report.txt");
			FileWriter fWriter = new FileWriter(Report);
    		PrintWriter pWriter = new PrintWriter(fWriter);
    		
    		Iterator Set_1 =Verbatim_Check.iterator();
    		while(Set_1.hasNext())
    		{
      			Object object_1 = Set_1.next();
      			pWriter.println(object_1+" Verbatim");
    		}
    		
    		Iterator Set_2 =Whitespace_Formatting.iterator();
    		while(Set_2.hasNext())
    		{
      			Object object_2 = Set_2.next();
      			pWriter.println(object_2+" Whitespace");
    		}
    		
    		Iterator Set_3 =Remove_Comments.iterator();
    		while(Set_3.hasNext())
    		{
      			Object object_3 = Set_3.next();
      			pWriter.println(object_3+" Comments");
    		}
    		Iterator Set_4 =Formatting_Comments.iterator();
    		while(Set_4.hasNext())
    		{
      			Object object_4 = Set_4.next();
      			pWriter.println(object_4+" Formatting");
    		}
    		Iterator Set_5 =Identifiers_Datatypes.iterator();
    		while(Set_5.hasNext())
    		{
      			Object object_5 = Set_5.next();
      			pWriter.println(object_5+" ID");
    		}
    		Iterator Set_6 =Block_Copy.iterator();
    		while(Set_6.hasNext())
    		{
      			Object object_6 = Set_6.next();
      			pWriter.println(object_6+" Block");
    		}
    		Iterator Set_7 =Code_Blocks.iterator();
    		while(Set_7.hasNext())
    		{
      			Object object_7 = Set_7.next();
      			pWriter.println(object_7+" Structure");
    		}
    		
    		pWriter.close();
		}
		catch(Exception e) {}
		
	}
}
