package com.hhj.shop.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QR {

    private static final int BLACK = 0xFF000000; //黑色
    private static final int WHITE = 0xFFFFFFFF;  //白色

    public QR() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        throw new IOException("Could not write an image of format "
                + format + " to " + file);
    }


    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }

    //返回二维码
    public void makeQRCode(String qr_code) throws WriterException, IOException {


        int width = 300; // 二维码图片宽度
        int height = 300; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码

        BitMatrix bitMatrix = new MultiFormatWriter().encode(qr_code,
                BarcodeFormat.QR_CODE, width, height, hints);
        // 生成二维码
        File outputFile = new File("d:" + File.separator + "new.jpg");
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }

    //直接返回二维码
    public void makeQRCode2(String content, OutputStream outputStream) throws Exception{  //生成二维码
        //EncodeHintType 编码类型   设置zxing二维码空白区域大小
        Map<EncodeHintType,Object> map=new HashMap<>();
        // StandardCharsets.UTF_8    JDK1.7自带的字符编码产量字段
        map.put(EncodeHintType.CHARACTER_SET, "utf-8" );
        map.put(EncodeHintType.MARGIN,2);
        //二维码的制作
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        //BarcodeFormat 条形码
        BitMatrix bitMatrix=qrCodeWriter.encode(content, BarcodeFormat.QR_CODE,200,200,map);
        MatrixToImageWriter.writeToStream(bitMatrix,"jpeg",outputStream);

    }


}
