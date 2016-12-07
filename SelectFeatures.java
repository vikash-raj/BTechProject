import java.util.*;
import java.io.*;
import java.text.*;
class SelectFeatures{
	public static void main(String arg[])throws Exception{
		Scanner in=new Scanner(new File("Features.txt"));
		PrintWriter out=new PrintWriter(new File("newFeatures.txt"));
		while(in.hasNext()){
			String str=in.nextLine();
			String s[]=str.split(" ");
			out.println(s[1]);
		}
		in.close();
		out.close();
	}
}