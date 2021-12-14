package com.odeyalo.music.analog.spotify.checkers;

import com.odeyalo.music.analog.spotify.exceptions.AccessException;
import org.springframework.security.core.Authentication;

public interface AccessChecker<T> {
    /**
     *
     * @param obj object for checking access
     * @param authentication - user that need check
     * @return - true if user have access to this resource
     */
    boolean haveAccess(T obj, Authentication authentication) throws AccessException;

    /**
     *
     * @param obj
     * @param authentication
     * @return true if user can manipulate with object(add something, remove, etc)
     * @throws AccessException
     */
    boolean canManipulate(T obj, Authentication authentication) throws AccessException;
}
