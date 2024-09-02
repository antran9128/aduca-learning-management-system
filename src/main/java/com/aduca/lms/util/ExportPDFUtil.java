package com.aduca.lms.util;

import com.aduca.lms.domain.Order;
import com.aduca.lms.domain.Payment;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import java.io.IOException;
import java.util.List;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class ExportPDFUtil {
  public static void generateInvoicePDF(Payment payment, List<Order> orderItems, HttpServletResponse response) throws IOException, DocumentException {
    // Set response content type and headers
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");

    // Load the HTML template
    String htmlContent = generateHTML(payment, orderItems);

    // Generate PDF document
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(htmlContent);
    renderer.layout();
    renderer.createPDF(response.getOutputStream());
  }

  private static String generateHTML(Payment payment, List<Order> orderItems) {
    StringBuilder sb = new StringBuilder();
    String customerDetails =
      "<strong>Name:</strong> " + payment.getName() + "<br />" +
        "<strong>Email:</strong> " + payment.getEmail() + "<br />" +
        "<strong>Phone:</strong> " + payment.getPhone() + "<br />" +
        "<strong>Address:</strong> " + payment.getAddress() + "<br />";

    String invoiceDetails =
      "<h3><span style='color: green;'>Invoice:</span> #" + payment.getInvoiceNo() + "</h3>" +
        "Order Date: " + payment.getOrderDate() + "<br />" +
        "Delivery Date: " + payment.getOrderDate() + "<br />" +
        "Payment Type: " + payment.getPaymentType() + "<br />";

    sb.append("<html lang='en'>")
      .append("<head>")
      .append("<meta charset='utf-8'/>")
      .append("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'/>")
      .append("<title>Invoice</title>")
      .append("<style type='text/css'>")
      .append("* { font-family: Verdana, Arial, sans-serif; }")
      .append("table{ font-size: x-small; }")
      .append("tfoot tr td{ font-weight: bold; font-size: x-small; }")
      .append(".gray { background-color: lightgray }")
      .append(".font{ font-size: 15px; }")
      .append(".authority { float: right }")
      .append(".authority h5 { margin-top: -10px; color: green; margin-left: 35px; }")
      .append(".thanks p { color: green; font-size: 16px; font-weight: normal; font-family: serif; margin-top: 20px; }")
      .append("</style>")
      .append("</head>")
      .append("<body>")
      .append("<table width='100%' style='background: #F7F7F7; padding:0 20px 0 20px;'>")
      .append("<tr>")
      .append("<td valign='top'>")
      .append("<h2 style='color: green; font-size: 26px;'><strong>ADUCA</strong></h2>")
      .append("</td>")
      .append("<td align='right'>")
      .append("<pre class='font'>")
      .append("Unlock Your Potential, Learn Anytime, Anywhere with Aduca\n")
      .append("Email:support@easylearningbd.com\n")
      .append("Mob: 1245454545\n")
      .append("Dhaka 1207,Dhanmondi:#4\n")
      .append("</pre>")
      .append("</td>")
      .append("</tr>")
      .append("</table>")
      .append("<table width='100%' style='background:white; padding:2px;'></table>")
      .append("<table width='100%' style='background: #F7F7F7; padding:0 5 0 5px;' class='font'>")
      .append("<tr>")
      .append("<td>")
      .append("<p class='font' style='margin-left: 20px;'>")
      .append(customerDetails)
      .append("</p>")
      .append("</td>")
      .append("<td>")
      .append("<p class='font'>")
      .append(invoiceDetails)
      .append("</p>")
      .append("</td>")
      .append("</tr>")
      .append("</table>")
      .append("<br></br>")
      .append("<h3>Products</h3>")
      .append("<table width='100%'>")
      .append("<thead style='background-color: green; color:#FFFFFF;'>")
      .append("<tr class='font'>")
      .append("<th>Image</th>")
      .append("<th>Course Name</th>")
      .append("<th>Unit Price</th>")
      .append("<th>Total</th>")
      .append("</tr>")
      .append("</thead>")
      .append("<tbody>");

    for (Order item : orderItems) {
      sb.append("<tr class='font'>")
        .append("<td align='center'><img src='src/main/webapp/resources/upload/course").append(item.getCourse().getId()).append("/thumbnail/").append(item.getCourse().getCourseImage()).append("' height='60px;' width='60px;' alt='' /></td>")
        .append("<td align='center'>").append(item.getCourse().getCourseName()).append("</td>")
        .append("<td align='center'>").append(item.getPrice()).append("</td>")
        .append("<td align='center'>price Tk</td>")
        .append("</tr>");
    }

    sb.append("</tbody>")
      .append("</table>")
      .append("<br/>")
      .append("<table width='100%' style='padding:0 10px 0 10px;'>")
      .append("<tr>")
      .append("<td align='right'>")
      .append("<h2><span style='color: green;'>Subtotal:</span>  ").append(payment.getTotalAmount()).append("</h2>")
      .append("<h2><span style='color: green;'>Total:</span>  ").append(payment.getTotalAmount()).append("</h2>")
      .append("</td>")
      .append("</tr>")
      .append("</table>")
      .append("<div class='thanks mt-3'>")
      .append("<p>Thanks For Buying Products..!!</p>")
      .append("</div>")
      .append("<div class='authority float-right mt-5'>")
      .append("<p>-----------------------------------</p>")
      .append("<h5>Authority Signature:</h5>")
      .append("</div>")
      .append("</body>")
      .append("</html>");

    return sb.toString();
  }

}
