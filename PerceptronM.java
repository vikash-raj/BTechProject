import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class PerceptronM{
	static int MAX_ITERATION=100;
	static double LEARNING_RATE=0.5;
	static int NO_OF_FEATURES=0;
	static int BIAS_COLUMN=(7130-1);//(7130-1)
	static int theta=0;
	static int MAX_SELECTED_FEATURES=100;
	public static void main(String arg[])throws Exception{
		Scanner class1Input=new Scanner(new File("CNS_class_1.txt"));//class N==0 ans class R==1;
		Scanner class11Input=new Scanner(new File("CNS_class_1.txt"));//replicate copy of class 1;
		Scanner class0Input=new Scanner(new File("CNS_class_0.txt"));
		Scanner attributes=new Scanner(new File("CNS_Attributes_Names.txt"));
		PrintWriter F_output=new PrintWriter(new File("FinalOutput.txt"));
		/*Scanner class1Input=new Scanner(new File("Class1DataSet.txt"));//class N==0 ans class R==1;
		Scanner class11Input=new Scanner(new File("Class1DataSet.txt"));//replicate copy of class 1;
		Scanner class0Input=new Scanner(new File("Class0DataSet.txt"));*/
		NO_OF_FEATURES=class11Input.nextLine().split(" ").length;
		//int targetOutput[]=new int[NO_OF_INPUTS];
		ArrayList<String> input=new ArrayList<String>();
		while(class1Input.hasNext()){
			input.add(class1Input.nextLine());
		}
		while(class0Input.hasNext()){
			input.add(class0Input.nextLine());
		}
		double weights[]=new double[NO_OF_FEATURES];
		for(int i=0;i<weights.length;i++){
			weights[i]=1;
		}
		double localError,globalError;
		int p,iteration,output;
		int NO_OF_INPUTS=input.size();
		iteration=0;
		do{
			iteration++;
			globalError=0;
			for(p=0; p < NO_OF_INPUTS; p++){
                String strIn[]=input.get(p).split(" ");				
				output=calculateOutput(theta,weights,strIn);
				//localError=Integer.parseInt(strIn[BIAS_COLUMN])-output;
				localError=getTargetOutput(strIn)-output;
				for(int j=0;j<weights.length;j++){					
					if(j!=BIAS_COLUMN){
						//System.out.println("input.get(j)="+strIn[j]);
						if(strIn[j].charAt(0)=='?')
				            strIn[j]='0'+"";
						weights[j] += LEARNING_RATE*localError*Double.parseDouble(strIn[j]);
					}
					else
						weights[j] += LEARNING_RATE*localError;
				}
				globalError += (localError * localError);
			}
			/* Root Mean Squared Error */
			System.out.println("Iteration "+iteration+" : RMSE = "+Math.sqrt(globalError/NO_OF_INPUTS));
		}while(globalError!=0&&iteration<=MAX_ITERATION);
		/*----------------------------------------------------------*/
		/*Arrays.sort(weights);
		          for(int j=0;j<weights.length;j++)
					  System.out.println("weights["+j+"]="+weights[j]);*/
		    featureSelection(weights,attributes,MAX_SELECTED_FEATURES,F_output,input);		  
		/*------------------------------------------------------------*/
		//System.out.println("\n=======\nDecision boundary equation:");
		//System.out.println(weights[1] +"*x + "+weights[2]+"*y +  "+weights[3]+"*z + "+weights[0]+" = 0");
		//Scanner testInput=new Scanner(new File("testDataSet.txt"));
		
	}
	static int calculateOutput(int theta, double weights[], String input[])
	{
		double sum=0.0d;
		for(int i=0;i<input.length;i++){
			if(input[i].charAt(0)=='?')
				input[i]='0'+"";
			if(i!=BIAS_COLUMN)
			 sum += Double.parseDouble(input[i])*weights[i];
		}
		sum=sum+weights[BIAS_COLUMN];
		return (sum >= theta) ? 1 : 0;
	}
    static int getTargetOutput(String inputArray[]){
		//String inputArray[]=str.split(" ");
		char ch=inputArray[BIAS_COLUMN].charAt(0);
		return (ch=='0') ? 0 : 1 ; 
	}
	static void featureSelection(double weights[],Scanner attributes,int MAX_SELECTED_FEATURES,PrintWriter F_output,ArrayList<String> input)throws Exception{
		int selectedAtttributes[]=new int[NO_OF_FEATURES];//to selecte attributes
		for(int i=0;i<selectedAtttributes.length;i++)
			selectedAtttributes[i]=i;
		sort(NO_OF_FEATURES-1,selectedAtttributes,weights);
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		map.put(NO_OF_FEATURES-1,1);
		
		for(int i=0;i<NO_OF_FEATURES-1;i++)//setting all features by importance 0
			map.put(i,0);
		for(int i=0;i<MAX_SELECTED_FEATURES;i++)
			map.put(selectedAtttributes[i],1);
		
		//---taking attributes names in array
		String attNames[]=new String[NO_OF_FEATURES];
		int k=0;
		while(attributes.hasNext()){
			attNames[k++]=attributes.nextLine();
		}
		for(int i=0;i<attNames.length;i++){
			if(map.get(i)==1){
				F_output.print(attNames[i]+" ");
				//System.out.print(attNames[i]+" ");
			}				
		}
		F_output.println();
		//System.out.println();
		for(int i=0;i<input.size();i++){
			String str[]=input.get(i).split(" ");
			for(int j=0;j<str.length;j++){
				if(map.get(j)==1){
					F_output.print(str[j]+" ");
					//System.out.print(str[i]+" ");
				}					
			}
			F_output.println();
			//System.out.println();
		}
		F_output.close();
	}
	static void sort(int n,int selectedAtttributes[],double weights[]){
		/*for(int j=0;j<n;j++){
		  for(int i=0;i<n-1;i++){
			  System.out.println(j+" "+i);
			  if(weights[i]>weights[i+1]){
				  swap(weights,i,i+1);
				  swap2(selectedAtttributes,i,i+1);
			  }
		  }
	  }*/
	}
	static void swap(double[] arr,int i,int j){
	  double temp=arr[i];
	  arr[i]=arr[j];
	  arr[j]=temp;
  }
  static void swap2(int[] arr,int i,int j){
	  int temp=arr[i];
	  arr[i]=arr[j];
	  arr[j]=temp;
  }
}