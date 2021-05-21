package com.ex;

public class Main {

    public static void main(String[] args) {

        AbstractApp app = new App();
        app.willRun();
        app.run();
        app.didRun();

    }
}
