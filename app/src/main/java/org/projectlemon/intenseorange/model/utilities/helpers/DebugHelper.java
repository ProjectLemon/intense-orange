package org.projectlemon.intenseorange.model.utilities.helpers;

import org.projectlemon.intenseorange.controller.implementations.NetworkDevice;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by linuslagerhjelm on 16-01-22.
 */
public class DebugHelper {
    private NetworkDevice device;
    private final String creationTime = new Date().toString();
    private String threadName;
    private static List<Thread> currentThreads = new LinkedList<>();


    public DebugHelper(String name) {
        this.threadName = name;
    }

    public void dump() {
        Date d = new Date();
        System.err.println("==========DUMP==========");
        System.err.println("["+d.toString()+"]"+" Dump by: "+threadName+"| created: "+creationTime);
        System.err.println("Other running threads and their status: ");
        for(Thread t: currentThreads) {
            System.err.println("Name: "+t.getName()+"| id: "+
                                t.getId()+"| State: "+
                                t.getState()+"| Context Class: "+
                                t.getContextClassLoader()+"| Alive: "+
                                t.isAlive());
        }
        System.err.println("=========/DUMP==========");
    }

    public void log(String message) {
        Date d = new Date();
        System.err.println(d.getTime()+" From "+threadName+": "+message);
    }

    public static void addThread(Thread t) {
        if(t != null) {
            currentThreads.add(t);
        }
    }
}
