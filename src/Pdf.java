import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;

public class Pdf {
    public void print(String name, ArrayList prod, String total) throws IOException {
        Path path = Paths.get("");
        String myFilePath = path.toAbsolutePath().toString();
        FileWriter fichero = new FileWriter(myFilePath + "/Pedido" + name.charAt(0) + name.charAt(1) + ".xml");
        fichero.write("-------------------------------EXXON---------------------------\n\n");
        fichero.write("\nNombre de la empresa: " + name);
        fichero.write("\nRFC: " + rfc(name));
        fichero.write("\n\n");
        fichero.write("Pedido:\n");

        fichero.write("Producto \t\t\t Cantidad \t\t Importe\n");
        fichero.write("______________________________________________________________\n\n");
        for (int i = 0; i < prod.size(); i++) {
            fichero.write("" + prod.get(i) + "\n");
        }
        fichero.write("\n\nTotal final:" + total);
        fichero.write("\n\nCertificado:" + certificado());
        fichero.close();

        Process p = Runtime.getRuntime().exec("ipconfig");

    }

    public boolean createFactura(String comp, ArrayList compra, String total) throws FileNotFoundException, DocumentException {
        boolean flag = false;
        String myRfc = rfc(comp);
        String fileName = "Pedido" + comp.charAt(0) + comp.charAt(1) + ".pdf";

        FileOutputStream file = new FileOutputStream(fileName);
        Document doc = new Document();
        PdfWriter.getInstance(doc, file);
        doc.open();

        Paragraph parrafo = new Paragraph("---------------------EXXON---------------------");
        parrafo.setAlignment(1);
        doc.add(parrafo);

        doc.add(new Paragraph(""));
        doc.add(new Paragraph("Nombre de la empresa:" + comp));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("RFC:" + myRfc));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Productos:"));
        doc.add(new Paragraph("\n"));

        for (int i = 0; i < compra.size(); i++) {
            doc.add(new Paragraph("" + compra.get(i)));
            doc.add(new Paragraph("\n"));
        }

        doc.add(new Paragraph("Total final: " + total));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("\n"));
        doc.add(new Paragraph("Certificado: " + certificado()));

        doc.close();

        return flag;
    }

    private String rfc(String name) {
        String charset[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String rfc = "";
        int pos = 0;

        for (int i = 0; i < 4; i++) {
            rfc += name.charAt(i);
        }

        for (int i = 0; i < 6; i++) {
            pos = (int) (Math.random() * (charset.length - 1)) + 1;
            rfc += charset[pos];
        }
        return rfc;
    }

    private String certificado() {
        String charset[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };
        String number[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String cert = "";
        int pos = 0;

        for (int i = 0; i < 10; i++) {
            pos = (int) (Math.random() * (charset.length - 1)) + 1;
            cert += charset[pos];
        }

        for (int i = 0; i < 10; i++) {
            pos = (int) (Math.random() * (number.length - 1)) + 1;
            cert += number[pos];
        }
        return cert;
    }
}
