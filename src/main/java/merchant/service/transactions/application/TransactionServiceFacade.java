package merchant.service.transactions.application;

import merchant.service.infrastructure.exceptions.ValidationException;
import merchant.service.transactions.domain.model.Filter;
import merchant.service.transactions.domain.model.Report;
import merchant.service.transactions.domain.model.Transaction;
import merchant.service.transactions.infraestrutura.dto.list.ListDTO;
import merchant.service.transactions.infraestrutura.dto.report.ReportDTO;
import merchant.service.transactions.infraestrutura.dto.transaction.TransactionDTO;

public interface TransactionServiceFacade {

    ReportDTO report(Report report) throws ValidationException;
    ListDTO list(Filter filter) throws ValidationException;
    TransactionDTO getTransaction(Transaction transaction) throws ValidationException;

}
