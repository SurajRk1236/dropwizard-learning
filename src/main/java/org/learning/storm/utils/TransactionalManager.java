package org.learning.storm.utils;

import com.google.inject.Singleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.function.Function;

@Singleton
public class TransactionalManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionalManager.class);

    private final SessionFactory sessionFactory;

    @Inject
    public TransactionalManager(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <V> V transactional(final Function<Session, V> callback) {
        return transactional(callback, false);
    }

    public <V> V readOnlyTransactional(final Function<Session, V> callback) {
        return transactional(callback, true);
    }

    private <V> V transactional(final Function<Session, V> callback, final boolean readOnly) {
        Session session = null;
        Transaction transaction;

        // We are already in a transaction
        if (ManagedSessionContext.hasBind(sessionFactory)) {
            if (sessionFactory.getCurrentSession().isDefaultReadOnly()) {
                throw new IllegalStateException("Cannot nest writable transaction in a read-only transaction");
            }

            return callback.apply(sessionFactory.getCurrentSession());
        }

        try {
            session = sessionFactory.openSession();
            ManagedSessionContext.bind(session);
            session.setDefaultReadOnly(readOnly);
            transaction = session.beginTransaction();
            try {
                V result = callback.apply(session);
                transaction.commit();
                return result;
            } catch (Exception e) {
                transaction.rollback();
                LOGGER.error("Exception occurred while executing transaction", e);

                throw e;
            }
        } finally {
            if (session != null) {
                session.close();
            }

            ManagedSessionContext.unbind(sessionFactory);
        }
    }
}
