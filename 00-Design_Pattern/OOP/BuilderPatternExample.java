class House {
          private String walls;
          private String roof;
          private String floor;

          private House(HouseBuilder builder) {
                    this.walls = builder.walls;
                    this.roof = builder.roof;
                    this.floor = builder.floor;
          }

          public static class HouseBuilder {
                    private String walls;
                    private String roof;
                    private String floor;

                    public HouseBuilder setWalls(String walls) {
                              this.walls = walls;
                              return this;
                    }

                    public HouseBuilder setRoof(String roof) {
                              this.roof = roof;
                              return this;
                    }

                    public HouseBuilder setFloor(String floor) {
                              this.floor = floor;
                              return this;
                    }

                    public House build() {
                              return new House(this);
                    }

          }

          @Override
          public String toString() {
                    return "House [walls=" + walls + ", roof=" + roof + ", floor=" + floor + "]";
          }

}

public class BuilderPatternExample {
          public static void main(String[] args) {
                    House house = new House.HouseBuilder()
                                        .setWalls("Brick Walls")
                                        .setRoof("Concrete Roof")
                                        .setFloor("Marble Floor")
                                        .build();

                    System.out.println(house);
          }
}
