package by.bsu.appserver.runner;

import by.bsu.appserver.service.impl.RemoteFootballerServiceImpl;
import by.bsu.common.remote.RemoteFootballerService;


import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by User on 22.03.2017.
 */
public class Runner {

    public static final String BINDING_NAME = "sample/FootballerService";

    public static void main(String... args) throws Exception {
        System.out.print("Starting registry...");
        final Registry registry = LocateRegistry.createRegistry(2099);
        System.out.println(" OK");

        final RemoteFootballerService service = new RemoteFootballerServiceImpl();
        Remote stub = UnicastRemoteObject.exportObject(service, 0);

        System.out.print("Binding service...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
