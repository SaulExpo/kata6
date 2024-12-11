package ulpgc.es;

import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WorkingDaysService {
    private final int port;
    private final CommandFactory factory;
    private Javalin app;

    public WorkingDaysService(int port, CommandFactory factory) {
        this.port = port;
        this.factory = factory;
        factory.register("working-days", workingDaysBuilder());
        factory.register("working-date", workingDateBuilder());
    }

    private void execute(String command, HttpServletRequest req, HttpServletResponse res){
        factory.with(req, res).build(command).execute();
    }

    public void start(){
        app = Javalin.create()
                .get("/working-days", ctx -> execute("working-days", ctx.req(), ctx.res()))
                .get("/working-date", ctx -> execute("working-date", ctx.req(), ctx.res()))
                .start();
    }

    public void stop(){
        app.stop();
    }

    private static CommandFactory.Builder workingDateBuilder() {
        return (req, res) -> new WorkingDateCommand(WorkingDateAdapter.inputOf(req), WorkingDateAdapter.outputOf(res));
    }

    private static CommandFactory.Builder workingDaysBuilder() {
        return (req, res) -> new WorkingDayCommand(WorkingDaysAdapter.inputOf(req), WorkingDaysAdapter.outputOf(res));
    }
}
