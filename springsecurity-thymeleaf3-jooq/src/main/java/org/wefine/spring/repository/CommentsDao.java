package org.wefine.spring.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.wefine.spring.config.jooq.JooqDaoImpl;
import org.wefine.spring.jooq.tables.pojos.Comments;
import org.wefine.spring.jooq.tables.records.CommentsRecord;

@Repository
public class CommentsDao extends JooqDaoImpl<CommentsRecord, Comments, Integer> {

    @Autowired
    public CommentsDao(DSLContext dsl) {
        super(org.wefine.spring.jooq.tables.Comments.COMMENTS, Comments.class, dsl);
    }

    @Override
    protected Integer getId(Comments object) {
        return object.getId();
    }
}
