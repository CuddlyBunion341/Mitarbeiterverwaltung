package controller;

import view.Bridge;
import view.LogIn;
import view.LoggedIn;
import view.LoggedOut;

public class ViewController {

    public void start(){
        LoggedOut  loggedOut = new LoggedOut();
        Bridge transmition = new Bridge() {
            @Override
            public void loginPressed(int value) {
                if (value == 0){
                    LogIn login = new LogIn();
                }
                else{
                    LoggedIn loggedIn = new LoggedIn();
                    loggedOut.dispose();
                }
            }
        };
        loggedOut.setBridge(transmition);
    }
}
