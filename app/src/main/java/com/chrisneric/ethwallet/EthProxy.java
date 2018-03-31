package com.chrisneric.ethwallet;

import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.chrisneric.ethwallet.AppGlobals.ETH_ACCOUNT_URL;
import static com.chrisneric.ethwallet.AppGlobals.ETH_PRICE_URL;

public class EthProxy {
    private static JSONObject ethStatus;

    public static JSONObject convertStreamToJson(InputStream is) throws Exception {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String line;

        while ((line = streamReader.readLine()) != null) {
            responseStrBuilder.append(line);
        }
        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

        is.close();
        return jsonObject;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
        return sb.toString();
    }

    public static void getEthBalance(final TextView outLabel) {
        HttpGetAsyncTask task = new HttpGetAsyncTask();
        task.setUpdateListener(new HttpGetAsyncTask.OnUpdateListener() {
            public void onUpdate(InputStream obj) {
                try {
                    outLabel.setText(Double.toString(convertStreamToJson(obj).getDouble("result") / 1000000000000000000L) + " ETH");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        task.execute(ETH_ACCOUNT_URL);
    }

    public static void getEthValue(final TextView usdLabel, final TextView btcLabel) {
        HttpGetAsyncTask task = new HttpGetAsyncTask();
        task.setUpdateListener(new HttpGetAsyncTask.OnUpdateListener() {
            public void onUpdate(InputStream obj) {
                try {
                    ethStatus = convertStreamToJson(obj);
                    usdLabel.setText(ethStatus.getJSONObject("result").getString("ethusd") + " USD");
                    btcLabel.setText(ethStatus.getJSONObject("result").getString("ethbtc") + " BTC");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        task.execute(ETH_PRICE_URL);
    }
}

