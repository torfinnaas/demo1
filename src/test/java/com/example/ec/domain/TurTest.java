package com.torfinn.demo1.domain;

import com.torfinn.demo1.domain.Grad;
import com.torfinn.demo1.domain.Tur;
import com.torfinn.demo1.domain.TurPakke;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Mary Ellen Bowman.
 */
public class TurTest {
    @Test
    public void testConstructorAndGetters() throws Exception {
        TurPakke p = new TurPakke("CC","name");
        Tur tour = new Tur("title","description",50, p, Grad.Difficult);
        assertNull(tour.getId());
        assertThat(tour.getTittel(), is("title"));
        assertThat(tour.getBeskrivelse(), is("description"));
        assertThat(tour.getPris(), is(50));
        assertThat(tour.getTurPakke().getKode(), is("CC"));
        assertThat(tour.getGrad(), is(Grad.Difficult));

    }

    @Test
    public void equalsHashcodeVerify() {
        TurPakke p = new TurPakke("CC","name");
        Tur tur1 = new Tur("title","description",50, p, Grad.Difficult);
        Tur tur2 = new Tur("title","description",50, p, Grad.Difficult);

        assertThat(tur1, is(tur2));
        assertThat(tur1.hashCode(), is(tur2.hashCode()));
    }

}