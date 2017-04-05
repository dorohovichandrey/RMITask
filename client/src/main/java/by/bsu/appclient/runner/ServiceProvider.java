package by.bsu.appclient.runner;

import by.bsu.common.remote.RemoteFootballerService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static by.bsu.appclient.runner.Client.BINDING_NAME;

/**
 * Created by User on 30.03.2017.
 */
public class ServiceProvider {


    public static RemoteFootballerService getInstance() {

        return Helper.service;
    }

    private static class Helper{

        private static final RemoteFootballerService service;

        static {
            try {
                Registry registry = LocateRegistry.getRegistry("10.150.5.199", 2099);
                service = (RemoteFootballerService) registry.lookup(BINDING_NAME);
            } catch (Exception e){
                throw new RuntimeException("Fatal", e);
            }
        }

    }
}
