package pick.login;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;
import pick.arguments.Exceptions;
import pick.arguments.SteamUri;

public class SteamAuthenticator {
    private final CallBack callBack;
    private final ConsumerManager consumerManager = new ConsumerManager();
    private final DiscoveryInformation discoveryInformation;

    public SteamAuthenticator(CallBack callBack) throws DiscoveryException {
        if (callBack == null) {
            throw new IllegalArgumentException(Exceptions.NULL_PARAMETER.getException());
        } else {
            this.callBack = callBack;
            this.consumerManager.setMaxAssocAttempts(0);
            this.discoveryInformation = this.consumerManager.associate(this.consumerManager.discover(SteamUri.STEAM_OPENID.getSteamUri()));
        }
    }

    public String getLoginUrl() throws MessageException, ConsumerException {
        AuthRequest authRequest = this.consumerManager.authenticate(this.discoveryInformation, this.callBack.getUrl());
        return authRequest.getDestinationUrl(true);
    }

    public Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> queryPairs = new HashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return queryPairs;
    }

    public String getVerifiedSteamId(Map<String, String> responseMap) throws MessageException, DiscoveryException, AssociationException {
        if (responseMap == null) {
            throw new IllegalArgumentException(Exceptions.NULL_PARAMETER.getException());
        } else {
            ParameterList parameterList = new ParameterList(responseMap);
            VerificationResult verificationResult = this.consumerManager.verify(this.callBack.getUrl(), parameterList, this.discoveryInformation);
            Identifier verifiedId = verificationResult.getVerifiedId();
            if (verifiedId == null) {
                throw new IllegalArgumentException(Exceptions.NULL_PARAMETER.getException());
            } else {
                return this.extractSteamId(verifiedId.getIdentifier());
            }
        }
    }

    private String extractSteamId(String identifier) {
        return identifier.replaceAll("https://steamcommunity.com/openid/id/", "");
    }
}
