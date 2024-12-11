package ulpgc.es;

import io.javalin.Javalin;

public class WorkingDaysService {
    private final int port;
    private Javalin app;

    public WorkingDaysService(int port) {
        this.port = port;
    }

    public void start(){
        app = Javalin.create().start();
    }

    public void stop(){
        app.stop();
    }
}
