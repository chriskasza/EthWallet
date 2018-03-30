package com.chrisneric.ethwallet;

import android.util.JsonReader;

import java.io.InputStreamReader;

public class EthProxy {
    public static void parseEthJson(InputStreamReader reader) {
        try {
            if (reader != null) {
                JsonReader jsonReader = new JsonReader(reader);

                jsonReader.beginObject(); // Start processing the JSON object
                while (jsonReader.hasNext()) { // Loop through all keys
                    String key = jsonReader.nextName(); // Fetch the next key

                    System.out.println("JsonReader key, " + key + ", and value, " + jsonReader.peek());

                    switch (key) {
                        case ("ethbtc"):
                            EthVal.ethbtc = jsonReader.nextDouble();
                            break;
                        case ("ethbtc_timestamp"):
                            EthVal.ethbtc_timestamp = jsonReader.nextInt();
                            break;
                        case ("ethusd"):
                            EthVal.ethusd = jsonReader.nextDouble();
                            break;
                        case ("ethusd_timestamp"):
                            EthVal.ethusd_timestamp = jsonReader.nextInt();
                            break;
                        case ("result"):
                            break;
                        default:
                            jsonReader.skipValue(); // Skip values of other keys
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static class EthVal {
        static double ethbtc;
        static int ethbtc_timestamp;
        static double ethusd;
        static int ethusd_timestamp;
    }
}

