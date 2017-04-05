package by.bsu.appclient.runner;

import by.bsu.appclient.view.frame.AppFrame;
import by.bsu.common.remote.RemoteFootballerService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by User on 22.03.2017.
 */
public class Client {

    public static final String BINDING_NAME = "sample/FootballerService";

    public static void main(String... args) throws Exception {
       /* Registry registry = LocateRegistry.getRegistry("localhost", 2099);
        RemoteFootballerService service = (RemoteFootballerService) registry.lookup(BINDING_NAME);*/
        /*service.editOperation();
        service.delete();*/



        new AppFrame();
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}
