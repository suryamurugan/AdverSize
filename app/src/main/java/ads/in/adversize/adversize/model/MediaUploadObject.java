package ads.in.adversize.adversize.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suryamurugan on 23/4/18.
 */

public class MediaUploadObject {

    // Basic Details //
    private  String mediaType;
    private String mediaAvailability;
    private String mediaLandmark;
    private String mediaFacing;

    // Media Dimensions and Location//
    private String mediaWidth;
    private String mediaHeight;
    private String mediaLocality;
    private String mediaCity;
    private String mediaState;
    private String mediaGoogleLatitude;
    private String mediaGoogleLongitude;

    // media Price/Sq.ft. (in.Rupess)
    private String mediaPrice3;
    private String mediaPrice6;
    private String mediaPrice12;

    private String mediaImgData;

    private String vendorId;


    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaAvailability() {
        return mediaAvailability;
    }

    public void setMediaAvailability(String mediaAvailability) {
        this.mediaAvailability = mediaAvailability;
    }

    public String getMediaLandmark() {
        return mediaLandmark;
    }

    public void setMediaLandmark(String mediaLandmark) {
        this.mediaLandmark = mediaLandmark;
    }

    public String getMediaFacing() {
        return mediaFacing;
    }

    public void setMediaFacing(String mediaFacing) {
        this.mediaFacing = mediaFacing;
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

    public String getMediaLocality() {
        return mediaLocality;
    }

    public void setMediaLocality(String mediaLocality) {
        this.mediaLocality = mediaLocality;
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

    public String getMediaPrice3() {
        return mediaPrice3;
    }

    public void setMediaPrice3(String mediaPrice3) {
        this.mediaPrice3 = mediaPrice3;
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

    public String getMediaImgData() {
        return mediaImgData;
    }

    public void setMediaImgData(String mediaImgData) {
        this.mediaImgData = mediaImgData;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
