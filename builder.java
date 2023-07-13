
class Computer {

    int[] HDDs;
    int[] SSDs;
    int[] RAMs;
    String processor;
    String os;
    String graphicsCard;

    Computer() {
    }

    Computer(Computer computer) {
        this();
        this.HDDs = computer.HDDs;
        this.SSDs = computer.SSDs;
        this.RAMs = computer.RAMs;
        this.processor = computer.processor;

        if (computer.os != null)
            this.os = computer.os;
        if (computer.graphicsCard != null)
            this.graphicsCard = computer.graphicsCard;
    }

    public Computer clone() {
        return new Computer(this);
    }
}

interface builder {
    void reset();

    void setHDDs(int[] list);

    void setSSDs(int[] list);

    void setRAMs(int[] list);

    void setProcessor(String name);

    void setOS(String name);

    void setGraphicsCard(String name);

    public static void main(String[] args) {

        // simple example how to use
        ComputerValueBuilder cmp = new ComputerValueBuilder();
        cmp.setHDDs(new int[] { 256, 512 });
        cmp.setSSDs(new int[] { 128, 1024 });
        cmp.setProcessor("Intel Core i5");
        int value = cmp.getProduct();
        System.out.println(value);

        // mass production of 100 for each using builder pattern
        Computer[] gamingComputersArray = new Computer[100];
        Computer[] workStationsArray = new Computer[100];
        for (int i = 0; i < 100; i++) {
            ComputerBuilder builder = new ComputerBuilder();
            Director director = new Director();
            director.constructGamingLaptop(builder);
            gamingComputersArray[i] = builder.getProduct();
        }
        for (int i = 0; i < 100; i++) {
            ComputerBuilder builder = new ComputerBuilder();
            Director director = new Director();
            director.constructWorkStation(builder);
            workStationsArray[i] = builder.getProduct();
        }

        // values of these two types of laptops
        int value1;
        ComputerValueBuilder builder1 = new ComputerValueBuilder();
        Director director = new Director();
        director.constructGamingLaptop(builder1);
        value1 = builder1.getProduct();
        System.out.println("value of a gaming laptop: " + value1);

        int value2;
        ComputerValueBuilder builder2 = new ComputerValueBuilder();
        director.constructWorkStation(builder2);
        value2 = builder2.getProduct();
        System.out.println("value of a work station: " + value2);

        // mass production of 100 for each using prototype pattern
        ComputerBuilder gamingPrototypeBuilder = new ComputerBuilder();
        director.constructGamingLaptop(gamingPrototypeBuilder);
        Computer gamingPrototype = gamingPrototypeBuilder.getProduct();

        ComputerBuilder workstationPrototypeBuilder = new ComputerBuilder();
        director.constructGamingLaptop(workstationPrototypeBuilder);
        Computer workstationPrototype = workstationPrototypeBuilder.getProduct();

        Computer[] secondgamingComputersArray = new Computer[100];
        Computer[] secondworkStationsArray = new Computer[100];
        for (int i = 0; i < 100; i++) {
            Computer builder = gamingPrototype.clone();
            secondgamingComputersArray[i] = builder;
        }
        for (int i = 0; i < 100; i++) {
            Computer builder = workstationPrototype.clone();
            secondworkStationsArray[i] = builder;
        }

        System.out.println("processor: " + secondgamingComputersArray[19].processor);

    }

}

class ComputerBuilder implements builder {
    Computer computer;

    ComputerBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.computer = new Computer();
    }

    @Override
    public void setHDDs(int[] list) {
        int len = list.length;
        this.computer.HDDs = new int[list.length];
        for (int i = 0; i < len; i++)
            this.computer.HDDs[i] = list[i];
    }

    @Override
    public void setSSDs(int[] list) {
        int len = list.length;
        this.computer.SSDs = new int[list.length];
        for (int i = 0; i < len; i++)
            this.computer.SSDs[i] = list[i];
    }

    @Override
    public void setRAMs(int[] list) {
        int len = list.length;
        this.computer.RAMs = new int[list.length];
        for (int i = 0; i < len; i++)
            this.computer.RAMs[i] = list[i];
    }

    @Override
    public void setProcessor(String name) {
        this.computer.processor = name;
    }

    @Override
    public void setOS(String name) {
        this.computer.os = name;
    }

    @Override
    public void setGraphicsCard(String name) {
        this.computer.graphicsCard = name;
    }

    public Computer getProduct() {
        Computer product = this.computer;
        this.reset();
        return product;
    }
}

class ComputerValueBuilder implements builder {

    int value;

    ComputerValueBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.value = 0;
    }

    @Override
    public void setHDDs(int[] list) {
        int len = list.length;
        for (int i = 0; i < len; i++)
            switch (list[i]) {
                case 256:
                    this.value += 250;
                    break;
                case 512:
                    this.value += 500;
                    break;
                case 1024:
                    this.value += 1000;
                    break;
                case 2048:
                    this.value += 2000;
                    break;
            }
    }

    @Override
    public void setSSDs(int[] list) {
        int len = list.length;
        for (int i = 0; i < len; i++)
            switch (list[i]) {
                case 128:
                    this.value += 250;
                    break;
                case 256:
                    this.value += 500;
                    break;
                case 512:
                    this.value += 1000;
                    break;
                case 1024:
                    this.value += 2000;
                    break;
            }
    }

    @Override
    public void setRAMs(int[] list) {
        int len = list.length;
        for (int i = 0; i < len; i++)
            switch (list[i]) {
                case 4:
                    this.value += 250;
                    break;
                case 8:
                    this.value += 500;
                    break;
                case 16:
                    this.value += 1000;
                    break;
                case 32:
                    this.value += 2000;
                    break;
            }
    }

    @Override
    public void setProcessor(String name) {
        switch (name) {
            case "Intel Core i5":
                this.value += 3000;
                break;
            case "Intel Core i7":
                this.value += 10000;
                break;
            case "AMD Ryzen 7":
                this.value += 12000;
                break;
            case "AMD Ryzen 9":
                this.value += 19000;
                break;
        }
    }

    @Override
    public void setOS(String name) {
        // it has no value in the price chart so it is empty
    }

    @Override
    public void setGraphicsCard(String name) {
        switch (name) {
            case "Nvidia RTX":
                this.value += 15000;
                break;
            case "Nvidia GTX":
                this.value += 10000;
                break;
            case "Nvidia MX":
                this.value += 5000;
                break;
            case "AMD Radeon":
                this.value += 5000;
                break;
        }
    }

    public int getProduct() {
        int product = this.value;
        this.reset();
        return product;
    }
}

class Director {
    public void constructGamingLaptop(builder builder) {
        builder.reset();
        builder.setHDDs(new int[] { 1024 });
        builder.setSSDs(new int[] { 256, 512 });
        builder.setRAMs(new int[] { 8, 16 });
        builder.setProcessor("Intel Core i7");
        builder.setOS("Windows 11");
        builder.setGraphicsCard("Nvidia RTX");
    }

    public void constructWorkStation(builder builder) {
        builder.reset();
        builder.setHDDs(new int[] { 512, 512 });
        builder.setRAMs(new int[] { 8, 8 });
        builder.setProcessor("AMD Ryzen 9");
    }
}
