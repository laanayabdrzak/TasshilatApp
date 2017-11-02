package m2t.com.tashilatappprototype.common.pojo;

/**
 * Created by laanaya on 2/15/17.
 */

public class ContactUs {

    private String addressContactus;
    private String contactusId;
    private String faxContactus;
    private String mailContactus;
    private String phoneContactus;
    private String siteContactus;
    private String statusContactus;
    private String webSiteContactus;

    public ContactUs() {
    }

    public ContactUs(String contactusId, String addressContactus, String faxContactus, String mailContactus, String phoneContactus, String siteContactus, String statusContactus, String webSiteContactus) {
        this.contactusId = contactusId;
        this.addressContactus = addressContactus;
        this.faxContactus = faxContactus;
        this.mailContactus = mailContactus;
        this.phoneContactus = phoneContactus;
        this.siteContactus = siteContactus;
        this.statusContactus = statusContactus;
        this.webSiteContactus = webSiteContactus;
    }

    public String getAddressContactus() {
        return addressContactus;
    }

    public void setAddressContactus(String addressContactus) {
        this.addressContactus = addressContactus;
    }

    public String getContactusId() {
        return contactusId;
    }

    public void setContactusId(String contactusId) {
        this.contactusId = contactusId;
    }

    public String getFaxContactus() {
        return faxContactus;
    }

    public void setFaxContactus(String faxContactus) {
        this.faxContactus = faxContactus;
    }

    public String getMailContactus() {
        return mailContactus;
    }

    public void setMailContactus(String mailContactus) {
        this.mailContactus = mailContactus;
    }

    public String getPhoneContactus() {
        return phoneContactus;
    }

    public void setPhoneContactus(String phoneContactus) {
        this.phoneContactus = phoneContactus;
    }

    public String getSiteContactus() {
        return siteContactus;
    }

    public void setSiteContactus(String siteContactus) {
        this.siteContactus = siteContactus;
    }

    public String getStatusContactus() {
        return statusContactus;
    }

    public void setStatusContactus(String statusContactus) {
        this.statusContactus = statusContactus;
    }

    public String getWebSiteContactus() {
        return webSiteContactus;
    }

    public void setWebSiteContactus(String webSiteContactus) {
        this.webSiteContactus = webSiteContactus;
    }

    @Override
    public String toString() {
        return "ContactUs{" +
                "addressContactus='" + addressContactus + '\'' +
                ", contactusId='" + contactusId + '\'' +
                ", faxContactus='" + faxContactus + '\'' +
                ", mailContactus='" + mailContactus + '\'' +
                ", phoneContactus='" + phoneContactus + '\'' +
                ", siteContactus='" + siteContactus + '\'' +
                ", statusContactus='" + statusContactus + '\'' +
                ", webSiteContactus='" + webSiteContactus + '\'' +
                '}';
    }
}
