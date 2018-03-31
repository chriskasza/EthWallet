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

        if (!streamReader.ready()) {
            System.out.println("########################\nNOT READY");
            return null;
        }

        while ((line = streamReader.readLine()) != null) {
            responseStrBuilder.append(line);
            if (!streamReader.ready()) {
                break;
            }
        }
        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

        is.close();
        return jsonObject;
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
                    usdLabel.setText(ethStatus.getJSONObject("result").getString("ethusd"));
                    btcLabel.setText(ethStatus.getJSONObject("result").getString("ethbtc"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        task.execute(ETH_PRICE_URL);
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

//    protected static class EthVal {
//        static double ethbtc;
//        static int ethbtc_timestamp;
//        static double ethusd;
//        static int ethusd_timestamp;
//    }

//    public static void parseEthJson(InputStreamReader reader) {
//        try {
//            if (reader != null) {
//                JsonReader jsonReader = new JsonReader(reader);
//
//                jsonReader.beginObject(); // Start processing the JSON object
//                while (jsonReader.hasNext()) { // Loop through all keys
//                    String key = jsonReader.nextName(); // Fetch the next key
//
//                    System.out.println("JsonReader key, " + key + ", and value, " + jsonReader.peek());
//
//                    switch (key) {
//                        case ("ethbtc"):
//                            EthVal.ethbtc = jsonReader.nextDouble();
//                            break;
//                        case ("ethbtc_timestamp"):
//                            EthVal.ethbtc_timestamp = jsonReader.nextInt();
//                            break;
//                        case ("ethusd"):
//                            EthVal.ethusd = jsonReader.nextDouble();
//                            break;
//                        case ("ethusd_timestamp"):
//                            EthVal.ethusd_timestamp = jsonReader.nextInt();
//                            break;
//                        case ("result"):
//                            break;
//                        default:
//                            jsonReader.skipValue(); // Skip values of other keys
//                            break;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

