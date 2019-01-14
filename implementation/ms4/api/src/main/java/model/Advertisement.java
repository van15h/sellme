package model;

import com.google.gson.annotations.Expose;

/**
 * Class to create advertisement objects. Each user can have one or more of these objects.
 * They all will have unique id.
 */
public class Advertisement {

    private int id;
    @Expose
    private String userId;
    @Expose
    private String subject;
    @Expose
    private int price;
    @Expose
    private String description;
    @Expose
    private String contactInfo;

    Advertisement() {

    }
    /**
     * Constructor for Advertisement objects
     * @param userId user id, who created the advertisement
     * @param subject main tags/naming of the advertisement
     * @param price price of advertisement
     * @param description more precise description of the advertisement
     * @param contactInfo contact details of the seller
     */
    public Advertisement(String userId, String subject, int price, String description,
                         String contactInfo) {
        this();
        this.userId = userId;
        this.subject = subject;
        this.price = price;
        this.description = description;
        this.contactInfo = contactInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", userId=" + userId +
                ", subject='" + subject + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
