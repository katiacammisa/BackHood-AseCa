package edu.austral.aseca.app.respository;

import edu.austral.aseca.app.models.Receipt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

}
