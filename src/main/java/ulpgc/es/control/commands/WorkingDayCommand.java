package ulpgc.es.control.commands;

import ulpgc.es.control.Command;
import ulpgc.es.model.WorkingDaysCalendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDayCommand implements Command {
    private final Input input;
    private final Output output;

    public WorkingDayCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        Iterator<LocalDate> iterator = new WorkingDaysCalendar().iteratorfor(input.start());
        LocalDate current = input.start();
        int workingDays = 0;
        while (current.isBefore(input.end())){
            workingDays++;
            current =iterator.next();
        }
        output.workingDays(workingDays);
    }

    public interface Input {
        LocalDate start();
        LocalDate end();
    }

    public interface Output {
        void workingDays(int days);
    }
}
