package com.common.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.common.domain.user.QUser.user;

@Repository
public class UserQueryRepository {

    private final JPAQueryFactory query;

    public UserQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public void findByName(String username) {
         query.selectFrom(user)
                .where(user.userName.eq(username))
                .fetchOne();
    }
}
