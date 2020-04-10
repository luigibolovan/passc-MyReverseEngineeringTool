package ro.upt.passc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;


/**
 * @author Luigi Bolovan
 *
 * Default information collector.
 * Its job is to print on the screen informations about the classes that it receives.
 * What informations?
 *      - General information about a class - its type(class/interface)
 *                                          - if it's public or not
 *                                          - if it's abstract
 *      - Which interfaces it implements
 *      - The name of its superclass
 *      - A brief list of its fields containing also the modifier for every field and its type
 *      - A list of methods with their parameters' type and their returned type
 *      - A list of constructors with their parameters' type
 *
 */
public class DefaultInfoCollector implements InfoCollector {
    @Override
    public void getInfos(List<Class<?>> jarClass) {
        for(Class<?> aClass : jarClass){
            System.out.println("\t" + aClass.getName());
            System.out.println("----------------------------------------------------");

            printGeneralInfoAboutClasses(aClass);
            printImplementedInterfaces(aClass);
            printSuperClasses(aClass);
            printClassFields(aClass);
            printClassMethods(aClass);
            printClassConstructors(aClass);

            System.out.println("\n");
        }
    }

    public void printClassConstructors(Class<?> aClass) {
        Constructor<?>[] classConstructors = aClass.getConstructors();
        System.out.println("Constructors: " + ((classConstructors.length == 0) ? "none" : ""));
        for(Constructor<?> constructor : classConstructors){
            String partialOutput = "\t" + constructor.getName() + "(";
            Class<?>[] constructorParameterTypes = constructor.getParameterTypes();
            for(Class<?> aParamType : constructorParameterTypes){
                partialOutput += aParamType + ", ";
            }
            if(constructorParameterTypes.length > 0){
                partialOutput = partialOutput.substring(0, partialOutput.length() - 2);
            }
            partialOutput += ")";
            System.out.println(partialOutput);
        }
    }

    public void printClassMethods(Class<?> aClass) {
        Method[] classMethods = aClass.getDeclaredMethods();
        System.out.println("Methods:");
        for(Method aMethod : classMethods){
            Class<?>[] parameterTypes = aMethod.getParameterTypes();
            String partialOutput = "\t" + aMethod.getReturnType().toString()+ " " + aMethod.getName() + "(";
            for(Class<?> aParamType : parameterTypes){
                partialOutput += aParamType + ", ";
            }
            if(parameterTypes.length > 0) {
                partialOutput = partialOutput.substring(0, partialOutput.length() - 2);
            }
            partialOutput += ")";
            System.out.println(partialOutput);
        }
    }

    public void printClassFields(Class<?> aClass) {
        Field[] classFields = aClass.getDeclaredFields();
        System.out.println("Fields:");
        for(Field aField : classFields){
            int fieldModifier = aField.getModifiers();
            System.out.println("\t" + (Modifier.isPublic(fieldModifier) ? "public " : " ") +
                                (Modifier.isPrivate(fieldModifier) ? "private " : " ") +
                                aField.getType() + " " +
                                aField.getName());
        }
    }

    public void printSuperClasses(Class<?> aClass) {
        String extendedClass = "Extended class: ";
        Class<?> superClass = aClass.getSuperclass();
        if (superClass == null) {
            System.out.println(extendedClass + "none");
        } else {
            System.out.println(extendedClass + superClass.getName());
        }
    }

    public void printGeneralInfoAboutClasses(Class<?> aClass) {
        System.out.println("Class name: " + aClass.getName());
        System.out.println("Type: " + (aClass.isInterface() ? "interface" : "class"));
        int classModifier = aClass.getModifiers();
        System.out.println("Is public?: " + Modifier.isPublic(classModifier));
        System.out.println("Is abstract?: " + Modifier.isAbstract(classModifier));

    }

    public void printImplementedInterfaces(Class<?> aClass) {
        Class<?>[] interfaces = aClass.getInterfaces();
        String interfacesImplemented = "Implemented interfaces: ";
        for (Class<?> implementedInterface : interfaces)
            interfacesImplemented += implementedInterface.getName() + ", ";
        if (interfaces.length == 0) {
            interfacesImplemented += "none";
        } else {
            interfacesImplemented = interfacesImplemented.substring(0, interfacesImplemented.length() - 2);
        }
        System.out.println(interfacesImplemented);
    }
}
