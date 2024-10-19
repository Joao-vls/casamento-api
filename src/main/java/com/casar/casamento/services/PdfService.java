package com.casar.casamento.services;

import com.casar.casamento.model.CasamentoSemContrato;
import com.casar.casamento.model.Usuario;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

    @Autowired
    CasamentoSemContratoService casamentoSemContratoService;

    public ByteArrayInputStream generatePdf(String content) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PageSize pageSize = new PageSize(283.5f, 425.25f);
            // Criar o escritor de PDF
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument, pageSize);

            if (authentication != null && authentication.getPrincipal() instanceof Usuario usuario) {

                List<CasamentoSemContrato> usuarioList = casamentoSemContratoService.getCasamentosPorUsuario(usuario);
                String noivosFormatted = String.join(" e ", usuarioList.get(0).getNoivos());
                String local= String.valueOf(usuarioList.get(0).getLocal());

                // Adicionar uma nova página
                PdfCanvas canvas = new PdfCanvas(pdfDocument.addNewPage());

                // Adicionar as imagens (ajuste a posição conforme necessário)
                addImageToPdf(document, "static/decora2.png", -20, -130);
                addImageToPdf(document, "static/decora1.png", -70, 180);

                // Carregar a fonte personalizada do classpath
                PdfFont fontGreatVibes = addFonte("static/fonts/GreatVibes-Regular.ttf");
                PdfFont fontOpenSans = addFonte("static/fonts/OpenSans_Condensed-SemiBold.ttf");

                // Obter a largura da página
                float pageWidth = pageSize.getWidth();

                // Centralizar o título "Casamento"
                String title = "Casamento";
                float titleWidth = fontGreatVibes.getWidth(title, 32);
                float titleX = (pageWidth - titleWidth) / 2;

                canvas.beginText();
                canvas.setFontAndSize(fontGreatVibes, 32);
                canvas.moveText(titleX, 260); // Define a posição do texto
                canvas.showText(title);
                canvas.endText();

                // Centralizar o texto dos noivos
                float noivosWidth = fontGreatVibes.getWidth(noivosFormatted, 32);
                float noivosX = (pageWidth - noivosWidth) / 2;

                canvas.setColor(new DeviceRgb(108, 144, 167), true);
                canvas.beginText();
                canvas.setFontAndSize(fontGreatVibes, 32);
                canvas.moveText(noivosX, 200); // Define a posição do texto
                canvas.showText(noivosFormatted);
                canvas.endText();

                // Centralizar o conteúdo
                String line1 = content + " venha celebrar essa";
                String line2 = "nova fase com a gente!";

                float line1Width = fontOpenSans.getWidth(line1, 15);
                float line2Width = fontOpenSans.getWidth(line2, 15);

                float line1X = (pageWidth - line1Width) / 2;
                float line2X = (pageWidth - line2Width) / 2;

                canvas.setColor(new DeviceRgb(0, 0, 0), true);
                canvas.beginText();
                canvas.setFontAndSize(fontOpenSans, 15);
                canvas.moveText(line1X, 120); // Centraliza e define a posição do texto
                canvas.showText(line1);
                canvas.moveText(line2X - line1X, -15); // Centraliza a segunda linha e move para baixo
                canvas.showText(line2);
                canvas.endText();

                line1 = local;
                line1Width = fontOpenSans.getWidth(line1, 10);
                line1X = (pageWidth - line1Width) / 2;

                canvas.setColor(new DeviceRgb(0, 0, 0), true);
                canvas.beginText();
                canvas.setFontAndSize(fontOpenSans, 10);
                canvas.moveText(line1X, 85); // Centraliza e define a posição do texto
                canvas.showText(line1);
                canvas.endText();
            }
            // Fechar o documento
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }


    private void addImageToPdf(Document document, String imagePath, float x, float y) throws Exception {
        ClassPathResource imgFile = new ClassPathResource(imagePath);
        InputStream imgStream = imgFile.getInputStream();
        byte[] imgBytes = imgStream.readAllBytes();
        ImageData imageData = ImageDataFactory.create(imgBytes);
        Image image = new Image(imageData);
        image.scaleToFit(320, 320); // Ajusta o tamanho da imagem
        image.setFixedPosition(x, y); // Define a posição da imagem
        document.add(image);
    }

    private PdfFont addFonte(String s) throws IOException {
        ClassPathResource fontResource = new ClassPathResource(s);
        InputStream fontStream = fontResource.getInputStream();
        return PdfFontFactory.createFont(fontStream.readAllBytes(), PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED);
    }
}
