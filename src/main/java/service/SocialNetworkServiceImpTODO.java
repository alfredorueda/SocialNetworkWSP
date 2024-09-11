package service;

import exception.FriendshipAlreadyExistsException;
import exception.PersonAlreadyExistsException;
import exception.PersonNotExistException;
import model.Person;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SocialNetworkServiceImpTODO implements SocialNetworkService {

    private Map<Person, Set<Person>> personFriendships = new HashMap<>();

    @Override
    public void addPerson(@NotNull Person person) throws PersonAlreadyExistsException {
    }

    @Override
    public Set<Person> getPeople() {
        return new HashSet<>();

    }

    @Override
    public void addFriendship(@NotNull Person p1, @NotNull Person p2) throws PersonNotExistException, FriendshipAlreadyExistsException {

    }

    @Override
    public Set<Person> getFriends(@NotNull Person person) throws PersonNotExistException {

        return new HashSet<>();
    }

    private Set<Person> friends(@NotNull Person person) {
        return new HashSet<>();
    }

    private Set<Person> friendsNotCopy(@NotNull Person person) {
        return new HashSet<>();
    }

    private void checkPersonExists(Person person) throws PersonNotExistException {

    }


    //https://www.cs.usfca.edu/~galles/visualization/BFS.html
    public List<Person> getMinimumPath(Person src, Person dst) throws PersonNotExistException {
        return new ArrayList<>();

    }

    private List<Person> buildPath(Person dst, Map<Person, Person> predecesor, boolean found) {

        return new ArrayList<>();
    }

}
