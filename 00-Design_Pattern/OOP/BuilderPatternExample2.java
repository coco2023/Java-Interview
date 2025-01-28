import java.util.Random;

class Computer {
          // Mandatory attributes
          private String CPU;
          private String RAM;

          // Optional attributes
          private String GPU;
          private String storage;
          private String coolingSystem;

          Computer(ComputerBuilder builder) {
                    this.CPU = builder.CPU;
                    this.RAM = builder.RAM;
                    this.GPU = builder.GPU;
                    this.storage = builder.storage;
                    this.coolingSystem = coolingSystem;
          }

          @Override
          public String toString() {
                    return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", GPU=" + GPU +
                                        ", Storage=" + storage + ", CoolingSystem=" + coolingSystem + "]";
          }

          public static class ComputerBuilder {
                    private String CPU;
                    private String RAM;
                    private String GPU;
                    private String storage;
                    private String coolingSystem;

                    // Builder constructor for mandatory attributes
                    public ComputerBuilder(String CPU, String RAM) {
                              this.CPU = CPU;
                              this.RAM = RAM;
                    }

                    public ComputerBuilder setGPU(String GPU) {
                              this.GPU = GPU;
                              return this;
                    }

                    public ComputerBuilder setStorage(String storage) {
                              this.storage = storage;
                              return this;
                    }

                    public ComputerBuilder setCoolingSystem(String coolingSystem) {
                              this.coolingSystem = coolingSystem;
                              return this;
                    }

                    public Computer build() {
                              return new Computer(this);
                    }

          }
}

public class BuilderPatternExample2 {
          public static void main(String[] args) {
                    Computer computer = new Computer.ComputerBuilder("Intel i7", "16GB")
                                        .setGPU("NVIDIA RTX 3080")
                                        .setStorage("1TB SSD")
                                        .setCoolingSystem("Liquid Cooling")
                                        .build();

                    System.out.println(computer);
          }
}