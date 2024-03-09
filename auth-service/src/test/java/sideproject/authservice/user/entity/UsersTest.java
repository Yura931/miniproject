package sideproject.authservice.user.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sideproject.authservice.user.enums.AccountType;
import sideproject.authservice.user.enums.UserRoles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.IntStream;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersTest {

    @PersistenceContext
    EntityManager em;

    Map<String, UUID> uuidMap = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        IntStream.range(0, 10).forEach((i) -> {
            Users user = Users.createUser("email" + i, "password" + i, "nickname" + i, AccountType.EMAIL);
            UserRole.generateNewMemberByRoleUser(user);
            UserRole.generateNewMemberByRoleAdmin(user);

            em.persist(user);
            em.flush();

            if(i == 0)
                uuidMap.put("firstUuid", user.getId());
        });
    }

    @Test
    public void testUsersEntity() throws Exception {
        List resultList = em.createQuery("select u from Users u").getResultList();
        Assertions.assertThat(resultList).size().isEqualTo(10);
    }


    @Test
    void deleteEntityTest() {
        em.createQuery("delete from Users u");
        List resultList = em.createQuery("select u from Users u").getResultList();

        Assertions.assertThat(resultList).size().isEqualTo(0);
    }


    @Test
    void userRoleTest() {
        Users users = em.find(Users.class, uuidMap.get("firstUuid"));
        List<UserRole> roles = users.getRoles();
        Assertions.assertThat(users.getRoles().get(0).getRole()).isEqualTo(UserRoles.ROLE_USER);
        Assertions.assertThat(roles).size().isEqualTo(2);
    }
}

