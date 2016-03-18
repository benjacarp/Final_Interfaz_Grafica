/*
package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import model.Car;
import model.Client;
import model.Prestamo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

*/
/**
 * Created by ASUS on 18/03/2016.
 *//*

public class FacturaInvoiceFactory extends PrestamoInvoiceFactory {

    protected static String generatePDF(Prestamo prestamo) throws DocumentException, FileNotFoundException {
        long pretamoId = prestamo.getId();

        LOGGER.info("Generating invoice for prestamo number: " + pretamoId);

        String fileName = getFileName(pretamoId);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));

        writer.setCompressionLevel(0);

        document.open();

        Paragraph paragraph = new Paragraph("Prestamo");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        StringBuilder stringBuilder = new StringBuilder();
        paragraph = new Paragraph();
        paragraph.add(stringBuilder
                .append("AutosYa SRL")
                .append(NEW_LINE)
                .append("C.U.I.T: 20-34953806-8")
                .append(NEW_LINE)
                .append("Ing. Brutos: 901 174423-2")
                .append(NEW_LINE)
                .append("Esquiu 1591 - Provincia de Tucumán")
                .append(NEW_LINE)
                .append("Tel. 0381-434-9999/1")
                .append(NEW_LINE)
                .append("Tel. 0381-431-8888/1")
                .append(NEW_LINE)
                .append("Inicio de Actividades: 02/10/00")
                .append(NEW_LINE)
                .append("IVA RESPONSABLE INSCRIPTO")
                .append(NEW_LINE)
                .append(SEPARATOR)
                .toString());
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add("ORIGINAL\n");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add(stringBuilder
                .append("COMPROBANTE ")
//                .append(DEFAULT_INVOICE_TYPE)
                .append(" Nº 0000000")
                .append(String.valueOf(pretamoId))
                .append(NEW_LINE)
                .append("Fecha: ")
                .append(DATE_FORMAT.format(prestamo.getDate()))
                .append(NEW_LINE)
                .append(SEPARATOR)
                .toString());
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        Client client = prestamo.getClient();
        paragraph.add(stringBuilder
                .append(client.getName())
                .append(NEW_LINE)
                .append("D.N.I.: ")
                .append(client.getDni())
                .append(NEW_LINE)
                .append(SEPARATOR)
                .toString());
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        Car car = prestamo.getCar();
        paragraph.add(stringBuilder
                .append("Auto")
                .append(NEW_LINE)
                .append(car.getMarca())
                .append(NEW_LINE)
                .append("Chapa: ")
                .append(car.getPatente())
                .append(NEW_LINE)
                .append("Precio por dia: $")
                .append(car.getPrice())
                .append(NEW_LINE)
                .append("Dias: ")
                .append(prestamo.getDays())
                .append(NEW_LINE)
                .append("Total: $")
                .append(prestamo.getTotal())
                .append(NEW_LINE)
                .append(SEPARATOR)
                .toString());
        document.add(paragraph);

        document.close();

        LOGGER.info("Invoice generated successfully for prestamo " + pretamoId);
        LOGGER.info("File Name: " + fileName);

        return fileName;
    }

    protected static String getFileName(long pretamoId) {
        return new StringBuilder()
                .append("Factura 00000")
                .append(pretamoId)
                .append(FILE_FORMAT)
                .toString();
    }
}
*/
