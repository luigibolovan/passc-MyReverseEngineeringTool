package ro.upt.passc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luigi Bolovan
 *
 * Interface for collecting informations from the classes within the jar
 */
public interface InfoCollector {
    void getInfos(List<Class<?>> jarClass);
}
