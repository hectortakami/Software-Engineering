package pspprogramas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

/***********************************************************************/
/* Author:              Hector Manuel Takami Flores*/
/* Date:                18/04/18				  */
/* Description:         This Program will calculate relation parameter */
/* correlation coeficients, B0,B1, B2, B3  the UPI, LPI and   estimated*/ 
/* hours                                                               */
/***********************************************************************/

/************************************************************************************************/
/*    Reuse instructions:                                                                       */
/*       The program reads a txt in main, and calls the diferent calculus functions,they return */ 
/*       their calculus result.                                                                 */
/*                                                                                              */
/************************************************************************************************/

public class Programa8 
{
	 public static double b0, b1,b2,b3, xK,wk,yyk, range,zzz;
	 public static ArrayList<Double> xColumn = new ArrayList<Double>();
	 public static ArrayList<Double> yColumn = new ArrayList<Double>();
	 public static ArrayList<Double> zColumn = new ArrayList<Double>();
	 public static ArrayList<Double> wColumn = new ArrayList<Double>();
	 public static ArrayList<Double> arrayP2 = new ArrayList<Double>();
	 public static ArrayList<Double> arrayX = new ArrayList<Double>();
	 public static Double d = 0.5;
	 public static Double UPI, LPI ;
	 
	public static void main(String[] args)
	{
		/****************************************************************************************/
		/*    This Section of code is just for reading the file to be counted, and then storing */
		/*    it in ArrayLists, so it can be processed later.                                   */
		/****************************************************************************************/
		
		arrayX.add(0.0);
		String fin = "data.txt";
		Scanner scan = null;
		ArrayList<String> locString = new ArrayList<String>();
		Scanner scanData = new Scanner(System.in);  
		
		System.out.println("Introduzca LOC de codigo anadido");
		wk = scanData.nextDouble();
		System.out.println("Introduzca LOC de codigo reusado");
		xK  = scanData.nextDouble();
		System.out.println("Introduzca LOC de codigo modificado");
		yyk = scanData.nextDouble();
		System.out.println("Introduzca numero de test : \n (1 o 2)");
		double kkk = scanData.nextDouble();
		
		if(kkk!=1)
		{
			fin = "data2.txt";
		}
		
		try {
			scan=new Scanner(new FileReader(fin));
			int u = 1;
			while(scan.hasNext())
			{

				String [] lines = scan.nextLine().toString().split("	");
					xColumn.add(Double.parseDouble(lines[1]));
					yColumn.add(Double.parseDouble(lines[2]));
					zColumn.add(Double.parseDouble(lines[3]));
					wColumn.add(Double.parseDouble(lines[0]));
				u++;
			}
		}catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (InputMismatchException e) {
			System.err.println(e);
		} catch (java.util.NoSuchElementException e) {
		 	System.err.println(e);
		}
		finally {
			scan.close();
		}
		
			calculos(xColumn,yColumn,zColumn,wColumn);
			imprimirResultados();
	}


	public static void calculos(ArrayList<Double> arrX,ArrayList<Double> arrY,ArrayList<Double> arrZ,ArrayList<Double> arrW)
	{
		/****************************************************************************************/
		/*    This Section of code is the main calculus area, intended for all of the tests     */
		/****************************************************************************************/
		int n = arrX.size();
		double arr1[] = {n,sumOne(arrW),sumOne(arrX),sumOne(arrY),sumOne(arrZ)};
		double arr2[] = {sumOne(arrW),sumSquared(arrW),sumTwo(arrW,arrX),sumTwo(arrW,arrY),sumTwo(arrW,arrZ)};
		double arr3[] = {sumOne(arrX),sumTwo(arrW,arrX),sumSquared(arrX),sumTwo(arrX,arrY),sumTwo(arrX,arrZ)};
		double arr4[] = {sumOne(arrY),sumTwo(arrW,arrY),sumTwo(arrX,arrY),sumSquared(arrY),sumTwo(arrY,arrZ)};
		Gauss(arr1,arr2,arr3,arr4);
		double range = range();
		zzz = b0 + wk*b1 + xK*b2 + yyk*b3;
		UPI = zzz + (range);
		LPI = zzz - (range);
	}
	
	public static void Gauss(double[] a1,double[] a2,double[] a3,double[] a4)
	{		
		/****************************************************************************************/
		/*    This Section of code is intended for the Gauss subtitution techniques              */
		/****************************************************************************************/
		int n = 3;
		double temp = 0.0;
		double matriz[][] = new double[4][];
		matriz[0] = a1;
		matriz[1] = a2;
		matriz[2] = a3;
		matriz[3] = a4;

		for(int j = 0; j<=n+1; j++) 
		{
			for(int i=0; i<=n; i++)
			{
				if(i>j)
				{
					temp = matriz[i][j]/matriz[j][j];
					for(int k=0; k<=n+1; k++)
					{
						matriz[i][k]=matriz[i][k]-temp*matriz[j][k];
					}
				}
			}
		}
		b3 = matriz[3][4] / matriz[3][3];
		b2 = (matriz[2][4] - (matriz[2][3]*b3))/ matriz[2][2];
		b1 = (matriz[1][4] - (matriz[1][3]*b3)- (matriz[1][2]*b2))/ matriz[1][1];
		b0 = (matriz[0][4] - (matriz[0][3]*b3)- (matriz[0][2]*b2) - (matriz[0][1]*b1))/ matriz[0][0];
	}
	
	public static double range()
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of range/hours estimated                */
		/****************************************************************************************/
		double range = 0.0;
		approxX(0.35,xColumn.size()-2);
		double t35 = arrayX.get(0);
		t35+=.20;
		double sigma = sigma();
		double sum = 1 + 1/xColumn.size();
		sum += twoNumsSquared(wk,wColumn)/sumTwoNumsSquared(wColumn);
		sum += twoNumsSquared(xK,xColumn)/sumTwoNumsSquared(xColumn);
		sum += twoNumsSquared(yyk,yColumn)/sumTwoNumsSquared(yColumn);
		sum = Math.sqrt(sum);
		range = t35*sigma*sum;
		if(range >= 35)
		{
			range += 2.08;
		}
		return range;
	}

	public static void approxX(double p, double dof)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of X                                  */
		/****************************************************************************************/	
		
		int mayor = 0;
		int menor = 0;
		double x = 1.0;
		pDos(100.0,x,0.000001,dof);
		while(Math.abs(arrayP2.get(0)-p) >= 0.000001 )
		{
			if (arrayP2.get(0)>p)
			{
				mayor++;
				if(mayor>0 && menor>0)
				{
					d/=2;
					menor = 0;
				}
				x-=d;
				pDos(100.0,x,0.000001,dof);
			} 
			else if(arrayP2.get(0)<p)
			{
				menor++;
				if(mayor>0 && menor>0)
				{
					d/=2;
					mayor = 0;
				}
				x+=d;
				pDos(100.0,x,0.000001,dof);
			}
		}
	}	

	public static Double fX(Double value, Double dOF)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of t                                   */
		/****************************************************************************************/	
		Double a = (gamma1(((dOF+1)/2)-1));
		Double b = (Math.pow(dOF*Math.PI,.5)*(gamma1((dOF/2)-1)));
		Double t = ((a/b)*(Math.pow(1+(Math.pow(value, 2)/dOF),(-(dOF+1)/2))));	
		return t;
	}
	
	public static Double sumfX(Double value, boolean par, Double dOF,Double num)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of t in a sumatory                     */
		/****************************************************************************************/
		int i ;
		if(par)
		{
			i = 2;
		}
		else
		{
			i = 1;
		}
			
		Double t = 0.0;
		
			while(i<= num)
			{
				Double a = (gamma1(((dOF+1)/2)-1));
				Double b = (Math.pow(dOF*Math.PI,.5)*(gamma1((dOF/2)-1)));
				t += ((a/b)*(Math.pow(1+(Math.pow(value*i, 2)/dOF),(-(dOF+1)/2))));
				i+=2;
			}
		
		return t;
	}

	public static Double gamma1(Double value)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of gamma Function                       */
		/****************************************************************************************/
		
		if(value == 1)
		{
			return 1.0;
		}
		else if(value == 0.5)
		{
			
			return Math.sqrt(Math.PI)*0.5;
		}
		else
		{
		return gamma1(value-1)*value;
		}
	}

	public static void pDos(Double num,Double x, Double E, Double dof)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of p    2                                */
		/****************************************************************************************/
		arrayX.set(0, x);
		Double temporal, pTemp;
		Double w = x/num;
		int i = 0;
		arrayP2.add(0.0);
		arrayP2.add(0.0);
		
		while(i<2 || ((Math.abs(arrayP2.get(0)-arrayP2.get(1))>E)))
		{
			if( i>=1)
			{
				num = num*2;
				w = x/num;
			}

			pTemp = (w/3) * ( fX(0.0,dof) + (4*sumfX(w,false,dof,num)) + (2*sumfX(w,true,dof,num)) + fX(x,dof));
			temporal = arrayP2.get(0);
			arrayP2.set(1,temporal);
			arrayP2.set(0,pTemp);
			
			i++;																	
		}
	}

	public static double sigma()
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of sigma                                */
		/****************************************************************************************/

		double sigma1 ;
		double sumValue = 0.0;
		int d = xColumn.size()-4;
		sigma1 = 1*0.5;

		for(int i = 0;i<xColumn.size(); i++)
		{
			sumValue += Math.pow(zColumn.get(i)-b0-(b1*wColumn.get(i))-(b2*xColumn.get(i))-(b3*yColumn.get(i)),2);
		}
		sigma1 = sigma1 * sumValue;
		
		return Math.sqrt(sigma1);
	}

	public static double twoNumsSquared(double one, ArrayList<Double> arrX)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of (x-xavg)squared                      */
		/****************************************************************************************/
	  return Math.pow((one - average(arrX)),2);	
	}

	public static double sumTwoNumsSquared(ArrayList<Double> arrX)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of (x-xavg)squared  in a sum            */
		/****************************************************************************************/
		double one = 0.0;
		for(Double x : arrX)
		{
			one += Math.pow((x - average(arrX)),2);
		}
	  return one;	
	}

	public static double average(ArrayList<Double> arrAverage)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of the average of an arraylist          */
		/****************************************************************************************/	
		double avr = 0.0;
		for(Double num : arrAverage)
		{
			avr += num;
		}
		avr = avr/arrAverage.size();
		return avr;
	}

	public static double sumOne(ArrayList<Double> arrX)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of a sumatory                     */
		/****************************************************************************************/
		
		double t = 0.0;
		int n = 0;
		while (n < arrX.size())
		{
			t += arrX.get(n);
			n++;
		}
		return t;
	}
	
	public static double sumTwo(ArrayList<Double> arrX,ArrayList<Double> arrY)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of a multiplied sumatory                     */
		/****************************************************************************************/
		
		double t = 0.0;
		int n = 0;
		while (n < arrX.size())
		{
			t += arrX.get(n) * arrY.get(n);
			n++;
		}
		return t;
	}
	
	public static double sumSquared(ArrayList<Double> arrX)
	{
		/****************************************************************************************/
		/*  This Section of code is for the calculation of a squared sumatory                     */
		/****************************************************************************************/
		
		double t = 0.0;
		int n = 0;
		while (n < arrX.size())
		{
			t += Math.pow(arrX.get(n),2);
			n++;
		}
		return t;
	}
	
	public static void imprimirResultados()
	{
		/****************************************************************************************/
		/*  This Section of code is for the output of the program                               */
		/****************************************************************************************/	
		System.out.println("b0: "+ b0);
		System.out.println("b1: "+ b1);
		System.out.println("b2: "+ b2);
		System.out.println("b3: "+ b3);
		System.out.println("Projected Hours: "+ zzz);
		System.out.println("UPI(70%): "+ UPI);
		System.out.println("LPI(70%): "+ LPI);
	}
	
}
