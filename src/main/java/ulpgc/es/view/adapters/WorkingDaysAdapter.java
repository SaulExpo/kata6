package ulpgc.es.view.adapters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ulpgc.es.control.commands.WorkingDayCommand;

import java.io.IOException;
import java.time.LocalDate;

public class WorkingDaysAdapter {
    public static WorkingDayCommand.Input inputOf(HttpServletRequest request){
        return new WorkingDayCommand.Input() {
            @Override
            public LocalDate start() {
                return LocalDate.parse(request.getParameter("start"));
            }

            @Override
            public LocalDate end() {
                return LocalDate.parse(request.getParameter("end"));
            }
        };
    }

    public static WorkingDayCommand.Output outputOf(HttpServletResponse response){
        return days -> {
            try {
                response.getWriter().write(days);
                response.setStatus(200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
