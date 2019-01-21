package com.torfinn.demo1.service;

import com.torfinn.demo1.domain.Tur;
import com.torfinn.demo1.domain.TurRating;
import com.torfinn.demo1.repo.TurRatingRepository;
import com.torfinn.demo1.repo.TurRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Mary Ellen Bowman
 */
@RunWith(MockitoJUnitRunner.class)
public class TourRatingServiceTest {

    private static final int CUSTOMER_ID = 123;
    private static final int TOUR_ID = 1;
    private static final int TOUR_RATING_ID = 100;

    @Mock
    private TurRepo tourRepositoryMock;
    @Mock
    private TurRatingRepository tourRatingRepositoryMock;

    @InjectMocks //Autowire TourRatingService(tourRatingRepositoryMock, tourRepositoryMock)
    private TurRatingService service;

    @Mock
    private Tur tourMock;
    @Mock
    private TurRating tourRatingMock;


    /**
     * Mock responses to commonly invoked methods.
     */
    @Before
    public void setupReturnValuesOfMockMethods() {
        when(tourRepositoryMock.findById(TOUR_ID)).thenReturn(Optional.of(tourMock));
        when(tourMock.getId()).thenReturn(TOUR_ID);
        when(tourRatingRepositoryMock.findByTurIdAndCustomerId(TOUR_ID,CUSTOMER_ID)).thenReturn(Optional.of(tourRatingMock));
        when(tourRatingRepositoryMock.findByTurId(TOUR_ID)).thenReturn(Arrays.asList(tourRatingMock));
    }

    /**************************************************************************************
     *
     * Verify the service return value
     *
     **************************************************************************************/
    @Test
    public void lookupRatingById() {
        when(tourRatingRepositoryMock.findById(TOUR_RATING_ID)).thenReturn(Optional.of(tourRatingMock));

        //invoke and verify lookupRatingById
        assertThat(service.lookupRatingById(TOUR_RATING_ID).get(), is(tourRatingMock));
    }

    @Test
    public void lookupAll() {
        when(tourRatingRepositoryMock.findAll()).thenReturn(Arrays.asList(tourRatingMock));

        //invoke and verify lookupAll
        assertThat(service.lookupAll().get(0), is(tourRatingMock));
    }

    @Test
    public void getAverageScore() {
        when(tourRatingMock.getScore()).thenReturn(10);

        //invoke and verify getAverageScore
        assertThat(service.getAverageScore(TOUR_ID), is(10.0));
    }

    @Test
    public void lookupRatings() {
        //create mocks of Pageable and Page (only needed in this test)
        Pageable pageable = mock(Pageable.class);
        Page page = mock(Page.class);
        when(tourRatingRepositoryMock.findByTurId(1, pageable)).thenReturn(page);

        //invoke and verify lookupRatings
        assertThat(service.lookupRatings(TOUR_ID, pageable), is(page));
    }

    /**************************************************************************************
     *
     * Verify the invocation of dependencies.
     *
     **************************************************************************************/

    @Test
    public void delete() {
        //invoke delete
        service.delete(1,CUSTOMER_ID);

        //verify tourRatingRepository.delete invoked
        verify(tourRatingRepositoryMock).delete(any(TurRating.class));
    }

    @Test
    public void rateMany() {
        //invoke rateMany
        service.rateMany(TOUR_ID, 10, new Integer[]{CUSTOMER_ID, CUSTOMER_ID + 1});

        //verify tourRatingRepository.save invoked twice
        verify(tourRatingRepositoryMock, times(2)).save(any(TurRating.class));
    }

    @Test
    public void update() {
        //invoke update
        service.update(TOUR_ID,CUSTOMER_ID,5, "great");

        //verify tourRatingRepository.save invoked once
        verify(tourRatingRepositoryMock).save(any(TurRating.class));

        //verify and tourRating setter methods invoked
        verify(tourRatingMock).setComment("great");
        verify(tourRatingMock).setScore(5);
    }

    @Test
    public void updateSome() {
        //invoke updateSome
        service.updateSome(TOUR_ID, CUSTOMER_ID, 1, "awful");

        //verify tourRatingRepository.save invoked once
        verify(tourRatingRepositoryMock).save(any(TurRating.class));

        //verify and tourRating setter methods invoked
        verify(tourRatingMock).setComment("awful");
        verify(tourRatingMock).setScore(1);
    }

     /**************************************************************************************
     *
     * Verify the invocation of dependencies
     * Capture parameter values.
     * Verify the parameters.
     *
     **************************************************************************************/

     @Test
    public void createNew() {
        //prepare to capture a TourRating Object
        ArgumentCaptor<TurRating> tourRatingCaptor = ArgumentCaptor.forClass(TurRating.class);

        //invoke createNew
        service.createNew(TOUR_ID, CUSTOMER_ID, 2, "ok");

        //verify tourRatingRepository.save invoked once and capture the TourRating Object
        verify(tourRatingRepositoryMock).save(tourRatingCaptor.capture());

        //verify the attributes of the Tour Rating Object
        assertThat(tourRatingCaptor.getValue().getTur(), is(tourMock));
        assertThat(tourRatingCaptor.getValue().getCustomerId(), is(CUSTOMER_ID));
        assertThat(tourRatingCaptor.getValue().getScore(), is(2));
        assertThat(tourRatingCaptor.getValue().getComment(), is("ok"));
    }
}