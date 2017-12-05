/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.HibernateUtil;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author fevonlopes
 */
public class Report {
    protected Connection cn;

    public Report() {
        Session hib = HibernateUtil.getSession();
        SessionImpl sessionImpl = (SessionImpl) hib;
        cn = sessionImpl.connection();
    }

    public boolean impRelPDFJasper(String rel, HashMap params, FacesContext facesContext) {
        try {
            facesContext.responseComplete();
            ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
            String path = context.getRealPath("/WEB-INF/reports");
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");
            OutputStream out = response.getOutputStream();

//      NO MAC   (path + "//" + rel);
            File file = new File (path + "\\" + rel);
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, params, cn);
            JasperExportManager.exportReportToPdfStream(jp, out);

            return true;
        } catch (Exception ex) {
            System.out.println("Exceção: " + ex.getMessage());
            return false;
        }

    }

    public boolean impRelExcelJasper(String rel, HashMap params, FacesContext facesContext) {
        try {
            facesContext.responseComplete();
            ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
            String path = context.getRealPath("/WEB-INF/reports");
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            response.setContentType("excel");
            OutputStream out = response.getOutputStream();
            
//      NO MAC   (path + "//" + rel);
            InputStream io = this.getClass().getResourceAsStream(path + "\\" + rel);
            JasperReport jr = (JasperReport) JRLoader.loadObject(io);;
            JasperPrint jp = JasperFillManager.fillReport(jr, params, cn);

            JExcelApiExporter exporter = new JExcelApiExporter();
            exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
            exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JExcelApiExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
            exporter.setParameter(JExcelApiExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
            exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();

            return true;
        } catch (Exception ex) {
            System.out.println("Excecao: " + ex.getMessage());
            return false;
        }

    }
}