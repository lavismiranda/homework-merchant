package merchant.service.transactions.view;

import merchant.service.infrastructure.exceptions.ValidationException;
import merchant.service.transactions.application.impl.TransactionService;
import merchant.service.transactions.domain.model.Filter;
import merchant.service.transactions.domain.model.Report;
import merchant.service.transactions.domain.model.Transaction;
import merchant.service.transactions.infraestrutura.dto.list.ListDTO;
import merchant.service.transactions.infraestrutura.dto.report.ReportDTO;
import merchant.service.transactions.infraestrutura.dto.transaction.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionEndpoint {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportDTO> report(@RequestBody Report report) throws ValidationException {
        return ResponseEntity.ok(this.transactionService.report(report));
    }

    @PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListDTO> list(@RequestBody Filter filter) throws ValidationException {
        return ResponseEntity.ok(this.transactionService.list(filter));
    }

    @PostMapping(value = "/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> transaction(@RequestBody Transaction transaction) throws ValidationException{
        return ResponseEntity.ok((this.transactionService.getTransaction(transaction)));
    }
}
