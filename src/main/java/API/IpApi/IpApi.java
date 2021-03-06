package API.IpApi;

import API.ApiBase;
import API.IpApi.IpProperties.IpMaster;
import lombok.Getter;

@Getter
public class IpApi extends ApiBase {

    private static final String host = "http://ip-api.com/json/";
    private static IpMaster ipMaster;
    private final double lat;
    private final double lon;


    public IpApi() {
        ipMaster = convertJsonToJava(callApi(host), IpMaster.class);
        this.lat = ipMaster.getLat();
        this.lon = ipMaster.getLon();
    }

}
