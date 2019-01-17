package com.torfinn.demo1.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class TurRatingPk implements Serializable {
    @ManyToOne
    private Tur tur;

    @Column(insertable = false, updatable = false,nullable = false)
    private Integer customerId;

    public TurRatingPk() {
    }

    /**
     * Fully initialize a Tour Rating Pk
     *
     * @param tur          the tour.
     * @param customerId    the customer identifier.
     */
    public TurRatingPk(Tur tur, Integer customerId) {
        this.tur = tur;
        this.customerId = customerId;
    }

    public Tur getTur() {
        return tur;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TurRatingPk that = (TurRatingPk) o;

        if (!tur.equals(that.tur)) return false;
        return customerId.equals(that.customerId);

    }

    @Override
    public int hashCode() {
        int result = tur.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TurRatingPk{" +
                "tur=" + tur +
                ", customerId=" + customerId +
                '}';
    }
}
