package m2t.com.tashilatappprototype.common.utils;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import m2t.com.tashilatappprototype.common.pojo.EncaisseResponse;
import m2t.com.tashilatappprototype.common.pojo.Facture;

/**
 * Created by laanaya on 12/19/17.
 */

public class HtmlToPDF {

    public static void createFilePDF(Activity activity, String fileName, String oper, Facture facture, EncaisseResponse encaisseResponse) {
        String FILE = Environment.getExternalStorageDirectory().toString()
                + "/RECU/" + fileName +".pdf";

        // Add Permission into Manifest.xml
        // <uses-permission
        // android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

        // Create New Blank Document
        Document document = new Document(PageSize.A4);

        // Create Directory in External Storage
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/RECU");
        myDir.mkdirs();

        // Create Pdf Writer for Writting into New Created Document
        try {
            PdfWriter.getInstance(document, new FileOutputStream(FILE));

            // Open Document for Writting into document
            document.open();

            // User Define Method
            addMetaData(document);
            createPDFFromHtml(document,oper, facture, encaisseResponse);
            //addTitlePage(document);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Close Document after writting all content
        document.close();

        Toast.makeText(activity, "PDF File is Created. Location : " + FILE,
                Toast.LENGTH_LONG).show();

    }

    // Set PDF document Properties
    public static void addMetaData(Document document)
    {
        document.addTitle("RESUME");
        document.addSubject("Person Info");
        document.addKeywords("Personal,	Education, Skills");
        document.addAuthor("TAG");
        document.addCreator("TAG");
    }

    public static void createPDFFromHtml(Document document, String oper, Facture facture, EncaisseResponse encaisseResponse) {
        try {

            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
            Date currentTime = Calendar.getInstance().getTime();

            HTMLWorker htmlWorker = new HTMLWorker(document);
            String strData = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<body>\n" +
                    "\n" +
                    "<h2 align=\"center\">Recu de paiement emis par M2T</h2>\n" +
                    "<h3 align=\"center\">Date de Transaction: "+ dt1.format(currentTime).toString() +"</h3>"+
                    "\n\n"+
                    "<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <td>Operateur</td>\n" +
                    "    <td>:"+ oper +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>No recu</td>\n" +
                    "    <td>:"+ encaisseResponse.getRecuNum() +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>No transaction</td>\n" +
                    "    <td>:"+ encaisseResponse.getFacturierTrxNum() +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Nom Client</td>\n" +
                    "    <td>:"+ facture.getNomClient() +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>No Contrat</td>\n" +
                    "    <td>:"+ facture.getNumContrat() +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>No Tournee</td>\n" +
                    "    <td>:"+ facture.getTournee() +"</td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "<table style=\"width:100%\">\n" +
                    "\t<tr>\n" +
                    "    \t<td>Designation</td>\n" +
                    "    \t<td>Montants(Dhs)</td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "<p>==========================================================</p>\n" +
                    "<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <td>F 645</td>\n" +
                    "    <td>:ONEE</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Penalite</td>\n" +
                    "    <td>:"+ Utils.convertPrice(facture.getMntPenalite()) +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Mois facturation</td>\n" +
                    "    <td>:" + Utils.getDatefromString(facture.getDate()) +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Date lim paiement</td>\n" +
                    "    <td>:"+ Utils.getDatefromString(facture.getDateLimite()) +"</td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "<p>==========================================================</p>\n" +
                    "<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <td>Total TTC Transaction</td>\n" +
                    "    <td>:"+ Utils.convertPrice(facture.getMntTTC()) +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Dont frais</td>\n" +
                    "    <td>:"+ Utils.convertPrice(facture.getMntFrais())+"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Dont timbre</td>\n" +
                    "    <td>:"+ Utils.convertPrice(facture.getMntTimbre()) +"</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>Paye par</td>\n" +
                    "    <td>:Espece</td>\n" +
                    "  </tr>\n" +
                    "</table>\n" +
                    "<h4 align=\"center\"> TasshilatChaabiCash vous remercie </h4>\n" +
                    "</body>\n" +
                    "</html>";
            htmlWorker.parse(new StringReader(strData));
            document.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
