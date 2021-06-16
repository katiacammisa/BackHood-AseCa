package edu.austral.aseca.app.respository;

import edu.austral.aseca.app.models.Receipt;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Optional;

public class EmptyReceiptRepository implements ReceiptRepository {
  
  @Override
  public <S extends Receipt> Iterable<S> saveAll(Iterable<S> entities) {
    return Collections.emptyList();
  }
  
  @Override
  public Optional<Receipt> findById(Long aLong) {
    return Optional.empty();
  }
  
  @Override
  public boolean existsById(Long aLong) {
    return true;
  }
  
  @Override
  public Iterable<Receipt> findAll() {
    return Collections.emptyList();
  }
  
  @Override
  public Iterable<Receipt> findAllById(Iterable<Long> longs) {
    return Collections.emptyList();
  }
  
  @Override
  public long count() {
    return 0;
  }
  
  @Override
  public <S extends Receipt> S save(S entity) {
    return (S) Receipt.empty();
  }
  
  @Override
  public void deleteById(Long aLong) {
  
  }
  
  @Override
  public void delete(Receipt entity) {
  
  }
  
  @Override
  public void deleteAll(Iterable<? extends Receipt> entities) {
  
  }
  
  @Override
  public void deleteAll() {
  
  }
}
