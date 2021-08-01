package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "notifications")
public class Notification {

    protected Notification() {

    }

    private int id;
    private String ruleName;
    private String marketName;
    private double price;
    private Calendar sentAt;

    public void setId(int id) {
        this.id = id;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSentAt(Calendar sentAt) {
        this.sentAt = sentAt;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getId() {
        return id;
    }

    @Column(name = "rule_name")
    public String getRuleName() {
        return ruleName;
    }

    @Column(name = "market_name")
    public String getMarketName() {
        return marketName;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_at")
    public Calendar getSentAt() {
        return sentAt;
    }

    public static class Builder {
        private final Notification notification;

        public Builder() {
            this.notification = new Notification();
        }

        public Builder ruleName(String ruleName) {
            notification.setRuleName(ruleName);
            return this;
        }

        public Builder marketName(String marketName) {
            notification.setMarketName(marketName);
            return this;
        }

        public Builder price(double price) {
            notification.setPrice(price);
            return this;
        }

        public Notification build() {
            Calendar sentAt = Calendar.getInstance();
            sentAt.setTimeInMillis(System.currentTimeMillis());
            notification.setSentAt(sentAt);
            return notification;
        }
    }
}
