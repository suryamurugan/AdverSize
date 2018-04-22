package ads.in.adversize.adversize.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MediaObject {

    @SerializedName("mediaID")
    @Expose
    private String mediaID;
    @SerializedName("mediaName")
    @Expose
    private String mediaName;
    @SerializedName("mediaWidth")
    @Expose
    private String mediaWidth;
    @SerializedName("mediaHeight")
    @Expose
    private String mediaHeight;
    @SerializedName("mediaStreet")
    @Expose
    private String mediaStreet;
    @SerializedName("mediaCity")
    @Expose
    private String mediaCity;
    @SerializedName("mediaState")
    @Expose
    private String mediaState;
    @SerializedName("mediaGoogleLatitude")
    @Expose
    private String mediaGoogleLatitude;
    @SerializedName("mediaGoogleLongitude")
    @Expose
    private String mediaGoogleLongitude;
    @SerializedName("mediaPrice")
    @Expose
    private String mediaPrice;
    @SerializedName("mediaPrice6")
    @Expose
    private String mediaPrice6;
    @SerializedName("mediaPrice12")
    @Expose
    private String mediaPrice12;
    @SerializedName("mediaImgLocation")
    @Expose
    private String mediaImgLocation;
    @SerializedName("mediaAvailability")
    @Expose
    private String mediaAvailability;
    @SerializedName("mediaRating")
    @Expose
    private Object mediaRating;
    @SerializedName("productID")
    @Expose
    private String productID;
    @SerializedName("vendorID")
    @Expose
    private String vendorID;
    @SerializedName("mediaTotalPrice")
    @Expose
    private String mediaTotalPrice;
    @SerializedName("mediaTotalPrice6")
    @Expose
    private String mediaTotalPrice6;
    @SerializedName("mediaTotalPrice12")
    @Expose
    private String mediaTotalPrice12;

    public String getMediaID() {
        return mediaID;
    }

    public void setMediaID(String mediaID) {
        this.mediaID = mediaID;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaWidth() {
        return mediaWidth;
    }

    public void setMediaWidth(String mediaWidth) {
        this.mediaWidth = mediaWidth;
    }

    public String getMediaHeight() {
        return mediaHeight;
    }

    public void setMediaHeight(String mediaHeight) {
        this.mediaHeight = mediaHeight;
    }

    public String getMediaStreet() {
        return mediaStreet;
    }

    public void setMediaStreet(String mediaStreet) {
        this.mediaStreet = mediaStreet;
    }

    public String getMediaCity() {
        return mediaCity;
    }

    public void setMediaCity(String mediaCity) {
        this.mediaCity = mediaCity;
    }

    public String getMediaState() {
        return mediaState;
    }

    public void setMediaState(String mediaState) {
        this.mediaState = mediaState;
    }

    public String getMediaGoogleLatitude() {
        return mediaGoogleLatitude;
    }

    public void setMediaGoogleLatitude(String mediaGoogleLatitude) {
        this.mediaGoogleLatitude = mediaGoogleLatitude;
    }

    public String getMediaGoogleLongitude() {
        return mediaGoogleLongitude;
    }

    public void setMediaGoogleLongitude(String mediaGoogleLongitude) {
        this.mediaGoogleLongitude = mediaGoogleLongitude;
    }

    public String getMediaPrice() {
        return mediaPrice;
    }

    public void setMediaPrice(String mediaPrice) {
        this.mediaPrice = mediaPrice;
    }

    public String getMediaPrice6() {
        return mediaPrice6;
    }

    public void setMediaPrice6(String mediaPrice6) {
        this.mediaPrice6 = mediaPrice6;
    }

    public String getMediaPrice12() {
        return mediaPrice12;
    }

    public void setMediaPrice12(String mediaPrice12) {
        this.mediaPrice12 = mediaPrice12;
    }

    public String getMediaImgLocation() {
        return mediaImgLocation;
    }

    public void setMediaImgLocation(String mediaImgLocation) {
        this.mediaImgLocation = mediaImgLocation;
    }

    public String getMediaAvailability() {
        return mediaAvailability;
    }

    public void setMediaAvailability(String mediaAvailability) {
        this.mediaAvailability = mediaAvailability;
    }

    public Object getMediaRating() {
        return mediaRating;
    }

    public void setMediaRating(Object mediaRating) {
        this.mediaRating = mediaRating;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getMediaTotalPrice() {
        return mediaTotalPrice;
    }

    public void setMediaTotalPrice(String mediaTotalPrice) {
        this.mediaTotalPrice = mediaTotalPrice;
    }

    public String getMediaTotalPrice6() {
        return mediaTotalPrice6;
    }

    public void setMediaTotalPrice6(String mediaTotalPrice6) {
        this.mediaTotalPrice6 = mediaTotalPrice6;
    }

    public String getMediaTotalPrice12() {
        return mediaTotalPrice12;
    }

    public void setMediaTotalPrice12(String mediaTotalPrice12) {
        this.mediaTotalPrice12 = mediaTotalPrice12;
    }

}