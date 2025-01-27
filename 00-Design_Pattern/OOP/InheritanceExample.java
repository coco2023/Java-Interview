class Animal {
          public void makeSound() {
                    System.out.println("Animal makes a sound");
          }
}

class Dog extends Animal {
          @Override
          public void makeSound() {
                    System.out.println("Dog makes a sound");
          }
}

class Cat extends Animal {
          @Override
          public void makeSound() {
                    System.out.println("Cat makes a sound");
          }
}

public class InheritanceExample {
          public static void main(String[] args) {
                    Animal animal = new Animal();
                    animal.makeSound();

                    Animal dog = new Dog();
                    dog.makeSound();

                    Animal cat = new Cat();
                    cat.makeSound();
          }
}
