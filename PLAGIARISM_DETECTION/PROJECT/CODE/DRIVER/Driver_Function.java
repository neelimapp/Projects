public class Driver_Function
{
	public static void main(String[] args)
	{
		Process process_1,process_2,process_3,process_4,process_5,process_6,process_7,process_8,process_9,process_10,process_11,process_12;
		try
		{
		String Path=args[0];
		process_1 = Runtime.getRuntime().exec("java Merge_Files "+Path);
		Thread.sleep(1000);
		process_2 = Runtime.getRuntime().exec("java Verbatim_Check "+Path);
		Thread.sleep(1000);
		process_3 = Runtime.getRuntime().exec("java Remove_Whitespace "+Path);
		Thread.sleep(1000); 
		process_4 = Runtime.getRuntime().exec("java Remove_Comments "+Path);
		Thread.sleep(1000);
		process_5 = Runtime.getRuntime().exec("java Diff_Remove_Comments "+Path);
		Thread.sleep(1000);
		process_6 = Runtime.getRuntime().exec("java Diff_Formatting_Comments "+Path);
		Thread.sleep(1000);
		process_7 = Runtime.getRuntime().exec("java Tokenizer "+Path);
		Thread.sleep(1000);
		process_8 = Runtime.getRuntime().exec("java Diff_Identifiers_Datatypes "+Path);
		Thread.sleep(1000);
		process_9 = Runtime.getRuntime().exec("java Extract_Function "+Path);
		Thread.sleep(1000);
		process_10 = Runtime.getRuntime().exec("java Block_Copy "+Path);
		Thread.sleep(1000);
		process_11 = Runtime.getRuntime().exec("java Run_Algorithm "+Path);
		Thread.sleep(1000);
		process_12 = Runtime.getRuntime().exec("java Combine_Results "+Path);
		}
		catch(Exception e) {}
	}
}
