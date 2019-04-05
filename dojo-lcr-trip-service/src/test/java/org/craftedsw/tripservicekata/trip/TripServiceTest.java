package org.craftedsw.tripservicekata.trip;

import org.assertj.core.api.Assertions;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.craftedsw.tripservicekata.user.UserBuilder.aUser;

public class TripServiceTest {

    private static final User UNUSED_USER = null;
    private static final User ANOTHER_USER = new User();
    private static final Trip TO_BRAZIL = new Trip();
    private static final User GUEST = null;
    private static final User LOGGED_IN_USER = new User();
    private static final Trip TO_LONDON = new Trip();

    private TripDAO tripDAO = new TripDAO() {
        @Override
        public List<Trip> tripsByUser(User user){
            return Arrays.asList(TO_BRAZIL, TO_LONDON);
        }
    };

    private TripService tripService = new TripService(tripDAO);

    @Test
    public void getTripsByUser_with_logged_user_and_is_firend_return_trips() {
        //GIVEN
        User friend = aUser()
                .friendsWith(ANOTHER_USER, LOGGED_IN_USER)
                .withTrips(TO_BRAZIL, TO_LONDON)
                .build();

        //WHEN
        List<Trip> friendTrips = tripService.getTripsByUser(friend, LOGGED_IN_USER);

        //THEN
        Assertions.assertThat(friendTrips).containsExactly(TO_BRAZIL, TO_LONDON);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void getTripsByUser_with_unlogged_user_throw_exception() {
        //WHEN
        tripService.getTripsByUser(UNUSED_USER, GUEST);

        //NOT EXPECTED
        Assertions.fail("An exception should have been throwed.");
    }

    @Test
    public void getTripsByUser_with_logged_user_but_not_friends_return_no_trips() {
        //GIVEN
        User friend = aUser()
                .friendsWith(ANOTHER_USER)
                .withTrips(TO_BRAZIL)
                .build();

        //WHEN
        List<Trip> friendTrips = tripService.getTripsByUser(friend, LOGGED_IN_USER);

        //THEN
        Assertions.assertThat(friendTrips).isEmpty();
    }

}
