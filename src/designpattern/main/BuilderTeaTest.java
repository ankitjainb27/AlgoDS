package designpattern.main;

/**
 * Date 07/07/17
 *
 * @author Ankit Jain
 */

public class BuilderTeaTest {
    public static void main(String[] args) {
        BuilderTeaBuilder builderTeaBuild = new BuilderTeaBuilder(true, true)
                .addSugar()
                .addMilk();
        BuilderTea builderTea = builderTeaBuild.build();
        System.out.println(builderTea.toString());
    }
}

class BuilderTea {

    //Required Parameters
    boolean hotwater;
    boolean tea;

    //Optional Parameters
    boolean milk;
    boolean sugar;

    public BuilderTea(BuilderTeaBuilder builderTeaBuilder) {
        this.milk = builderTeaBuilder.milk;
        this.sugar = builderTeaBuilder.sugar;
        this.tea = builderTeaBuilder.tea;
        this.hotwater = builderTeaBuilder.hotwater;

    }

    @Override
    public String toString() {
        return "milk: " + milk + "   sugar: " + sugar + "   tea " + tea + "  hotwater: " + hotwater;
    }
}

class BuilderTeaBuilder {

    //Required Parameters
    boolean hotwater;
    boolean tea;

    //Optional Parameters
    boolean milk;
    boolean sugar;

    public BuilderTeaBuilder(boolean hotwater, boolean tea) {
        this.hotwater = hotwater;
        this.tea = tea;
    }

    public BuilderTeaBuilder addMilk() {
        milk = true;
        return this;
    }

    public BuilderTeaBuilder addSugar() {
        sugar = true;
        return this;
    }

    public BuilderTea build() {
        return new BuilderTea(this);
    }
}

