package ro.upt.passc;

/**
 * @author Luigi Bolovan
 *
 * Client class.
 */
public class ReverseEngineeringToolClient {
    private static ClassFinder mClassFinder = new ClassFinder(new DefaultInfoCollector());
    public static void main(String[] args){
        mClassFinder.findClasses();
    }
}
