package enrollmentprocessor;

/**
 * Coding exercise: Availity receives enrollment files from various benefits
 * management and enrollment solutions (I.e. HR platforms, payroll platforms).
 * Most of these files are typically in EDI format. However, there are some
 * files in CSV format. For the files in CSV format, write a program that will
 * read the content of the file and separate enrollees by insurance company in
 * its own file. Additionally, sort the contents of each file by last and first
 * name (ascending). Lastly, if there are duplicate User Ids for the same
 * Insurance Company, then only the record with the highest version should be
 * included. The following data points are included in the file:
 *
 * -User Id (string) 
 * -First and Last Name (string) 
 * -Version (integer) 
 * -Insurance Company (string)
 */
public class App {
	public static EnrollmentProcessor processor;
	
	public static void main(String[] args) 
	{
		processor = new EnrollmentProcessor(args[0]);
		processor.process();
	}
}
