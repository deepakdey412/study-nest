package com.deepak.Study_Nest.service;

import com.deepak.Study_Nest.service.CertificateService.CertificateData;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class PdfCertificateService {

    // Colors matching the certificate design
    private static final Color DARK_BLUE = new Color(13, 27, 62);      // Dark navy blue for borders
    private static final Color MEDIUM_BLUE = new Color(25, 55, 109);   // Medium blue for accents
    private static final Color LIGHT_BLUE = new Color(41, 128, 185);   // Light blue for highlights
    private static final Color TEXT_DARK = new Color(51, 51, 51);      // Dark gray for text
    private static final Color TEXT_LIGHT = new Color(102, 102, 102);  // Light gray for secondary text

    public byte[] generateCertificatePdf(CertificateData certData) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try (PDDocument document = new PDDocument()) {
            // Create landscape page (A4 landscape)
            PDPage page = new PDPage(new PDRectangle(842, 595));
            document.addPage(page);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                
                float pageWidth = page.getMediaBox().getWidth();
                float pageHeight = page.getMediaBox().getHeight();
                
                // Draw decorative borders and background
                drawCertificateBackground(contentStream, pageWidth, pageHeight);
                
                // Draw main content area (white box)
                contentStream.setNonStrokingColor(Color.WHITE);
                contentStream.addRect(90, 85, pageWidth - 180, pageHeight - 170);
                contentStream.fill();
                
                // Draw border around white box
                contentStream.setStrokingColor(MEDIUM_BLUE);
                contentStream.setLineWidth(2);
                contentStream.addRect(90, 85, pageWidth - 180, pageHeight - 170);
                contentStream.stroke();
                
                // Draw "STUDY NEST" badge in top right
                drawStudyNestBadge(contentStream, pageWidth, pageHeight);
                
                // Title: "CERTIFICATE"
                contentStream.setNonStrokingColor(DARK_BLUE);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 48);
                String title = "CERTIFICATE";
                float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000 * 48;
                contentStream.newLineAtOffset((pageWidth - titleWidth) / 2, pageHeight - 160);
                contentStream.showText(title);
                contentStream.endText();
                
                // Subtitle: "OF COMPLETION"
                contentStream.setNonStrokingColor(LIGHT_BLUE);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 18);
                String subtitle = "OF COMPLETION";
                float subtitleWidth = PDType1Font.HELVETICA.getStringWidth(subtitle) / 1000 * 18;
                contentStream.newLineAtOffset((pageWidth - subtitleWidth) / 2, pageHeight - 195);
                contentStream.showText(subtitle);
                contentStream.endText();
                
                // Certificate Number
                contentStream.setNonStrokingColor(TEXT_LIGHT);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                String certNum = "Certificate No: " + certData.getCertificateNumber();
                float certNumWidth = PDType1Font.HELVETICA.getStringWidth(certNum) / 1000 * 10;
                contentStream.newLineAtOffset((pageWidth - certNumWidth) / 2, pageHeight - 215);
                contentStream.showText(certNum);
                contentStream.endText();
                
                // "This certifies that"
                contentStream.setNonStrokingColor(TEXT_DARK);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                String certifies = "This certifies that";
                float certifiesWidth = PDType1Font.HELVETICA.getStringWidth(certifies) / 1000 * 14;
                contentStream.newLineAtOffset((pageWidth - certifiesWidth) / 2, pageHeight - 250);
                contentStream.showText(certifies);
                contentStream.endText();
                
                // Student Name (with underline)
                contentStream.setNonStrokingColor(DARK_BLUE);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 26);
                String studentName = certData.getStudentName();
                float nameWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(studentName) / 1000 * 26;
                float nameX = (pageWidth - nameWidth) / 2;
                float nameY = pageHeight - 285;
                contentStream.newLineAtOffset(nameX, nameY);
                contentStream.showText(studentName);
                contentStream.endText();
                
                // Underline for name
                contentStream.setStrokingColor(LIGHT_BLUE);
                contentStream.setLineWidth(1.5f);
                contentStream.moveTo(nameX - 20, nameY - 5);
                contentStream.lineTo(nameX + nameWidth + 20, nameY - 5);
                contentStream.stroke();
                
                // Student Details (PRN and Roll No)
                contentStream.setNonStrokingColor(TEXT_LIGHT);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 11);
                String details = "PRN: " + certData.getPrn() + "  |  Roll No: " + certData.getRollNo();
                float detailsWidth = PDType1Font.HELVETICA.getStringWidth(details) / 1000 * 11;
                contentStream.newLineAtOffset((pageWidth - detailsWidth) / 2, pageHeight - 310);
                contentStream.showText(details);
                contentStream.endText();
                
                // Achievement text
                contentStream.setNonStrokingColor(TEXT_DARK);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 13);
                String achievement1 = "has successfully completed the";
                float ach1Width = PDType1Font.HELVETICA.getStringWidth(achievement1) / 1000 * 13;
                contentStream.newLineAtOffset((pageWidth - ach1Width) / 2, pageHeight - 340);
                contentStream.showText(achievement1);
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
                String achievement2 = "All the module test of semester " + certData.getSemester();
                float ach2Width = PDType1Font.HELVETICA_BOLD.getStringWidth(achievement2) / 1000 * 13;
                contentStream.newLineAtOffset((pageWidth - ach2Width) / 2, pageHeight - 358);
                contentStream.showText(achievement2);
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 13);
                String achievement3 = "conducted by StudyNest";
                float ach3Width = PDType1Font.HELVETICA.getStringWidth(achievement3) / 1000 * 13;
                contentStream.newLineAtOffset((pageWidth - ach3Width) / 2, pageHeight - 376);
                contentStream.showText(achievement3);
                contentStream.endText();
                
                // Date
                contentStream.setNonStrokingColor(TEXT_DARK);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                String dateText = "on " + certData.getIssueDate();
                float dateWidth = PDType1Font.HELVETICA.getStringWidth(dateText) / 1000 * 12;
                contentStream.newLineAtOffset((pageWidth - dateWidth) / 2, pageHeight - 400);
                contentStream.showText(dateText);
                contentStream.endText();
                
                // Performance box (moved below date)
                drawPerformanceBox(contentStream, certData, pageWidth, pageHeight);
                
                // Signatures
                drawSignatures(contentStream, pageWidth);
                
                log.info("PDF certificate generated successfully for: {}", certData.getStudentName());
            }
            
            document.save(baos);
            
        } catch (IOException e) {
            log.error("Error generating PDF certificate", e);
            throw e;
        }
        
        return baos.toByteArray();
    }
    
    private void drawCertificateBackground(PDPageContentStream contentStream, float pageWidth, float pageHeight) throws IOException {
        // Dark blue outer border
        contentStream.setNonStrokingColor(DARK_BLUE);
        contentStream.addRect(0, 0, pageWidth, pageHeight);
        contentStream.fill();
        
        // Medium blue inner area
        contentStream.setNonStrokingColor(MEDIUM_BLUE);
        contentStream.addRect(15, 15, pageWidth - 30, pageHeight - 30);
        contentStream.fill();
        
        // Decorative triangles (left side)
        contentStream.setNonStrokingColor(LIGHT_BLUE);
        contentStream.moveTo(0, 200);
        contentStream.lineTo(100, 250);
        contentStream.lineTo(0, 300);
        contentStream.closePath();
        contentStream.fill();
        
        contentStream.moveTo(0, 350);
        contentStream.lineTo(80, 380);
        contentStream.lineTo(0, 410);
        contentStream.closePath();
        contentStream.fill();
        
        // Decorative triangles (right side)
        contentStream.moveTo(pageWidth, 200);
        contentStream.lineTo(pageWidth - 100, 250);
        contentStream.lineTo(pageWidth, 300);
        contentStream.closePath();
        contentStream.fill();
        
        contentStream.moveTo(pageWidth, 350);
        contentStream.lineTo(pageWidth - 80, 380);
        contentStream.lineTo(pageWidth, 410);
        contentStream.closePath();
        contentStream.fill();
    }
    
    private void drawStudyNestBadge(PDPageContentStream contentStream, float pageWidth, float pageHeight) throws IOException {
        float badgeX = pageWidth - 150;
        float badgeY = pageHeight - 150;
        
        // Badge background (ribbon shape)
        contentStream.setNonStrokingColor(DARK_BLUE);
        contentStream.addRect(badgeX, badgeY - 40, 80, 60);
        contentStream.fill();
        
        // Badge text
        contentStream.setNonStrokingColor(Color.WHITE);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        String badge1 = "STUDY";
        float badge1Width = PDType1Font.HELVETICA_BOLD.getStringWidth(badge1) / 1000 * 12;
        contentStream.newLineAtOffset(badgeX + (80 - badge1Width) / 2, badgeY + 5);
        contentStream.showText(badge1);
        contentStream.endText();
        
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        String badge2 = "NEST";
        float badge2Width = PDType1Font.HELVETICA_BOLD.getStringWidth(badge2) / 1000 * 12;
        contentStream.newLineAtOffset(badgeX + (80 - badge2Width) / 2, badgeY - 10);
        contentStream.showText(badge2);
        contentStream.endText();
    }
    
    private void drawPerformanceBox(PDPageContentStream contentStream, CertificateData certData, 
                                    float pageWidth, float pageHeight) throws IOException {
        float boxY = 165;  // Moved lower to avoid overlap
        float boxWidth = 300;
        float boxHeight = 50;
        float boxX = (pageWidth - boxWidth) / 2;
        
        // Box background
        contentStream.setNonStrokingColor(new Color(240, 248, 255)); // Light blue background
        contentStream.addRect(boxX, boxY, boxWidth, boxHeight);
        contentStream.fill();
        
        // Box border
        contentStream.setStrokingColor(LIGHT_BLUE);
        contentStream.setLineWidth(1);
        contentStream.addRect(boxX, boxY, boxWidth, boxHeight);
        contentStream.stroke();
        
        // Performance text
        contentStream.setNonStrokingColor(TEXT_DARK);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        String perf1 = "Tests Passed: " + certData.getPassedTests() + "/" + certData.getTotalTests();
        float perf1Width = PDType1Font.HELVETICA_BOLD.getStringWidth(perf1) / 1000 * 10;
        contentStream.newLineAtOffset((pageWidth - perf1Width) / 2, boxY + 30);
        contentStream.showText(perf1);
        contentStream.endText();
        
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 10);
        String perf2 = "Average Score: " + certData.getAveragePercentage() + "%";
        float perf2Width = PDType1Font.HELVETICA.getStringWidth(perf2) / 1000 * 10;
        contentStream.newLineAtOffset((pageWidth - perf2Width) / 2, boxY + 15);
        contentStream.showText(perf2);
        contentStream.endText();
    }
    
    private void drawSignatures(PDPageContentStream contentStream, float pageWidth) throws IOException {
        float signY = 115;  // Moved up slightly to give space
        
        // Left signature
        contentStream.setNonStrokingColor(TEXT_DARK);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
        String sign1Name = "Nathan Cole";
        float sign1Width = PDType1Font.HELVETICA_BOLD.getStringWidth(sign1Name) / 1000 * 11;
        contentStream.newLineAtOffset(200 - sign1Width / 2, signY);
        contentStream.showText(sign1Name);
        contentStream.endText();
        
        contentStream.setNonStrokingColor(TEXT_LIGHT);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 9);
        String sign1Title = "Chief Executive Officer";
        float sign1TitleWidth = PDType1Font.HELVETICA.getStringWidth(sign1Title) / 1000 * 9;
        contentStream.newLineAtOffset(200 - sign1TitleWidth / 2, signY - 15);
        contentStream.showText(sign1Title);
        contentStream.endText();
        
        // Signature line
        contentStream.setStrokingColor(TEXT_LIGHT);
        contentStream.setLineWidth(0.5f);
        contentStream.moveTo(150, signY + 15);
        contentStream.lineTo(250, signY + 15);
        contentStream.stroke();
        
        // Right signature
        contentStream.setNonStrokingColor(TEXT_DARK);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
        String sign2Name = "Aaron Loeb";
        float sign2Width = PDType1Font.HELVETICA_BOLD.getStringWidth(sign2Name) / 1000 * 11;
        contentStream.newLineAtOffset(pageWidth - 200 - sign2Width / 2, signY);
        contentStream.showText(sign2Name);
        contentStream.endText();
        
        contentStream.setNonStrokingColor(TEXT_LIGHT);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 9);
        String sign2Title = "Training Coordinator";
        float sign2TitleWidth = PDType1Font.HELVETICA.getStringWidth(sign2Title) / 1000 * 9;
        contentStream.newLineAtOffset(pageWidth - 200 - sign2TitleWidth / 2, signY - 15);
        contentStream.showText(sign2Title);
        contentStream.endText();
        
        // Signature line
        contentStream.setStrokingColor(TEXT_LIGHT);
        contentStream.setLineWidth(0.5f);
        contentStream.moveTo(pageWidth - 250, signY + 15);
        contentStream.lineTo(pageWidth - 150, signY + 15);
        contentStream.stroke();
    }
}
