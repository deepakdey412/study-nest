package com.deepak.Study_Nest.service;

import com.deepak.Study_Nest.service.CertificateService.CertificateData;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class PdfCertificateService {

    public byte[] generateCertificatePdf(CertificateData certData) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try (PDDocument document = new PDDocument()) {
            // Create page with landscape orientation to match certificate image
            PDPage page = new PDPage(new PDRectangle(842, 595)); // A4 Landscape
            document.addPage(page);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                
                float pageWidth = page.getMediaBox().getWidth();
                float pageHeight = page.getMediaBox().getHeight();
                
                // Load and draw background image
                try {
                    ClassPathResource imgFile = new ClassPathResource("STUDY.png");
                    PDImageXObject pdImage = PDImageXObject.createFromByteArray(
                        document, 
                        imgFile.getInputStream().readAllBytes(), 
                        "background"
                    );
                    
                    // Draw image to fill entire page maintaining aspect ratio
                    float imgWidth = pdImage.getWidth();
                    float imgHeight = pdImage.getHeight();
                    float scale = Math.max(pageWidth / imgWidth, pageHeight / imgHeight);
                    float scaledWidth = imgWidth * scale;
                    float scaledHeight = imgHeight * scale;
                    float x = (pageWidth - scaledWidth) / 2;
                    float y = (pageHeight - scaledHeight) / 2;
                    
                    contentStream.drawImage(pdImage, x, y, scaledWidth, scaledHeight);
                    
                    log.info("Background image loaded successfully");
                } catch (Exception e) {
                    log.error("Could not load background image: {}", e.getMessage());
                    throw new IOException("Background image STUDY.png not found in resources", e);
                }
                
                // Set text color to dark (matching certificate design)
                contentStream.setNonStrokingColor(0.2f, 0.2f, 0.2f); // Dark gray/black
                
                // Student Name - positioned in the blank line after "This certifies that"
                // Based on the image, this appears around 350px from top
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                String studentName = certData.getStudentName();
                float nameWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(studentName) / 1000 * 20;
                contentStream.newLineAtOffset((pageWidth - nameWidth) / 2, pageHeight - 245);
                contentStream.showText(studentName);
                contentStream.endText();
                
                // Semester Number - positioned in the blank after "All the module test of semester"
                // Based on the image, this appears around 420px from top
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                String semester = String.valueOf(certData.getSemester());
                // Position it where the blank is (approximately at x=610)
                contentStream.newLineAtOffset(610, pageHeight - 305);
                contentStream.showText(semester);
                contentStream.endText();
                
                // Date - positioned in the blank after "on"
                // Based on the image, this appears around 500px from top
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                String date = certData.getIssueDate();
                // Position it where the blank is (approximately at x=185)
                contentStream.newLineAtOffset(185, pageHeight - 365);
                contentStream.showText(date);
                contentStream.endText();
                
                log.info("PDF certificate generated successfully for: {}", certData.getStudentName());
            }
            
            document.save(baos);
            
        } catch (IOException e) {
            log.error("Error generating PDF certificate", e);
            throw e;
        }
        
        return baos.toByteArray();
    }
}
