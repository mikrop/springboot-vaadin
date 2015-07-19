package cz.mikropsoft.springboot.controller;

import cz.mikropsoft.springboot.domain.Todo;
import cz.mikropsoft.springboot.service.TodoRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report/")
public class ReportController {

    @Autowired
    TodoRepository repository;

    @Autowired
    @Qualifier(value = "todosView")
    JasperReportsPdfView view;

    @RequestMapping(value = "todos/pdf", method = RequestMethod.GET, produces = "application/pdf")
    public ModelAndView generateTodosReport() {

        List<Todo> todos = repository.findAll();
        JRDataSource dataSource = new JRBeanCollectionDataSource(todos, false);

        Map<String, Object> model = new HashMap<>();
        model.put("datasource", dataSource);

        return new ModelAndView(view, model);
    }

}
