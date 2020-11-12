package net.originmobi.pdv.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Rollback
@WebMvcTest
@Transactional
@ActiveProfiles("dev")
public abstract class IntegrationTest {

  @Autowired
  private ObjectMapper objectMapper;
  @PersistenceContext
  private EntityManager entityManager;

  protected <T> T save(T entity) {
    entityManager.persist(entity);
    return entity;
  }

  protected <T> T saveAndFlush(T entity) {
    entityManager.persist(entity);
    entityManager.flush();
    return entity;
  }

  protected void flush() {
    entityManager.flush();
  }
}
