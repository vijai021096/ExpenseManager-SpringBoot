package com.luv2code.spring.ExpenseTracker.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.luv2code.spring.ExpenseTracker.Model.Expense;

public class GeneratePdfReport {
	 public static ByteArrayInputStream expenseReport(List<Expense> expenses) {

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {
	        	 PdfWriter.getInstance(document, out);
		            document.open();
	        	 String para = "Your Expenses"; 
		        	
		        	Paragraph parag = new Paragraph (para); 
		        	parag.setAlignment(Element.ALIGN_CENTER);
		        	document.add(parag); 
		        	
		        	 document.add( Chunk.NEWLINE );
		             document.add( Chunk.NEWLINE );
		             
	            PdfPTable table = new PdfPTable(4);
	            table.setWidthPercentage(80);
	            table.setWidths(new int[]{5,8,6,3});

	            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

	            PdfPCell hcell;
	            hcell = new PdfPCell(new Phrase("Expense Date", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Category", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);
	            
	            hcell = new PdfPCell(new Phrase("Notes", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            hcell = new PdfPCell(new Phrase("Amount", headFont));
	            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            table.addCell(hcell);

	            int sum=0;
	            
	            for (Expense expense : expenses) {
                   sum+=expense.getAmount();
	            	
	                PdfPCell cell;

	                cell = new PdfPCell(new Phrase(String.valueOf(expense.getExpenseDate())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                cell.setPaddingLeft(5);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(expense.getCategory()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(cell);

	                cell = new PdfPCell(new Phrase(expense.getExpenseNotes()));
	                cell.setPaddingLeft(5);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	                table.addCell(cell);

	                
	                cell = new PdfPCell(new Phrase(String.valueOf(expense.getAmount())));
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.setPaddingRight(5);
	                table.addCell(cell);
	            }

	           
	            document.add(table);
	           
	            document.add( Chunk.NEWLINE );
	          
	        	
	        	PdfPTable ta = new PdfPTable(1);
	        	ta.setWidthPercentage(100);
	        	ta.addCell(getCell("Total Expense="+sum, PdfPCell.ALIGN_RIGHT));
	        	document.add(ta);
	            document.close();
	            
	        } catch (DocumentException ex) {
	        
	            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return new ByteArrayInputStream(out.toByteArray());
	    }
	 
	 public static PdfPCell getCell(String text, int alignment) {
 	    PdfPCell cell = new PdfPCell(new Phrase(text));
 	    cell.setPadding(0);
 	    cell.setHorizontalAlignment(alignment);
 	    cell.setBorder(PdfPCell.NO_BORDER);
 	    return cell;
 	}
	}

