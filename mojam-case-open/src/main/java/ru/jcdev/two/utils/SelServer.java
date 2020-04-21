package ru.jcdev.two.utils;

import org.openqa.grid.internal.utils.configuration.StandaloneConfiguration;
import org.openqa.selenium.remote.server.SeleniumServer;

public class SelServer {

    private SeleniumServer server;
    private StandaloneConfiguration standaloneConfiguration;

    public SelServer() {
        standaloneConfiguration = new StandaloneConfiguration();
        server = new SeleniumServer(standaloneConfiguration);
    }

    public void startSelServer() {
        server.start();
    }

    public void stopSelServer() {
        server.stop();
    }

    public boolean isServerUp() {
        return server.isStarted();
    }
}
