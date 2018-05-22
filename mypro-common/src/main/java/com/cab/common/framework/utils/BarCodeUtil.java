package com.cab.common.framework.utils;

/**
 * Created by admin on 2018/5/21.
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class BarCodeUtil {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, bm.get(i, j) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeBitMatricToFile(BitMatrix bm, String format,
                                            File file) {
        BufferedImage image = toBufferedImage(bm);
        try {
            if (!ImageIO.write(image, format, file)) {
                throw new RuntimeException("Can not write an image to file" + file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format " + format);
        }
    }


    public static String generateBarCode(int width, int height, String text) throws Exception{
        // 条形码的输入是13位的数字
        String format = "png";
        HashMap<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bm = new MultiFormatWriter().encode(text,
                BarcodeFormat.EAN_13, width, height, hints);
        // 生成条形码图片
        String outFilePath = "D:\\myworkspace\\mypro-parent\\mypro-web\\src\\main\\webapp\\template\\generator\\barcode";
        String outFile = outFilePath+"\\"+text+".png";
        File out = new File(outFile);
        BarCodeUtil.writeBitMatricToFile(bm, format, out);
        return outFile;
    }

    public static void main(String[] args) throws Exception{
        generateBarCode(105,50,"6923450657713");
    }
}

