package hu.gerviba.webschop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "openings")
public class OpeningEntity implements Serializable {
    
    private static final long serialVersionUID = -5055415932299248831L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private int timeIntervals;
    
    @Column
    private Long dateStart;
    
    @Column
    private Long dateEnd;

    @Column
    private Long orderStart;
    
    @Column
    private Long orderEnd;

    @Column
    private String prUrl;

    @Column(length = 255)
    @Size(max = 255)
    private String feeling;
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private CircleEntity circle;

    @Column
    @Min(0)
    private int maxOrder;

    @Column
    @Min(0)
    private int maxOrderPerHalfHour;

    public OpeningEntity() {}

    public OpeningEntity(Long dateStart, Long dateEnd, Long orderStart, Long orderEnd, String prUrl,
            String feeling, CircleEntity circle, int maxOrder, int maxOrderPerHalfHour) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.orderStart = orderStart;
        this.orderEnd = orderEnd;
        this.prUrl = prUrl;
        this.feeling = feeling;
        this.circle = circle;
        this.maxOrder = maxOrder;
        this.maxOrderPerHalfHour = maxOrderPerHalfHour;
    }

    public Long getId() {
        return id;
    }

    public Long getDateStart() {
        return dateStart;
    }

    public Long getDateEnd() {
        return dateEnd;
    }

    public Long getOrderStart() {
        return orderStart;
    }

    public Long getOrderEnd() {
        return orderEnd;
    }

    public String getPrUrl() {
        return prUrl;
    }

    public String getFeeling() {
        return feeling;
    }

    public CircleEntity getCircle() {
        return circle;
    }

    public int getMaxOrder() {
        return maxOrder;
    }

    public int getMaxOrderPerHalfHour() {
        return maxOrderPerHalfHour;
    }

    public void setTimeIntervals(int timeIntervals) {
        this.timeIntervals = timeIntervals;
    }

    public void setDateStart(Long dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Long dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setOrderStart(Long orderStart) {
        this.orderStart = orderStart;
    }

    public void setOrderEnd(Long orderEnd) {
        this.orderEnd = orderEnd;
    }

    public void setPrUrl(String prUrl) {
        this.prUrl = prUrl;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public void setCircle(CircleEntity circle) {
        this.circle = circle;
    }

    public void setMaxOrder(int maxOrder) {
        this.maxOrder = maxOrder;
    }

    public void setMaxOrderPerHalfHour(int maxOrderPerHalfHour) {
        this.maxOrderPerHalfHour = maxOrderPerHalfHour;
    }
    
}
