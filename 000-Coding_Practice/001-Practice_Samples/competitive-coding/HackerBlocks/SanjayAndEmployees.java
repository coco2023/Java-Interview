package HackerBlocks;

import java.util.ArrayList;
import java.util.Scanner;

public class SanjayAndEmployees {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int X, N;
		ArrayList<Employee> arrayList = new ArrayList<>();
		X = scn.nextInt();
		N = scn.nextInt();
		for (int i = 0; i < N; i++) {
			String input = scn.next();
			int salary = scn.nextInt();
			if (salary > X) {
				arrayList.add(new Employee(input, salary));
			}
		}
		sort(arrayList, 0, arrayList.size() - 1, "salary");
		ArrayList<ArrayList<Integer>> indicesPair=new ArrayList<>();
		int salary=arrayList.get(0).getSalary();
		int startIndex=0,endIndex=0;
		for(int i=1;i<arrayList.size();i++){
			if(arrayList.get(i).getSalary()==salary){
				endIndex++;
				if(i==arrayList.size()-1){
					ArrayList<Integer> temp=new ArrayList<>();
					temp.add(startIndex);
					temp.add(endIndex);
					indicesPair.add(temp);
				}
			}
			
			else{
				ArrayList<Integer> temp=new ArrayList<>();
				temp.add(startIndex);
				temp.add(endIndex);
				endIndex=startIndex=i;
				salary=arrayList.get(i).getSalary();
				indicesPair.add(temp);
			}
		}
		for(int i=0;i<indicesPair.size();i++){
			if(indicesPair.get(i).get(1)>indicesPair.get(i).get(0)){
				sort(arrayList, indicesPair.get(i).get(0), indicesPair.get(i).get(1), "name");
			}
		}
		for(int i=0;i<arrayList.size();i++){
			System.out.println(arrayList.get(i).getName()+" "+arrayList.get(i).getSalary());
		}
		scn.close();
	}

	static int partition(ArrayList<Employee> arr, int low, int high, String param) {
		int pivot1 = 0;
		String pivot2 = "";
		if (param.equals("salary"))
			pivot1 = arr.get(high).getSalary();
		else
			pivot2 = arr.get(high).getName();
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (param.equals("salary")) {
				if (arr.get(j).getSalary() >= pivot1) {
					i++;
					Employee temp = arr.get(i);
					arr.set(i, arr.get(j));
					arr.set(j, temp);
				}
			} else {
				if (arr.get(j).getName().compareTo(pivot2) <= 0) {
					i++;
					Employee temp = arr.get(i);
					arr.set(i, arr.get(j));
					arr.set(j, temp);
				}
			}
		}
		Employee temp = arr.get(i + 1);
		arr.set(i + 1, arr.get(high));
		arr.set(high, temp);

		return i + 1;
	}

	static void sort(ArrayList<Employee> arr, int low, int high, String param) {
		if (low < high) {
			int pi = partition(arr, low, high, param);
			sort(arr, low, pi - 1, param);
			sort(arr, pi + 1, high, param);
		}
	}

	public static class Employee {
		private String name;
		private int salary;

		public Employee(String name, int salary) {
			this.name = name;
			this.salary = salary;
		}

		public Employee getEmployee() {
			return this;
		}

		public String getName() {
			return this.name;
		}

		public int getSalary() {
			return this.salary;
		}
	}
}
