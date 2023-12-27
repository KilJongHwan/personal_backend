package com.projectBackend.project.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class TextCompressor {

    public static String compress(String text) throws IOException {
        if (text == null || text.isEmpty()) {
            return text;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gzipOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
        }
        // Base64 인코딩을 사용하여 바이트 배열을 문자열로 변환
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }


    public static String decompress(String compressedText) throws IOException {
        // Base64 디코딩을 사용하여 문자열을 바이트 배열로 변환
        byte[] compressedData = Base64.getDecoder().decode(compressedText);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(compressedData);
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(bais)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipInputStream.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
        }
        return new String(baos.toByteArray(), StandardCharsets.UTF_8);
    }
}
