package com.pdf.parsing;

import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;

public class PdfReadExample {
    
    private static final String FILE_NAME = "lemh1a1.pdf";
    
    public static void main(String[] args) {
        
        PdfReader reader;
        
        try {
            
            reader = new PdfReader(FILE_NAME);
    
            for(int i =0; i < reader.getXrefSize(); i++)
            {
                PdfObject pdfobj = reader.getPdfObject(i);
                System.out.println("pdfobj = " + pdfobj);
                
                if (pdfobj != null && pdfobj.isStream()) {
                    PdfStream stream = (PdfStream) pdfobj;
                    System.out.println("stream = " + stream);
                    PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
                    System.out.println("pdfsubtype = " + pdfsubtype);
                }
            }
            
            // pageNumber = 1
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
            
            System.out.println(textFromPage);
            
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
