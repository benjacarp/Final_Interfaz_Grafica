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
import java.util.Date;

/**
 * Created by martin on 08/12/15.
 */
public class PrestamoInvoiceFactory {
    protected static final Logger LOGGER = Logger.getLogger(PrestamoInvoiceFactory.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    protected static final String FILE_FORMAT = ".pdf";
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

        Paragraph paragraph = new Paragraph("Contrato");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(NEW_LINE);
        paragraph.add(NEW_LINE);
        document.add(paragraph);

        Date date = prestamo.getStart();
        StringBuilder stringBuilder = new StringBuilder();
        paragraph = new Paragraph();
        paragraph.add(stringBuilder
                .append("" + date.getDate() + "/" + (date.getMonth()+1) + "/" + (1900 + date.getYear()))
                .append(NEW_LINE)
                .append("San Miguel de Tucumán")
                .append(NEW_LINE)
                .append(NEW_LINE)
                .toString());
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add(stringBuilder
                .append("En el día de la fecha, se celebra el préstamo del vehiculo " + prestamo.getCar().getMarca())
                .append(" en perfectas condiciones al sr/sra " + prestamo.getClient().getName())
                .append(" quien se compromete a entregarlo en las mismas condiciones en la cual fue recibido, debiendo pagar $")
                .append("" + prestamo.getCar().getPrice())
                .append(" por cada día, hasta el día en que sea devuelto.")
                .toString());
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add("Sin otro motivo, queda constituido este préstamo, registrado con el numero: " + prestamo.getId());
        document.add(paragraph);

        stringBuilder.setLength(0);
        paragraph = new Paragraph();
        paragraph.add(NEW_LINE);
        paragraph.add(NEW_LINE);
        paragraph.add(NEW_LINE);
        paragraph.add(stringBuilder
            .append("                    ")
                .append("........................")
                .append("                        ")
                .append("                        ")
                .append("........................").toString());
        document.add(paragraph);

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
                .append("Contrato 00000")
                .append(pretamoId)
                .append(FILE_FORMAT)
                .toString();
    }
}
