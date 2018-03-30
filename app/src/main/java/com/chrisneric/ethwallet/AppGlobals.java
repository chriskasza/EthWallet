package com.chrisneric.ethwallet;

public class AppGlobals {
    public static final String ETHEREUM_ADDRESS = "0x1A734D01000212E5f03bE1E3829b92d52fa1e888";
    public static final String ETHERSCAN_KEY = "2FKCN18ST4SQ34KFYJQUKYXI8AIRGBV5SE";
    public static final String ETHERSCAN_BASE_URL = "https://api.etherscan.io/";
    public static final String ETH_PRICE_URL = ETHERSCAN_BASE_URL +
            "api?module=stats&action=ethprice&apikey=" +
            ETHERSCAN_KEY;
    public static final String ETH_ACCOUNT_URL = ETHERSCAN_BASE_URL +
            "api?module=account&action=balance&address=" +
            ETHEREUM_ADDRESS +
            "&tag=latest&apikey=2FKCN18ST4SQ34KFYJQUKYXI8AIRGBV5SE";
}
