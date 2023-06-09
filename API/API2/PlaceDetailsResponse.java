package mainActivity.API.API2;

public class PlaceDetailsResponse {
    private PlaceResult result;
    private String status;


    public PlaceDetailsResponse(PlaceResult result, String status) {
        this.result = result;
        this.status = status;
    }
    public PlaceResult getResult() {
        return result;
    }
}
