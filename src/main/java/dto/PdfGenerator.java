package dto;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import model.Student;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.List;


public class PdfGenerator {

    public static byte[] generatePdf(List<Student> students) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();

        // Create an HTML table from student data
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<table border='1'>");
        htmlContent.append("<th>Name</th><th>Email</th></tr>");

        for (Student student : students) {

            htmlContent.append("<td>").append(student.getName()).append("</td>");
            htmlContent.append("<td>").append(student.getEmail()).append("</td></tr>");
        }

        htmlContent.append("</table>");

        // Parse HTML content to PDF
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(htmlContent.toString()));

        document.close();

        return outputStream.toByteArray();
    }
}
