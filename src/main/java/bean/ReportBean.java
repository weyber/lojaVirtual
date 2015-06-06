package bean;

import java.io.IOException;
import java.util.HashMap;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class ReportBean {
    
    private JasperPrint jasperPrint;

    private  String report;

    private JRBeanCollectionDataSource collection;

    private HttpServletResponse httpServletResponse;

    private javax.servlet.ServletOutputStream servletStream;

    private ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

    @Autowired
    OrderBean orderBean;
    
    /** report */
    public void generateReport() throws JRException,IOException {

        collection = new JRBeanCollectionDataSource(orderBean.getRequestItems());

        report = context.getRealPath("/resources/relatorios/report.jasper");

        jasperPrint = JasperFillManager.fillReport(report,
                new HashMap<String, Object>(), collection);

        /* Http */
        httpServletResponse = (HttpServletResponse) context.getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=Orders-Report .pdf");

        servletStream = httpServletResponse.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
