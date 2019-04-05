package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    private final TripDAO tripDao;

    public TripService(TripDAO tripDao) {
        this.tripDao = tripDao;
    }

    public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
        validate(loggedUser);

        if (user.isFriendWith(loggedUser)) {
            return tripDao.tripsByUser(user);
        } else {
            return Collections.emptyList();
        }
    }

    private void validate(User loggedUser) {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
    }


}
