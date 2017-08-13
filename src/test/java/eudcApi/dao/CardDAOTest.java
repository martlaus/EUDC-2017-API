package eudcApi.dao;

import eudcApi.common.test.DatabaseTestBase;
import eudcApi.model.Card;
import eudcApi.model.User;
import javassist.bytecode.stackmap.TypeData;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

//Created by karl on 16.02.16
public class CardDAOTest extends DatabaseTestBase {

    @Inject
    private CardDAO cardDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private EntityManager entityManager;

    @Inject
    private static final Logger LOGGER = Logger.getLogger(TypeData.ClassName.class.getName());

    @Test
    public void findAll() {
        List<Card> cards = cardDAO.findAll();

        assertNotNull(cards);
    }

    @Test
    public void saveCard() {
        int initialSize = cardDAO.findAll().size();
        Card card = cardDAO.testMakeCard("Lorem Ipsum", "Bug the fuck out");

        assertEquals(initialSize + 1, cardDAO.findAll().size());
    }

    @Test
    public void createUserCards() {
        User user = new User(4L);
        user.setEmail("ashbttnrjnyjn5y57n4u6@lkskl.yi");
        userDAO.saveUser(user);

        List<Card> usersCards = cardDAO.findUsersCards(user);
        cardDAO.createUserCards(user);
        List<Card> usersCards2 = cardDAO.findUsersCards(user);

        assertEquals(usersCards.size() + 3, usersCards2.size());
        userDAO.getUserById(4L);
    }

    @Test
    public void createUserCardsForExistingUsers() {
        User user = userDAO.getUserById(3L);

        List<Card> usersCards = cardDAO.findUsersCards(user);
        cardDAO.createUserCards(user);
        List<Card> usersCards2 = cardDAO.findUsersCards(user);

        assertEquals(usersCards.size() + 1, usersCards2.size());
    }

    @Test
    public void getUsersCard() {
        User user = userDAO.getUserByEmail("admin@admin.kz");

        List<Card> cards = cardDAO.findUsersCards(user);

        assertNotNull(cards);
    }

    @Test
    public void deleteUsersCard() {

        User user = userDAO.getUserByEmail("admin@admin.kz");
        Card cardAboutToGetRekt = cardDAO.testMakeCard("I AM CARD", "I AM GETTING REKT THE FUCK OUT");
        List<Card> userCards1 = cardDAO.findUsersCards(user);
        Long cardAboutToGetRektId = userCards1.get(userCards1.size() - 1).getId();
        System.out.println(userCards1.size());

        cardDAO.deleteUserCard(user, cardAboutToGetRektId);
        System.out.println(userCards1.size());
        List<Card> userCards2 = cardDAO.findUsersCards(user);
        System.out.println(userCards2.size());
        assertTrue(userCards1.size() > userCards2.size());
        assertCardIsDeletedFromCards(userCards2, cardAboutToGetRektId);

    }

    @Test
    public void deleteUserCardAsAdmin() {

        Card cardToBeDeleted = cardDAO.testMakeCard("AM I DEAD?", "PLEASE DON'T TRASH ME!");
        List<Card> allCards1 = cardDAO.findAll();
        Long cardToBeDeletedID = cardToBeDeleted.getId();

        System.out.println(allCards1.get(allCards1.size() - 1).getTitle());

        assertNotNull(cardToBeDeletedID);
        List<Card> allUserCards1 = cardDAO.findCardsByCardId(cardToBeDeletedID);

        cardDAO.deleteUserCardAsAdmin(cardToBeDeletedID);
        assertCardAndUserCardDeleteRelation(allCards1,allUserCards1,cardToBeDeletedID);

        List<Card> allCards2 = cardDAO.findAll();
        List<Card> allUserCards2 = cardDAO.findCardsByCardId(cardToBeDeletedID);
        assertCardIsDeletedFromCards(allUserCards2, cardToBeDeletedID);

        assertTrue(allCards1.size() > allCards2.size());
        assertTrue(allUserCards1.size() > allUserCards2.size());
        assertNotEquals(cardToBeDeletedID, allCards2.get(allCards2.size()-1).getId());
    }

    private void assertValidCard(Card Card) {
        assertNotNull(Card.getId());
        if (Card.getId() == 1) {
            assertEquals("Lorem Ipsum", Card.getTitle());
        } else if (Card.getId() == 2) {
            assertEquals("Ipsum Lorem", Card.getTitle());
        } else {
            fail("Card with unexpected id.");
        }
    }

    private void assertCardAndUserCardDeleteRelation(List<Card> allCards,List<Card> allUserCards,long cardId) {
        assertCardIsDeletedFromCards(allCards, cardId);
        assertCardIsDeletedFromCards(allUserCards, cardId);
    }

    private boolean assertCardIsDeletedFromCards(List<Card> cards, long cardId) {
        return cards.stream().noneMatch(x -> x.getId().equals(cardId));
    }

}
