/******************************************************************/
/* Program Assignment:             7                                                       
/* Name:                           Hector Manuel Takami Flores                                   
/* Date:                           7/04/18                
/* Description:                    Find values
/******************************************************************/

import java.util.ArrayList;
import java.util.Scanner;

public class Program7 {
    
      	public static double mean(ArrayList<Double> cal){
		double sumFloat = 0;
		double averageFloat = 0;
		for (int i = 0; i < cal.size(); i++){
			sumFloat += cal.get(i);
		}
		averageFloat = sumFloat / cal.size();
		return averageFloat;
	}
	//End
	//Start
	public static double B2(double beta1, double xAverage, double yAverage){
		double cal = yAverage - beta1*xAverage;
		return cal;
	}
	//End
	//Start
	public static double B1(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList, double xAverage, double yAverage){
		double cal = 0;
		cal = multiple(xArrayList, yArrayList)-(xArrayList.size()*xAverage*yAverage);
		cal = cal / (multiple(xArrayList, xArrayList) - xArrayList.size()*xAverage*xAverage);
		return cal;
	}
	//End
	//Start
	public static double calR(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList){
		double cal = 0;
		cal = xArrayList.size()*multiple(xArrayList, yArrayList)-multiple(xArrayList)*multiple(yArrayList);
		double minus = 0;
		minus = xArrayList.size()*multiple(xArrayList, xArrayList)-multiple(xArrayList)*multiple(xArrayList);
		minus *= yArrayList.size()*multiple(yArrayList, yArrayList)-multiple(yArrayList)*multiple(yArrayList);
		cal = cal / Math.sqrt(minus);
		return cal;
	}
	//End
	//Start
	public static double multiple(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList){
		double cal = 0;
		for (int i = 0; i < xArrayList.size(); i++){
			cal += xArrayList.get(i)*yArrayList.get(i);
		}
		return cal;
	}
	//End
	//Start
	public static double multiple(ArrayList<Double> xArrayList){
		double cal = 0;
		for (int i = 0; i < xArrayList.size(); i++){
			cal += xArrayList.get(i);
		}
		return cal;
	}
	//End
	//Start
	public static double gamma(double x){
		double ans = 1;
		while (x > 1){			
			x = x - 1;
			ans = ans * x;
		}
		if (Math.abs(x-1/2)< 1) return ans*Math.sqrt(Math.PI); else return ans; 
	}
	//End
	//Start
	public static double f(double x,double dof){
		double cal = 0;
		cal = Math.pow((1 + x * x / dof), -(dof + 1) / 2) * gamma((dof + 1) / 2);
		cal = cal / (Math.sqrt(dof * Math.PI) * gamma(dof / 2)); 
		return cal;
	}
	//End
	//Start
	public static double tailArea(double r, ArrayList<Double> arrayList){
		double x = Math.abs(r) * Math.sqrt(arrayList.size() - 2) / Math.sqrt(1 - r * r);
		return 1 - 2 * integration(x, arrayList.size() - 2);			
	}
	//End
	//Start
	public static double integration(double x,double dof){
		int num_seg = 10;
		final double E = 0.00001;
		double answer_old = 0, answer_new = 0;
		do{
			double w = x / num_seg;
			answer_old = answer_new;
			answer_new = 0;
			for (int i = 1; i < num_seg; i++){
				if (i % 2 == 0){
					answer_new += 2 * f(i*w,dof);
				}else{
					answer_new += 4 * f(i*w,dof);
				}
			}
			answer_new += f(0,dof) + f(x,dof);
			answer_new *= w / 3;
			num_seg *= 2;
		}while(Math.abs(answer_old - answer_new) > E);
		return answer_new;
	}
	//End
	//Start
	public static double X(double p,double dof){
		double x = 1.0;
		double d = 0.5;
		boolean isBig = false;		
		int num_seg = 1000;
		final double E = 0.00000001;
		double answer = 0;
		while(Math.abs(answer - p) > E){
			double w = x / num_seg;			
			answer = 0;
			for (int i = 1; i < num_seg; i++){
				if (i % 2 == 0){
					answer += 2 * f(i*w,dof);
				}else{
					answer += 4 * f(i*w,dof);
				}
			}
			answer += f(0,dof) + f(x,dof);
			answer *= w / 3;
			if (Math.abs(answer - p) > E){
				if (isBig){
					if (answer <= p) {
						d /= 2;
						isBig = !isBig;
					}
					x -= d;
				}else{
					if (answer >= p) {
						d /= 2;
						isBig = !isBig;
					}
					x += d;
				}
			}
		}
		return x;
	}
	//End
	//Start
	public static double sigma(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList,double b0, double b1){
		ArrayList<Double> cal = new ArrayList<Double>();
		for (int i = 0; i < xArrayList.size(); i++){
			cal.add(yArrayList.get(i) - b0 - b1 * xArrayList.get(i));
		}
		return Math.sqrt(multiple(cal,cal) / (xArrayList.size() - 2));
	}			
	//End
	//Start
	public static double range(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList,double b0, double b1,double xk){
		double range = X(0.35, xArrayList.size() - 2) * sigma(xArrayList, yArrayList, b0, b1);
		ArrayList<Double> cal = new ArrayList<Double>();
		for (int i = 0; i < xArrayList.size(); i++){
			cal.add(xArrayList.get(i) - mean(xArrayList));
		}		
		return range * Math.sqrt(1 + (1.0 / xArrayList.size()) + ((xk - mean(xArrayList)) * (xk - mean(xArrayList)) / multiple(cal,cal)));
	}
        
        
        public static void main(String[] args){
                double x,y;
                ArrayList<Double> xArrayList = new ArrayList<Double>();
                ArrayList<Double> yArrayList = new ArrayList<Double>();

                for(int i=0; i<10; i++){
                Scanner sc = new Scanner(System.in);
                System.out.println("\n\n*** Ingresa valor de X***\n");   
                x=sc.nextDouble();
                xArrayList.add(x);}
                System.out.println(xArrayList);

                for(int i=0; i<10; i++){
                Scanner sc = new Scanner(System.in);
                System.out.println("\n\n*** Ingresa valor de Y***\n");   
                y=sc.nextDouble();
                yArrayList.add(y);
                }

                
		double xAverage = mean(xArrayList);
		double yAverage = mean(yArrayList);
		
		double b1 = B1(xArrayList,yArrayList,xAverage,yAverage);
		double b0 = B2(b1,xAverage,yAverage);
		double r = calR(xArrayList,yArrayList);
		
		double xk = 386 ;
		double yi = b0 + b1 * xk;
		double range = range(xArrayList, yArrayList, b0, b1, xk);
		
		System.out.format("r=%.9f\n",r);
		System.out.format("r^2=%.8f\n",r*r);
		System.out.format("tailArea=%.10f\n",tailArea(r, xArrayList));
		System.out.format("beta0=%.8f\n",b0);
		System.out.format("beta1=%.9f\n",b1);
		System.out.format("yk=%7f\n",yi);
		System.out.format("Range=%.7f\n",range);
		System.out.format("UPI=%.7f\n",(yi+range));
		System.out.format("LPI=%.7f\n",(yi-range));
		
                
	}

    
}
