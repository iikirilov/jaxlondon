package io.blk.jaxlondon;

import io.blk.contracts.generated.JaxToken;

import okhttp3.OkHttpClient;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;


public class JaxTokenExample {

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .authenticator((route, response) -> {
                    String credential = okhttp3.Credentials.basic("epirus", "epirus-rocks");
                    return response.request().newBuilder().header("Authorization", credential).build();
                })
                .build();

        Web3j web3j = Web3j.build(
                new HttpService(
                        "https://epirus:epirus-rocks@geth.epirus.web3labs.com/rinkeby",
                        client,
                        false));

        // Load personal rinkeby account
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "<password>",
                        "<walletfile>");

        // Deploy JAXToken
        JaxToken jaxToken = JaxToken.deploy(
                web3j, credentials,
                new StaticGasProvider(DefaultGasProvider.GAS_PRICE,
                        DefaultGasProvider.GAS_LIMIT))
                .send();

        // Query smart contract to find out the balance of our personal account
        System.out.println(
                jaxToken.balanceOf(credentials.getAddress()).send());

    }
}
