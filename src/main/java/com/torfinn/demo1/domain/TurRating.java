package com.torfinn.demo1.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;


@Entity
public class TurRating {

    @EmbeddedId
    private TurRatingPk pk;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;

    /**
     * Create a fully initialized TourRating.
     *
     * @param pk         primiary key of a tour and customer id.
     * @param score      Integer score (1-5)
     * @param comment    Optional comment from the customer
     */
    public TurRating(TurRatingPk pk, Integer score, String comment) {
        this.pk = pk;
        this.score = score;
        this.comment = comment;
    }

    protected TurRating() {
    }

    @Override
    public String toString() {
        return "TurRating{" +
                "pk=" + pk +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurRating that = (TurRating) o;
        return Objects.equals(pk, that.pk) &&
                Objects.equals(score, that.score) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, score, comment);
    }

    public TurRatingPk getPk() {
        return pk;
    }

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public void setPk(TurRatingPk pk) {
        this.pk = pk;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
