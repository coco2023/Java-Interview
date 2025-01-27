interface Shape {
          void draw();
}

// Circle Class
class Circle implements Shape {
          private double radius;

          public Circle(double radius) {
                    this.radius = radius;
          }

          @Override
          public void draw() {
                    System.out.println("Drawing a Circle with radius: " + radius);
          }
}

// Rectangle Class
class Rectangle implements Shape {
          private double length;
          private double breadth;

          public Rectangle(double length, double breadth) {
                    this.length = length;
                    this.breadth = breadth;
          }

          @Override
          public void draw() {
                    System.out.println("Drawing a Rectangle with length: " + length + " and breadth: " + breadth);
          }
}

// Shape Factory
class ShapeFactory {
          public static Shape getShape(String shapeType, double... parameters) {
                    if (shapeType == null || parameters == null) {
                              throw new IllegalArgumentException("Shape type or parameters cannot be null.");
                    }

                    switch (shapeType.toUpperCase()) {
                              case "CIRCLE":
                                        if (parameters.length != 1) {
                                                  throw new IllegalArgumentException(
                                                                      "Circle requires 1 parameter: radius.");
                                        }
                                        return new Circle(parameters[0]);

                              case "RECTANGLE":
                                        if (parameters.length != 2) {
                                                  throw new IllegalArgumentException(
                                                                      "Rectangle requires 2 parameters: length and breadth.");
                                        }
                                        return new Rectangle(parameters[0], parameters[1]);

                              default:
                                        throw new IllegalArgumentException("Invalid shape type: " + shapeType);
                    }
          }
}

// Main Class to Test
public class EnhancedFactoryPatternExample {
          public static void main(String[] args) {
                    try {
                              Shape circle = ShapeFactory.getShape("CIRCLE", 5.0);
                              circle.draw();

                              Shape rectangle = ShapeFactory.getShape("RECTANGLE", 10.0, 20.0);
                              rectangle.draw();

                              Shape triangle = ShapeFactory.getShape("TRIANGLE", 6.0, 8.0);
                              triangle.draw();

                              // Testing invalid shape type
                              Shape unknown = ShapeFactory.getShape("PENTAGON", 5.0);
                              unknown.draw();
                    } catch (IllegalArgumentException e) {
                              System.err.println(e.getMessage());
                    }
          }
}
