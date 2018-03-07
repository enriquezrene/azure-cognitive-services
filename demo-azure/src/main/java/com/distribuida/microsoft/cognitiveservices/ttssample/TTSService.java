package com.microsoft.cognitiveservices.ttssample;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.InputStream;

public class TTSService {

    private static String ttsServiceUri = "https://speech.platform.bing.com/synthesize";

    /**
     * Synthesize the voice through the specified parameters.
     */
    public static byte[] Synthesize(String textToSynthesize, String outputFormat, String locale, String genderName, String voiceName) throws Exception {

        // Note: The way to get api key:
        // Free: https://www.microsoft.com/cognitive-services/en-us/subscriptions?productId=/products/Bing.Speech.Preview
        // Paid: https://portal.azure.com/#create/Microsoft.CognitiveServices/apitype/Bing.Speech/pricingtier/S0

        Authentication auth = new Authentication("3d75c7d0b2a743d3872b87fd70081784");
        String accessToken = auth.GetAccessToken();

        HttpsURLConnection webRequest = HttpsConnection.getHttpsConnection(ttsServiceUri);
        webRequest.setDoInput(true);
        webRequest.setDoOutput(true);
        webRequest.setConnectTimeout(5000);
        webRequest.setReadTimeout(15000);
        webRequest.setRequestMethod("POST");

        webRequest.setRequestProperty("Content-Type", "application/ssml+xml");
        webRequest.setRequestProperty("X-Microsoft-OutputFormat", outputFormat);
        webRequest.setRequestProperty("Authorization", "Bearer " + accessToken);
        webRequest.setRequestProperty("X-Search-AppId", "07D3234E49CE426DAA29772419F436CA");
        webRequest.setRequestProperty("X-Search-ClientID", "1ECFAE91408841A480F00935DC390960");
        webRequest.setRequestProperty("User-Agent", "TTSAndroid");
        webRequest.setRequestProperty("Accept", "*/*");

        String body = XmlDom.createDom(locale, genderName, voiceName, textToSynthesize);
        byte[] bytes = body.getBytes();
        webRequest.setRequestProperty("content-length", String.valueOf(bytes.length));
        webRequest.connect();

        DataOutputStream dop = new DataOutputStream(webRequest.getOutputStream());
        dop.write(bytes);
        dop.flush();
        dop.close();

        InputStream inSt = webRequest.getInputStream();
        ByteArray ba = new ByteArray();

        int rn2 = 0;
        int bufferLength = 4096;
        byte[] buf2 = new byte[bufferLength];
        while ((rn2 = inSt.read(buf2, 0, bufferLength)) > 0) {
            ba.cat(buf2, 0, rn2);
        }

        inSt.close();
        webRequest.disconnect();

        return ba.getArray();
    }
}
