import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.*;
import java.io.*;
import java.util.StringTokenizer;
import org.bouncycastle.*;
import org.bouncycastle.asn1.*;

import static java.lang.System.*;

public class PDFToText {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pdf="G:/Data Structures and Algorithms in Java~tqw~_darksiderg.pdf";
    String txt="G:/test.txt";
    StringBuffer text=new StringBuffer() ;
    String resultText="";
    PdfReader reader;
    try {
        reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            text.append(strategy.getResultantText());

        }
        resultText=text.toString();
        resultText = resultText.replaceAll("-\n", "");
        System.out.println("-->"+resultText);

        StringTokenizer stringTokenizer=new StringTokenizer(resultText, "\n");
        PrintWriter lineWriter = new PrintWriter(new FileOutputStream("G:/test.txt"));
        while (stringTokenizer.hasMoreTokens()){
            String curToken = stringTokenizer.nextToken();
            lineWriter.println(curToken);
        }
        lineWriter.flush();
        lineWriter.close();
        out.flush();
        out.close();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

	}

