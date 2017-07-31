package designpattern.main;

/**
 * Date 07/07/17
 *
 * @author Ankit Jain
 */
public class FactoryPatternShape {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape(ShapeFactory.Shapes.Circle);
        System.out.println(shape.area());
        shape = shapeFactory.getShape(ShapeFactory.Shapes.Rectangle);
        System.out.println(shape.area());
        shape = shapeFactory.getShape(ShapeFactory.Shapes.Triangle);
        System.out.println(shape.area());

    }
}

interface Shape {
    double area();
}

class Circle implements Shape {

    @Override
    public double area() {
        return Math.PI;
    }
}

class Rectangle implements Shape {

    @Override
    public double area() {
        return 1;
    }
}

class Triangle implements Shape {

    @Override
    public double area() {
        return 0.5;
    }
}

class ShapeFactory {
    enum Shapes {
        Circle, Rectangle, Triangle
    }


    public Shape getShape(Shapes shape) {
        switch (shape) {
            case Circle:
                return new Circle();
            case Rectangle:
                return new Rectangle();
            case Triangle:
                return new Triangle();
        }
        return null;
    }

}
