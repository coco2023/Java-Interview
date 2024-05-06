package Lecture17;

import java.util.Random;

public class Client2 {

	public static void main(String[] args) {
		LinkedList<Person> test = new LinkedList<>();
		Random random = new Random();
		test.AddFirst(new Person(random.nextInt(100), "Hello"));
		test.AddFirst(new Person(random.nextInt(100), "Iello"));
		test.AddFirst(new Person(random.nextInt(100), "Jello"));
		test.AddFirst(new Person(random.nextInt(100), "Kello"));
		test.AddFirst(new Person(random.nextInt(100), "Lello"));
		test.AddFirst(new Person(random.nextInt(100), "Mello"));
		test.AddFirst(new Person(random.nextInt(100), "Nello"));
		test.display();
		// test.reverse_recursively_data();
		// test.display();
		test.display();
	}

	static class Person implements Comparable<Person> {
		int age;
		String name;

		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Person o) {
			return this.name.compareTo(o.name);
		}

		@Override
		public String toString() {
			String retVal = "[Age: " + this.age + " , Name: " + this.name + "]";
			return retVal;
		}

	}

	static class Student implements Comparable<Student> {
		int marks;
		String name;

		public Student(int marks, String name) {
			this.marks = marks;
			this.name = name;
		}

		@Override
		public int compareTo(Student o) {
			return this.marks - o.marks;
		}

		public String toString() {
			String retVal = "[Marks: " + this.marks + " , Name: " + this.name + "]";
			return retVal;
		}

	}
}
