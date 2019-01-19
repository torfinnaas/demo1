package com.torfinn.demo1.domain;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class TurRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tur_id")
    private Tur tur;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;



    protected TurRating() {
        // For Spring
    }




    /**
     * Create a fully initialized TourRating.
     *
     * @param tur         primiary key of a tour
     * @param customerId  and customer id.
     * @param score      Integer score (1-5)
     * @param comment    Optional comment from the customer
     */
    public TurRating(Tur tur, Integer customerId, Integer score, String comment) {
        this.tur = tur;
        this.customerId = customerId;
        this.score = score;
        this.comment = comment;
    }

    /**
     * Create a fully initialized TourRating.
     *
     * @param tur         primiary key of a tour
     * @param customerId  and customer id.
     * @param score      Integer score (1-5)
     */
    public TurRating(Tur tur, Integer customerId, Integer score) {
        this.tur = tur;
        this.customerId = customerId;
        this.score = score;
        this.comment = toComment(score);
    }


    /**
     * Auto Generate a message for a score.
     *
     * @param score
     * @return
     */
    private String toComment(Integer score) {
        switch (score) {
            case 1:return "Terrible";
            case 2:return "Poor";
            case 3:return "Fair";
            case 4:return "Good";
            case 5:return "Great";
            default: return score.toString();
        }
    }




    @Override
    public String toString() {
        return "TurRating{" +
                "TurId=" + tur.getId() +
                ", customerId=" + customerId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurRating that = (TurRating) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tur, that.tur) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(score, that.score) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tur, customerId, score, comment);
    }

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }


    public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Tur getTur() {
        return tur;
    }

    public void setTur(Tur tur) {
        this.tur = tur;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
