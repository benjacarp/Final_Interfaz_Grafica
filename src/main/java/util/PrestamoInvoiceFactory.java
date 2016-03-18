package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import exception.FileGenerationException;
import model.Car;
import model.Client;
import model.Prestamo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * Created by martin on 08/12/15.
 */
public class PrestamoInvoiceFactory {
    protected static final Logger LOGGER = Logger.getLogger(PrestamoInvoiceFactory.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final String FILE_FORMAT = ".pdf";
//    private static final String DEFAULT_INVOICE_TYPE = "FACTURA B";
    protected static final String NEW_LINE = "\n";
    private static final String TABULAR = "\t";
    protected static final String SEPARATOR = "\n-----------------------------------------------------" +
            "---------------------------------------------------------------------\n\n";

    /**
     * Generates an invoice in PDF for a given sale. After that, shows the invoice to the user.
     *
     * @param prestamo
     * @throws FileGenerationException If sale is null or if something goes wrong during the invoice generation.
     */
    public static void generateInvoice(Prestamo prestamo) throws FileGenerationException {
        if (prestamo == null) {
            throw new FileGenerationException("Cannot generate invoice for a null prestamo.");
        }

        String fileName = null;

        try {
            fileName = generatePDF(prestamo);
        } catch (DocumentException | FileNotFoundException ex) {
            String errorMessage = "Error during invoice generation for sale " + prestamo.getId();
            LOGGER.error(errorMessage, ex);
            throw new FileGenerationException(errorMessage, ex);
        }

        if (StringUtils.isNotEmpty(fileName)) {
            openInvoice(fileName);
        }
    }

    /**
     * Creates the PDF file for a given prestamo.
     *
     * @param prestamo
     * @return
     * @throws DocumentException
     * @throws FileNotFoundException
     */
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
//                .append("Fecha: ")
//                .append(DATE_FORMAT.format(prestamo.getDate()))
//                .append(NEW_LINE)
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

//        PdfPTable table = new PdfPTable(5);
//        table.addCell("ISBN");
//        table.addCell("DESCRIPCION");
//        table.addCell("PRECIO UNIT");
//        table.addCell("CANTIDAD");
//        table.addCell("IMPORTE");

//        Book book = null;
//        for(SaleDetail detail: prestamo.getSaleDetails()) {
//            book = detail.getBook();
//            table.addCell(book.getIsbn());
//            table.addCell(book.getTitle());
//            table.addCell(String.valueOf(book.getPrice()));
//            table.addCell(String.valueOf(detail.getQuantity()));
//            table.addCell(String.valueOf(detail.getAmount()));
//        }
//        document.add(table);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        Car car = prestamo.getCar();
        paragraph.add(stringBuilder
                .append(car.getMarca())
                .append(NEW_LINE)
                .append("Chapa: ")
                .append(car.getPatente())
                .append(NEW_LINE)
                .append("Precio por dia: $")
                .append(car.getPrice())
                .append(NEW_LINE)
                .append(SEPARATOR)
                .toString());
        document.add(paragraph);
//
//        stringBuilder.setLength(0);
//        paragraph = new Paragraph();
//        paragraph.add(stringBuilder
//                .append(NEW_LINE)
//                .append("TOTAL ")
//                .append(TABULAR)
//                .append(" $ ")
//                .append(TABULAR)
//                .append(prestamo.getTotalAmount())
//                .append(NEW_LINE)
//                .toString());
//        document.add(paragraph);

        document.close();

        LOGGER.info("Invoice generated successfully for prestamo " + pretamoId);
        LOGGER.info("File Name: " + fileName);

        return fileName;
    }

    /**
     * Opens a given PDF file with evince.
     *
     * @param fileName
     */
    protected static void openInvoice(String fileName) {
        try {
            Runtime.getRuntime().exec("evince " + fileName);
        } catch (Exception ex) {
            LOGGER.error("Error trying to open the invoice with evince.", ex);
        }
    }

    /**
     * Returns the file name for an invoice.
     *
     * @param pretamoId
     * @return
     */
    protected static String getFileName(long pretamoId) {
        return new StringBuilder()
                .append("prestamo 00000")
                .append(pretamoId)
                .append(FILE_FORMAT)
                .toString();
    }
}
