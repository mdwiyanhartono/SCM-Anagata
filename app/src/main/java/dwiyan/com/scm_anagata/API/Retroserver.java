package dwiyan.com.scm_anagata.API;

import android.content.Context;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dwiyanon.
 */

public class Retroserver {

    private static OkHttpClient getUnsafeOkHttpClient(int TO) {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager

            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.readTimeout(10, TimeUnit.SECONDS)
//                    .connectTimeout(10, TimeUnit.SECONDS)
//                    .writeTimeout(10, TimeUnit.SECONDS);
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    }).readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS);

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //    private static final String base_url = "https://192.168.43.136/Api-IconPlus/"; //local
//    private static final String base_url = "https://192.168.31.138/Api-SCM/"; //localAnagata
//    private static final String base_url = "https://192.168.8.103/Api-SCM/"; //localModem
//    private static final String base_url = "https://172.20.10.15/Api-SCM/"; //localHotspotIphone
    private static final String base_url = "https://103.16.198.159/Api-SCM/"; //serverAnagata
//    private static final String base_url = "https://58.65.244.45/Api-BrightNet/"; //ServerMultitek
//    private static final String base_url = "https://bright.isat.net.id/Api-BrightNet/"; //ServerBatam
//    private static final String base_url = "https://192.168.100.151/Api-IconPlus/"; //LocalWIFICOTTA

    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        int TO = 10;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getUnsafeOkHttpClient(TO))
                    .build();
        }
        return retrofit;
    }
}