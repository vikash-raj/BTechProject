import java.util.*;
import java.io.*;
import java.text.*;
class ModifyData{
	public static void main(String arg[])throws Exception{
        Scanner in=new Scanner(new File("CNS.txt"));
		PrintWriter class0=new PrintWriter("CNS_class_0.txt");
		PrintWriter class1=new PrintWriter("CNS_class_1.txt");
        while(in.hasNext()){
			String s=in.nextLine().replaceAll(","," ");
			String str[]=s.split(" ");
			if(str[str.length-1].compareTo("0")==0)
				class0.println(s);
			else
				class1.println(s);
		}
		in.close();
		class0.close();
		class1.close();
	}
} 