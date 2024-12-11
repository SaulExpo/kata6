package ulpgc.es;

import ulpgc.es.control.CommandFactory;
import ulpgc.es.view.WorkingDaysService;

public class Main {
    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        new WorkingDaysService(7070, factory).start();
    }
}
