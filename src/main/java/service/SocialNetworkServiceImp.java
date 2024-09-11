package service;

import exception.FriendshipAlreadyExistsException;
import exception.PersonAlreadyExistsException;
import exception.PersonNotExistException;
import model.Person;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SocialNetworkServiceImp implements SocialNetworkService {

    private Map<Person, Set<Person>> personFriendships = new HashMap<>();

    @Override
    public void addPerson(@NotNull Person person) throws PersonAlreadyExistsException {
        if(personFriendships.containsKey(person)){
            throw new PersonAlreadyExistsException(person);
        }
        personFriendships.put(person, new HashSet<>());
    }

    @Override
    public Set<Person> getPeople() {
        return Set.copyOf(personFriendships.keySet());
    }

    @Override
    public void addFriendship(@NotNull Person p1, @NotNull Person p2) throws PersonNotExistException, FriendshipAlreadyExistsException {
        checkPersonExists(p1);
        checkPersonExists(p2);

        Set<Person> p1Friends = personFriendships.get(p1);
        if(p1Friends.contains(p2)){
            throw new FriendshipAlreadyExistsException(p1, p2);
        }
        p1Friends.add(p2);
        //personFriendships.put(p1, p1Friends); //¿Quizás  no hace falta por la referencia?

        Set<Person> p2Friends = personFriendships.get(p2); //¿Quizás no hace falta la comprobación inversa?
        //if(p2Friends.contains(p1)){
        //    throw new FriendshipAlreadyExistsException(p2, p1);
        //}
        p2Friends.add(p1);
        //personFriendships.put(p2, p2Friends); //¿Quizás no hace falta por la referencia?
    }

    @Override
    public Set<Person> getFriends(@NotNull Person person) throws PersonNotExistException {
        checkPersonExists(person);
        return friends(person);
    }

    private Set<Person> friends(@NotNull Person person) {
        return Set.copyOf(personFriendships.get(person));
    }

    private Set<Person> friendsNotCopy(@NotNull Person person) {
        return personFriendships.get(person);
    }

    private void checkPersonExists(Person person) throws PersonNotExistException {
        if (!personFriendships.containsKey(person)){
            throw new PersonNotExistException(person);
        }
    }


    //https://www.cs.usfca.edu/~galles/visualization/BFS.html
    public List<Person> getMinimumPath(Person src, Person dst) throws PersonNotExistException {
        checkPersonExists(src);
        checkPersonExists(dst);

        Person currentPerson = src;

        Set<Person> visitedPeople = new HashSet<>();
        Map<Person, Person> predecesor = new HashMap<>();
        Queue<Person> bsfQueue = new LinkedList();

        bsfQueue.add(currentPerson);
        visitedPeople.add(currentPerson);

        boolean found = false;

        while (!bsfQueue.isEmpty()) {
            currentPerson = bsfQueue.poll();
            if (currentPerson.equals(dst)) {
                found = true;
                break;
            } else {
                for (Person friend : friendsNotCopy(currentPerson)) {
                    if (!visitedPeople.contains(friend)) {
                        bsfQueue.add(friend);
                        visitedPeople.add(friend);
                        predecesor.put(friend, currentPerson);
                    }
                }
            }
        }

        return buildPath(dst, predecesor, found);

    }

    private List<Person> buildPath(Person dst, Map<Person, Person> predecesor, boolean found) {
        List<Person> path = new ArrayList<>();
        if(found){
            for (Person person = dst; person != null; person = predecesor.get(person)) {
                path.add(person);
            }

            Collections.reverse(path);
        }

        return path;
    }

}
