package org.wefine.spring.config.jooq;

import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A generic JooqDao interface for a pojo and a primary key type.
 * <p>
 * This type is implemented by generated JooqDao classes to provide a common API for
 * common actions on POJOs
 *
 * @param <R> The generic record type.
 * @param <P> The generic POJO type.
 * @param <T> The generic primary key type. This is a regular
 *            <code>&lt;T></code> type for single-column keys, or a
 *            {@link Record} subtype for composite keys.
 * @author Lukas Eder
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public interface JooqDao<R extends TableRecord<R>, P, T> {

    /**
     * Expose the {@link RecordMapper} that is used internally by this
     * <code>JooqDao</code> to map from records of type <code>R</code> to POJOs of
     * type <code>P</code>.
     *
     * @return the <code>JooqDao</code>'s underlying <code>RecordMapper</code>
     */
    RecordMapper<R, P> mapper();

    /**
     * Performs an <code>INSERT</code> statement for a given POJO
     *
     * @param object The POJO to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    void insert(P object) throws DataAccessException;

    /**
     * Performs a batch <code>INSERT</code> statement for a given set of POJOs
     *
     * @param objects The POJOs to be inserted
     * @throws DataAccessException if something went wrong executing the query
     * @see #insert(Collection)
     */
    void insert(P... objects) throws DataAccessException;

    /**
     * Performs a batch <code>INSERT</code> statement for a given set of POJOs
     *
     * @param objects The POJOs to be inserted
     * @throws DataAccessException if something went wrong executing the query
     * @see #insert(Object...)
     */
    void insert(Collection<P> objects) throws DataAccessException;

    /**
     * Performs an <code>UPDATE</code> statement for a given POJO
     *
     * @param object The POJO to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    void update(P object) throws DataAccessException;

    /**
     * Performs a batch <code>UPDATE</code> statement for a given set of POJOs
     *
     * @param objects The POJOs to be updated
     * @throws DataAccessException if something went wrong executing the query
     * @see #update(Collection)
     */
    void update(P... objects) throws DataAccessException;

    /**
     * Performs a batch <code>UPDATE</code> statement for a given set of POJOs
     *
     * @param objects The POJOs to be updated
     * @throws DataAccessException if something went wrong executing the query
     * @see #update(Object...)
     */
    void update(Collection<P> objects) throws DataAccessException;

    /**
     * Performs a <code>DELETE</code> statement for a given set of POJOs
     *
     * @param objects The POJOs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     * @see #delete(Collection)
     */
    void delete(P... objects) throws DataAccessException;

    /**
     * Performs a <code>DELETE</code> statement for a given set of POJOs
     *
     * @param objects The POJOs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     * @see #delete(Object...)
     */
    void delete(Collection<P> objects) throws DataAccessException;

    /**
     * Performs a <code>DELETE</code> statement for a given set of IDs
     *
     * @param ids The IDs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     * @see #delete(Collection)
     */
    void deleteById(T... ids) throws DataAccessException;

    /**
     * Performs a <code>DELETE</code> statement for a given set of IDs
     *
     * @param ids The IDs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     * @see #delete(Object...)
     */
    void deleteById(Collection<T> ids) throws DataAccessException;

    /**
     * Checks if a given POJO exists
     *
     * @param object The POJO whose existence is checked
     * @return Whether the POJO already exists
     * @throws DataAccessException if something went wrong executing the query
     */
    boolean exists(P object) throws DataAccessException;

    /**
     * Checks if a given ID exists
     *
     * @param id The ID whose existence is checked
     * @return Whether the ID already exists
     * @throws DataAccessException if something went wrong executing the query
     */
    boolean existsById(T id) throws DataAccessException;

    /**
     * Count all records of the underlying table.
     *
     * @return The number of records of the underlying table
     * @throws DataAccessException if something went wrong executing the query
     */
    long count() throws DataAccessException;

    /**
     * Find all records of the underlying table.
     *
     * @return All records of the underlying table
     * @throws DataAccessException if something went wrong executing the query
     */
    List<P> findAll() throws DataAccessException;

    /**
     * Find a record of the underlying table by ID.
     *
     * @param id The ID of a record in the underlying table
     * @return A record of the underlying table given its ID, or
     * <code>null</code> if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     */
    P findById(T id) throws DataAccessException;

    /**
     * Find records by a given field and a set of values.
     *
     * @param field  The field to compare values against
     * @param values The accepted values
     * @return A list of records fulfilling <code>field IN (values)</code>
     * @throws DataAccessException if something went wrong executing the query
     */
    <Z> List<P> fetch(Field<Z> field, Z... values) throws DataAccessException;

    /**
     * Find a unique record by a given field and a value.
     *
     * @param field The field to compare value against
     * @param value The accepted value
     * @return A record fulfilling <code>field = value</code>, or
     * <code>null</code>
     * @throws DataAccessException This exception is thrown
     *                             <ul>
     *                             <li>if something went wrong executing the query</li>
     *                             <li>if the query returned more than one value</li>
     *                             </ul>
     */
    <Z> P fetchOne(Field<Z> field, Z value) throws DataAccessException;


    /**
     * Find a unique record by a given field and a value.
     *
     * @param field The field to compare value against
     * @param value The accepted value
     * @return A record fulfilling <code>field = value</code>
     * @throws DataAccessException This exception is thrown
     *                             <ul>
     *                             <li>if something went wrong executing the query</li>
     *                             <li>if the query returned more than one value</li>
     *                             </ul>
     */
    <Z> Optional<P> fetchOptional(Field<Z> field, Z value) throws DataAccessException;


    /**
     * Get the underlying table
     */
    Table<R> getTable();

    /**
     * Search with pagination.
     *
     * @param pageable indicate page size, sort, etc
     * @return Pojo's list
     */
    List<P> search(Pageable pageable);

    /**
     * Search with pagination, and condition.
     *
     * @param pageable   indicate page size, sort, etc
     * @param conditionMap search condition
     * @return Pojo's list
     */
    List<P> search(Pageable pageable, Map<String, Object> conditionMap);
}
