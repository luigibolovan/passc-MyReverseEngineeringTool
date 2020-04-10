package ro.upt.passc;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @author Luigi Bolovan
 *
 * Class responsible to search within the jar file for classes.
 * A list of classes is built and it's sent to the information collector which will print the informations using stdout
 */
public class ClassFinder {
    private InfoCollector mCollector;
    private List<Class<?>> mFoundClasses = new ArrayList<>();

    public ClassFinder(InfoCollector collector){
        mCollector = collector;
    }

    public void findClasses() {
        System.out.print("Enter full path to jar: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pathToJar = null;
        try{
            pathToJar = reader.readLine(); //get the path to the jar
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        File jarFile = new File(pathToJar);//create a file with the given path
        URL[] urls = new URL[1];

        try{
            urls[0] = jarFile.toURI().toURL(); //get the url to the file for creating the class loader later
        }catch(MalformedURLException murle){
            murle.printStackTrace();
        }

        JarInputStream jarInputStream = null;

        try{
            jarInputStream = new JarInputStream(new FileInputStream(pathToJar));
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        // create class loader with the url to the jar file in order to be able to work with the .class files
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);
        try {
            String className;
            for(JarEntry nextJarEntry = jarInputStream.getNextJarEntry(); nextJarEntry != null; nextJarEntry = jarInputStream.getNextJarEntry()){
                if(!nextJarEntry.isDirectory()){
                    //only files allowed
                    String jarEntryName = nextJarEntry.getName();
                    if(jarEntryName.contains(".class")) {
                        //work only with class files
                        if (jarEntryName.contains("/")) {
                            //in a directory
                            jarEntryName = jarEntryName.replace("/", ".");
                        }
                        className = jarEntryName.substring(0, jarEntryName.length() - ".class".length());

                        Class<?> classWithinJar = Class.forName(className, true, urlClassLoader);
                        mFoundClasses.add(classWithinJar);
                    }
                }
            }
        }catch(IOException | ClassNotFoundException ioe){
            ioe.printStackTrace();
        }
        mCollector.getInfos(mFoundClasses);
    }
}
