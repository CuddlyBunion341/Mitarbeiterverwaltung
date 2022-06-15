package controller;

import view.Bridge;
import view.LogIn;
import view.LoggedIn;
import view.LoggedOut;

public class ViewController {

    public void start(){
        LoggedOut  loggedOut = new LoggedOut();
        LogIn logIn = new LogIn();
        logIn.setVisible(false);
        Bridge transmition = new Bridge() {
            @Override
            public void loginPressed(int value) {
                if (value == 0){
                    logIn.setVisible(true);
                }
                else{
                    LoggedIn loggedIn = new LoggedIn();
                    loggedOut.dispose();
                    logIn.dispose();
                }
            }
        };
        loggedOut.setBridge(transmition);
        logIn.setBridge(transmition);
    }
}
