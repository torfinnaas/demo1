package com.torfinn.demo1.domain;

import com.torfinn.demo1.domain.TurPakke;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Mary Ellen Bowman
 */
public class TurPakkeTest {
    @Test
    public void testConstructorAndGetters() throws Exception {
        TurPakke p = new TurPakke("CC","name");
        assertThat(p.getNavn(), is("name"));
        assertThat(p.getKode(), is("CC"));
    }

    @Test
    public void equalsHashcodeVerify() {
        TurPakke p1 = new TurPakke("CC","name");
        TurPakke p2 = new TurPakke("CC","name");

        assertThat(p1,is(p2));
        assertThat(p1.hashCode(), is(p2.hashCode()));
    }
}