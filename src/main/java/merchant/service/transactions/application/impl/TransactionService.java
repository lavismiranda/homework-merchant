package merchant.service.transactions.application.impl;

import lombok.extern.slf4j.Slf4j;
import merchant.service.infrastructure.exceptions.ValidationException;
import merchant.service.transactions.application.TransactionServiceFacade;
import merchant.service.transactions.domain.model.Filter;
import merchant.service.transactions.domain.model.Report;
import merchant.service.transactions.domain.model.Transaction;
import merchant.service.transactions.infraestrutura.TransactionGateway;
import merchant.service.transactions.infraestrutura.dto.list.ListDTO;
import merchant.service.transactions.infraestrutura.dto.report.ReportDTO;
import merchant.service.transactions.infraestrutura.dto.transaction.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class TransactionService implements TransactionServiceFacade {

    private TransactionGateway transactionGateway;

    public TransactionService(TransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public ReportDTO report(Report report) throws ValidationException {
        ReportDTO reportDTO = this.transactionGateway.report(report);
        if(!reportDTO.getStatus().equals("APPROVED")){
            throw new ValidationException(40402,"report not found");
        }
        return reportDTO;
    }

    @Override
    public ListDTO list(Filter filter) throws ValidationException {
        ListDTO listDTO = this.transactionGateway.list(filter);
        if(listDTO != null && listDTO.getData().isEmpty()){
            throw new ValidationException(40406,"list not found");
        }
        return listDTO;
    }

    @Override
    public TransactionDTO getTransaction(Transaction transaction) throws ValidationException {
        TransactionDTO transactionDTO = this.transactionGateway.getBy(transaction);
        if(transactionDTO.getCustomerInfo() == null){
            throw new ValidationException(40402,"transaction not found");
        }
        return transactionDTO;
    }
}
