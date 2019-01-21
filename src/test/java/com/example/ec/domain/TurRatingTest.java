package com.torfinn.demo1.domain;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by Mary Ellen Bowman
 */
public class TurRatingTest {

    private Tur tur = new Tur("title","description", 50,
            new TurPakke("CC","name"), Grad.Difficult);

    @Test
    public void testConstructor1() throws Exception {
        TurRating rating = new TurRating(tur, 1, 1, "comment");
        testIt(rating);
        assertThat(rating.getComment(), is("comment"));
    }
    @Test
    public void testConstructor2() throws Exception {
        TurRating rating = new TurRating(tur, 1, 1);
        testIt(rating);
        assertThat(rating.getComment(), is("Terrible"));
    }

    private void testIt(TurRating rating){
        assertThat(rating.getId(), is(nullValue()));
        assertThat(rating.getTur(), is(tur));
        assertThat(rating.getScore(), is(1));
        assertThat(rating.getCustomerId(), is(1));
    }

    @Test
    public void equalsHashcodeVerify() {
        TurRating rating1 = new TurRating(tur, 1, 1, "comment");
        TurRating rating2 = new TurRating(tur, 1, 1, "comment");

        assertEquals(rating1,rating2);
        assertEquals(rating1.hashCode(), rating2.hashCode());
    }
}