package merchant.service;

import merchant.service.customers.merchant.domain.model.Login;
import merchant.service.customers.merchant.infrastructure.dto.client.ClientDTO;
import merchant.service.customers.merchant.infrastructure.dto.client.CustomerInfoDTO;
import merchant.service.customers.merchant.infrastructure.dto.merchant.VerifyAuthenticationDTO;
import merchant.service.transactions.domain.model.Filter;
import merchant.service.transactions.domain.model.Report;
import merchant.service.transactions.domain.model.Transaction;
import merchant.service.transactions.infraestrutura.dto.list.InfoDTO;
import merchant.service.transactions.infraestrutura.dto.list.ListDTO;
import merchant.service.transactions.infraestrutura.dto.report.ReportDTO;
import merchant.service.transactions.infraestrutura.dto.report.ResponseDTO;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;

public class ObjectTests {

    public ObjectTests() throws ParseException {
    }

    public static Login getLogin(){
        return new Login("demo@financialhouse.io","cjaiU8CV");
    }

    public static Login getFakeLogin(){
        return new Login("demo@fail.io","cjaiU8CVerror");
    }

    public static Transaction getTransaction(){
        return new Transaction("997877-1517822176-3");
    }

    public static ClientDTO getClient(){
        return new ClientDTO(getCustomerInfo());
    }

    private static CustomerInfoDTO getCustomerInfo(){
        return new CustomerInfoDTO("0000000000000000","john@doe.com","John","Doe");
    }

    public static Report getReport() throws ParseException {
        Date from = DateUtils.parseDateStrictly("2000-07-01",
                new String[] {"yyyy-MM-dd"});
        Date to = DateUtils.parseDate("2015-10-01",
                new String[] {"yyyy-MM-dd"});
        return new Report(from,to,3,3,"CREDITCARD");
    }

    public static Report getReportNOK() throws ParseException {
        Date from = DateUtils.parseDateStrictly("2021-07-01",
                new String[] {"yyyy-MM-dd"});
        Date to = DateUtils.parseDate("2015-10-01",
                new String[] {"yyyy-MM-dd"});
        return new Report(from,to,0,0,"CREDITCARD");
    }

    public static Transaction getFakeTransaction(){
        return new Transaction("997877-1517822176-3-5");
    }

    public static Filter getFilterRangeNOK() throws ParseException {
        Date from = DateUtils.parseDateStrictly("2015-07-01",
                new String[] {"yyyy-MM-dd"});
        Date to = DateUtils.parseDate("2015-10-01",
                new String[] {"yyyy-MM-dd"});
        Filter filter = new Filter();
        filter.setFromDate(from);
        filter.setToDate(to);
        return filter;
    }

    public static Filter getFilterRangeOK() throws ParseException {
        Date from = DateUtils.parseDateStrictly("2000-07-01",
                new String[] {"yyyy-MM-dd"});
        Date to = DateUtils.parseDate("2021-10-01",
                new String[] {"yyyy-MM-dd"});
        Filter filter = new Filter();
        filter.setFromDate(from);
        filter.setToDate(to);
        return filter;
    }

}
