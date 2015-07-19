package cz.mikropsoft.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

@Configuration
public class ReportConfig {

    @Autowired
    ApplicationContext appContext;

    @Bean
    public JasperReportsPdfView todosView() {

        JasperReportsPdfView view = new JasperReportsPdfView();
        view.setUrl("classpath:todos-report.jrxml");
        view.setReportDataKey("datasource");
        view.setApplicationContext(appContext);
        return view;

    }

}
